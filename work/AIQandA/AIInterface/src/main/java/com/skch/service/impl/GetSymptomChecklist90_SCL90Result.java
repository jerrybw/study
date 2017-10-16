/**
 * title:com.skch.service.impl.GetSymptomChecklist90_SCL90Result.java
 * date:2017年9月28日下午2:56:51
 * version:1.0
 * description:症状自评量表（SCL-90）结果计算
 * 一、计分规则：按照选项顺序依次是：A：0分，B：1分，C：2分,D：3分，E：4分
	二、评估结果：
			  1、总分：90个项目所得分之和；
              2、总症状指数（即总均分）：将总分除以90（=总分÷90）；
              3、阳性症状痛苦水平：将所有得分为1～4分的项目的总分除以得分为1～4的项目数；
              4、9个因子分：①躯体化：第1，4，12，27，40，42，48，49，52，53，56，58题总分除以12；
                            ②强迫症状：第3，9，10，28，38，45，46，51，55，65题总分除以10；
                            ③人际关系敏感：第6，21，34，36，37，41，61，69，73题总分除以9；
                            ④抑郁：第5，14，15，20，22，26，29，30，31，32，54，71，79题总分除以13；
                            ⑤焦虑：第2，17，23，33，39，57，72，78，80，86题总分除以10；
                            ⑥敌对：第11，24，63，67，74，81题总分除以6；
                            ⑦恐怖：第13，25，47，50，70，75，82题总分除以7；
                            ⑧偏执：第8，18，43，68，76，83题总分除以6；
                            ⑨精神病性：第7，16，35，62，77，84，85，87，88，90题总分除以10；
                            ⑩其他：第19，44，59，60，64，66，89题总分除以7
 */
package com.skch.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

/**
 * developer:徐希武 date:2017年9月28日下午2:56:51 description:症状自评量表（SCL-90）结果计算
 */
public class GetSymptomChecklist90_SCL90Result implements GetResultInter {
	private int total;// 总分
	private int[] scores;//分数数组
	private int oneToFourScoreTotal;
	private int oneTwoFourScoreNum;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skch.service.GetResultInter#getResult(java.util.Map)
	 */

	@Override
	public String getResult(Map<String, List<T_variable>> param) throws Exception {
		// 开始计算结果
		List<T_variable> listVariables = param.get("t_variables");
		scores = new int[listVariables.size()];
		for (T_variable t_variable : listVariables) {
			int score = 0;
			switch (t_variable.getParamvalue().toUpperCase()) {
			case "A":
				score = 0;
				break;
			case "B":
				score = 1;
				break;
			case "C":
				score = 2;
				break;
			case "D":
				score = 3;
				break;
			case "E":
				score = 4;
				break;
			default:
				score = 0;
				break;
			}
			scores[listVariables.indexOf(t_variable)]=score;
			//计算总分
			total +=score;
			//计算阳性症状数据
			if(score==1||score==4) {
				oneTwoFourScoreNum++;
				oneToFourScoreTotal+=score;
			}
		}
		//由于题目和索引是-1的关系，所以程序中已经自动-1
		//躯体化：第1，4，12，27，40，42，48，49，52，53，56，58题总分除以12；
		float quti = (float)(scores[0]+scores[3]+scores[11]+scores[26]+scores[39]+scores[41]+scores[47]+scores[48]+scores[51]+scores[52]
				+scores[55]+scores[57])/12.0f;
		//强迫症状：第3，9，10，28，38，45，46，51，55，65题总分除以10；
		float qiangpo = (float)(scores[2]+scores[8]+scores[9]+scores[27]+scores[37]+scores[44]+scores[45]+scores[50]
				+scores[54]+scores[64])/10.0f;
		//人际关系敏感：第6，21，34，36，37，41，61，69，73题总分除以9
		float renji = (float)(scores[5]+scores[20]+scores[33]+scores[35]+scores[36]+scores[40]+scores[60]+scores[68]+scores[72])/9.0f;
		//抑郁：第5，14，15，20，22，26，29，30，31，32，54，71，79题总分除以13；
		float youyu = (float)(scores[4]+scores[13]+scores[14]+scores[19]+scores[21]+scores[25]+scores[28]+scores[29]+scores[30]+scores[31]
				+scores[53]+scores[70]+scores[78])/13.0f;
		//焦虑：第2，17，23，33，39，57，72，78，80，86题总分除以10；
		float jiaolv = (float)(scores[1]+scores[16]+scores[22]+scores[32]+scores[38]+scores[56]+scores[71]+scores[77]
				+scores[79]+scores[85])/10.0f;
		//敌对：第11，24，63，67，74，81题总分除以6；
		float didui = (float)(scores[10]+scores[23]+scores[62]+scores[66]+scores[73]+scores[80])/6.0f;
		//恐怖：第13，25，47，50，70，75，82题总分除以7；
		float kongbu = (float)(scores[12]+scores[24]+scores[46]+scores[49]+scores[69]+scores[74]+scores[81])/7.0f;
		//偏执：第8，18，43，68，76，83题总分除以6；
		float pianzhi = (float)(scores[7]+scores[17]+scores[42]+scores[67]+scores[75]+scores[82])/6.0f;
		//精神病性：第7，16，35，62，77，84，85，87，88，90题总分除以10；
		float jingshen = (float)(scores[6]+scores[15]+scores[34]+scores[61]+scores[76]+scores[83]+scores[84]+scores[86]
				+scores[87]+scores[89])/10.0f;
		//其他：第19，44，59，60，64，66，89题总分除以7
		float other = (float)(scores[18]+scores[43]+scores[58]+scores[59]+scores[63]+scores[65]+scores[88])/7.0f;
		
		//最后内容的组合
		StringBuffer buffer = new StringBuffer();
		buffer.append("评估结果是:").append("总分:").append(total).append("\n");
		buffer.append("总症状指数:").append(getTwoFloat((float)total/90f)).append("\n");
		buffer.append("阳性症状痛苦水平:").append(getTwoFloat(((float)oneToFourScoreTotal)/((float)oneTwoFourScoreNum))).append("\n");
		buffer.append("躯体化:").append(getTwoFloat(quti)).append("\n");
		buffer.append("强迫症状:").append(getTwoFloat(qiangpo)).append("\n");
		buffer.append("人际关系敏感:").append(getTwoFloat(renji)).append("\n");
		buffer.append("抑郁:").append(getTwoFloat(youyu)).append("\n");
		buffer.append("焦虑:").append(getTwoFloat(jiaolv)).append("\n");
		buffer.append("敌对:").append(getTwoFloat(didui)).append("\n");
		buffer.append("恐怖:").append(getTwoFloat(kongbu)).append("\n");
		buffer.append("偏执:").append(getTwoFloat(pianzhi)).append("\n");
		buffer.append("精神病性:").append(getTwoFloat(jingshen)).append("\n");
		buffer.append("其他:").append(getTwoFloat(other)).append("\n");
		return buffer.toString();
	}


	public String getTwoFloat(float number){
		DecimalFormat decimalFormat=new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		return decimalFormat.format(number);
	}
}
