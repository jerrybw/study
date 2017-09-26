package com.jerry.work.util;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by 向博文 on 2017/8/25.
 */
public class SendWeiXin {

    private static Logger logger = Logger.getLogger(SendWeiXin.class);

    public static void sendWeiXin(String url, String tmpId, String openId, String clickUrl, String first, Map<String,String> keyWords, String remark){
        String param = "tmpid="+tmpId+"&openid="+openId+"&url="+clickUrl+"&first="+first+"&remark="+remark;
        for(int i = 1;i<=keyWords.size();i++){
            param += "&keyword"+i+"="+keyWords.get("keyWord"+i);
        }
        String result = HttpRequest.sendPost(url,param);
    }

}
