package org.t01.gdp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.Major;
import org.t01.gdp.domain.MajorExample;

@Repository
public interface MajorMapper {
    long countByExample(MajorExample example);

    int deleteByExample(MajorExample example);

    int deleteByPrimaryKey(String id);

    int insert(Major record);

    int insertSelective(Major record);

    List<Major> selectByExample(MajorExample example);

    Major selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Major record, @Param("example") MajorExample example);

    int updateByExample(@Param("record") Major record, @Param("example") MajorExample example);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
}