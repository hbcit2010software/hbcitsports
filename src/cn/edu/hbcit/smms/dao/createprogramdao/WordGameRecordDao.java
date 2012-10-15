package cn.edu.hbcit.smms.dao.createprogramdao;
/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	生成秩序册
 * 子模块名称：	生成word文档
 *
 * 备注：生成运动员的破记录和学生裁判的信息
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-24	V1.0	        田小英		新建
*/
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import cn.edu.hbcit.smms.pojo.Record;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

public class WordGameRecordDao {
	public void wordGameRecord(String filePath, ArrayList lastRecord_man, ArrayList lastRecord_woman, String fileName, Map studentJudge){
		Document document = new Document(PageSize.A4);
		try {
			RtfWriter2.getInstance(document,
					new FileOutputStream(filePath+fileName));

			document.open();
		
		BaseFont bfFont = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		
		Table tRecord = new Table(7);
		 String sex = null;
		 sex = "女子";
		 Cell feMale = new  Cell(new Paragraph("河北工院田径运动会记录("+sex+")组",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			feMale.setBorderWidth(0);
			feMale.setColspan(7);
			tRecord.addCell(feMale);
			
			feMale = new  Cell(new Paragraph("项目",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
			
			feMale = new  Cell(new Paragraph("成绩",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
			
			feMale = new  Cell(new Paragraph("运动员",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
			feMale = new  Cell(new Paragraph("系别",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
			feMale = new  Cell(new Paragraph("运动会",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
			feMale = new  Cell(new Paragraph("时间",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
			feMale = new  Cell(new Paragraph("备注",new Font(Font.NORMAL, 18,
					Font.BOLD, new Color(0, 0, 0))));
			tRecord.addCell(feMale);
		for( int i = 0; i < lastRecord_woman.size(); i++){
			Record record =(Record) lastRecord_woman.get(i);
				
				
				feMale = new Cell(record.getItemname());
				tRecord.addCell(feMale);
				
				feMale = new Cell(record.getScore());
				tRecord.addCell(feMale);
				
				feMale = new Cell(record.getPlayername());
				tRecord.addCell(feMale);
				
				feMale = new Cell(record.getDepartname());
				tRecord.addCell(feMale);
				
				feMale = new Cell(record.getSportsname());
				tRecord.addCell(feMale);
				
				feMale = new Cell(record.getRecordtime());
				tRecord.addCell(feMale);
				
				feMale = new Cell(new Paragraph());
				tRecord.addCell(feMale);
				
				
			}
		
		
		sex = "男子";
		Cell male = new  Cell(new Paragraph("河北工院田径运动会记录("+sex+")组",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		male.setBorderWidth(0);
		male.setColspan(7);
		tRecord.addCell(male);
		
		male = new  Cell(new Paragraph("项目",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		
		male = new  Cell(new Paragraph("成绩",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		
		male = new  Cell(new Paragraph("运动员",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		male = new  Cell(new Paragraph("系别",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		male = new  Cell(new Paragraph("运动会",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		male = new  Cell(new Paragraph("时间",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		male = new  Cell(new Paragraph("备注",new Font(Font.NORMAL, 18,
				Font.BOLD, new Color(0, 0, 0))));
		tRecord.addCell(male);
		for( int i = 0; i < lastRecord_man.size(); i++){
			Record record =(Record) lastRecord_man.get(i);
				
				
				male = new Cell(record.getItemname());
				tRecord.addCell(male);
				
				male = new Cell(record.getScore());
				tRecord.addCell(male);
				
				male = new Cell(record.getPlayername());
				tRecord.addCell(male);
				
				male = new Cell(record.getDepartname());
				tRecord.addCell(male);
				
				male = new Cell(record.getSportsname());
				tRecord.addCell(male);
				
				male = new Cell(record.getRecordtime());
				tRecord.addCell(male);
				
				male = new Cell(new Paragraph());
				tRecord.addCell(male);
			}
			
			
		
		
		/**
		 * 各系的学生裁判员
		 */

		Paragraph s = new Paragraph("各系学生裁判员名单", new Font(Font.NORMAL, 18,
				Font.BOLDITALIC, new Color(0, 0, 0)));
		s.setAlignment(1);
		Table sJudge = new Table(6);
		int count = Integer.parseInt(studentJudge.get("count").toString());
		//System.out.println(count);
		for( int i = 0; i < count; i++ ){
			String judgeTitle = (String) studentJudge.get("department"+i)+"("+
			studentJudge.get("contact"+i)+":     "+studentJudge.get("tel"+i)+")";
			//System.out.println(judgeTitle);
			Cell judge = new Cell(new Paragraph(judgeTitle, new Font(Font.NORMAL, 13,
					Font.BOLDITALIC, new Color(0, 0, 0))));
			judge.setColspan(6);
			judge.setBorderWidth(0);
			sJudge.addCell(judge);
			String[] judgei = (String[]) studentJudge.get(i+"1");
			for( int j = 0; j < judgei.length; j++){
				Cell judgeMember = new Cell(judgei[j]);
				judgeMember.setBorderWidth(0);
				sJudge.addCell(judgeMember);
			}
			for(int j = 0; j < 6 - judgei.length%6; j++){        
				judge = new Cell(new Paragraph());
				judge.setBorderWidth(0);
				sJudge.addCell(judge);
			}
		}
		
		
		
		document.add(tRecord);
		document.add(s);
		document.add(sJudge);
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
