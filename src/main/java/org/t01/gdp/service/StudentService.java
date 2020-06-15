package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SubjectMapper subjectMapper;

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

    public boolean cross_review_create(List<Student> list){
        if(list.size()<=1)return false;
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.valueOf(o1.getMajorId())-Integer.valueOf(o2.getMajorId());
            }
        });
        int start_id=-1;
        Random random=new Random();
        for (int i=0;i<list.size();i++){
            Student student=list.get(i);
            if(i>0){
                Student student1=list.get(i-1);
                if(student.getMajorId().equals(student1.getMajorId())){
                    student.setCrossStudentId(list.get(random.nextInt(i-start_id)+start_id).getId());
                }
                else{
                    start_id=i;
                }
            }
            else {
                start_id=0;
            }
        }
        return true;
    }
}
