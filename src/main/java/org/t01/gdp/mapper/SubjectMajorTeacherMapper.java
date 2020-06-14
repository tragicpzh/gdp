package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.SubjectMajorTeacher;

import java.util.List;
@Repository
public interface SubjectMajorTeacherMapper {
    List<SubjectMajorTeacher> selectByTeacherId(String id);
}
