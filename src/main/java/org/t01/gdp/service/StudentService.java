package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.StudentExample;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;

    public Student getStudentInfoById(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    public String updateState(Student student) {
        String id = student.getId();
        studentMapper.updateByPrimaryKeySelective(student);
        return studentMapper.selectByPrimaryKey(id).getState();
    }

    public Subject getSubjectById(long subjectId) {
        return subjectMapper.selectByPrimaryKey(subjectId);
    }

    public int updateStudentOpeningScore(String teacherId, String studentId, Long subjectId, Integer score) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (subject.getReviewTeacherId1().equals(teacherId)) {
            student.setOpenScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId2().equals(teacherId)) {
            student.setOpenScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId3().equals(teacherId)) {
            student.setOpenScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentMiddleScore(String teacherId, String studentId, Long subjectId, Integer score) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (subject.getReviewTeacherId1().equals(teacherId)) {
            student.setMiddleScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId2().equals(teacherId)) {
            student.setMiddleScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId3().equals(teacherId)) {
            student.setMiddleScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentConclusionScore(String teacherId, String studentId, Long subjectId,Integer score){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if(subject.getReviewTeacherId1().equals(teacherId)){
            student.setConclusionScore1(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId2().equals(teacherId)){
            student.setConclusionScore2(score);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId3().equals(teacherId)){
            student.setConclusionScore3(score);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentTeacherPaperScore(String teacherId, String studentId, Long subjectId,Integer score){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        if(subject.getCreateTeacherId().equals(teacherId)){
            Student student = new Student();
            student.setTeacherPaperScore(score);

            StudentExample studentExample = new StudentExample();
            StudentExample.Criteria criteria = studentExample.createCriteria();
            criteria.andIdEqualTo(studentId);

            return studentMapper.updateByExampleSelective(student,studentExample);
        }
        return -1;
    }
}
