package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Teacher;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface UserMapper {
    Set<String> getAllStudentId();

    Set<String> getAllTeacherId();

    List<Map<String, String>> getAllStudents();

    List<Map<String, String>> getAllTeachers();
}
