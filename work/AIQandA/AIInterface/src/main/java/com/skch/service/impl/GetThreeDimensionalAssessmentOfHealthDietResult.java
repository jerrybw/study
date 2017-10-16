/**
 * title:com.skch.service.impl.GetThreeDimensionalAssessmentOfHealthDietResult.java
 * date:2017年9月28日下午5:32:15
 * version:1.0
 * description:健康状况立体评估——饮食
 * 	一、计算症状计数：按照选项顺序依次是：A：0分，B：1分，C：2分，D:3分，相加得总分即本部分的症状计数。
	二、计算症状负担：本部分的症状负担=本部分的症状计数（即总分）除以本部分问题总数（即数字20）
    如果计算结果是1以上，则说明该患者症状负担很明显；在1以上数字变大，症状负担也明显增长。
 */
package com.skch.service.impl;

import java.util.List;
import java.util.Map;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;
import com.skch.util.FormatNumberUtils;

/**
 * developer:徐希武 date:2017年9月28日下午5:32:15 description:
 */
public class GetThreeDimensionalAssessmentOfHealthDietResult implements GetResultInter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skch.service.GetResultInter#getResult(java.util.Map)
	 */
	@Override
	public String getResult(Map<String, List<T_variable>> param) throws Exception {
		List<T_variable> list = param.get("t_variables");
		int score = 0;
		for (T_variable t_variable : list) {
			switch (t_variable.getParamvalue().toUpperCase()) {
			case "A":
					score+=0;
				break;
			case "B":
					score+=1;
				break;
			case "C":
					score+=2;
				break;
			case "D":
					score+=3;
				break;

			default:
				score+=0;
				break;
			}
		}
		StringBuffer buffer = new StringBuffer();
		float result = (float)score/list.size();
		buffer.append("您的健康立体评估--药物:\n症状计数:").append(score).append("\n");
		buffer.append("症状负担:").append(FormatNumberUtils.getOneFloat(result)).append("         ").append(result>1?"症状负担明显，症状负担在增加":"症状负担较小");
		//计算症状负担
		return buffer.toString();
	}

}

