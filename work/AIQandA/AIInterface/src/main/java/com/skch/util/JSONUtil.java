package com.skch.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.sf.json.JSONObject;

/**
 * Created by XX on 2017/7/27.
 */
public class JSONUtil {

    public static JsonObject fromJson(String json){
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(json);
        JsonObject jsonObject = parse.getAsJsonObject();
        return jsonObject;
    }

    public static String getValue(String str,String key){
        JsonObject jsonObject = fromJson(str);
        JsonPrimitive pri = jsonObject.getAsJsonPrimitive(key);
        String value = pri.toString();
        value = sub(value);
        return value;
    }

    public static String sub(String str){
        return str.substring(1,str.length() - 1);
    }
}
