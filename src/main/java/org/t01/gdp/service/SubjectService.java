package org.t01.gdp.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.SubjectExample;
import org.t01.gdp.mapper.SubjectMapper;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

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

}
