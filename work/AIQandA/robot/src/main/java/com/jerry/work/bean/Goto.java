package com.jerry.work.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 向博文 on 2017/8/21.
 */
@Entity
public class Goto {

    private Integer id;
    private String property;
    private String subject;
    private String script;
    private String askId;
    private String value;
    private String weixin;
    private String prompt;

    @Override
    public String toString() {
        return "Goto{" +
                "id=" + id +
                ", property='" + property + '\'' +
                ", subject='" + subject + '\'' +
                ", script='" + script + '\'' +
                ", askId='" + askId + '\'' +
                ", value='" + value + '\'' +
                ", weixin='" + weixin + '\'' +
                ", prompt='" + prompt + '\'' +
                '}';
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getAskId() {
        return askId;
    }

    public void setAskId(String askId) {
        this.askId = askId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Goto() {

    }
}
