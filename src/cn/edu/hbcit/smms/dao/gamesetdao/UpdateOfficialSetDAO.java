package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.databasedao.DBTest;
/**
 * 修改运动会工作人员类
 * @author 姚瑶
 *
 */
public class UpdateOfficialSetDAO {
	private PreparedStatement psmt;
	protected final Logger log = Logger.getLogger(DBTest.class.getName());
	/**
	 * 更改大会主席团人员
	 * @return
	 */
	public boolean updatePresidiumBySportsid(int sportsid,String presidium,String org_committee_1,String org_committee_2,
			String org_committee_3,String secretariat_1,String secretariat_2,String secretariat_3,
			String secretariat_4,String secretariat_5,String secretariat_6,String secretariat_7,
			String arbitration){
		DBConn db = new DBConn();
		boolean retuValue = false;
		int flag=0;
		String sql ="update t_official set presidium='"+presidium+"', org_committee_1='"+org_committee_1+"'," +
		"org_committee_2='"+org_committee_2+"',org_committee_3='"+org_committee_3+"',secretariat_1='"+secretariat_1+"'," +
		"secretariat_2='"+secretariat_2+"',secretariat_3='"+secretariat_3+"',secretariat_4='"+secretariat_4+"',secretariat_5='"+secretariat_5+"'," +
		"secretariat_6='"+secretariat_6+"',secretariat_7='"+secretariat_7+"',arbitration='"+arbitration+"'"+"where sportsid='"+sportsid+"'";
				try {
			          Connection conn = db.getConn();
			          if(conn != null){
			        	  psmt=conn.prepareStatement(sql);
					      flag=psmt.executeUpdate();
			    			if(flag>0){
			    			retuValue = true;
			    			}
		         }
		         db.freeConnection(conn);
		     } catch (SQLException e) {                 
		         e.printStackTrace();       
		     }       
		   return retuValue;
		     }
	/**
	 * 
	 *更改径赛裁判员类
	 * @return
	 */
	public boolean updateTrackjudgeBySportsid(int sportsid,String chiefjudge_1, String chiefjudge_2,String trackjudge, 
			String trackjudge_rollcall_1,String trackjudge_rollcall_2,String trackjudge_rollcall_3,
			String startingpoint_1,String startingpoint_2,String startingpoint_3,String timejudge_1,
			String timejudge_2,String timejudge_3,String endpoint_1,String endpoint_2,String endpoint_3,
			String endpoint_4,String endpoint_5) {
		DBConn db = new DBConn();
		boolean retuValue = false;
		int flag=0;
		String sql ="update t_official set chiefjudge_1='"+chiefjudge_1+"', chiefjudge_2='"+chiefjudge_2+"'," +
		"trackjudge='"+trackjudge+"',trackjudge_rollcall_1='"+trackjudge_rollcall_1+"',trackjudge_rollcall_2='"+trackjudge_rollcall_2+"'," +
		"trackjudge_rollcall_3='"+trackjudge_rollcall_3+"',startingpoint_1='"+startingpoint_1+"',startingpoint_2='"+startingpoint_2+"',startingpoint_3='"+startingpoint_3+"'," +
		"timejudge_1='"+timejudge_1+"',timejudge_2='"+timejudge_2+"',timejudge_3='"+timejudge_3+"',endpoint_1='"+endpoint_1+"',endpoint_2='"+endpoint_2+"'," +
				"endpoint_3='"+endpoint_3+"',endpoint_4='"+endpoint_4+"',endpoint_5='"+endpoint_5+"'"+"where sportsid='"+sportsid+"'";
		 try {
	          Connection conn = db.getConn();
	          if(conn != null){
	        	  psmt=conn.prepareStatement(sql);
			      flag=psmt.executeUpdate();
	    			if(flag>0){
	    			retuValue = true;
	    			}
         }
	     psmt.close();
         db.freeConnection(conn);
     } catch (SQLException e) {                 
         e.printStackTrace();       
     }       
   return retuValue;
     }
	/**
	 * 更改田赛裁判员类
	 * @return
	 */
	public boolean updateFieldjudgeBySportsid(int sportsid,String fieldjudge,String fieldjudge_1,String fieldjudge_2,String fieldjudge_3,
			String fieldjudge_4,String fieldjudge_5,String fieldjudge_6){
		    DBConn db = new DBConn();
		    boolean retuValue = false;
		    int flag=0;
		    String sql ="update t_official set fieldjudge='"+fieldjudge+"', fieldjudge_1='"+fieldjudge_1+"'," +
		"fieldjudge_2='"+fieldjudge_2+"',fieldjudge_3='"+fieldjudge_3+"',fieldjudge_4='"+fieldjudge_4+"'," +
		"fieldjudge_5='"+fieldjudge_5+"',fieldjudge_6='"+fieldjudge_6+"'"+"where sportsid='"+sportsid+"'";
		    	try {
			          Connection conn = db.getConn();
			          if(conn != null){
			        	  psmt=conn.prepareStatement(sql);
					      flag=psmt.executeUpdate();
			    			if(flag>0){
			    			retuValue = true;
			    			}
		         }
			     psmt.close();
		         db.freeConnection(conn);
		     } catch (SQLException e) {                 
		         e.printStackTrace();       
		     }       
		   return retuValue;
		     } 
	
}
