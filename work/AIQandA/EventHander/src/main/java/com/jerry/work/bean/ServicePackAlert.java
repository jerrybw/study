package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class ServicePackAlert {
    private Integer id;
    private String servicePackName;
    private String servicePackId;
    private String event;
    private Integer number;
    private String content;

    @Override
    public String toString() {
        return "ServicePackAlert{" +
                "id=" + id +
                ", servicePackName='" + servicePackName + '\'' +
                ", servicePackId='" + servicePackId + '\'' +
                ", event='" + event + '\'' +
                ", number=" + number +
                ", content='" + content + '\'' +
                '}';
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

    public String getServicePackName() {
        return servicePackName;
    }

    public void setServicePackName(String servicePackName) {
        this.servicePackName = servicePackName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ServicePackAlert() {

    }
}
