package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/9/1.
 */
public class Task {

    private Integer id;
    private String taskName;//任务名称
    private String taskContent;//任务内容
    private String handMethod;//处理方法
    private Integer tmp;
    private Integer imToP;
    private String tmpId;
    private String first;
    private String url;
    private String keyWords;
    private String remark;
    private String imToPMsg;
    private String servicePackId;
    private Integer imToG;
    private String imToGMsg;
    private Integer status = 1;//是否有效 0 代表无效 1代表有效

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getHandMethod() {
        return handMethod;
    }

    public void setHandMethod(String handMethod) {
        this.handMethod = handMethod;
    }

    public Integer getTmp() {
        return tmp;
    }

    public void setTmp(Integer tmp) {
        this.tmp = tmp;
    }

    public Integer getImToP() {
        return imToP;
    }

    public void setImToP(Integer imToP) {
        this.imToP = imToP;
    }

    public String getImToPMsg() {
        return imToPMsg;
    }

    public void setImToPMsg(String imToPMsg) {
        this.imToPMsg = imToPMsg;
    }

    public Integer getImToG() {
        return imToG;
    }

    public void setImToG(Integer imToG) {
        this.imToG = imToG;
    }

    public String getImToGMsg() {
        return imToGMsg;
    }

    public void setImToGMsg(String imToGMsg) {
        this.imToGMsg = imToGMsg;
    }

    public String getTmpId() {
        return tmpId;
    }

    public void setTmpId(String tmpId) {
        this.tmpId = tmpId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getServicePackId() {
        return servicePackId;
    }

    public void setServicePackId(String servicePackId) {
        this.servicePackId = servicePackId;
    }

    public Task() {

    }
}
