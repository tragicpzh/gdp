package org.t01.gdp.domain;

import java.util.Date;

public class Subject {
    private Long id;

    private Long createTeacherId;

    private Long crossReviewTeacher;

    private Long majorId;

    private String name;

    private String direction;

    private Integer difficulty;

    private String technology;

    private String describe;

    private String document;

    private Long reviewTeacherId1;

    private Long reviewTeacherId2;

    private Long reviewTeacherId3;

    private String state;

    private Date createTime;

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

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology == null ? null : technology.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document == null ? null : document.trim();
    }

    public Long getReviewTeacherId1() {
        return reviewTeacherId1;
    }

    public void setReviewTeacherId1(Long reviewTeacherId1) {
        this.reviewTeacherId1 = reviewTeacherId1;
    }

    public Long getReviewTeacherId2() {
        return reviewTeacherId2;
    }

    public void setReviewTeacherId2(Long reviewTeacherId2) {
        this.reviewTeacherId2 = reviewTeacherId2;
    }

    public Long getReviewTeacherId3() {
        return reviewTeacherId3;
    }

    public void setReviewTeacherId3(Long reviewTeacherId3) {
        this.reviewTeacherId3 = reviewTeacherId3;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}