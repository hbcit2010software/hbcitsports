/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   项目设置
*
* 备注：
*
* 修改历史：
* 2012-7-18	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Group;
import cn.edu.hbcit.smms.pojo.Item;

/**
 * 项目设置类
 * 简要说明:
 * 负责运动会赛前管理——项目设置
 * @author 李玮
 * @version 1.00  2012-7-18下午10:15:07	新建
 */

public class ItemDAO {

	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(SportsDAO.class.getName());
	DBConn db = new DBConn();
	
	//DELETE FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?) 删除指定运动会的t_group2item信息
	/**
	 * 获取所有项目信息
	 * @return
	 */
	public ArrayList selectItemInfo(){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,itemname,itemtype,scoreformatid FROM t_item";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Item item = new Item();
				item.setId(rs.getInt(1));
				item.setItemname(rs.getString(2));
				item.setItemtype(rs.getInt(3));
				item.setScoreformatid(rs.getInt(4));
				list.add(item);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取项目信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 获取指定某届运动会的组别信息:
	 * 当届group2sports的id值、组别名称groupname
	 * @return
	 */
	public ArrayList selectGroupOfSports(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT t_group2sports.id,t_group.groupname FROM t_group2sports INNER JOIN t_group WHERE t_group2sports.sportsid=? AND t_group.id=t_group2sports.groupid ORDER BY t_group.groupname ASC";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Group gp = new Group();
				gp.setId(rs.getInt(1));
				gp.setGroupname(rs.getString(2));
				list.add(gp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取此届运动会的组别信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 删除指定ID运动会的项目信息
	 * @param sportsId
	 * @return
	 */
	public boolean removeGroupToItem(int sportsId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("删除指定ID运动会的项目信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 为t_group2item表插入数据构建SQL语句
	 * INSERT INTO t_group2item (gp2spid,itemid,matchtype) VALUES (1,1,'3'), (1,2,'3')  插入多行
	 * @param sourceArray
	 * @return
	 */
	public String getSqlOfInsertT_group2item(String[] sourceArray){
		StringBuilder result = new StringBuilder();
		if(sourceArray.length == 0){
			//sourceArray.length == 0说明前台传的值全部被过滤掉了,那么就生成一个永假的sql语句
			result.append("UPDATE t_group2item SET gp2spid=1 WHERE 1=2");
		}else{
			result.append("INSERT INTO t_group2item (gp2spid,itemid,matchtype) VALUES ");
			for(int i=0; i<sourceArray.length; i++){
				String[] tempArray = sourceArray[i].split(",");
				result.append("(");
				result.append(tempArray[0]);
				result.append(",");
				result.append(tempArray[1]);
				result.append(",'");
				result.append(tempArray[2]);
				result.append("')");
				if(i != sourceArray.length-1){
					result.append(",");
				}
			}
		}
		
		log.debug("插入t_group2item表的SQL语句是：" + result.toString());
		return result.toString();
	}
	/**
	 * 插入项目与组别的关联信息（t_group2item）
	 * @param sql 已构建好的insert语句
	 * @return
	 */
	public boolean addGroupToItem(String sql){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		
		try{
			pStatement = conn.prepareStatement(sql);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("插入项目与组别的关联信息（t_group2item）失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 获取t_group2item中符合本届运动会的数量
	 * @param sportsId
	 * @return
	 */
	public int countGroupToItem(int sportsId){
		int rst = 0;
		conn = db.getConn();
		String sql = "SELECT COUNT(DISTINCT t_group2item.id) FROM t_group2item INNER JOIN t_group2sports WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				rst = rs.getInt(1);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取此届运动会的项目数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
}
