package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SubjectMapper subjectMapper;

    public int updateStudentOpeningScore(String teacherId, String studentId, Long subjectId,Integer score){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if(subject.getReviewTeacherId1().equals(teacherId)){
            student.setOpenScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId2().equals(teacherId)){
            student.setOpenScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId3().equals(teacherId)){
            student.setOpenScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentMiddleScore(String teacherId, String studentId, Long subjectId,Integer score){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if(subject.getReviewTeacherId1().equals(teacherId)){
            student.setMiddleScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId2().equals(teacherId)){
            student.setMiddleScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId3().equals(teacherId)){
            student.setMiddleScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }
}
