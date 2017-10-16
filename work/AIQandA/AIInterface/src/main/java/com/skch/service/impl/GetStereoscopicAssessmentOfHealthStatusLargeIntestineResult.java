/**
 * title:com.skch.service.impl.GetStereoscopicAssessmentOfHealthStatusLargeIntestineResult.java
 * date:2017年9月29日上午10:25:42
 * version:1.0
 * description:健康状况立体评估——大肠
 * 一、计算症状计数：按照选项顺序依次是：A：0分，B：1分，C：2分，D:3分，相加得总分即本部分的症状计数。
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
 * developer:徐希武
 * date:2017年9月29日上午10:25:42
 * description:
 */
public class GetStereoscopicAssessmentOfHealthStatusLargeIntestineResult implements GetResultInter {

	/* (non-Javadoc)
	 * @see com.skch.service.GetResultInter#getResult(java.util.Map)
	 */
	@Override
	public String getResult(Map<String, List<T_variable>> param) throws Exception {
		List<T_variable> list = param.get("t_variables");
		int score = 0;
		for(int i=0;i<list.size();i++) {
				if(list.get(i).getParamvalue().toUpperCase().equals("A")) {
					score+=0;
				}else if(list.get(i).getParamvalue().toUpperCase().equals("B")) {
					score+=1;
				}else if(list.get(i).getParamvalue().toUpperCase().equals("C")){
					score+=2;
				}else if(list.get(i).getParamvalue().toUpperCase().equals("D")) {
					score+=3;
				}
		}
		float temp = (float)score/list.size();
		return "健康状况立体评估——大肠:\n症状计数:"+score+"  症状负担:"+FormatNumberUtils.getOneFloat(temp)+"  "+(temp>1?"症状负担明显，症状负担在增加":"症状负担较小");
	}

}
