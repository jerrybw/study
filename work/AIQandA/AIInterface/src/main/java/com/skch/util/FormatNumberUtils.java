/**
 * title:com.skch.util.FormatNumberUtils.java
 * date:2017年9月29日上午10:19:56
 * version:
 * description:
 */
package com.skch.util;

import java.text.DecimalFormat;

/**
 * developer:徐希武
 * date:2017年9月29日上午10:19:56
 * description:
 */
public class FormatNumberUtils {
	
	/**
	 * 
	 * date:2017年9月29日 上午10:21:20
	 * description:获取以为小数
	 */
	public static String getOneFloat(float number) {
		return new DecimalFormat("0.0").format(number);
	}
}
