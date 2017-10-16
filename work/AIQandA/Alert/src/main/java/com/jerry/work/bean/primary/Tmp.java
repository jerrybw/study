package com.jerry.work.bean.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 向博文 on 2017/9/18.
 */
@Entity()
@Table(name = "ai_tmp")
public class Tmp {
    private int id;
    private String dTmpId;
    private String pTmpId;
    private String event;

    public Tmp() {
    }

    @Id
    @GeneratedValue
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdTmpId() {
        return dTmpId;
    }

    public void setdTmpId(String dTmpId) {
        this.dTmpId = dTmpId;
    }

    public String getpTmpId() {
        return pTmpId;
    }

    public void setpTmpId(String pTmpId) {
        this.pTmpId = pTmpId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
