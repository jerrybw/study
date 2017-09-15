package com.jerry.work.bean;

/**
 * Created by 向博文 on 2017/8/29.
 */
public class Navigation {

    private Integer id;
    private String navigationName;
    private String url;

    @Override
    public String toString() {
        return "Navigation{" +
                "id=" + id +
                ", navigationName='" + navigationName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Navigation() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
