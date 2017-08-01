package com.jerry.study.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by 向博文 on 2017/8/1.
 */
@Document(collection = "T_conversation")
public class Conversation {

    private String fromId;
    private String toId;
    private String type;
    private Integer timestamp;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
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
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
