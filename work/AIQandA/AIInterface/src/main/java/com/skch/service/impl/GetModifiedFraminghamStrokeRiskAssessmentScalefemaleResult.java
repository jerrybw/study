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
public class GetModifiedFraminghamStrokeRiskAssessmentScalefemaleResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        Integer score = 0;
        for (T_variable t_variable : t_variables) {
            String paramKey = t_variable.getParamkey().toUpperCase();
            String paramValue = t_variable.getParamvalue().toUpperCase();
            if("年龄（岁）".equals(paramKey)){
                score += getScoreByLetter(paramValue, GetScores.Multiply,1);
            }else if("未治疗时收缩压（mmHg）".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Add,1);
            }else if("治疗后的收缩压（mmHg）".equals(paramKey)){
                score += getScoreByLetter(paramValue,GetScores.Add,1);
            }else if("糖尿病史".equals(paramKey)){
                score += getScoreByLetter(paramValue, GetScores.Multiply,3);
            }else if("吸烟".equals(paramKey)){
                score += getScoreByLetter(paramValue, GetScores.Multiply,3);
            }else if("心血管病".equals(paramKey)){
                score += getScoreByLetter(paramValue, GetScores.Multiply,2);
            }else if("心房颤动".equals(paramKey)){
                score += getScoreByLetter(paramValue, GetScores.Multiply,6);
            }else if("左心室肥厚".equals(paramKey)){
                score += getScoreByLetter(paramValue, GetScores.Multiply,4);
            }
        }
        int probability = 0;
        switch (score){
            case 1:
            case 2:
                probability = 1;
                break;
            case 3:
            case 4:
            case 5:
                probability = 2;
                break;
            case 6:
                probability = 3;
                break;
            case 7:
            case 8:
                probability = 4;
                break;
            case 9:
                probability = 5;
                break;
            case 10:
                probability = 6;
                break;
            case 11:
                probability = 8;
                break;
            case 12:
                probability = 9;
                break;
            case 13:
                probability = 11;
                break;
            case 14:
                probability = 13;
                break;
            case 15:
                probability = 16;
                break;
            case 16:
                probability = 19;
                break;
            case 17:
                probability = 23;
                break;
            case 18:
                probability = 27;
                break;
            case 19:
                probability = 32;
                break;
            case 20:
                probability = 37;
                break;
            case 21:
                probability = 43;
                break;
            case 22:
                probability = 50;
                break;
            case 23:
                probability = 57;
                break;
            case 24:
                probability = 64;
                break;
            case 25:
                probability = 71;
                break;
            case 26:
                probability = 78;
                break;
            case 27:
                probability = 84;
                break;
        }
        String result = "您的总分为"+score+"，10年内得卒中风险为"+probability+"%";
        return result;
    }
}
