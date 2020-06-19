package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;
    private final UserMapper userMapper;
    private final SqlMapper sqlMapper;

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

}
