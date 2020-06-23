package org.t01.gdp.service;

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

    public Map<String,Object> toDoList(Long teacherId){
        Map<String,Object>map=new HashMap<>();
        Teacher teacher=teacherMapper.selectByPrimaryKey(teacherId);
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
        subjectExample.createCriteria().andReviewTeacherId1EqualTo(teacherId);
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
        subjectExample.createCriteria().andReviewTeacherId2EqualTo(teacherId);
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
        subjectExample.createCriteria().andReviewTeacherId3EqualTo(teacherId);
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
        subjectExample.createCriteria().andCrossReviewTeacherEqualTo(teacherId);
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
        subjectExample.createCriteria().andCreateTeacherIdEqualTo(teacherId);
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
