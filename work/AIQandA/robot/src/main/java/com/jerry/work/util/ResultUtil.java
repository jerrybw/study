package com.jerry.work.util;

import net.sf.json.JSONObject;

/**
 * Created by 向博文 on 2017/8/18.
 */
public class ResultUtil {

    public static String handSuccessResult(String result){
        String resultJsonStr = "{" +
                "\"code\":" + 1 +
                ",\"answer\":\"" + result +
                "\"}";
        return resultJsonStr;
    }

    public static String handErrorResult(String error){
        String resultJsonStr = "{" +
                "\"code\":" + 0 +
                ",\"answer\":\"" + error +
                "\"}";
        return JSONObject.fromObject(resultJsonStr).toString();
    }
}
