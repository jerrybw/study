package com.jerry.work.bean.secondary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 向博文 on 2017/8/28.
 */
@Entity
@Table(name = "dao_sk_appointment_book")
public class AlertMsg {

    @Id
    @GeneratedValue
    private Integer id;
    private Date adddate;
    private String zt;
    private String yyId;
    private String gzid;
    private String yyUid;
    private String yyStart; //开始提醒事件
    private String yyEnd;
    private String yyContent; // 提醒事件
    private String yyImage;
    private String yyVoice;
    private String yyState;
    private String yyTime;
    private String yyRemind; // 提前提醒 为时间格式的字符串 yyyy-mm-dd hh:mm:ss
    private String yyType; // 类型 2 为提醒 1 为预约
    private String valid; // 是否有效  0 无效 1 有效

    @Override
    public String toString() {
        return "AlertMsg{" +
                "id=" + id +
                ", adddate=" + adddate +
                ", zt='" + zt + '\'' +
                ", yyId='" + yyId + '\'' +
                ", gzid='" + gzid + '\'' +
                ", yyUid='" + yyUid + '\'' +
                ", yyStart='" + yyStart + '\'' +
                ", yyEnd='" + yyEnd + '\'' +
                ", yyContent='" + yyContent + '\'' +
                ", yyImage='" + yyImage + '\'' +
                ", yyVoice='" + yyVoice + '\'' +
                ", yyState='" + yyState + '\'' +
                ", yyTime='" + yyTime + '\'' +
                ", yyRemind='" + yyRemind + '\'' +
                ", yyType='" + yyType + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getYyId() {
        return yyId;
    }

    public void setYyId(String yyId) {
        this.yyId = yyId;
    }

    public String getGzid() {
        return gzid;
    }

    public void setGzid(String gzid) {
        this.gzid = gzid;
    }

    public String getYyUid() {
        return yyUid;
    }

    public void setYyUid(String yyUid) {
        this.yyUid = yyUid;
    }

    public String getYyStart() {
        return yyStart;
    }

    public void setYyStart(String yyStart) {
        this.yyStart = yyStart;
    }

    public String getYyEnd() {
        return yyEnd;
    }

    public void setYyEnd(String yyEnd) {
        this.yyEnd = yyEnd;
    }

    public String getYyContent() {
        return yyContent;
    }

    public void setYyContent(String yyContent) {
        this.yyContent = yyContent;
    }

    public String getYyImage() {
        return yyImage;
    }

    public void setYyImage(String yyImage) {
        this.yyImage = yyImage;
    }

    public String getYyVoice() {
        return yyVoice;
    }

    public void setYyVoice(String yyVoice) {
        this.yyVoice = yyVoice;
    }

    public String getYyState() {
        return yyState;
    }

    public void setYyState(String yyState) {
        this.yyState = yyState;
    }

    public String getYyTime() {
        return yyTime;
    }

    public void setYyTime(String yyTime) {
        this.yyTime = yyTime;
    }

    public String getYyRemind() {
        return yyRemind;
    }

    public void setYyRemind(String yyRemind) {
        this.yyRemind = yyRemind;
    }

    public String getYyType() {
        return yyType;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public void setYyType(String yyType) {

        this.yyType = yyType;
    }

    public AlertMsg() {

    }
}
