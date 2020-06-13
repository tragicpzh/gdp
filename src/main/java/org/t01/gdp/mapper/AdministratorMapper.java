package org.t01.gdp.mapper;

import org.t01.gdp.domain.Administrator;

public interface AdministratorMapper {
    int deleteByPrimaryKey(String id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);
}