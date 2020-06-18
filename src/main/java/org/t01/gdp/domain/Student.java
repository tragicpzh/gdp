package org.t01.gdp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Student {
    private Long id;

    private String studentId;

    private String password;

    private String name;

    private String phoneNumber;

    private String email;

    private Date createTime;

    private String headPortrait;

    private Long majorId;

    private String state;

    private Long subjectId;

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

    private Integer crossPaperScore;

    private BigDecimal finalScore;

    private String openEvaluation1;

    private String openEvaluation2;

    private String openEvaluation3;

    private String middleEvaluation1;

    private String middleEvaluation2;

    private String middleEvaluation3;

    private String teacherPaperEvaluation;

    private String crossPaperEvaluation;

    private String conclusionEvaluation1;

    private String conclusionEvaluation2;

    private String conclusionEvaluation3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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

    public Integer getCrossPaperScore() {
        return crossPaperScore;
    }

    public void setCrossPaperScore(Integer crossPaperScore) {
        this.crossPaperScore = crossPaperScore;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public String getOpenEvaluation1() {
        return openEvaluation1;
    }

    public void setOpenEvaluation1(String openEvaluation1) {
        this.openEvaluation1 = openEvaluation1 == null ? null : openEvaluation1.trim();
    }

    public String getOpenEvaluation2() {
        return openEvaluation2;
    }

    public void setOpenEvaluation2(String openEvaluation2) {
        this.openEvaluation2 = openEvaluation2 == null ? null : openEvaluation2.trim();
    }

    public String getOpenEvaluation3() {
        return openEvaluation3;
    }

    public void setOpenEvaluation3(String openEvaluation3) {
        this.openEvaluation3 = openEvaluation3 == null ? null : openEvaluation3.trim();
    }

    public String getMiddleEvaluation1() {
        return middleEvaluation1;
    }

    public void setMiddleEvaluation1(String middleEvaluation1) {
        this.middleEvaluation1 = middleEvaluation1 == null ? null : middleEvaluation1.trim();
    }

    public String getMiddleEvaluation2() {
        return middleEvaluation2;
    }

    public void setMiddleEvaluation2(String middleEvaluation2) {
        this.middleEvaluation2 = middleEvaluation2 == null ? null : middleEvaluation2.trim();
    }

    public String getMiddleEvaluation3() {
        return middleEvaluation3;
    }

    public void setMiddleEvaluation3(String middleEvaluation3) {
        this.middleEvaluation3 = middleEvaluation3 == null ? null : middleEvaluation3.trim();
    }

    public String getTeacherPaperEvaluation() {
        return teacherPaperEvaluation;
    }

    public void setTeacherPaperEvaluation(String teacherPaperEvaluation) {
        this.teacherPaperEvaluation = teacherPaperEvaluation == null ? null : teacherPaperEvaluation.trim();
    }

    public String getCrossPaperEvaluation() {
        return crossPaperEvaluation;
    }

    public void setCrossPaperEvaluation(String crossPaperEvaluation) {
        this.crossPaperEvaluation = crossPaperEvaluation == null ? null : crossPaperEvaluation.trim();
    }

    public String getConclusionEvaluation1() {
        return conclusionEvaluation1;
    }

    public void setConclusionEvaluation1(String conclusionEvaluation1) {
        this.conclusionEvaluation1 = conclusionEvaluation1 == null ? null : conclusionEvaluation1.trim();
    }

    public String getConclusionEvaluation2() {
        return conclusionEvaluation2;
    }

    public void setConclusionEvaluation2(String conclusionEvaluation2) {
        this.conclusionEvaluation2 = conclusionEvaluation2 == null ? null : conclusionEvaluation2.trim();
    }

    public String getConclusionEvaluation3() {
        return conclusionEvaluation3;
    }

    public void setConclusionEvaluation3(String conclusionEvaluation3) {
        this.conclusionEvaluation3 = conclusionEvaluation3 == null ? null : conclusionEvaluation3.trim();
    }
}