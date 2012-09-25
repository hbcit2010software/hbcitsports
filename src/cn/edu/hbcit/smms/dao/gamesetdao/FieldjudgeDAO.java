package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.databasedao.DBTest;
import cn.edu.hbcit.smms.pojo.Fieldjudge;
import cn.edu.hbcit.smms.pojo.FildItemJudge;

/**
 * 田赛项目裁判管理类
 * @author 姚瑶
 *
 */
public class FieldjudgeDAO {
	private PreparedStatement psmt;
	protected final Logger log = Logger.getLogger(DBTest.class.getName());
/**
 * 查询所有田塞裁判
 * @return
 */
/**
	public List selectFildItemJudge(int sportsid){
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			 Connection conn = db.getConn();
			String sql = "SELECT t_fieldjudge.id,t_item.itemname,judge_1,judge_2,judge_3" +
					" FROM t_fieldjudge" +
					" INNER JOIN t_group2item ON t_fieldjudge.gp2itid=t_group2item.id" +
					" INNER JOIN t_item ON t_group2item.itemid=t_item.id" +
					" INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid" +
					" WHERE t_group2sports.sportsid=? AND t_item.itemtype=2";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sportsid);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				FildItemJudge fildItem = new FildItemJudge();
				fildItem.setJudgeId(rs.getInt("id"));
				fildItem.setItemName(rs.getString("itemname"));
				fildItem.setJudge1(rs.getString("judge_1"));
				fildItem.setJudge1(rs.getString("judge_2"));
				fildItem.setJudge1(rs.getString("judge_3"));
				list.add(fildItem);
			}
	    
        db.freeConnection(conn);
    } catch (SQLException e) {                 
        e.printStackTrace();       
		
	}
        return list;
	}*/
	
	/**
	 * 
	 *修改小框中田赛裁判类
	 * @return
	 */
	public boolean updateFiledItemJudge(int fildJudgeId,String judge_1,String judge_2,String judge_3){
		    DBConn db = new DBConn();
		    boolean retuValue = false;
		    int flag=0;
		    String sql ="UPDATE t_fieldjudge SET judge_1=?, judge_2=?,  judge_3=? WHERE gp2itid=?" ;
		    
		    	try {
			          Connection conn = db.getConn();
			          if(conn != null){
			        	  psmt=conn.prepareStatement(sql);
			        	  psmt.setString(1, judge_1);
			        	  psmt.setString(2, judge_2);
			        	  psmt.setString(3, judge_3);
			        	  psmt.setInt(4, fildJudgeId);
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
	 * 根据gp2itid查询田赛裁判显示在小框中
	 * @param itemname
	 * @return
	 */
	public ArrayList selectFiledItemJudgeByItemname(int gp2itid) {
		ArrayList list = new ArrayList();
		DBConn db = new DBConn();
		String sql = "SELECT judge_1,judge_2,judge_3 FROM t_fieldjudge WHERE gp2itid = ?";
		
		try {
			 Connection conn = db.getConn();
			 if(conn != null){
				 psmt = conn.prepareStatement(sql);
				 psmt.setInt(1, gp2itid);
			     ResultSet rs = psmt.executeQuery();
			     
	         while (rs.next()) {
	        	 String[] temp = new String[4];
	        	 temp[0] = rs.getString(1);
	        	 temp[1] = rs.getString(2);
	        	
	        	 temp[2] = rs.getString(3);
	        	 temp[3] = gp2itid+"";
	 				//item.setId(rs.getInt("id"));
	 				//item.setItemtype(rs.getString("itemtype"));
	 			list.add(temp);
	 				//System.out.println("lllllllllll"+list);
	 				//System.out.println("aaaaaaa"+fieldjudge);
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
	 * 根据sportsid查询所有学生部门
	 * @return
	 */
	public ArrayList selectStuDepartmentBySportsid(int sportsid) {
		ArrayList list = new ArrayList();
		DBConn db = new DBConn();
		String sql = "SELECT t_sports2department.id,t_department.departshortname FROM t_department " +
				"JOIN t_sports2department ON t_sports2department.departid=t_department.id " +
				"WHERE t_department.departtype=1 AND t_sports2department.sportsid=?" ;	//获取部门名称
		try {
			 Connection conn = db.getConn();
			 if(conn != null){
			   psmt = conn.prepareStatement(sql);
			   psmt.setInt(1, sportsid);
			   ResultSet rs = psmt.executeQuery();
			   while (rs.next()) {
	            	String[] temp = new String[2];
	            	temp[0] = rs.getInt(1)+"";
	            	temp[1] = rs.getString(2);
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
	
}
