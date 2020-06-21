package org.t01.gdp.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

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

    public String create_review(Long college_id,int start,int end,List<CrossreviewInfo> list){
        String msg="";
        Random random=new Random();
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria()
                .andCollegeIdEqualTo(college_id);
        List<Teacher> teachers=teacherMapper.selectByExample(teacherExample);
        for(int i=start;i<=end;i++) {
            int cnt = 0;
            while (cnt >= 0) {
                cnt++;
                if (cnt > 20) {
                    msg += "无法为" + String.valueOf(teachers.get(i).getId()) + "号课题分配交叉评阅老师";
                    break;
                }
                int cas = random.nextInt(teachers.size());
                if (teachers.get(cas).getId().equals(list.get(i).getCreateTeacherId())) continue;
                else {
                    list.get(i).setCrossReviewTeacher(Long.valueOf(cas));
                    break;
                }
            }
        }
        return msg;
    }//分配中的子方法

    public String cross_review_create(List<Subject> subjects){
        if(subjects.size()<=1)return "课题数量太少";

        List<CrossreviewInfo> list=new ArrayList<CrossreviewInfo>();        //创建CrossreviewInfo数组，为了加入学院id属性。
        for (Subject subject : subjects) {
            MajorExample majorExample=new MajorExample();
            majorExample.createCriteria()
                    .andIdEqualTo(subject.getMajorId());
            List<Major> majors=majorMapper.selectByExample(majorExample);
            Long college_id=(majors.size()>0?majors.get(0).getCollegeId():null);        //查询获得学院id

            CrossreviewInfo temp=new CrossreviewInfo();
            temp.setId(subject.getId());
            temp.setCreateTeacherId(subject.getCreateTeacherId());
            temp.setCrossReviewTeacher(null);
            temp.setCollegeId(college_id);
            list.add(temp);
        }
        Collections.sort(list, new Comparator<CrossreviewInfo>() {
            @Override
            public int compare(CrossreviewInfo o1, CrossreviewInfo o2) {
                return o1.getCollegeId().intValue()-o2.getCollegeId().intValue();
            }
        });     //根据学院ID排序

        int start_id=-1;
        int end_id=-1;
        String msg="";

        for (int i=0;i<list.size();i++){
            CrossreviewInfo crossreviewInfo=list.get(i);
            if(i>0){
                CrossreviewInfo crossreviewInfo1=list.get(i-1);
                if(crossreviewInfo.getCollegeId().equals(crossreviewInfo1.getCollegeId())){
                    continue;
                }
                else {
                    end_id=i-1;
                    Long college_id=crossreviewInfo1.getCollegeId();
                    msg+=create_review(college_id,start_id,end_id,list);
                    start_id=i;
                }
            }
           else {
                start_id=0;
            }
        }                                 //自动分配交叉评审老师，满足同学院非开题老师

        end_id=list.size()-1;                                               //为最后一组学院的课题分配交叉评审老师
        Long college_id=list.get(start_id).getCollegeId();
        msg+=create_review(college_id,start_id,end_id,list);

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

    public PageInfo<StudentAndSubject> cross_review_select(ReviewSearchCase reviewSearchCase,int pageNo,int pageSize,Long teacher_id){
        return subjectService.searchReview(reviewSearchCase,pageNo,pageSize);
    }//查看交叉评阅列表

    public Map<String,Object> simpleSelect(Long teacher_id){
        Map<String,Object> map=new HashMap<String,Object>();
        Teacher teacher=teacherMapper.selectByPrimaryKey(teacher_id);
        College college=collegeMapper.selectByPrimaryKey(teacher.getCollegeId());
        map.put("name",teacher.getName());
        map.put("college",college.getName());
        map.put("direction",teacher.getDirection());
        map.put("email",teacher.getEmail());
        map.put("telephone",teacher.getPhoneNumber());
        return  map;
    }

    public Map<String,Object> ToDoList(Long teacher_id){
        Map<String,Object>map=new HashMap<String,Object>();
        Teacher teacher=teacherMapper.selectByPrimaryKey(teacher_id);
        map.put("emailexsit",(teacher.getEmail()!=null)?true:false);
        map.put("headexsit",(teacher.getHeadPortrait()!=null)?true:false);
        map.put("telephoneexsit",(teacher.getPhoneNumber()!=null)?true:false);

        Boolean subjectUp=true,openReview=true,midReview=true,paperReview=true,crossReview=true,conReview=true;
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.createCriteria().andCreateTeacherIdEqualTo(teacher.getId()).andStateEqualTo("RETURN");
        List<Subject> subjects=subjectMapper.selectByExample(subjectExample);
        subjectUp=(subjects.size()>0)?false:true;
        
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
                if(openReview==false&&midReview==false&&conReview==false)break;
            }
            if(openReview==false&&midReview==false&&conReview==false)break;
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
                if(openReview==false&&midReview==false&&conReview==false)break;
            }
            if(openReview==false&&midReview==false&&conReview==false)break;
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
                if(openReview==false&&midReview==false&&conReview==false)break;
            }
            if(openReview==false&&midReview==false&&conReview==false)break;
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
                if(crossReview==false)break;
            }
            if(crossReview==false)break;
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
                if(paperReview==false)break;
            }
            if(paperReview==false)break;
        }

        map.put("subjectUp",subjectUp);
        map.put("openReview",openReview);
        map.put("midReview",midReview);
        map.put("conReview",conReview);
        map.put("crossReview",crossReview);
        map.put("paperReview",paperReview);
        return map;
    }

    public List<Map<String,Object>> getReview(Long teacher_id){
        List<Teacher> teachers=teacherMapper.selectByExample(null);
        Map<Long,String> teacher_map=new HashMap<Long,String>();
        for (Teacher teacher : teachers) {
            teacher_map.put(teacher.getId(),teacher.getName());
        }
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.or().andReviewTeacherId1EqualTo(teacher_id);
        subjectExample.or().andReviewTeacherId2EqualTo(teacher_id);
        subjectExample.or().andReviewTeacherId3EqualTo(teacher_id);
        List<Subject> subjects=subjectMapper.selectByExample(subjectExample);
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for (Subject subject : subjects) {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",subject.getId());
            map.put("name",subject.getName());
            map.put("review1",teacher_map.get(subject.getReviewTeacherId1()));
            map.put("review2",teacher_map.get(subject.getReviewTeacherId2()));
            map.put("review3",teacher_map.get(subject.getReviewTeacherId3()));
            list.add(map);
        }
        return list;
    }
}
