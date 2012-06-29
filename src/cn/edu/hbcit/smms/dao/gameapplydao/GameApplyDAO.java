package cn.edu.hbcit.smms.dao.gameapplydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.GameApplyPJ;
/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：   赛事报名
 * 子模块名称：   报名情况查询
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 */
/**
 * @author 陈丹凤
 */
public class GameApplyDAO {
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(GameApplyDAO.class.getName());
	DBConn db = new DBConn();


	/**
	 * 获取系别名称
	 * @param username
	 * @return departName
	 * @author 陈丹凤
	 */
	public String getDepartmentName(String username){
		String departName = "";
		String sql = "SELECT departname FROM t_department WHERE id IN (SELECT departid FROM t_sysadmin WHERE username=?)";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			while( rs.next() ){
				departName = rs.getString(1);
			}
			db.freeConnection(conn);
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getDepartmentName"+e.getMessage());
		}
		return departName;
	}
	/**
	 * 获取组别类型
	 * @param groupid
	 * @return flag
	 * @author 
	 * @version 10.50  2012/6/24  新建
	 */
	public int getGroupType(int groupid){
		int flag = 0;
		String sql ="select grouptype from t_group where id = ?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, groupid);
			rs = pStatement.executeQuery();
			while(rs.next()){
				flag = rs.getInt(1);
			}
			db.freeConnection(conn);
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getGroupType"+e.getMessage());
		}
		return flag;
	}
	/**
	 * 获取本届运动会所有的分组
	 * @param sportsid
	 * @return groupNameList
	 * @author 陈丹凤
	 * @version 15.22  2012/6/15  新建
	 */
	public ArrayList selectAllGroupNameBySportsId(int sportsid){
		ArrayList groupNameList = new ArrayList();
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement("SELECT id,groupname FROM t_group" +
					" WHERE id IN( SELECT groupid FROM t_group2sports WHERE sportsid=?)");
			pStatement.setInt(1, sportsid);
			rs=pStatement.executeQuery();
			while(rs.next()){
				GameApplyPJ groupName = new GameApplyPJ();
				groupName.setId(rs.getInt(1));
				groupName.setGroupname(rs.getString(2));
				groupNameList.add(groupName);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return groupNameList;
	}
	/**
	 * 根据组别类型查询该届组别
	 * @param sportsid
	 * @param grouptype
	 * @return groupList
	 */
	public ArrayList selectGroupNameByGroupType(int sportsid , int grouptype){
		ArrayList groupList = new ArrayList();
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement("SELECT id,groupname FROM t_group" +
					" WHERE id IN( SELECT groupid FROM t_group2sports WHERE sportsid=?)AND grouptype=?");
			pStatement.setInt(1, sportsid);
			pStatement.setInt(2, grouptype);
			rs=pStatement.executeQuery();
			while(rs.next()){
				GameApplyPJ groupName = new GameApplyPJ();
				groupName.setId(rs.getInt(1));
				groupName.setGroupname(rs.getString(2));
				groupList.add(groupName);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);	
		}catch(SQLException e){
			e.printStackTrace();	
	}
		return groupList;
	}
	/**
	 * 根据id查询项目名称
	 * @param registitem
	 * @return jsonarray
	 */
	public JSONArray selectItemName(String registitem){
		   JSONArray jsonarray = new JSONArray();
		   String[] items = registitem.split(";");
		   for(int i=0;i<items.length;i++){
			   int itemid = Integer.parseInt(items[i]);
			   String sql = "select itemname from t_item where id =?";
			   conn = db.getConn();
			   try{
				   pStatement=conn.prepareStatement(sql);
				   pStatement.setInt(1, itemid);
				   rs = pStatement.executeQuery();
				   while(rs.next()){
					   jsonarray.add(rs.getString(1));
				   }
				   rs.close();
					pStatement.close();
			   }catch( Exception e){
				   e.getStackTrace();
			   }
		   }
		   
			return jsonarray;
	   }
	/**
	 * 分组查询本系运动员信息
	 * @author 陈丹凤
	 * @param groupid
	 * @param departid
	 * @return list
	 * @version 11.12  2012/6/15  新建
	 */
	public ArrayList selectPlayerByGroupName(int groupid , int departid){
		ArrayList list = new ArrayList();
		conn = db.getConn();
		String sql = "SELECT playernum,playername,playersex,registitem FROM t_player" +
		             " WHERE groupid=? AND sp2dpid IN(SELECT id FROM t_sports2department WHERE departid = ?)";
		JSONArray JSONlist = new JSONArray();
		GameApplyDAO gad = new GameApplyDAO();
		try{
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, groupid);
			pStatement.setInt(2, departid);
			rs=pStatement.executeQuery();
			while(rs.next()){
				GameApplyPJ playerList = new GameApplyPJ();
				playerList.setPlayernum(rs.getString(1));
				playerList.setPlayername(rs.getString(2));
				playerList.setPlayersex(rs.getInt(3));
				JSONlist = gad.selectItemName(rs.getString(4));
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<JSONlist.size();i++){
					sb.append(JSONlist.getString(i));
					if(i!=JSONlist.size()-1){
						sb.append(",");
					}
					
				}
				playerList.setRegistitem(sb.toString());
				list.add(playerList);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 分组查询该届运动会项目
	 * @author 陈丹凤
	 * @param sportsid
	 * @param grouptype
	 * @return itemList
	 * @version 11.12  2012/6/23  新建
	 */
	public ArrayList selectAllItemsByGroupType(int sportsid , int grouptype){
		ArrayList itemList = new ArrayList();
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement("SELECT id, itemname FROM t_item WHERE id IN " +
					"(SELECT itemid FROM t_group2item WHERE gp2spid IN " +
					"(SELECT id FROM t_group2sports WHERE sportsid =? AND groupid IN" +
					" (SELECT id FROM t_group WHERE grouptype=?)))");
			pStatement.setInt(1, sportsid);
			pStatement.setInt(2, grouptype);
			rs=pStatement.executeQuery();
			while(rs.next()){
				GameApplyPJ item = new GameApplyPJ();
				item.setItemid(rs.getInt(1));
				item.setItemname(rs.getString(2));
				itemList.add(item);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return itemList;
	}
	/**
	 * 根据运动员号，查询是否有该号运动员
	 * @version 8.36  2012/6/19 新建
	 * @param num
	 * @return retuValue
	 */
	public boolean selectPlayerByNum(String num){
		boolean retuValue = false;
		int count;
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement("SELECT COUNT(*) FROM t_player WHERE playernum= ?;");
			pStatement.setString(1, num);
			rs=pStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
				if (count != 0) {
					retuValue = true;
				}
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			retuValue = false;
			e.printStackTrace();
		}
		return retuValue;
	}
	/**
	 * 根据运动员号，查询该号运动员信息
	 * @author 陈丹凤
	 * @param num
	 * @return list
	 * @version 11.12  2012/6/18  新建
	 */
	public ArrayList selectAllPlayerByNum(String num){
		ArrayList list = new ArrayList();
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement("select playernum,sp2dpid,playername,playersex,groupid,registitem" +
					" from t_player where playernum= ?");
			pStatement.setString(1,num);
			rs=pStatement.executeQuery();
			while (rs.next()){
				GameApplyPJ player = new GameApplyPJ();
				player.setPlayernum(rs.getString(1));
				player.setSp2dpid(rs.getInt(2));
				player.setPlayername(rs.getString(3));
				player.setPlayersex(rs.getInt(4));
				player.setGroupid(rs.getInt(5));
				player.setRegistitem(rs.getString(6));
				list.add(player);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据运动员号更改运动员信息，使选中该号运动员时获取所有信息并传到修改页面
	 * @author 陈丹凤
	 * @param num
	 * @param playername
	 * @param playersex
	 * @param groupid
	 * @param registitem
	 * @return retuValue
	 * @version 18.50  2012/6/18 新建
	 */
	public boolean updatePlayerByNum(String num,String playername,int  playersex,String registitem ,int groupid){
		boolean retuValue=false;
		try{
			conn = db.getConn();
			conn.createStatement().executeUpdate("update t_player set playername='"+playername.trim()+"', " +
					"playersex="+playersex+",groupid="+groupid+",registitem='"+registitem+"' "+"where playernum='"+num.trim()+"'");
			retuValue=true;
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return retuValue;
	}

}