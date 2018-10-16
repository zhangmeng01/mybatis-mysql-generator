package com.syswin.xwtoon.db.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述: 代码生成工具类
 * 
 * @date 2016年7月22日 下午4:55:40
 * @version 0.0.1
 * @author llh
 */
public class GenUtils {

	private static Logger logger = LoggerFactory.getLogger(GenUtils.class);

	/**
	 * 功能描述:处理目标String的数据格式
	 * 
	 * @date 2016年7月22日 下午4:56:07
	 * @version 2.2.0
	 * @author llh
	 */
	public static String replaceUnderlineAndfirstToUpper(String srcStr, String mark, String targetMark) {
		String newString = "";
		int first = 0;
		while (srcStr.indexOf(mark) != -1) {
			first = srcStr.indexOf(mark);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + targetMark;
				srcStr = srcStr.substring(first + mark.length(), srcStr.length());
				srcStr = firstCharacterToUpper(srcStr);
			}
		}
		newString = newString + srcStr;
		return newString;
	}

	/**
	 * 首字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String firstCharacterToUpper(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
	}

	/**
	 * 首字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String firstCharacterToLower(String srcStr) {
		return srcStr.substring(0, 1).toLowerCase() + srcStr.substring(1);
	}

}
