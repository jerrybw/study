package com.jerry.study.bean.primary;

/**
 * Created by 向博文 on 2017/8/29.
 */
public class UserMessage {

    private String userName;
    private String message;

    @Override
    public String toString() {
        return "UserMessage{" +
                "userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public UserMessage() {
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
