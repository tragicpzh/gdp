package org.t01.gdp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class AdministratorService {
    private final Logger LOG = LoggerFactory.getLogger(AdministratorService.class);

    @Autowired
    AdministratorMapper administratorMapper;
    @Autowired
    FileService fileService;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    SqlMapper sqlMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MonitorService monitorService;
    @Autowired
    MajorMapper majorMapper;

    public void getUserInfo(long id){
        administratorMapper.selectByPrimaryKey(id);
    }

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long administratorId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/administrator/" + administratorId + '/';
        fileService.uploadUserImage(path, multipartFile);
    }

    public Map<String,Object> simpleSelect(Long adminId){
        Map<String,Object> map=new HashMap<>();
        Administrator administrator=administratorMapper.selectByPrimaryKey(adminId);
        map.put("name",administrator.getName());
        map.put("email",administrator.getEmail());
        map.put("telephone",administrator.getPhoneNumber());
        return map;
    }

    public Map<String,Object> toDoList(Long adminId){
        Map<String,Object> map=new HashMap<>();
        Administrator administrator=administratorMapper.selectByPrimaryKey(adminId);
        map.put("emailexsit",administrator.getEmail()!=null);
        map.put("headexsit",administrator.getHeadPortrait()!=null);
        map.put("telephoneexsit",administrator.getPhoneNumber()!=null);
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.or().andStateEqualTo("NEW");
        subjectExample.or().andStateEqualTo("MODIFIED");
        List<Subject> subjectList=subjectMapper.selectByExample(subjectExample);
        map.put("subjectExamine",subjectList.isEmpty());
        return  map;
    }

    public Map<String,Object> getLable(){
        Map<String,Object> map=new HashMap<>();
        int cnt=0;
        List<Subject>subjects=subjectMapper.selectByExample(null);
        Calendar calendarNow=Calendar.getInstance();
        for (Subject subject : subjects) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(subject.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
            &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
            &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )cnt++;
        }
        map.put("newSubject",cnt);

        String state=sqlMapper.selectone();
        String subjectState="";
        switch (state) {
            case "NO_SELECTION":
                subjectState+= "选题阶段";
                break;
            case "SELECT_COMPLETE":
                subjectState+= "选题阶段";
                break;
            case "NoOpenDoc":
                subjectState+= "开题阶段";
                break;
            case "WaitOpenScore":
                subjectState+= "开题阶段";
                break;
            case "OpenComplete":
                subjectState+= "开题阶段";
                break;
            case "Working":
                subjectState+= "论文编写阶段";
                break;
            case "NoMidDoc":
                subjectState+= "中期答辩阶段";
                break;
            case "WaitMidScore":
                subjectState+= "中期答辩阶段";
                break;
            case "MidComplete":
                subjectState+= "中期答辩阶段";
                break;
            case "NoPaperDoc":
                subjectState+= "论文评阅阶段";
                break;
            case "WaitPaperScore":
                subjectState+= "论文评阅阶段";
                break;
            case "PaperComplete":
                subjectState+= "论文评阅阶段";
                break;
            case "NoConDoc":
                subjectState+= "毕业答辩阶段";
                break;
            case "WaitConScore":
                subjectState+= "毕业答辩阶段";
                break;
            case "ConComplete":
                subjectState+= "毕业答辩阶段";
                break;
            case "WaitFinalScore":
                subjectState+= "最终阶段";
                break;
            case "Finished":
                subjectState+= "最终阶段";
                break;
            default:
                LOG.warn("未知阶段:{}",state);
        }
        map.put("subjectState",subjectState);

        int sum=0;
        List<Administrator>administrators=administratorMapper.selectByExample(null);
        List<Teacher> teachers=teacherMapper.selectByExample(null);
        List<Student> students=studentMapper.selectByExample(null);
        for (Administrator administrator : administrators) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(administrator.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
                    &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
                    &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        for (Teacher teacher : teachers) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(teacher.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
                    &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
                    &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        for (Student student : students) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(student.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
                    &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
                    &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        map.put("newUser",sum);

        int mem;
        MonitorRecord monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getTotalMemory()!=null){
            mem= (int) ((monitorRecord.getUsedMemory()) *100/ monitorRecord.getTotalMemory());
        }
        else mem=0;
        String mems=String.valueOf(mem);
        mems+="%";
        map.put("memoryCount",mems);
        return map;
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
                    msg = msg + "无法为" + list.get(i).getId() + "号课题分配交叉评阅老师;";
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

    public boolean createReview() {
        List<Subject> subjects=subjectMapper.selectByExample(null);
        String msg="";
        for (Subject subject : subjects) {
            Teacher teacher=teacherMapper.selectByPrimaryKey(subject.getCreateTeacherId());   //获得出题人信息
            Long college_id=teacher.getCollegeId();                                         //获得学院id
            TeacherExample example=new TeacherExample();
            example.createCriteria()
                    .andCollegeIdEqualTo(college_id)
                    .andDirectionEqualTo(subject.getDirection())
                    .andIdNotEqualTo(subject.getCreateTeacherId());                           //根据学院和方向进行查询
            List<Teacher> teachers=teacherMapper.selectByExample(example);
            int size=teachers.size();

            if(size<3){
                TeacherExample simple_example=new TeacherExample();
                simple_example.createCriteria()
                        .andCollegeIdEqualTo(college_id)
                        .andIdNotEqualTo(subject.getCreateTeacherId());                       //根据学院进行查询
                List<Teacher> simple_teachers=teacherMapper.selectByExample(simple_example);
                Set<Long> set=new HashSet<Long>();
                Random r=new Random();
                int simple_size=simple_teachers.size();


                if(size+simple_size<3){
                    msg+="无法为"+subject.getId()+"号课题分配评审团队;";
                    continue;
                }
                else if(size==1){                                                                   //满足方向的老师优先选择
                    set.add(teachers.get(0).getId());
                }
                else if(size==2){
                    set.add(teachers.get(0).getId());
                    set.add(teachers.get(1).getId());
                }

                while(set.size()<3){                                                            //剩余的老师从同学院选出
                    set.add(simple_teachers.get(r.nextInt(simple_size)).getId());
                }

                Iterator<Long> iterator = set.iterator();
                subject.setReviewTeacherId1(iterator.next());
                subject.setReviewTeacherId2(iterator.next());
                subject.setReviewTeacherId3(iterator.next());
            }
            else {
                Set<Integer> set=new HashSet<>();
                Random r=new Random();

                while(set.size()<3){                                                            //从满足学院和方向的老师中随机选择
                    set.add(r.nextInt(size));
                }

                Iterator<Integer> iterator = set.iterator();
                subject.setReviewTeacherId1(teachers.get(iterator.next()).getId());
                subject.setReviewTeacherId2(teachers.get(iterator.next()).getId());
                subject.setReviewTeacherId3(teachers.get(iterator.next()).getId());
            }
            subjectMapper.updateByPrimaryKeySelective(subject);                                 //更新课题信息
        }
        return msg;
    }//自动分配评审团队

}
