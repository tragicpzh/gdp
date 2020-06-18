package org.t01.gdp.mapper;

import org.t01.gdp.domain.StudentAndSubject;
import org.t01.gdp.domain.StudentExample;

import java.util.List;

public interface StudentAndSubjectMapper {
    List<StudentAndSubject> selectByCreateTeacherId(long id);
    List<StudentAndSubject> selectByCrossReviewTeacherId(long id);
    List<StudentAndSubject> selectByReviewTeacherId(long id);
}