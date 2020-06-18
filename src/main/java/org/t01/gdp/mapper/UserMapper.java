package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Teacher;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface UserMapper {
    Set<String> getAllIds();

    Set<String> getAllPhoneNumbers();

    Set<String> getAllEmails();

    List<Map<String, Object>> getAllStudents();

    List<Map<String, Object>> getAllTeachers();

    Map<String, Object> getStudentByStudentId(long id);

    Map<String, Object> getTeacherByTeacherID(long id);

    Map<String, Object> getAdminByAdminID(long id);

    int updateStudentByStudentId(String id, String name, String phoneNumber, String email, String otherInfo);

    int updateTeacherByTeacherID(String id, String name, String phoneNumber, String email, String otherInfo);

    int deleteStudentByStudentId(long id);

    int deleteTeacherByTeacherId(long id);
}
