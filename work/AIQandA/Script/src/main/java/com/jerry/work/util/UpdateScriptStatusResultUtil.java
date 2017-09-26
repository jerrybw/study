package com.jerry.work.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/18.
 */
public class UpdateScriptStatusResultUtil {

    private static Map<String, String> codeMsg;

    static {
        codeMsg = new HashMap<String, String>();
        codeMsg.put("1", "修改成功");
        codeMsg.put("502", "json数据param异常");
        codeMsg.put("501", "script脚本名异常");
        codeMsg.put("500", "系统异常");
        codeMsg.put("503", "数据库中未查找到数据，请检查参数是否正确");
        codeMsg.put("504", "修改任务状态失败");
    }

    public static String handResult(String code, String result) {
        String resultJsonStr = "{" +
                "code:'" + code + "'" +
                ",msg:'" + codeMsg.get(code) + "'";
        resultJsonStr += "}";
        return JSONObject.fromObject(resultJsonStr).toString();
    }
}
