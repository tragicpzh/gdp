package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.StudentAndSubject;

import java.util.List;

@Repository
public interface StudentAndSubjectMapper {
    List<StudentAndSubject> selectByCreateTeacherId(long id);
    List<StudentAndSubject> selectByCrossReviewTeacherId(long id);
    List<StudentAndSubject> selectByPaperReviewTeacherId(long id);
    List<StudentAndSubject> selectByOpenReviewTeacherId(long id);
    List<StudentAndSubject> selectByMiddleReviewTeacherId(long id);
    List<StudentAndSubject> selectByConclusionReviewTeacherId(long id);
}