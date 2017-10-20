package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/10/20.
 */
public class SchemeCondition {
    private int id;
    private Integer servicePackId;
    private String script;
    private String formItemName;
    private String formItemValue;
    private int schemeId;

    @Override
    public String toString() {
        return "SchemeCondition{" +
                "id=" + id +
                ", servicePackId=" + servicePackId +
                ", script='" + script + '\'' +
                ", formItemName='" + formItemName + '\'' +
                ", formItemValue='" + formItemValue + '\'' +
                ", schemeId=" + schemeId +
                '}';
    }

    public SchemeCondition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getServicePackId() {
        return servicePackId;
    }

    public void setServicePackId(Integer servicePackId) {
        this.servicePackId = servicePackId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getFormItemName() {
        return formItemName;
    }

    public void setFormItemName(String formItemName) {
        this.formItemName = formItemName;
    }

    public String getFormItemValue() {
        return formItemValue;
    }

    public void setFormItemValue(String formItemValue) {
        this.formItemValue = formItemValue;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
}
