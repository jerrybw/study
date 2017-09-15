package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.enumPack.GetScores;
import com.skch.service.GetResultInter;
import com.skch.util.CountScore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetStrokeRiskScoreCardResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        Integer score = 0;
        String result = "";
        int countA = 0;
        int countB = 0;
        int countC = 0;
        for (T_variable t_variable : t_variables) {
            String paramValue = t_variable.getParamvalue().toUpperCase();
            if("A".equals(paramValue)){
                countA++;
            }else if("B".equals(paramValue)){
                countB++;
            }else {
                countC++;
            }
        }
        if(countA >= 3){
            result = "请尽快到医院就医，及早预防脑卒中。";
        }else if(countC >=6 && countC <=8){
            result = "你现在的状况非常好，请继续保持下去。";
        }else {
            result = "你现在的状况良好，请继续保持，争取再有些改善。";
        }
        return result;
    }
}
