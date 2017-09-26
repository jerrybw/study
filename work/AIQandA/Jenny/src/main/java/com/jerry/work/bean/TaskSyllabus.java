package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/9/1.
 */
public class TaskSyllabus {

    private Integer id;
    private String userId;//用户id
    private Integer taskNumber;//任务序号
    private Integer taskId;//任务id
    private String start;//开始时间 数值型  毫秒
    private String end;//结束时间 数值型  毫秒
    private String taskStatus;//任务状态  目前分为三种 ： 未开始  进行中  已完成
    private String taskResult;//任务结果
    private String jenny;//管理人
    private String groupId;


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

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public String getJenny() {
        return jenny;
    }

    public void setJenny(String jenny) {
        this.jenny = jenny;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public TaskSyllabus() {

    }
}
