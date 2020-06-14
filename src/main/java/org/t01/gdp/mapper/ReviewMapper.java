package org.t01.gdp.mapper;

import org.springframework.stereotype.Repository;
import org.t01.gdp.domain.PaperReviewInfo;

import java.util.List;

@Repository
public interface ReviewMapper {
    public List<PaperReviewInfo> getPaperReviewInfoForTeacher(String reviewTeacherId);
}
