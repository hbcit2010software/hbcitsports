package cn.edu.hbcit.smms.dao.createprogramdao;
/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     生成秩序册模块
 * 子模块名称：   生成word文档
 *
 * 备注：查询运动员姓名 与号码
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2012/6/19     V1.0   李兆珠         新建
 */
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.PlayerPojo;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
/**
 * 
 * @author 李兆珠
 *
 */
public class WordSelectPlayer {
	LoginDAO ld = new LoginDAO();
	int sportId = ld.selectCurrentSportsId();
	//int sportId = 1;
	public void SelPlaWD(String filePath, String fileName ) {
		
		SelectPlayerDAO sd=new SelectPlayerDAO();
		ArrayList departList = new ArrayList();
		//ArrayList selectSportsid=new ArrayList();
		departList=sd.selectDepartment(sportId);
		//selectSportsid=sd.selectSportsid();
		Document document = new Document(PageSize.A4);
		try {
			RtfWriter2.getInstance(document,
					new FileOutputStream(filePath+fileName));

			document.open();
		
		
		for(int i = 0;i < departList.size();i++)
		{
			
			int id=Integer.parseInt(departList.get(i).toString());
			//int sid = Integer.parseInt(selectSportsid.get(i).toString());
			ArrayList playBoy = new ArrayList();
			ArrayList playGirl = new ArrayList();
			ArrayList playTeacher = new ArrayList();
			int type=sd.selectDepartmentType(id);
			if(type==1){
			//查询学生男子组的姓名和号码
			playBoy=sd.selectPlayersByDept(1, id, sportId);
		    String groupname="男子组";
		    String departname=sd.selectDepartmentName(id) ;
		    Paragraph p = new Paragraph(departname, new Font(Font.BOLD, 18,
					Font.BOLD, new Color(0, 0, 0)));
			p.setAlignment(1);
			document.add(p);
		 // document.add(new Paragraph(groupname));
			 Table table = new Table(8); 
			Cell cc5=new Cell(groupname);
		
			cc5.setColspan(8);
			cc5.setBorderWidth(0);
			table.addCell(cc5);
			
		 
			//指定表格为八列
			
			table.setBorder(0);
			table.setBorderWidth(0);
			table.setBorderColor(Color.WHITE);
			table.setPadding(0);
			table.setSpacing(0);
		   for (int a = 0; a < playBoy.size(); a++){
				PlayerPojo pojp = (PlayerPojo)playBoy.get(a);
		
				Cell cc=new Cell(pojp.getPlayernum());
				cc.setBorderWidth(0);
				table.addCell(cc);
				Cell ce=new Cell(pojp.getPlayername());
				ce.setBorderWidth(0);
				table.addCell(ce);
			}
		  
		  // document.add(p1);
		   document.add(table);
		 //查询学生女子组的姓名和号码
		   Table table1 = new Table(8); 
		 //指定表格为八列
		   table1.setBorder(0);
			table1.setBorderWidth(0);
			table1.setBorderColor(Color.WHITE);
			table1.setPadding(0);
			table1.setSpacing(0);
		  playGirl=sd.selectPlayersByDept(0, id, sportId);
		  String groupname1="女子组";
		 // document.add(new Paragraph(groupname1));
		 
			Cell cc6=new Cell(groupname1);
			cc6.setColspan(8);
			cc6.setBorderWidth(0);
			table1.addCell(cc6);
			
		  for (int a = 0; a < playGirl.size(); a++){
			  PlayerPojo pojp = (PlayerPojo)playGirl.get(a);
				Cell cc1=new Cell(pojp.getPlayernum());
				cc1.setBorderWidth(0);
				table1.addCell(cc1);
				Cell ce1=new Cell(pojp.getPlayername());
				ce1.setBorderWidth(0);
				table1.addCell(ce1);
				}
		  document.add(table1);
		   document.add(new Paragraph());
		   document.add(new Paragraph());
		} else{
			//查询教工组的姓名和号码
			playTeacher=sd.selectPlayersByDept1(id, sportId);

			 String groupname="教工组";
			    String departname=sd.selectDepartmentName(id) ;
			    Paragraph p = new Paragraph(departname, new Font(Font.BOLD, 18,
						Font.BOLD, new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			  // document.add(new Paragraph(groupname));
				 Table table2 = new Table(8); 
					Cell cc6=new Cell(groupname);
					cc6.setColspan(8);
					cc6.setBorderWidth(0);
					table2.addCell(cc6);
					
			    
				//指定表格为八列
				
				table2.setBorder(0);
				table2.setBorderWidth(0);
				table2.setBorderColor(Color.WHITE);
				table2.setPadding(0);
				table2.setSpacing(0);
			   for (int a = 0; a < playTeacher.size(); a++){
				   PlayerPojo pojp = (PlayerPojo)playTeacher.get(a);
			
					Cell cc=new Cell(pojp.getPlayernum());
					cc.setBorderWidth(0);
					table2.addCell(cc);
					Cell ce=new Cell(pojp.getPlayername());
					ce.setBorderWidth(0);
					table2.addCell(ce);
				}
			  
			  // document.add(p1);
			   document.add(table2);
		}
		}
			
		document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       }

}
