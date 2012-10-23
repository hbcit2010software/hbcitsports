package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Admin;
 
public class DaHuiJiLvDao {
	protected final Logger log = Logger.getLogger(DaHuiJiLvDao.class.getName());
	/**
	 * 记录是否存在的查询
	 * @param sportsid 运动会Id
	 * @return boolean
	 */
	public boolean getQuery( int sportsid) {
	 	boolean air = false;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	PreparedStatement ps = conn.prepareStatement("select * from t_official where sportsid = ?");
   			 	ps.setInt(1, sportsid);
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
  * 根据sportsId查询
  * @param sportsId 运动会Id
  * @return ArrayList
  * @author 于德水
  * @version 2012/6/24 新建
  */
	public ArrayList seleteBySportsId( int sportsId) {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select remarks_5, openingceremony, closingceremony from t_official where sportsid = ?");
    			 ps.setInt(1,sportsId);
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setMeetingJl(rs.getString("remarks_5"));
                	admin.setOpenDh(rs.getString("openingceremony"));
                	admin.setCloseDh(rs.getString("closingceremony"));
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
   * 插入记录
   * @author 于德水
   * @version 2012/6/20 新建
   */
 
public boolean add(int sportsid,String open, String close, String rule) {
	boolean retuValue = false;
	DBConn db = new DBConn();
	try {
		
		Connection conn = db.getConn();
		PreparedStatement ps = conn.prepareStatement("insert into t_official (sportsid,remarks_5,openingceremony,closingceremony) values(?,?,?,?)");
		 ps.setInt(1,sportsid);
		 ps.setString(2,rule);
		 ps.setString(3,open);
		 ps.setString(4,close);
 
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
 /**
  * 更改记录
  * @param sportsid 运动会Id
  * @param open 开幕式
  * @param close 闭幕式
  * @param rule 大会纪律
  * @return boolean
  * @author 于德水
  * @version 2012/6/20 新建
  */
public boolean update( int sportsid,String open, String close, String rule) {
	boolean retuValue = false;
	DBConn db = new DBConn();
	try {
		
		Connection conn = db.getConn();
		PreparedStatement ps = conn.prepareStatement("update t_official set remarks_5 = ?,openingceremony = ?,closingceremony = ? where sportsid = ?");
		 ps.setString(1,rule);
		 ps.setString(2,open);
		 ps.setString(3,close);		 
		 ps.setInt(4, sportsid);
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
