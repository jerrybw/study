package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;
import com.skch.util.CountScore;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetAdrenalDysfunctionTestResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = CountScore.getScore(param);
        String result = "";
        if(score >=0 && score <=7){
            result = "你可能有轻度的肾上腺功能障碍";
        }else if(score >=8 && score<= 10){
            result = "你可能有中度的肾上腺功能障碍";
        }else if(score >= 11){
            result = "你可能有重度的肾上腺功能障碍";
        }
        return "数据采集完成";
    }
}
