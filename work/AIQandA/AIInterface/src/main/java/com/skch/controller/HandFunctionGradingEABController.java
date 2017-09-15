package com.skch.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.skch.util.JSONUtil.*;

/**
 * Created by XX on 2017/7/24.
 */
@RestController
public class HandFunctionGradingEABController {

    @RequestMapping("HandFunctionGradingEAB")
    public String handFunctionGradingEAB(String str){
        JsonObject jsonObject = fromJson(str);
        Integer score = 0;
        String s1 = getValue(str,"s1");
        if(s1.equals("能")){
            score += 1;
        }
        String s2 = getValue(str,"s2");
        if(s2.equals("能")){
            score += 1;
        }
        String s3 = getValue(str,"s3");
        if(s3.equals("能")){
            score += 1;
        }
        String s4 = getValue(str,"s4");
        if(s4.equals("能")){
            score += 1;
        }
        String s5 = getValue(str,"s5");
        if(s5.equals("能")){
            score += 1;
        }
        JsonArray words = jsonObject.getAsJsonArray("Words");
        return "{Words:\"" + sub(words.get(score).toString()) + "\"}";
    }

}
