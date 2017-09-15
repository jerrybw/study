package com.jerry.work.util;

import org.apache.log4j.Logger;

/**
 * Created by 向博文 on 2017/8/25.
 */
public class SendWeiXin {

    private static Logger logger = Logger.getLogger(SendWeiXin.class);

    public static void sendWeiXin(String url,String openId,String clickUrl,String first,String keyword1,String keyword2,String remark){
        String param = "openid="+openId+"&url="+clickUrl+"&first="+first+"&keyword1="+keyword1+"&keyword2="+keyword2+"&remark="+remark;
        String result = HttpRequest.sendPost(url,param);
    }

}
