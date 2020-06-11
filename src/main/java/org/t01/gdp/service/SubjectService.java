package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.mapper.SubjectMapper;

@Service
public class SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    public void updateById(Subject subject, String examine_flag) {
            subject.setState(examine_flag);
            subjectMapper.updateByPrimaryKeySelective(subject);
    }

}
