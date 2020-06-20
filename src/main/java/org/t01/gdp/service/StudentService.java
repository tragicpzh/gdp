package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;
    private final UserMapper userMapper;
    private final SqlMapper sqlMapper;
    private final MajorMapper majorMapper;
    private final CollegeMapper collegeMapper;

    public Student getStudentInfoById(String id) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentIdEqualTo(id);
        return studentMapper.selectByExample(studentExample).get(0);
    }

    public Student getStudent(long id){
        return studentMapper.selectByPrimaryKey(id);
    }

    public String updateState(Student student) {
        String id = student.getStudentId();
        studentMapper.updateByPrimaryKeySelective(student);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentIdEqualTo(id);
        return studentMapper.selectByExample(studentExample).get(0).getState();
    }

    public Subject getSubjectById(long subjectId) {
        return subjectMapper.selectByPrimaryKey(subjectId);
    }

    public int updateStudentOpeningScore(long teacherId, long studentId, long subjectId, Integer score) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if (subject.getReviewTeacherId1() == teacherId) {
            student.setOpenScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId2() == teacherId) {
            student.setOpenScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId3() == teacherId) {
            student.setOpenScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentMiddleScore(long teacherId, long studentId, Long subjectId, Integer score) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if (subject.getReviewTeacherId1() == teacherId) {
            student.setMiddleScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId2() == teacherId) {
            student.setMiddleScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId3() == teacherId) {
            student.setMiddleScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentConclusionScore(long teacherId, long studentId, Long subjectId,Integer score){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if(subject.getReviewTeacherId1() == teacherId){
            student.setConclusionScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId2() == teacherId){
            student.setConclusionScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId3() == teacherId){
            student.setConclusionScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentTeacherPaperScore(long teacherId, long studentId, long subjectId,Integer score){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if(subject.getCreateTeacherId().equals(teacherId) && student.getSubjectId().equals(subjectId)){
            student = new Student();
            student.setTeacherPaperScore(score);
            student.setId(studentId);

            return studentMapper.updateByPrimaryKeySelective(student);
        }
        return -1;
    }

//    public int updateStudentCrossPaperScore(String reviewStudentId, String studentId, Integer score){
//        Student student = studentMapper.selectByPrimaryKey(reviewStudentId);
//        if(student.getCrossStudentId().equals(studentId)){
//            student = new Student();
//            student.setStudentPaperScore(score);
//
//            StudentExample studentExample = new StudentExample();
//            StudentExample.Criteria criteria = studentExample.createCriteria();
//            criteria.andIdEqualTo(studentId);
//
//            return studentMapper.updateByExampleSelective(student,studentExample);
//        }
//        return -1;
//    }

    public void updateStudentOpenReport(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setOpenDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void updateStudentMiddleReport(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setMiddleDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void updateStudentConclusionReport(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setConclusionDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void updatePaper(Long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setPaperDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public Map<String,Object> simpleSelect(Long student_id){
        Map<String,Object> map=new HashMap<String,Object>();
        Student student=studentMapper.selectByPrimaryKey(student_id);
        Major major=majorMapper.selectByPrimaryKey(student.getMajorId());
        College college=collegeMapper.selectByPrimaryKey(major.getCollegeId());
        Subject subject=subjectMapper.selectByPrimaryKey(student.getSubjectId());
        map.put("name",student.getName());
        map.put("college",college.getName());
        map.put("major",major.getName());
        map.put("email",student.getEmail());
        map.put("telephone",student.getPhoneNumber());
        map.put("subject",subject.getName());
        return  map;
    }

    public Map<String,Object> ToDoList(Long student_id){
        Map<String,Object>map=new HashMap<String,Object>();
        Student student=studentMapper.selectByPrimaryKey(student_id);
        map.put("emailexsit",(student.getEmail()!=null)?true:false);
        map.put("headexsit",(student.getHeadPortrait()!=null)?true:false);
        map.put("telephoneexsit",(student.getPhoneNumber()!=null)?true:false);

        Boolean subject=true,open=true,mid=true,con=true,paper=true;
        String state=student.getState();
        switch(state){
            case "NO_SELECTION":
                subject=false;
                break;
            case "NoOpenDoc":
                open=false;
                break;
            case "NoMidDoc":
                mid=false;
                break;
            case "NoPaperDoc":
                paper=false;
                break;
            case "NoConDoc":
                con=false;
                break;
        }

        map.put("subjectDoc",subject);
        map.put("openDoc",open);
        map.put("midDoc",mid);
        map.put("conDoc",con);
        map.put("paperDoc",paper);
        return map;
    }

    public Map<String,Object> getScore(Long student_id){
        Map<String,Object>map=new HashMap<String,Object>();
        Student student=studentMapper.selectByPrimaryKey(student_id);
       // map.put("open",(student.getOpenScore1()));
        return map;
    }
}
