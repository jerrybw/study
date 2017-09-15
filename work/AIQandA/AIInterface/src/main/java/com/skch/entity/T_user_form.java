package com.skch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by XX on 2017/7/27.
 */
@Entity
public class T_user_form {

    @Id
    private String userId;//用户id
    private Integer formId;//表标识
    private Integer itemId;//项标识
    private Integer itemNumber;//编号


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public T_user_form() {

    }

    public T_user_form(String userId, Integer formId, Integer itemId,Integer itemNumber) {
        this.userId = userId;
        this.formId = formId;
        this.itemId = itemId;
        this.itemNumber = itemNumber;
    }
}
