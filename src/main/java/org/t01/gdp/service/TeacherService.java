package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.Teacher;
import org.t01.gdp.mapper.SubjectMapper;
import org.t01.gdp.mapper.TeacherMapper;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SubjectMapper subjectMapper;

    public Teacher selectTeacherById(String id){
        return teacherMapper.selectByPrimaryKey(id);
    }

    public int addSubject(Subject subject)
    {
        return subjectMapper.insert(subject);
    }
}