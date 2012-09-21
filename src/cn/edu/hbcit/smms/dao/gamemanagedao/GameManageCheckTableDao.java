/*
* Copyright(C) 2012, XXXXXXXX.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2004/12/12		0.1		张 三		新建
* 2005/02/05		0.1		李 四		Bug修正
*/
package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.GameManageCheckTablePojo;

/**
 *赛中管理---检录表 数据库操作类
 *
 *连接数据库
 *
 *详细解释。
 * @author 杨春华
 * @version 1.00  2011/12/07 新規作成<br>
 */

public class GameManageCheckTableDao {
	private static Connection conn;
	private static ResultSet rs;
	protected final Logger log = Logger.getLogger(GameManageCheckTableDao.class.getName());
	private LoginDAO loginDAO = new LoginDAO();
	/**
	 * 获取运动会组别
	 * @author 杨春华
     * @return grouplist
	 */
	public ArrayList getGroupList() {
		DBConn db = new DBConn();
		ArrayList grouplist=new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select id, groupname from t_group"); 
                int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	GameManageCheckTablePojo ct=new GameManageCheckTablePojo(); 
                   ct.setGroupId(rs.getInt(1));
                   ct.setGroupName(rs.getString(2));
                   grouplist.add(ct);
                    for(int i=1;i<=c;i++){
                          log.debug(rs.getObject(i));
                    }
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) { 
        	log.debug(e.getMessage());
            e.printStackTrace();       
        } 
        return grouplist;
      
    }
	
	/**
	 * 
	* 获取运动会的项目名称	
	* @param groupid
	* @return itemList
	 */
	public ArrayList getItemList(int groupid){
		System.out.println("getItemList-->groupid="+groupid);
		int sportsid = loginDAO.selectCurrentSportsId();
		DBConn db = new DBConn();
		ArrayList itemlist=new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery(
                		"select finalitemtype, finalitemname from t_finalitem " +
                		"where gp2itid in (select id from t_group2item " +
                		"where gp2spid =(select id from t_group2sports " +
                		"where groupid='"+groupid+"' and sportsid='"+sportsid+"'))"); 
                int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	GameManageCheckTablePojo ct=new GameManageCheckTablePojo(); 
                    ct.setItemType(rs.getString(1));
                    ct.setItemName(rs.getString(2));
                   itemlist.add(ct);
                    for(int i=1;i<=c;i++){
                          log.debug(rs.getObject(i));
                    }
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {  
        	log.debug(e.getMessage());
            e.printStackTrace();       
        } 
        return itemlist;	
		
	}
	
	
	/**
	 * 
	* 判断预赛的成绩是否为空	
	* 方法补充说明
	* @return boolean 
	 */
	public boolean isScoreNull( String finalitemname ) {
		DBConn db = new DBConn();
		boolean flag = false;
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement();
               
                ResultSet rs = statement.executeQuery( "select score from t_match where finalitemid in (select id from t_finalitem where finalitemname='" + finalitemname+"')");  
               
                if(rs.next()){
                   
                	flag=true;
                  
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {
        	log.debug(e.getMessage());
            e.printStackTrace();       
        } 
        return flag;
      
    }
	/**
	 * 
	* getFinalMatchList
	* 方法补充说明	获得指定项目的一个小组的运动员信息
	* @param 
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	
	public JSONArray getOneItemPlayerMessageByTeam( String itemname ,String finalitemtype ,int teamnum ){
			System.out.println("finalitemtype="+finalitemtype);
			DBConn db = new DBConn();
			JSONArray alllist = new JSONArray();
			Connection conn = null;
			String sql = null; 
			GameManageCheckTableDao gmctd = new GameManageCheckTableDao();
			String matchtype = gmctd.getMatchType(itemname);		//获取比赛类型       1：径赛，2：田赛，3：接力
			if( finalitemtype.equals("1") || finalitemtype.equals("3")  ){		//预赛、预决赛
				if( matchtype.equals("1") || matchtype.equals("2")){				//竞赛、田赛
					System.out.println("matchtype="+matchtype);
					sql = "SELECT t_match.teamnum,t_player.playernum,t_player.playername,t_match.runway FROM " +
					"t_match JOIN t_player ON t_player.id=t_match.playerid WHERE " +
					"t_match.finalitemid =(   SELECT id FROM t_finalitem WHERE finalitemname= " +
					"(SELECT finalitemname FROM t_finalitem WHERE finalitemtype = '"+finalitemtype+"'  AND  gp2itid = (" +
							"SELECT gp2itid FROM t_finalitem WHERE finalitemname = '"+itemname+"'))) and teamnum = '"+teamnum+"' order by runway";
				}else{			//接力
					sql = "SELECT playerid,playernum,playername,teamnum,runway from t_match " +
							"join t_sports2department on t_sports2department.departid = t_match.playerid" +
							"join t_player on t_player.sp2dpid = t_sports2department.id" +
							"join t_finalitem on t_finalitem.id = t_match.finalitemid" +
							"where finalitemid = (SELECT id from t_finalitem where " +
							"finalitemname = '"+itemname+"') and finalitemtype = '"+finalitemtype+"' and teamnum = '"+teamnum+"' "  ;
				} 
			}else{
				
				System.out.println("finalitemtype="+finalitemtype);
				if(finalitemtype.equals("2")){			//决
					
					//int num = updateFinalMatch(itemname ,promotionnum);	//设置决赛赛道
					//System.out.println("决="+num);
					if( matchtype.equals("1") || matchtype.equals("2") ){		//竞赛、田赛
						sql = "SELECT t_match.teamnum,t_player.playernum,t_player.playername,t_match.runway FROM " +
						"t_match JOIN t_player ON t_player.id=t_match.playerid WHERE " +
						"t_match.finalitemid =(   SELECT id FROM t_finalitem WHERE finalitemname= " +
						"(SELECT finalitemname FROM t_finalitem WHERE finalitemtype = '"+finalitemtype+"'  AND  gp2itid = (" +
						"SELECT gp2itid FROM t_finalitem WHERE finalitemname = '"+itemname+"'))) and teamnum = "+teamnum+" order by runway limit 3";
					}else{		//接力
						sql = "SELECT playerid,playernum,playername,teamnum,runway from t_match " +
						"join t_sports2department on t_sports2department.departid = t_match.playerid " +
						"join t_player on t_player.sp2dpid = t_sports2department.id " +
						"join t_finalitem on t_finalitem.id = t_match.finalitemid " +
						"where finalitemid = (SELECT id from t_finalitem where " +
						"finalitemname = '"+itemname+"') and finalitemtype = '"+finalitemtype+"' and  teamnum = "+teamnum+" ";
					}
				}
			}
			
	        try {
	        		conn = db.getConn(); 
	                Statement statement = conn.createStatement();
	              //juesai
	                if(finalitemtype.equals("2")){
	                if( matchtype.equals("1") || matchtype.equals("2") ){//竟赛和田赛
	                	 ResultSet rs = statement.executeQuery(sql);	
	                	 while( rs.next() ){  
	                		 JSONArray list = new JSONArray();
	                		 //list.add(Integer.toString(rs.getInt(1)));
	                		 list.add(rs.getString(2));
	                		 list.add(rs.getString(3));
	                		 list.add(Integer.toString(rs.getInt(4)));
	                		 alllist.add(list);
	  	                }
	  	                rs.close();
	                }
	                
	                //接力
	                if( matchtype.equals("3") ){
	                	 ResultSet rs = statement.executeQuery(sql);
	                	 while(rs.next()){
	                		 JSONArray list = new JSONArray();
	                		 //list.add(Integer.toString(rs.getInt(1)));
	                		 list.add(rs.getString(2));
	                		 list.add(Integer.toString(rs.getInt(3)));
	                		 list.add(Integer.toString(rs.getInt(4)));
	                		 list.add(rs.getString(5));
	                		 alllist.add(list); 
	                	 }
	                	 rs.close();
	                }
	                }
	                else{
	                	if( matchtype.equals("1") || matchtype.equals("2")){//竟赛和田赛
		                	 ResultSet rs = statement.executeQuery(sql);	
		                	 while( rs.next() ){  
		                		 JSONArray list = new JSONArray();
		                		 //list.add(Integer.toString(rs.getInt(1)));
		                		 list.add(rs.getString(2));
		                		 list.add(rs.getString(3));
		                		 list.add(Integer.toString(rs.getInt(4)));
		                		 alllist.add(list);
		  	                }
		  	                rs.close();
		                }
		                
		                //接力
		                if(  matchtype.equals("3") ){
		                	 ResultSet rs = statement.executeQuery(sql);
		                	 while(rs.next()){
		                		 JSONArray list = new JSONArray();
		                		 //list.add(Integer.toString(rs.getInt(1)));
		                		 list.add(rs.getString(2));
		                		 list.add(Integer.toString(rs.getInt(3)));
		                		 list.add(Integer.toString(rs.getInt(4)));
		                		 list.add(rs.getString(5));
		                		 alllist.add(list); 
		                	 }
		                	 rs.close();
		                }
	                	
	                }
	            db.freeConnection(conn);
	        } catch (SQLException e) {  
	        	log.debug(e.getMessage());
	            e.printStackTrace();     
	        }
		return alllist;
	}
	
	/**
	 * 
	* 方法说明	
	* 方法补充说明		获取某个项目的全部小组的运动员的信息
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public JSONArray getItemPlayerMessageAllTeam( String finalitemname ,String itemtype  ){
		//1预赛；2决赛；3预决赛
		JSONArray alllist = new JSONArray();
		int flag = 0;
		if( "2".equals(itemtype)){
			GameManageCheckTableDao gmctd = new GameManageCheckTableDao();
			int promotionnum = 0;
			promotionnum = gmctd.getPromotionnum(finalitemname);//项目规定的晋级数
			System.out.println("promotionnum="+promotionnum);
			updateFinalMatch(finalitemname ,promotionnum);	//设置决赛赛道
		}
		flag = getItemTeamnumber(finalitemname);//获得项目的小组数
		System.out.println("flag="+flag);
		for( int i = 0 ; i < flag ; i++){
			JSONArray list = new JSONArray();
			list = getOneItemPlayerMessageByTeam(finalitemname, itemtype,i+1);
			alllist.add(list);
		}
		return alllist;
		
	}
	/**
	 * 
	* 方法说明	
	* 方法补充说明		获取指定项目的小组数
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public int getItemTeamnumber( String finalitemname ){
		System.out.println("finalitemname="+finalitemname);
		String sql = "SELECT COUNT(DISTINCT(teamnum)) FROM t_match WHERE finalitemid " +
		"IN ( SELECT id FROM t_finalitem WHERE finalitemname = ? )";
		DBConn db = new DBConn();
		conn = db.getConn();
		int flag = 0;
		PreparedStatement  pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,finalitemname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				flag = rs.getInt(1);
			}
			 db.freeConnection(conn);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	* 方法说明	
	* 方法补充说明	获取指定项目的项目类型      例如：1：径赛，2：田赛，3：接力
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public String getMatchType( String finalitemname ){
		String sql = "SELECT itemtype from t_item where id = (" +
		"SELECT itemid from t_group2item where id in (" +
		"SELECT gp2itid from t_finalitem where finalitemname = '"+finalitemname+"'))";
		DBConn db = new DBConn();
		conn = db.getConn();
		String str = "";
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while( rs.next() ){
				str = rs.getString("itemtype");
				
			}
			 db.freeConnection(conn);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		
		return str;
	}
	
	
	/**
	 * 
	* 方法说明	
	* 方法补充说明		设置决赛赛道
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public int updateFinalMatch( String finalitemname ,int promotionnum ){
		DBConn db = new DBConn();
		conn = db.getConn();
		PreparedStatement  pstmt = null;	ResultSet rs = null;
		PreparedStatement pstmt1 = null;	ResultSet rs1 = null; 
		String sql = "insert into t_match(teamnum,runway playerid,finalitemid) values (teamnum = 1,runway = ?, playerid = ?,finalitemid = ?) ";
		
		GameManageCheckTableDao gmctd = new GameManageCheckTableDao();
		String matchType = null;
		matchType = gmctd.getMatchType(finalitemname);//1：径赛，2：田赛，3：接力
		
		int finalitemid = getFinalitemid(finalitemname);	//finalitemid
		int num = 0;
		int[] runway = gmctd.getFinalRunWay(promotionnum);//赛道分配
		if( matchType == "1" || matchType == "3" ){
			String sql1 = "SELECT playerid FROM t_match WHERE finalitemid = (" +
					"SELECT id FROM t_finalitem WHERE finalitemname = ? " +
					")ORDER BY score+0 DESC LIMIT ? ";
			try {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, finalitemname);
				pstmt.setInt(2, promotionnum);
				rs = pstmt.executeQuery();
				rs.close();
				pstmt.close();
				pstmt1 = conn.prepareStatement(sql);
				for( int i = 0 ; i < runway.length ; i++ ){
					pstmt1.setInt(1, runway[i]);
					pstmt1.setInt(2, rs.getInt(1));
					pstmt1.setInt(3, finalitemid);
					num += pstmt1.executeUpdate();
					System.out.println("num="+num+",runway[i]="+runway[i]);
				}
				pstmt1.close();
				db.freeConnection(conn);
			} catch (Exception e) {
				log.debug(e.getMessage());
				e.printStackTrace();
			}
			
		}if( matchType == "2"){
			String sql1 = "SELECT teamnum,runway,playerid FROM t_match WHERE finalitemid = (" +
			"SELECT id FROM t_finalitem WHERE finalitemname = ? " +
			")ORDER BY score+0  LIMIT ? ";
			
			try {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, finalitemname);
				pstmt.setInt(2, promotionnum);
				rs = pstmt.executeQuery();
				
				pstmt1 = conn.prepareStatement(sql);
				for( int i = 0 ; i < runway.length ; i++ ){
					pstmt1.setInt(1, runway[i]);
					pstmt1.setInt(2, rs.getInt(1));
					pstmt1.setInt(3, finalitemid);
					num += pstmt1.executeUpdate();
					System.out.println("num="+num+",runway[i]="+runway[i]);
				}
				
				db.freeConnection(conn);
			} catch (Exception e) {
				log.debug(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		return num;
	}
	
	/**
	 * 
	* 方法说明	
	* 方法补充说明	生成决赛赛道
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public int[] getFinalRunWay( int Promotionnum ){
		int[] runway = new int[Promotionnum];
		  int a = 1;
		  runway[0] = 5;
		  int k = (-1);
		 
		  for (int i = 1; i < Promotionnum; i++){
			  if(i%2 == 0){
				  k = 1;
			  }
			  if(i%2==1){
				  k = (-1);
			  }
			  runway[i] = 5 + a*k; 
			  if(i%2 == 0){
				  a++;
			  }
		  }
		return runway;
		
	}
	/**
	 * 
	* 方法说明	
	* 方法补充说明	获取指定项目的晋级标注
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public int getPromotionnum( String finalitemname ){
		System.out.println("getPromotionnum-->finalitemname="+finalitemname);
		String sql = "SELECT promotionnum FROM t_finalitem " +
				"WHERE gp2itid IN ( SELECT gp2itid FROM t_finalitem WHERE finalitemname = ? ) AND (finalitemtype = '3' OR finalitemtype = '1')";
		DBConn db = new DBConn();
		conn = db.getConn();
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			//System.out.println("rs="+rs.next());
			if( rs.next() ){
				System.out.println("rs=");
				flag = rs.getInt(1);
				System.out.println("flag="+flag);
			}
			 db.freeConnection(conn);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("rs="+flag);
		return flag; 	
		
	}
	 /**获取当前运动会的名字
	  * 
	 * 方法说明	
	 * 方法补充说明
	 * @param 参数名 参数类型 参数意义注释
	 * @return 返回值的类型 意义注释
	 * @exception 例外的类型 意义注释
	  */
	public String getSportsName(){
		return loginDAO.selectCurrentSportsName();
	}
	/**
	 * 获取项目的finalitemid
	* 方法说明	
	* 方法补充说明
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public int getFinalitemid( String finalitemname){
		String sql = "SELECT id FROM t_finalitem WHERE finalitemname = ?";
		int finalitemid = 0;
		DBConn db = new DBConn();
		conn = db.getConn();
		PreparedStatement  pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finalitemname);
			rs = pstmt.executeQuery();
			if( rs.next()){
				finalitemid = rs.getInt(1);
			}
			db.freeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalitemid ;
	}
}
	 

