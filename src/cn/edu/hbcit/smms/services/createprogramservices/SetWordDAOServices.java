package cn.edu.hbcit.smms.services.createprogramservices;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.createprogramdao.SetWordDAO;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * 生成word类
 *
 *简要说明
 *
 *详细解释。
 * @author 韩鑫鹏
 * @version 1.00  2011/12/07 新規作成<br>
 */

public class SetWordDAOServices {
	
	SetWordDAO swd = new SetWordDAO();
	
	/**
	 * 设置word生成路径 并打开Document
	 * @param Document
	 * @param String path
	 */
	public void setWordPath( Document docu,String path){
		swd.setWordPath( docu, path);
	  }
	
	/**
	 * 设置表头
	 * @param docu
	 * @param group
	 */
	public void setHead(Document docu, String group){
		swd.setHead(docu, group);
    }
	 /**
	  * 百米类表格添加
	  * @param Document docu
	  * @param String title
	  * @param ArrayList arrayList
	  * @param int line
	  * @param int[] groupNum
	  */
	  public void add100Table(Document docu,String title, ArrayList arrayList, int line, int[] groupNum){
		 swd.add100Table(docu, title, arrayList, line, groupNum);
	  }
	  
	  /**
	   * 1500米类表格添加
	   * @param docu
	   * @param title
	   * @param arrayList
	   * @param line
	   * @param groupNum
	   */
	  public void add1500Table(Document docu,String title, ArrayList arrayList, int line, int[] groupNum){
		  swd.add1500Table(docu, title, arrayList, line, groupNum);
	  }
	  
	  /**
	   * 田赛表格添加
	   * @param docu
	   * @param title
	   * @param arrayList
	   * @param line
	   */
	  public void addFiledTable(Document docu,String title, ArrayList arrayList, int line){
		 swd.addFiledTable(docu, title, arrayList, line);
	  }
	  
	  
	  /**
	   * 关闭Document
	   * @param Document  docu
	   */
	  public void closeDocument(Document docu){
		  swd.closeDocument(docu);
	  }
}
