package com.skch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * Created by 向博文 on 2017/8/4.
 */
@Document(collection = "evaluationResult")
public class MongoDBResult {

    @Id
    private String id;
    private String userId;
    private LocalDateTime createTime;
    private Map<String,Object> items;

    public MongoDBResult( String userId, LocalDateTime createTime,Map<String,Object> items) {
        this.userId = userId;
        this.createTime = createTime;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Map<String,Object> getItems() {
        return items;
    }

    public void setItems(Map<String,Object> items) {
        this.items = items;
    }

    public MongoDBResult() {

    }

    @Override
    public String toString() {
        return "MongoDBResult{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", items=" + items +
                '}';
    }
}
