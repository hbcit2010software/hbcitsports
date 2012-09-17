package cn.edu.hbcit.smms.dao.createprogramdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.GroupRunwayInfo;

public class AdjustByHandDao {
    protected final Logger log = Logger.getLogger(AdjustByHandDao.class.getName());
	
	private Connection conn;
	private PreparedStatement  ps;
	private ResultSet rs;
	
	/**
	 * 获取指定组的运动员和跑道信息
	 * @param teamnum  第几组
	 * @param finalitemid  最终分好组后的项目
	 * @return  List各组运动员和跑道信息
	 */
	public List getRunwayInfo(int finalitemid, int teamnum ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String sql = "SELECT teamnum,t_player.playernum,runway,t_match.playerid FROM t_match " +
					" INNER JOIN t_player ON t_player.id = t_match.playerid " +
					" WHERE t_match.finalitemid=? and t_match.teamnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, finalitemid);
			ps.setInt(2, teamnum);
			rs = ps.executeQuery();
			sql.length();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				GroupRunwayInfo gRunway = new GroupRunwayInfo();
				gRunway.setTeamNum(rs.getInt(1));
				gRunway.setPlayNum(rs.getString(2));
				gRunway.setRunway(rs.getInt(3));
				gRunway.setPlayerId(rs.getInt(4));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				list.add(gRunway);
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	/**
	 * 修改跑道
	 * @param finalitemid  
	 * @param teamnum  第几组
	 * @param playerid   运动员id
	 * @param runway  跑道
	 * @return 是否修改成功的受影响的行数
	 */
	
	public int  updatePlayerRunway(int finalitemid , int teamnum, String[] playerid, String[] runway ) {
		//List list = new ArrayList();
		DBConn db = new DBConn();
		int flag = 0;
		try {
			for(int i = 0; i < playerid.length; i++){
			conn = db.getConn();
		    String sql = "UPDATE t_match SET runway=?  WHERE teamnum=? AND playerid=? AND finalitemid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(runway[i]));
			ps.setInt(2, teamnum);
			ps.setInt(3, Integer.parseInt(playerid[i]));
			ps.setInt(4, finalitemid);
			flag += ps.executeUpdate();
			db.freeConnection(conn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return flag;
	}
	
	
	public List getItemInfo() {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String sql = "SELECT t_finalitem.groupnum,teamnum,t_player.playernum,runway,t_match.playerid " +
					" FROM t_match " +
					" INNER JOIN t_player ON t_player.id = t_match.playerid  " +
					" INNER JOIN t_finalitem ON t_finalitem.id=t_match.finalitemid " +
					" WHERE t_match.finalitemid=1";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			sql.length();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				GroupRunwayInfo gRunway = new GroupRunwayInfo();
				gRunway.setTeamNum(rs.getInt(1));
				gRunway.setPlayNum(rs.getString(2));
				gRunway.setRunway(rs.getInt(3));
				gRunway.setPlayerId(rs.getInt(4));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				list.add(gRunway);
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	


}
