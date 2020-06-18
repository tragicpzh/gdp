package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.mapper.AdministratorMapper;

@Service
public class AdministratorService {
    @Autowired
    AdministratorMapper administratorMapper;

    public void getUserInfo(long id){
        administratorMapper.selectByPrimaryKey(id);
    }
}
