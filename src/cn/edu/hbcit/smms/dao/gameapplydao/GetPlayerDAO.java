package cn.edu.hbcit.smms.dao.gameapplydao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.Item;
import cn.edu.hbcit.smms.pojo.Player;
import cn.edu.hbcit.smms.pojo.Player;
import cn.edu.hbcit.smms.pojo.PlayerNum;
import cn.edu.hbcit.smms.pojo.Sports;


/**
 * 报名类
 *
 * 本类的简要描述：
 * 学生、教工报名界面的获取
 *
 * @author 吕志瑶
 * @version 1.00  2012-6-25 新建类
 */
public class GetPlayerDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	CallableStatement cs = null;
	private Connection conn;
	ResultSet rs;
	protected final Logger log = Logger.getLogger(GetPlayerDAO.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 根据用户名获取departid
	 * @author 陈系晶
	 * @param username
	 * @return
	 */
	public int getDepartid(String username){
		int rst = 0;
		String sql = "SELECT departid FROM t_sysadmin where username=?";
		conn = db.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				rst = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取部门id失败！");
			log.error(e.getMessage());
		}
		return rst ;
	}
	
	/**
	 * 根据运动会Id和部门Id获取Sp2dpid
	 * @author 陈系晶
	 * @param flag1
	 * @return
	 */
	public int getSp2dpid(int flag1){
		LoginDAO loginDAO = new LoginDAO();
		int sportsid = loginDAO.selectCurrentSportsId();//获取运动会Id
		int departid = flag1;
		int rst = 0;
		String sql = "SELECT id FROM  t_sports2department where departid=? and sportsid=?";
		conn = db.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, departid);
			pstmt.setInt(2, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				rst = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取运动会-部门联系表id失败！");
			log.error(e.getMessage());
		}
		return rst ;
	}
	
	/**
	 * 根据sp2dpid查找运动员号码
	 * @param sp2dpid
	 * @return
	 */	
	public ArrayList getPlayerNum(int sp2dpid,int numtype){
		ArrayList list = new ArrayList();
		String sql = "select beginnum,endnum from t_playernum where sp2dpid = ? and numtype = ? ";
		DBConn db = new DBConn();
			try{
				conn = db.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sp2dpid);
				pstmt.setInt(2, numtype);
				rs = pstmt.executeQuery();
				while( rs.next() ){
					PlayerNum num = new PlayerNum();
					num.setBeginnum(rs.getString(1));
					num.setEndnum(rs.getString(2));
					//System.out.println(rs.getString(1)+rs.getString(2));
					list.add(num);
					
				}
				db.freeConnection(conn);
			}catch( Exception e){
				e.getStackTrace();
				System.out.println(e.getMessage());
			}
			return list;
		}
/**
 * 根据运动会名字和组别的类型获得运动会的比赛项目总数	
 * @param sportsname
 * @param grouptype
 * @return
 */
	public int getItemNumber( String sportsname,int grouptype){
		int flag = 0;
		String sql = "SELECT COUNT(itemname) FROM t_item WHERE id IN " +
				"(SELECT itemid FROM t_group2item WHERE gp2spid IN " +
				"(SELECT id FROM t_group2sports WHERE groupid IN " +
				"(SELECT id FROM t_group WHERE grouptype = ?) AND sportsid IN " +
				"(SELECT id FROM t_sports WHERE sportsname = ?	)))";
		try{
			conn = db.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grouptype);
			pstmt.setString(2, sportsname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				flag = rs.getInt(1);
			}
			db.freeConnection(conn);
		}catch( Exception e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return flag;
	}
	/**
	 * 根据运动会名字和组别的类型获得运动会的比赛项目
	 * @param sportsname
	 * @param grouptype
	 * @return
	 */
	public ArrayList getItemName(String sportsname,int grouptype){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,itemname FROM t_item WHERE id IN " +
				"(SELECT itemid FROM t_group2item WHERE gp2spid IN " +
				"(SELECT id FROM t_group2sports WHERE groupid IN " +
				"(SELECT id FROM t_group WHERE grouptype = ?) AND sportsid IN " +
				"(SELECT id FROM t_sports WHERE sportsname = ?	)))";
		try{
			conn = db.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grouptype);
			pstmt.setString(2, sportsname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				Item itemname = new Item();
				itemname.setItemid(rs.getInt(1));
				itemname.setItemname(rs.getString(2));
				log.debug(itemname.getItemid()+"))))"+itemname.getItemname());
				list.add(itemname);
			}
			
		}catch( Exception e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		db.freeConnection(conn);
		return list;
	} 
/**
 * 获得数据库内时间最近的运动会名字	
 * @return
 */
	public String getSportsName()
	{
		String sportsname = null;
		String sql="SELECT sportsname FROM t_sports ORDER BY sportsbegin DESC LIMIT 1";
		try{
			conn=db.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				sportsname = rs.getString(1);
			}
			db.freeConnection(conn);
		}catch(Exception e){
			e.getStackTrace();
		    System.out.println(e.getMessage());
		}
		
		return sportsname;
	}	
/**
* 获取本届运动会id
* @return
*/
	public int getSportID(){
		int flag = 0;
		String sql = "SELECT id FROM t_sports where current=1";
		conn = db.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				flag = rs.getInt(1);
			}
			db.freeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("getSportID"+e.getMessage());
		}
		return flag ;
	}
	
	
/**
* 获取本届运动会所有的分组
* @author 陈系晶
* @param sportsid
* @return groupNameList
*/
	public ArrayList selectAllGroupName(){
		ArrayList groupNameList = new ArrayList();
		String sql = "SELECT id,groupname FROM t_group WHERE id IN" +
				"( SELECT groupid FROM t_group2sports WHERE sportsid = ?)" +
				"AND grouptype = 0";
		conn = db.getConn();
		try{
			GetPlayerDAO gpa = new GetPlayerDAO();
			int sid = gpa.getSportID();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Player group = new Player();
				group.setId(rs.getInt(1));
				group.setGroupname(rs.getString(2));
				groupNameList.add(group);
				
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return groupNameList;
	}
	
	/**
	 * 根据sql语句添加数据
	 * @param sql  要执行的sql语句
	 */
	public void addPlayerBySql(String sql){
		conn = db.getConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.executeUpdate(); 
            }
            db.freeConnection(conn);  
            }catch (SQLException e) {                  	
            e.printStackTrace(); } 
    }
		
}
