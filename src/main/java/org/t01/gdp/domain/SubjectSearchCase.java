package org.t01.gdp.domain;

import java.util.List;

public class SubjectSearchCase {
    private Long id;
    private String name;
    private Integer difficulty;
    private String majorName;
    private String direction;
    private List<String> state;

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }

    public SubjectSearchCase() {
    }

    public SubjectSearchCase(Long id, String name, Integer difficulty, String majorName, String direction, List<String> state) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.majorName = majorName;
        this.direction = direction;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
