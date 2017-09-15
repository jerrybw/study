package com.jerry.work.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/18.
 */
public class GetScriptStatusResultUtil {

    private static Map<String, String> codeMsg;

    static {
        codeMsg = new HashMap<String, String>();
        codeMsg.put("1", "已完成");
        codeMsg.put("0", "未完成");
        codeMsg.put("502", "json数据param异常");
        codeMsg.put("501", "script脚本名异常");
        codeMsg.put("500", "系统异常");
    }

    public static String handResult(String code, String result) {
        String resultJsonStr = "{" +
                "code:'" + code + "'" +
                ",msg:'" + codeMsg.get(code) + "'";
        if (code == "1") {
            resultJsonStr += ",result:" + result;
        }
        resultJsonStr += "}";
        return JSONObject.fromObject(resultJsonStr).toString();
    }
}
