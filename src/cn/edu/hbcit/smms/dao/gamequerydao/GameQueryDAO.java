package cn.edu.hbcit.smms.dao.gamequerydao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.QueryPageData;

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
public class GameQueryDAO {
	/**
	 * GameQueryDAO类
	 *前台页面下拉菜单动态数据显示
	 *简要说明
	 *
	 *详细解释。
	 * @author 袁仕杰
	 * @version 1.00  2011/12/07 新規作成<br>
	 */
	private Statement stmt;
	private PreparedStatement pstmt = null;
	private CallableStatement cs = null;
	private Connection conn = null;
	private ResultSet rs = null;
	DBConn db = new DBConn();
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getSportsName(){
		ArrayList list = new ArrayList();
		String sql = "select id,sportsname from t_sports GROUP BY id DESC";
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while( rs.next() ){
					QueryPageData snd = new QueryPageData();
					snd.setId(rs.getInt(1));
					snd.setSportsname(rs.getString(2));
					list.add(snd);
				}
				rs.close();
				pstmt.close();
				db.freeConnection(conn);
			}catch( Exception e){
				e.getStackTrace();
				System.out.println(e.getMessage());
			}
			return list;
		}
	/**
	 * 
	 * @param sportsid
	 * @return ArrayList
	 */
	public ArrayList selectDepartNameInSports(int sportsid){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,departname FROM t_department WHERE id IN(SELECT departid FROM t_sports2department WHERE sportsid=?)";
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				QueryPageData snd = new QueryPageData();
				snd.setDepartid(rs.getInt(1));
				snd.setDepartname(rs.getString(2));
				list.add(snd);
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch( Exception e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}
	/**
	 * 
	 * @param sportsid
	 * @return ArrayList
	 */
	public ArrayList selectGroupBySportsId( int sportsid){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,groupname FROM t_group WHERE id IN( SELECT groupid FROM t_group2sports WHERE sportsid=?)";
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				QueryPageData snd = new QueryPageData();
				snd.setGroupid(rs.getInt(1));
				snd.setGroupname(rs.getString(2));
				list.add(snd);
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch( Exception e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}
	/**
	 * 
	 * @param sportsid
	 * @param itemtype
	 * @return ArrayList
	 * 
	 */
	public ArrayList selectItemNameByType( int sportsid,String itemtype){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,itemname FROM t_item WHERE id" +
				" IN(SELECT itemid FROM t_group2item WHERE gp2spid" +
				" IN(SELECT id FROM t_group2sports WHERE sportsid=?)) AND itemtype=?";
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			pstmt.setString(2, itemtype);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				QueryPageData snd = new QueryPageData();
				snd.setItemid(rs.getInt(1));
				snd.setItemname(rs.getString(2));
				list.add(snd);
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch( Exception e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}
}
