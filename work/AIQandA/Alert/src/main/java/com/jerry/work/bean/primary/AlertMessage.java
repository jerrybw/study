package com.jerry.work.bean.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 向博文 on 2017/8/18.
 */
@Entity
public class AlertMessage {

    private Integer id;//主键
    private Long fireMoment;//提醒时间
    private String toId;//接收人id
    private String msg;//提醒消息
    private Integer except = 0;//是否失效 0 否
    private Integer sms = 1;//是否通过sms通知 0 否
    private Integer weixin = 1;//是否通过微信通知 0否
    private Integer ims = 1;//是否通过ims通知 0否
    private Integer times = 1;//通知总次数
    private Integer leftTimes = 1;//剩余通知次数
    private Integer relay = 5;//通知间隔时间 单位为分钟 必须为1分钟的整倍数
    private Long begin;//开始通知时间
    private Long end;//结束通知时间

    @Override
    public String toString() {
        return "AlertMessage{" +
                "id=" + id +
                ", fireMoment=" + fireMoment +
                ", toId='" + toId + '\'' +
                ", msg='" + msg + '\'' +
                ", except=" + except +
                ", sms=" + sms +
                ", weixin=" + weixin +
                ", ims=" + ims +
                ", times=" + times +
                ", leftTimes=" + leftTimes +
                ", relay=" + relay +
                ", begin=" + begin +
                ", end=" + getEnd() +
                '}';
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getExcept() {
        return except;
    }

    public void setExcept(Integer except) {
        this.except = except;
    }

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getWeixin() {
        return weixin;
    }

    public void setWeixin(Integer weixin) {
        this.weixin = weixin;
    }

    public Integer getIms() {
        return ims;
    }

    public void setIms(Integer ims) {
        this.ims = ims;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getLeftTimes() {
        return leftTimes;
    }

    public void setLeftTimes(Integer leftTimes) {
        this.leftTimes = leftTimes;
    }

    public Integer getRelay() {
        return relay;
    }

    public void setRelay(Integer relay) {
        this.relay = relay;
    }

    public Long getFireMoment() {
        return fireMoment;
    }

    public void setFireMoment(Long fireMoment) {
        this.fireMoment = fireMoment;
    }

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        end = begin + times * relay * 60 * 1000;
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public AlertMessage() {

    }
}
