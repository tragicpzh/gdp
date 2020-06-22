package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Major;
import org.t01.gdp.domain.MajorExample;
import org.t01.gdp.mapper.MajorMapper;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    MajorMapper majorMapper;

    //通过名字和学院查询专业
    public List<Major> selectMajorByName(String name){
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andNameEqualTo(name);
        return majorMapper.selectByExample(majorExample);
    }

    //通过学院查询专业
    public List<Major> selectMajorByCollege(Long collegeId){
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andCollegeIdEqualTo(collegeId);
        return majorMapper.selectByExample(majorExample);
    }
}
