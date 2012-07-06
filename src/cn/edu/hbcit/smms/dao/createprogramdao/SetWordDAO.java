package cn.edu.hbcit.smms.dao.createprogramdao;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * 秩序册——赛事分组 word生成
 * @author 韩鑫鹏
 * 2012——06——19 17:12
 */
public class SetWordDAO {

	/**
	 * 设置word生成路径 并打开Document
	 * @param Document
	 * @param String path
	 */
	public void setWordPath( Document docu,String path){
		   try { 
		      RtfWriter2.getInstance(docu,  
				    new FileOutputStream(path)); 
		      docu.open();  
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	  }
	
	/**
	 * 设置表头
	 * @param docu
	 * @param group
	 */
	public void setHead(Document docu, String group){
		try{
			Paragraph p = new Paragraph(group, new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0)));
			p.setAlignment(1);
			docu.add(p);
		}catch(Exception e){
			e.printStackTrace();
		}
    	
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
		  try{
			  Table table = new Table(line);     
			  table.setBorderWidth(0);
			  table.setBorderWidth(0);
			  table.setPadding(0);    
			  table.setSpacing(0); 
			  Cell cl = new Cell(new Paragraph(title, new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0))));//单元格   
			  cl.setBorderWidth(0);
			  
			  cl.setColspan(line);
		      table.addCell(cl);
		      
			  int count = 0;
			  for (int i = 0; i < groupNum.length ; i++){
				  Cell cs = new Cell("第"+(i+1)+"组");//单元格   
				  cs.setBorderWidth(0);
			      table.addCell(cs);
				  for (int j = 0; j < groupNum[i]; j++){
			    	Cell cc = new Cell((String)arrayList.get(count));//单元格   
			    	cc.setBorderWidth(0);
			    	table.addCell(cc);
			    	count++;
				  }
				  for (int k = 0; k < (line - groupNum[i] - 1); k++){
					  Cell cc = new Cell("");//单元格   
				    	cc.setBorderWidth(0);
				    	table.addCell(cc);
				  }
			     }
			  docu.add(table);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		   
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
		  try{
			  Table table = new Table(line);     
			  table.setBorderWidth(0);
			  table.setBorderWidth(0);
			  table.setPadding(0);    
			  table.setSpacing(0); 
			  Cell cl = new Cell(new Paragraph(title, new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0))));//单元格   
			  cl.setBorderWidth(0);
			  cl.setColspan(line);
		      table.addCell(cl);
		      
			  int count = 0;
			  for (int i = 0; i < groupNum.length ; i++){
				  Cell cs = new Cell("第"+(i+1)+"组");//单元格   
				  cs.setBorderWidth(0);
			      table.addCell(cs);
				  for (int j = 0; j < groupNum[i]; j++){
			    	Cell cc = new Cell((String)arrayList.get(count));//单元格   
			    	cc.setBorderWidth(0);
			    	table.addCell(cc);
			    	count++;
				  }
				 int temp = groupNum[i] % line;
				  for (int k = 0; k < (line - temp - 1); k++){
					  Cell cc = new Cell("");//单元格   
				    	cc.setBorderWidth(0);
				    	table.addCell(cc);
				  }
			    }
			  docu.add(table);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		   
	  }
	  /**
	   * 田赛表格添加
	   * @param docu
	   * @param title
	   * @param arrayList
	   * @param line
	   */
	  public void addFiledTable(Document docu,String title, ArrayList arrayList, int line){
		  try{
			  Table table = new Table(line);     
			  table.setBorderWidth(0);
			  table.setBorderWidth(0);
			  table.setPadding(0);    
			  table.setSpacing(0); 
			  Cell cl = new Cell(new Paragraph(title, new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0))));//单元格   
			  cl.setBorderWidth(0);
			  cl.setColspan(line);
		      table.addCell(cl);
		      int arrayListSize = arrayList.size();
			  int nullCellNum = line - (arrayListSize % line);
			  for (int i = 0; i < arrayList.size(); i++){
				  
			    	Cell cc = new Cell((String)arrayList.get(i));//单元格   
			    	//System.out.print((String)arrayList.get(i));
			    	cc.setBorderWidth(0);
			    	table.addCell(cc);
			    	
				  }
			 for (int k = 0; k < nullCellNum; k++){
				  Cell cc = new Cell("");//单元格   
				    cc.setBorderWidth(0);
				    table.addCell(cc);
				}
			  
			  docu.add(table);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		   
	  }
	  
	  /**
	   * 关闭Document
	   * @param Document  docu
	   */
	  public void closeDocument(Document docu){
		  try{
			  docu.close(); 
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }
}
