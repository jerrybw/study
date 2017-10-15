package com.excel.java.bean;

/**
 * 用户实体类
 * Created by 向博文 on 2017/10/13.
 */
public class User {

    private int id;//用户标识
    private String userName;//用户登录名
    private String passWord;//用户密码
    private String nickname;//用户昵称
    private String cellPhone;//用户手机号
    private String email;//用户邮箱
    private long birthday;//用户生日，以时间戳形式存储

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }
}
