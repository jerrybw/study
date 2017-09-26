package com.jerry.work.service;

import com.jerry.work.util.HttpRequest;

/**
 * Created by 向博文 on 2017/9/18.
 */
public class GetMessageService {
    public static String getUserMessageByUserId(String userId) {
        String url = "http://d.china-healthcare.cn/app/jk/id/JL108";
        String param = "uid=" + userId;
        String message = "";
        try {
            message = HttpRequest.sendPost(url, param);
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return message;
    }

    public static String getGroupMessageByGroupId(String groupId){
        String url = "http://d.china-healthcare.cn/app/jk/id/JL107";
        String param = "groupid=" + groupId;
        String message = "";
        try {
            message = HttpRequest.sendPost(url, param);
        } catch (Exception e) {
            throw new RuntimeException("405");
        }
        return message;
    }
}
