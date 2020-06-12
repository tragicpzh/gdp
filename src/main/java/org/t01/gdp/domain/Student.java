package org.t01.gdp.domain;

import java.math.BigDecimal;

public class Student {
    private String id;

    private String majorId;

    private String state;

    private String email;

    private String phoneNumber;

    private Long subjectId;

    private String crossStudentId;

    private String openDocument;

    private String middleDocument;

    private String conclusionDocument;

    private String paperDocument;

    private Integer openScore1;

    private Integer openScore2;

    private Integer openScore3;

    private Integer middleScore1;

    private Integer middleScore2;

    private Integer middleScore3;

    private Integer conclusionScore1;

    private Integer conclusionScore2;

    private Integer conclusionScore3;

    private Integer teacherPaperScore;

    private Integer studentPaperScore;

    private BigDecimal finalScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getCrossStudentId() {
        return crossStudentId;
    }

    public void setCrossStudentId(String crossStudentId) {
        this.crossStudentId = crossStudentId == null ? null : crossStudentId.trim();
    }

    public String getOpenDocument() {
        return openDocument;
    }

    public void setOpenDocument(String openDocument) {
        this.openDocument = openDocument == null ? null : openDocument.trim();
    }

    public String getMiddleDocument() {
        return middleDocument;
    }

    public void setMiddleDocument(String middleDocument) {
        this.middleDocument = middleDocument == null ? null : middleDocument.trim();
    }

    public String getConclusionDocument() {
        return conclusionDocument;
    }

    public void setConclusionDocument(String conclusionDocument) {
        this.conclusionDocument = conclusionDocument == null ? null : conclusionDocument.trim();
    }

    public String getPaperDocument() {
        return paperDocument;
    }

    public void setPaperDocument(String paperDocument) {
        this.paperDocument = paperDocument == null ? null : paperDocument.trim();
    }

    public Integer getOpenScore1() {
        return openScore1;
    }

    public void setOpenScore1(Integer openScore1) {
        this.openScore1 = openScore1;
    }

    public Integer getOpenScore2() {
        return openScore2;
    }

    public void setOpenScore2(Integer openScore2) {
        this.openScore2 = openScore2;
    }

    public Integer getOpenScore3() {
        return openScore3;
    }

    public void setOpenScore3(Integer openScore3) {
        this.openScore3 = openScore3;
    }

    public Integer getMiddleScore1() {
        return middleScore1;
    }

    public void setMiddleScore1(Integer middleScore1) {
        this.middleScore1 = middleScore1;
    }

    public Integer getMiddleScore2() {
        return middleScore2;
    }

    public void setMiddleScore2(Integer middleScore2) {
        this.middleScore2 = middleScore2;
    }

    public Integer getMiddleScore3() {
        return middleScore3;
    }

    public void setMiddleScore3(Integer middleScore3) {
        this.middleScore3 = middleScore3;
    }

    public Integer getConclusionScore1() {
        return conclusionScore1;
    }

    public void setConclusionScore1(Integer conclusionScore1) {
        this.conclusionScore1 = conclusionScore1;
    }

    public Integer getConclusionScore2() {
        return conclusionScore2;
    }

    public void setConclusionScore2(Integer conclusionScore2) {
        this.conclusionScore2 = conclusionScore2;
    }

    public Integer getConclusionScore3() {
        return conclusionScore3;
    }

    public void setConclusionScore3(Integer conclusionScore3) {
        this.conclusionScore3 = conclusionScore3;
    }

    public Integer getTeacherPaperScore() {
        return teacherPaperScore;
    }

    public void setTeacherPaperScore(Integer teacherPaperScore) {
        this.teacherPaperScore = teacherPaperScore;
    }

    public Integer getStudentPaperScore() {
        return studentPaperScore;
    }

    public void setStudentPaperScore(Integer studentPaperScore) {
        this.studentPaperScore = studentPaperScore;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }
}