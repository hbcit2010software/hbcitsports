package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import net.sf.json.JSONArray;



import cn.edu.hbcit.smms.dao.databasedao.DBConn;


public class QueryMark {
	DBConn dbc = new DBConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	/**
	 *通过部门查积分
	* @param 参数名 参数类型
	* @return
	 */
	public int queryByGroup( int departid , int sportsid ,int grouptype ){
		String sql = null;
		int sum = 0;
		conn = dbc.getConn();
		
		try {
			if( grouptype == 1 || grouptype == 0 ){
				sql = "SELECT score FROM t_position WHERE finalitemid IN (" +
				"SELECT id FROM t_finalitem WHERE gp2itid IN(" +
				"SELECT id FROM t_group2item WHERE gp2spid IN (" +
				"SELECT id FROM t_group2sports WHERE groupid IN (" +
				"SELECT id FROM t_group WHERE grouptype = ?) AND sportsid = ?)))" +
				" AND playerid IN (SELECT id FROM t_player WHERE sp2dpid IN (" +
				"SELECT id FROM t_sports2department WHERE departid = ?))";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, grouptype);
				pstmt.setInt(2, sportsid);
				pstmt.setInt(3, departid);
				rs = pstmt.executeQuery();
				while( rs.next() ){
					sum += Integer.parseInt(rs.getString(1));
				}
			}else{
				sql = "SELECT sum FROM t_mark WHERE sp2dpid = (" +
				"SELECT id FROM t_sports2department WHERE departid = ? AND sportsid = ?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, departid);
				pstmt.setInt(2, sportsid);
				rs = pstmt.executeQuery();
				while( rs.next() ){
					sum += Integer.parseInt(rs.getString(1));
				}
			}
			
			 dbc.freeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	
	public JSONArray getMarkDocMessage( int sportsid ){
		String sql = "SELECT SUM(score+0),grouptype,departname FROM t_position JOIN t_player ON t_player.id = t_position.playerid" +
				"JOIN t_sports2department ON t_sports2department.id =  t_player.sp2dpid" +
				"JOIN t_department ON t_department.id = t_sports2department.departid" +
				"JOIN t_group ON t_group.id = t_player.groupidWHERE sportsid = ? GROUP BY grouptype,departname";
		conn = dbc.getConn();
		JSONArray alllist = new JSONArray();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				JSONArray list = new JSONArray();
				list.add(rs.getString(3));
				list.add(rs.getBoolean(2)+"");
				list.add(Integer.toString(rs.getInt(1)));
				alllist.add(list);
			}
			 dbc.freeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alllist;
	}
	public void createMarkDoc( int sportsid ,String file ) throws DocumentException, 
		IOException{
		QueryMark qm = new QueryMark();
		String sportsname = "";
		sportsname = qm.getSportsName(sportsid);
		//String file = "";	//路径
		// 设置纸张大小 
		Document document = new Document(PageSize.A4); 
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中 
		RtfWriter2.getInstance(document, new FileOutputStream(file)); 
		document.open(); 
		// 设置中文字体 
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", 
		"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
		// 标题字体风格 
		Font titleFont = new Font(bfChinese, 12, Font.BOLD); 
		// 正文字体风格 
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL); 
		Paragraph title = new Paragraph(sportsname+"部门积分表"); 
		// 设置标题格式对齐方式 
		title.setAlignment(Element.ALIGN_CENTER); 
		title.setFont(titleFont); 
		document.add(title); 
		
		Table aTable = new Table(4); 
		int width[] = {25,25,25,25}; 
		aTable.setWidths(width);//设置每列所占比例 
		aTable.setWidth(90); // 占页面宽度 90% 

		aTable.setAlignment(Element.ALIGN_CENTER);//居中显示 
		aTable.setAlignment(Element.ALIGN_MIDDLE);//纵向居中显示 
		aTable.setAutoFillEmptyCells(true); //自动填满 
		aTable.setBorderWidth(1); //边框宽度 
		aTable.setBorderColor(new Color(0, 125, 255)); //边框颜色 
		aTable.setPadding(2);//衬距，看效果就知道什么意思了 
		aTable.setSpacing(3);//即单元格之间的间距 
		aTable.setBorder(2);//边框 
		
		aTable.addCell(new Cell("单位")); 
		aTable.addCell(new Cell("组别")); 
		aTable.addCell(new Cell("积分")); 
		aTable.addCell(new Cell("总积分")); 
		
		Cell depart = new Cell("");
		depart.setRowspan(2); 
		aTable.addCell(depart); 


		
	}
	
	public String getSportsName( int sportsid ){
		String sql = "SELECT sportsname FROM t_sports WHERE id =  ? ";
		conn = dbc.getConn();
		String str = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				str = rs.getString(1);
			}
			dbc.freeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 *通过作别查积分
	* @param 参数名 参数类型
	* @return
	 */
	public int queryBItem( int grouptype, int itemid ){
		String sql = "SELECT score FROM t_position WHERE playerid = (" +
				"SELECT id FROM t_player WHERE groupid = (" +
				"SELECT id FROM t_group WHERE grouptype = ?)) AND finalitemid = (" +
				"	SELECT id FROM t_finalitem WHERE gp2itid = (" +
				"SELECT id FROM t_group2item WHERE itemid = ? ))";
		int sum = 0;
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grouptype);
			pstmt.setInt(2, itemid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				sum += Integer.parseInt(rs.getString(1));
			}
			 dbc.freeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
}
