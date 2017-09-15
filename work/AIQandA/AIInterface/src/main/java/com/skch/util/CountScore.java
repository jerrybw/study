package com.skch.util;

import com.skch.entity.T_variable;
import com.skch.enumPack.GetScores;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/8.
 */
public class CountScore {
    private static String result = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int[] getScoresByMultiply(int times) {
        return new int[]{0, 1 * times, 2 * times, 3 * times, 4 * times, 5 * times, 6 * times, 7 * times, 8 * times, 9 * times,
                10 * times, 11 * times, 12 * times, 13 * times, 14 * times, 15 * times, 16 * times, 17 * times, 18 * times,
                19 * times, 20 * times, 21 * times, 22 * times, 23 * times, 24 * times, 25 * times, 26 * times};
    }

    private static int[] getScoresByAdd(int add) {
        return new int[]{0 + add, 1 + add, 2 + add, 3 + add, 4 + add, 5 + add, 6 + add, 7 + add, 8 + add, 9 + add,
                10 + add, 11 + add, 12 + add, 13 + add, 14 + add, 15 + add, 16 + add, 17 + add, 18 + add,
                19 + add, 20 + add, 21 + add, 22 + add, 23 + add, 24 + add, 25 + add, 26 + add};
    }

    public static Integer getScore(Map<String, List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        Integer score = 0;
        for (T_variable t_variable : t_variables) {
            String paramValue = t_variable.getParamvalue();
            if ("能".equals(paramValue) || "是".equals(paramValue)) {
                score++;
            }
        }
        return score;
    }

    public static Integer getScoreByLetters(Map<String, List<T_variable>> param, int times) {
        List<T_variable> t_variables = param.get("t_variables");
        Integer score = 0;
        for (T_variable t_variable : t_variables) {
            String paramValue = t_variable.getParamvalue().toUpperCase();
            score += getScoreByLetter(paramValue, GetScores.Multiply, times);
        }
        return score;
    }

    public static int getScoreByLetter(String letter,GetScores getScores,int times) {
        int[] scores = null;
        if(GetScores.Multiply == getScores){
            scores = getScoresByMultiply(times);
        }else if(GetScores.Add == getScores){
            scores = getScoresByAdd(times);
        }
        if (result.contains(letter)) {
            return scores[result.indexOf(letter)];
        }
        return 0;
    }

    public static int getScoreByLetter(String letter,int[] scores) {
        if (result.contains(letter)) {
            return scores[result.indexOf(letter)];
        }
        return 0;
    }

}
