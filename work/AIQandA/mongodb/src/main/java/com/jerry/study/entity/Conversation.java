package com.jerry.study.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by 向博文 on 2017/8/1.
 */
@Document(collection = "conversation")
public class Conversation {

    @Id
    private String id;

    private Long hid;
    private String encryptionStr;
    private String from;
    private String to;
    private String type;
    private Integer timestamp;
    private String info;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public String getEncryptionStr() {
        return encryptionStr;
    }

    public void setEncryptionStr(String encryptionStr) {
        this.encryptionStr = encryptionStr;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Conversation() {

    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                ", hid=" + hid +
                ", encryptionStr='" + encryptionStr + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", info='" + info + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
