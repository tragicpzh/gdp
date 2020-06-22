package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.SubjectInfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public interface SqlMapper {
    SubjectInfo selectSubjectByPrimaryKey(Long id) ;
    List<HashMap<String, Object>> getUserByRole(String role);
    List<SubjectInfo> selectBycondition(String subject_name, String subject_teacher, String subject_major, Long subject_id, String subject_direction, Integer difficult_min, Integer difficult_max,String subject_state);
    String selectone();
    List<Subject> selectfiveById(Long majorId);
}
