package com.skch.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.skch.util.JSONUtil.*;

import java.util.Map;
import java.util.Set;


/**
 * Created by XX on 2017/7/25.
 */
@RestController
public class FuglMeyerBalanceFunctionEvaluationController {

    @RequestMapping("FuglMeyerBalanceFunctionEvaluation")
    public String fuglMeyerBalanceFunctionEvaluation(String str){
        Integer score = 0;
        JsonObject jsonObject = fromJson(str);
        Set<Map.Entry<String ,JsonElement>> set = jsonObject.entrySet();
        for (Map.Entry<String,JsonElement> entry :set) {
            JsonElement value =  entry.getValue();
            Integer eachScore = 0;
            switch (sub(value.toString())){
                case ("B"):
                    eachScore = 1;
                    break;
                case ("C"):
                    eachScore = 2;
                    break;
            }
            score += eachScore;
        }
        return "{Words:\"病人的得分为：" + score + "\"}";
    }
}
