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
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Group;
import cn.edu.hbcit.smms.pojo.Group2itemPojo;
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
	protected final Logger log = Logger.getLogger(ItemDAO.class.getName());
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
	/**
	 * 获取指定某届运动会的项目信息:
	 * 当届gp2spid、itemid、matchtype，用,连接
	 * 如："9,1,2"
	 * @return
	 */
	public ArrayList selectItemStringOfSports(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT t_group2item.gp2spid,t_group2item.itemid,t_group2item.matchtype FROM t_group2item,t_group2sports WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2sports.sportsid=?";
		conn = db.getConn();
		String gp2spidItemidMatchtype = "";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Group2itemPojo gi = new Group2itemPojo();
				gp2spidItemidMatchtype =Integer.toString(rs.getInt(1)) + "," + Integer.toString(rs.getInt(2)) + "," + Integer.toString(rs.getInt(3));
				log.debug("gp2spidItemidMatchtype连接后的串为："+gp2spidItemidMatchtype);
				gi.setGp2spidItemidMatchtype(gp2spidItemidMatchtype);
				list.add(gi);
			}
			db.freeConnection(rs,pStatement,conn);
		}catch(Exception e){
			log.error("获取指定某届运动会的项目信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 获取指定某届运动会的项目信息:
	 * 当届t_group2item中的id,gp2spid、itemid、matchtype，用,连接
	 * 如："11,9,1,2"
	 * @return
	 */
	public ArrayList selectItemStringOfSportsIncludeId(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT t_group2item.id,t_group2item.gp2spid,t_group2item.itemid,t_group2item.matchtype FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)";
		conn = db.getConn();
		String gp2spidItemidMatchtype = "";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Group2itemPojo gi = new Group2itemPojo();
				gp2spidItemidMatchtype =Integer.toString(rs.getInt(1)) + "," + Integer.toString(rs.getInt(2)) + "," + Integer.toString(rs.getInt(3))+ "," + Integer.toString(rs.getInt(4));
				log.debug("Id,gp2spid,Itemid,Matchtype连接后的串为："+gp2spidItemidMatchtype);
				gi.setGp2spidItemidMatchtype(gp2spidItemidMatchtype);
				list.add(gi);
			}
			db.freeConnection(rs,pStatement,conn);
		}catch(Exception e){
			log.error("获取指定某届运动会的项目信息(含id)失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 更新t_group2item数据
	 * @param itemInfo[i] 格式为：gp2spid,itemid,matchtype 如：4,2,5
	 * @return
	 */
	public boolean updateGroup2Item(String[] itemInfo){
		boolean flag = false;
		int count[]; 
		String sql = "UPDATE t_group2item SET matchtype=? WHERE gp2spid=? AND itemid=?";
		try{
			conn = db.getConn();
			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(sql);

			for(int i=0; i<itemInfo.length; i++){
				String[] tempArray = itemInfo[i].split(",");
				pStatement.setString(1, tempArray[2]);
				pStatement.setInt(2, Integer.parseInt(tempArray[0]));
				pStatement.setInt(3, Integer.parseInt(tempArray[1]));
				pStatement.addBatch();
			}
			count = pStatement.executeBatch();
			conn.commit();
			for(int i : count){  
			    if(i == 0) {
			    	conn.rollback();              // 回滚，非常重要 
			    	log.error("更新t_group2item数据出现异常，回滚=========》"); 
			    	flag = false;
			    	break;
			    }else{
			    	flag = true;
			    }
			   }
		}catch(Exception e){
			try{
				// 回滚，非常重要  
				conn.rollback();
			}catch(SQLException e1){
				log.error(e1.getMessage());
			}
			log.error("更新t_group2item数据失败！");
			log.error(e.getMessage());
		}finally{
			db.freeConnection(pStatement,conn);
		}

		return flag;
	}
	
	/**
	 * 根据Matchtype获取应拆分成的Finalitem数量
	 * Matchtype='1'预决赛计数1；Matchtype='2'预赛+决赛 计数2
	 * @param sportsId
	 * @return
	 */
	public int countMatchtype(int sportsId){
		int rst = 0;
		String sql = "SELECT DISTINCT (SELECT COUNT(*) FROM t_group2item,t_group2sports WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2sports.sportsid=? AND matchtype='1')+(SELECT COUNT(*)*2 FROM t_group2item,t_group2sports WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2sports.sportsid=? AND matchtype='2') AS c1 FROM t_group2item";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				rst = rs.getInt(1);
			}
			db.freeConnection(rs, pStatement, conn);
		}catch(Exception e){
			log.error("获取此届运动会的Matchtype数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	public boolean splitFinalitem(int sportsId){
		boolean flag = false;
		//查询指定运动会ID的gp2spid和matchtype
		String sql_QueryGp2spidAndMatchtype = "SELECT t_group2item.id,t_group.groupname ,t_item.itemname,t_group2item.matchtype FROM t_group2item,t_group2sports,t_item,t_group WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2item.itemid=t_item.id AND t_group2sports.groupid=t_group.id AND t_group2item.matchtype<>'0' AND t_group2sports.sportsid=?";
		//插入拆分后的t_finalitem
		String sql_InsertFinalitem = "INSERT INTO t_finalitem (gp2itid,finalitemname,finalitemtype,sportsid) VALUES (?,?,?,?)";
		int count[]; 
		try{
			conn = db.getConn();
			pStatement = conn.prepareStatement(sql_QueryGp2spidAndMatchtype);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			
			//以下为批量处理用
			PreparedStatement tempPs = null;
			Connection tempConn = null;
			tempConn = db.getConn();
			tempConn.setAutoCommit(false);// 需要用到事务，不能让他自动提交，需要手动提交
			tempPs = tempConn.prepareStatement(sql_InsertFinalitem);
			while(rs.next()){
				if(rs.getInt(4) == 1){  //如果检索出来matchtype是“预决赛”
					tempPs.setInt(1, rs.getInt(1));//gp2itid
					tempPs.setString(2, rs.getString(2) + rs.getString(3) + "预决赛");//finalitemname
					tempPs.setString(3, "3");//finalitemtype  3代表预决赛
					tempPs.setInt(4, sportsId);//sportsid
					tempPs.addBatch();
				}
				if(rs.getInt(4) == 2){  //如果检索出来matchtype是“预赛+决赛”,则添加两条记录
					tempPs.setInt(1, rs.getInt(1));//gp2itid
					tempPs.setString(2, rs.getString(2) + rs.getString(3) + "预赛");//finalitemname
					tempPs.setString(3, "1");//finalitemtype  3代表预决赛
					tempPs.setInt(4, sportsId);//sportsid
					tempPs.addBatch();
					//
					tempPs.setInt(1, rs.getInt(1));//gp2itid
					tempPs.setString(2, rs.getString(2) + rs.getString(3) + "决赛");//finalitemname
					tempPs.setString(3, "2");//finalitemtype  3代表预决赛
					tempPs.setInt(4, sportsId);//sportsid
					tempPs.addBatch();
				}
			}
			count = tempPs.executeBatch();
			tempConn.commit();
			
			for(int i : count){  
			    if(i == 0) {
			    	tempConn.rollback();              // 回滚，非常重要 
			    	log.error("分割生成Finalitem出现异常，回滚=========》");
			     //System.out.println("======出现异常，回滚=========");  
			    }  
			   } 
			tempPs.close();
			tempConn.close();
		}catch(SQLException e){
			try{
				// 回滚，非常重要  
				conn.rollback();
			}catch(SQLException e1){
				log.error(e1.getMessage());
			}
			log.error("获取指定某届运动会的项目信息失败！splitFinalitem()");
			log.error(e.getMessage());
		}finally{
			db.freeConnection(rs,pStatement,conn);
		}
		return flag;
	}
	
}
