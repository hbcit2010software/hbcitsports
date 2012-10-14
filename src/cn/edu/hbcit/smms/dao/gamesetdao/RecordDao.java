package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.databasedao.DBTest;
import cn.edu.hbcit.smms.pojo.Admin;

public class RecordDao {
	protected final Logger log = Logger.getLogger(RecordDao.class.getName());
	/**
	 * 查询所有记录
	 * @return air（ArrayList）
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public ArrayList getQuery() {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select * from t_record"); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
    				admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setItemName(getItemName( rs.getInt("itemid")));
    				air.add(admin);
                     
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }
	/**
	 * 查询所有项目名称
	 * @return ArrayList
	 * @author 于德水
	 * @version 2012/6/24 新建
	 */
	public ArrayList getItemRecord() {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select id,itemname from t_item"); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setIteId(rs.getInt("id"));
    				admin.setItemName(rs.getString("itemname"));
    				air.add(admin);
                     
                }
                rs.close();
                statement.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }
	
	/**
	 * 记录是否存在的查询
	 * @param itemId 项目Id
	 * @param recTime 创记录时间
	 * @return boolean
	 * @author 于德水
	 * @version 2012/6/20 新建
	 */
	public boolean getQuery( int itemId, String recTime) {
	 	boolean air = false;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	PreparedStatement ps = conn.prepareStatement("select * from t_record where itemid = ? and recordtime = ?");
   			 	ps.setInt(1, itemId);
   			 	ps.setString(2, recTime);
                ResultSet rs = ps.executeQuery(); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	 air = true;
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    
}
	/**
	 * 根据项目名称查询
	 * @param itemId 项目ID 
	 * @return air （ArrayList）
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public ArrayList seleteByName( int itemId) {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select * from t_record where itemid = ?");
    			 ps.setInt(1,itemId);
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setIteId(rs.getInt("itemid"));
    				air.add(admin);
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }

	/**
	 * 获取项目Id
	 * @param itemName 项目名称
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public int getItemId( String itemName) {
		int id = 0;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select id from t_item  where itemname like ?");
    			 ps.setString(1,"%"+itemName+"%");
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	//Admin admin = new Admin();
    				 id = rs.getInt("id");
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
        return id;
    }
	/**
	 * 获取项目名称
	 * @param itemId 项目Id
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public String getItemName( int itemId) {
		String name = null;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select itemname from t_item  where id = ?");
    			 ps.setInt(1,itemId);
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	//Admin admin = new Admin();
    				name = rs.getString("itemname");
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
        return name;
    }
	/**
	 * 根据学生记录id的查询
	 * @param recordId	学生记录id
	 * @return ArrayList
	 * @author 于德水
	 * @version 2012/6/23
	 */
	 
	public ArrayList seleteByRecordId( int recordId) {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	String sql = "select * from t_record where id = ?";
    			PreparedStatement ps = conn.prepareStatement(sql);
    			 ps.setInt(1,recordId);
    			 
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRecordId(rs.getInt("id"));
                	admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setItemName(getItemName( rs.getInt("itemid")));
    				air.add(admin);
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }
	
	/**
	 * 根据项目名称及破记录时间的查询
	 * @param itemId 项目Id
	 * @param recordTime 破记录时间
	 * @return  ArrayList
	 * @author 于德水
	 * @version 2012/6/23
	 */
	public ArrayList seleteByNameTime( int itemId, String recordTime, boolean flag) {
		log.debug("itemId:"+itemId+",recordTime:"+recordTime+",flag:"+flag);
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
		Connection conn = db.getConn();
		String sql = "";
		if(flag == false){
			sql = "select * from t_record where itemid = ?  ";
		}else{
			sql = "select * from t_record where itemid = ? and recordtime =? ";
		}
        try {
            
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement(sql);
    			if(recordTime.equals("")){
    				ps.setInt(1,itemId);
    			}else{
    			 ps.setInt(1,itemId);
    			 ps.setString(2,recordTime);
    			}
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
    			 int count = 0;
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRecordId(rs.getInt("id"));
                	admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setItemName(getItemName( rs.getInt("itemid")));
    				air.add(admin);
    				count++;
                }
                log.debug("seleteByNameTime查询个数："+count);
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
      
    }
	
	/**
	 * 添加新记录
	 * @param plaName 运动员名
	 * @param sportsName1 运动会名
	 * @param recTime  记录时间
	 * @param recLevel  级别
	 * @param plaSex  运动员性别
	 * @param sor    成绩
	 * @param depName  系别
	 * @param iteId    项目ID
	 * @return retuValue 
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public boolean addRecord(String plaName,String sportsName1,String recTime,String recLevel,int plaSex,String sor, String depName, int itemId) {
		
		boolean retuValue = false;
		DBConn db = new DBConn();
		
			
		try {
			 
			Connection conn = db.getConn();
			String sql = "insert into t_record (itemid ,sex, score, playername, "+
			"departname, sportsname, recordtime, recordlevel) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			 
			 ps.setInt(1, itemId);
			 System.out.println("hhhhhhhhhhhhhhhhh"+itemId);
			 ps.setInt(2,plaSex);
			 ps.setString(3,sor);
			 ps.setString(4,plaName);
			 ps.setString(5,depName);
			 ps.setString(6,sportsName1);
			 ps.setString(7,recTime);
			 ps.setString(8,recLevel);
			int s = ps.executeUpdate();
			if(s==1)
			{
			retuValue = true;
			}
			ps.close();
			db.freeConnection(conn);
		} catch (SQLException e) {                 
            e.printStackTrace();       
        }
		
		return retuValue;
	}
	
	/**
	 * 修改记录
	 * @param plaName 运动员名
	 * @param newIteId 项目ID
	 * @param recordId  学生记录ID
	 * @param sportsName1 运动会名
	 * @param newRecTime 记录时间
	 * @param recLevel  级别
	 * @param plaSex   运动员性别
	 * @param sor    成绩
	 * @param depName 系部
	 * @return boolean 
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public boolean updateRecord(String plaName, String sportsName1,String newRecTime,String recLevel,int plaSex,String sor, String depName, int recordId)
	{
		
		boolean retuValue = false;
		DBConn db = new DBConn();
		
		try {
			
			Connection conn = db.getConn();
			String sql = "update t_record  set sex = ?, score = ?,"+
			" playername = ?, departname = ?, sportsname = ?, recordtime = ?, recordlevel = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			 
			 ps.setInt(1,plaSex);
			 ps.setString(2,sor);
			 ps.setString(3,plaName);
			 ps.setString(4,depName);
			 ps.setString(5,sportsName1);
			 ps.setString(6,newRecTime);
			 ps.setString(7,recLevel);
			 ps.setInt(8,recordId);

			int s = ps.executeUpdate();
			if(s==1)
			{
			retuValue = true;
			}
			ps.close();
			db.freeConnection(conn);
		} catch (SQLException e) {                 
            e.printStackTrace();       
        }   
		return retuValue;
	}
	
	/**
	 * 删除记录
	 * @param recordtime 记录时间
	 * @param recordId 学生记录Id
	 * @return boolean
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public boolean deleteRecord( int recordId) {
		boolean retuValue = false;
		DBConn db = new DBConn();
		try {
			
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement("delete from t_record  where id = ?");
			 
			 ps.setInt(1,recordId);
			 int s = ps.executeUpdate();
			 if(s == 1){
			retuValue = true;
			 }
			 ps.close();
			db.freeConnection(conn);
			 
		} catch (SQLException e) {                 
            e.printStackTrace();       
        }   
		return retuValue;
	}
	
}
