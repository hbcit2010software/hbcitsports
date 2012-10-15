package cn.edu.hbcit.smms.dao.createprogramdao;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     生成秩序册
 * 子模块名称：   赛前相关的设置
 *
 * 
 *
 * 修改历史：
 * 时间			                 版本号	姓名		修改内容
 * 2012-09-110        0.1      田小英          新建
 */
/**
 * @author 田小英
 *
 */
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.smms.pojo.GameDatePlanPojo;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

public class WordGameBeforInfoDao {
	/**
	 * 创建word文档 步骤: 1,建立文档 2,创建一个书写器 3,打开文档 4,向文档中写入数据 5,关闭文档
	 */



		
		public void wordDocument( String filePath, String fileName, Map gameInfoMap, Map fildJudgeMap, Map getGameDate, 
				Map getItemByMale, Map getItemByFemale, List studentList, List teacherList,
				Map getGameDateInfo){
			//创建word文档，并设置纸张大小
			Document document = new Document(PageSize.A4);
			try{
				
				RtfWriter2.getInstance(document,
						new FileOutputStream(filePath+fileName));
				
				document.open();            //打开文档
				
				BaseFont bfFont = BaseFont.createFont("STSongStd-Light",
						"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);             // 设置中文字体
				
				
				//向文档中写入数据
				
				Paragraph presidiumInfo = new Paragraph("大会主席团及工作人员", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				presidiumInfo.setAlignment(1);
				Paragraph presidiumInfo1= new Paragraph("大会主席团", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				presidiumInfo1.setAlignment(1);
				
				Table presidiumTable = new Table(7);        //指定表格为7列
				presidiumTable.setPadding(0);
				presidiumTable.setSpacing(0);
				presidiumTable.setBorder(0);                 //设置未填入任何内容的空表格的边线为零
				String[] pre = (String[]) gameInfoMap.get("presidium");
				for(int i = 0; i < pre.length; i++){
					Cell cell0 = new Cell(pre[i]);          //表格的列为固定值，单元格要一个个的添加
					cell0.setBorderWidth(0);                //设置单元格的边线为0
					presidiumTable.addCell(cell0);
				}
				
				
				Paragraph presidiumInfo2= new Paragraph("大会组委会", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				presidiumInfo2.setAlignment(1);
				Table committeeTable = new Table(7);
				committeeTable.setPadding(0);
				committeeTable.setSpacing(0);
				committeeTable.setBorder(0);
				Cell committeeCell = new Cell("主任:");
				committeeCell.setBorderWidth(0);
				committeeTable.addCell(committeeCell);
				String[] comm1 = (String[]) gameInfoMap.get("committee1");
				for(int i = 0; i < comm1.length; i++){
				    committeeCell = new Cell(comm1[i]);
				    committeeCell.setBorderWidth(0);
				    committeeTable.addCell(committeeCell);
				}
				for(int j = 0; j < 6 - comm1.length; j++){              //设置一行结束之后余下的单元格，使之变为空单元格,下同
					committeeCell = new Cell(new Paragraph());
					committeeCell.setBorderWidth(0);
					committeeTable.addCell(committeeCell);
					
				}
				
				committeeCell = new Cell("副主任:");
				committeeCell.setBorderWidth(0);
				committeeTable.addCell(committeeCell);
				String[] comm2 = (String[]) gameInfoMap.get("committee2");
				for(int i = 0; i < comm2.length; i++){
				    committeeCell = new Cell(comm2[i]);
				    committeeCell.setBorderWidth(0);
				    committeeTable.addCell(committeeCell);
				}
				for(int j = 0; j < 6 - comm2.length; j++){             
					committeeCell = new Cell(new Paragraph());
					committeeCell.setBorderWidth(0);
					committeeTable.addCell(committeeCell);
				}
				
				committeeCell = new Cell("委员:");
				committeeCell.setBorderWidth(0);
				committeeTable.addCell(committeeCell);
				String[] comm3 = (String[]) gameInfoMap.get("committee3");
				for(int i = 0; i < comm3.length; i++){
					for(int j = 1; j < comm3.length / 6 + 1; j++){            //若内容为多行,终止变量为数据的行数,
					if( i / 6 == j && i % 6 == 0){                            //若数据满一行，则在下一行生成一个段落标记符，段落标记符占一个单元格,
						committeeCell = new Cell(new Paragraph());            //则新一行的数据会从第二个单元格开始添加，
						committeeCell.setBorderWidth(0);                      //实现之后的效果就是从下一行开始的每一行第一个单元格为空
						committeeTable.addCell(committeeCell);
					}
				 }
					committeeCell = new Cell(comm3[i]);
					committeeCell.setBorderWidth(0);
					committeeTable.addCell(committeeCell);
				}
				
				
				Paragraph presidiumInfo3= new Paragraph("大会秘书处", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				presidiumInfo3.setAlignment(1);
				Table secretariatTable = new Table(7);
				secretariatTable.setSpacing(0);
				secretariatTable.setPadding(0);
				secretariatTable.setBorder(0);
				Cell secretariatCell = new Cell("秘书长:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec1 = (String[])gameInfoMap.get("secretariat1");
				for(int i = 0; i < sec1.length; i++){
					secretariatCell = new Cell(sec1[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec1.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				secretariatCell = new Cell("副秘书长:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec2 = (String[])gameInfoMap.get("secretariat2");
				for(int i = 0; i < sec2.length; i++){
					secretariatCell = new Cell(sec2[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec2.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				secretariatCell = new Cell("会务组负责人:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec3 = (String[])gameInfoMap.get("secretariat3");
				for(int i = 0; i < sec3.length; i++){
					secretariatCell = new Cell(sec3[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec3.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				secretariatCell = new Cell("宣传组负责人:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec4 = (String[])gameInfoMap.get("secretariat4");
				for(int i = 0; i < sec4.length; i++){
					secretariatCell = new Cell(sec4[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec4.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				secretariatCell = new Cell("奖品组负责人:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec5 = (String[])gameInfoMap.get("secretariat5");
				for(int i = 0; i < sec5.length; i++){
					secretariatCell = new Cell(sec5[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec5.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				secretariatCell = new Cell("保卫组负责人:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec6 = (String[])gameInfoMap.get("secretariat6");
				for(int i = 0; i < sec6.length; i++){
					secretariatCell = new Cell(sec6[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec6.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				secretariatCell = new Cell("后勤保障组负责人:");
				secretariatCell.setBorderWidth(0);
				secretariatTable.addCell(secretariatCell);
				String[] sec7 = (String[])gameInfoMap.get("secretariat7");
				for(int i = 0; i < sec7.length; i++){
					secretariatCell = new Cell(sec7[i]);
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				for(int j = 0; j < 6 - sec7.length; j++){
					secretariatCell = new Cell(new Paragraph());
					secretariatCell.setBorderWidth(0);
					secretariatTable.addCell(secretariatCell);
				}
				
				
				
				
				Paragraph presidiumInfo4= new Paragraph("大会仲裁委员会", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				presidiumInfo4.setAlignment(1);
				Table arbitrationTable = new Table(7);
				arbitrationTable.setPadding(0);
				arbitrationTable.setSpacing(0);
				arbitrationTable.setBorder(0);
				String[] arbitration = (String[])gameInfoMap.get("arbitration");
				for(int i = 0; i < arbitration.length; i++){
					Cell arbitrationCell = new Cell(arbitration[i]);
					arbitrationCell.setBorderWidth(0);
					arbitrationTable.addCell(arbitrationCell);
				}
				/**
				 * 所有的裁判员包括田赛的
				 */
				
				Paragraph judge = new Paragraph("裁判员", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				judge.setAlignment(1);
				Table judgeTable = new Table(7);
				judgeTable.setPadding(0);
				judgeTable.setSpacing(0);
				judgeTable.setBorder(0);
				Cell judgeCell = new Cell("总裁判长：");
				judgeCell.setBorderWidth(0);
				judgeCell.setWidth(200);
				judgeTable.addCell(judgeCell);
				String[] chiefjudge1 = (String[])gameInfoMap.get("chiefjudge1");
				for(int i = 0; i < chiefjudge1.length; i++){
					
					for(int j = 1; j < chiefjudge1.length / 6 + 1; j++)  {                 
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(chiefjudge1[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - chiefjudge1.length % 6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("副总裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] chiefjudge2 = (String[])gameInfoMap.get("chiefjudge2");
				for(int i = 0; i < chiefjudge2.length; i++){
					for(int j = 1; j < chiefjudge2.length / 6 + 1; j++)  {                 
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(chiefjudge2[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - chiefjudge2.length % 6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("一、径赛裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] trackjudge = (String[])gameInfoMap.get("trackjudge");
				for(int i = 0; i < trackjudge.length; i++){
					for(int j = 1; j < trackjudge.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(trackjudge[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - trackjudge.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("检录裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] trackjudge1 = (String[])gameInfoMap.get("trackjudge1");
				for(int i = 0; i < trackjudge1.length; i++){
					for(int j = 1; j < trackjudge1.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(trackjudge1[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - trackjudge1.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("裁判长助理：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] trackjudge2 = (String[])gameInfoMap.get("trackjudge2");
				for(int i = 0; i < trackjudge2.length; i++){
					for(int j = 1; j < trackjudge2.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(trackjudge2[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - trackjudge2.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("检录员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] trackjudge3 = (String[])gameInfoMap.get("trackjudge3");
				for(int i = 0; i < trackjudge3.length; i++){
					for(int j = 1; j < trackjudge3.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(trackjudge3[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - trackjudge3.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("起点裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] startingpoint1 = (String[])gameInfoMap.get("startingpoint1");
				for(int i = 0; i < startingpoint1.length; i++){
					for(int j = 1; j < startingpoint1.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(startingpoint1[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - startingpoint1.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("裁判长助理：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] startingpoint2 = (String[])gameInfoMap.get("startingpoint2");
				for(int i = 0; i < startingpoint2.length; i++){
					for(int j = 1; j < startingpoint2.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(startingpoint2[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - startingpoint2.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("发令员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] startingpoint3 = (String[])gameInfoMap.get("startingpoint3");
				for(int i = 0; i < startingpoint3.length; i++){
					for(int j = 1; j < startingpoint3.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(startingpoint3[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - startingpoint3.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("记时长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] timejudge1 = (String[])gameInfoMap.get("timejudge1");
				for(int i = 0; i < timejudge1.length; i++){
					for(int j = 1; j < timejudge1.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(timejudge1[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - timejudge1.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("记时员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] timejudge2 = (String[])gameInfoMap.get("timejudge2");
				for(int i = 0; i < timejudge2.length; i++){
					for(int j = 1; j < timejudge2.length / 6 + 1; j++) {                
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
					
					}
				 }
					judgeCell = new Cell(timejudge2[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - timejudge2.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("司线员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] timejudge3 = (String[])gameInfoMap.get("timejudge3");
				for(int i = 0; i < timejudge3.length; i++){
						for(int j = 1; j < timejudge3.length / 6 + 1; j++)  {                  
							if( i / 6 == j && i % 6 == 0){  
								
								judgeCell = new Cell(new Paragraph());
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
						}
					judgeCell = new Cell(timejudge3[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - timejudge3.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("终点裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] endpoint1 = (String[])gameInfoMap.get("endpoint1");
				for(int i = 0; i < endpoint1.length; i++){
						for(int j = 1; j < endpoint1.length / 6 + 1; j++)  {                  
							if( i / 6 == j && i % 6 == 0){  
								
								judgeCell = new Cell(new Paragraph());
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
						}
					judgeCell = new Cell(endpoint1[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - endpoint1.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("裁判长助理：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] endpoint2 = (String[])gameInfoMap.get("endpoint2");
				for(int i = 0; i < endpoint2.length; i++){
					for(int j = 1; j < endpoint2.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(endpoint2[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - endpoint2.length; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("终点裁判员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] endpoint3 = (String[])gameInfoMap.get("endpoint3");
				for(int i = 0; i < endpoint3.length; i++){
					for(int j = 1; j < endpoint3.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(endpoint3[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - endpoint3.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("终点纪录长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] endpoint4 = (String[])gameInfoMap.get("endpoint4");
				for(int i = 0; i < endpoint4.length; i++){
					for(int j = 1; j < endpoint4.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(endpoint4[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - endpoint4.length; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("记录员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] endpoint5 = (String[])gameInfoMap.get("endpoint5");
				for(int i = 0; i < endpoint5.length; i++){
					for(int j = 1; j < endpoint5.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(endpoint5[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - endpoint5.length; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("二、田赛裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge = (String[])gameInfoMap.get("fieldjudge");
				for(int i = 0; i < fieldjudge.length; i++){
					for(int j = 1; j < fieldjudge.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge.length; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				/**
				 * 用来生成田赛裁判长
				 */
				//Table table7 = new Table(7);
				for(int i = 0; i <(Integer.parseInt((String)fildJudgeMap.get("length"))); i++){
					for( int j = 1; j < 4; j++){
						if(j==1){
						if(fildJudgeMap.get(""+i+j) != null){
							judgeCell = new Cell(fildJudgeMap.get("itemName"+i)+"裁判长");
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
							String[] judge1 = (String[])fildJudgeMap.get(i+"1");
							for( int x = 0; x < judge1.length; x++){
								judgeCell = new Cell(judge1[x]);
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
							for(int y = 0; y < 6 - judge1.length%6; y++){        
								judgeCell = new Cell(new Paragraph());
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
						}
						}
						if(j==2){
						if(fildJudgeMap.get(""+i+j) != null){
							judgeCell = new Cell("裁判长助理");
							//cel1.setColspan(2);
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
							String[] judge2 = (String[])fildJudgeMap.get(i+"2");
							for( int x = 0; x < judge2.length; x++){
								judgeCell = new Cell(judge2[x]);
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
							for(int y = 0; y < 6 - judge2.length%6; y++){        
								judgeCell = new Cell(new Paragraph());
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
						}
						}
						if(j==3){
						if(fildJudgeMap.get(""+i+j) != null){
							judgeCell = new Cell("裁判员");
							//cel2.setColspan(2);
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
							String[] judge3 = (String[])fildJudgeMap.get(i+"3");
							for( int x = 0; x < judge3.length; x++){
								judgeCell = new Cell(judge3[x]);
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
							for(int y = 0; y < 6 - judge3.length%6; y++){        
								judgeCell = new Cell(new Paragraph());
								judgeCell.setBorderWidth(0);
								judgeTable.addCell(judgeCell);
							}
						}
						}
					}
				}
				
				judgeCell = new Cell("总记录裁判长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge1 = (String[])gameInfoMap.get("fieldjudge1");
				for(int i = 0; i < fieldjudge1.length; i++){
					for(int j = 1; j < fieldjudge1.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge1[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge1.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("记录员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge2 = (String[])gameInfoMap.get("fieldjudge2");
				for(int i = 0; i < fieldjudge2.length; i++){
					for(int j = 1; j < fieldjudge2.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge2[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge2.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("检查长：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge3 = (String[])gameInfoMap.get("fieldjudge3");
				for(int i = 0; i < fieldjudge3.length; i++){
					for(int j = 1; j < fieldjudge3.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge3[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge3.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("检查员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge4 = (String[])gameInfoMap.get("fieldjudge4");
				for(int i = 0; i < fieldjudge4.length; i++){
					for(int j = 1; j < fieldjudge4.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge4[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge4.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("场地器材组长 ：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge5 = (String[])gameInfoMap.get("fieldjudge5");
				for(int i = 0; i < fieldjudge5.length; i++){
					for(int j = 1; j < fieldjudge5.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge5[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge5.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				judgeCell = new Cell("器材员：");
				judgeCell.setBorderWidth(0);
				judgeTable.addCell(judgeCell);
				String[] fieldjudge6 = (String[])gameInfoMap.get("fieldjudge6");
				for(int i = 0; i < fieldjudge6.length; i++){
					for(int j = 1; j < fieldjudge6.length / 6 + 1; j++)  {                  
						if( i / 6 == j && i % 6 == 0){  
							
							judgeCell = new Cell(new Paragraph());
							judgeCell.setBorderWidth(0);
							judgeTable.addCell(judgeCell);
						}
					}
					judgeCell = new Cell(fieldjudge6[i]);
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				for(int j = 0; j < 6 - fieldjudge6.length%6; j++){
					judgeCell = new Cell(new Paragraph());
					judgeCell.setBorderWidth(0);
					judgeTable.addCell(judgeCell);
				}
				
				
				
				
				Paragraph gameRules = new Paragraph("大会竞赛规程", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				gameRules.setAlignment(1);
				
				Paragraph date = new Paragraph("一、竞赛日期:" + getGameDate.get("sportsbegin") + "~" + getGameDate.get("sportsend"));
				Paragraph address = new Paragraph("      地点：" + getGameDate.get("address"));
				Paragraph item = new Paragraph("二、竞赛项目");
				
				List male = (List)getItemByMale.get("男子组");
				String maleItem = "";
				int count1 = male.size();
				for(int i = 0; i < male.size(); i++){
					 maleItem = maleItem + "   " + male.get(i)+"   ";
				}
				Paragraph pMale = new Paragraph("男子组:"+maleItem+"(共"+count1+"项)");
				
				List female = (List)getItemByFemale.get("女子组");
				String feMaleItem = "";
				int count2 = female.size();
				for(int i = 0; i < female.size(); i++){
					feMaleItem = feMaleItem + "   " + female.get(i)+"   ";
				}
				Paragraph pFemale = new Paragraph("女子组:"+feMaleItem+"(共"+count2+"项)");
				
				Paragraph remarks1 = new Paragraph("三、参加办法");
				Paragraph pRemarks1 = new Paragraph((String)gameInfoMap.get("remarks1"));
				Paragraph remarks2 = new Paragraph("四、竞赛说明");
				Paragraph pRemarks2 = new Paragraph((String)gameInfoMap.get("remarks2"));
				Paragraph remarks3 = new Paragraph("五、计分办法");
				Paragraph pRemarks3 = new Paragraph((String)gameInfoMap.get("remarks3"));
				Paragraph remarks4 = new Paragraph("六、其他");
				Paragraph pRemarks4 = new Paragraph((String)gameInfoMap.get("remarks4"));
				
				
				
				
				Paragraph meetingDiscipline = new Paragraph("大会纪律", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				meetingDiscipline.setAlignment(1);
				
				Paragraph pRemarks5 = new Paragraph((String)gameInfoMap.get("remarks5"));
				
				Paragraph meetingProgram = new Paragraph("大会程序", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				meetingProgram.setAlignment(1);


				Paragraph openingCeremony = new Paragraph("开幕式", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				openingCeremony.setAlignment(1);
				Paragraph pOpeningceremony = new Paragraph((String)gameInfoMap.get("openingceremony"));
				
				Paragraph closeingCeremony = new Paragraph("闭幕式", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				closeingCeremony.setAlignment(1);
				Paragraph pClosingceremony = new Paragraph((String)gameInfoMap.get("closingceremony"));
				
				Paragraph studentNumber = new Paragraph("运动员号码分布", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				studentNumber.setAlignment(1);
				
				 Table studentTable = new Table(2);
			        for(int i = 0; i < studentList.size(); i++){
			        	Cell studentCell = new Cell((String) studentList.get(i));
			        	studentCell.setBorderWidth(0);
			        	studentTable.addCell(studentCell);
			        	
			        }
			        for(int j = 0; j < 2 - studentList.size()%2; j++){        
						Cell c = new Cell(new Paragraph());
						c.setBorderWidth(0);
						studentTable.addCell(c);
					}
				
				Paragraph teacherNumber = new Paragraph("教工运动员号码分布", new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				teacherNumber.setAlignment(1);
				
				 Table teacherTable = new Table(2);
			        for(int i = 0; i < teacherList.size(); i++){
			        	Cell teacherCell = new Cell((String) teacherList.get(i));
			        	teacherCell.setBorderWidth(0);
			        	teacherTable.addCell(teacherCell);
			        	
			        }
			        for(int y = 0; y < 2 - teacherList.size()%2; y++){        
						Cell cl1 = new Cell(new Paragraph());
						cl1.setBorderWidth(0);
						teacherTable.addCell(cl1);
					}
				
				Paragraph gameSchedule = new Paragraph("竞赛日程", new Font(Font.NORMAL, 13,
						Font.BOLD, new Color(0, 0, 0)));
				gameSchedule.setAlignment(1);
				
				  Table GameDateInfoTable = new Table(3);
					List gameDateList = (List)getGameDateInfo.get("dateList");
					if(gameDateList.size() != 0 && gameDateList.get(0) != null){
					List gameDateInfoList = (List)getGameDateInfo.get("gameInfoList");
					for( int i = 0; i < gameDateList.size(); i++){
			        	String gameDate = (String)gameDateList.get(i);
			        	System.out.println(gameDate);
			        	Cell cellDayAMTrack = new Cell(new Paragraph(gameDate+"上午",new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0))));
			        	cellDayAMTrack.setColspan(3);
			        	cellDayAMTrack.setUseAscender(true);
			        	cellDayAMTrack.setVerticalAlignment(cellDayAMTrack.ALIGN_CENTER);
			        	cellDayAMTrack.setBorderWidth(0);
			        	GameDateInfoTable.addCell(cellDayAMTrack);
			        	Cell cellTrack = new Cell(new Paragraph("径赛",new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0))));
			        	cellTrack.setColspan(3);
			        	cellTrack.setBorderWidth(0);
			        	GameDateInfoTable.addCell(cellTrack);
			        	 
			        	 
			        	
			        	for(int j = 0; j < gameDateInfoList.size(); j++){
			        		GameDatePlanPojo dp = (GameDatePlanPojo) gameDateInfoList.get(j);
			        		if(gameDate.equals(((GameDatePlanPojo) gameDateInfoList.get(j)).getFinalDate())==true){
			        		    String time =dp.getTime();
			        		    System.out.println(time);
			        		    if(time != null && !time.equals("")){
			        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
			        			if(dp.getItemType().equals("1") && (time1 >= 0 && time1 <= 12)){
			        				Cell FinalItem = new Cell(dp.getFinalItem());
			        				FinalItem.setBorderWidth(0);
			        				GameDateInfoTable.addCell(FinalItem);
			        				if(dp.getGroupNum() == null || dp.getGroupNum().equals("0")){
			        					int groupNum = 1;
			        					Cell GroupNum = new Cell(groupNum+"组");
			        					GroupNum.setBorderWidth(0);
				        				GameDateInfoTable.addCell(GroupNum);
			        				}else{
			        				Cell GroupNum = new Cell(dp.getGroupNum()+"组");
			        				GroupNum.setBorderWidth(0);
			        				GameDateInfoTable.addCell(GroupNum);
			        				}
			        				
			        				Cell Time  = new Cell(dp.getTime());
			        				Time.setBorderWidth(0);
			        				GameDateInfoTable.addCell(Time);
			        			}	
			        		   }
			        		}
			        	}
			        	
			        	
			        	Cell cellFild = new Cell(new Paragraph("田赛",new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0))));
			        	cellFild.setColspan(3);
			        	cellFild.setBorderWidth(0);
			        	GameDateInfoTable.addCell(cellFild);
			        	for(int j = 0; j < gameDateInfoList.size(); j++){
			        		GameDatePlanPojo dp = (GameDatePlanPojo) gameDateInfoList.get(j);
			        		if(gameDate.equals(((GameDatePlanPojo) gameDateInfoList.get(j)).getFinalDate())==true){
			        		    String time =dp.getTime();
			        		    if(time != null && !time.equals("")){
			        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
			        			if(dp.getItemType().equals("2") && (time1 >= 0 && time1 <= 12)){
			        				Cell FinalItem = new Cell(dp.getFinalItem());
			        				FinalItem.setBorderWidth(0);
			        				GameDateInfoTable.addCell(FinalItem);
			        				Cell GroupNum = new Cell();
			        				GroupNum.setBorderWidth(0);
			        				GameDateInfoTable.addCell(GroupNum);
			        				Cell Time  = new Cell(dp.getTime());
			        				Time.setBorderWidth(0);
			        				GameDateInfoTable.addCell(Time);
			        			}	
			        		    }
			        		}
			        	}
			        	
			        	
			        	
			        	Cell cellDayPMTrack = new Cell(new Paragraph(gameDate+"下午",new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0))));
			        	cellDayPMTrack.setColspan(3);
			        	cellDayPMTrack.setBorderWidth(0);
			        	GameDateInfoTable.addCell(cellDayPMTrack);
			        	Cell cellFild1 = new Cell(new Paragraph("径赛",new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0))));
			        	cellFild1.setColspan(3);
			        	cellFild1.setBorderWidth(0);
			        	GameDateInfoTable.addCell(cellFild1);
			        	for(int j = 0; j < gameDateInfoList.size(); j++){
			        		GameDatePlanPojo dp = (GameDatePlanPojo) gameDateInfoList.get(j);
			        		if(gameDate.equals(((GameDatePlanPojo) gameDateInfoList.get(j)).getFinalDate())==true){
			        		    String time =dp.getTime();
			        		    if(time != null && !time.equals("")){
			        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
			        			if(dp.getItemType().equals("1") && (time1 >= 13 && time1 <= 24)){
			        				Cell FinalItem = new Cell(dp.getFinalItem());
			        				FinalItem.setBorderWidth(0);
			        				GameDateInfoTable.addCell(FinalItem);
//			        				Cell GroupNum = new Cell(dp.getGroupNum()+"组");
//			        				GroupNum.setBorderWidth(0);
//			        				GameDateInfoTable.addCell(GroupNum);
			        				if(dp.getGroupNum() == null || dp.getGroupNum().equals("0")){
			        					int groupNum = 1;
			        					Cell GroupNum = new Cell(groupNum+"组");
			        					GroupNum.setBorderWidth(0);
				        				GameDateInfoTable.addCell(GroupNum);
			        				}else{
			        				Cell GroupNum = new Cell(dp.getGroupNum()+"组");
			        				GroupNum.setBorderWidth(0);
			        				GameDateInfoTable.addCell(GroupNum);
			        				}
			        				Cell Time  = new Cell(dp.getTime());
			        				Time.setBorderWidth(0);
			        				GameDateInfoTable.addCell(Time);
			        			}	
			        		   }
			        		}
			        	}
			        	

			        	

			        	
			        	Cell cellPMFild = new Cell(new Paragraph("田赛",new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0))));
			        	cellPMFild.setColspan(3);
			        	cellPMFild.setBorderWidth(0);
			        	GameDateInfoTable.addCell(cellPMFild);
			        	for(int j = 0; j < gameDateInfoList.size(); j++){
			        		GameDatePlanPojo dp = (GameDatePlanPojo) gameDateInfoList.get(j);
			        		if(gameDate.equals(((GameDatePlanPojo) gameDateInfoList.get(j)).getFinalDate())==true){
			        		    String time =dp.getTime();
			        		    if(time != null && !time.equals("")){
			        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
			        			if(dp.getItemType().equals("2") && (time1 >= 13 && time1 <= 24)){
			        				Cell FinalItem = new Cell(dp.getFinalItem());
			        				FinalItem.setBorderWidth(0);
			        				GameDateInfoTable.addCell(FinalItem);
			        				Cell GroupNum = new Cell();
			        				GroupNum.setBorderWidth(0);
			        				GameDateInfoTable.addCell(GroupNum);
			        				Cell Time  = new Cell(dp.getTime());
			        				Time.setBorderWidth(0);
			        				GameDateInfoTable.addCell(Time);
			        			}	
			        		   }
			        		}
			        	}
						
			        }
					}else{
						GameDateInfoTable.addCell("日期未设置");
					}


					document.add(presidiumInfo);
					document.add(presidiumInfo1);
					document.add(presidiumTable);
					document.add(presidiumInfo2);
					document.add(committeeTable);
					document.add(presidiumInfo3);
					document.add(secretariatTable);
					document.add(presidiumInfo4);
					document.add(arbitrationTable);
					document.add(judge);
					document.add(judgeTable);
					document.add(gameRules);
					document.add(date);
					document.add(address);
					document.add(item);
					document.add(pMale);
					document.add(pFemale);
					document.add(remarks1);
					document.add(pRemarks1);
					document.add(remarks2);
					document.add(pRemarks2);
					document.add(remarks3);
					document.add(pRemarks3);
					document.add(remarks4);
					document.add(pRemarks4);
					document.add(meetingDiscipline);
					document.add(pRemarks5);
					document.add(meetingProgram);
					document.add(openingCeremony);
					document.add(pOpeningceremony);
					document.add(closeingCeremony);
					document.add(pClosingceremony);
					document.add(studentNumber);
					document.add(studentTable);
					document.add(teacherNumber);
					document.add(teacherTable);
					document.add(gameSchedule);
					document.add(GameDateInfoTable);
					document.close();                    //关闭文档
					
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


	}

}
