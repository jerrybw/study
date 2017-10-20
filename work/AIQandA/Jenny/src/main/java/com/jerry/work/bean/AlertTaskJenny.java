package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/10/20.
 */
public class AlertTaskJenny {
    private Integer id;
    private Integer taskId;
    private Long alertTimestamp;
    private Integer alertTimes;
    private Integer shouldAlertTimes;

    @Override
    public String toString() {
        return "AlertTaskJenny{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", alertTimestamp=" + alertTimestamp +
                ", alertTimes=" + alertTimes +
                ", shouldAlertTimes=" + shouldAlertTimes +
                '}';
    }

    public AlertTaskJenny() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Long getAlertTimestamp() {
        return alertTimestamp;
    }

    public void setAlertTimestamp(Long alertTimestamp) {
        this.alertTimestamp = alertTimestamp;
    }

    public Integer getAlertTimes() {
        return alertTimes;
    }

    public void setAlertTimes(Integer alertTimes) {
        this.alertTimes = alertTimes;
    }

    public Integer getShouldAlertTimes() {
        return shouldAlertTimes;
    }

    public void setShouldAlertTimes(Integer shouldAlertTimes) {
        this.shouldAlertTimes = shouldAlertTimes;
    }
}
