package org.t01.gdp.domain;

public class CrossreviewInfo {
    private Long id;
    private Long createTeacherId;
    private Long crossReviewTeacher;
    private Long collegeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTeacherId() {
        return createTeacherId;
    }

    public void setCreateTeacherId(Long createTeacherId) {
        this.createTeacherId = createTeacherId;
    }

    public Long getCrossReviewTeacher() {
        return crossReviewTeacher;
    }

    public void setCrossReviewTeacher(Long crossReviewTeacher) {
        this.crossReviewTeacher = crossReviewTeacher;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
}
