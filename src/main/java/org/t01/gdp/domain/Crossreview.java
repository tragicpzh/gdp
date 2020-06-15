package org.t01.gdp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Crossreview {
    private Long id;

    private String createTeacher;

    private String stuName;

    private String major;

    private String name;

    private String direction;

    private BigDecimal difficulty;

    private String technology;

    private String describe;

    private String document;

    private String email;

    private String openDocument;

    private String middleDocument;

    private String conclusionDocument;

    private String paperDocument;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenDocument() {
        return openDocument;
    }

    public void setOpenDocument(String openDocument) {
        this.openDocument = openDocument;
    }

    public String getMiddleDocument() {
        return middleDocument;
    }

    public void setMiddleDocument(String middleDocument) {
        this.middleDocument = middleDocument;
    }

    public String getConclusionDocument() {
        return conclusionDocument;
    }

    public void setConclusionDocument(String conclusionDocument) {
        this.conclusionDocument = conclusionDocument;
    }

    public String getPaperDocument() {
        return paperDocument;
    }

    public void setPaperDocument(String paperDocument) {
        this.paperDocument = paperDocument;
    }
}
