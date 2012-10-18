package cn.edu.hbcit.smms.dao.gameapplydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.SelPlayerByGroupPojo;

public class SelGameApplyDAO {

	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(SelGameApplyDAO.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 查询本系该届运动会的运动员信息
	 * @param departname
	 * @param sportsId
	 * @param grouptype
	 * @return
	 */
	public ArrayList selectPlayerItemsMessage( String username,int sportsId ,int grouptype ){
		ArrayList playerList = new ArrayList();
		int i = 0 ;
		SelGameApplyDAO sga = new SelGameApplyDAO();
		conn = db.getConn();
		String sql = "SELECT playernum,playername,groupid,registitem FROM t_player " +
				"WHERE groupid IN (  SELECT t_group.id FROM t_group JOIN t_group2sports ON t_group2sports.groupid = t_group.id WHERE grouptype = ?)" +
				" AND " + "sp2dpid IN( SELECT id FROM t_sports2department WHERE sportsid = ? AND " +
				"departid IN (SELECT departid FROM t_sysadmin WHERE username=?));";
		try{
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, grouptype);
			pStatement.setInt(2, sportsId);
			pStatement.setString(3, username);
			rs=pStatement.executeQuery();
			while(rs.next()){
				i++;
				SelPlayerByGroupPojo player = new SelPlayerByGroupPojo();
				player.setPlayernumID(i);
				player.setPlayernum(rs.getString(1));
				player.setPlayername(rs.getString(2));
				player.setGroupid(rs.getInt(3));
				String items[] = sga.getItemCountBySportsId(rs.getString(4), sportsId,grouptype);
				player.setItems(items);
				playerList.add(player);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return playerList;
	}
	/**
	 * 获取系别名称
	 * @param username
	 * @return departName
	 * @author 
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
		}
		return departName;
	}
	/**
	 * 查询组别类型，即学生组、教工组
	 * @param sportsid
	 * @return
	 */
	public ArrayList selectAllGroupTypes(int sportsid,String username ){
		ArrayList<SelPlayerByGroupPojo> groupTypeList = new ArrayList<SelPlayerByGroupPojo>();
		String sql = "SELECT DISTINCT(grouptype) FROM t_group WHERE id IN( " +
				"SELECT groupid FROM t_player WHERE sp2dpid IN ( " +
				"SELECT id FROM t_sports2department WHERE departid IN ( " +
				"SELECT departid FROM t_sysadmin WHERE username = ? ) AND sportsid = ?))";
		try{
			conn = db.getConn();
			pStatement = conn.prepareStatement(sql);		
			pStatement.setInt(2, sportsid);			
			pStatement.setString(1, username);
			rs=pStatement.executeQuery();
			while(rs.next()){
				SelPlayerByGroupPojo groupType = new SelPlayerByGroupPojo();
				groupType.setGrouptype(rs.getInt(1));
				groupTypeList.add(groupType);				
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);	
		}catch(SQLException e){
			e.printStackTrace();	
		}
			return groupTypeList;	
	}
	/**
	 * 查询该组别类型的所有组别
	 * @param sportsid
	 * @param grouptype
	 * @return groupList
	 */
	public ArrayList selectGroupNameByGroupType(int sportsid , int grouptype){
		ArrayList groupNameList = new ArrayList();
		String sql = "SELECT id,groupname FROM t_group" +
					" WHERE id IN( SELECT groupid FROM t_group2sports WHERE sportsid=?)AND grouptype=?";
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, sportsid);
			pStatement.setInt(2, grouptype);
			rs=pStatement.executeQuery();
			while(rs.next()){
				SelPlayerByGroupPojo groupName = new SelPlayerByGroupPojo();
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
	 * 根据组别类型，分别查询当届运动会项目
	 * @author 
	 * @param sportsid
	 * @param grouptype
	 * @return itemList
	 */
	public ArrayList selectAllItemsByGroupType(int sportsid , int grouptype){
		ArrayList itemList = new ArrayList();
		String sql="SELECT id, itemname,itemtype FROM t_item WHERE id IN " +
					"(SELECT itemid FROM t_group2item WHERE matchtype<>0 AND gp2spid IN " +
					"(SELECT id FROM t_group2sports WHERE sportsid =? AND groupid IN" +
					" (SELECT id FROM t_group WHERE grouptype=?)))";
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, sportsid);
			pStatement.setInt(2, grouptype);
			rs=pStatement.executeQuery();
			while(rs.next()){
				SelPlayerByGroupPojo item = new SelPlayerByGroupPojo();
				item.setItemid(rs.getInt(1));
				item.setItemname(rs.getString(2));
				item.setItemtype(rs.getString(3));
				itemList.add(item);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return itemList;
	}
	/**
	 * 查询本系当届运动会的领队、教练、队医
	 * @param sportsid
	 * @param departid
	 * @return
	 */
	public ArrayList selectAllLeaderByUserName(String username){
		ArrayList leaderList = new ArrayList();
		String sql = "SELECT teamleader,coach,doctor FROM t_sports2department WHERE departid " +
					"IN (SELECT departid FROM t_sysadmin WHERE username=?)" +
							" AND sportsid IN (SELECT id FROM t_sports WHERE current=1)";
		try{
			conn = db.getConn();
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs=pStatement.executeQuery();
			while (rs.next()){
				SelPlayerByGroupPojo leader = new SelPlayerByGroupPojo();
				leader.setTeamleader(rs.getString(1));
				leader.setCoach(rs.getString(2));
				leader.setDoctor(rs.getString(3));
				leaderList.add(leader);
			}	
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return leaderList;
	}
	/**
	 * 修改该系领队信息
	 * @param teamleader
	 * @param coach
	 * @param doctor
	 * @return
	 */
	public boolean updateLeaderByUserName(String username,String teamleader,String coach,String doctor){
		boolean retuValue=false;
		String sql = "update t_sports2department set teamleader='"+teamleader+"',coach='"+coach+"'," +
					"doctor='"+doctor+"' "+"where departid IN (SELECT departid FROM t_sysadmin WHERE username='"+username+"')";
		try{
			conn = db.getConn();
			conn.createStatement().executeUpdate(sql);
			retuValue = true;
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return retuValue;
	}



/**
 * 获取报选的项目id
 * @param registitem
 * @param sportsid
 * @param grouptype
 * @return String nums[]
 */
	public String[] getItemCountBySportsId(String registitem,int sportsid,int grouptype){
		ArrayList list = new ArrayList();
		String sql = "SELECT id, itemname FROM t_item WHERE id IN " +
				"(SELECT itemid FROM t_group2item WHERE matchtype<>0 AND gp2spid IN " +
				"(SELECT id FROM t_group2sports WHERE sportsid =? AND groupid IN" +
				"(SELECT id FROM t_group WHERE grouptype=?))) ORDER BY t_item.id";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsid);
			pStatement.setInt(2, grouptype);
			rs = pStatement.executeQuery();
			while( rs.next() ){
				list.add(rs.getInt(1));
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch( Exception e){
			e.getStackTrace();
		}
		
//获得运动员所报项目id
		String itemsid[] = registitem.split(";");
		String nums[] = new String[list.size()];
		for(int i = 0;i<list.size();i++){
			boolean flag = true;
			if(flag = true){
				for(int j = 0;j<itemsid.length;j++){
					if(itemsid[j].equals(list.get(i).toString())){
						nums[i] = " checked";
						j =Integer.parseInt(itemsid[j++]);
						continue;
					}
				}
			}
		}
		return nums;
	}
	public int infoDelete(int sp2dpid,String playernum){
		int flag = 0;
		String sql = "";
		sql = "UPDATE t_player" +
			" SET playername=NULL,playersex=NULL,groupid=NULL,registitem=NULL,numid=NULL" +
			" WHERE sp2dpid=? AND playernum=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sp2dpid);
			pStatement.setString(2, playernum);
			flag = pStatement.executeUpdate();
			pStatement.close();
			conn.close();
		}catch( Exception e){
			e.getStackTrace();
		}
		return flag;
	}
	
}