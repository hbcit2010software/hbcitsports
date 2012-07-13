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
import org.apache.log4j.Logger;

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
	protected final Logger log = Logger.getLogger(UtilTools.class.getName());
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
	
	/**
	 * ISO-8859-1字符串转换为utf-8
	 * @param text
	 * @return
	 */
	public String toUTF8( String text ){
		try{
			text = new String(text.getBytes("ISO-8859-1"),"utf-8");
		}catch(Exception e){
			log.error("toChinese"+e.getMessage());
		}
		return text;
	}
	

}
