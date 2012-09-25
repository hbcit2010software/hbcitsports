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
/**
 * 简要说明：规程页面设计类
 * 
 * @author 于德水
 * 
 *  @version 1.00  2012/6/13  新建
 *
 */
public class GuiChengDao {
	protected final Logger log = Logger.getLogger(GuiChengDao.class.getName());
	/**
	 *  @author 于德水
	 *  
	 * 简要说明：记录是否存在的查询
	 * 
	 * 详细解释：
	 * @return ass（boolean）
	 * @version 1.00  2012/6/13  新建
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
    			PreparedStatement ps = conn.prepareStatement("select remarks_1, remarks_2, remarks_3, remarks_4 from t_official where sportsid = ?");
    			 ps.setInt(1,sportsId);
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRems1(rs.getString("remarks_1"));
                	admin.setRems2(rs.getString("remarks_2"));
                	admin.setRems3(rs.getString("remarks_3"));
                	admin.setRems4(rs.getString("remarks_4"));
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
	/*public ArrayList getSportName() {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select sportsname from t_sports"); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
    				admin.setSportsName(rs.getString("sportsname"));
    				air.add(admin);
                     
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }*/
	/**
	  @author 于德水
	 *  
	 * 简要说明:运动会id的查询
	 * 
	 * 详细解释：
	 * @return air（ArrayList）
	 * @version 1.00  2012/6/13  新建
	 */
	public void getSportId(String sportsname ) {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	PreparedStatement ps = conn.prepareStatement("select id from t_sports where sportsname = ?");
   			 	ps.setString(1,sportsname); 
                ResultSet rs = ps.executeQuery(); 
                //int c = rs.getMetaData().getColumnCount();
                if(rs.next()){
                	Admin admin = new Admin();
    				admin.setSportsid(rs.getInt("id"));
    				
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
  
    }
	/**
	 * 
	 * @author 于德水
	 * 
	 * 简要说明：插入记录
	 * 详细解释：
	 * @param action 参加办法
	 * @param conts 竞赛说明
	 * @param pionts 计分方法
	 * @param others 其他
	 * @return retuValue（boolean）
	 * @version 1.00  2012/6/13  新建
	 */
	public boolean add(int sportsid,String action, String conts, String pionts, String others) {
		boolean retuValue = false;
		DBConn db = new DBConn();
		try {
			
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement("insert into t_official (sportsid,remarks_1,remarks_2,remarks_3,remarks_4) values(?,?,?,?,?)");
			 ps.setInt(1,sportsid);
			 ps.setString(2,action);
			 ps.setString(3,conts);
			 ps.setString(4,pionts);
			 ps.setString(5,others);
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
	 * @author 于德水
	 * 
	 * 简要说明：更改记录
	 * 详细解释：
	 * @param action 参加办法
	 * @param conts 竞赛说明
	 * @param pionts 计分方法
	 * @param others 其他
	 * @return retuValue（boolean）
	 * @version 1.00  2012/6/13  新建
	 */
	public boolean update( int sportsid,String action, String conts, String pionts, String others) {
		boolean retuValue = false;
		DBConn db = new DBConn();
		try {
			
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement("update t_official set remarks_1 = ?,remarks_2 = ?,remarks_3 = ?, remarks_4 = ? where sportsid = ?");
			 ps.setString(1,action);
			 ps.setString(2,conts);
			 ps.setString(3,pionts);
			 ps.setString(4,others);
			 ps.setInt(5, sportsid);
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


