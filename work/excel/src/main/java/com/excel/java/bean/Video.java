package com.excel.java.bean;

/**
 * 视频实体类
 * Created by 向博文 on 2017/10/13.
 */
public class Video {
    private Integer id;//视频标识
    private String videoName;//视频名字
    private String videoUrl;//视频地址
    private String videoIntroduce;//视频简介
    private int videoTime;//视频时长
    private int videoType;//视频类型
    private long updateTime;//上传时间

    public Video() {
    }

    public Video(String videoName, String videoUrl, String videoIntroduce, int videoTime, int videoType, long updateTime) {
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.videoIntroduce = videoIntroduce;
        this.videoTime = videoTime;
        this.videoType = videoType;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoIntroduce() {
        return videoIntroduce;
    }

    public void setVideoIntroduce(String videoIntroduce) {
        this.videoIntroduce = videoIntroduce;
    }

    public int getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(int videoTime) {
        this.videoTime = videoTime;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videoName='" + videoName + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoIntroduce='" + videoIntroduce + '\'' +
                ", videoTime=" + videoTime +
                ", videoType=" + videoType +
                ", updateTime=" + updateTime +
                '}';
    }
}
