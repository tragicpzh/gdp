package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.Teacher;
import org.t01.gdp.domain.TeacherExample;
import org.t01.gdp.mapper.SubjectMapper;
import org.t01.gdp.mapper.TeacherMapper;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SubjectMapper subjectMapper;

    public Teacher selectTeacherById(long teacherId){
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    public int addSubject(Subject subject)
    {
        return subjectMapper.insert(subject);
    }
}
