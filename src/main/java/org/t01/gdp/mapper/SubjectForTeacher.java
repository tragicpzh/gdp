package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.SubjectBrief;

import java.util.List;
@Repository
public interface SubjectForTeacher {
    List<SubjectBrief> selectByTeacherId(String teacherId);
}
