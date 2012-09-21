package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;

public class AddScore {
	protected final Logger log = Logger.getLogger(AddScore.class.getName());
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConn dbc = new DBConn();
	
	
	/**
	 * 判断指定条件的成绩是否插入(插入t_match表中)
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
		int num = 0;
		boolean flag = false;	//更改是否成功
		
		JSONArray list = new JSONArray();
		AddScore as = new AddScore();
		list = as.getRecordOnlyByitemgroup(group, finalitemname);	//表中的最高记录
		log.debug("list"+list);
		String recorescore = list.get(1).toString();	//最好成绩
		String recorelevel = list.get(2).toString();	//所破记录
		String level = "0";
		System.out.println("score="+score);
		if("".equals(score)){
			score = "0.0";
		}
		if( Double.parseDouble(score) > Double.parseDouble(recorescore)){
			
			if( recorelevel == "2"){
				level = "2";
			}
			level = "1";
		}
		try {
			DBConn dbc = new DBConn();
			conn = dbc.getConn();
			pstmt = conn.prepareStatement(sql);
			if(score.equals("0.0")){
				score = "";
			}
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
			log.debug("isAddOnlyScore"+e.getMessage());
			flag = false;
			e.printStackTrace();
			
		}
		
		return flag;
		
	}
	/**
	 * 添加单个运动员的信息到t_position表中
	 * @param finalitemid
	 * @param playerid
	 * @param position
	 * @param score
	 */
	public void addOnlyScoreToPosition(int finalitemid,int playerid,int position,String score ){
		String sql = "INSERT INTO t_position(finalitemid,playerid,position,score) VALUES (?,?,?,?)";
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finalitemid);
			pstmt.setInt(2, playerid);
			pstmt.setInt(3, position);
			pstmt.setString(4, score);
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			log.debug("addOnlyScoreToPosition---->flag="+e.getMessage());
			log.debug("addOnlyScoreToPosition="+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 获取当前运动会，某个组别的某个项目的运动员的信息（含成绩）同时添加到t_position表中
	 * @param finalitemname
	 * @param sportsid
	 * @param groupname
	 */
	public void getGpItPlayerMessage( String finalitemname ,int sportsid , String groupname ){
		String itemType = getItemType(finalitemname);//1径赛；2田赛；3接力
		String sql = null;
		if( "2".equals(itemType) ){
			sql = "SELECT t_finalitem.id,playerid,score FROM t_match " +
			"JOIN t_finalitem ON t_match.finalitemid = t_finalitem.id " +
			"JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id " +
			"JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id " +
			"JOIN t_group ON t_group2sports.groupid = t_group.id " +
			"WHERE t_group2sports.sportsid = ? AND  t_group.groupname = ? AND t_finalitem.finalitemname = ? ORDER BY t_match.score DESC";
		}else{
			sql = "SELECT t_finalitem.id,playerid,score FROM t_match " +
			"JOIN t_finalitem ON t_match.finalitemid = t_finalitem.id " +
			"JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id " +
			"JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id " +
			"JOIN t_group ON t_group2sports.groupid = t_group.id " +
			"WHERE t_group2sports.sportsid = ? AND  t_group.groupname = ? AND t_finalitem.finalitemname = ? ";
		}
		
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			pstmt.setString(2, groupname);
			pstmt.setString(3, finalitemname);
			rs = pstmt.executeQuery();
			int i = 0;
			while( rs.next() ){
				i++;
				addOnlyScoreToPosition(rs.getInt(1), rs.getInt(2), i, rs.getString(3));//添加信息到t_position
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("getGpItPlayerMessage="+e.getMessage());
			e.printStackTrace();
		}
		
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
		
		for( int i = inputScoreIndex(scorea); i < playernamea.length; i++){
			flag = this.isAddOnlyScore(playernamea[i], scorea[i], finalitemname , group);
			if( !flag ){
				break;
			}
		}
		LoginDAO loginDAO = new LoginDAO();
		int sportsid =loginDAO.selectCurrentSportsId();		//当前运动会的id
		getGpItPlayerMessage( finalitemname, sportsid, group);//向t_position表中插入数据
		return flag;
	}

	/**
	 * 获取决赛运动员信息
	 * @param finalitemname
	 * @param position
	 * @return	JSONArray
	 */
	public JSONArray getAddIntegralMessage( String finalitemname){
		String sql = "SELECT promotionnum FROM t_finalitem " +
				"WHERE gp2itid IN ( SELECT gp2itid FROM t_finalitem WHERE finalitemname = ?) AND (finalitemtype = '1' OR finalitemtype = '3')";
		
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
		
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		JSONArray alllist = new JSONArray();
		ResultSet rs = null;		PreparedStatement pstmt = null;	
		ResultSet rs2 = null;		PreparedStatement pstmt2 = null;
		ResultSet rs3 = null;		PreparedStatement pstmt3 = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();	//晋级人数
			
			pstmt3 = conn.prepareStatement(sqltp);
			pstmt3.setString(1, finalitemname);
			rs3 = pstmt3.executeQuery();	//预赛、决赛、预决赛
			
			int promotionnum = 0;
			while( rs.next() ){	
				promotionnum = rs.getInt(1);	//晋级数量
			}
			
			String finalitemtype = null;
			while( rs3.next() ){	
				finalitemtype = rs3.getString(1);	//1，预赛、2，决赛、3，预决赛
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
			rs.close();
			pstmt.close(); 
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("getAddIntegralMessage:"+e.getMessage());
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
	public int getIntegral( String finalitemname ,String group){
		LoginDAO loginDAO = new LoginDAO();
		int sportsid =loginDAO.selectCurrentSportsId();		//当前运动会的id
		String sql = "SELECT position,mark,recordmark_low,recordmark_high FROM t_rule WHERE sportsid = ?";
		
		ResultSet rs = null;		PreparedStatement pstmt = null;
		
		int position = 0;		//名次
		String mark = null;		//积分标准
		int recordmark_low = 0;		//院记录积分
		int recordmark_high = 0;	//省记录积分
		
		int sum = 0;		//运动员的个人积分
		int flag = 0;		//更改是否成功
		
		JSONArray recordScore = getRecordOnlyByitemgroup(group, finalitemname);
		
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				position = rs.getInt(1);
				mark = rs.getString(2);
				recordmark_low = rs.getInt(3);
				recordmark_high = rs.getInt(4);
			}
			log.debug("mark="+mark);
			String[] markArray = mark.split(",");	//拆分积分
			
			JSONArray alllist = getAddIntegralMessage(finalitemname);
			
			//组合积分字符串
			for( int a = markArray.length - 1 ; a < alllist.size(); a++ ){
				mark += ",0";
			}
			markArray = mark.split(",");	//重新拆分积分
			for( int i = 0 ; i < alllist.size() ; i++ ){
				sum = Integer.parseInt(markArray[i]);	//名次积分
				JSONArray list = new JSONArray();
				list = alllist.getJSONArray(i);
				if( list.size() != 0 ){
					for( int j = 0 ; j < list.size() ; j++ ){
						
						int playerid =	Integer.parseInt(list.get(0).toString());
						int score =	Integer.parseInt(list.get(1).toString());
						int finalitemid = Integer.parseInt(list.get(2).toString());
						
						if( score > Integer.parseInt(recordScore.getString(1))){	//破纪录积分
							if( recordScore.getString(2).equals("0")){//院记录
								sum += recordmark_low;
								flag += updateRecord(finalitemid, playerid, "0", score);
								flag += addIntegral(finalitemid, playerid, sum);
							}
							if( recordScore.getString(2).equals("1")){//省记录
								sum += recordmark_high;
								flag += updateRecord(finalitemid, playerid, "1", score);
								flag += addIntegral(finalitemid, playerid, sum);
							}
						}else{		//未破记录积分
							flag = addIntegral(finalitemid, playerid, sum);
						}
						addIntegralToMark(playerid, sum);//添加积分到t_position表中
					}
				}
			}
										
			 rs.close();
			 pstmt.close();
		} catch (Exception e) {
			log.debug("getIntegral:"+e.getMessage());
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
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ?))";
		DBConn dbc = new DBConn();
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
			log.debug("getItemType:"+e.getMessage());
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
	public int addIntegral( int finalitemid , int playerid , int sum){
		String sql = "UPDATE t_position SET marks = ? WHERE finalitemid = ? AND playerid = ?";
		int flag = 0;
			DBConn dbc = new DBConn();
			conn = dbc.getConn();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sum);
				pstmt.setInt(2, finalitemid);
				pstmt.setInt(3, playerid);
				flag = pstmt.executeUpdate();
			} catch (Exception e) {
				log.debug("添加单个运动员的积分--->addIntegral:"+e.getMessage());
				e.printStackTrace();
			}
		
		return flag;
	}
	/**
	 * 添加记录表中的最优记录
	 * @param finalitemid
	 * @param playerid
	 * @param position
	 * @param score
	 * @return int
	 */
	public int updateRecord( int finalitemid , int playerid , String recordlevel , int score){
		ArrayList playerMes = getPlayerMessage(playerid);// playername,playersex,departname2012-01
		ArrayList itemMes = getItemMessage(finalitemid);//itemid,DATE
		String sql = "INSERT INTO t_record(itemid,sex,score,playername,departname,recordtime,recordlevel) VALUES (?,?,?,?,?,?,?)";
		int flag = 0;
			DBConn dbc = new DBConn();
			conn = dbc.getConn();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, (Integer)itemMes.get(0));
				pstmt.setInt(2, (Integer)playerMes.get(1));
				pstmt.setString(3, Integer.toString(score));
				pstmt.setString(4, playerMes.get(0).toString());
				pstmt.setString(5, playerMes.get(2).toString());
				pstmt.setString(6, itemMes.get(1).toString().substring(0, 7));
				pstmt.setString(7, recordlevel);
				flag = pstmt.executeUpdate();
				
			} catch (Exception e) {
				log.debug("添加记录表中的最优记录--->updateRecord:"+e.getMessage());
				e.printStackTrace();
			}
		
		return flag;
	}
	
	/**
	 * 获取某个组别中某个项目的最高纪录 	id,score,recordlevel
	 * @param group
	 * @param finalitemname
	 * @return	JSONArray
	 */
	public JSONArray getRecordOnlyByitemgroup( String group,String finalitemname){
		log.debug("getRecordOnlyByitemgroupName: group:"+group+",finalitemname:"+finalitemname);
		String sql = "SELECT id,score,recordlevel FROM t_record WHERE itemid IN (" +
				"SELECT itemid FROM t_group2item WHERE id IN (" +
				"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ? )" +
				") AND sex IN (SELECT playersex FROM t_player WHERE groupid IN (" +
				"SELECT id FROM t_group WHERE groupname = ?))";
		DBConn dbc = new DBConn();
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
				log.debug("getRecordOnlyByitemgroup:"+rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
			}
		} catch (Exception e) {
			log.debug("getRecordOnlyByitemgroup:"+e.getMessage());
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
		DBConn dbc = new DBConn();
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
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				str = rs.getString(1);
			}
			log.debug("str="+str);
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getFormatReg"+e.getMessage());
		}
		return str;
	}
	
	/**
	 * 获得成绩录入的下标
	 * @param finalitmname
	 * @return
	 */
	public int inputScoreIndex(String[] score){
		int flag = 0;
		for( int i = 0 ; i < score.length ; i++ ){
			if( !"".equals( score[i]) || score[i] != null){
				flag = i;
				break;
			}
		}
		return flag;
	}
	/**
	 * 获取当前运动会的 运动会和部门id
	 * @return
	 */
	public ArrayList getS2Did(){
		String sql = "SELECT id FROM t_sports2department WHERE sportsid = ?";
		LoginDAO loginDAO = new LoginDAO();
		int sportsid = loginDAO.selectCurrentSportsId();
		ArrayList s2did = new ArrayList();
		
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid );
			rs = pstmt.executeQuery();
			while( rs.next()){
				s2did.add(rs.getInt(1));
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("addIntegralToMark="+e.getMessage());
			e.printStackTrace();
		}
		return s2did;
		
	}
	
	/**
	 * 向t_mark表插入积分
	 * @param playerid
	 * @param sum
	 */
	public void addIntegralToMark( int playerid, int sum){
		addToMark();
		String sqls = "SELECT sp2dpid,playertype FROM t_player WHERE id = ?";
		int sp2dpid = 0;
		int playertype = 0;//1,true stu ;0,false tea ;
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			//查询sp2dpid,playertype
			pstmt = conn.prepareStatement(sqls);
			pstmt.setInt(1, playerid);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				sp2dpid = rs.getInt(1);
				playertype = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
			
			//加入积分
			if( "1".equals( playertype )){	//学生的积分
				String sql = "UPDATE t_mark SET stusum = ? WHERE sp2dpid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sum);
				pstmt.setInt(2, sp2dpid);
				int flag = pstmt.executeUpdate();
				log.debug("addIntegralToMark---> student ="+flag);
			}else{	//老师的积分
				String sql = "UPDATE t_mark SET teasum = ? WHERE sp2dpid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sum);
				pstmt.setInt(2, sp2dpid);
				int flag = pstmt.executeUpdate();
				log.debug("addIntegralToMark---> student ="+flag);
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("addIntegralToMark---> sqls ="+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 向t_mark表中插入sp2dpid
	 */
	public void addToMark(){
		int flag = 0 ;
		ArrayList s2pid = getS2Did();
		String sql = "INSERT INTO t_mark(sp2dpid) VALUES (?)";
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			for( int i = 0 ; i < s2pid.size() ; i++ ){
				pstmt.setInt(1, Integer.parseInt( s2pid.get(i).toString() ));
				flag = pstmt.executeUpdate();
				if( flag < 0){
					log.debug("addIntegralToMark--->flag="+flag+"sp2dpid插入未成功");
				}
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("addIntegralToMark="+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取该项目的比赛类型
	 */
	public String getFinalitemType( String finalitemname){
		String finalitemType = null;
		String sql = "SELECT finalitemtype FROM t_finalitem WHERE finalitemname = ?";//1预赛；2决赛；3预决赛
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			if( rs.next()){
				finalitemType = rs.getString(1);
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("getFinalitemType="+e.getMessage());
			e.printStackTrace();
		}
		return finalitemType;
	}
	/**
	 * 获取运动员的部分信息    playername,playersex,departname
	 * @param playerid
	 * @return
	 */
	public ArrayList getPlayerMessage( int playerid){
		String sql = "SELECT playername,playersex,departname FROM t_player " +
				"JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.id " +
				"JOIN t_department ON t_sports2department.departid = t_department.id WHERE t_player.id = 1";
		
		ArrayList list = new ArrayList();
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, playerid);
			rs = pstmt.executeQuery();
			if( rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getInt(2));
				list.add(rs.getString(3));
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			log.debug("getPlayerMessage="+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取项目的部分信息 		itemid,DATE
	 * @param finalitemid
	 * @return
	 */
	public ArrayList getItemMessage( int finalitemid){
		String sql = "SELECT itemid,DATE FROM t_group2item " +
				"JOIN t_finalitem ON t_group2item.id = t_finalitem.gp2itid WHERE t_finalitem.id = ? ";
		ArrayList list = new ArrayList();
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finalitemid);
			rs = pstmt.executeQuery();
			if( rs.next()){
				list.add(rs.getInt(1));
				list.add(rs.getString(2));
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
