package com.jerry.work.util;

import com.jerry.work.util.HttpRequest;

/**
 * Created by 向博文 on 2017/9/18.
 */
public class GetMessageUtil {
    public static String getUserMessageByUserId(String userId) {
        String url = "http://d.china-healthcare.cn/app/jk/id/JL108";
        String param = "uid=" + userId;
        String message = "";
        message = HttpRequest.sendPost(url, param);
        return message;
    }

    public static String getGroupMessageByGroupId(String groupId){
        String url = "http://d.china-healthcare.cn/app/jk/id/JL107";
        String param = "groupid=" + groupId;
        String message = "";
        message = HttpRequest.sendPost(url, param);
        return message;
    }
}
