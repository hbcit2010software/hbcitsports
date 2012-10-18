
package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.io.IOException;
import java.awt.Color;   
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.util.ArrayList;

import cn.edu.hbcit.smms.pojo.GameManagePoJo;

import com.lowagie.text.Cell;   
import com.lowagie.text.Document;   
import com.lowagie.text.DocumentException;   
import com.lowagie.text.Element;   
import com.lowagie.text.Font;   
import com.lowagie.text.PageSize;   
import com.lowagie.text.Paragraph;   
import com.lowagie.text.Table;   
import com.lowagie.text.pdf.BaseFont;   
import com.lowagie.text.rtf.RtfWriter2; 
import com.lowagie.text.DocumentException;


/**
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 生成word文档类
 *
 *
 * @author 刘然
 * @version 1.00  2012/06/13 新規作成<br>
 */
public class GameManageCreateWordOfAthleteInfDao {

	public void createDocContext(String file,String fileName,ArrayList athleteList)throws DocumentException, IOException{   

		//设置纸张大小   
		try{
        Document document = new Document(PageSize.A4);   
        //建立一个书写器，与document对象关联   
        RtfWriter2.getInstance(document, new FileOutputStream(file + fileName + "成绩单.doc"));   
        document.open();   
        //设置中文字体   
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);   
        //标题字体风格   
        Font titleFont = new Font(bfChinese,16,Font.BOLD);   
        //正文字体风格   
        Font contextFont = new Font(bfChinese,10,Font.NORMAL);   
        //System.out.println("WORD====="+fileName);
        Paragraph title = new Paragraph(fileName+"成绩单");   
        //设置标题格式对齐方式   
        title.setAlignment(Element.ALIGN_CENTER);   
        title.setFont(titleFont);   
        document.add(title);
      //设置Table表格,创建一个7列的表格   
        Table table = new Table(7);   
        int width[] = {10,16,15,10,16,15,18};//设置每列宽度比例   
        table.setWidths(width);   
        table.setWidth(90);//占页面宽度比例   
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满   
        table.setBorderWidth(1);//边框宽度   
        Cell cell = new Cell();
        cell.setVerticalAlignment(Element.ALIGN_CENTER);   
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);   
        table.addCell(new Cell("排名"));
        table.addCell(new Cell("运动员号码"));
        table.addCell(new Cell("运动员名称"));
        table.addCell(new Cell("性别"));
        table.addCell(new Cell("运动员成绩"));
        table.addCell(new Cell("破纪录级别"));
        table.addCell(new Cell("部门名称"));
        for (int i = 0; i< athleteList.size(); i++){        	
        	GameManagePoJo gm = (GameManagePoJo)athleteList.get(i);
        	if(gm.getFoul().equals("1")){
        		continue;
        	}else{
        	table.addCell(new Cell("第"+(i+1)+"名"));        	
        	table.addCell(new Cell(gm.getPlayernum()));
        	table.addCell(new Cell(gm.getPlayername()));
        	table.addCell(new Cell(gm.getPlayersex()));
        	table.addCell(new Cell(gm.getScore()));
        	table.addCell(new Cell(gm.getRecordlevel()));
        	table.addCell(new Cell(gm.getDepartname())); 	
	       }}
        document.add(table);
        Paragraph bottomName1 = new Paragraph("总记录长签字：_________________");   
        bottomName1.setAlignment(Element.ALIGN_LEFT);   
        document.add(bottomName1);
        Paragraph bottomName2 = new Paragraph("总裁判长签字：_________________");   
        bottomName1.setAlignment(Element.ALIGN_LEFT);     
        document.add(bottomName2);
        document.close(); 
        }
	catch(Exception e){
		e.printStackTrace();
	}}
		
	}

