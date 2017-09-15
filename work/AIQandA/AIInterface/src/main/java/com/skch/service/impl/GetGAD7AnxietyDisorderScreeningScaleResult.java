package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScoreByLetters;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetGAD7AnxietyDisorderScreeningScaleResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = getScoreByLetters(param,1);
        String result = "";
        if (score>=0 && score<=5){
            result="根据您的回答，我们判断您患有轻度焦虑症。";
        }else if(score>=6 && score<=10){
            result="根据您的回答，我们判断您患有中度焦虑症。";
        }else if(score>=11 && score<=15){
            result="根据您的回答，我们判断您患有重度焦虑症。";
        }
        return result;
    }
}
