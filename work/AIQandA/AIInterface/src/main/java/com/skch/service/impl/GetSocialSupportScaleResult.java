package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetSocialSupportScaleResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = 0;
        List<T_variable> t_variables = param.get("t_variables");
        for (T_variable t: t_variables) {
            switch (t.getParamvalue()){
                case "有时":
                    score += 1;
                    break;
                case "经常":
                    score += 2;
                    break;
            }
        }
        String result = "患者的得分为"+score+"分";
        return result;
    }
}
