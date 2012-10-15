package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.GameManagePoJo;

/**
 *  
 * 赛中管理--------->成绩管理--------->数据库操作类
 * 
 * @author 刘然
 * @version 1.00  2012/06/13 新規作成<br>
 *
 * 修改bug  刘然        2012/09/09
 */
public class GameManageDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	protected final Logger log = Logger.getLogger(GameManageDao.class.getName());
	LoginDAO ld = new LoginDAO();
	int sportsid = ld.selectCurrentSportsId();
	/**
     * 获取本届运动会组别
     * @return groupList
     * 刘然     修改bug     2012-9-6  17:20
     */
	public ArrayList<GameManagePoJo> getGroup()
	{
		DBConn db = new DBConn();
		
		ArrayList<GameManagePoJo> groupList = new ArrayList<GameManagePoJo>();
		try{
			conn = db.getConn();
			if(!conn.equals(""))
			{
				pstmt = conn.prepareStatement(" SELECT * FROM t_group where id in (select groupid from t_group2sports where sportsid=?)");
				pstmt.setInt(1, sportsid);
				rs = pstmt.executeQuery();
				int c = rs.getMetaData().getColumnCount();
				while(rs.next())
				{
					GameManagePoJo gm = new GameManagePoJo();
					gm.setGroupid(rs.getInt("id"));
					gm.setGroupname(rs.getString("groupname"));
					groupList.add(gm);
					for(int i=1;i<=c;i++){
                    	log.debug(rs.getObject(i));
				}}				
			}			   
				db.freeConnection(conn);
		}catch(Exception e){
			e.printStackTrace();
		}
		 return groupList;
	}
	
	/**
	 * 获取项目类型
	 * @return itemType
	 */	
	public String getItemType(int finalItemId)
	{
		DBConn db = new DBConn();
		String itemType = "";
		try
		{
			conn = db.getConn();
			pstmt = conn.prepareStatement("SELECT itemtype FROM t_item WHERE id IN (SELECT itemid FROM t_group2item WHERE id IN (SELECT gp2itid FROM t_finalitem WHERE id=?))");
			pstmt.setInt(1, finalItemId);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
					itemType = rs.getString(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return itemType;
	}
	
	
	/**
     * 获取本届运动会拆分后的项目名称
     * @return finalItemList
     * 刘然     修改bug     2012-9-6  17:28
     */
	public ArrayList<GameManagePoJo> getFinalItem(int groupid)
	{
		LoginDAO ld = new LoginDAO();
		int sportsid = ld.selectCurrentSportsId();
		
		DBConn db = new DBConn();
		ArrayList<GameManagePoJo> finalItemList = new ArrayList<GameManagePoJo>();
		try{
			conn = db.getConn();
			if(!conn.equals(""))
			{
				pstmt = conn.prepareStatement("SELECT * FROM t_finalitem where gp2itid IN (SELECT id FROM t_group2item where gp2spid=(SELECT id FROM t_group2sports where groupid=? " +
						"and sportsid=?))");
				pstmt.setInt(1, groupid);
				pstmt.setInt(2, sportsid);
				rs = pstmt.executeQuery();
				int c = rs.getMetaData().getColumnCount();
				System.out.println("rs.getRow()=============="+rs.getRow());
				while(rs.next())
				{
					GameManagePoJo gm = new GameManagePoJo();
					gm.setItemid(rs.getInt("id"));
					gm.setItemname(rs.getString("finalitemname"));
					System.out.println("dafadfasf"+rs.getInt("id"));
					System.out.println("asdfasdfas"+rs.getString("finalitemname"));
					finalItemList.add(gm);
					for(int i=1;i<=c;i++){
                    	log.debug(rs.getObject(i));
				}}				
			}			   
				db.freeConnection(conn);
		}catch(Exception e){
			e.printStackTrace();
		}		
		 return finalItemList;
	}
	
	/**
     * 根据项目ID和项目类型获取运动员信息名单
     * @return athleteList
     */
	public ArrayList<GameManagePoJo> getAthleteList(int finalItemId,String itemType)
	{
		DBConn db = new DBConn();
		ArrayList<GameManagePoJo> athleteList = new ArrayList<GameManagePoJo>();
		try{
			conn = db.getConn();
			String sql1 = "SELECT t_player.playernum,t_player.playername,t_player.playersex,t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match "+ 
            "JOIN  t_player ON t_player.id=t_match.playerid "+ 
            "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "+
            "JOIN t_department ON t_department.id=t_sports2department.departid "+
            "WHERE t_match.finalitemid=? AND t_sports2department.sportsid=? "+ 
            "ORDER BY t_match.score+0 DESC ";
			String sql2 = "SELECT t_player.playernum,t_player.playername,t_player.playersex,t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match "+ 
            "JOIN  t_player ON t_player.id=t_match.playerid "+ 
            "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "+
            "JOIN t_department ON t_department.id=t_sports2department.departid "+
            "WHERE t_match.finalitemid=? AND t_sports2department.sportsid=? "+ 
            "ORDER BY t_match.score+0 ASC ";
			String sql3 = "SELECT t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match  " +
					"JOIN t_department ON t_department.id=t_match.playerid " +
					"JOIN t_finalitem ON t_finalitem.id=t_match.finalitemid " +
					"WHERE t_match.finalitemid=? AND t_finalitem.sportsid=? " +
					" ORDER BY t_match.score+0 ASC ";
			if(!conn.equals(" "))
			{
				System.out.println("getAthleteList:"+itemType+"=============");
				if(itemType.equals("2"))
				{
					pstmt = conn.prepareStatement(sql1);					
				}
				else if(itemType.equals("1"))
				{
					pstmt = conn.prepareStatement(sql2);
				}
				else{
					pstmt = conn.prepareStatement(sql3);
				}
				
				pstmt.setInt(1, finalItemId);
				pstmt.setInt(2, sportsid);
				rs = pstmt.executeQuery();
				int c = rs.getMetaData().getColumnCount();
				System.out.println("rs.getRow()=============="+rs.getRow());
				
				while(rs.next())
				   {					
					GameManagePoJo gm = new GameManagePoJo();
					if(!itemType.equals("3")){
					gm.setPlayernum(rs.getString("playernum"));
					gm.setPlayername(rs.getString("playername"));
					if(rs.getInt("playersex") == 0)
					{
						gm.setPlayersex("女");
					}
					else
					{ 
						gm.setPlayersex("男");
					}
					}
					gm.setMatchid(rs.getInt("id"));					
					
					gm.setDepartname(rs.getString("departname"));
					if(rs.getInt("foul") == 0)
					{
						gm.setFoul("没有违纪");
					}
					else{
						gm.setFoul("已经违纪");
					}
					gm.setScore(rs.getString("score"));
					if(rs.getInt("recordlevel") == 0)
					{
						gm.setRecordlevel("破院记录");
					}
					else if(rs.getInt("recordlevel") == 1)
					{
						gm.setRecordlevel("破省记录");
					}
					else
					{
						
							gm.setRecordlevel("未破纪录");
					}
					gm.setMatchid(rs.getInt("id"));
					athleteList.add(gm);					
					for(int i=1;i<=c;i++){
                    	log.debug(rs.getObject(i));
				     }
			       }								   
			}	db.freeConnection(conn);
		}catch(Exception e){
			e.printStackTrace();
		}		
		 return athleteList;
	}
	
	
	/**
     * 根据赛事ID获取运动员信息名单
     * @return athleteList
     */
	public ArrayList<GameManagePoJo> getAth(int matchid,int finalItemId)
	{
		System.out.println("matchid==="+matchid);
		DBConn db = new DBConn();
		ArrayList<GameManagePoJo> athleteList = new ArrayList<GameManagePoJo>();
		try{
			conn = db.getConn();
			String sql = "SELECT t_player.playernum,t_player.playername,t_player.playersex,t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match  " +
					"JOIN  t_player ON t_player.id=t_match.playerid  " +
					"JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid  " +
					"JOIN t_department ON t_department.id=t_sports2department.departid  " +
					"WHERE t_match.id=? AND t_sports2department.sportsid=? ";
			String sql1 = "SELECT  t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match " +
					" JOIN t_department ON t_department.id=t_match.playerid  " +
					"JOIN t_sports2department ON  t_sports2department.departid=t_department.id " +
					" WHERE t_match.id=? AND t_sports2department.sportsid=?  ORDER BY t_match.score+0 ASC ";
				String itemtype = this.getItemType(finalItemId);
				if(itemtype.equals("3")){
					pstmt = conn.prepareStatement(sql1);	
				}else{
					pstmt = conn.prepareStatement(sql);	
				}
				pstmt.setInt(1, matchid);
				pstmt.setInt(2, sportsid );
				rs = pstmt.executeQuery();
				int c = rs.getMetaData().getColumnCount();
				/*System.out.println("rs.getRow()=============="+rs.getRow());*/
				
				while(rs.next())
				   {					
					GameManagePoJo gm = new GameManagePoJo();
					if(!itemtype.equals("3")){
					gm.setPlayernum(rs.getString("playernum"));
					gm.setPlayername(rs.getString("playername"));
									
					if(rs.getInt("playersex") == 0)
					{
						gm.setPlayersex("女");
					}
					else
					{ 
						gm.setPlayersex("男");
					}}
					gm.setMatchid(rs.getInt("id"));	
					gm.setDepartname(rs.getString("departname"));
					gm.setFoul(rs.getInt("foul")+"");
					gm.setScore(rs.getString("score"));
					gm.setRecordlevel(rs.getInt("recordlevel")+"");
					gm.setMatchid(rs.getInt("id"));
					athleteList.add(gm);					
					for(int i=1;i<=c;i++){
                    	log.debug(rs.getObject(i));
				     }						   
			}	db.freeConnection(conn);
		}catch(Exception e){
			e.printStackTrace();
		}		
		 return athleteList;
	}
    /**
     * 删除运动员根据运动员编号
     * @return flag
     *//*
	public boolean deletePlayer(int playerNum)
	{
		boolean flag = false;
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			if(!conn.equals(" "))
			{
			pstmt = conn.prepareStatement("DELETE FROM t_player where playernum=?");
			pstmt.setInt(1, playerNum);
			int i = pstmt.executeUpdate();
			System.out.println("删除影响行数i="+i);
			if(i>0)
			{
				flag = true;
			}
			}}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}	*/
	
	/**
     * 获取运动员基本信息
     * @return flag
     */
	public ArrayList<GameManagePoJo> getPlayerList(int playerNum,int finalItemId)
	{
		DBConn db = new DBConn();
		ArrayList<GameManagePoJo> playerList = new ArrayList<GameManagePoJo>();
		try{
			conn = db.getConn();
			if(!conn.equals(" "))
			{
				pstmt = conn.prepareStatement("SELECT t_player.playernum,t_player.playername,t_player.playersex,t_match.score,t_match.foul," +
						"t_match.id,t_match.recordlevel,t_department.departname,t_finalitem.finalitemname,t_finalitem.id  FROM t_player "+   
                        "JOIN  t_match ON t_match.playerid=t_player.id "+
                        "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "+
                        "JOIN t_department ON t_department.id=t_sports2department.departid "+
                        "JOIN t_finalitem ON t_finalitem.id=t_match.finalitemid "+
                        "WHERE t_player.playernum=? AND t_finalitem.id=?");
				pstmt.setInt(1, playerNum);
				pstmt.setInt(2, finalItemId);
				rs = pstmt.executeQuery();
				int c = rs.getMetaData().getColumnCount();
				while(rs.next())
				   {					
					GameManagePoJo gm = new GameManagePoJo();
					gm.setPlayernum(rs.getString("playernum"));
					gm.setPlayername(rs.getString("playername"));
					gm.setMatchid(rs.getInt("id"));
					gm.setFinalitemname(rs.getString("finalitemname"));
					if(rs.getInt("playersex") == 0)
					{
						gm.setPlayersex("女");
					}
					else
					{ 
						gm.setPlayersex("男");
					}
					gm.setDepartname(rs.getString("departname"));
					gm.setFoul2(rs.getInt("foul"));
					gm.setScore(rs.getString("score"));
					gm.setRecordlevel2(rs.getInt("recordlevel"));
					playerList.add(gm);					
					for(int i=1;i<=c;i++){
                    	log.debug(rs.getObject(i));
				     }
			       }								   
			}	db.freeConnection(conn);
		}catch(Exception e){
			e.printStackTrace();
		}		
		 return playerList;
	}
	
	/**
	 * 
	 * @param matchid
	 * @return  获取项目名称
	 */
	public String finalItemName(int matchid){
		String finalItemName = "";
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			String sql = "SELECT t_finalitem.finalitemname FROM t_finalitem WHERE id IN (SELECT t_match.finalitemid FROM t_match WHERE id=?)" +
					" AND t_finalitem.sportsid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, matchid);
			pstmt.setInt(2, sportsid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				finalItemName = rs.getString(1);
				System.out.println("finalItemName========="+finalItemName);
			}
		}catch (Exception e) {
			log.debug(e);
		}
		return finalItemName;
	}
	
	/**
	 * 
	 * @param matchid
	 * @return  获取组别名称
	 */
	public String groupName(int matchid){
		String groupName = "";
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			String sql = "SELECT t_group.groupname FROM t_group WHERE id=(SELECT t_player.groupid FROM t_player WHERE t_player.id=(SELECT t_match.playerid FROM t_match WHERE t_match.id=?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, matchid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				groupName = rs.getString(1);
				System.out.println("groupName========="+groupName);
			}
		}catch (Exception e) {
			log.debug(e);
		}
		return groupName;
	}
	
	/**
	 * 根据参数删除指定t_position中数据
	 * @param finalitemname
	 * @param sportsid
	 * @param groupname
	 */
	public void deletePositionPlayer(String finalitemname,int sportsid,String groupname){
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			String sql = "DELETE FROM t_position WHERE (t_position.finalitemid = (SELECT id FROM t_finalitem WHERE t_finalitem.finalitemname=? AND t_finalitem.sportsid=?)) AND  " +
					"(t_position.playerid IN (SELECT t_player.id FROM t_player WHERE t_player.groupid=(SELECT t_group.id FROM t_group WHERE groupname=?)))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			pstmt.setInt(2, sportsid);
			pstmt.setString(3, groupname);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 更新运动员成绩基本信息
     * @return flag
     */
	public boolean updateMatch(int matchid,String score,int foul,int recordlevel)
	{
		boolean flag = false;
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			if(!conn.equals(" "))
			{
			pstmt = conn.prepareStatement("UPDATE  t_match set score=?,foul=?,recordlevel=? WHERE id=?");
			pstmt.setString(1, score);
			pstmt.setInt(2, foul);
			pstmt.setInt(3, recordlevel);
			pstmt.setInt(4, matchid);
			int i = pstmt.executeUpdate();
			System.out.println("删除影响行数i="+i);
			if(i>0)
			{
				flag = true;
			}
			}}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}	
	
	public void deleteRecordPlayer(String finalitemname,int matchid){
		DBConn db = new DBConn();
		try{
			conn = db.getConn();
			String sql = "DELETE FROM t_record WHERE t_record.itemid=(SELECT t_group2item.itemid FROM t_group2item WHERE id=(SELECT gp2itid FROM t_finalitem WHERE t_finalitem.finalitemname=?)) AND " +
					"t_record.playername=(SELECT playername FROM t_player WHERE id=(SELECT t_match.playerid FROM t_match WHERE id=?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			pstmt.setInt(2, matchid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 根据项目打印本项目的word文档
     * @return flag
     */
	public ArrayList<GameManagePoJo> createWordOfAthleteInf(int finalItemId,String itemType)
	{
		    DBConn db = new DBConn();
		    ArrayList<GameManagePoJo> athleteList = new ArrayList<GameManagePoJo>();
		    try{
		    	conn = db.getConn();
				String sql1 = "SELECT t_player.playernum,t_player.playername,t_player.playersex,t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match  "+ 
	            "JOIN  t_player ON t_player.id=t_match.playerid "+ 
	            "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "+
	            "JOIN t_department ON t_department.id=t_sports2department.departid "+
	            "JOIN t_finalitem ON t_finalitem.id=t_match.finalitemid "+
	            "WHERE t_match.finalitemid=? AND t_sports2department.sportsid=? "+ 
	            "ORDER BY t_match.score+0 DESC";
				String sql2 = "SELECT t_player.playernum,t_player.playername,t_player.playersex,t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match "+ 
	            "JOIN  t_player ON t_player.id=t_match.playerid "+ 
	            "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "+
	            "JOIN t_department ON t_department.id=t_sports2department.departid "+
	            "WHERE t_match.finalitemid=? AND t_sports2department.sportsid=? "+ 
	            "ORDER BY t_match.score+0 ASC";
				String sql3 = "SELECT  t_match.score,t_match.foul,t_match.id,t_match.recordlevel,t_department.departname  FROM t_match  " +
				" JOIN t_department ON t_department.id= t_match.playerid " +
				" JOIN t_finalitem ON t_finalitem.id=t_match.finalitemid " +
				" WHERE t_match.finalitemid=? AND t_finalitem.sportsid=? "+ 
                "ORDER BY t_match.score+0 ASC ";
				if(!conn.equals(" "))
				{
					System.out.println(itemType+"=============");
					if(itemType.equals("2"))
					{
						pstmt = conn.prepareStatement(sql1);					
					}
					else if(itemType.equals("1"))
					{
						pstmt = conn.prepareStatement(sql2);
					}
					else{
						pstmt = conn.prepareStatement(sql3);
					}
					
					
					pstmt.setInt(1, finalItemId);
					pstmt.setInt(2, sportsid);
					rs = pstmt.executeQuery();
					int c = rs.getMetaData().getColumnCount();				
				while(rs.next())
				   {					
					GameManagePoJo gm = new GameManagePoJo();
					if(!itemType.equals("3")){
						gm.setPlayernum(rs.getString("playernum"));
						gm.setPlayername(rs.getString("playername"));
						if(rs.getInt("playersex") == 0)
						{
							gm.setPlayersex("女");
						}
						else
						{ 
							gm.setPlayersex("男");
						}
						}
					
					gm.setScore(rs.getString("score"));
					if(rs.getInt("recordlevel") == 0)
					{
						gm.setRecordlevel("未破纪录");
					}
					else if(rs.getInt("recordlevel") == 1)
					{
						gm.setRecordlevel("破院记录");
					}
					else
					{
						
							gm.setRecordlevel("破省记录");
					}
					gm.setFoul(rs.getInt("foul")+"");
					gm.setDepartname(rs.getString("departname"));
					athleteList.add(gm);					
					for(int i=1;i<=c;i++){
                    	log.debug(rs.getObject(i));
				     }
			       }								   
				db.freeConnection(conn);
		}}catch(Exception e){
			e.printStackTrace();
		}		
		 return athleteList;
	}
	
	/**
	 * 
	* 方法说明 获取当前届次
	* @param finalItemId
	* @return fileName
	 */
	public String createWordOfSportsName(int finalItemId)
	{
		    DBConn db = new DBConn();
		    String fileName = "";
		    try{
		    	conn = db.getConn();
				if(!conn.equals(""))
				{
					pstmt = conn.prepareStatement("SELECT t_sports.sportsname,t_finalitem.finalitemname,t_group.groupname FROM t_finalitem " +
							"JOIN t_group2item ON t_group2item.id=t_finalitem.gp2itid " +
							"JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid "+
                            "JOIN t_group ON t_group.id=t_group2sports.groupid "+
                            "JOIN t_sports ON t_sports.id=t_group2sports.sportsid "+
                            "WHERE t_finalitem.id=? "+
                            "ORDER BY t_sports.sportsend+0 DESC LIMIT 1");
					pstmt.setInt(1, finalItemId);
					rs = pstmt.executeQuery();
					if(rs.next())
					{					
					    fileName = rs.getString("sportsname") + "-" + rs.getString("groupname") + "-" + rs.getString("finalitemname");
					}
		         }
				}catch(Exception e){
			e.printStackTrace();
		}	
		return fileName;
	}
}

