package com.skch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by XX on 2017/7/28.
 */
@Entity
public class T_result {

    @Id
    @GeneratedValue
    private Integer id;//结果标识
    private String userId;//用户标识
    private String userName;//用户姓名
    private Date createTime;//生成结果时间
    private String formName;//表名
    private String formPurpose;//评估目的
    private String result;//评估结果
    private String MongoDB;//mongodb存储位置
    private String issueTime;//推送时间
    private String issuerId;//推送时间
    private String servicePackId;
    private String groupId;

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getServicePackId() {
        return servicePackId;
    }

    public void setServicePackId(String servicePackId) {
        this.servicePackId = servicePackId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormPurpose() {
        return formPurpose;
    }

    public void setFormPurpose(String formPurpose) {
        this.formPurpose = formPurpose;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMongoDB() {
        return MongoDB;
    }

    public void setMongoDB(String mongoDB) {
        MongoDB = mongoDB;
    }

    public T_result() {

    }
}
