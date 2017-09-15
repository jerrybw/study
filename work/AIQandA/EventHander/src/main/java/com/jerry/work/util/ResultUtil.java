package com.jerry.work.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/18.
 */
public class ResultUtil {

    private static Map<String,String> codeMsg ;

    static {
        codeMsg = new HashMap<String,String>();
        codeMsg.put("1","成功");
        codeMsg.put("500","系统异常");
        codeMsg.put("501","查询不到此服务包，请验证服务包名称是否正确");
        codeMsg.put("401","添加任务失败");
        codeMsg.put("403","json参数data解析错误，请检查参数是否正确");
        codeMsg.put("402","根据服务包群id未查找到任务，请检查群id是否正确");
    }

    public static String handResult(String code){
        String resultJsonStr = "{" +
                "code:'" + code +
                "',msg:'" + codeMsg.get(code) +
                "'}";
        return JSONObject.fromObject(resultJsonStr).toString();
    }

    public static String handFindCaseResult(String code,String result){
        String resultJsonStr = "{" +
                "code:'" + code +
                "',msg:'" + codeMsg.get(code) +
                "',result:'" + result +
                "'}";
        return JSONObject.fromObject(resultJsonStr).toString();
    }
}
