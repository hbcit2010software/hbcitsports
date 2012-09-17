package cn.edu.hbcit.smms.dao.createprogramdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.PlayerPojo;
/**
 *生成秩序册   运动员号码
 * @author 李兆珠
 *
 */
public class SelectPlayerDAO {
	DBConn db = new DBConn();
	ResultSet rs = null;
	Connection conn = null;
	private PreparedStatement ps;
	
	
	/**
	 * 根据部门查询学生运动员
	 * @return ArrayList
	 */
	public ArrayList selectPlayersByDept(int playersex,int departid,int sportsid ) {
		ArrayList playerList = new ArrayList();
		try {
			conn = db.getConn();
			ps = conn.prepareStatement(
					"SELECT * FROM t_player WHERE playersex=? AND sp2dpid " +
					"IN(SELECT id FROM t_sports2department" +
					" WHERE departid=? AND sportsid=?)");
			ps.setInt(1, playersex);
			ps.setInt(2, departid);
			ps.setInt(3, sportsid);
			rs=ps.executeQuery();
			while (rs.next()) {
				
				PlayerPojo player=new PlayerPojo();

				player.setPlayernum(rs.getString("playernum"));
				player.setPlayername(rs.getString("playername"));	
				playerList.add(player);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		db.freeConnection(conn);
		return playerList;
	}
	
	/**selectPlayersByDept1
	 * 
	 * @param departid
	 * @param sportsid
	 * @return
	 */
	public ArrayList selectPlayersByDept1(int departid,int sportsid ) {
		ArrayList playerList = new ArrayList();
		try {
			conn = db.getConn();
			ps = conn.prepareStatement(
					"SELECT * FROM t_player WHERE  sp2dpid IN(SELECT id" +
					" FROM t_sports2department WHERE departid=? AND sportsid=?)");
			
			ps.setInt(1, departid);
			ps.setInt(2, sportsid);
			rs=ps.executeQuery();
			while (rs.next()) {
				
				PlayerPojo player=new PlayerPojo();

				player.setPlayernum(rs.getString("playernum"));
				player.setPlayername(rs.getString("playername"));	
				playerList.add(player);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		db.freeConnection(conn);
		return playerList;
	}
	
	
	/**
	 * 查询部门id
	 * 
	 * @return ArrayList
	 */
	public ArrayList selectDepartment(){
		ArrayList departList = new ArrayList();
		try {
			conn = db.getConn();
			rs = conn.createStatement().executeQuery(
					" SELECT id FROM t_department  ORDER BY departtype DESC,id ");
			while (rs.next()) {
			Integer ig=new Integer(rs.getInt(1));
			departList.add(ig);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		db.freeConnection(conn);
	    return departList;
}
	
	/**
	 * 根据Id查询部门名称
	 * 
	 * @param id
	 * @return name
	 */
	public String selectDepartmentName(int id )
	{
		String name="";
		try {
			conn = db.getConn();
			ps = conn.prepareStatement(
					"SELECT departid FROM t_sports2department WHERE id=?");
			
		     ps.setInt(1, id);
		     //System.out.println("11111111111111111");
		     rs=ps.executeQuery();
			while (rs.next()) {
			
			 name=new String(rs.getString("departname"));
			
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		db.freeConnection(conn);
		return name;
	}
	/**
	 * 根据Id查询部门类型
	 * 
	 * @param id
	 * @return  type	 */
	public int selectDepartmentType(int id )
	{
		int type=0;
		try {
			conn = db.getConn();
			ps = conn.prepareStatement(
					"SELECT departtype FROM t_department WHERE id=?");
			
		     ps.setInt(1, id);
		     rs=ps.executeQuery();
			while (rs.next()) {
			type=rs.getInt("departtype");
				}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		db.freeConnection(conn);
		return type;
	}

}
