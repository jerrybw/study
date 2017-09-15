package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetModifiedBarthelIndexMBIResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        //ADL能力缺陷程度：
        //0-20=极严重功能缺陷；25-45=严重功能缺陷；50-70=中度功能缺陷；75-95=轻度功能缺陷；100=ADL自理
        Integer score = getScore(param);
        String result = "";
        if(score >= 0 && score <= 20){
            result = "病患的ADL能力缺陷程度为极严重功能缺陷";
        }else if (score >= 25 && score <= 45){
            result = "病患的ADL能力缺陷程度为严重功能缺陷";
        }else if (score >= 50 && score <= 70){
            result = "病患的ADL能力缺陷程度为中度功能缺陷";
        }else if (score >= 75 && score <= 95){
            result = "病患的ADL能力缺陷程度为轻度功能缺陷";
        }else if (score >= 100){
            result = "病患的ADL能力缺陷程度为ADL自理";
        }
        return result;
    }
}
