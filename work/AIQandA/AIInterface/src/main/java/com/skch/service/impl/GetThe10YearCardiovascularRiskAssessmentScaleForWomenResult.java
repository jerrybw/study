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
public class GetThe10YearCardiovascularRiskAssessmentScaleForWomenResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        Integer score = 0;
        String probability = "";
        String result = "";
        Map<String,String> map = new HashMap<String,String>();
        for (T_variable t_variable : t_variables) {
            String paramKey = t_variable.getParamkey().toUpperCase();
            String paramValue = t_variable.getParamvalue().toUpperCase();
            map.put(paramKey,paramValue);
        }
        String age = map.get("年龄");
        String cholesterol = map.get("胆固醇");
        String smoking = map.get("吸烟");
        String treatment = map.get("治疗");
        String pressure = map.get("收缩压");
        if(age != null&&cholesterol!=null&&treatment!=null&&smoking!=null&&pressure!=null) {
            if (age.equals("A")) {
                score += -7;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,4,8,11,13});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,9);
            } else if (age.equals("B")) {
                score += -3;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,4,8,11,13});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,9);
            } else if (age.equals("C")) {
                score += 0;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,3,6,8,10});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,7);
            } else if (age.equals("D")) {
                score += 3;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,3,6,8,10});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,7);
            } else if (age.equals("E")) {
                score += 6;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,2,4,5,7});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,4);
            } else if (age.equals("F")) {
                score += 8;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,2,4,5,7});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,4);
            } else if (age.equals("G")) {
                score += 10;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,2,3,4});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,2);
            } else if (age.equals("H")) {
                score += 12;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,2,3,4});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,2);
            } else if (age.equals("I")) {
                score += 14;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,1,2,2});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,1);
            } else if (age.equals("J")) {
                score += 16;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,1,2,2});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,1);
            }else {
                return "";
            }
        }
        if("A".equals(treatment)){
            score += CountScore.getScoreByLetter(pressure,new int[]{0,1,2,3,4});
        }else if("B".equals(treatment)){
            score += CountScore.getScoreByLetter(pressure,new int[]{0,3,4,5,6});
        }else {
            return "";
        }
        if(score < 9){
            probability = "<1";
        }else {
            switch (score){
                case 9:
                case 10:
                case 11:
                case 12:
                    probability = "为1";
                    break;
                case 13:
                case 14:
                    probability = "为2";
                    break;
                case 15:
                    probability = "为3";
                    break;
                case 16:
                    probability = "为4";
                    break;
                case 17:
                    probability = "为5";
                    break;
                case 18:
                    probability = "为6";
                    break;
                case 19:
                    probability = "为8";
                    break;
                case 20:
                    probability = "为11";
                    break;
                case 21:
                    probability = "为14";
                    break;
                case 22:
                    probability = "为17";
                    break;
                case 23:
                    probability = "为22";
                    break;
                case 24:
                    probability = "为27";
                    break;
                default:
                    probability = "≥30";
            }
        }
        result = "根据回答判读您10年内得心血管病风险"+probability+"%";
        return result;
    }
}
