package org.t01.gdp.domain;

import java.util.Date;

public class UserInfo {
    private String id;

    private String role;

    private String name;

    private String phoneNumber;

    private String email;

    private Date createTime;

    public UserInfo(String id, String role, String name, String phoneNumber, String email, Date createTime) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "DisplayUser{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
