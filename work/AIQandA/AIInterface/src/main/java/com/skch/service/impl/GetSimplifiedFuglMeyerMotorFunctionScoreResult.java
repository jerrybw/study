package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScoreByLetters;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetSimplifiedFuglMeyerMotorFunctionScoreResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = getScoreByLetters(param,1);
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
        return "根据答案得到患者为：" + result;
    }
}
