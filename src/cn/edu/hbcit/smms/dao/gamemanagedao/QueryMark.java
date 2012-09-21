package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumn;


import org.apache.log4j.Logger;

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


import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.QueryMarkPoJo;


public class QueryMark {
	
	  protected final Logger log = Logger.getLogger(QueryMark.class.getName());
		
	  DBConn dbc = new DBConn();
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  Connection conn = null;
	  
	  /**
	   * 获取参赛部门
	   * @return
	   */
	  
	  public List<QueryMarkPoJo> getDepName(){
		  LoginDAO ld = new LoginDAO();
		  int sportsid = ld.selectCurrentSportsId();
		  List<QueryMarkPoJo> depNameList = new ArrayList<QueryMarkPoJo>();
		  try{
			  conn = dbc.getConn();
			  String sql = "SELECT departname FROM t_department WHERE id IN (" +
			  		"SELECT departid FROM t_sports2department WHERE sportsid=?)";
			  
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, sportsid);
			  rs = pstmt.executeQuery();
			  while(rs.next()){
				  QueryMarkPoJo qm = new QueryMarkPoJo();
				  qm.setDepName(rs.getString(1));
				  depNameList.add(qm);
				  System.out.println("dep========="+depNameList.size());
			  }			  
		  }catch (Exception e) {
			log.debug(e);
		}
		  return depNameList;
	  }
	  
	  /**
	   * 获取学生总积分
	   * @param depNameList
	   * @return
	   */
	  public List<QueryMarkPoJo> getStudentsMark(List<QueryMarkPoJo> depNameList){
		  List<QueryMarkPoJo>  studentsMarkList = new ArrayList<QueryMarkPoJo>();
		  try{
			  conn = dbc.getConn(); 
			  String sql = "SELECT SUM(marks) FROM t_position " +
			  		"JOIN t_player ON t_position.playerid=t_player.id WHERE" +
			  		" (t_player.sp2dpid=(SELECT t_sports2department.id FROM t_sports2department WHERE t_sports2department.departid=" +
			  		"(SELECT t_department.id FROM t_department WHERE t_department.departname=?)) " +
			  		"AND t_player.playertype='1')";
			  pstmt = conn.prepareStatement(sql);
			  
	      for(QueryMarkPoJo queryMarkPoJo:depNameList){
			  pstmt.setString(1, queryMarkPoJo.getDepName());
			  rs = pstmt.executeQuery();
			  while(rs.next()){
				  QueryMarkPoJo qm = new QueryMarkPoJo();
				 
				       qm.setStudetsMarks(rs.getInt(1));
				 
				       studentsMarkList.add(qm);
				  System.out.println("getStudetsMarks========"+qm.getStudetsMarks());
			  }}
		  }catch (Exception e) {
			e.printStackTrace();
		}
		  return studentsMarkList;
	  }
	  
	  /**
	   * 获取教工总积分
	   * @param depNameList
	   * @return
	   */
	  public List<QueryMarkPoJo> getTeacherMark(List<QueryMarkPoJo> depNameList){
		  List<QueryMarkPoJo>  teacherMarkList = new ArrayList<QueryMarkPoJo>();
		  try{
			  conn = dbc.getConn(); 
			  String sql = "SELECT SUM(marks) FROM t_position " +
			  		"JOIN t_player ON t_position.playerid=t_player.id WHERE" +
			  		" (t_player.sp2dpid=(SELECT t_sports2department.id FROM t_sports2department WHERE t_sports2department.departid=" +
			  		"(SELECT t_department.id FROM t_department WHERE t_department.departname=?)) " +
			  		"AND t_player.playertype='0')";
			  pstmt = conn.prepareStatement(sql);
			  
	      for(QueryMarkPoJo queryMarkPoJo:depNameList){
			  pstmt.setString(1, queryMarkPoJo.getDepName());
			  rs = pstmt.executeQuery();
			  while(rs.next()){
				  QueryMarkPoJo qm = new QueryMarkPoJo();
				 
				       qm.setTeacherMarks(rs.getInt(1));
				 
				       teacherMarkList.add(qm);
				  System.out.println("markList========"+qm.getStudetsMarks());
			  }}
		  }catch (Exception e) {
			e.printStackTrace();
		}
		  return teacherMarkList;
	  }
	  
	  /**
	   * 获取学生最终总积分
	   * @param depNameList
	   * @return
	   */
	  public List<QueryMarkPoJo> getStudentsFinalMark(List<QueryMarkPoJo> depNameList){
		  List<QueryMarkPoJo> studentsMarkFinalList = new ArrayList<QueryMarkPoJo>();
		  try{
			  conn = dbc.getConn(); 
			  String sql = "SELECT SUM(stusum) FROM t_mark "+
			   "WHERE sp2dpid=(SELECT t_sports2department.id FROM t_sports2department WHERE t_sports2department.departid=" +
			   "(SELECT id FROM t_department WHERE t_department.departname=?))";
			  pstmt = conn.prepareStatement(sql);
			  System.out.println("==============++++"+depNameList.size());
			for(QueryMarkPoJo queryMarkPoJo:depNameList){
			  pstmt.setString(1, queryMarkPoJo.getDepName());
			  rs = pstmt.executeQuery();
			  while(rs.next()){
				  QueryMarkPoJo qm = new QueryMarkPoJo();
				  
				       qm.setFinalStudentsSum(rs.getInt(1));
				       
				  studentsMarkFinalList.add(qm);
				  System.out.println("markFinalList========="+qm.getFinalStudentsSum());
			  }}
		  }catch (Exception e) {
			e.printStackTrace();
		}
		  return studentsMarkFinalList;
	  }
	  

	  /**
	   * 获取教工最终总积分
	   * @param depNameList
	   * @return
	   */
	  public List<QueryMarkPoJo> getTeacherFinalMark(List<QueryMarkPoJo> depNameList){
		  List<QueryMarkPoJo> teacherMarkFinalList = new ArrayList<QueryMarkPoJo>();
		  try{
			  conn = dbc.getConn(); 
			  String sql = "SELECT SUM(teasum) FROM t_mark "+
			   "WHERE sp2dpid=(SELECT t_sports2department.id FROM t_sports2department WHERE t_sports2department.departid=" +
			   "(SELECT id FROM t_department WHERE t_department.departname=?))";
			  pstmt = conn.prepareStatement(sql);
			  System.out.println("==============++++"+depNameList.size());
			for(QueryMarkPoJo queryMarkPoJo:depNameList){
			  pstmt.setString(1, queryMarkPoJo.getDepName());
			  rs = pstmt.executeQuery();
			  while(rs.next()){
				  QueryMarkPoJo qm = new QueryMarkPoJo();
				  
				       qm.setFinalTeacherSum(rs.getInt(1));
				       
				       teacherMarkFinalList.add(qm);
				  System.out.println("markFinalList========="+qm.getTeacherMarks());
			  }}
		  }catch (Exception e) {
			e.printStackTrace();
		}
		  return teacherMarkFinalList;
	  }
	  
	  /**
	   * 修改积分
	   */
	  public boolean updateMark(String depName,int finalsm,int finaltm){
		  
		  boolean flag = false;
		  LoginDAO ld = new LoginDAO();
		  int sportsid = ld.selectCurrentSportsId();
		  try{
			  
			  conn = dbc.getConn();
			  String sql = "UPDATE t_mark SET stusum=?,teasum=? WHERE sp2dpid=" +
			  		"(SELECT t_sports2department.id FROM t_sports2department WHERE " +
			  		"(t_sports2department.sportsid=? AND t_sports2department.departid=" +
			  		"(SELECT t_department.id FROM t_department WHERE t_department.departname=?)))";
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1,finalsm);
			  pstmt.setInt(2,finaltm);
			  pstmt.setInt(3,sportsid);
			  pstmt.setString(4,depName);
			  int i = pstmt.executeUpdate();
			  if(i>0){
				  flag = true;
				  
			  }
		  }catch (Exception e) {
			log.debug(e);
		}
		  System.out.println("flag========="+flag);
		  return flag;
	  }
	  
	public void createMarksDocContext(String file,List<QueryMarkPoJo> depNameList,List<QueryMarkPoJo> studentsMarkList,List<QueryMarkPoJo> teacherMarkList,List<QueryMarkPoJo> studentsFinalMarkList,List<QueryMarkPoJo> teacherFinalMarkList)throws DocumentException, IOException{   

		  LoginDAO ld = new LoginDAO();
		  int sportsid = ld.selectCurrentSportsId();
		  System.out.println(sportsid);
		  String sportsname = "";
          try{
			  
			  conn = dbc.getConn();
			  pstmt = conn.prepareStatement("select sportsname from t_sports where id=?");
			  pstmt.setInt(1, sportsid);
			  rs = pstmt.executeQuery();
			  System.out.println(rs.next());
			  if(rs.next()){
				  sportsname = rs.getString(1);
				  System.out.println(rs.getString(1));
				}
				
		        Document document = new Document(PageSize.A4);   
		        //建立一个书写器，与document对象关联   
		        RtfWriter2.getInstance(document, new FileOutputStream(file +sportsname+ "部门积分表.doc"));   
		        document.open();   
		        //设置中文字体   
		        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);   
		        //标题字体风格   
		        Font titleFont = new Font(bfChinese,16,Font.BOLD);   
		        //正文字体风格   
		        Font contextFont = new Font(bfChinese,10,Font.NORMAL);   
		        Paragraph title = new Paragraph(sportsname+"部门积分表");   
		        //设置标题格式对齐方式   
		        title.setAlignment(Element.ALIGN_CENTER);   
		        title.setFont(titleFont);   
		        document.add(title);
		        
		      //设置Table表格,创建一个5列的表格   
		        Table table = new Table(5);   
		        int width[] = {20,20,20,20,20};//设置每列宽度比例   
		        table.setWidths(width);   
		        table.setWidth(90);//占页面宽度比例   
		        table.setAlignment(Element.ALIGN_CENTER);//居中   
		        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
		        table.setAutoFillEmptyCells(true);//自动填满   
		        table.setBorderWidth(1);//边框宽度   
		        Cell cell = new Cell();
		        cell.setVerticalAlignment(Element.ALIGN_CENTER);   
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);   
		        table.addCell(new Cell("部门"));
		        table.addCell(new Cell("学生实得积分"));
		        table.addCell(new Cell("教师实得积分"));
		        table.addCell(new Cell("学生最终积分"));
		        table.addCell(new Cell("教师最终积分"));
		        for(int i = 0;i<depNameList.size();i++)
		        {
		        	QueryMarkPoJo qm = (QueryMarkPoJo)depNameList.get(i);
		        	QueryMarkPoJo qm1 = (QueryMarkPoJo)studentsMarkList.get(i);
		        	QueryMarkPoJo qm2 = (QueryMarkPoJo)teacherMarkList.get(i);
		        	QueryMarkPoJo qm3 = (QueryMarkPoJo)studentsFinalMarkList.get(i);
		        	QueryMarkPoJo qm4 = (QueryMarkPoJo)teacherFinalMarkList.get(i);
		            table.addCell(new Cell(qm.getDepName()+""));
		            table.addCell(new Cell(qm1.getStudetsMarks()+""));
		            table.addCell(new Cell(qm2.getTeacherMarks()+""));
		            table.addCell(new Cell(qm3.getFinalStudentsSum()+""));
		            table.addCell(new Cell(qm4.getFinalTeacherSum()+""));
		        }
		        
		        document.add(table);

		        document.close();
		        
          }catch (Exception e) {
			log.debug(e);
		}
				
			}
				
}
	
