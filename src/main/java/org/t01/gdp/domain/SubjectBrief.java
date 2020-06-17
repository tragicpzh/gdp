package org.t01.gdp.domain;


public class SubjectBrief {
    private long id;
    private String name;
    private float difficulty;
    private long majorId;
    private String direction;
    private String state;

    @Override
    public String toString() {
        return "{"
                + "\"id\":"
                + id
                + ",\"name\":\""
                + name + '\"'
                + ",\"difficulty\":"
                + difficulty
                + ",\"majorId\":\""
                + majorId + '\"'
                + ",\"direction\":\""
                + direction + '\"'
                + ",\"state\":\""
                + state + '\"'
                + "}";

    }

    public long getId() {
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

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(float difficulty) {
        this.difficulty = difficulty;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
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
}
