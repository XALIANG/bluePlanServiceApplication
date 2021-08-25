package com.blue.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class User {
    private Integer uId;
    private String userName;
    private String UserAccount;
    private String password;
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }




    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String UserAccount) {
        this.UserAccount = UserAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uId +
                ", name='" + userName + '\'' +
                ", account='" + UserAccount + '\'' +
                ", password='" + password + '\'' +
                ", password='" + roleId + '\'' +
                '}';
    }


}
