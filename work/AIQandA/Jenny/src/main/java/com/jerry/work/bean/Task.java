package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/9/1.
 */
public class Task {

    private Integer id;
    private String taskName;//任务名称
    private String taskContent;//任务内容
    private String handMethod;//处理方法
    private String param;//参数

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", handMethod='" + handMethod + '\'' +
                ", param='" + param + '\'' +
                '}';
    }

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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Task() {

    }
}
