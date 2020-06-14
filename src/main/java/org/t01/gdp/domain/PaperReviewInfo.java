package org.t01.gdp.domain;

public class PaperReviewInfo {
    private String studentId;
    private String subjectId;
    private String subjectName;
    private String majorId;
    private String direction;
    private String paperDocument;

    @Override
    public String toString() {
        return "{"
                + "\"studentId\":\""
                + studentId + '\"'
                + ",\"subjectId\":\""
                + subjectId + '\"'
                + ",\"subjectName\":\""
                + subjectName + '\"'
                + ",\"majorId\":\""
                + majorId + '\"'
                + ",\"direction\":\""
                + direction + '\"'
                + ",\"paperDocument\":\""
                + paperDocument + '\"'
                + "}";

    }

    public String getPaperDocument() {
        return paperDocument;
    }

    public void setPaperDocument(String paperDocument) {
        this.paperDocument = paperDocument;
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
