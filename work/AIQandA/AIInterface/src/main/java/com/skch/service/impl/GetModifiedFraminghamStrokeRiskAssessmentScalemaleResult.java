package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.enumPack.GetScores;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScoreByLetter;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetModifiedFraminghamStrokeRiskAssessmentScalemaleResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        Integer score = 0;
        for (T_variable t_variable : t_variables) {
            String paramKey = t_variable.getParamkey().toUpperCase();
            String paramValue = t_variable.getParamvalue().toUpperCase();
            if("年龄（岁）".equals(paramKey)||"未治疗时收缩压（mmHg）".equals(paramKey)||"治疗后的收缩压（mmHg）".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Multiply,1);
            }else if("糖尿病史".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Multiply,2);
            }else if("吸烟".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Multiply,3);
            }else if("心血管病".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Multiply,4);
            }else if("心房颤动".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Multiply,4);
            }else if("左心室肥厚".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Multiply,5);
            }
        }
        int probability = 0;
        switch (score){
            case 1:
            case 2:
                probability = 3;
                break;
            case 3:
            case 4:
                probability = 4;
                break;
            case 5:
            case 6:
                probability = 5;
                break;
            case 7:
                probability = 6;
                break;
            case 8:
                probability = 7;
                break;
            case 9:
                probability = 8;
                break;
            case 10:
                probability = 10;
                break;
            case 11:
                probability = 11;
                break;
            case 12:
                probability = 13;
                break;
            case 13:
                probability = 15;
                break;
            case 14:
                probability = 17;
                break;
            case 15:
                probability = 20;
                break;
            case 16:
                probability = 22;
                break;
            case 17:
                probability = 26;
                break;
            case 18:
                probability = 29;
                break;
            case 19:
                probability = 33;
                break;
            case 20:
                probability = 37;
                break;
            case 21:
                probability = 42;
                break;
            case 22:
                probability = 47;
                break;
            case 23:
                probability = 52;
                break;
            case 24:
                probability = 57;
                break;
            case 25:
                probability = 63;
                break;
            case 26:
                probability = 68;
                break;
            case 27:
                probability = 74;
                break;
            case 28:
                probability = 79;
                break;
            case 29:
                probability = 84;
                break;
            case 30:
                probability = 88;
                break;

        }
        String result = "您的总分为"+score+"，10年内得卒中风险为"+probability+"%";
        return result;
    }
}
