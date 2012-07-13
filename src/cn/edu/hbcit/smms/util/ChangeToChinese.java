package cn.edu.hbcit.smms.util;

import org.apache.log4j.Logger;


public class ChangeToChinese {
	protected final Logger log = Logger.getLogger(ChangeToChinese.class.getName());
	public String toChinese( String text ){
		try{
			text = new String(text.getBytes("ISO-8859-1"),"utf-8");
		}catch(Exception e){
			log.error("toChinese"+e.getMessage());
		}
		return text;
	}

}
