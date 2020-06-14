package org.t01.gdp.domain;


public class SubjectMajorTeacher {
    private Long id;

    private String Teacher;

    private String major;

    private String name;

    private String direction;

    private String state;

    public SubjectMajorTeacher() {
    }

    public SubjectMajorTeacher(Long id, String teacher, String major, String name, String direction, String state) {
        this.id = id;
        Teacher = teacher;
        this.major = major;
        this.name = name;
        this.direction = direction;
        this.state = state;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
