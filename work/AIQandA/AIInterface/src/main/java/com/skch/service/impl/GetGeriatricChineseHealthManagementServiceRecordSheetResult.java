/**
 * title:com.skch.service.impl.GetGeriatricChineseHealthManagementServiceRecordSheetResult.java
 * date:2017年9月29日下午1:42:57
 * version:1.0
 * description:老年人中医药健康管理服务记录表
 * 评分标准：1、1-6题不计分；7-39题按照选项依次是：A:1分，B:2分，C:3分，D:4分，E:5分
          2、体质类型及对应问题
             ①气虚质：第8、9、10、20题；
             ②阳虚质：第17、18、19、35题；
             ③阴虚质：第16、27、32、37题；
             ④痰湿质：第15、22、34、38题；
             ⑤湿热质：第29、31、33、36题；
             ⑥血瘀质：第25、28、30、39题；
             ⑦气郁质：第11、12、13、14题；
             ⑧特禀质：第21、23、24；26题；
             ⑨平和质:第7、8、10、11、19题（其中，第8、10、11、19题反向计分，即A：5分，B：4分，C：3分，D：2分，E：1分）
		评估结果：
		1、各题目得分相加之和≥11分，判定结果为“是”；
		各题目得分相加之和为9～10分，判定结果为“倾向是”；
		各条目得分相加之和≤8分，判定结果为“否”。
		（次判定方法用于气虚质、阳虚质、阴虚质、痰湿质、湿热质、血瘀质、气郁质、特禀质的判定）
          2、各条目得分相加之和≥17分，同时其他8种体质得分均≤8分，判定结果为“是”；
          各条目得分相加之和≥17分，同时其他8种体质得分均≤10分，判定结果为“倾向是”；
          不满足上述条件者，判定结果为“否”。（次判定方法用于平和质的判定）

 */
package com.skch.service.impl;

import java.util.List;
import java.util.Map;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

/**
 * developer:徐希武 date:2017年9月29日下午1:42:57 description:
 */
public class GetGeriatricChineseHealthManagementServiceRecordSheetResult implements GetResultInter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skch.service.GetResultInter#getResult(java.util.Map)
	 */
	@Override
	public String getResult(Map<String, List<T_variable>> param) throws Exception {
		List<T_variable> list = param.get("t_variables");
		int[] scores = new int[list.size() - 6];
		int score = 0;
		for (int i = 0; i < list.size(); i++) {
			// 接下来的才是正确的分数
			if (i >=6) {
				// 首先要判断是不是 8 10 11 19 题，因为这些题是反向得分 A5 B4 C3 D2 E1
				if (i != 7 || i != 9 || i != 10 || i != 18) {
					switch (list.get(i).getParamvalue().toUpperCase()) {
					case "A":
						score = 1;
						break;
					case "B":
						score = 2;
						break;
					case "C":
						score = 3;
						break;
					case "D":
						score = 4;
						break;
					case "E":
						score = 5;
						break;
					default:
						score = 0;
						break;
					}
				} else {
					switch (list.get(i).getParamvalue().toUpperCase()) {
					case "A":
						score = 5;
						break;
					case "B":
						score = 4;
						break;
					case "C":
						score = 3;
						break;
					case "D":
						score = 2;
						break;
					case "E":
						score = 1;
						break;
					default:
						score = 0;
						break;
					}
				}
				scores[i-6]=score;
			}
		}
		//在这处理数据
		//①气虚质：第8、9、10、20题；
		int qixu = scores[7-6]+scores[8-6]+scores[9-6]+scores[19-6];
		//②阳虚质：第17、18、19、35题；
		int yangxu = scores[16-6]+scores[17-6]+scores[18-6]+scores[34-6];
		//③阴虚质：第16、27、32、37题；
		int yinxu = scores[15-6]+scores[26-6]+scores[31-6]+scores[36-6];
		//④痰湿质：第15、22、34、38题；
		int tanshi = scores[14-6]+scores[21-6]+scores[33-6]+scores[37-6];
		//⑤湿热质：第29、31、33、36题；
		int shire = scores[28-6]+scores[30-6]+scores[32-6]+scores[35-6];
		//⑥血瘀质：第25、28、30、39题；
		int xueyu = scores[24-6]+scores[27-6]+scores[29-6]+scores[38-6];
		//⑦气郁质：第11、12、13、14题；
		int qiyu = scores[10-6]+scores[11-6]+scores[12-6]+scores[13-6];
		//⑧特禀质：第21、23、24；26题；
		int tebing = scores[20-6]+scores[22-6]+scores[23-6]+scores[25-6];
		//⑨平和质:第7、8、10、11、19题
		int pinghe = scores[6-6]+scores[7-6]+scores[9-6]+scores[10-6]+scores[18-6];
		StringBuffer buffer = new StringBuffer();
		buffer.append("根据您的得分，评定您的体质如下：\n");
		buffer.append("气虚质:").append(caculateLevel(qixu)).append("\n");
		buffer.append("阳虚质:").append(caculateLevel(yangxu)).append("\n");
		buffer.append("阴虚质:").append(caculateLevel(yinxu)).append("\n");
		buffer.append("痰湿质:").append(caculateLevel(tanshi)).append("\n");
		buffer.append("湿热质:").append(caculateLevel(shire)).append("\n");
		buffer.append("血瘀质:").append(caculateLevel(xueyu)).append("\n");
		buffer.append("气郁质:").append(caculateLevel(qiyu)).append("\n");
		buffer.append("特禀质:").append(caculateLevel(tebing)).append("\n");
		if(pinghe>=17) {
			//进入判断其他条件
			if(qixu<=8&&yangxu<=8&&yinxu<=8&&tanshi<=8&&shire<=8&&xueyu<=8&&qiyu<=8&&tebing<=8) {
				buffer.append("平和质:"+"是");
			}else if(qixu<=10&&yangxu<=10&&yinxu<=10&&tanshi<=10&&shire<=10&&xueyu<=10&&qiyu<=10&&tebing<=10) {
				buffer.append("平和质:"+"是");
			}
		}else {
			buffer.append("平和质:"+"否");
		}
		return buffer.toString();
	}
	private String caculateLevel(int number) {
		if(number>=11) {
			return "是";
		}else if(number<=10&&number>=9) {
			return "倾向是";
		}else {
			return "否";
		}
	}
}
