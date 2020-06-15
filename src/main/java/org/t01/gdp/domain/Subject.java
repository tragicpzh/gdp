package org.t01.gdp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Subject {
    private Long id;

    private String createTeacherId;

    private String majorId;

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

    @Override
    public String toString() {
        return "{"
                + "\"id\":"
                + id
                + ",\"createTeacherId\":\""
                + createTeacherId + '\"'
                + ",\"majorId\":\""
                + majorId + '\"'
                + ",\"name\":\""
                + name + '\"'
                + ",\"direction\":\""
                + direction + '\"'
                + ",\"difficulty\":"
                + difficulty
                + ",\"technology\":\""
                + technology + '\"'
                + ",\"describe\":\""
                + describe + '\"'
                + ",\"document\":\""
                + document + '\"'
                + ",\"reviewTeacherId1\":\""
                + reviewTeacherId1 + '\"'
                + ",\"reviewTeacherId2\":\""
                + reviewTeacherId2 + '\"'
                + ",\"reviewTeacherId3\":\""
                + reviewTeacherId3 + '\"'
                + ",\"state\":\""
                + state + '\"'
                + ",\"createTime\":\""
                + createTime + '\"'
                + "}";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTeacherId() {
        return createTeacherId;
    }

    public void setCreateTeacherId(String createTeacherId) {
        this.createTeacherId = createTeacherId == null ? null : createTeacherId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
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

    public String getReviewTeacherId1() {
        return reviewTeacherId1;
    }

    public void setReviewTeacherId1(String reviewTeacherId1) {
        this.reviewTeacherId1 = reviewTeacherId1 == null ? null : reviewTeacherId1.trim();
    }

    public String getReviewTeacherId2() {
        return reviewTeacherId2;
    }

    public void setReviewTeacherId2(String reviewTeacherId2) {
        this.reviewTeacherId2 = reviewTeacherId2 == null ? null : reviewTeacherId2.trim();
    }

    public String getReviewTeacherId3() {
        return reviewTeacherId3;
    }

    public void setReviewTeacherId3(String reviewTeacherId3) {
        this.reviewTeacherId3 = reviewTeacherId3 == null ? null : reviewTeacherId3.trim();
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