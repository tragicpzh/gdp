package org.t01.gdp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SubjectInfo {
    private Long id;

    private String createTeacher;

    private String major;

    private String name;

    private String direction;

    private BigDecimal difficulty;

    private String technology;

    private String describe;

    private String document;

    private String reviewTeacherId1;

    private String reviewTeacherId2;

    private String reviewTeacherId3;

    private String state;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTeacher() {
        return createTeacher;
    }

    public void setCreateTeacher(String createTeacher) {
        this.createTeacher = createTeacher;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getReviewTeacherId1() {
        return reviewTeacherId1;
    }

    public void setReviewTeacherId1(String reviewTeacherId1) {
        this.reviewTeacherId1 = reviewTeacherId1;
    }

    public String getReviewTeacherId2() {
        return reviewTeacherId2;
    }

    public void setReviewTeacherId2(String reviewTeacherId2) {
        this.reviewTeacherId2 = reviewTeacherId2;
    }

    public String getReviewTeacherId3() {
        return reviewTeacherId3;
    }

    public void setReviewTeacherId3(String reviewTeacherId3) {
        this.reviewTeacherId3 = reviewTeacherId3;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
