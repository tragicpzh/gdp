package org.t01.gdp.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class TimePoint {
    @JSONField(name = "INDEX")
    private int index;

    @JSONField(name = "NAME")
    private String name;

    @JSONField(name = "DATE_TIME", format = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;

    @JSONField(name = "HOME_PAGE_NOTICE")
    private String homePageNotice;

    @JSONField(name = "MESSAGE_NOTICE")
    private String messageNotice;

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

    public String getHomePageNotice() {
        return homePageNotice;
    }

    public void setHomePageNotice(String homePageNotice) {
        this.homePageNotice = homePageNotice;
    }

    public String getMessageNotice() {
        return messageNotice;
    }

    public void setMessageNotice(String messageNotice) {
        this.messageNotice = messageNotice;
    }

    @Override
    public String toString() {
        return "{" +
                "\"index\":\"" + index +
                "\",\"name\":\"" + name +
                "\",\"dateTime\":\"" + dateTime +
                "\",\"homePageNotice\":\"" + homePageNotice + '\"' +
                "\",\"messageNotice\":\"" + messageNotice + '\"' +
                '}';
    }
}
