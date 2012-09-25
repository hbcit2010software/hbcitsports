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
import cn.edu.hbcit.smms.pojo.Department;
import cn.edu.hbcit.smms.pojo.Fieldjudge;
import cn.edu.hbcit.smms.pojo.Item;
import cn.edu.hbcit.smms.pojo.Official;
import cn.edu.hbcit.smms.pojo.Stujudge;

/**
 * 运动会工作人员添加类
 * @author 姚瑶
 *
 */

public class OfficialSetDAO {
	private PreparedStatement psmt;
	protected final Logger log = Logger.getLogger(OfficialSetDAO.class.getName());
	
	/**
	 * 根据sql语句添加数据
	 * @param sql  要执行的sql语句
	 * @return int
	 */
	public int addRecordBySql(String sql){
		DBConn db = new DBConn();
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
	 * 判断运动会是否存在
	 */
	public boolean spoid(int sportsid) {
		ArrayList list = new ArrayList();
		DBConn db = new DBConn();
		boolean flag=false;
		String sql = "SELECT sportsid FROM t_official WHERE sportsid=? ";
		try {
			 Connection conn = db.getConn();
			  if(conn != null){
					 psmt = conn.prepareStatement(sql);
				     psmt.setInt(1, sportsid);
				     ResultSet rs = psmt.executeQuery();
		         while (rs.next()) {
		        	 Official official =new Official();
		        	 official.setSportsid(rs.getInt("sportsid"));
		        	 list.add(official);
		 			}  
		         if(list.size()==0){
		        	flag= true;
		         }else{
		        	 flag=false;
		         }
				 rs.close();
		        }
			    psmt.close();
		        db.freeConnection(conn);
		    } catch (SQLException e) {                 
		        e.printStackTrace();       
		    }
				return flag;       
		  }
	/**
	 * 
	 * 大会主席团添加类
	 * @return
	 */
	public boolean addPresidium(int sportsid,String presidium,String org_committee_1,String org_committee_2,
			String org_committee_3,String secretariat_1,String secretariat_2,String secretariat_3,
			String secretariat_4,String secretariat_5,String secretariat_6,String secretariat_7,
			String arbitration){
		DBConn db = new DBConn();
		boolean retuValue = false;
		int flag=0;
		 try {
	          Connection conn = db.getConn();
	          if(conn != null){
	        	    String sql ="INSERT INTO t_official(presidium,org_committee_1,org_committee_2," +
	        	    		"org_committee_3,secretariat_1,secretariat_2,secretariat_3,secretariat_4," +
	        	    		"secretariat_5,secretariat_6,secretariat_7,arbitration)values(?,?,?,?,?,?,?,?,?,?,?,?) where sportsid=?";
	        	    psmt=conn.prepareStatement(sql);
	        	    //System.out.println(sql);
	        	    psmt.setInt(1, sportsid);
	        	    psmt.setString(2, presidium);
	        	   	psmt.setString(3, org_committee_1);
	    			psmt.setString(4, org_committee_2);
	    			psmt.setString(5, org_committee_3);
	    			psmt.setString(6, secretariat_1);
	    			psmt.setString(7, secretariat_2);
	    			psmt.setString(8, secretariat_3);
	    			psmt.setString(9, secretariat_4);
	    			psmt.setString(10, secretariat_5);
	    			psmt.setString(11, secretariat_6);
	    			psmt.setString(12, secretariat_7);
	    			psmt.setString(13, arbitration);
	    			flag=psmt.executeUpdate();
	    			if(flag>0){
	    			retuValue = true;
	    			}
	    			conn.close();
	    			//System.out.println(sql);
	}	
	          psmt.close();
	          db.freeConnection(conn);
	     } catch (SQLException e) {                 
	         e.printStackTrace();       
	     }       
	   return retuValue;
	     }
	/**
	 * 运动会径赛裁判员添加类
	 * @return
	 */
	public boolean addTrackjudge(int sportsid,String chiefjudge_1, String chiefjudge_2,String trackjudge, 
			String trackjudge_rollcall_1,String trackjudge_rollcall_2,String trackjudge_rollcall_3,
			String startingpoint_1,String startingpoint_2,String startingpoint_3,String timejudge_1,
			String timejudge_2,String timejudge_3,String endpoint_1,String endpoint_2,String endpoint_3,
			String endpoint_4,String endpoint_5) {
		DBConn db = new DBConn();
		boolean retuValue = false;
		int flag=0;
		 try {
	          Connection conn = db.getConn();
	          if(conn != null){
	        	    String sql = "INSERT INTO t_official(chiefjudge_1, chiefjudge_2,trackjudge,trackjudge_rollcall_1," +
	        	    		"trackjudge_rollcall_2,trackjudge_rollcall_3,startingpoint_1,startingpoint_2," +
	        	    		"startingpoint_3,timejudge_1,timejudge_2,timejudge_3,endpoint_1,endpoint_2," +
	        	    		"endpoint_3,endpoint_4,endpoint_5)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)where sportsid=?";
	                psmt=conn.prepareStatement(sql);
	                psmt.setInt(1, sportsid);
	                psmt.setString(2, chiefjudge_1);
	    			psmt.setString(3, chiefjudge_2);
	    			psmt.setString(4, trackjudge);
	    			psmt.setString(5, trackjudge_rollcall_1);
	    			psmt.setString(6, trackjudge_rollcall_2);
	    			psmt.setString(7, trackjudge_rollcall_3);
	    			psmt.setString(8, startingpoint_1);
	    			psmt.setString(9, startingpoint_2);
	    			psmt.setString(10, startingpoint_3);
	    			psmt.setString(11, timejudge_1);
	    			psmt.setString(12, timejudge_2);
	    			psmt.setString(13, timejudge_3);
	    			psmt.setString(14, endpoint_1);
	    			psmt.setString(15, endpoint_2);
	    			psmt.setString(16, endpoint_3);
	    			psmt.setString(17, endpoint_4);
	    			psmt.setString(18, endpoint_5);
	    			flag=psmt.executeUpdate();
	    			if(flag>0){
	    			retuValue = true;
	    			}
	    			conn.close();
	    		}
	     psmt.close();
         db.freeConnection(conn);
     } catch (SQLException e) {                 
         e.printStackTrace();       
     }       
   return retuValue;
     }
	/**
	 * 运动会田赛裁判员添加类
	 * @return
	 */
	public boolean addFieldjudge(int sportsid,String fieldjudge,String fieldjudge_1,String fieldjudge_2,String fieldjudge_3,
			String fieldjudge_4,String fieldjudge_5,String fieldjudge_6){
		    DBConn db = new DBConn();
		    boolean retuValue = false;
		    int flag=0;
		 try {
	          Connection conn = db.getConn();
	           if(conn != null){
	        	    String sql = "INSERT INTO t_official(fieldjudge,fieldjudge_1,fieldjudge_2,fieldjudge_3,fieldjudge_4," +
	        	    		"fieldjudge_5,fieldjudge_6)values(?,?,?,?,?,?,?) where sportsid=?";
	        	    psmt=conn.prepareStatement(sql);
	        	    psmt.setInt(1, sportsid);
	        	    psmt.setString(2, fieldjudge);
	    			psmt.setString(3, fieldjudge_1);
	    			psmt.setString(4, fieldjudge_2);
	    			psmt.setString(5, fieldjudge_3);
	    			psmt.setString(6, fieldjudge_4);
	    			psmt.setString(7, fieldjudge_5);
	    			psmt.setString(8, fieldjudge_6);
	    			flag=psmt.executeUpdate();
	    			if(flag>0){
	    			retuValue = true;
	    			}
	    			conn.close();
	    		}
	     psmt.close();      
         db.freeConnection(conn);
     } catch (SQLException e) {                 
         e.printStackTrace();       
     }       
   return retuValue;
     }
	/**
	 * 根据sportsid查询所有田赛项目
	 * @param sportsid
	 * @return
	 */

public ArrayList selectAllItem(int sportsid) {
		ArrayList list = new ArrayList();
		ArrayList itemName = new ArrayList();
		DBConn db = new DBConn();
		String sql = "SELECT t_item.itemname,t_group2item.id FROM t_item JOIN t_group2item " +
				"ON t_item.id = t_group2item.itemid JOIN t_group2sports ON t_group2item.gp2spid " +
				"= t_group2sports.id WHERE t_group2sports.sportsid=? AND t_item.itemtype=2";
		try {
			 Connection conn = db.getConn();
			 if(conn != null){
				 psmt = conn.prepareStatement(sql);
			     psmt.setInt(1, sportsid);
			     ResultSet rs = psmt.executeQuery();
	         while (rs.next()) {
	        	 String itemname = rs.getString(1);
	        	 log.debug("itemnameaaaaaaaaaaaaaaa"+itemname);
	        	 boolean flag = false;
	        	 for (int i = 0; i < itemName.size(); i++){
	        		 String item = itemName.get(i).toString();
	        		 if (itemname.trim().equals(item.trim())){
	        			 flag = true;
	        			 break;
	        		 }
	        	}
	        	if (flag == true){
	        		continue;
	        	}else{
	        		itemName.add(itemname);
	        		 log.debug("itemName"+itemName);
	        	}
	        	 String[] temp = new String[2];
	 			 temp[0] = itemname;
	 			 temp[1] = rs.getInt(2)+"";
	        	 list.add(temp);
	        	 log.debug("list"+list.get(0));
	 			}  
			 rs.close();
        }
		psmt.close();
        db.freeConnection(conn);
    } catch (SQLException e) {                 
        e.printStackTrace();       
    }
		return list;       
  }

/**
 * 根据sportsid查询所有田赛裁判员（查看）
 * @param sportsid
 * @return
 */
public ArrayList selectAllFiledJudge(int sportsid) {
	ArrayList list = new ArrayList();;
	DBConn db = new DBConn();
	String sql = "SELECT t_item.itemname,t_group2item.id,t_fieldjudge.judge_1,t_fieldjudge.judge_2," +
			"t_fieldjudge.judge_3 FROM t_item JOIN t_group2item ON t_item.id = t_group2item.itemid JOIN" +
			" t_group2sports ON t_group2sports.id=t_group2item.gp2spid JOIN t_fieldjudge ON t_group2item.id" +
			" = t_fieldjudge.gp2itid WHERE t_group2sports.sportsid=? AND t_item.itemtype=2";
	try {
		 Connection conn = db.getConn();
		 if(conn != null){
			 psmt = conn.prepareStatement(sql);
		     psmt.setInt(1, sportsid);
		     ResultSet rs = psmt.executeQuery();
         while (rs.next()) {
        	 String itemname = rs.getString(1);
        	 log.debug("itemnameqqqqqq:"+itemname);
        	 String[] temp = new String[5];
 			 temp[0] = itemname;
 			 temp[1] = rs.getInt(2)+"";
 			 if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
 				temp[2] = "未填写";
 				log.debug("temp[2]:"+temp[3]);
 			 }else{
 				temp[2] = rs.getString(3);
 				log.debug("temp[2]:"+temp[2]);
 			 }
 			if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
				 temp[3] = "未填写";
				 log.debug("temp[2]:"+temp[2]);
			 }else{
 				temp[3] = rs.getString(4);
 				log.debug("temp[2]:"+temp[2]);
 			 }
 			if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
				 temp[4] = "未填写";
				 log.debug("temp[2]:"+temp[2]);
			 }else{
 				temp[4] = rs.getString(5);
 				log.debug("temp[2]:"+temp[2]);
 			 }
 			log.debug("listqqqqq:"+list);
        	 list.add(temp);
 		}  
		 rs.close();
    }
	psmt.close();
    db.freeConnection(conn);
} catch (SQLException e) {                 
    e.printStackTrace();       
}
	return list;       
}
/**
 * 根据sportsid查询学生裁判
 * @param sportsid
 * @return
 */
public ArrayList selectAllStuJudge(int sportsid) {
	ArrayList list = new ArrayList();
	DBConn db = new DBConn();
	String sql = "SELECT t_department.departshortname,t_stujudge.sp2dpid,t_stujudge.contact,t_stujudge.tel," +
			"t_stujudge.member FROM t_stujudge JOIN t_sports2department ON t_stujudge.sp2dpid = t_sports2department.id " +
			"JOIN t_department ON t_department.id=t_sports2department.departid WHERE sportsid = ? and t_department.departtype=1";
	try {
		 Connection conn = db.getConn();
		 if(conn != null){
			 psmt = conn.prepareStatement(sql);
		     psmt.setInt(1, sportsid);
		     ResultSet rs = psmt.executeQuery();
         while (rs.next()) {
        	 String depName = rs.getString(1);
        	 
        	 String[] temp = new String[5];
 			 temp[0] = depName;
 			 temp[1] = rs.getInt(2)+"";
 			 if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
 				temp[2] = "未填写";
 				log.debug("temp[2]:"+temp[3]);
 			 }else{
 				temp[2] = rs.getString(3);
 				log.debug("temp[2]:"+temp[2]);
 			 }
 			if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
				 temp[3] = "未填写";
				 log.debug("temp[2]:"+temp[2]);
			 }else{
 				temp[3] = rs.getString(4);
 				log.debug("temp[2]:"+temp[2]);
 			 }
 			if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
				 temp[4] = "未填写";
				 log.debug("temp[2]:"+temp[2]);
			 }else{
 				temp[4] = rs.getString(5);
 				log.debug("temp[2]:"+temp[2]);
 			 }
 			log.debug("listqqqqq:"+list);
        	list.add(temp);
 		}  
         
		 rs.close();
    }
	psmt.close();
    db.freeConnection(conn);
} catch (SQLException e) {                 
    e.printStackTrace();       
}
	return list;       
}
	/**
	 * 根据sportsid查询所有工作人员
	 * @return
	 */
	public ArrayList selectOfficialBySportsid(int sportsid) {
		ArrayList list = new ArrayList();
		DBConn db = new DBConn();
		String sql = "SELECT presidium,org_committee_1,org_committee_2," +
 		"org_committee_3,secretariat_1,secretariat_2,secretariat_3,secretariat_4," +
 		"secretariat_5,secretariat_6,secretariat_7,arbitration,chiefjudge_1," +
 		"chiefjudge_2,trackjudge,trackjudge_rollcall_1,trackjudge_rollcall_2," +
 		"trackjudge_rollcall_3,startingpoint_1,startingpoint_2,startingpoint_3," +
 		"timejudge_1,timejudge_2,timejudge_3,endpoint_1,endpoint_2,endpoint_3," +
 		"endpoint_4,endpoint_5,fieldjudge,fieldjudge_1,fieldjudge_2,fieldjudge_3," +
 		"fieldjudge_4,fieldjudge_5,fieldjudge_6,remarks_1,remarks_2,remarks_3," +
 		"remarks_4,remarks_5,openingceremony, closingceremony FROM t_official WHERE sportsid=?";
		try {
			 Connection conn = db.getConn();
			 if(conn != null){
				 psmt = conn.prepareStatement(sql);
				 psmt.setInt(1, sportsid);
				 ResultSet rs = psmt.executeQuery();
	         while (rs.next()) {
	            	 Official official = new Official();
	            	 official.setPresidium(rs.getString("presidium"));
	            	 official.setOrg_committee_1(rs.getString("org_committee_1"));
	            	 official.setOrg_committee_2(rs.getString("org_committee_2"));
	            	 official.setOrg_committee_3(rs.getString("org_committee_3"));
	            	 official.setSecretariat_1(rs.getString("secretariat_2"));
	            	 official.setSecretariat_2(rs.getString("secretariat_1"));
	            	 official.setSecretariat_3(rs.getString("secretariat_3"));
	            	 official.setSecretariat_4(rs.getString("secretariat_4"));
	            	 official.setSecretariat_5(rs.getString("secretariat_5"));
	            	 official.setSecretariat_6(rs.getString("secretariat_6"));
	            	 official.setSecretariat_7(rs.getString("secretariat_7"));
	            	 official.setArbitration(rs.getString("arbitration"));
	            	 official.setChiefjudge_1(rs.getString("chiefjudge_1"));
	            	 official.setChiefjudge_2(rs.getString("chiefjudge_2"));
	            	 official.setTrackjudge(rs.getString("trackjudge"));
	            	 official.setTrackjudge_rollcall_1(rs.getString("trackjudge_rollcall_1"));
	            	 official.setTrackjudge_rollcall_2(rs.getString("trackjudge_rollcall_2"));
	            	 official.setTrackjudge_rollcall_3(rs.getString("trackjudge_rollcall_3"));
	            	 official.setStartingpoint_1(rs.getString("startingpoint_1"));
	            	 official.setStartingpoint_2(rs.getString("startingpoint_2"));
	            	 official.setStartingpoint_3(rs.getString("startingpoint_3"));
	            	 official.setTimejudge_1(rs.getString("timejudge_1"));
	            	 official.setTimejudge_2(rs.getString("timejudge_2"));
	            	 official.setTimejudge_3(rs.getString("timejudge_3"));
	            	 official.setEndpoint_1(rs.getString("endpoint_1"));
	            	 official.setEndpoint_2(rs.getString("endpoint_2"));
	            	 official.setEndpoint_3(rs.getString("endpoint_3"));
	            	 official.setEndpoint_4(rs.getString("endpoint_4"));
	            	 official.setEndpoint_5(rs.getString("endpoint_5"));
	            	 official.setFieldjudge(rs.getString("fieldjudge"));
	            	 official.setFieldjudge_1(rs.getString("fieldjudge_1"));
	            	 official.setFieldjudge_2(rs.getString("fieldjudge_2"));
	            	 official.setFieldjudge_3(rs.getString("fieldjudge_3"));
	            	 official.setFieldjudge_4(rs.getString("fieldjudge_4"));
	            	 official.setFieldjudge_5(rs.getString("fieldjudge_5"));
	            	 official.setFieldjudge_6(rs.getString("fieldjudge_6"));
	            	 official.setRemarks_1(rs.getString("remarks_1"));
	            	 official.setRemarks_2(rs.getString("remarks_2"));
	            	 official.setRemarks_3(rs.getString("remarks_3"));
	            	 official.setRemarks_4(rs.getString("remarks_4"));
	            	 official.setRemarks_5(rs.getString("remarks_5"));
	            	 official.setOpeningceremony(rs.getString("openingceremony"));
	            	 official.setClosingceremony(rs.getString("closingceremony"));
	 				 list.add(official);
	 			}  
	       rs.close();
        }
		psmt.close();
        db.freeConnection(conn);
    } catch (SQLException e) {                 
        e.printStackTrace();       
    }
		return list;       
  }
	/**
	 * 根据sp2dpid查询学生裁判类
	 * @param sp2dpid
	 * @return
	 */
	public ArrayList selectStuJudge(int sp2dpid) {
		ArrayList list = new ArrayList();
		DBConn db = new DBConn();
		String sql = "SELECT contact,tel,member FROM t_stujudge WHERE sp2dpid =?";
		try {
			 Connection conn = db.getConn();
			 if(conn != null){
				 psmt = conn.prepareStatement(sql);
			     psmt.setInt(1, sp2dpid);
			     ResultSet rs = psmt.executeQuery();
	         while (rs.next()) {
	        	 String[] temp = new String[4];
	 			 temp[0] = sp2dpid+"";
	 			 if (rs.getString(1)== null || rs.getString(1).trim().equals("")){
	 				temp[1] = "未填写";
	 			 }else{
	 				temp[1] = rs.getString(1);
	 			 }
	 			if (rs.getString(2)== null || rs.getString(2).trim().equals("")){
					 temp[2] = "未填写";
				 }else{
	 				temp[2] = rs.getString(2);
	 			 }
	 			if (rs.getString(3)== null || rs.getString(3).trim().equals("")){
					 temp[3] = "未填写";
				 }else{
	 				temp[3] = rs.getString(3);
	 			 }
	 			log.debug("小框中学生裁判:"+list);
	        	list.add(temp);
	 		}  
			 rs.close();
	    }
	    db.freeConnection(conn);
	} catch (SQLException e) {                 
	    e.printStackTrace();       
	}
		return list;       
	}
	/**
	 * 
	 *修改学生裁判类
	 * @return
	 */
	public boolean updateStuJudge(int id,String contact,String tel,String member){
	    DBConn db = new DBConn();
	    boolean retuValue = false;
	    int flag=0;
	    String sql ="UPDATE t_stujudge SET contact=?,tel=?,member=? WHERE sp2dpid =?";
	    log.debug("sql================="+sql);
	    	try {
		          Connection conn = db.getConn();
		          if(conn != null){
		        	  psmt=conn.prepareStatement(sql);
		        	  
		        	  psmt.setString(1,contact);
		        	  psmt.setString(2,tel);
		        	  psmt.setString(3,member);
		        	  psmt.setInt(4, id);
		        	  log.debug("sql================="+sql);
				      flag=psmt.executeUpdate();
		    	 }
		         psmt.close();
	             db.freeConnection(conn);
	         } catch (SQLException e) {                 
	                e.printStackTrace();       
	          } 
	     if(flag>0){
	    	retuValue = true;
	     }
	     return retuValue;
	 } 
}
