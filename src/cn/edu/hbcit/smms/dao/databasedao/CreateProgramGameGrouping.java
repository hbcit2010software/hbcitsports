
package cn.edu.hbcit.smms.dao.databasedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.pojo.Group2itemPojo;
import cn.edu.hbcit.smms.pojo.T_finalitemPojo;
import cn.edu.hbcit.smms.pojo.T_groupPojo;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     生成秩序册
 * 子模块名称：   赛事分组
 *
 * 备注： 赛事分组及生成秩序册需要的数据库操作类
 *
 * 修改历史：
 * 时间			                 版本号	姓名		修改内容
 * 2012006-13 15:33   0.1      韩鑫鹏          新建
 */
/**
 * @author 韩鑫鹏
 *
 */
public class CreateProgramGameGrouping {
	protected final Logger log = Logger.getLogger(CreateProgramGameGrouping.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 根据运动会id查询部门的总数
	 * @param sportsid
	 * @return int
	 */
	public int selectDepartmentSum(int sportsid){
		
		int tieSum = 0;
		String sql = "SELECT COUNT(*) FROM t_group2sports WHERE sportsid=?";
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	
                PreparedStatement statement = conn.prepareStatement(sql); 
                statement.setInt(1, sportsid);
                ResultSet rs = statement.executeQuery(); 
                while(rs.next()){
                	tieSum = rs.getInt(1);
                    }
                rs.close();
                //db.closeRsAll(rs,conn);
                }  
            db.freeConnection(conn); 
            
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return tieSum;
    }
	
	/**
	 * 根据   运动会id 部门总数    查询部门和运动会联系id 
	 * @param sportsid  运动会id
	 * @param departmentCount  部门数目
	 * @return
	 */
	public int[] selectSp2dpid(int sportsid, int departmentCount){
		
		int[] sp2dpid = new int[departmentCount];
		String sql = "SELECT id FROM t_group2sports WHERE sportsid=?";
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	
                PreparedStatement statement = conn.prepareStatement(sql); 
                statement.setInt(1, sportsid);
                ResultSet rs = statement.executeQuery(); 
                while(rs.next()){
                	for (int i = 0; i < departmentCount; i++){
                		sp2dpid[i] = rs.getInt(1);
                	}
                }
                rs.close();
            }  
            db.freeConnection(conn);  
        }catch (SQLException e) {                 
            e.printStackTrace(); } 
        return sp2dpid;
    }
	
	/**
	 * 根据运动会id查询每个项目各系的限报人数
	 * @param 运动会id
	 * @return int 
	 */
	public int selectPerDepartment(int sportsid){
		
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
            }
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            	
            e.printStackTrace(); } 
            return result;
    }
	
	/**
	 * 根据     运动会的id、项目类型         查询该届运动会的所有能报的项目的    组别与运动会联系表id 项目id
	 * @param sportId  运动会id
	 * @param itemtype  项目类型(田赛和径赛还有接力)
	 * @return   
	 */
	public ArrayList selectGroupItem(int sportId, String itemtype){
		
		ArrayList group2itemArrayList = new ArrayList();
		String sql = "SELECT * FROM t_group2item WHERE gp2spid IN (SELECT id FROM  t_group2sports WHERE sportsid=?) AND itemid IN (SELECT id FROM t_item WHERE itemtype=?)";
		try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                statement.setString(2, itemtype);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	Group2itemPojo g2i = new Group2itemPojo();
                	g2i.setId(rs.getInt("id"));
                	g2i.setGp2spid(rs.getInt("gp2spid"));
                	g2i.setItemid(rs.getInt("itemid"));
                	group2itemArrayList.add(g2i);
                    }
                rs.close();
               }  
            //conn.close();
            
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
		return group2itemArrayList;
	}

/**
 * 根据    运动会id  查询    组别id  组别与运动会联系表id  
 * @param sportId    运动会id 
 * @return  HashMap
 */
    public HashMap selectGroupItem(int sportId){
		
	    HashMap g2sHashMap = new HashMap();
		String sql = "SELECT * FROM t_group2sports WHERE sportsid=?";
		try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                rs = statement.executeQuery(); 
                while(rs.next()){
                	Integer groupid = new Integer(rs.getInt("groupid"));
                	Integer id = new Integer(rs.getInt("id"));
                	g2sHashMap.put(groupid, id);
                    }
                rs.close();
               }  
            //conn.close();
            
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
		return g2sHashMap;
	}

    /**
 * 根据运动会的id    查询运动员(以数字+运动员id的形式储存)、  组别与运动会表的id和项目id的结合  HashMap
 * @param sportId
 * @param g2sHashMap  
 * @return
 */
public HashMap selectPlarerIdAndG2IId(int sportId, HashMap g2sHashMap){
	int i = 0;
    HashMap p2gHashMap = new HashMap(); //运动员、   组别运动会联系表id和项目结合    的id HashMap
	String sql = "SELECT id,groupid,registitem FROM t_player WHERE groupid IN(SELECT groupid FROM t_group2sports WHERE sportsid=?)";
	try {
        Connection conn = db.getConn();
        if(conn != null){
        	ResultSet rs = null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, sportId);
            rs = statement.executeQuery(); 
            while(rs.next()){
            	Integer playerid = new Integer(rs.getInt("id"));
            	Integer groupid = new Integer(rs.getInt("groupid"));
            	int g2sid = Integer.parseInt(g2sHashMap.get(groupid).toString());
            	String items = rs.getString("registitem");
            	String[] itemid = items.split(";");
            	for (int j = 0; j < itemid.length; j++){
            		String id = i + ";" + playerid;
            		String item = itemid[j];
                	String registitem = g2sid + ";" + item;
                	p2gHashMap.put(id, registitem);
                	i++;
            	}
            	
             }
            rs.close();
           }  
        //conn.close();
        
        db.freeConnection(conn);  
        }catch (SQLException e) {                 
        e.printStackTrace(); } 
	return p2gHashMap;
}

/**
 * 根据运动会的id查询       运动员id、 部门与运动会联系表id   HashMap<运动员id,部门与运动会联系表id>
 * @param sportId 运动会id
 * @return  HashMap
 */
public HashMap selectPlayerIdD2SId(int sportId){
    HashMap player2d2sIDHashMap = new HashMap(); //运动员、  部门与运动会联系表    的id HashMap
	String sql = "SELECT id,sp2dpid FROM t_player WHERE sp2dpid IN(SELECT id FROM t_sports2department WHERE sportsid=?)";
	try {
        Connection conn = db.getConn();
        if(conn != null){
        	ResultSet rs = null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, sportId);
            rs = statement.executeQuery(); 
            while(rs.next()){
            	Integer playerid = new Integer(rs.getInt("id"));
            	Integer sp2dpid = new Integer(rs.getInt("sp2dpid"));
            	player2d2sIDHashMap.put(playerid, sp2dpid);
                }
            rs.close();
           }  
        //conn.close();
        
        db.freeConnection(conn);  
        }catch (SQLException e) {                 
        e.printStackTrace(); } 
	return player2d2sIDHashMap;
}

    /**
     * 根据运动会的id查询      最终项目表id（v）  与       组别与项目联系表的id（k）    的对应关系    
     * @param sportId
     * @return  HashMap
     */
    public HashMap selectG2itidVSd2s2gID(int sportId){
        HashMap g2itidVSd2s2gID = new HashMap(); //最终项目表id  与       组别与项目联系表的id    的对应关系  
	    String sql = "SELECT id,gp2itid FROM t_finalitem WHERE gp2itid IN(SELECT id FROM t_group2item WHERE gp2spid";
	    sql = sql + " IN(SELECT id FROM t_group2sports WHERE sportsid=?))";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	    ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                rs = statement.executeQuery(); 
                while(rs.next()){
            	    Integer id = new Integer(rs.getInt("id"));
            	    Integer gp2itid = new Integer(rs.getInt("gp2itid"));
            	    g2itidVSd2s2gID.put(gp2itid, id);
                    }
                rs.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return g2itidVSd2s2gID;
    }
    
    /**
     * 根据运动会的id查询   组别与项目联系表的id<v> 与     组别与运动会联系表的id与项目id联合起来 <k>      的关系
     * @param sportId
     * @return HashMap
     */
    public HashMap selectGp2itid(int sportId){
        HashMap g2itidVSd2s2gID = new HashMap(); //最终项目表id  与       组别与项目联系表的id    的对应关系  
	    String sql = "SELECT id,gp2spid,itemid FROM t_group2item WHERE gp2spid IN(SELECT id FROM t_group2sports WHERE sportsid=?)";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	    ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                rs = statement.executeQuery(); 
                while(rs.next()){
            	    Integer id = new Integer(rs.getInt("id"));
            	    Integer gp2spid = new Integer(rs.getInt("gp2spid"));
            	    Integer itemid = new Integer(rs.getInt("itemid"));
            	    String temp = gp2spid + ";" + itemid;
            	    g2itidVSd2s2gID.put(temp, id);
                    }
                rs.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return g2itidVSd2s2gID;
    }

    /**
     * 根据运动会的id 查询该届运动会的    itemid(k)  itemName(v)
     * @param sportId
     * @return  HashMap
     */
    public HashMap selectT_item(int sportId){
        HashMap t_item = new HashMap(); 
	    String sql = "SELECT id,itemname FROM t_item WHERE id IN (SELECT itemid FROM t_group2item WHERE gp2spid IN(SELECT id FROM t_group2sports WHERE sportsid=?))";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	    ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, sportId);
                rs = statement.executeQuery(); 
                while(rs.next()){
            	    Integer id = new Integer(rs.getInt("id"));
            	    String temp = (String)rs.getString("itemname");
            	    t_item.put(id, temp);
                    }
                rs.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return t_item;
    }
    
    /**
     * 根据运动会id查询最终项目表女子组信息  finalitemid(k) finalitemPojo(v)
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaGirl(int sportId){
    	HashMap flaGirl = new HashMap(); 
	    String sql = "SELECT * FROM t_finalitem WHERE gp2itid IN (SELECT id FROM t_group2item" +
	    		" WHERE gp2spid IN (SELECT id FROM t_group2sports WHERE sportsid=? "
	            + "AND groupid IN(SELECT id FROM t_group WHERE groupname LIKE '%女%' " +
	            		"AND groupname NOT  LIKE '%男女%')))";
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
                	finalg.setGp2itid(rs.getInt("groupnum"));
                	finalg.setPromotionnum(rs.getInt("promotionnum"));
                	flaGirl.put(flaid, finalg);
                    }
                rs.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return flaGirl;
    }

    /**
     * 根据运动会id查询最终项目表男子组信息
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaBoy(int sportId){
    	HashMap flaBoy = new HashMap(); 
	    String sql = "SELECT * FROM t_finalitem WHERE gp2itid IN (SELECT id FROM t_group2item" +
	    		" WHERE gp2spid IN (SELECT id FROM t_group2sports WHERE sportsid=? "
	            + "AND groupid IN(SELECT id FROM t_group WHERE groupname LIKE '%男%')))";
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
                	finalg.setGp2itid(rs.getInt("groupnum"));
                	finalg.setPromotionnum(rs.getInt("promotionnum"));
                	flaBoy.put(flaid, finalg);
                    }
                rs.close();
               }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return flaBoy;
    }

    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return itemType;
    }
   
    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return pnums;
    }

    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return players;
    }
    
    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return gname;
    }
    
    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return pnums;
    }

    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return pnums;
    }

    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return department;
    }
    
    /**
     * 根据运动会id查询T_group表信息 
     * @param sportsid 
     * @return  ArrayList
     */
    public ArrayList selectT_group(int sportsid){
    	ArrayList groupinfo = new ArrayList();
	    String sql = "SELECT * FROM t_group WHERE id IN (SELECT groupid FROM t_group2sports WHERE sportsid=?)";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsid);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   T_groupPojo tgp = new T_groupPojo();
            	   tgp.setId(rs.getInt("id"));
            	   tgp.setGroupname(rs.getString("groupname"));
            	   tgp.setGrouptype(rs.getInt("grouptype"));
            	   groupinfo.add(tgp);
                  }
               rs.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return groupinfo;
    }
    
    /**
     *根据运动会id查询组别与运动会表id（k）与 组别类型（v）
     * @param sportsid
     * @return
     */
    public HashMap selectGtype(int sportsid){
    	HashMap groupinfo = new HashMap();
	    String sql = "SELECT t_group2sports.id,grouptype FROM t_group JOIN t_group2sports ON" +
	    		" t_group.id=t_group2sports.groupid WHERE t_group2sports.sportsid=?";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsid);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer id = new Integer(rs.getInt(1));
            	   Integer grouptype = new Integer(rs.getInt(2));
            	   groupinfo.put(id, grouptype);
                  }
               rs.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return groupinfo;
    }
    
    /**
     * 根据运动会id查询部门id（k）与部门类型（v）的关系
     * @param sportsid
     * @return HashMap
     */
    public HashMap selectT_epartment(int sportsid){
    	HashMap groupinfo = new HashMap();
	    String sql = "SELECT id,departtype FROM t_department WHERE id IN (SELECT departid FROM t_sports2department WHERE sportsid=?)";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsid);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer id = new Integer(rs.getInt(1));
            	   Integer grouptype = new Integer(rs.getInt(2));
            	   groupinfo.put(id, grouptype);
                  }
               rs.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return groupinfo;
    }
    
    /**
     * 根据运动会的id查询运动员id（k）与部门id（v）的关系
     * @param sportsid
     * @return HashMap
     */
    public HashMap selectPid2DidBySid(int sportsid){
    	HashMap p2d = new HashMap();
	    String sql = "SELECT t_player.id,t_department.id FROM t_player JOIN t_sports2department ON t_player.sp2dpid = t_player.id " +
                      "JOIN t_department ON t_sports2department.departid = t_department.id " +
                      "WHERE t_sports2department.sportsid = ?";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsid);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer pid = new Integer(rs.getInt(1));
            	   Integer did = new Integer(rs.getInt(2));
            	   p2d.put(pid, did);
                  }
               rs.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return p2d;
    }
    
    /**
     * 根据运动会id查询最终项目表id（v） 与  组别与运动会联系表id_项目表id的关系（k）
     * @param sportsid
     * @return HashMap
     */
    public HashMap selectFidRg2s2IiD(int sportsid){
    	HashMap p2d = new HashMap();
	    String sql = "SELECT t_finalitem.id,t_group2item.gp2spid,t_group2item.itemid FROM t_finalitem " +
	    		"JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id " +
	    		"JOIN t_group2sports ON t_group2sports.id = t_group2item.gp2spid WHERE t_group2sports.sportsid = ?";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
        	   ResultSet rs = null;
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, sportsid);
               rs = statement.executeQuery(); 
               while(rs.next()){
            	   Integer fid = new Integer(rs.getInt(1));
            	   int gid = rs.getInt(2);
            	   int iid = rs.getInt(3);
            	   String g_iid = gid + ";" + iid;
            	   p2d.put(g_iid, fid);
                  }
               rs.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
	    return p2d;
    }
    
    /**
     * 根据最终项目id 更改最终项目表里面的groupnum
     * @param groupnum int
     * @param finalitemid int
     */
    public void uodateGroupnumByFid(int groupnum, int finalitemid){
	    String sql = "UPDATE t_finalitem SET groupnum = ? WHERE id = ?";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setInt(1, groupnum);
               statement.setInt(2, finalitemid);
               statement.executeUpdate();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
    }
   
    /**
     * 根据finalitemid查询每个项目的每组的人数
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList selectTrackGroup(int finalitemid){
    	ArrayList groupnum = new ArrayList();
	    String sql = "SELECT COUNT(teamnum) FROM t_match WHERE finalitemid = ?  GROUP BY teamnum ORDER BY teamnum";
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return groupnum;
    }
    
    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }
    
    /**
     * 根据finalitemid查询接力的各组部门名
     * @param finalitemid 
     * @return  ArrayList
     */
    public ArrayList slectRelayInfo(int finalitemid){
    	//ArrayList trackInfo = new ArrayList();
    	ArrayList pn = new ArrayList();
    	//ArrayList rw = new ArrayList();
    	String sql = "SELECT t_department.departshortname,t_match.runway FROM t_match JOIN t_department ON" +
    			" t_match.playerid = t_department.id WHERE t_match.finalitemid = 1 ORDER BY teamnum,runway";
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }
    
    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return trackInfo;
    }
    
    /**
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
    public ArrayList slectTrack1500Ps(int finalitemid){
    	
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
    }
   
    /**
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
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            return pn;
    }
     
     /**
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
             }
         
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
             return pn;
     }
     
     /**
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
                  
              }
          
              db.freeConnection(conn);  
              }catch (SQLException e) {                 
              e.printStackTrace(); } 
             
      }
}



