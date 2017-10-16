/**
 * title:com.skch.service.impl.GetFemaleSexualFunctionIndex_FSFIResult.java
 * date:2017年9月29日下午3:08:16
 * version:1.0
 * description:女性性功能指数调查量表
 * 
 * 第2～20题按照选项顺序依次是：A、5分，B、4分，C、3分，D、2分，E、1分，F、0分，各分数相加得总分。
 */
package com.skch.service.impl;

import java.util.List;
import java.util.Map;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

/**
 * developer:徐希武 date:2017年9月29日下午3:08:16 description:
 */
public class GetFemaleSexualFunctionIndex_FSFIResult implements GetResultInter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skch.service.GetResultInter#getResult(java.util.Map)
	 */
	@Override
	public String getResult(Map<String, List<T_variable>> param) throws Exception {
		// TODO Auto-generated method stub
		List<T_variable> list = param.get("t_variables");
		list.remove(0);
		int score = 0;
		for (T_variable t_variable : list) {
			switch (t_variable.getParamvalue().toUpperCase()) {
			case "A":
					score+=5;
				break;
			case "B":
					score+=4;
				break;
			case "C":
					score+=3;
				break;
			case "D":
					score+=2;
				break;
			case "E":
					score+=1;
				break;
			case "F":
					score+=0;
				break;
			}
		}
		return "您的得分是:"+score;
	}

}
