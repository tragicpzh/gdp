package org.t01.gdp.domain;

public class StudentAndSubject {
    private String studentId;
    private String subjectId;
    private String subjectName;
    private String majorId;
    private String direction;

    public StudentAndSubject() {
    }

    public StudentAndSubject(String studentId, String subjectId, String subjectName, String majorId, String direction) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.majorId = majorId;
        this.direction = direction;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
