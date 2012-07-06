/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	通用处理
 * 子模块名称：	
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-6		V1.0		李玮			新建
*/
package cn.edu.hbcit.smms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * 本类的简要描述：
 * 通用数据处理工具
 *
 * @author 李玮
 * @version 1.00  2012-6-6 新建类
 */

public class UtilTools {
	
	/**
	 * 判断字符串是否为数字类型
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
			return true;
	} 
	

}
