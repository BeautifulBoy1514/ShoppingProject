package com.igeek.pojo;

/**
 * 用户类，用于描述用户的基本信息
 * @author Yongqiang Jia
 * @version 1.0
 */
public class User {
    //用户编号
    private int userId;
    //用户姓名
    private  String userName;
    //密码
    private String password;

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
