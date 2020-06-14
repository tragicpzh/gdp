package org.t01.gdp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

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
    SubjectMajorTeacherMapper subjectMajorTeacherMapper;

    public void updateById(Subject subject, String examine_flag) {
        subject.setState(examine_flag);
        subjectMapper.updateByPrimaryKeySelective(subject);
    }

    public int updateWithSubject(Subject subject) {
        return subjectMapper.updateByPrimaryKey(subject);
    }

    public PageInfo<StudentAndSubject> getSubjectsByReviewTeacherId(int pageNo, int pageSize, String reviewTeacherId) {
        PageHelper.startPage(pageNo, pageSize);
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
            if (students.size() > 0) {
                for (Student student : students) {
                    studentAndSubjectList.add(new StudentAndSubject(student.getId(), allSubject.getId().toString(),
                            allSubject.getName(), allSubject.getMajorId(), allSubject.getDirection()));
                }
            }
        }
        return new PageInfo<>(studentAndSubjectList);
    }

    public PageInfo<Subject> getSubjects(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(subjectMapper.selectAll());
    }
    public PageInfo<SubjectMajorTeacher> getSubjectsByTeacherId(int pageNo, int pageSize, String id) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(subjectMajorTeacherMapper.selectByTeacherId(id));
    }

    public void chooseSubjectBystudent(String student_id, String subject_id) {
        Student student = new Student();
        student.setSubjectId(Long.valueOf(subject_id));
        student.setId(student_id);
        studentMapper.updateByPrimaryKeySelective(student);
        return;
    }

    public Subject selectSubjectBystudent(String subject_id) {
        Subject subject;
        subject = subjectMapper.selectByPrimaryKey(Long.valueOf(subject_id));
        return subject;
    }

    public List listSubjectBystudent(Listsubject listsubject) {
        UserExample userExample = new UserExample();                                                     //根据教师名字查询教师id
        userExample.createCriteria().andNameEqualTo(listsubject.getCreate_teacher());
        List<User> userlist = userMapper.selectByExample(userExample);
        String create_teacher_id = (userlist.size() > 0 ? userlist.get(0).getId() : null);

        MajorExample majorExample = new MajorExample();                                                  //根据专业名字查询专业id
        majorExample.createCriteria().andNameEqualTo(listsubject.getMayjor());
        List<Major> majorlist = majorMapper.selectByExample(majorExample);
        String mayjor_id = (majorlist.size() > 0 ? majorlist.get(0).getId() : null);

        SubjectExample subjectExample = new SubjectExample();                                             //条件查询subject
        subjectExample.createCriteria().andIdEqualTo(listsubject.getSubject_id())
                .andCreateTeacherIdEqualTo(create_teacher_id)
                .andMajorIdEqualTo(mayjor_id)
                .andNameLike(listsubject.getName())
                .andDirectionEqualTo(listsubject.getDirection())
                .andDifficultyBetween(listsubject.getDifficult_min(), listsubject.getDifficult_max());
        List<Subject> list = subjectMapper.selectByExample(subjectExample);

        return list;
    }
}
