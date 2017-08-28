package com.jerry.study.bean;

/**
 * 接收结果封装类
 * Created by 向博文 on 2017/8/28.
 */
public class UserTags {
    private String userName;
    private String tagName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public UserTags() {

    }

    @Override
    public String toString() {
        return "UserTags{" +
                "userName='" + userName + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
