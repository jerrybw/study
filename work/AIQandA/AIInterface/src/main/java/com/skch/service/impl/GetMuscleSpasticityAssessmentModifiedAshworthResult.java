package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;
import com.skch.controller.MuscleSpasticityAssessmentController;

import static com.skch.util.CountScore.getScoreByLetters;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetMuscleSpasticityAssessmentModifiedAshworthResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        String result = "";
        for (T_variable t:t_variables) {
            result += t.getParamkey() +"：" + MuscleSpasticityAssessmentController.translate(t.getParamvalue()) + "<br>";
        }
        return  result;
    }

}
