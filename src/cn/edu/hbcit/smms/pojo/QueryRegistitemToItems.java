package cn.edu.hbcit.smms.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;

/*
 * Copyright(C) 2004, XXXXXXXX.
 *
 * 模块名称：     AAAAAAAAAAA
 * 子模块名称：   BBBBBBBBBBB
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2004/12/12		0.1		张 三		新建
 * 2005/02/05		0.1		李 四		Bug修正
 */
public class QueryRegistitemToItems {
	/**
	 * QueryRegistitemToItems类
	 *
	 *简要说明
	 *获得选择多个项目的运动员的选择项目
	 *详细解释。
	 * @author 袁仕杰
	 * @version 1.00  2012/06/16 新規作成<br>
	 */
	Logger log = Logger.getLogger(QueryRegistitemToItems.class.getName());
	ResultSet rs = null;
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	DBConn db = new DBConn();
	/**根据运动员号码获取训动员所报的项目（分离后的项目）
	 * */
	ArrayList listfinames = new ArrayList();
	public JSONArray getItems( String playernum){
		ArrayList finalitemnames = new ArrayList();
		String finalitemname ="";
		JSONArray jsonarray = new JSONArray();
			String sql = " SELECT DISTINCT t_finalitem.finalitemname"+
			" FROM t_position " +
			" JOIN t_player ON t_position.playerid = t_player.id"+
			" JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id"+ 
			" WHERE t_player.playernum = ?";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				rs = pstmt.executeQuery();
				while(rs.next()){	
					jsonarray.add("<b>"+rs.getString(1)+"</b>");
					finalitemname = rs.getString(1);
					finalitemnames.add(finalitemname);
				}
				//this.getRecordlevel(playernum, finalitemnames);
				rs.close();
				pstmt.close();
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
			this.getFinalitemnames(finalitemnames);
			return jsonarray;
		}
	public ArrayList getFinalitemnames(ArrayList  finalitemnames){
		listfinames = new ArrayList();
		listfinames = finalitemnames;
		return listfinames;
	}
	/**根据运动员号码获取训动员所报的项目的成绩（分离后的项目）
	 * */
	public JSONArray getScores( String playernum){
		JSONArray jsonarray = new JSONArray();
			String sql = " SELECT DISTINCT t_position.score,t_position.finalitemid"+
			" FROM t_position" +
			" JOIN t_player ON t_position.playerid = t_player.id"+
			" WHERE t_player.playernum =?" +
			" ORDER BY t_position.finalitemid IN(SELECT DISTINCT t_finalitem.id" +
			" FROM t_position " +
			" JOIN t_player ON t_position.playerid = t_player.id" +
			" JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id" +
			" WHERE t_player.playernum = ? )";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				pstmt.setString(2, playernum);
				rs = pstmt.executeQuery();
				while(rs.next()){
					jsonarray.add(rs.getString(1));
				}
				rs.close();
				pstmt.close();
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
		return jsonarray;
	}
	/**根据运动员号码获取训动员所报的项目的名次（分离后的项目）
	 * */
	public JSONArray getPosition( String playernum){
		JSONArray jsonarray = new JSONArray();
			String sql = "SELECT DISTINCT t_position.position"+
			" FROM t_position" +
			" JOIN t_player ON t_position.playerid = t_player.id"+
			" WHERE t_player.playernum =?";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				rs = pstmt.executeQuery();
				while(rs.next()){
					jsonarray.add(rs.getString(1));
				}
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
		return jsonarray;
	}
//JSONArray  ---String
	public JSONArray getRecordlevel( String playernum){
		JSONArray jsonarray = new JSONArray();
		this.getItems(playernum);
		int size=listfinames.size(); 
		String[] finalitemname = (String[])listfinames.toArray(new String[size]); 
		for(int i=0;i<finalitemname.length;i++){ 
			String finalitem = finalitemname[i];
			String sql = "SELECT DISTINCT t_match.recordlevel FROM t_match " +
					" JOIN t_finalitem ON t_finalitem.id = t_match.finalitemid" +
					" JOIN t_player ON t_player.id = t_match.playerid" +
					" WHERE t_finalitem.finalitemname=? AND t_player.playernum=?";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, finalitem);
				pstmt.setString(2, playernum);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					String rel = "";
					if(rs.getString(1).equals("0")){
						 rel = "无";
					}else if(rs.getString(1).equals("1")){
						 rel = "院";
					}else if(rs.getString(1).equals("2")){
						 rel = "省";
					}
					jsonarray.add(rel);
				}
				rs.close();
				pstmt.close();
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
		}
		return jsonarray;
	}
	/**
	 * 根据运动员号码和项目查询（未分离的项目名称）查询分离后的项目名称
	 * */
	public JSONArray getItem( String playernum,String item){
		JSONArray jsonarray = new JSONArray();
			String sql = "SELECT DISTINCT t_finalitem.finalitemname" +
					" FROM t_position JOIN t_player ON t_position.playerid = t_player.id" +
					" JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id" +
					" WHERE t_player.playernum = ? AND t_finalitem.finalitemname LIKE \"%\"?\"%\"";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				pstmt.setString(2, item);
				rs = pstmt.executeQuery();
				while(rs.next()){
					jsonarray.add(rs.getString(1));
				}
				rs.close();
				pstmt.close();
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
		return jsonarray;
	}
	/**
	 * 根据运动员号码和项目查询（未分离的项目名称）查询分离后的项目成绩
	 * */
	public JSONArray getScore( String playernum ,String item){
		JSONArray jsonarray = new JSONArray();
			String sql = "SELECT DISTINCT t_position.score" +
					" FROM t_position " +
					" JOIN t_player ON t_position.playerid = t_player.id" +
					" JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id" +
					" WHERE t_player.playernum = ? AND t_finalitem.finalitemname LIKE \"%\"?\"%\"";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				pstmt.setString(2, item);
				rs = pstmt.executeQuery();
				while(rs.next()){
					jsonarray.add(rs.getString(1));
				}
				rs.close();
				pstmt.close();
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
		return jsonarray;
	}
	/**
	 * 根据运动员号码和项目查询（未分离的项目名称）查询分离后的项目名次
	 * */
	public JSONArray getPosition1( String playernum, String item){
		JSONArray jsonarray = new JSONArray();
			String sql = "SELECT DISTINCT t_position.position" +
			" FROM t_position " +
			" JOIN t_player ON t_position.playerid = t_player.id" +
			" JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id" +
			" WHERE t_player.playernum = ? AND t_finalitem.finalitemname LIKE \"%\"?\"%\"";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				pstmt.setString(2, item);
				rs = pstmt.executeQuery();
				while(rs.next()){
					jsonarray.add(rs.getString(1));
				}
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}catch( Exception e){
				e.getStackTrace();
			}
		return jsonarray;
	}
	/**
	 * 根据运动员号码和项目查询（未分离的项目名称）查询分离后的项目名称
	 * */
	public JSONArray getRecordlevel2( String playernum,String item){
		JSONArray jsonarray = new JSONArray();
			String sql = "SELECT DISTINCT recordlevel FROM t_match" +
					" JOIN t_player ON t_player.id = t_match.playerid" +
					" JOIN t_finalitem ON t_finalitem.id = t_match.finalitemid" +
					" WHERE t_player.playernum=? AND t_finalitem.finalitemname=?";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, playernum);
				pstmt.setString(2, item);
				log.debug("aaaaaaaaaaaaaaaaaaaaaaaaaa"+sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					String rel = "";
					if(rs.getString(1).equals("0")){
						 rel = "无";
					}else if(rs.getString(1).equals("1")){
						 rel = "院";
					}else if(rs.getString(1).equals("2")){
						 rel = "省";
					}
					jsonarray.add(rel);
				}
				db.freeConnection(conn);
			}catch( SQLException se){
				se.getStackTrace();
			}return jsonarray;
		}
	}