package org.t01.gdp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MajorMapper majorMapper;

    @Autowired
    SqlMapper sqlMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    SubjectMajorTeacherMapper subjectMajorTeacherMapper;

    public void updateById(Subject subject, String examine_flag) {
            subject.setState(examine_flag);
            subjectMapper.updateByPrimaryKeySelective(subject);
    }

    public int updateWithSubject(Subject subject){
        return subjectMapper.updateByPrimaryKey(subject);
    }

    public PageInfo<StudentAndSubject> getSubjectsByReviewTeacherId(int pageNo, int pageSize, String reviewTeacherId){
        PageHelper.startPage(pageNo,pageSize);
        //查询reviewTeacherId中有一个与传入的id相同的subject
        SubjectExample subjectExample1 = new SubjectExample();
        subjectExample1.createCriteria().andReviewTeacherId1EqualTo(reviewTeacherId);
        List<Subject> subjects1 = subjectMapper.selectByExample(subjectExample1);
        SubjectExample subjectExample2 = new SubjectExample();
        subjectExample2.createCriteria().andReviewTeacherId2EqualTo(reviewTeacherId);
        List<Subject> subjects2 = subjectMapper.selectByExample(subjectExample2);
        SubjectExample subjectExample3 = new SubjectExample();
        subjectExample3.createCriteria().andReviewTeacherId2EqualTo(reviewTeacherId);
        List<Subject> subjects3 = subjectMapper.selectByExample(subjectExample3);
        //所有subject存入一条链表
        List<Subject> allSubjects = new ArrayList<>();
        allSubjects.addAll(subjects1);
        allSubjects.addAll(subjects2);
        allSubjects.addAll(subjects3);
        //生成一个学生与选题的list
        List<StudentAndSubject> studentAndSubjectList = new ArrayList<>();
        for (Subject allSubject : allSubjects) {
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andSubjectIdEqualTo(allSubject.getId());
            List<Student> students = studentMapper.selectByExample(studentExample);
            if(students.size()>0){
                for (Student student : students) {
                    studentAndSubjectList.add(new StudentAndSubject(student.getId(), allSubject.getId().toString(),
                            allSubject.getName(),allSubject.getMajorId(),allSubject.getDirection()));
                }
            }
        }
        return new PageInfo<>(studentAndSubjectList);
    }

    public PageInfo<PaperReviewInfo> getSubjectsToPaperReview(int pageNo, int pageSize, String reviewTeacherId){
        PageHelper.startPage(pageNo,pageSize);

        List<PaperReviewInfo> paperReviewInfoForTeacher = reviewMapper.getPaperReviewInfoForTeacher(reviewTeacherId);

        return new PageInfo<>(paperReviewInfoForTeacher);
    }

    public boolean chooseSubjectByStudent(String student_id, String subject_id){
        Student student=new Student();
        student.setSubjectId(Long.valueOf(subject_id));
        student.setId(student_id);
        studentMapper.updateByPrimaryKeySelective(student);
        return true;
    }

    public PageInfo<SubjectMajorTeacher> getSubjectsByTeacherId(int pageNo, int pageSize, String id) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(subjectMajorTeacherMapper.selectByTeacherId(id));
    }

    public SubjectInfo selectSubjectByStudent(String subject_id){
        SubjectInfo subjectInfo;
        subjectInfo=sqlMapper.selectSubjectByPrimaryKey(Long.valueOf(subject_id));
        return subjectInfo;
    }

    public PageInfo<SubjectInfo> listSubjectBystudent(int pageNo,
                                                      int pageSize,
                                                      String subject_name,
                                                      String subject_teacher,
                                                      String subject_major,
                                                      String subject_ID,
                                                      String subject_direction,
                                                      String difficult_mn,
                                                      String difficult_mx) {
        PageHelper.startPage(pageNo,pageSize);
        BigDecimal difficult_min=(difficult_mn!=""?new BigDecimal(difficult_mn):null);
        BigDecimal difficult_max=(difficult_mx!=""?new BigDecimal(difficult_mx):null);
        Long subject_id=(subject_ID!=""?new Long(subject_ID):null);
        List<SubjectInfo> list=sqlMapper.selectBycondition(
                subject_name,
                subject_teacher,
                subject_major,
                subject_id,
                subject_direction,
                difficult_min,
                difficult_max);
        PageInfo<SubjectInfo> subjectPageInfo=new PageInfo<>(list);
        return subjectPageInfo;
    }
}
