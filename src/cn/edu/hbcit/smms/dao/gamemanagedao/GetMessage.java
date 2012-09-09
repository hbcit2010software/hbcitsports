package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import net.sf.json.JSONArray;

public class GetMessage {
	
	protected final Logger log = Logger.getLogger(GetMessage.class.getName());
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//DBConn dbc = new DBConn();
	/**
	 * 获取某个项目的制定小组运动员的信息
	 * @param finalitemname
	 * @param teamnum
	 * @return	JSONArray
	 */
	public JSONArray getPlayerMessageOnlyTeam( String finalitemname , String teamnum ){
		System.out.println("finalitemname="+finalitemname);
		String sql = "SELECT playernum,playername,score,runway FROM t_player JOIN " +
				"t_match t_match ON t_match.playerid = t_player.id " +
				"WHERE finalitemid IN (SELECT id FROM t_finalitem WHERE finalitemname = ?) " +
				"AND teamnum = ? ORDER BY runway ";
		String sqlrelay = "SELECT departname,runway,score FROM t_match JOIN t_department ON t_department.id = t_match.playerid WHERE" +
				"finalitemid IN ( SELECT id FROM t_finalitem WHERE finalitemname = ?)";

		
		JSONArray list = new JSONArray();
		String itemtype = getItemType(finalitemname);		//项目的类型
		
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		
		if( itemtype.equals("3") ){
			
			try {
				pstmt = conn.prepareStatement(sqlrelay);
				pstmt.setString(1, finalitemname);
				rs = pstmt.executeQuery();
				while( rs.next() ){
					JSONArray pm = new JSONArray();
					pm.add(rs.getString(1));	//部门
					pm.add(Integer.toString(rs.getInt(2)));    //道次 	
					pm.add(rs.getInt(3));		//成绩
					
					list.add(pm);
				}
				dbc.freeConnection(conn);	//释放连接
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.debug("getPlayerMessageOnlyTeam3"+e.getMessage());
			}
			
		}else{
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, finalitemname);
				pstmt.setString(2, teamnum);
				rs = pstmt.executeQuery();
				
				while( rs.next() ){
					JSONArray pm = new JSONArray();
					pm.add(rs.getString(1));	//运动员号
					pm.add(rs.getString(2));	//运动员姓名
					pm.add(Integer.toString(rs.getInt(4)));	//运动员所在跑道号
					pm.add(rs.getString(3));		//成绩
					list.add(pm);
				}
				dbc.freeConnection(conn);	//释放连接
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.debug("getPlayerMessageOnlyTeam"+e.getMessage());
			}
			
		}
		return list;
	}
	
	/**
	 * 获得某个项目的所有小组运动员的信息
	 * @param finalitemname
	 * @return	JSONArray
	 */
	public JSONArray getPlayerMessage( String finalitemname ){
		
		JSONArray list = new JSONArray();
		String sql = "SELECT COUNT(DISTINCT(teamnum)) FROM t_match WHERE finalitemid " +
				"IN ( SELECT id FROM t_finalitem WHERE finalitemname = ? )";  //计算总共有几个小组的sql语句
		
		try {
			String itemtype = getItemType(finalitemname);		//项目的类型
			list.add(itemtype);		//添加项目类型
			System.out.println("itemtype="+itemtype);
			
			DBConn dbc = new DBConn();
			conn = dbc.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			int flag = 0;
			if( rs.next() ){
				flag = rs.getInt(1);	//项目的小组数
			}
			for( int i = 1 ;i < flag + 1 ;i++ ){
				list.add( getPlayerMessageOnlyTeam( finalitemname, Integer.toString(i) ) );
			}
			log.debug("getPlayerMessage1"+list);
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getPlayerMessagelist"+e.getMessage());
		}
		return list;
	}
	
	/**
	 * 获取某个项目参加的总人数
	 * @param finalitemname
	 * @return	int
	 */
	public int getPlayerNumber( String finalitemname ){
		System.out.println("getPlayerNumber="+finalitemname);
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		String sql = "SELECT COUNT(DISTINCT(teamnum)) FROM t_match WHERE finalitemid " +
				"IN ( SELECT id FROM t_finalitem WHERE finalitemname = ? )";
		int num = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			int flag = 0;
			while( rs.next() ){
				flag = rs.getInt(1);
			}
			for( int i = 1 ;i < flag + 1 ;i++ ){
				JSONArray listOnly = new JSONArray();
				listOnly = this.getPlayerMessageOnlyTeam(finalitemname, Integer.toString(i));
				num += listOnly.size();
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getPlayerNumber"+e.getMessage());
		}
		return num;
	}
	/**
	 * 获得某个项目的类型
	 * @param finalitemname
	 * @return	String	
	 */
	public String getItemType( String finalitemname ){
		String sql = "SELECT itemtype FROM t_item WHERE id IN " +
				"(SELECT itemid FROM t_group2item WHERE id IN " +
				"(SELECT gp2itid FROM t_finalitem WHERE finalitemname = ?))";
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		String itemtype = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				itemtype = rs.getString(1);
				log.debug(rs.getString(1));
			}
				dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getItemType"+e.getMessage());
		}
		return itemtype;
	}
	
	public JSONArray getItemNameByTY( String itemtype ){
		String sql = "SELECT finalitemname FROM t_finalitem WHERE gp2itid IN " +
				"( SELECT id FROM t_group2item WHERE itemid IN ( SELECT id FROM t_item WHERE itemtype = ?))";
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		JSONArray list = new JSONArray();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemtype);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				list.add(rs.getString(1));
				System.out.println(rs.getString(1));
			}
				dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getItemNameByTY="+e.getMessage());
		}
		return list;
		
	}

}
