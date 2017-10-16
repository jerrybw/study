package com.skch.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import static com.skch.util.JSONUtil.*;
/**
 * Created by XX on 2017/7/25.
 */

@RestController
public class MuscleSpasticityAssessmentController {

    @RequestMapping("MuscleSpasticityAssessment")
    public String muscleSpasticityAssessment(String str){
        StringBuffer result = new StringBuffer("");
        JsonObject jsonObject = fromJson(str);
        Set<Map.Entry<String ,JsonElement>> set = jsonObject.entrySet();
        for (Map.Entry<String ,JsonElement> entry :set) {
            String key = entry.getKey();
            String value =  entry.getValue().toString();
            result.append(key + "：" + translate(sub(value)) + "<br/>");

        }
        return "{Words:\"" + result.toString() + "\"}";
    }

    public static String translate(String str) {
        switch (str) {
            case "A":
                return "0";
            case "B":
                return "Ⅰ";
            case "C":
                return "Ⅰ+";
            case "D":
                return "Ⅱ";
            case "E":
                return "Ⅲ";
            case "F":
                return "Ⅳ";
            default:
                return "";
        }
    }
}
