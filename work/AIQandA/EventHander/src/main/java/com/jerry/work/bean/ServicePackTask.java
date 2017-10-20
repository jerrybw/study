package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class ServicePackTask {

    private Integer id;
    private String groupId;
    private String userId;
    private String url;
    private int type;
    private int status = 0;
    private String issuerId;
    private String issueTime;
    private String servicePackId;
    private String caseName;
    private String finishTime;
    private int isAlerted;//是否提醒过，1表示没有，0表示提醒过了

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getServicePackId() {
        return servicePackId;
    }

    public void setServicePackId(String servicePackId) {
        this.servicePackId = servicePackId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServicePackTask() {

    }

    public int getIsAlerted() {
        return isAlerted;
    }

    public void setIsAlerted(int isAlerted) {
        this.isAlerted = isAlerted;
    }

    public ServicePackTask(String groupId, String userId, String url, int type, String issuerId, String issueTime, String servicePackId, String caseName) {
        this.groupId = groupId;
        this.userId = userId;
        this.url = url;
        this.type = type;
        this.issuerId = issuerId;
        this.issueTime = issueTime;
        this.servicePackId = servicePackId;
        this.caseName = caseName;
    }

}
