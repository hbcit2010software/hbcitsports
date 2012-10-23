/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	领队报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-21			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.dao.gameapplydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;

public class AddTeamleaderDAO {
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	private ResultSet rs = null;
	protected final Logger log = Logger.getLogger(AddTeamleaderDAO.class.getName());
	DBConn db = new DBConn();
	/**
	 * 插入t_ports2Department表信息
	 * @param sportsId
	 * @param departId
	 * @param teamLeader
	 * @param coach
	 * @param doctor
	 * @return
	 */
	public int addSports2Department(int sportsId,int departId,String teamLeader,
			String coach,String doctor){
		conn = db.getConn();
		String sql = "update t_sports2department set teamleader=?,coach=?,doctor=? where sportsId=? and departId=?";
		int flag = 0;
		 try {
			 pStatement = conn.prepareStatement(sql);
	         pStatement.setString(1, teamLeader);
	         pStatement.setString(2, coach);
	         pStatement.setString(3, doctor);
	         pStatement.setInt(4, sportsId);
	         pStatement.setInt(5, departId);
	         flag = pStatement.executeUpdate();
		 }catch (SQLException e) { 
			 log.debug("添加失败！");
	    	 log.debug(e.getMessage());
	    	 //System.out.println(e);       
		 }
		 return flag;
		
	}
/*
 * 根据部门和运动会id查询记录条数
 * 吕志瑶
 */
	public int updatePlayerNum( int sportsId,int departId){
		int flag = 0;
		String sql = "SELECT COUNT(teamleader) FROM t_sports2department where sportsid = ? and departid = ?";
		try{
			conn = db.getConn();
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, departId);
			rs = pStatement.executeQuery();
			while( rs.next() ){
				flag = rs.getInt(1);
			}
			db.freeConnection(rs,pStatement,conn);
		}catch( Exception e){
			log.debug(e.getMessage());
			//e.getStackTrace();
			//System.out.println(e.getMessage());
		}
		return flag;
	}
}
