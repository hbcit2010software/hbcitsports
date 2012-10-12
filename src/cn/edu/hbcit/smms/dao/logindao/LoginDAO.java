/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	登录管理模块
 * 子模块名称：	登录
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-8		V1.0		李玮 		新建
*/
package cn.edu.hbcit.smms.dao.logindao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import cn.edu.hbcit.smms.dao.databasedao.DBConn;

/**
 * 登录类
 *
 * 本类的简要描述：
 * 负责登录验证
 *
 * @author 李玮
 * @version 1.00  2012-6-8 新建类
 */

public class LoginDAO {
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(LoginDAO.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean canLogin(String username, String password){
		boolean flag = false;
		int tempInt = 0;
		String sql = "SELECT COUNT(*) FROM t_sysadmin WHERE username=? AND password=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			rs = pStatement.executeQuery();
			while(rs.next()){
				tempInt = rs.getInt(1);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("登录验证失败，可能是数据库操作错误！");
			log.error(e.getMessage());
		}
		if(tempInt > 0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取用户权限
	 * @param username
	 * @return
	 */
	public int selectUserRights(String username){
		int rst = 0;
		String sql = "SELECT userright FROM t_sysadmin WHERE username=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			while(rs.next()){
				rst = rs.getInt(1);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取用户权限失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 获取当前运动会ID
	 * @return
	 */
	public int selectCurrentSportsId(){
		int rst = 0;
		String sql = "SELECT id FROM t_sports WHERE current=1";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				rst = rs.getInt(1);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取运动会ID失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 获取当前运动会名称
	 * @return
	 */
	public String selectCurrentSportsName(){
		String name = "";
		String sql = "SELECT sportsname FROM t_sports WHERE current=1";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				name = rs.getString(1);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取运动会名称失败！");
			log.error(e.getMessage());
		}
		return name;
	}
	
}
