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

//    public boolean cross_review_create(List<Student> list){
//        if(list.size()<=1)return false;
//        Collections.sort(list, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return Integer.valueOf(o1.getMajorId())-Integer.valueOf(o2.getMajorId());
//            }
//        });
//        int start_id=-1;
//        Random random=new Random();
//        for (int i=0;i<list.size();i++){
//            Student student=list.get(i);
//            if(i>0){
//                Student student1=list.get(i-1);
//                if(student.getMajorId().equals(student1.getMajorId())){
//                    student.setCrossStudentId(list.get(random.nextInt(i-start_id)+start_id).getId());
//                }
//                else{
//                    start_id=i;
//                }
//            }
//            else {
//                start_id=0;
//            }
//        }
//        return true;
//    }

//    public Crossreview selectCrossReview(String student_id){
//        StudentExample studentExample=new StudentExample();//取得评阅对象id
//        studentExample.createCriteria().andIdEqualTo(student_id);
//        List<Student> list=studentMapper.selectByExample(studentExample);
//        Student student= (list.size()>0?list.get(0):null);
//
//        UserExample userExample=new UserExample();//取得评阅对象姓名
//        userExample.createCriteria().andIdEqualTo(student.getCrossStudentId());
//        List<User> list1=userMapper.selectByExample(userExample);
//        String name=(list1.size()>0?list1.get(0):null).getName();
//
//        Crossreview crossreview=sqlMapper.selectCrossReview(student.getCrossStudentId());
//        crossreview.setStuName(name);
//        return  crossreview;
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

    public void updatePaper(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setPaperDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

}
