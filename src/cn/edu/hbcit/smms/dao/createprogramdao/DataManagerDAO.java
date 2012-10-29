
package cn.edu.hbcit.smms.dao.createprogramdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Group2itemPojo;
import cn.edu.hbcit.smms.pojo.T_finalitemPojo;
import cn.edu.hbcit.smms.pojo.T_groupPojo;
import cn.edu.hbcit.smms.pojo.Track1500PlayerPojo;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     生成秩序册
 * 子模块名称：   赛事分组
 *
 * 备注： 赛事分组需要的数据库操作类
 *
 * 修改历史：
 * 时间			                 版本号	姓名		修改内容
 * 2012006-13 15:33   0.1      韩鑫鹏          新建
 */
/**
 * @author 韩鑫鹏
 *
 */
public class DataManagerDAO {
	protected final Logger log = Logger.getLogger(DataManagerDAO.class.getName());
	DBConn db = new DBConn();
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	/**公用
	 * 根据sql语句添加数据
	 * @param sql  要执行的sql语句
	 * @return int
	 */
	public int addRecordBySql(String sql){
		
		int result = 0;
        try {
            Connection conn = db.getConn();
            if(conn != null){
                PreparedStatement statement = conn.prepareStatement(sql);
                result = statement.executeUpdate(); 
                statement.close();
            }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            	
            e.printStackTrace(); } 
            return result;
    }

	/**
	 * 根据sportsid删除分组情况
	 * @param sql
	 */
	public void deleteT_matchBySid(int sportsid){
			
			String sql = "DELETE FROM t_match WHERE finalitemid IN(SELECT id FROM " +
					"t_finalitem WHERE gp2itid IN(SELECT id FROM t_group2item WHERE " +
					"gp2spid IN(SELECT id FROM t_group2sports WHERE sportsid = ?)))";
	        try {
	            Connection conn = db.getConn();
	            if(conn != null){
	                PreparedStatement statement = conn.prepareStatement(sql);
	                statement.setInt(1, sportsid);
	                statement.executeUpdate(); 
	                log.debug("删除赛事分组信息");
	                statement.close();
	            }
	            db.freeConnection(conn);  
	            }catch (SQLException e) {                 
	            	
	            e.printStackTrace(); } 
	           
	    }
//************************************生成word**********************************************************	
    /**word
     * 根据运动会id查询最终项目表女子组信息  finalitemid(k) finalitemPojo(v)
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaGirl(int sportId){
    	HashMap flaGirl = new HashMap(); 
	    String sql = "SELECT * FROM t_finalitem WHERE finalitemtype!='2' and gp2itid IN (SELECT id FROM t_group2item" +
	    		" WHERE gp2spid IN (SELECT id FROM t_group2sports WHERE sportsid=? "
	            + "AND groupid IN(SELECT id FROM t_group WHERE groupname LIKE '%女%' " +
	            		"AND groupname NOT  LIKE '%男女%'))) ORDER BY finalitemname";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	    ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	T_finalitemPojo finalg = new T_finalitemPojo();
                	int temp = rs.getInt("id");
                	Integer flaid = new Integer(temp);
                	finalg.setId(temp);
                	finalg.setGp2itid(rs.getInt("gp2itid"));
                	finalg.setFinalitemname(rs.getString("finalitemname"));
                	finalg.setFinalitemtype(rs.getString("finalitemtype"));
                	finalg.setDate(rs.getString("date"));
                	finalg.setTime(rs.getString("time"));
                	finalg.setGroupnum(rs.getInt("groupnum"));
                	finalg.setPromotionnum(rs.getInt("promotionnum"));
                	flaGirl.put(flaid, finalg);
                    }
                rs.close();
                statement.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return flaGirl;
    }

    /**word
     * 根据运动会id查询最终项目表男子组信息
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaBoy(int sportId){
    	HashMap flaBoy = new HashMap(); 
	    String sql = "SELECT * FROM t_finalitem WHERE finalitemtype!='2' and gp2itid IN (SELECT id FROM t_group2item" +
	    		" WHERE gp2spid IN (SELECT id FROM t_group2sports WHERE sportsid=? "
	            + "AND groupid IN(SELECT id FROM t_group WHERE groupname LIKE '%男%'))) ORDER BY finalitemname";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	    ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	T_finalitemPojo finalg = new T_finalitemPojo();
                	int temp = rs.getInt("id");
                	Integer flaid = new Integer(temp);
                	finalg.setId(temp);
                	finalg.setGp2itid(rs.getInt("gp2itid"));
                	finalg.setFinalitemname(rs.getString("finalitemname"));
                	finalg.setFinalitemtype(rs.getString("finalitemtype"));
                	finalg.setDate(rs.getString("date"));
                	finalg.setTime(rs.getString("time"));
                	finalg.setGroupnum(rs.getInt("groupnum"));
                	finalg.setPromotionnum(rs.getInt("promotionnum"));
                	flaBoy.put(flaid, finalg);
                    }
                rs.close();
                statement.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return flaBoy;
    }

    /**word
     * 根据最终项目表的id查询项目类型
     * @param finalItemId
     * @return  String
     */
    public String selectItemTypeByFinId(int finalItemId){
    	String itemType = "";
	    String sql = "SELECT itemtype FROM t_item WHERE id IN( SELECT itemid FROM t_group2item WHERE" +
	    		" id IN ( SELECT gp2itid FROM t_finalitem WHERE id=? ))";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, finalItemId);
               rs = statement.executeQuery(); 
               while(rs.next()){
                	itemType = rs.getString(1);
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return itemType;
    }
   
    /**word
     * 根据最终项目表id径赛100类查询运动员编号(接力也可用此方法查询)
     * @param int finalItemId
     * @param HashMap players
     * @return
     */
    public ArrayList selectMatchByFinId(int finalItemId, HashMap players){
    	ArrayList pnums= new ArrayList();
	    String sql = "SELECT playerid FROM t_match WHERE finalitemid=? ORDER BY teamnum,runway ";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, finalItemId);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer playid = new Integer(rs.getInt(1));
            	   pnums.add(players.get(playid));
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return pnums;
    }

    /**word
     * 根据运动会的id查询运动员的id（k）与 号码（v）的关系
     * @param sportsId
     * @return  HashMap
     */
    public HashMap selectPlayersBySid(int sportsId){
    	HashMap players = new HashMap();
	    String sql = "SELECT * FROM t_player WHERE sp2dpid IN( SELECT id FROM t_sports2department WHERE sportsid=?)";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsId);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer playerId = new Integer(rs.getInt("id"));
            	   String playerNum = rs.getString("playernum");
            	   players.put(playerId, playerNum);
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return players;
    }
    
    /**word
     * 根据最终项目表id查询组别名称
     * @param finalItemId
     * @return int
     */
    public String selectGrNameByFid(int finalItemId){
    	String gname = "";
	    String sql = "SELECT groupname FROM t_group WHERE id IN ( SELECT groupid FROM t_group2sports " +
	    		     "WHERE id IN(SELECT gp2spid FROM t_group2item " +
	    			 "WHERE id IN (SELECT gp2itid FROM t_finalitem WHERE id = ?)))";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, finalItemId);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   gname = rs.getString("groupname");
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return gname;
    }
    
    /**word
     * 根据最终项目表的id查询田赛运动员的编号
     * @param int finalItemId
     * @param HashMap players
     * @return ArrayList
     */
    public ArrayList selectFilePnumByFid(int finalItemId, HashMap players){
    	ArrayList pnums = new ArrayList();
	    String sql = "SELECT playerid FROM t_match WHERE finalitemid=?";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, finalItemId);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer pid = new Integer(rs.getInt(1));
            	   pnums.add(players.get(pid));
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return pnums;
    }

    /**word
     * 根据最终项目表id径赛查询1500类运动员编号 
     * @param int finalItemId
     * @param HashMap players
     * @return
     */
    public ArrayList select1500ByFinId(int finalItemId, HashMap players){
    	ArrayList pnums= new ArrayList();
	    String sql = "SELECT playerid FROM t_match WHERE finalitemid=? ORDER BY teamnum";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, finalItemId);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer playid = new Integer(rs.getInt(1));
            	   pnums.add(players.get(playid));
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return pnums;
    }

    /**word
     * 根据运动会id查询部门的id（k） 名称（V）
     * @param int  sportsid
     * @return  HashMap
     */
    public HashMap selectDepartmentBySid(int sportsid){
    	HashMap department = new HashMap();
	    String sql = "SELECT id,departshortname FROM t_department WHERE id IN( SELECT departid FROM t_sports2department WHERE sportsid = ?)";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsid);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer depId = new Integer(rs.getInt(1));
            	   String shortName = rs.getString(2);
            	   department.put(depId, shortName);
                  }
               rs.close();
               statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return department;
    }
    
//********************************赛事查看********************************************
    /**look
     * 根据finalitemid查询每个项目的每组的人数
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList selectTrackGroup(int finalitemid){
    	ArrayList groupnum = new ArrayList();
	    String sql = "SELECT COUNT(t_match.teamnum) FROM t_match JOIN t_player ON t_match.playerid=t_player.id WHERE" +
	    		" t_match.finalitemid = ?  GROUP BY t_match.teamnum ORDER BY t_match.teamnum";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   Integer group = new Integer(rs.getInt(1));
             	   groupnum.add(group);
                   }
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return groupnum;
    }
    
    /**look
     * 根据finalitemid查询接力每个项目的每组的人数
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList selectRelGroup(int finalitemid){
    	ArrayList groupnum = new ArrayList();
	    String sql = "SELECT COUNT(t_match.teamnum) FROM t_match JOIN t_department ON t_match.playerid=t_department.id WHERE" +
	    		" t_match.finalitemid = ?  GROUP BY t_match.teamnum ORDER BY t_match.teamnum";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   Integer group = new Integer(rs.getInt(1));
             	   groupnum.add(group);
                   }
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return groupnum;
    }
    
    /**look
     * 根据finalitemid查询每个径赛项目的各组运动员  编号（ArrayList）     跑道号（ArrayList）
     * @param finalitemid
     * @return ArrayList       <String>  编号+跑道号
     */
    public ArrayList slectTrackInfo(int finalitemid){
    	//ArrayList trackInfo = new ArrayList();
    	ArrayList pn = new ArrayList();
    	//ArrayList rw = new ArrayList();
    	String sql = "SELECT t_player.playernum,t_match.runway FROM t_match JOIN t_player " +
    			"ON t_match.playerid = t_player.id WHERE t_match.finalitemid = ? ORDER BY t_match.teamnum,t_match.runway";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   String pnum = rs.getString(1);
             	   int runway = rs.getInt(2);
             	   pnum = pnum+"("+runway+")";
             	   pn.add(pnum);
             	   //Integer runway = new Integer(rs.getInt(2));
             	  // rw.add(runway);
                }
                //trackInfo.add(pn);
                //trackInfo.add(rw);
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }
    
    /**look
     * 根据finalitemid查询接力的各组部门名
     * @param finalitemid 
     * @return  ArrayList
     */
    public ArrayList slectRelayInfo(int finalitemid){
    	//ArrayList trackInfo = new ArrayList();
    	ArrayList pn = new ArrayList();
    	//ArrayList rw = new ArrayList();
    	String sql = "SELECT t_department.departshortname,t_match.runway FROM t_match JOIN t_department ON" +
    			" t_match.playerid = t_department.id WHERE t_match.finalitemid = ? ORDER BY teamnum,runway";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   String pnum = rs.getString(1);
             	   int runway = rs.getInt(2);
             	   pnum = pnum+"("+runway+")";
             	   pn.add(pnum);
             	   //Integer runway = new Integer(rs.getInt(2));
             	   //rw.add(runway);
                }
               // trackInfo.add(pn);
               // trackInfo.add(rw);
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }
    
    /**look
     * 根据finalitemid得到finalitemid与分组序号finalitemid + ";" + teamnum
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList slectFidRGnum(int finalitemid){
    	ArrayList trackInfo = new ArrayList();;
    	String sql = "SELECT DISTINCT(teamnum) FROM t_match WHERE finalitemid = ? ORDER BY teamnum";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   int pnum = rs.getInt(1);
             	   String temp = finalitemid + ";" + pnum;
             	   trackInfo.add(temp);
                }
                
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return trackInfo;
    }
    
    /**look
    * 根据最终项目名查询项目名称
    * @param finalitemid
    * @return
    */
    public String selectItnameByFid(int finalitemid){
    	String name = "";
    	String sql = "SELECT itemname FROM t_item WHERE id IN(SELECT itemid " +
    			     "FROM t_group2item WHERE id IN (SELECT gp2itid FROM t_finalitem WHERE id = ?))";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	name = rs.getString(1);   
                }
                
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return name;
    }
    
    /**
     * 根据finalitemid查询长跑类运动员信息
     * @param finalitemid
     * @return  ArrayList  编号+组号
     */
   /* public ArrayList slectTrack1500Ps(int finalitemid){
    	
    	ArrayList pn = new ArrayList();
    	
    	String sql = "SELECT t_player.playernum,t_match.teamnum FROM t_match JOIN t_player ON " +
    			"t_match.playerid = t_player.id WHERE t_match.finalitemid = ? ORDER BY t_match.teamnum";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   String pnum = rs.getString(1);
             	   int runway = rs.getInt(2);
             	   pnum = pnum+"("+runway+")";
             	   pn.add(pnum);
             	   
                }
                
                rs.close();
            }
    
    
    
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }*/
    
    public List slectTrack1500Ps(int finalitemid){
    	List list = new ArrayList();
		try {
			Connection conn = db.getConn();
			String select = " SELECT DISTINCT t_match.teamnum FROM t_match JOIN t_player ON  " +
					"t_match.playerid = t_player.id WHERE t_match.finalitemid = ? ORDER BY t_match.teamnum";
			//System.out.println(conn);
			ps = conn.prepareStatement(select);
			ps.setInt(1, finalitemid);
			rs = ps.executeQuery();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list.add(rs.getInt(1));
				
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	    return list;
    }
    
    
    public Map getTrack1500(int finalitemid) {
	    Map track1500Map = new HashMap();
		List list = new ArrayList();
		List slectTrack1500 = this.slectTrack1500Ps(finalitemid);
		track1500Map.put("track1500teamnum", slectTrack1500);
		try {
			StringBuffer sql = new StringBuffer();
			
			
			for( int j = 0; j < slectTrack1500.size(); j++){
			Connection conn = db.getConn();
			sql.append(" SELECT t_player.playernum, t_match.teamnum ");
			sql.append(" FROM t_match ");
			sql.append(" INNER JOIN t_player ON t_match.playerid = t_player.id ");
			
			sql.append(" WHERE t_match.finalitemid = ? AND t_match.teamnum='"+slectTrack1500.get(j)+"'");
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, finalitemid);
			rs = ps.executeQuery();
			sql.setLength(0);
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Track1500PlayerPojo trackp = new Track1500PlayerPojo();
				
				trackp.setPlayerNum(rs.getString(1));
				trackp.setTeamnum(rs.getInt(2));
				//System.out.println(rs.getString(1));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				list.add(trackp);
			}
			db.freeConnection(conn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		track1500Map.put("track1500List", list);
		return track1500Map;
	}
    
   
    /**look
     * 根据finalitemid查询田赛类运动员信息
     * @param finalitemid
     * @return  ArrayList  编号
     */
     public ArrayList slectFilePs(int finalitemid){
    	
    	ArrayList pn = new ArrayList();
    	
    	String sql = "SELECT t_player.playernum FROM t_player JOIN t_match ON " +
    			"t_match.playerid = t_player.id WHERE t_match.finalitemid = ?";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
             	   String pnum = rs.getString(1);
             	   pn.add(pnum);
             	   
                }
                
                rs.close();
                statement.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }
//************************************修改长跑分组数目******************************************    
     /**update1500
      * 根据finalitemid查询该届运动会报名长跑运动员的id（分组后）    以( id+"")方式存储
      * @param finalitemid
      * @return  ArrayList
      */
     public ArrayList selectPnumByFid(int finalitemid){
     	
     	ArrayList pn = new ArrayList();
     	
     	String sql = "SELECT playerid FROM t_match WHERE finalitemid = ?";
 	    try {
             Connection conn = db.getConn();
             if(conn != null){
             	 ResultSet rs = null;
                 PreparedStatement statement = conn.prepareStatement(sql);
                 statement.setInt(1, finalitemid);
                 rs = statement.executeQuery(); 
                 while(rs.next()){
              	   int pid = rs.getInt(1);
              	   String pnum = pid +"";
              	   pn.add(pnum);
              	   
                 }
                 
                 rs.close();
                 statement.close();
             }
         
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
             return pn;
     }
     
     /**update1500
      * 根据finalitemid删除长跑运动员的分组信息
      * @param finalitemid
      * @return
      */
     public void deletePnumByFid(int finalitemid){
      	
      	
      	
      	String sql = "delete FROM t_match WHERE finalitemid = ?";
  	    try {
              Connection conn = db.getConn();
              if(conn != null){
              	 ResultSet rs = null;
                  PreparedStatement statement = conn.prepareStatement(sql);
                  statement.setInt(1, finalitemid);
                  statement.executeUpdate();
                  statement.close();
              }
          
              db.freeConnection(conn);  
              }catch (SQLException e) {                 
              e.printStackTrace(); } 
             
      }
     

    
//*****************************赛事编排*********************************
     /**
      * 根据运动会id查询groupId+itemId
      * @param sportsId 
      * @param itemtype 
      * @return ArrayList   <String>groupId;itemId
      */
     public ArrayList selectItemBySid(int sportsId, String itemtype){
    	 ArrayList grp2item = new ArrayList();
    	 String sql = "SELECT t_group.id,t_item.id FROM t_group " +
    	 		"JOIN t_group2sports ON t_group.id = t_group2sports.groupid " +
    	 		"JOIN t_group2item ON t_group2sports.id = t_group2item.gp2spid " +
    	 		"JOIN t_item ON t_group2item.itemid = t_item.id " +
    	 		"WHERE t_group2sports.sportsid = ? AND t_item.itemtype = ? AND t_group2item.matchtype <> 0";
 		 try {
             Connection conn = db.getConn();
             if(conn != null){
             	 ResultSet rs = null;
                 PreparedStatement statement = conn.prepareStatement(sql);
                 statement.setInt(1, sportsId);
                 statement.setString(2, itemtype);
                 rs = statement.executeQuery(); 
                 while(rs.next()){
                 	String temp = "";
                 	temp = rs.getInt(1) + ";" + rs.getInt(2);
                 	grp2item.add(temp);
                 }
                 
                 rs.close();
                 statement.close();
                }
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
          log.debug("从数据库中取出的groupId+itemId" + grp2item);
    	 return grp2item;
    	 
     }
     
     /**
      * 根据sportsId查询项目的id与name
      * @param sportsId
      * @return HashMap<String,String>
      */
     public HashMap selectItemId2nameBySid(int sportsId){
    	 HashMap itemId2name = new HashMap();
    	 String sql = "SELECT * FROM t_item WHERE id IN" +
    	 		"(SELECT itemid FROM t_group2item WHERE gp2spid " +
    	 		"IN (SELECT id FROM t_group2sports WHERE sportsid = ?)) ";
 		 try {
             Connection conn = db.getConn();
             if(conn != null){
             	 ResultSet rs = null;
                 PreparedStatement statement = conn.prepareStatement(sql);
                 statement.setInt(1, sportsId);
                 rs = statement.executeQuery(); 
                 while(rs.next()){
                 	String itemId = (rs.getInt(1)+"").trim();
                 	String itemName = rs.getString(2);
                 	itemId2name.put(itemId, itemName);
                    }
                 rs.close();
                 statement.close();
                }
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
    	 return itemId2name;
     }
     
     /**
      * 根据sportsId查询数字+运动员id，运动员组别id+所报项目id对照HashMap
      * @param sportsId
      * @return HashMap
      */
     public HashMap selectplayer2itemBySid(int sportsId){
    	 HashMap itemId2name = new HashMap();
    	 String sql = "SELECT id,groupid,registitem FROM t_player " +
    	 		"WHERE sp2dpid IN" +
    	 		"(SELECT id FROM t_sports2department WHERE sportsid = ?)";
 		 try {
             Connection conn = db.getConn();
             if(conn != null){
             	 ResultSet rs = null;
                 PreparedStatement statement = conn.prepareStatement(sql);
                 statement.setInt(1, sportsId);
                 rs = statement.executeQuery(); 
                 while(rs.next()){
                	int pid = rs.getInt(1);
                	int pgid = rs.getInt(2); 
                 	String registitem = rs.getString(3);
                 	if (registitem == null || registitem.equals("")){
                 		continue;
                 	}
                 	
                 	if (registitem.indexOf(";") >= 0){
                 		String[] pitem = null;
                 		pitem = registitem.split(";");
                 		for (int i = 0; i < pitem.length; i++){
                     		String pidString = (i + ";" + pid).trim();
                     		String g2i = (pgid + ";" + pitem[i]).trim();
                     		itemId2name.put(pidString,g2i);
                     	}
                 	}else{
                 		itemId2name.put(1+";"+pid, pgid + ";" + registitem);
                 	}
                 	
                 	
                 }
                 rs.close();
                 statement.close();
                }
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
    	 return itemId2name;
     }
     
     /**
 	 * 根据运动会id查询每个项目各系的限报人数
 	 * @param 运动会id
 	 * @return int 
 	 */
 	public int selectPerDep(int sportsid){
 		
 		int confineNumber = 0;
 		String sql = "SELECT perdepartment FROM t_rule WHERE sportsid=?";
         try {
             Connection conn = db.getConn();
             if(conn != null){
             	
                 PreparedStatement statement = conn.prepareStatement(sql); 
                 statement.setInt(1, sportsid);
                 ResultSet rs = statement.executeQuery(); 
                 while(rs.next()){
                 	confineNumber = rs.getInt(1);
                     }
                 //db.closeRsAll(rs,conn);
                 rs.close();
                 }  
             
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
             return confineNumber;
     }
 	
 	/**
 	 * 根据sportsId查询部门id集合
 	 * @param sportsId
 	 * @return  ArrayList
 	 */
 	public ArrayList selectDepidBySid(int sportsId){
   	 ArrayList depid = new ArrayList();
   	 String sql = "SELECT id FROM t_department WHERE id IN " +
   	 		"(SELECT departid FROM t_sports2department WHERE sportsid = ?)";
		 try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportsId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	String did = (rs.getInt(1)+"").trim();
                	depid.add(did);
                   }
                
                rs.close();
                statement.close();
               }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
   	 return depid;
    }
 	
 	/**
 	 * 根据sportsId查询
 	 * @param sportsId运动员id，部门id对照 HashMap
 	 * @return HashMap
 	 */
 	public HashMap slectPlaid2DepidySid(int sportsId){
   	 HashMap plaId2depid = new HashMap();
   	 String sql = "SELECT t_player.id,t_sports2department.departid " +
   	 		"FROM t_player JOIN t_sports2department ON " +
   	 		"t_player.sp2dpid = t_sports2department.id WHERE t_sports2department.sportsid = ?";
		 try {
            Connection conn = db.getConn();
            if(conn != null){
            	 ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportsId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	String pid = (rs.getInt(1) + "").trim();
                	String did = (rs.getInt(2) + "").trim();
                	plaId2depid.put(pid, did);
                }
                rs.close();
                statement.close();
               }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
   	 return plaId2depid;
    }
 	
 	/**
 	 * 根据sportsId查询组别id+项目id，最终项目id对应HashMap
 	 * @param sportsId
 	 * @return HashMap
 	 */
 	public HashMap slectItem2flaBySid(int sportsId){
 	   	 HashMap plaId2depid = new HashMap();
 	   	 String sql = "SELECT t_finalitem.id,t_group2item.itemid,t_group2sports.groupid " +
 	   	 		"FROM t_finalitem" +
 	   	 		" JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id " +
 	   	 		"JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id " +
 	   	 		"WHERE t_group2sports.sportsid=? and t_finalitem.finalitemtype!='2'";
 			 try {
 	            Connection conn = db.getConn();
 	            if(conn != null){
 	            	 ResultSet rs = null;
 	                PreparedStatement statement = conn.prepareStatement(sql);
 	                statement.setInt(1, sportsId);
 	                rs = statement.executeQuery(); 
 	                while(rs.next()){
 	                	String fid = (rs.getInt(1) + "").trim();
 	                	String g2id = (rs.getInt(3) + ";" + rs.getInt(2)).trim();
 	                	plaId2depid.put(g2id, fid);
 	                }
 	                rs.close();
 	                statement.close();
 	               }
 	            db.freeConnection(conn);  
 	            }catch (SQLException e) {                 
 	            e.printStackTrace(); } 
 	   	 return plaId2depid;
 	    }
    
 	/**
     * 根据sportsId查询组别的id与name
     * @param sportsId
     * @return HashMap<String,String>
     */
    public HashMap slectGroupId2nameBySid(int sportsId){
   	    HashMap itemId2name = new HashMap();
   	    String sql = "SELECT * FROM t_group WHERE id IN" +
   	    		"(SELECT groupid FROM t_group2sports WHERE sportsid = ?)";
		try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportsId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	String itemId = (rs.getInt("id")+"").trim();
                	String itemName = rs.getString("groupname");
                	itemId2name.put(itemId, itemName);
                   }
                rs.close();
                statement.close();
               }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
   	    return itemId2name;
    }
    
    /**
 	 * 根据sportsId查询学生部门id集合
 	 * @param sportsId
 	 * @return  ArrayList
 	 */
 	public ArrayList slectStuDepidBySid(int sportsId){
 		ArrayList depid = new ArrayList();
 		String sql = "SELECT id FROM t_department WHERE id IN " +
   	 		"(SELECT departid FROM t_sports2department WHERE sportsid = ?) AND departtype = 1";
		try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportsId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	String did = (rs.getInt(1)+"").trim();
                	depid.add(did);
                   }
                
                rs.close();
                statement.close();
               }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
         return depid;
    }
 	
 	/**
	 * 根据sql语句修改分组情况
	 * @param sql
	 */
	public void updateGroupNumBySql(String sql){
		String[] flag = sql.split("#");
		
        try {
            Connection conn = db.getConn();
            if(conn != null){
                
                for (int i = 0; i < flag.length; i++){
                	String temp = flag[i];
                	PreparedStatement statement = conn.prepareStatement(temp);
                	statement.executeUpdate(); 
                	statement.close();
                }
                
            }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            	
            e.printStackTrace(); } 
            
	}

	/**
	 * 检查是否已经分过组，若以分过返回“true” 否则返回“flase”
	 * @param sportsId
	 * @return String 
	 */
	public String checkGroup(int sportsId){
		String flag = "flase";
		String sql = "SELECT id FROM t_match WHERE finalitemid IN(SELECT id FROM t_finalitem WHERE " +
				"gp2itid IN(SELECT id FROM t_group2item WHERE gp2spid IN" +
				"(SELECT id FROM t_group2sports WHERE sportsid = ?)))" ;
        try {
            Connection conn = db.getConn();
            if(conn != null){
                
            	    ResultSet rs = null;
                	PreparedStatement statement = conn.prepareStatement(sql);
                	statement.setInt(1, sportsId);
                	rs = statement.executeQuery();
                	while(rs.next()){
                		flag = "true";
                		break;
                	}
                	statement.close();
                
                
            }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            	
            e.printStackTrace(); } 
           return flag;
	}
}



