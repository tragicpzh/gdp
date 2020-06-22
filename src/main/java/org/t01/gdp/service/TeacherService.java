package org.t01.gdp.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    MajorMapper majorMapper;
    @Autowired
    SubjectService subjectService;
    @Autowired
    FileService fileService;
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    StudentMapper studentMapper;

    public Teacher selectTeacherById(long teacherId){
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    public int addSubject(Subject subject)
    {
        return subjectMapper.insert(subject);
    }

    public String createReview(Long collegeId, int start, int end, List<CrossreviewInfo> list){
        String msg="";
        Random random=new Random();
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria()
                .andCollegeIdEqualTo(collegeId);
        List<Teacher> teachers=teacherMapper.selectByExample(teacherExample);
        for(int i=start;i<=end;i++) {
            int cnt = 0;
            while (cnt >= 0) {
                cnt++;
                if (cnt > 20) {
                    msg = msg + "无法为" + String.valueOf(list.get(i).getId()) + "号课题分配交叉评阅老师";
                    break;
                }
                int cas = random.nextInt(teachers.size());
                if (teachers.get(cas).getId().equals(list.get(i).getCreateTeacherId())) continue;
                else {
                    list.get(i).setCrossReviewTeacher(teachers.get(cas).getId());
                    break;
                }
            }
        }
        return msg;
    }//分配中的子方法

    public String crossReviewCreate(){
        List<Subject> subjects=subjectMapper.selectByExample(null);

        if(subjects.size()<=1)return "课题数量太少";

        List<CrossreviewInfo> list=new ArrayList<>();        //创建CrossreviewInfo数组，为了加入学院id属性。
        for (Subject subject : subjects) {
            MajorExample majorExample=new MajorExample();
            majorExample.createCriteria()
                    .andIdEqualTo(subject.getMajorId());
            List<Major> majors=majorMapper.selectByExample(majorExample);
            Long collegeId=(!majors.isEmpty()?majors.get(0).getCollegeId():null);        //查询获得学院id

            CrossreviewInfo temp=new CrossreviewInfo();
            temp.setId(subject.getId());
            temp.setCreateTeacherId(subject.getCreateTeacherId());
            temp.setCrossReviewTeacher(null);
            temp.setCollegeId(collegeId);
            list.add(temp);
        }
        Collections.sort(list, new Comparator<CrossreviewInfo>() {
            @Override
            public int compare(CrossreviewInfo o1, CrossreviewInfo o2) {
                return o1.getCollegeId().intValue()-o2.getCollegeId().intValue();
            }
        });     //根据学院ID排序

        int startId=-1;
        int endId=-1;
        String msg="";

        for (int i=0;i<list.size();i++){
            CrossreviewInfo crossreviewInfo=list.get(i);
            if(i>0){
                CrossreviewInfo crossreviewInfo1=list.get(i-1);
                if(crossreviewInfo.getCollegeId().equals(crossreviewInfo1.getCollegeId())){
                    continue;
                }
                else {
                    endId=i-1;
                    Long collegeId=crossreviewInfo1.getCollegeId();
                    msg = msg + createReview(collegeId,startId,endId,list);
                    startId=i;
                }
            }
           else {
                startId=0;
            }
        }                                 //自动分配交叉评审老师，满足同学院非开题老师

        endId=list.size()-1;                                               //为最后一组学院的课题分配交叉评审老师
        Long collegeId=list.get(startId).getCollegeId();
        msg = msg + createReview(collegeId,startId,endId,list);

        for (CrossreviewInfo crossreviewInfo : list) {
            Subject subject=new Subject();
            subject.setCrossReviewTeacher(crossreviewInfo.getCrossReviewTeacher());
            SubjectExample subjectExample=new SubjectExample();
            subjectExample.createCriteria()
                    .andIdEqualTo(crossreviewInfo.getId());
            subjectMapper.updateByExampleSelective(subject,subjectExample);
        }                   //更新subject表中的数据
       return msg;
    }//自动分配交叉评阅老师

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/teacher/" + teacherId + '/';
        fileService.uploadUserImage(path, multipartFile);
    }

    public Map<String,Object> simpleSelect(Long teacherId){
        Map<String,Object> map=new HashMap<>();
        Teacher teacher=teacherMapper.selectByPrimaryKey(teacherId);
        College college=collegeMapper.selectByPrimaryKey(teacher.getCollegeId());
        map.put("name",teacher.getName());
        map.put("college",college.getName());
        map.put("direction",teacher.getDirection());
        map.put("email",teacher.getEmail());
        map.put("telephone",teacher.getPhoneNumber());
        return  map;
    }

    public Map<String,Object> toDoList(Long teacher_id){
        Map<String,Object>map=new HashMap<>();
        Teacher teacher=teacherMapper.selectByPrimaryKey(teacher_id);
        map.put("emailexsit",(teacher.getEmail()!=null)?true:false);
        map.put("headexsit",(teacher.getHeadPortrait()!=null)?true:false);
        map.put("telephoneexsit",(teacher.getPhoneNumber()!=null)?true:false);

        Boolean subjectUp=true;
        Boolean openReview=true;
        Boolean midReview=true;
        Boolean paperReview=true;
        Boolean crossReview=true;
        Boolean conReview=true;
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.createCriteria().andCreateTeacherIdEqualTo(teacher.getId()).andStateEqualTo("RETURN");
        List<Subject> subjects=subjectMapper.selectByExample(subjectExample);
        subjectUp=(!subjects.isEmpty())?false:true;

        //review_teacher_id1
        subjectExample.clear();
        subjectExample.createCriteria().andReviewTeacherId1EqualTo(teacher_id);
        List<Subject> subjects1=subjectMapper.selectByExample(subjectExample);
        for (Subject subject : subjects1) {
            StudentExample studentExample=new StudentExample();
            studentExample.createCriteria().andSubjectIdEqualTo(subject.getId());
            List<Student> students=studentMapper.selectByExample(studentExample);
            for (Student student : students) {
                String state=student.getState();
                if(state.equals("WaitOpenScore")&&student.getOpenScore1()==null)openReview=false;
                if(state.equals("WaitMidScore")&&student.getMiddleScore1()==null)midReview=false;
                if(state.equals("WaitConScore")&&student.getConclusionScore1()==null)conReview=false;
                if(!openReview&&!midReview&&!conReview)break;
            }
            if(!openReview&&!midReview&&!conReview)break;
        }

        //review_teacher_id2
        subjectExample.clear();
        subjectExample.createCriteria().andReviewTeacherId2EqualTo(teacher_id);
        List<Subject> subjects2=subjectMapper.selectByExample(subjectExample);
        for (Subject subject : subjects2) {
            StudentExample studentExample=new StudentExample();
            studentExample.createCriteria().andSubjectIdEqualTo(subject.getId());
            List<Student> students=studentMapper.selectByExample(studentExample);
            for (Student student : students) {
                String state=student.getState();
                if(state.equals("WaitOpenScore")&&student.getOpenScore2()==null)openReview=false;
                if(state.equals("WaitMidScore")&&student.getMiddleScore2()==null)midReview=false;
                if(state.equals("WaitConScore")&&student.getConclusionScore2()==null)conReview=false;
                if(!openReview&&!midReview&&!conReview)break;
            }
            if(!openReview&&!midReview&&!conReview)break;
        }

        //review_teacher_id3
        subjectExample.clear();
        subjectExample.createCriteria().andReviewTeacherId3EqualTo(teacher_id);
        List<Subject> subjects3=subjectMapper.selectByExample(subjectExample);
        for (Subject subject : subjects3) {
            StudentExample studentExample=new StudentExample();
            studentExample.createCriteria().andSubjectIdEqualTo(subject.getId());
            List<Student> students=studentMapper.selectByExample(studentExample);
            for (Student student : students) {
                String state=student.getState();
                if(state.equals("WaitOpenScore")&&student.getOpenScore3()==null)openReview=false;
                if(state.equals("WaitMidScore")&&student.getMiddleScore3()==null)midReview=false;
                if(state.equals("WaitConScore")&&student.getConclusionScore3()==null)conReview=false;
                if(!openReview&&!midReview&&!conReview)break;
            }
            if(!openReview&&!midReview&&!conReview)break;
        }

        //cross_review_teacher
        subjectExample.clear();
        subjectExample.createCriteria().andCrossReviewTeacherEqualTo(teacher_id);
        List<Subject> subjects4=subjectMapper.selectByExample(subjectExample);
        for (Subject subject : subjects4) {
            StudentExample studentExample=new StudentExample();
            studentExample.createCriteria().andSubjectIdEqualTo(subject.getId());
            List<Student> students=studentMapper.selectByExample(studentExample);
            for (Student student : students) {
                String state=student.getState();
                if(state.equals("WaitPaperScore")&&student.getCrossPaperScore()==null)crossReview=false;
                if(!crossReview)break;
            }
            if(!crossReview)break;
        }

        //paper_review_teacher
        subjectExample.clear();
        subjectExample.createCriteria().andCreateTeacherIdEqualTo(teacher_id);
        List<Subject> subjects5=subjectMapper.selectByExample(subjectExample);
        for (Subject subject : subjects5) {
            StudentExample studentExample=new StudentExample();
            studentExample.createCriteria().andSubjectIdEqualTo(subject.getId());
            List<Student> students=studentMapper.selectByExample(studentExample);
            for (Student student : students) {
                String state=student.getState();
                if(state.equals("WaitPaperScore")&&student.getTeacherPaperScore()==null)paperReview=false;
                if(!paperReview)break;
            }
            if(!paperReview)break;
        }

        map.put("subjectUp",subjectUp);
        map.put("openReview",openReview);
        map.put("midReview",midReview);
        map.put("conReview",conReview);
        map.put("crossReview",crossReview);
        map.put("paperReview",paperReview);
        return map;
    }

    public List<Map<String,Object>> getReview(Long teacherId){
        List<Teacher> teachers=teacherMapper.selectByExample(null);
        Map<Long,String> teacherMap=new HashMap<>();
        for (Teacher teacher : teachers) {
            teacherMap.put(teacher.getId(),teacher.getName());
        }
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.or().andReviewTeacherId1EqualTo(teacherId);
        subjectExample.or().andReviewTeacherId2EqualTo(teacherId);
        subjectExample.or().andReviewTeacherId3EqualTo(teacherId);
        List<Subject> subjects=subjectMapper.selectByExample(subjectExample);
        List<Map<String,Object>> list=new ArrayList<>();
        for (Subject subject : subjects) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",subject.getId());
            map.put("name",subject.getName());
            map.put("review1",teacherMap.get(subject.getReviewTeacherId1()));
            map.put("review2",teacherMap.get(subject.getReviewTeacherId2()));
            map.put("review3",teacherMap.get(subject.getReviewTeacherId3()));
            list.add(map);
        }
        return list;
    }
}
