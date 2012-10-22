package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	LoginDAO dao = new LoginDAO();
	private int sportsId = dao.selectCurrentSportsId();
	boolean a = true;
	boolean b = true;
	/**
	 * 判断指定条件的成绩是否插入(插入t_match表中)
	 * @param playernum
	 * @param score
	 * @param finalitemname
	 * @return	boolean
	 */
	public boolean isAddOnlyScore( String playernum , String score , String finalitemname , String group ){
		System.out.println("-----------------------playernamea="+playernum+",scorea[i]="+score+",finalitemname="+finalitemname+",group="+group);
		String itemType = getItemType(finalitemname);//1：径赛，2：田赛，3：接力
		String sql = null;
		if( "3".equals(itemType)){
			sql = "UPDATE t_match SET score = ?,recordlevel = ? WHERE finalitemid IN " +
			"(SELECT id FROM t_finalitem WHERE finalitemname = ? AND sportsid = ? ) AND " +
			"playerid IN (SELECT id FROM t_department WHERE departname = ? )";
		}else{
			sql = "UPDATE t_match SET score = ?,recordlevel = ? WHERE finalitemid IN " +
					"(SELECT id FROM t_finalitem WHERE finalitemname = ? AND sportsid =? ) AND " +
					"playerid IN ( SELECT id FROM t_player WHERE playernum = ? AND sp2dpid IN " +
					"( SELECT id FROM t_sports2department WHERE sportsid = ?))";
		}
		
		
		int num = 0;
		boolean flag = false;	//更改是否成功
		
		JSONArray list = new JSONArray();
		AddScore as = new AddScore();
		try {
			String level = "2";		//未破纪录
			String finalitemType = getFinalitemType(finalitemname);		//1预赛；2决赛；3预决赛
			if( "2".equals(finalitemType) || "3".equals(finalitemType)){
				
				list = as.getRecordOnlyByitemgroup(group, finalitemname,itemType);	//表中的最高记录
				if( !(list.size() == 0) ){
					String recorescore = list.get(0).toString();	//最好成绩
					String recorelevel = list.get(1).toString();	//所破记录
					System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  recorescore="+recorescore+",recorelevel="+recorelevel+",score="+score);
					if( "2".equals(itemType)){
						if( !("0.00".equals(score) || "0.00.00".equals(score) || "00.00.00".equals(score))){
							if( this.isScoreMax(score, recorescore)){
								if( recorelevel == "1"){	//破省记录
									level = "1";
								}
								level = "0";	//破院记录
							}
						}
						
					}else{
						if( !("0.00".equals(score) || "0.00.00".equals(score) || "00.00.00".equals(score))){
							if( this.isScoreMin(score, recorescore)){
								if( recorelevel == "1"){	//破省记录
									level = "1";
								}
								level = "0";	//破院记录
							}
						}
					}
				}
			}
			
			
			conn = dbc.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, score);
			pstmt.setString(2, level);
			pstmt.setString(3, finalitemname);
			pstmt.setInt(4, sportsId);
			pstmt.setString(5, playernum);
			if( !"3".equals(itemType)){
				pstmt.setInt(6, sportsId);
			}
			
			System.out.println("-----------------------playernamea="+playernum+",scorea[i]="+score+",finalitemname="+finalitemname+",group="+group);
			num = pstmt.executeUpdate();
			System.out.println("-----------------------playernamea="+playernum+",scorea[i]="+score+",finalitemname="+finalitemname+",group="+group+",num="+num);
			if( num > 0 ){
				flag = true;
			}
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("isAddOnlyScore="+e.getMessage());
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
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finalitemid);
			pstmt.setInt(2, playerid);
			pstmt.setInt(3, position);
			pstmt.setString(4, score);
			flag = pstmt.executeUpdate();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
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
			"WHERE t_group2sports.sportsid = ? AND  t_group.groupname = ? AND t_finalitem.finalitemname = ? ORDER BY t_match.score";
		}
		
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			pstmt.setString(2, groupname);
			pstmt.setString(3, finalitemname);
			rs = pstmt.executeQuery();
			int i = 0;
			if(a){
				String sqll = "DELETE FROM t_position WHERE finalitemid IN ( " +
						"SELECT id FROM t_finalitem WHERE finalitemname = ? AND sportsid = ?)";
				PreparedStatement pstmt1 = conn.prepareStatement(sqll);
				pstmt1.setString(1, finalitemname);
				pstmt1.setInt(2, sportsId);
				pstmt1.executeUpdate();
				pstmt1.close();
			}
			while( rs.next() ){
				i++;
				addOnlyScoreToPosition(rs.getInt(1), rs.getInt(2), i, rs.getString(3));//添加信息到t_position
			}
			a = false;
			dbc.freeConnection(rs,pstmt,conn);	//释放连接
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
	public boolean isAddScore( String playernum ,String score , String finalitemname , String group ){
		
		
		String regex = ",";
		boolean flag = false;	//接受成绩是否插入成功
		String[] playernuma = playernum.trim().split(regex);
		String[] scorea = score.trim().split(regex);
		for( int i = 0; i < playernuma.length; i++){
			System.out.println("-----------------------playernamea="+playernuma[i]+",scorea[i]="+scorea[i]+",finalitemname="+finalitemname+",group="+group);
			flag = this.isAddOnlyScore(playernuma[i], scorea[i], finalitemname , group);
			if( !flag ){
				break;
			}
		}
		if( flag){
			getGpItPlayerMessage( finalitemname, dao.selectCurrentSportsId(), group);//向t_position表中插入数据
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
		String sql = "SELECT promotionnum FROM t_finalitem " +
				"WHERE gp2itid IN ( SELECT gp2itid FROM t_finalitem WHERE finalitemname = ?) AND " +
				"finalitemtype = ? AND sportsid = ?";
		String sqltp = "SELECT finalitemtype FROM t_finalitem WHERE finalitemname = ? ";
		AddScore as = new AddScore();
		String matchtype = as.getItemType(finalitemname);	//1：径赛，2：田赛，3：接力;
		String finalItemType = getFinalitemType(finalitemname);
		System.out.println("matchtype="+matchtype);
		String sql2 = null;
		if( "1".equals(matchtype) || "3".equals(matchtype)){
			sql2 = "SELECT playerid,score,finalitemid FROM t_match WHERE finalitemid IN (" +
					"SELECT id FROM t_finalitem WHERE gp2itid = (" +
					"SELECT gp2itid FROM t_finalitem WHERE finalitemname= ? AND sportsid = ? ) AND finalitemtype = ? " +
					") ORDER BY score+0 LIMIT ?";
			
		}
		if( "2".equals(matchtype) ){
			sql2 = "SELECT playerid,score,finalitemid FROM t_match WHERE finalitemid IN (" +
			"SELECT id FROM t_finalitem WHERE gp2itid in (" +
			"SELECT gp2itid FROM t_finalitem WHERE finalitemname= ? AND sportsid = ?) AND finalitemtype = ? " +
			") ORDER BY score+0 desc LIMIT ?";
		}
		
		DBConn dbc = new DBConn();
		conn = dbc.getConn();
		JSONArray alllist = new JSONArray();
		ResultSet rs = null;		PreparedStatement pstmt = null;	
		ResultSet rs2 = null;		PreparedStatement pstmt2 = null;
		int sportsid = sportsId;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			pstmt.setString(2, finalItemType);
			pstmt.setInt(3, sportsid);
			rs = pstmt.executeQuery();	
			
			int promotionnum = 0;
			if( rs.next() ){	
				promotionnum = rs.getInt(1);	//晋级数量
			}
			System.out.println("sql2="+sql2);
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, finalitemname);
					pstmt2.setInt(2, sportsId);
					pstmt2.setString(3, finalItemType);
					pstmt2.setInt(4, promotionnum);
					rs2 = pstmt2.executeQuery();
					while( rs2.next() ){
						JSONArray list = new JSONArray();		//晋级运动员的信息
						list.add(Integer.toString(rs2.getInt(1)));		//playerid
						list.add(rs2.getString(2));		//score
						list.add(Integer.toString(rs2.getInt(3)));		//finalitemid
						alllist.add(list);
					}
			rs2.close();
			pstmt2.close();
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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>finalitemname="+finalitemname+",group="+group);
		int sportsid =sportsId;		//当前运动会的id
		String sql = "SELECT position,mark,recordmark_low,recordmark_high FROM t_rule WHERE sportsid = ?";
		
		ResultSet rs = null;		PreparedStatement pstmt = null;
		
		int position = 0;		//给积分的名次
		String mark = null;		//积分标准
		int recordmark_low = 0;		//院记录积分
		int recordmark_high = 0;	//省记录积分
		
		int sum = 0;		//运动员的个人积分
		int flag = 0;		//更改是否成功

		String itemType = getItemType(finalitemname);//1：径赛，2：田赛，3：接力
		JSONArray recordScore = getRecordOnlyByitemgroup(group, finalitemname,itemType);
		//DBConn dbc = new DBConn();
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
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>alllist="+alllist.size());
			//组合积分字符串
			for( int a = markArray.length - 1 ; a < alllist.size(); a++ ){
				mark += ",0";
			}
			markArray = mark.split(",");	//重新拆分积分
			
			for( int i = 0 ; i < alllist.size(); i++ ){
				JSONArray list = alllist.getJSONArray(i);
				if( markArray.length > i){
					sum = Integer.parseInt(markArray[i]);	//名次积分
					if( list.size() != 0 ){
							int playerid =	Integer.parseInt(list.get(0).toString());
							String score =	list.get(1).toString();
							int finalitemid = Integer.parseInt(list.get(2).toString());
							if( recordScore.size() == 0 ){//record无当前项目的最优记录
								if( i == 0 ){
									sum += recordmark_low;
									insertRecord(finalitemid, playerid, "0", score);
									addIntegral(finalitemid, playerid, sum );
								}else{
									addIntegral(finalitemid, playerid, sum);
								}
							}else{//record有当前项目的最优记录
								if( markArray.length > i){}
									if( "2".equals(itemType) ){//2,田赛
										if(  isScoreMax(score, recordScore.getString(0)) ){	//破纪录积分
											System.out.println("2,田赛");
											if( recordScore.getString(1).equals("0")){//院记录
												sum += recordmark_low;
												flag += insertRecord(finalitemid, playerid, "0", score);
												flag += addIntegral(finalitemid, playerid, sum);
											}else if( recordScore.getString(1).equals("1")){//省记录
												sum += recordmark_high;
												flag += insertRecord(finalitemid, playerid, "1", score);
												flag += addIntegral(finalitemid, playerid, sum);
											}
											
										}else{		//未破记录积分
											flag = addIntegral(finalitemid, playerid, sum);
										}
									}else{
										if( this.isScoreMin(score, recordScore.getString(0))){	//破纪录积分
											if( recordScore.getString(1).equals("0")){//院记录
												sum += recordmark_low;
												flag += insertRecord(finalitemid, playerid, "0", score);
												flag += addIntegral(finalitemid, playerid, sum);
											}else if( recordScore.getString(1).equals("1")){//省记录
												sum += recordmark_high;
												flag += insertRecord(finalitemid, playerid, "1", score);
												flag += addIntegral(finalitemid, playerid, sum);
											}
											
										}else{		//未破记录积分
											flag = addIntegral(finalitemid, playerid, sum);
										}
									}
								
							}
							//addIntegralToMark(playerid, sum ,group,itemType);//添加积分到t_position表中
					}
				}
			}
										
			 rs.close();
			 pstmt.close();
			 conn.close();
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
		String sql = "SELECT itemtype FROM t_item WHERE id in (" +
				"SELECT itemid FROM t_group2item WHERE id in (" +
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
			//DBConn dbc = new DBConn();
			conn = dbc.getConn();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sum);
				pstmt.setInt(2, finalitemid);
				pstmt.setInt(3, playerid);
				flag = pstmt.executeUpdate();
				pstmt.close();
				conn.close();
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
	public int insertRecord( int finalitemid , int playerid , String recordlevel , String score){
		
		ArrayList itemMes = getItemMessage(finalitemid);//itemid,DATE
		String sql = "INSERT INTO t_record(itemid,sex,score,playerid,playername,departname,sportsname,recordtime,recordlevel) VALUES (?,?,?,?,?,?,?,?,?)";
		String sqll = "SELECT finalitemname FROM t_finalitem WHERE id = ?";
		String finalitemnam = null;
		int sex = 0;
		int flag = 0;
			
			try {
				conn = dbc.getConn();
				//conn.setAutoCommit(false);
				//1,竞赛  2, 田赛   3,接力
				pstmt = conn.prepareStatement(sqll);
				pstmt.setInt(1, finalitemid);
				rs = pstmt.executeQuery();
				if( rs.next()){
					finalitemnam = rs.getString(1);
				}
				rs.close();
				pstmt.close();
				conn.close();
				
				ArrayList playerMes = getPlayerMessage(playerid ,finalitemnam,finalitemid);// playername,playersex,departname2012-01
				conn = dbc.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, (Integer)itemMes.get(0));
				pstmt.setInt(2, (Integer)playerMes.get(1));
				pstmt.setString(3, score);
				pstmt.setInt(4, playerid);
				pstmt.setString(5, playerMes.get(0).toString());
				pstmt.setString(6, playerMes.get(2).toString());
				pstmt.setString(7, dao.selectCurrentSportsName());
				pstmt.setString(8, itemMes.get(1).toString().substring(0, 7));
				pstmt.setString(9, recordlevel);
				flag = pstmt.executeUpdate();

				pstmt.close();
				conn.close();
			} catch (Exception e) {
				log.debug("添加记录表中的最优记录--->updateRecord:"+e.getMessage());
				e.printStackTrace();
			}
		
		return flag;
	}
	/**
	 * 删除recored表中的数据
	 * @param finalitemname
	 * @param groupname
	 */
	public void deleteRecordByFinalItemId( String finalitemname ,String groupname){
		String sql = "DELETE FROM t_record WHERE itemid IN ( " +
				"SELECT itemid FROM t_group2item WHERE id IN ( SELECT gp2itid FROM t_finalitem WHERE finalitemname = ? )) " +
				"AND sex IN (SELECT playersex FROM t_player WHERE groupid IN ( SELECT id FROM t_group WHERE groupname = ?)) AND sportsname = ?";
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			pstmt.setString(2, groupname);
			pstmt.setString(3, dao.selectCurrentSportsName());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			log.debug("deleteRecordByFinalItemId:"+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 获取某个组别中某个项目的最高纪录 	id,score,recordlevel
	 * @param group
	 * @param finalitemname
	 * @return	JSONArray
	 */
	public JSONArray getRecordOnlyByitemgroup( String group,String finalitemname,String itemType){
		String sql = null;
		if( "2".equals(itemType)){
			sql = "SELECT score,recordlevel FROM t_record WHERE itemid IN ( " +
					"SELECT itemid FROM t_group2item WHERE id IN ( " +
					"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ? ) " +
					") AND sex IN (SELECT playersex FROM t_player WHERE groupid IN ( " +
					"SELECT id FROM t_group WHERE groupname = ?)) ORDER BY score DESC LIMIT 1";
		}else{
			sql = "SELECT score,recordlevel FROM t_record WHERE itemid IN ( " +
			"SELECT itemid FROM t_group2item WHERE id IN ( " +
			"SELECT gp2itid FROM t_finalitem WHERE finalitemname = ? ) " +
			") AND sex IN (SELECT playersex FROM t_player WHERE groupid IN ( " +
			"SELECT id FROM t_group WHERE groupname = ?)) ORDER BY score LIMIT 1";
		}
		
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		JSONArray list = new JSONArray();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			pstmt.setString(2, group);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			log.debug("getRecordOnlyByitemgroup:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
		
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
		
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid );
			rs = pstmt.executeQuery();
			while( rs.next()){
				s2did.add(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
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
	public void addIntegralToMark( int playerid, int sum ,String group ,String itemType){
		System.out.println("//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分");
		System.out.println("playerid"+playerid);
		addToMark();
		String sqls = "SELECT sp2dpid,playertype FROM t_player WHERE id = ?";
		String sqlss = "SELECT stusum FROM t_mark WHERE sp2dpid = ?";
		String sqlq = "SELECT grouptype,t_sports2department.id FROM t_group " +
				"JOIN t_group2sports ON t_group.id = t_group2sports.groupid " +
				"JOIN t_sports2department ON t_sports2department.sportsid = t_group2sports.sportsid " +
				"WHERE groupname = ? AND t_group2sports.sportsid = ? AND t_sports2department.departid = ?";
		int sp2dpid = 0;
		int playertype = 0;//1,true stu ;0,false tea ;
		//DBConn dbc = new DBConn();
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
			System.out.println("sp2dpid="+sp2dpid+",playertype="+playertype);
			rs.close();
			pstmt.close();
			
			if( "3".equals(itemType)){
				conn = dbc.getConn();
				PreparedStatement pstmt1 = conn.prepareStatement(sqlq);
				pstmt1.setString(1, group);
				pstmt1.setInt(2, dao.selectCurrentSportsId());
				pstmt1.setInt(3, playerid);
				rs = pstmt1.executeQuery();
				if(rs.next()){
					playertype = rs.getInt(1);
					sp2dpid = rs.getInt(2);
				}
				rs.close();
				pstmt1.close();
			}
			
			
			//加入积分
			if( "1".equals( playertype+"" )){	//学生的积分
				System.out.println("//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分"+playertype+",sum="+sum);
				sqlss = "SELECT stusum FROM t_mark WHERE sp2dpid = ?";
				PreparedStatement pstmt1 = conn.prepareStatement(sqlss);
				pstmt1.setInt(1, sp2dpid);
				rs = pstmt1.executeQuery();
				if( rs.next()){
					sum += rs.getInt(1);
				}
				System.out.println("//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分//学生的积分"+sum);
				rs.close();
				pstmt1.close();
				System.out.println(")))))))))))))))))))))))))))))))))))sum"+sum);
				String sql = "UPDATE t_mark SET stusum = ? WHERE sp2dpid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sum);
				pstmt.setInt(2, sp2dpid);
				int flag = pstmt.executeUpdate();
				log.debug("addIntegralToMark---> student ="+flag);
			}else{	//老师的积分
				
				sqlss = "SELECT teasum FROM t_mark WHERE sp2dpid = ?";
				PreparedStatement pstmt1 = conn.prepareStatement(sqlss);
				pstmt1.setInt(1, sp2dpid);
				rs = pstmt1.executeQuery();
				if( rs.next()){
					sum += rs.getInt(1);
				}
				rs.close();
				pstmt1.close();
				
				String sql = "UPDATE t_mark SET teasum = ? WHERE sp2dpid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sum);
				pstmt.setInt(2, sp2dpid);
				int flag = pstmt.executeUpdate();
				log.debug("addIntegralToMark---> teacher ="+flag);
			}
			rs.close();
			pstmt.close();
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
		System.out.println("s2pid.size()=fffffffffffffffffffffffffffffffffffffffff"+s2pid.size());
		String sql1 = "SELECT id FROM t_mark WHERE sp2dpid = ?";
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, Integer.parseInt(s2pid.get(0).toString()));
			rs = pstmt.executeQuery();
			if( rs.next()){
				b =false;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			log.debug("addToMark ="+e.getMessage());
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO t_mark(sp2dpid) VALUES (?)";
		//DBConn dbc = new DBConn();
		if( b ){
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
				pstmt.close();
				dbc.freeConnection(conn);	//释放连接
			} catch (Exception e) {
				log.debug("addIntegralToMark="+e.getMessage());
				e.printStackTrace();
			}
			b = false;
		}
		
	}
	
	/**
	 * 获取该项目的比赛类型
	 */
	public String getFinalitemType( String finalitemname){
		String finalitemType = null;
		String sql = "SELECT finalitemtype FROM t_finalitem WHERE finalitemname = ?";//1预赛；2决赛；3预决赛
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			if( rs.next()){
				finalitemType = rs.getString(1);
			}
			rs.close();
			pstmt.close();
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
	public ArrayList getPlayerMessage( int playerid ,String finalitemnam ,int finalitemid ){
		
		String sql = null;
		
		
		String itemType = this.getItemType(finalitemnam);
		if( "3".equals(itemType)){
			sql = "SELECT '',t_group.groupsex,t_department.departname FROM t_match " +
					"JOIN t_finalitem ON t_match.finalitemid = t_finalitem.id " +
					"JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id " +
					"JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id " +
					"JOIN t_group ON t_group2sports.groupid = t_group.id " +
					"JOIN t_department ON t_match.playerid = t_department.id " +
					"WHERE t_department.id =? AND t_finalitem.sportsid=? AND t_finalitem.id=? ";
		}else{
			sql = "SELECT playername,playersex,departname FROM t_player " +
			"JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.id " +
			"JOIN t_department ON t_sports2department.departid = t_department.id WHERE t_player.id = ? and t_sports2department.sportsid = ?";
		}
		ArrayList list = new ArrayList();
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, playerid);
			pstmt.setInt(2, dao.selectCurrentSportsId());
			if( "3".equals(itemType)){
				pstmt.setInt(3, finalitemid);
			}
			rs = pstmt.executeQuery();
			if( rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getInt(2));
				list.add(rs.getString(3));
			}
			rs.close();
			pstmt.close();
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
		//DBConn dbc = new DBConn();
		conn = dbc.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, finalitemid);
			rs = pstmt.executeQuery();
			if( rs.next()){
				System.out.println("itemid="+rs.getInt(1)+",DATE="+rs.getString(2));
				list.add(rs.getInt(1));
				list.add(rs.getString(2));
			}
			rs.close();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 比较两个成绩的大
	 * @param score
	 * @param recordScore
	 * @return
	 */
	public boolean isScoreMax( String score , String recordScore){
		String[] scorea = score.split("\\.");
		String[] recordScorea = recordScore.split("\\.");
		for( int i = 0 ; i < scorea.length ; i++ ){
			if( Integer.parseInt( scorea[i]) > Integer.parseInt( recordScorea[i])){
				return true;
			}else if( Integer.parseInt( scorea[i]) < Integer.parseInt( recordScorea[i])){
				return false;
			}else{
				continue;
			}
		}
		return false;
	}
	/**
	 * 比较两个成绩的小
	 * @param score
	 * @param recordScore
	 * @return
	 */
	public boolean isScoreMin( String score , String recordScore){
		String[] scorea = score.split("\\.");
		String[] recordScorea = recordScore.split("\\.");
		boolean flag = false;
		System.out.println( scorea.length);
		for( int i = 0 ; i < scorea.length ; i++ ){
			System.out.println( Integer.parseInt( scorea[i]) +"==="+ Integer.parseInt( recordScorea[i]));
			if( Integer.parseInt( scorea[i]) < Integer.parseInt( recordScorea[i])){
				flag = true;break;
			}else if( Integer.parseInt( scorea[i]) > Integer.parseInt( recordScorea[i])){
				flag =  false;
			}
		}
		return flag;
	}
	
}
