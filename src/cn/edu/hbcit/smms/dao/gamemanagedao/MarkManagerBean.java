package cn.edu.hbcit.smms.dao.gamemanagedao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Department;
import cn.edu.hbcit.smms.pojo.ManageItemPJ;
import cn.edu.hbcit.smms.pojo.ManagerMarkPJ;

/**
 * 积分查询
 *
 *简要说明
 *
 *详细解释。
 * @author HuoLiFang
 * @version 1.00  2012/6/15  新建
 */


public class MarkManagerBean {	
		protected final Logger log = Logger.getLogger(MarkManagerBean.class.getName());
		/**
		 * 获取部门的名称
		 */
		DBConn db = new DBConn();
		Connection conn = db.getConn();
		Statement statement = null;
		ResultSet rs = null;
		public ArrayList getDepart() {
			
			ArrayList list = new ArrayList();
	        try {
	        		statement = conn.createStatement();  
	                rs = statement.executeQuery("select id,departname from t_department");
	                while(rs.next()){
	                	//System.out.println("rs="+rs.getInt(1)+",rs2="+rs.getString(2));
	                    Department pj = new Department();
	                    pj.setId(rs.getInt(1));
	                    pj.setDepartmentName(rs.getString(2));
	                    list.add(pj);	                  
	                }	                
	               
	        	rs.close();
	        	statement.close();
	            db.freeConnection(conn);
	        } catch (SQLException e) {
	        	
	            e.printStackTrace();       
	        }
	        return list;
	      
	    }
		
		/**
		 * 获取项目的名称
		 * @return
		 */
		public ArrayList getItem() {
			DBConn db = new DBConn();
			ArrayList list = new ArrayList();
	        try {
	            Connection conn = db.getConn();
	            if(conn != null){
	            	
	                Statement statement = conn.createStatement(); 
	                ResultSet rs = statement.executeQuery("select id,itemname from t_item");
	                while(rs.next()){	 
	                	//System.out.println("rs="+rs.getInt(1));
	                	ManageItemPJ  pj= new ManageItemPJ();
	                    pj.setId(rs.getInt(1));
	                    pj.setItemname(rs.getString(2));	                   
	                    list.add(pj);	                  
	                }	                
	                
	            }
	            rs.close();
	        	statement.close();
	            db.freeConnection(conn);
	        } catch (SQLException e) {                 
	            e.printStackTrace();       
	        }
	        return list;
	      
	    }
		/**
		 * 获取按部门和组别条件查询的结果集
		 * @param departId 部门名称的id号
		 * @param groupId  组别名称的id号
		 * @return
		 */
		
		public ArrayList getQuery(int departId,int groupId ) {			
			DBConn db = new DBConn();
			String sql="SELECT t_department.departname,t_group.groupname,t_mark.sum FROM t_mark JOIN t_sports2department ON t_sports2department.id=t_mark.sp2dpid JOIN t_department ON t_department.id=t_sports2department.departid  JOIN t_sports ON t_sports.id=t_sports2department.id JOIN t_group2sports ON  t_group2sports.groupid=t_sports.id JOIN t_group ON t_group.id=t_group2sports.groupid WHERE  t_department.id="+departId+" AND t_group.id="+groupId+"";
			ArrayList list = new ArrayList();
	        try {
	            Connection conn = db.getConn();
	            if(conn != null){
	            	
	                Statement statement = conn.createStatement(); 
	                ResultSet rs = statement.executeQuery(sql);
	                while(rs.next()){	
	                	ManagerMarkPJ pj = new ManagerMarkPJ();
	                    pj.setDepartname(rs.getString(1));
	                    pj.setGroupName(rs.getString(2));	                   
	                    pj.setSum(rs.getInt(3));	                    
	                    list.add(pj);	                  
	                }	                
	                rs.close();
		        	statement.close();
	            }	           
	            db.freeConnection(conn);
	        } catch (SQLException e) {                 
	            e.printStackTrace();       
	        }
	        return list;	      
	    }
		/**
		 * 获取按项目和组别条件查询得到的结果集
		 * @param itemId  项目名称的id
		 * @param roleId  组别名称的id
		 * @return
		 */
		public ArrayList getItemQuery(int itemId,int roleId ) {			   
			DBConn db = new DBConn();
			String sql="SELECT t_item.itemname,t_group.groupname,t_mark.sum FROM t_item,t_mark JOIN t_sports2department ON t_sports2department.id=t_mark.sp2dpid JOIN t_sports ON t_sports.id=t_sports2department.sportsid JOIN t_group2sports ON t_group2sports.sportsid=t_sports.id JOIN t_group ON t_group.id=t_group2sports.groupid WHERE t_group.id="+itemId+" AND t_item.id="+roleId+"";
			ArrayList list = new ArrayList();
	        try {
	            Connection conn = db.getConn();
	            if(conn != null){
	            	
	                Statement statement = conn.createStatement(); 
	                ResultSet rs = statement.executeQuery(sql);
	                while(rs.next()){	
	                	ManagerMarkPJ pj = new ManagerMarkPJ();
	                    pj.setItemname(rs.getString(1));
	                    pj.setGroupName(rs.getString(2));	                   
	                    pj.setSum(rs.getInt(3));	                    
	                    list.add(pj);
	                }	                
	                rs.close();
		        	statement.close();
	            }	           
	            db.freeConnection(conn);
	        } catch (SQLException e) {                 
	            e.printStackTrace();       
	        }
	        return list;
		}
		
	}

