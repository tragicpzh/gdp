package org.t01.gdp.domain;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private long id;

    private String roleId;

    private String name;

    private String phoneNumber;

    private String email;

    private String headPortrait;

    private Date createTime;

    public UserInfo(long id, String roleId, String name, String phoneNumber, String email, String headPortrait, Date createTime) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.headPortrait = headPortrait;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "{"
                + "\"id\":\""
                + id + '\"'
                + ",\"roleId\":\""
                + roleId + '\"'
                + ",\"name\":\""
                + name + '\"'
                + ",\"phoneNumber\":\""
                + phoneNumber + '\"'
                + ",\"email\":\""
                + email + '\"'
                + ",\"headPortrait\":\""
                + headPortrait + '\"'
                + ",\"createTime\":\""
                + createTime + '\"'
                + "}";

    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
