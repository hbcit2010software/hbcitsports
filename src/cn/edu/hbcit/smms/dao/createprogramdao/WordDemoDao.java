/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	生成秩序册
 * 子模块名称：	生成word文档
 *
 * 备注：生成赛前的相关信息
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-12	V1.0	        田小英		新建
*/
package cn.edu.hbcit.smms.dao.createprogramdao;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.smms.pojo.GameDatePlanPojo;
import cn.edu.hbcit.smms.pojo.OfficialPojo;
import cn.edu.hbcit.smms.pojo.SportRecordPojo;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;









public class WordDemoDao {
	public void wordDemo(Map map, Map map1, Map map2, List ls1, List ls2, List list3, List list4, String fileName) {
		OfficialPojo op = new OfficialPojo();
		
		// 创建word文档,并设置纸张的大小
		Document document = new Document(PageSize.A4);
		try {
			RtfWriter2.getInstance(document,
					new FileOutputStream("../"+ fileName +".doc"));

			document.open();

			// 设置合同头

			Paragraph ph = new Paragraph();
			Font f = new Font();

			Paragraph p = new Paragraph((String)map.get(op.getTitle()), new Font(Font.NORMAL, 18,
					Font.BOLDITALIC, new Color(0, 0, 0)));
			p.setAlignment(1);
			document.add(p);
			ph.setFont(f);

			// 设置中文字体
			BaseFont bfFont = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// Font chinaFont = new Font();
			/*
			 * 创建有七列的表格,
			 */
			Table table = new Table(7);   //指定表格为七列
			//document.add(new Paragraph("生成表格"));
			table.setBorderWidth(0);
			table.setBorderColor(Color.WHITE);
			table.setPadding(0);
			table.setSpacing(0);
			table.setBorder(0);
			Cell cell = new Cell((String) map.get(op.getTitle2()));  
			cell.setBorder(0);
			cell.setHeader(true);
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setColspan(7);
			cell.setRowspan(2);
			table.addCell(cell);
			table.endHeaders();
			String[] pr = (String[]) map.get("offical0");
			for(int i = 0; i < pr.length; i++){
				Cell cell0 = new Cell(pr[i]);
				cell0.setBorderWidth(0);
				table.addCell(cell0);
			}
			
			    
			
				Table table1 = new Table(7);
				table1.setBorderWidth(0);
				Cell cell1 = new Cell((String)map.get(op.getTitle3()));
				cell1.setHeader(true);
				cell1.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell1.setBorder(0);
				cell1.setColspan(7);
				cell1.setRowspan(2);
				table1.addCell(cell1);
				table1.endHeaders();
				cell1 = new Cell((String)map.get(op.getComm1()));
				cell1.setBorderWidth(0);
				table1.addCell(cell1);
				String[] c1 = (String[])map.get("offical1");
				for(int i = 0; i < c1.length; i++){
					 cell1 = new Cell(c1[i]);
					cell1.setBorderWidth(0);
					table1.addCell(cell1);
					
				}
				
				for(int j = 0; j < 6 - c1.length; j++){
					cell1 = new Cell(new Paragraph());
					cell1.setBorderWidth(0);
					table1.addCell(cell1);
					
				}
				cell1 = new Cell((String)map.get(op.getComm2()));
				cell1.setBorderWidth(0);
				table1.addCell(cell1);
				String[] c2 = (String[])map.get("offical2");
				for(int i = 0; i < c2.length; i++){
					cell1 = new Cell(c2[i]);
					cell1.setBorderWidth(0);
					
					table1.addCell(cell1);
				}
				for(int j = 0; j < 6 - c2.length; j++){
					cell1 = new Cell(new Paragraph());
					cell1.setBorderWidth(0);
					table1.addCell(cell1);
				}
				cell1 = new Cell((String)map.get(op.getComm3()));
				cell1.setBorderWidth(0);
				table1.addCell(cell1);
				String[] c3 = (String[])map.get("offical3");
				for(int i = 0; i < c3.length; i++){
					for(int j = 1; j < c3.length / 6 + 1; j++)
					if( i / 6 == j && i % 6 == 0){
						cell1 = new Cell(new Paragraph());
						cell1.setBorderWidth(0);
						table1.addCell(cell1);
					}
					cell1 = new Cell(c3[i]);
					cell1.setBorderWidth(0);
					table1.addCell(cell1);
					
				}
				
				
				Table table2 = new Table(7);
				Cell cell2 = new Cell((String)map.get(op.getTitle4()));
				cell2.setHeader(true);
				cell2.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell2.setBorder(0);
				cell2.setColspan(7);
				cell2.setRowspan(2);
				table2.addCell(cell2);
				table2.endHeaders();
				cell2 = new Cell((String)map.get(op.getSecr1()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s1 = (String[])map.get("offical4");
				for(int i = 0; i < s1.length; i++){
					cell2 = new Cell(s1[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				
				for(int j = 0; j < 6 - s1.length; j++){
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				cell2 = new Cell((String)map.get(op.getSecr2()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s2 = (String[])map.get("offical5");
				for(int i = 0; i < s2.length; i++){
					cell2 = new Cell(s2[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				for(int j = 0; j < 6 - s2.length; j++){         //设置一行文字结束之后所余下的单元格
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				cell2 = new Cell((String)map.get(op.getSecr3()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s3 = (String[])map.get("offical6");
				for(int i = 0; i < s3.length; i++){
					for(int j = 1; j < s3.length / 6 + 1; j++)
					if( i / 6 == j && i % 6 == 0){            //设置多行前面所空出的那一个格
						cell2 = new Cell(new Paragraph());
						cell2.setBorderWidth(0);
						table2.addCell(cell2);
					}
					cell2 = new Cell(s3[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				for(int j = 0; j < 6 - s3.length; j++){        
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				cell2 = new Cell((String)map.get(op.getSecr4()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s4 = (String[])map.get("offical7");
				for(int i = 0; i < s4.length; i++){
					for(int j = 1; j < s4.length / 6 + 1; j++)
						if( i / 6 == j && i % 6 == 0){            
							cell2 = new Cell(new Paragraph());
							cell2.setBorderWidth(0);
							table2.addCell(cell2);
						}
					cell2 = new Cell(s4[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				for(int j = 0; j < 6 - s4.length; j++){        
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				cell2 = new Cell((String)map.get(op.getSecr5()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s5 = (String[])map.get("offical8");
				for(int i = 0; i < s5.length; i++){
					for(int j = 1; j < s5.length / 6 + 1; j++)
						if( i / 6 == j && i % 6 == 0){            
							cell2 = new Cell(new Paragraph());
							cell2.setBorderWidth(0);
							table2.addCell(cell2);
						}
					cell2 = new Cell(s5[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				for(int j = 0; j < 6 - s5.length; j++){        
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				cell2 = new Cell((String)map.get(op.getSecr6()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s6 = (String[])map.get("offical9");
				for(int i = 0; i < s6.length; i++){
					for(int j = 1; j < s6.length / 6 + 1; j++)
						if( i / 6 == j && i % 6 == 0){            
							cell2 = new Cell(new Paragraph());
							cell2.setBorderWidth(0);
							table2.addCell(cell2);
						}
					cell2 = new Cell(s6[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				for(int j = 0; j < 6 - s6.length; j++){        
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				cell2 = new Cell((String)map.get(op.getSecr7()));
				cell2.setBorderWidth(0);
				table2.addCell(cell2);
				String[] s7 = (String[])map.get("offical10");
				for(int i = 0; i < s7.length; i++){
					for(int j = 1; j < s7.length / 6 + 1; j++)
						if( i / 6 == j && i % 6 == 0){            
							cell2 = new Cell(new Paragraph());
							cell2.setBorderWidth(0);
							table2.addCell(cell2);
						}
					cell2 = new Cell(s7[i]);
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				for(int j = 0; j < 6 - s7.length; j++){        
					cell2 = new Cell(new Paragraph());
					cell2.setBorderWidth(0);
					table2.addCell(cell2);
				}
				
//				
				
				Table table3 = new Table(7);
				Cell cell3 = new Cell((String)map.get(op.getTitle5()));
				cell3.setHeader(true);
				cell3.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell3.setBorder(0);
				cell3.setColspan(7);
				cell3.setRowspan(2);
				table3.addCell(cell3);
				table3.endHeaders();
				String[] of11 = (String[]) map.get("offical11");
				for(int i = 0; i < of11.length; i++){
					cell3 = new Cell(of11[i]);
					cell3.setBorderWidth(0);
					table3.addCell(cell3);
				}
				for(int y = 0; y < 7 - of11.length; y++){        
					cell3 = new Cell(new Paragraph());
					cell3.setBorderWidth(0);
					table3.addCell(cell3);
				}
				

				Paragraph p1 = new Paragraph((String)map.get(op.getTitle6()), new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				p1.setAlignment(1);
				
				
				
				Table table5 = new Table(7);
				for(int x = 12; x < 29; x++){
					Cell cell5 = new Cell((String) map.get(""+x));
					cell5.setBorderWidth(0);
					table5.addCell(cell5);
					String[] trx = (String[])map.get("offical"+x);
					for( int i = 0; i < trx.length; i++){
						for(int j = 1; j < trx.length / 6 + 1; j++)
							if( i / 6 == j && i % 6 == 0){  
								
								cell5 = new Cell(new Paragraph());
								cell5.setBorderWidth(0);
								table5.addCell(cell5);
							}
						cell5 = new Cell(trx[i]);
						cell5.setBorderWidth(0);
						table5.addCell(cell5);
					}
					for(int j = 0; j < 6 - trx.length%6; j++){        
						cell5 = new Cell(new Paragraph());
						cell5.setBorderWidth(0);
						table5.addCell(cell5);
					}
				}
				
				Table table4 = new Table(8);
				Cell cell4 = new Cell((String)map.get(op.getFildjud()));
				cell4.setColspan(2);
				cell4.setBorderWidth(0);
				table4.addCell(cell4);
				String[] fild = ((String[])map.get("offical29"));
				for( int i = 0; i < fild.length; i++){
					
					for(int j = 1; j < fild.length / 6 + 1; j++)
						if( i / 6 == j && i % 6 == 0){  
							
							cell4 = new Cell(new Paragraph());
							cell4.setBorderWidth(0);
							table4.addCell(cell4);
							
						}
					cell4 = new Cell(fild[i]);
					cell4.setBorderWidth(0);
					table4.addCell(cell4);
				}
				for(int j = 0; j < 6 - fild.length%6; j++){        
					cell4 = new Cell(new Paragraph());
					cell4.setBorderWidth(0);
					table4.addCell(cell4);
				}
				/**
				 * 此地是用来放田赛的裁判长
				 */
				Table table7 = new Table(7);
				for(int i = 0; i <(Integer.parseInt((String)map1.get("length"))); i++){
					for( int j = 1; j < 4; j++){
						if(j==1){
						if(map1.get(""+i+j) != null){
							Cell cel = new Cell(map1.get("iname"+i)+(String)map1.get("裁判长"));
							cel.setBorderWidth(0);
							table7.addCell(cel);
							String[] judge1 = (String[])map1.get(i+"1");
							for( int x = 0; x < judge1.length; x++){
								cel = new Cell(judge1[x]);
								cel.setBorderWidth(0);
								table7.addCell(cel);
							}
							for(int y = 0; y < 6 - judge1.length%6; y++){        
								cel = new Cell(new Paragraph());
								cel.setBorderWidth(0);
								table7.addCell(cel);
							}
						}
						}
						if(j==2){
						if(map1.get(""+i+j) != null){
							Cell cel1 = new Cell((String)map1.get("助理"));
							//cel1.setColspan(2);
							cel1.setBorderWidth(0);
							table7.addCell(cel1);
							String[] judge2 = (String[])map1.get(i+"2");
							for( int x = 0; x < judge2.length; x++){
								cel1 = new Cell(judge2[x]);
								cel1.setBorderWidth(0);
								table7.addCell(cel1);
							}
							for(int y = 0; y < 6 - judge2.length%6; y++){        
								cel1 = new Cell(new Paragraph());
								cel1.setBorderWidth(0);
								table7.addCell(cel1);
							}
						}
						}
						if(j==3){
						if(map1.get(""+i+j) != null){
							Cell cel2 = new Cell((String)map1.get("裁判员"));
							//cel2.setColspan(2);
							cel2.setBorderWidth(0);
							table7.addCell(cel2);
							String[] judge3 = (String[])map1.get(i+"3");
							for( int x = 0; x < judge3.length; x++){
								cel2 = new Cell(judge3[x]);
								cel2.setBorderWidth(0);
								table7.addCell(cel2);
							}
							for(int y = 0; y < 6 - judge3.length%6; y++){        
								cel2 = new Cell(new Paragraph());
								cel2.setBorderWidth(0);
								table7.addCell(cel2);
							}
						}
						}
					}
				}
				
				Table table6 = new Table(7);
				for(int x = 30; x < 36; x++){
					Cell cell6 = new Cell((String) map.get(""+x));
					cell6.setBorderWidth(0);
					table6.addCell(cell6);
					String[] flx = (String[])map.get("offical"+x);
					for( int i = 0; i < flx.length; i++){
						for(int j = 1; j < flx.length / 6 + 1; j++)
							if( i / 6 == j && i % 6 == 0){  
								
								cell6 = new Cell(new Paragraph());
								cell6.setBorderWidth(0);
								table6.addCell(cell6);
							}
						cell6 = new Cell(flx[i]);
						cell6.setBorderWidth(0);
						table6.addCell(cell6);
					}
					for(int j = 0; j < 6 - flx.length%6; j++){        
						cell6 = new Cell(new Paragraph());
						cell6.setBorderWidth(0);
						table6.addCell(cell6);
					}
				}
				
				Paragraph p2 = new Paragraph(op.getTitle7(), new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				p2.setAlignment(1);
				
				Paragraph p3 = new Paragraph(op.getTitle8()+map2.get("date"));
				//System.out.println(map2.get("date"));
				//System.out.println((String)map2.get("date"));
				Paragraph p4 = new Paragraph(op.getTitle10()+map2.get("address"));
				Paragraph p5 = new Paragraph(op.getTitle9());
				List list1 = (List)map2.get("男子组");
				String maleItem = "";
				int count1 = list1.size();
				for(int i = 0; i < list1.size(); i++){
					 maleItem = maleItem + "   " + list1.get(i)+"   ";
				}
				Paragraph p6 = new Paragraph("男子组:"+maleItem+"(共"+count1+"项)");
				
				List list2 = (List)map2.get("女子组");
				String feMaleItem = "";
				int count2 = list2.size();
				for(int i = 0; i < list2.size(); i++){
					feMaleItem = feMaleItem + "   " + list2.get(i)+"   ";
				}
				Paragraph p7 = new Paragraph("女子组:"+feMaleItem+"(共"+count2+"项)");
				Paragraph p8 = new Paragraph(op.getTitle37());
				Paragraph p9 = new Paragraph((String)map.get("offical36"));
				Paragraph p10 = new Paragraph(op.getTitle38());
				Paragraph p11 = new Paragraph((String)map.get("offical37"));
				Paragraph p12 = new Paragraph(op.getTitle39());
				Paragraph p13 = new Paragraph((String)map.get("offical38"));
				Paragraph p14 = new Paragraph(op.getTitle40());
				Paragraph p15 = new Paragraph((String)map.get("offical39"));
				Paragraph p16 = new Paragraph(op.getTitle41(),new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				p16.setAlignment(1);
				Paragraph p17 = new Paragraph((String)map.get("offical41"));
				Paragraph p18 = new Paragraph(op.getTitle15(),new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				p18.setAlignment(1);
				Paragraph p19 = new Paragraph(op.getTitle42(),new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
				p19.setAlignment(1);
				Paragraph p20 = new Paragraph((String)map.get("offical41"));
				Paragraph p21 = new Paragraph(op.getTitle43(),new Font(Font.NORMAL, 18,
								Font.BOLD, new Color(0, 0, 0)));
				p21.setAlignment(1);
				Paragraph p22 = new Paragraph((String)map.get("offical42"));
				
				Paragraph p23 = new Paragraph(op.getTitle18(),new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
		        p23.setAlignment(1);
		        
		        Table table8 = new Table(2);
		        for(int i = 0; i < ls1.size(); i++){
		        	Cell ce = new Cell((String) ls1.get(i));
		        	ce.setBorderWidth(0);
		        	table8.addCell(ce);
		        	
		        }
		        for(int y = 0; y < 2 - ls1.size()%2; y++){        
					Cell c = new Cell(new Paragraph());
					c.setBorderWidth(0);
					table8.addCell(c);
				}
		        Paragraph p24 = new Paragraph(op.getTitle19(),new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
		        p24.setAlignment(1);
		        Table table9 = new Table(2);
		        //System.out.println((String)ls2.get(0));
		        for(int i = 0; i < ls2.size(); i++){
		        	Cell ce = new Cell((String) ls2.get(i));
		        	ce.setBorderWidth(0);
		        	table9.addCell(ce);
		        	
		        }
		        for(int y = 0; y < 2 - ls2.size()%2; y++){        
					Cell cl1 = new Cell(new Paragraph());
					cl1.setBorderWidth(0);
					table9.addCell(cl1);
				}
		        
		        Paragraph p25 = new Paragraph(op.getTitle20(),new Font(Font.NORMAL, 18,
						Font.BOLD, new Color(0, 0, 0)));
		        p25.setAlignment(1);
		        
		        
		       
		        Table tablej = new Table(3);
				
				for( int i = 0; i < list3.size(); i++){
		        	String date = (String)list3.get(i);
		        	
		        	Cell cellDayAMTrack = new Cell(new Paragraph(date+"上午",new Font(Font.NORMAL, 18,
							Font.BOLD, new Color(0, 0, 0))));
		        	cellDayAMTrack.setColspan(3);
		        	cellDayAMTrack.setUseAscender(true);
		        	cellDayAMTrack.setVerticalAlignment(cellDayAMTrack.ALIGN_CENTER);
		        	cellDayAMTrack.setBorderWidth(0);
		        	tablej.addCell(cellDayAMTrack);
		        	Cell cellTrack = new Cell(new Paragraph("径赛",new Font(Font.NORMAL, 18,
							Font.BOLD, new Color(0, 0, 0))));
		        	cellTrack.setColspan(3);
		        	cellTrack.setBorderWidth(0);
		        	tablej.addCell(cellTrack);
		        	 
		        	 
		        	
		        	for(int j = 0; j < list4.size(); j++){
		        		GameDatePlanPojo dp = (GameDatePlanPojo) list4.get(j);
		        		if(date.equals(((GameDatePlanPojo) list4.get(j)).getFinalDate())==true){
		        		    String time =dp.getTime();
		        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
		        			if(dp.getItemType().equals("1") && (time1 >= 8 && time1 <= 12)){
		        				Cell FinalItem = new Cell(dp.getFinalItem());
		        				FinalItem.setBorderWidth(0);
		        				tablej.addCell(FinalItem);
		        				Cell GroupNum = new Cell(dp.getGroupNum());
		        				GroupNum.setBorderWidth(0);
		        				tablej.addCell(GroupNum);
		        				Cell Time  = new Cell(dp.getTime());
		        				Time.setBorderWidth(0);
		        				tablej.addCell(Time);
		        			}	
		        		}
		        	}
		        	
		        	
		        	Cell cellFild = new Cell(new Paragraph("田赛",new Font(Font.NORMAL, 18,
							Font.BOLD, new Color(0, 0, 0))));
		        	cellFild.setColspan(3);
		        	cellFild.setBorderWidth(0);
		        	tablej.addCell(cellFild);
		        	for(int j = 0; j < list4.size(); j++){
		        		GameDatePlanPojo dp = (GameDatePlanPojo) list4.get(j);
		        		if(date.equals(((GameDatePlanPojo) list4.get(j)).getFinalDate())==true){
		        		    String time =dp.getTime();
		        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
		        			if(dp.getItemType().equals("2") && (time1 >= 8 && time1 <= 12)){
		        				Cell FinalItem = new Cell(dp.getFinalItem());
		        				FinalItem.setBorderWidth(0);
		        				tablej.addCell(FinalItem);
		        				Cell GroupNum = new Cell(dp.getGroupNum());
		        				GroupNum.setBorderWidth(0);
		        				tablej.addCell(GroupNum);
		        				Cell Time  = new Cell(dp.getTime());
		        				Time.setBorderWidth(0);
		        				tablej.addCell(Time);
		        			}	
		        		}
		        	}
		        	
		        	
		        	
		        	Cell cellDayPMTrack = new Cell(new Paragraph(date+"下午",new Font(Font.NORMAL, 18,
							Font.BOLD, new Color(0, 0, 0))));
		        	cellDayPMTrack.setColspan(3);
		        	cellDayPMTrack.setBorderWidth(0);
		        	tablej.addCell(cellDayPMTrack);
		        	Cell cellFild1 = new Cell(new Paragraph("径赛",new Font(Font.NORMAL, 18,
							Font.BOLD, new Color(0, 0, 0))));
		        	cellFild1.setColspan(3);
		        	cellFild1.setBorderWidth(0);
		        	tablej.addCell(cellFild1);
		        	for(int j = 0; j < list4.size(); j++){
		        		GameDatePlanPojo dp = (GameDatePlanPojo) list4.get(j);
		        		if(date.equals(((GameDatePlanPojo) list4.get(j)).getFinalDate())==true){
		        		    String time =dp.getTime();
		        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
		        			if(dp.getItemType().equals("1") && (time1 >= 2 && time1 <= 7)){
		        				Cell FinalItem = new Cell(dp.getFinalItem());
		        				FinalItem.setBorderWidth(0);
		        				tablej.addCell(FinalItem);
		        				Cell GroupNum = new Cell(dp.getGroupNum());
		        				GroupNum.setBorderWidth(0);
		        				tablej.addCell(GroupNum);
		        				Cell Time  = new Cell(dp.getTime());
		        				Time.setBorderWidth(0);
		        				tablej.addCell(Time);
		        			}	
		        		}
		        	}
		        	

		        	

		        	
		        	Cell cellPMFild = new Cell(new Paragraph("田赛",new Font(Font.NORMAL, 18,
							Font.BOLD, new Color(0, 0, 0))));
		        	cellPMFild.setColspan(3);
		        	cellPMFild.setBorderWidth(0);
		        	tablej.addCell(cellPMFild);
		        	for(int j = 0; j < list4.size(); j++){
		        		GameDatePlanPojo dp = (GameDatePlanPojo) list4.get(j);
		        		if(date.equals(((GameDatePlanPojo) list4.get(j)).getFinalDate())==true){
		        		    String time =dp.getTime();
		        			int time1 =Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
		        			if(dp.getItemType().equals("2") && (time1 >= 2 && time1 <= 7)){
		        				Cell FinalItem = new Cell(dp.getFinalItem());
		        				FinalItem.setBorderWidth(0);
		        				tablej.addCell(FinalItem);
		        				Cell GroupNum = new Cell(dp.getGroupNum());
		        				GroupNum.setBorderWidth(0);
		        				tablej.addCell(GroupNum);
		        				Cell Time  = new Cell(dp.getTime());
		        				Time.setBorderWidth(0);
		        				tablej.addCell(Time);
		        			}	
		        		}
		        	}
					
		        }
				
				
				
				
				
				
				
				
	
			document.add(table);
			document.add(table1);
			document.add(table2);
			document.add(table3);
			document.add(p1);
			document.add(table5);
			document.add(table4);
			document.add(table7);
			document.add(table6);
			document.add(p2);
			document.add(p3);
			document.add(p4);
			document.add(p5);
			document.add(p6);
			document.add(p7);
			document.add(p8);
			document.add(p9);
			document.add(p10);
			document.add(p11);
			document.add(p12);
			document.add(p13);
			document.add(p14);
			document.add(p15);
			document.add(p16);
			document.add(p17);
			document.add(p18);
			document.add(p19);
			document.add(p20);
			document.add(p21);
			document.add(p22);
			document.add(p23);
			document.add(table8);
			document.add(p24);
			document.add(table9);
			document.add(p25);
			
			
			document.add(tablej);
			
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
