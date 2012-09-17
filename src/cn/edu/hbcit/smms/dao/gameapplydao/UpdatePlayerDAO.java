/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-21			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.dao.gameapplydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Group;

public class UpdatePlayerDAO {
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	private ResultSet rs = null;
	protected final Logger log = Logger.getLogger(UpdatePlayerDAO.class.getName());
	DBConn db = new DBConn();
	/**
	 * 根据性别和组别类型查找出组别id
	 * @param groupSex
	 * @return
	 */
	public int GetGroupIdBySex(boolean playerSex){
		conn = db.getConn();
		String sql = "SELECT id FROM t_group WHERE groupsex=? " +
				"AND grouptype=TRUE;";
		int flag = 0;
		 try {
	        	pStatement = conn.prepareStatement(sql);
	        	pStatement.setBoolean(1, playerSex);
	        	rs = pStatement.executeQuery();
	        	while(rs.next()){
					flag = rs.getInt(1);
	        	}
	        	db.freeConnection(conn);
	     }catch(SQLException e){
	    	 e.printStackTrace();
	     }
	     return flag;
	}
	
	/**
	 * 根据运动员号码插入（修改）运动员信息
	 * @param playerName
	 * @param playerSex
	 * @param registItem
	 * @param playerNum
	 * @return
	 */
	public int UpdatePlayerByNum(String playerName,boolean playerSex,
			String registItem,int groupId,int sp2dpid,String playerNum){
		conn = db.getConn();
		//GetPlayerDAO getPlayerDao = new GetPlayerDAO();
		String sql = "update t_player set playername=?,playersex=?,registitem=?," +
				"groupid=? where sp2dpid=? and playernum=?";
		int flag=0;
        try {
        	pStatement = conn.prepareStatement(sql);
        	pStatement.setString(1, playerName);
        	pStatement.setBoolean(2, playerSex);
        	pStatement.setString(3, registItem);
        	pStatement.setInt(4, groupId);
        	pStatement.setInt(5, sp2dpid);
        	pStatement.setString(6, playerNum); 
        	flag = pStatement.executeUpdate(); 
            conn.close();
            db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("添加运动员失败！");
    		log.error(e.getMessage());
    		System.out.println(e);       
        }
        return flag;
	}
	
	/**
	 * 插入（修改）教工信息
	 */
	public int UpdatePlayer(String[] allstr,int sp2dpid){
		conn = db.getConn();
		String sql = "update t_player set playername=?,playersex=?,registitem=?," +
				"groupid=? where sp2dpid=? and playernum=?";
		int flag = 0;
		
		try {
			
			for(int i = 0; i < allstr.length; i++ ){
				String mystr = allstr[i];
				String[] myPlayer = mystr.split(",");
				String playerNum = myPlayer[0];//运动员号码
				String playerName = myPlayer[1];//运动员姓名
				boolean playerSex = true;
				int groupid = Integer.parseInt(myPlayer[2]);//组别的id
				System.out.println(groupid);
				String sql1 = "select groupsex from t_group where id="+groupid;//根据组别的id查出性别
				pStatement = conn.prepareStatement(sql1);
				rs = pStatement.executeQuery();
				String sex = null;
				if(rs.next()){
					 sex = rs.getString(1);
				}
				//System.out.println(sex);
				if(sex.equals("1")){
					playerSex = true;
				}else {
					playerSex = false;
				}
				//System.out.println(playerSex);
/*************吕志瑶
 * 去掉项目类型分隔符
 * ******************/
				String registItem = "";//myPlayer[3]:运动员所报项目的id "2+3;4+5"
				String[] itemIdArr =  myPlayer[3].split(";");  //{"2+3","4+5"}
				for(int j=0; j<itemIdArr.length; j++){
					String[] tempStr = itemIdArr[j].split("#");  //{"2","3"}
					registItem = registItem + tempStr[0];
					if(j != itemIdArr.length-1){
						registItem += ";";
					}
				}
				//System.out.println("QQQQQQQQQQQQQ:"+registItem);
				
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, playerName);
				pStatement.setBoolean(2, playerSex);
				pStatement.setString(3, registItem);
				pStatement.setInt(4, groupid);
				pStatement.setInt(5, sp2dpid);
				pStatement.setString(6, playerNum); 
				
				flag = pStatement.executeUpdate(); 
					if( flag == 0){
						break;
					}
				pStatement.close();
			}
			conn.close();
			db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("添加运动员失败！");
    		log.error(e.getMessage());
    		System.out.println(e);       
		}
		return flag;
	}
	
	


}
