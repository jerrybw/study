package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScoreByLetters;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetPHQ9DepressionScreeningScaleResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = getScoreByLetters(param,1);
        String result = "";
        if (score>=0 && score<=4){
            result="根据您的回答，我们判断您没有抑郁症,建议您注意自我保重。";
        }else if(score>=5 && score<=9){
            result="根据您的回答，我们判断您可能有轻微抑郁症，建议您观察等待，随访时重复PHQ-9";
        }else if(score>=10 && score<=14){
            result="根据您的回答，我们判断您可能有中度抑郁症,建议您考虑咨询，制定治疗计划，随访或药物治疗";
        }else if(score>=15 && score<=19){
            result="根据您的回答，我们判断您可能有可能有中重度抑郁症,建议您积极药物治疗或心理治疗";
        }else if(score>=20 && score<=27){
            result="根据您的回答，我们判断您可能有重度抑郁症 建议您立即首先选择药物治疗，若严重损伤或对治疗无效，建议转移至精神疾病专家进行心理治疗和/或综合治疗)";
        }
        return result;
    }
}
