package com.skch.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
public class MiniMentalStatusChecklist {

    @RequestMapping("MiniMentalStatusChecklist")
    public String miniMentalStatusChecklist(String str){
        Integer score = 0;
        JsonObject param = fromJson(str);
        Object s1 = param.get("s1");
        Object s2 = param.get("s2");
        Set<Map.Entry<String, JsonElement>> s1entries = fromJson(s1.toString()).entrySet();
        Set<Map.Entry<String, JsonElement>> s2entries = fromJson(s2.toString()).entrySet();
        for (Map.Entry<String, JsonElement> p:s1entries){
            if("对".equals(sub(p.getValue().toString()))){
                score += 1;
            }
        }
        for (Map.Entry<String, JsonElement> p:s2entries){
            score += Integer.parseInt(sub(p.getValue().toString()));
        }
        String status = "";
        if(score <= 17){
            status = "文盲";
        } else if (score <= 20){
            status = "小学";
        } else if (score <= 22){
            status = "中学(包括中专)";
        } else {
            status = "大学(包括大专)";
        }
        return "{Words:\"根据答案判断，病人的精神状态处于"+status + "水平\"}";
    }
}
