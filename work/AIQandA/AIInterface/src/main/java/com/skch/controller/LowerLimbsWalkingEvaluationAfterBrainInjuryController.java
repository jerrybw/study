package com.skch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.skch.util.JSONUtil.*;
/**
 * Created by XX on 2017/7/25.
 */
@RestController
public class LowerLimbsWalkingEvaluationAfterBrainInjuryController {

    @RequestMapping("LowerLimbsWalkingEvaluationAfterBrainInjury")
    public String lowerLimbsWalkingEvaluationAfterBrainInjury(String str){
        String s1 = getValue(str,"s1");
        String answer = "{Words:\"根据回答，判断病人的下肢步行分级为"+s1+"级\"}";
        return answer;
    }
}
