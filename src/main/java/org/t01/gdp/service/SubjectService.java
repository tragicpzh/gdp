package org.t01.gdp.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import javax.jws.soap.SOAPBinding;
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

    public void updateById(Subject subject, String examine_flag) {
            subject.setState(examine_flag);
            subjectMapper.updateByPrimaryKeySelective(subject);
    }

    public int updateWithSubject(Subject subject){
        return subjectMapper.updateByPrimaryKey(subject);
    }

    public String selectIdAfterInsert(Subject subject){
        Subject subjectInDB;
        SubjectExample subjectExample = new SubjectExample();
        subjectExample.createCriteria().andCreateTeacherIdEqualTo(subject.getCreateTeacherId()).andCreateTimeEqualTo(subject.getCreateTime());
        List<Subject> subjectList= subjectMapper.selectByExample(subjectExample);
        if(subjectList.size()>0){
            return subjectList.get(0).getId().toString();
        }
        return null;
    }

    public void chooseSubjectBystudent(String student_id, String subject_id){
        Student student=new Student();
        student.setSubjectId(Long.valueOf(subject_id));
        student.setId(student_id);
        studentMapper.updateByPrimaryKeySelective(student);
        return;
    }

    public Subject selectSubjectBystudent(String subject_id){
        Subject subject;
        subject=subjectMapper.selectByPrimaryKey(Long.valueOf(subject_id));
        return subject;
    }

    public List listSubjectBystudent(Listsubject listsubject){
        UserExample userExample=new UserExample();                                                     //根据教师名字查询教师id
        userExample.createCriteria().andNameEqualTo(listsubject.getCreate_teacher());
        List<User> userlist=userMapper.selectByExample(userExample);
        String create_teacher_id=(userlist.size()>0?userlist.get(0).getId():null);

        MajorExample majorExample=new MajorExample();                                                  //根据专业名字查询专业id
        majorExample.createCriteria().andNameEqualTo(listsubject.getMayjor());
        List<Major> majorlist=majorMapper.selectByExample(majorExample);
        String mayjor_id=(majorlist.size()>0?majorlist.get(0).getId():null);

        SubjectExample subjectExample=new SubjectExample();                                             //条件查询subject
        subjectExample.createCriteria().andIdEqualTo(listsubject.getSubject_id())
                .andCreateTeacherIdEqualTo(create_teacher_id)
                .andMajorIdEqualTo(mayjor_id)
                .andNameLike(listsubject.getName())
                .andDirectionEqualTo(listsubject.getDirection())
                .andDifficultyBetween(listsubject.getDifficult_min(),listsubject.getDifficult_max());
        List<Subject> list=subjectMapper.selectByExample(subjectExample);

        return list;
    }
}
