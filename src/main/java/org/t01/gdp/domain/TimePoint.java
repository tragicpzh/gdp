package org.t01.gdp.domain;

import java.util.Date;

public class TimePoint {
    private int index;
    private String name;
    private Date dateTime;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date date) {
        this.dateTime = date;
    }

    @Override
    public String toString() {
        return "{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", date=" + dateTime +
                '}';
    }
}
