package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

@Service
@RequiredArgsConstructor
public class SubjectProcessService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SubjectMapper subjectMapper;

    public Student getStudentInfoById(String id) {
        return studentMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    public Subject getSubjectById(long id) {
        return subjectMapper.selectByPrimaryKey(id);
    }

    public String updateState(String id, String state) {
        Student student = new Student();
        student.setId(Long.valueOf(id));
        student.setState(state);
        studentMapper.updateByPrimaryKeySelective(student);
        return studentMapper.selectByPrimaryKey(Long.valueOf(id)).getState();
    }
}
