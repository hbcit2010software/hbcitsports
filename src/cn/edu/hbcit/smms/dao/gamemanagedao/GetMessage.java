package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import net.sf.json.JSONArray;

public class GetMessage {
	DBConn dbc = new DBConn();
	protected final Logger log = Logger.getLogger(GetMessage.class.getName());
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LoginDAO dao = new LoginDAO();
	//DBConn dbc = new DBConn();
	/**
	 * 获取某个项目的指定小组运动员的信息
	 * @param finalitemname
	 * @param teamnum
	 * @return	JSONArray
	 */
	public JSONArray getPlayerMessageOnlyTeam( String finalitemname , String teamnum ,String itemtype ,Connection conn , String scoreFormat){
		System.out.println("finalitemname="+finalitemname);
		String sql = "SELECT playernum,playername,score,runway FROM t_player JOIN " +
				"t_match t_match ON t_match.playerid = t_player.id " +
				"WHERE finalitemid IN (SELECT id FROM t_finalitem WHERE finalitemname = ?  AND sportsid = ?) " +
				"AND teamnum = ? ORDER BY runway ";
		String sqlrelay = "SELECT departname,runway,score FROM t_match JOIN t_department ON t_department.id = t_match.playerid WHERE " +
				"finalitemid IN ( SELECT id FROM t_finalitem WHERE finalitemname = ?  AND sportsid = ?)";

		
		JSONArray list = new JSONArray();
		if( itemtype.equals("3") ){
			System.out.println("itemType="+itemtype);
			try {
				pstmt = conn.prepareStatement(sqlrelay);
				pstmt.setString(1, finalitemname);
				pstmt.setInt(2, dao.selectCurrentSportsId());
				rs = pstmt.executeQuery();
				while( rs.next() ){
					JSONArray pm = new JSONArray();
					pm.add(rs.getString(1));	//部门
					pm.add(Integer.toString(rs.getInt(2)));    //道次 	
					pm.add(rs.getString(3));		//成绩
					pm.add(scoreFormat);
					list.add(pm);
				}
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.debug("getPlayerMessageOnlyTeam3"+e.getMessage());
			}
			
		}else{
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, finalitemname);
				pstmt.setInt(2, dao.selectCurrentSportsId());
				pstmt.setString(3, teamnum);
				rs = pstmt.executeQuery();
				
				while( rs.next() ){
					JSONArray pm = new JSONArray();
					pm.add(rs.getString(1));	//运动员号
					pm.add(rs.getString(2));	//运动员姓名
					pm.add(Integer.toString(rs.getInt(4)));	//运动员所在跑道号
					pm.add(rs.getString(3));		//成绩
					pm.add(scoreFormat);
					list.add(pm);
				}
				rs.close();
				pstmt.close();
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
		log.debug("finalitemname"+finalitemname);
		JSONArray list = new JSONArray();
		String sql = "SELECT COUNT(DISTINCT(teamnum)) FROM t_match WHERE finalitemid " +
				"IN ( SELECT id FROM t_finalitem WHERE finalitemname = ? )";  //计算总共有几个小组的sql语句
		
		try {
			String itemtype = getItemType(finalitemname);		//项目的类型田赛     竞赛    接力
			list.add(itemtype);		//添加项目类型
			System.out.println("itemtype="+itemtype);
			
			String scoreFormt = getFormat(finalitemname);
			conn = dbc.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			int flag = 0;
			if( rs.next() ){
				flag = rs.getInt(1);	//项目的小组数
			}
			for( int i = 1 ;i < flag + 1 ;i++ ){
				list.add( getPlayerMessageOnlyTeam( finalitemname, Integer.toString(i), itemtype , conn , scoreFormt) );
			}
			log.debug("getPlayerMessage1"+list);
			rs.close();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getPlayerMessagelist"+e.getMessage());
		}
		return list;
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
		//DBConn dbc = new DBConn();
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
			rs.close();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getItemType"+e.getMessage());
		}
		return itemtype;
	}
	
	
	/**
	 * 获取成绩的格式
	 * @param finalitemname
	 * @return	String
	 */
	public String getFormat( String finalitemname ){
		
		String sql = "SELECT FORMAT FROM t_scoreformat WHERE id in (" +
				"	SELECT scoreformatid FROM t_item WHERE id in (" +
				"SELECT itemid FROM t_group2item WHERE id in (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ?)))";
		String str = null;
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				str = rs.getString(1);
			}
			rs.close();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getFormat"+e.getMessage());
		}
		return str;
		
	}
	/**
	 * 获取成绩的格式的正则表达式
	 * @param finalitemname
	 * @return	String
	 */
	public String getFormatReg( String finalitemname ){
		String sql = "SELECT reg FROM t_scoreformat WHERE id IN (" +
				"SELECT scoreformatid FROM t_item WHERE id IN (" +
				"SELECT itemid FROM t_group2item WHERE id IN (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname =?)))";
		String str = null;
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				str = rs.getString(1);
			}
			log.debug("str="+str);
			rs.close();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getFormatReg"+e.getMessage());
		}
		return str;
	}

}
