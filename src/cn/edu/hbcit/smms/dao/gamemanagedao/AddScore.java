package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;

public class AddScore {
	protected final Logger log = Logger.getLogger(AddScore.class.getName());
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConn dbc = new DBConn();
	
	
	/**
	 * 判断指定条件的成绩是否插入
	 * @param teamnum
	 * @param runway
	 * @param score
	 * @param finalitemname
	 * @return	boolean
	 */
	public boolean isAddOnlyScore( String playername , String score , String finalitemname , String group ){
		String sql = "UPDATE t_match SET score = ?,recordlevel = ? WHERE finalitemid = " +
				"(  SELECT id FROM t_finalitem WHERE finalitemname = ? ) AND " +
				"playerid = (SELECT id FROM t_player WHERE playername = ?)";
		conn = dbc.getConn();
		
		int num = 0;
		boolean flag = false;
		
		JSONArray list = new JSONArray();
		AddScore as = new AddScore();
		list = as.getRecordOnlyByitemgroup(group, finalitemname);
		String recorescore = list.get(1).toString();
		String recorelevel = list.get(2).toString();
		String level = "0";
		System.out.println("score="+score);
		if( Double.parseDouble(score) > Double.parseDouble(recorescore)){
			if( recorelevel == "2"){
				level = "2";
			}
			level = "1";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, score);
			pstmt.setString(2, level);
			pstmt.setString(3, finalitemname);
			pstmt.setString(4, playername);
			
			num = pstmt.executeUpdate();
			if( num > 0 ){
				flag = true;
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = false;
			e.printStackTrace();
			log.debug("isAddOnlyScore"+e.getMessage());
		}
		
		return flag;
		
	}
	
	/**
	 * 判断所有的成绩是否插入
	 * @param playernum
	 * @param score
	 * @param finalitemname
	 * @return	boolean
	 */
	public boolean isAddScore( String playername ,String score , String finalitemname , String group ){
		String regex = ",";
		boolean flag = false;	//接受成绩是否插入成功
		String[] playernamea = playername.trim().split(regex);
		String[] scorea = score.trim().split(regex);
		for( int i = 0; i < playernamea.length; i++){
			flag = this.isAddOnlyScore(playernamea[i], scorea[i], finalitemname , group);
			if( !flag ){
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 获取决赛运动员信息
	 * @param finalitemname
	 * @param position
	 * @return	JSONArray
	 */
	public JSONArray getAddIntegralMessage( String finalitemname){
		String sql = "SELECT promotionnum FROM t_finalitem WHERE id = " +
				"(SELECT id FROM t_finalitem WHERE gp2itid = (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname= ?) AND finalitemtype = '1')";
		
		String sqltp = "SELECT finalitemtype FROM t_finalitem WHERE finalitemname = ? ";
		AddScore as = new AddScore();
		String matchtype = null;
		matchtype = as.getItemType(finalitemname);	//1：径赛，2：田赛，3：接力
		String sql2 = null;
		if( matchtype == "1" || matchtype == "3"){
			sql2 = "SELECT playerid,score,finalitemid FROM t_match WHERE finalitemid IN (" +
					"SELECT id FROM t_finalitem WHERE gp2itid = (" +
					"SELECT gp2itid FROM t_finalitem WHERE finalitemname= ?) AND finalitemtype = '1' " +
					") ORDER BY score+0 LIMIT ?";
			
		}
		if( matchtype == "2" ){
			sql2 = "SELECT playerid,score,finalitemid FROM t_match WHERE finalitemid IN (" +
			"SELECT id FROM t_finalitem WHERE gp2itid = (" +
			"SELECT gp2itid FROM t_finalitem WHERE finalitemname= ?) AND finalitemtype = '1' " +
			") ORDER BY score+0 desc LIMIT ?";
		}
		
		conn = dbc.getConn();
		JSONArray alllist = new JSONArray();
		ResultSet rs = null;		PreparedStatement pstmt = null;	
		ResultSet rs2 = null;		PreparedStatement pstmt2 = null;
		ResultSet rs3 = null;		PreparedStatement pstmt3 = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			
			pstmt3 = conn.prepareStatement(sqltp);
			pstmt3.setString(1, finalitemname);
			rs3 = pstmt3.executeQuery();
			int promotionnum = 0;
			while( rs.next() ){	
				promotionnum = rs.getInt(1);	//晋级数量
			}
			String finalitemtype = null;
			while( rs3.next() ){	
				finalitemtype = rs3.getString(1);	//预赛、决赛、预决赛
			}
			
			if( finalitemtype.equals("2") || finalitemtype.equals("3")){
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, finalitemname);
					pstmt2.setInt(2, promotionnum);
					rs2 = pstmt2.executeQuery();
					while( rs2.next() ){
						JSONArray list = new JSONArray();		//晋级运动员的信息
						list.add(Integer.toString(rs2.getInt(1)));		//playerid
						list.add(Integer.toString(rs2.getInt(2)));		//score
						list.add(Integer.toString(rs2.getInt(3)));		//finalitemid
						alllist.add(list);
					}
			}	
			if( finalitemtype.equals("2")){
				JSONArray list = new JSONArray();
				alllist.add(list);
			}
			rs.close();
			pstmt.close(); 
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alllist;
		
	}
	
	/**
	 * 获取全部决赛运动员的积分同时添加到t_position表中
	 * @param finalitemname
	 * @param sportsid
	 * @param group
	 * @return int
	 */
	public int getIntegral( String finalitemname  ,int sportsid ,String group){
		String sql = "SELECT position,mark,recordmark_low,recordmark_high FROM t_rule WHERE sportsid = ?";
		
		ResultSet rs = null;		PreparedStatement pstmt = null;
		
		int position = 0;		//名次
		String mark = null;		//积分标准
		int recordmark_low = 0;		//院记录积分
		int recordmark_high = 0;	//省记录积分
		
		int sum = 0;		//运动员的个人积分
		int flag = 0;		//更改是否成功
		
		AddScore as = new AddScore();
		JSONArray recordScore = new JSONArray();
		recordScore = as.getRecordOnlyByitemgroup(group, finalitemname);
		
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				log.debug("rs");
				position = rs.getInt(1);
				mark = rs.getString(2);
				recordmark_low = rs.getInt(3);
				recordmark_high = rs.getInt(4);
			}
			log.debug("mark="+mark);
			String[] markArray = mark.split(",");	//拆分积分
			
			JSONArray alllist = new JSONArray();
			alllist = this.getAddIntegralMessage(finalitemname);
			for( int i = 0 ; i < alllist.size() ; i++ ){
				sum = Integer.parseInt(markArray[i]);
				JSONArray list = new JSONArray();
				list = alllist.getJSONArray(i);
				if( list.size() != 0 ){
					for( int j = 0 ; j < list.size() ; j++ ){
						
						int playerid =	Integer.parseInt(list.get(0).toString());
						int score =	Integer.parseInt(list.get(1).toString());
						int finalitemid = Integer.parseInt(list.get(2).toString());
						
						if( score > Integer.parseInt(recordScore.getString(1))){
							if( recordScore.getString(2).equals("1")){
								sum += recordmark_low;
								flag += as.updateRecord(finalitemid, playerid, sportsid, score);
								flag += as.addIntegral(finalitemid, playerid, position, sum);
							}
							if( recordScore.getString(2).equals("2")){
								sum += recordmark_high;
								flag += as.updateRecord(finalitemid, playerid, sportsid, score);
								flag += as.addIntegral(finalitemid, playerid, position, sum);
							}
						}else{
							flag = as.addIntegral(finalitemid, playerid, position, sum);
						}
					}
				}
			}
			 rs.close();
			 pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取指定项目的类型        例如：1：径赛，2：田赛，3：接力
	 * @param finalitemname
	 * @return	String
	 */
	public String getItemType( String finalitemname ){
		String sql = "SELECT itemtype FROM t_item WHERE id = (" +
				"SELECT itemid FROM t_group2item WHERE id = (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname = '100预赛'))";
		
		conn = dbc.getConn();
		String str = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				str = rs.getString(1);
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
		
	}
	/**
	 * 添加单个运动员的积分
	 * @param finalitemid
	 * @param playerid
	 * @param position
	 * @param sum
	 * @return	int
	 */
	public int addIntegral( int finalitemid , int playerid , int position, int sum){
		String sql = "INSERT INTO t_position(finalitemid,playerid,position,score) VALUES(?,?,?,?)";
		conn = dbc.getConn();
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finalitemid);
			pstmt.setInt(2, playerid);
			pstmt.setInt(3, position);
			pstmt.setInt(4, sum);
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 更改记录表中的最优记录
	 * @param finalitemid
	 * @param playerid
	 * @param position
	 * @param score
	 * @return int
	 */
	public int updateRecord( int finalitemid , int playerid , int sportsid , int score){
		String sql = "UPDATE t_record SET itemid=(SELECT itemid FROM t_group2item WHERE id=(SELECT gp2itid FROM t_finalitem WHERE id=?))," +
				"sex=(SELECT playersex FROM t_player WHERE id=?)," +
				"score=(SELECT score FROM t_match WHERE playerid=?)," +
				"playername = (SELECT playername FROM t_player WHERE id = ?)," +
				"departname = (SELECT departname FROM t_department WHERE id = ( SELECT departid FROM t_sports2department WHERE " +
				"id = ( SELECT sp2dpid FROM t_player WHERE id = ?))),sportsname = (SELECT sportsname FROM t_sports WHERE id = ?)," +
				"recordtime = (SELECT DATE FROM t_finalitem WHERE id = ?)," +
				"recordlevel = (SELECT recordlevel FROM t_match WHERE finalitemid = ? AND playerid = ?)";
		conn = dbc.getConn();
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finalitemid);
			pstmt.setInt(2, playerid);
			pstmt.setInt(3, playerid);
			pstmt.setInt(4, playerid);
			pstmt.setInt(5, playerid);
			pstmt.setInt(6, sportsid);
			pstmt.setInt(7, finalitemid);
			pstmt.setInt(8, playerid);
			flag = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取某个组别中某个项目的最高纪录
	 * @param group
	 * @param finalitemname
	 * @return	JSONArray
	 */
	public JSONArray getRecordOnlyByitemgroup( String group,String finalitemname){
		
		String sql = "SELECT id,score,recordlevel FROM t_record WHERE itemid IN (" +
				"SELECT itemid FROM t_group2item WHERE id IN (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ? )" +
				") AND sex IN (SELECT playersex FROM t_player WHERE groupid IN (" +
				"SELECT id FROM t_group WHERE groupname = ?))";
		
		conn = dbc.getConn();
		JSONArray list = new JSONArray();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			pstmt.setString(2, group);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				list.add(Integer.toString(rs.getInt(1)));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 获取成绩的格式
	 * @param finalitemname
	 * @return	String
	 */
	public String getFormat( String finalitemname ){
		String sql = "SELECT FORMAT FROM t_scoreformat WHERE id = (" +
				"	SELECT scoreformatid FROM t_item WHERE id = (" +
				"SELECT itemid FROM t_group2item WHERE id = (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ?)))";
		String str = null;
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				str = rs.getString(1);
			}
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
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getFormatReg"+e.getMessage());
		}
		return str;
	}
	

}
