package com.skch.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import static com.skch.util.JSONUtil.*;
/**
 * Created by XX on 2017/7/25.
 */
@RestController
public class SimplifiedFuglMeyerMotorFunctionScoreController {

    @RequestMapping("SimplifiedFuglMeyerMotorFunctionScore")
    public String SimplifiedFuglMeyerMotorFunctionScore(String str){
        Integer score = 0;
        JsonObject jsonObject = fromJson(str);
        JsonArray key = jsonObject.getAsJsonArray("key");
        for (JsonElement k :key){
            String s = k.toString();
            Integer eachScore = 0;
            switch (sub(s)){
                case ("B"):
                    eachScore = 1;
                    break;
                case ("C"):
                    eachScore = 2;
                    break;
            }
            score += eachScore;
        }
        String result = "";
        if( score < 50){
            result = "Ⅰ级 严重运动障碍";
        }else if (score < 85){
            result = "Ⅱ级 明显运动障碍";
        }else if (score < 96){
            result = "Ⅲ级 中度运动障碍";
        }else {
            result = "Ⅳ级 轻度运动障碍";
        }
        return "{Words:\"病人属于：" + result + "\"}";
    }
}
