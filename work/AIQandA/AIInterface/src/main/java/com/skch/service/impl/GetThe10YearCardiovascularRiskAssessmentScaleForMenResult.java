package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.enumPack.GetScores;
import com.skch.service.GetResultInter;
import com.skch.util.CountScore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScoreByLetter;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetThe10YearCardiovascularRiskAssessmentScaleForMenResult implements GetResultInter
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
                score += -9;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,4,7,9,11});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,8);
            } else if (age.equals("B")) {
                score += -4;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,4,7,9,11});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,8);
            } else if (age.equals("C")) {
                score += 0;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,3,5,6,8});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,5);
            } else if (age.equals("D")) {
                score += 3;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,3,5,6,8});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,5);
            } else if (age.equals("E")) {
                score += 6;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,2,3,4,5});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,3);
            } else if (age.equals("F")) {
                score += 8;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,2,3,4,5});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,3);
            } else if (age.equals("G")) {
                score += 10;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,1,2,3});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,1);
            } else if (age.equals("H")) {
                score += 11;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,1,2,3});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,1);
            } else if (age.equals("I")) {
                score += 12;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,1,0,0});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,1);
            } else if (age.equals("J")) {
                score += 14;
                score += CountScore.getScoreByLetter(cholesterol,new int[]{0,1,1,0,0});
                score += CountScore.getScoreByLetter(smoking,GetScores.Multiply,1);
            }else {
                return "";
            }
        }
        if("A".equals(treatment)){
            score += CountScore.getScoreByLetter(pressure,new int[]{0,0,1,1,2});
        }else if("B".equals(treatment)){
            score += CountScore.getScoreByLetter(pressure,new int[]{0,1,2,2,3});
        }else {
            return "";
        }
        if(score < 0){
            probability = "<1";
        }else {
            switch (score){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    probability = "为1";
                    break;
                case 5:
                case 6:
                    probability = "为2";
                    break;
                case 7:
                    probability = "为3";
                    break;
                case 8:
                    probability = "为4";
                    break;
                case 9:
                    probability = "为5";
                    break;
                case 10:
                    probability = "为6";
                    break;
                case 11:
                    probability = "为8";
                    break;
                case 12:
                    probability = "为10";
                    break;
                case 13:
                    probability = "为12";
                    break;
                case 14:
                    probability = "为16";
                    break;
                case 15:
                    probability = "为20";
                    break;
                case 16:
                    probability = "为25";
                    break;
                default:
                    probability = "≥30";
            }
        }
        result = "根据回答判读您10年内得心血管病风险"+probability+"%";
        return result;
    }
}
