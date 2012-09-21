/**
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称： 赛前设置
 * 子模块名称： 项目设置
 *
 * 备注：
 *
 * 修改历史：
 * 2012-7-18 0.1 李玮 新建
 */
package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import hirondelle.date4j.DateTime;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.FinalItem;
import cn.edu.hbcit.smms.pojo.Group;
import cn.edu.hbcit.smms.pojo.Group2itemPojo;
import cn.edu.hbcit.smms.pojo.Item;
import cn.edu.hbcit.smms.pojo.PlayerNum;
import cn.edu.hbcit.smms.pojo.Sports2department;

/**
 * 项目设置类 简要说明: 负责运动会赛前管理——项目设置
 * 
 * @author 李玮
 * @version 1.00 2012-7-18下午10:15:07 新建
 */

public class ItemDAO {

	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(ItemDAO.class.getName());
	DBConn db = new DBConn();

	// DELETE FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT
	// t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)
	// 删除指定运动会的t_group2item信息
	/**
	 * 获取所有项目信息
	 * 
	 * @return
	 */
	public ArrayList selectItemInfo() {
		ArrayList list = new ArrayList();
		String sql = "SELECT id,itemname,itemtype,scoreformatid FROM t_item";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt(1));
				item.setItemname(rs.getString(2));
				item.setItemtype(rs.getString(3));
				item.setScoreformatid(rs.getInt(4));
				list.add(item);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		} catch (Exception e) {
			log.error("获取项目信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取指定某届运动会的组别信息: 当届group2sports的id值、组别名称groupname
	 * 
	 * @return
	 */
	public ArrayList selectGroupOfSports(int sportsId) {
		ArrayList list = new ArrayList();
		String sql = "SELECT t_group2sports.id,t_group.groupname FROM t_group2sports INNER JOIN t_group WHERE t_group2sports.sportsid=? AND t_group.id=t_group2sports.groupid ORDER BY t_group.groupname ASC";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Group gp = new Group();
				gp.setId(rs.getInt(1));
				gp.setGroupname(rs.getString(2));
				list.add(gp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		} catch (Exception e) {
			log.error("获取此届运动会的组别信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取本届运动会的举办日期（每天）
	 * 
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectSportsDays(int sportsId) {
		ArrayList list = new ArrayList();
		String sql = "SELECT sportsbegin,sportsend FROM t_sports WHERE id=?";
		String begin, end;
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				begin = rs.getString(1);
				end = rs.getString(2);
				DateTime beginDt = new DateTime(begin);
				DateTime endDt = new DateTime(end);
				log.debug("起始日期：" + begin + "结束日期：" + end + "，相差天数："
						+ beginDt.numDaysFrom(endDt));
				for (int i = 0; i < beginDt.numDaysFrom(endDt) + 1; i++) {
					list.add(beginDt.plusDays(i).format("YYYY-MM-DD").toString());
					log.debug("运动会举办日期是："
							+ beginDt.plusDays(i).format("YYYY-MM-DD").toString());
				}

			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取本届运动会的举办日期失败！");
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 为前台页面显示而获取FinalItem信息
	 * 
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectFinalItem(int sportsId) {
		ArrayList list = new ArrayList();
		// 查询项目名称、比赛阶段、项目类型、运动员类型等信息
		String sql = "SELECT DISTINCT t_finalitem.id,t_finalitem.finalitemname,t_finalitem.finalitemtype,t_item.itemtype,t_group.grouptype,t_finalitem.date,t_finalitem.time,t_finalitem.promotionnum FROM t_finalitem,t_item,t_group,t_group2item,t_group2sports,t_sports WHERE t_finalitem.gp2itid=t_group2item.id AND t_group2item.itemid=t_item.id AND t_group2item.gp2spid=t_group2sports.id AND t_group2sports.groupid=t_group.id AND t_finalitem.sportsid=? ORDER BY t_finalitem.finalitemtype,t_item.itemtype,t_finalitem.finalitemname";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				FinalItem fi = new FinalItem();
				fi.setId(rs.getInt(1));
				fi.setFinalitemname(rs.getString(2));
				fi.setFinalitemtype(rs.getString(3));
				fi.setItemtype(rs.getString(4));
				fi.setGrouptype(rs.getInt(5));
				fi.setDate(rs.getString(6));
				fi.setTime(rs.getString(7));
				fi.setPromotionnum(rs.getInt(8));
				list.add(fi);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的FinalItem信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 为前台页面显示而获取playernum信息
	 * 
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectPlayernum(int sportsId) {
		ArrayList list = new ArrayList();
		String sql = "SELECT t_playernum.id,t_department.departshortname,t_playernum.beginnum,t_playernum.endnum,t_playernum.numtype FROM t_playernum,t_sports2department,t_department WHERE t_playernum.sp2dpid=t_sports2department.id AND t_sports2department.departid=t_department.id AND t_sports2department.sportsid=? ORDER BY t_playernum.id ASC";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				PlayerNum pn = new PlayerNum();
				pn.setId(rs.getInt(1));
				pn.setDepartshortname(rs.getString(2));
				pn.setBeginnum(rs.getString(3));
				pn.setEndnum(rs.getString(4));
				pn.setNumtype(rs.getInt(5));
				list.add(pn);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的playernum信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 查询指定运动会的sp2dpid和departtype（该部门是否有学生）
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectSp2DpID(int sportsId) {
		ArrayList list = new ArrayList();
		String sql = "SELECT t_sports2department.id,t_department.departtype FROM t_sports2department,t_department WHERE t_department.id=t_sports2department.departid AND sportsid=?";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Sports2department sd = new Sports2department();
				sd.setId(rs.getInt(1));
				sd.setDeparttype(rs.getInt(2));
				list.add(sd);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的sp2dpid信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 构建插入T_playernum表的SQL语句
	 * @param sourceArray
	 * @return
	 */
	public String getSqlOfInsertT_playernum(ArrayList sourceArray) {
		//sourceArray: {[9,1],[12,1],[14,0]}
		StringBuilder result = new StringBuilder();
		if (sourceArray.size() == 0) {
			// sourceArray.size() == 0说明没有找到有效数据,那么就生成一个永假的sql语句
			result.append("UPDATE t_group2item SET gp2spid=1 WHERE 1=2");
		} else {
			result.append("INSERT INTO t_playernum (sp2dpid,numtype) VALUES  ");
			for (int i = 0; i < sourceArray.size(); i++) {
				result.append("( ");
				result.append(((Sports2department)sourceArray.get(i)).getId());
				if( ((Sports2department)sourceArray.get(i)).getDeparttype() == 0 ){ //该部门没有学生,则只添加一条
					result.append(" , ");
					result.append(0);
					result.append(" )");
				}else if( ((Sports2department)sourceArray.get(i)).getDeparttype() == 1 ){//该部门有学生，则添加两条
					result.append(" , ");
					result.append(1);
					result.append(" ),");
					result.append("( ");
					result.append(((Sports2department)sourceArray.get(i)).getId());
					result.append(" , ");
					result.append(0);
					result.append(" )");
				}
				
				if (i != sourceArray.size() - 1) {
					result.append(",");
				}
			}
		}

		log.debug("插入t_playernum表的SQL语句是：" + result.toString());
		return result.toString();
	}
	
	/**
	 * 删除指定ID运动会的项目信息
	 * 
	 * @param sportsId
	 * @return
	 */
	public boolean removeGroupToItem(int sportsId) {
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rst = pStatement.executeUpdate();
			//
			if (rst > 0) {
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		} catch (Exception e) {
			log.error("删除指定ID运动会的项目信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 删除指定ID运动会的运动员编号信息
	 */
	public boolean removePlayernum(int sportsId) {
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_playernum WHERE t_playernum.sp2dpid IN (SELECT t_sports2department.id FROM t_sports2department WHERE t_sports2department.sportsid=?)";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rst = pStatement.executeUpdate();
			//
			if (rst > 0) {
				flag = true;
			}
			db.freeConnection(pStatement,conn);
		} catch (Exception e) {
			log.error("删除指定ID运动会的运动员编号信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 为t_group2item表插入数据构建SQL语句 INSERT INTO t_group2item
	 * (gp2spid,itemid,matchtype) VALUES (1,1,'3'), (1,2,'3') 插入多行
	 * 
	 * @param sourceArray
	 * @return
	 */
	public String getSqlOfInsertT_group2item(String[] sourceArray) {
		StringBuilder result = new StringBuilder();
		if (sourceArray.length == 0) {
			// sourceArray.length == 0说明前台传的值全部被过滤掉了,那么就生成一个永假的sql语句
			result.append("UPDATE t_group2item SET gp2spid=1 WHERE 1=2");
		} else {
			result.append("INSERT INTO t_group2item (gp2spid,itemid,matchtype) VALUES ");
			for (int i = 0; i < sourceArray.length; i++) {
				String[] tempArray = sourceArray[i].split(",");
				result.append("(");
				result.append(tempArray[0]);
				result.append(",");
				result.append(tempArray[1]);
				result.append(",'");
				result.append(tempArray[2]);
				result.append("')");
				if (i != sourceArray.length - 1) {
					result.append(",");
				}
			}
		}

		log.debug("插入t_group2item表的SQL语句是：" + result.toString());
		return result.toString();
	}

	/**
	 * 插入项目与组别的关联信息（t_group2item）
	 * 
	 * @param sql
	 *            已构建好的insert语句
	 * @return
	 */
	public boolean addGroupToItem(String sql) {
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();

		try {
			pStatement = conn.prepareStatement(sql);
			rst = pStatement.executeUpdate();
			//
			if (rst > 0) {
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		} catch (Exception e) {
			log.error("插入项目与组别的关联信息（t_group2item）失败！");
			log.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 插入t_playernum
	 * 
	 * @param sql
	 *            已构建好的insert语句
	 * @return
	 */
	public boolean addT_playernum(String sql) {
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();

		try {
			pStatement = conn.prepareStatement(sql);
			rst = pStatement.executeUpdate();
			//
			if (rst > 0) {
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		} catch (Exception e) {
			log.error("插入t_playernum失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 获取t_group2item中符合本届运动会的数量
	 * 
	 * @param sportsId
	 * @return
	 */
	public int countGroupToItem(int sportsId) {
		int rst = 0;
		conn = db.getConn();
		String sql = "SELECT COUNT(DISTINCT t_group2item.id) FROM t_group2item INNER JOIN t_group2sports WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				rst = rs.getInt(1);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		} catch (Exception e) {
			log.error("获取此届运动会的项目数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}

	/**
	 * 获取指定某届运动会的项目信息: 当届gp2spid、itemid、matchtype，用,连接 如："9,1,2"
	 * 
	 * @return
	 */
	public ArrayList selectItemStringOfSports(int sportsId) {
		ArrayList list = new ArrayList();
		String sql = "SELECT t_group2item.gp2spid,t_group2item.itemid,t_group2item.matchtype FROM t_group2item,t_group2sports WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2sports.sportsid=?";
		conn = db.getConn();
		String gp2spidItemidMatchtype = "";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Group2itemPojo gi = new Group2itemPojo();
				gp2spidItemidMatchtype = Integer.toString(rs.getInt(1)) + ","
						+ Integer.toString(rs.getInt(2)) + ","
						+ Integer.toString(rs.getInt(3));
				log.debug("gp2spidItemidMatchtype连接后的串为："
						+ gp2spidItemidMatchtype);
				gi.setGp2spidItemidMatchtype(gp2spidItemidMatchtype);
				list.add(gi);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取指定某届运动会的项目信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取指定某届运动会的项目信息: 当届t_group2item中的id,gp2spid、itemid、matchtype，用,连接
	 * 如："11,9,1,2"
	 * 
	 * @return
	 */
	public ArrayList selectItemStringOfSportsIncludeId(int sportsId) {
		ArrayList list = new ArrayList();
		String sql = "SELECT t_group2item.id,t_group2item.gp2spid,t_group2item.itemid,t_group2item.matchtype FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=?)";
		conn = db.getConn();
		String gp2spidItemidMatchtype = "";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Group2itemPojo gi = new Group2itemPojo();
				gp2spidItemidMatchtype = Integer.toString(rs.getInt(1)) + ","
						+ Integer.toString(rs.getInt(2)) + ","
						+ Integer.toString(rs.getInt(3)) + ","
						+ Integer.toString(rs.getInt(4));
				log.debug("Id,gp2spid,Itemid,Matchtype连接后的串为："
						+ gp2spidItemidMatchtype);
				gi.setGp2spidItemidMatchtype(gp2spidItemidMatchtype);
				list.add(gi);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取指定某届运动会的项目信息(含id)失败！");
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 更新t_group2item数据
	 * 
	 * @param itemInfo
	 *            [i] 格式为：gp2spid,itemid,matchtype 如：4,2,5
	 * @return
	 */
	public boolean updateGroup2Item(String[] itemInfo) {
		boolean flag = false;
		int count[];
		String sql = "UPDATE t_group2item SET matchtype=? WHERE gp2spid=? AND itemid=?";
		try {
			conn = db.getConn();
			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(sql);

			for (int i = 0; i < itemInfo.length; i++) {
				String[] tempArray = itemInfo[i].split(",");
				pStatement.setString(1, tempArray[2]);
				pStatement.setInt(2, Integer.parseInt(tempArray[0]));
				pStatement.setInt(3, Integer.parseInt(tempArray[1]));
				pStatement.addBatch();
			}
			count = pStatement.executeBatch();
			conn.commit();
			for (int i : count) {
				if (i == 0) {
					conn.rollback(); // 回滚，非常重要
					log.error("更新t_group2item数据出现异常，回滚=========》");
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
		} catch (Exception e) {
			try {
				// 回滚，非常重要
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
			log.error("更新t_group2item数据失败！");
			log.error(e.getMessage());
		} finally {
			db.freeConnection(pStatement, conn);
		}

		return flag;
	}

	/**
	 * 更新t_finalitem时间、日期、晋级数量
	 * 
	 * @param finalItem
	 *            格式：id,date,time,promotionnum
	 * @return
	 */
	public boolean updateFinalItem(String[] finalItem) {
		boolean flag = false;
		int count[];
		String sql = "UPDATE t_finalitem SET date=?,time=?,promotionnum=? WHERE id=?";
		try {
			conn = db.getConn();
			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(sql);

			for (int i = 0; i < finalItem.length; i++) {
				// finalItem[i]格式：id,date,time,promotionnum
				String[] tempArray = finalItem[i].split(",");
				pStatement.setString(1, tempArray[1]);
				pStatement.setString(2, tempArray[2]);
				pStatement.setInt(3, Integer.parseInt(tempArray[3]));
				pStatement.setInt(4, Integer.parseInt(tempArray[0]));
				pStatement.addBatch();
			}
			count = pStatement.executeBatch();
			conn.commit();
			for (int i : count) {
				if (i == 0) {
					conn.rollback(); // 回滚，非常重要
					log.error("更新t_finalitem数据出现异常，回滚=========》");
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
		} catch (Exception e) {
			try {
				// 回滚，非常重要
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
			log.error("更新t_finalitem数据失败！");
			log.error(e.getMessage());
		} finally {
			db.freeConnection(pStatement, conn);
		}
		return flag;
	}
	/**
	 * 更新t_playernum起始号段、结束号段
	 * 
	 * @param finalItem
	 *            格式：id,beginnum,endnum
	 * @return
	 */
	public boolean updatePlayerNum(String[] playerNum) {
		boolean flag = false;
		int count[];
		String sql = "UPDATE t_playernum SET beginnum=?,endnum=? WHERE id=?";
		try {
			conn = db.getConn();
			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(sql);

			for (int i = 0; i < playerNum.length; i++) {
				// finalItem[i]格式：id,date,time,promotionnum
				String[] tempArray = playerNum[i].split(",");
				log.debug("updatePlayerNum的SQL语句为：UPDATE t_playernum SET beginnum="+tempArray[1]+",endnum="+tempArray[2]+" WHERE id="+tempArray[0]);
				pStatement.setString(1, tempArray[1]);    //beginnum
				pStatement.setString(2, tempArray[2]);    //endnum
				pStatement.setInt(3, Integer.parseInt(tempArray[0]));    //id
				pStatement.addBatch();
			}
			count = pStatement.executeBatch();
			log.debug(count[0]);
			conn.commit();
			for (int i : count) {
				log.debug("更新状态："+i);
				if (i == 0) {
					conn.rollback(); // 回滚，非常重要
					log.error("更新t_playernum数据出现异常，回滚=========》");
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
		} catch (Exception e) {
			try {
				// 回滚，非常重要
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
			log.error("更新t_playernum数据失败！");
			log.error(e.getMessage());
		} finally {
			db.freeConnection(pStatement, conn);
		}
		return flag;
	}

	/**
	 * 根据Matchtype获取应拆分成的Finalitem数量 Matchtype='1'预决赛计数1；Matchtype='2'预赛+决赛 计数2
	 * 本方法可用来验证Finalitem是否已经拆分
	 * 
	 * @param sportsId
	 * @return
	 */
	public int countMatchtype(int sportsId) {
		int rst = 0;
		String sql = "SELECT DISTINCT (SELECT COUNT(*) FROM t_group2item,t_group2sports WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2sports.sportsid=? AND matchtype='1')+(SELECT COUNT(*)*2 FROM t_group2item,t_group2sports WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2sports.sportsid=? AND matchtype='2') AS c1 FROM t_group2item";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				rst = rs.getInt(1);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的Matchtype数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}

	/**
	 * 获取此届运动会的FinalItem数量 本方法可用来验证Finalitem是否已经拆分(判断与countMatchtype()方法返回值是否相等)
	 * 
	 * @param sportsId
	 * @return
	 */
	public int countFinalItem(int sportsId) {
		int rst = 0;
		String sql = "SELECT COUNT(*) FROM t_finalitem WHERE sportsid=?";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				rst = rs.getInt(1);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的FinalItem数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}

	/**
	 * 获取此届运动会的sports2department数量
	 * 
	 * @param sportsId
	 * @return
	 */
	public int countSports2Department(int sportsId) {
		int rst = 0;
		String sql = "SELECT COUNT(*) FROM t_sports2department WHERE sportsid=?";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				rst = rs.getInt(1);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的sports2department数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 计算Numtype数量
	 * @param sportsId
	 * @return
	 */
	public int countNumtype(int sportsId) {
		int rst = 0;
		String sql = "SELECT ((SELECT COUNT(*) FROM t_department,t_sports2department WHERE t_sports2department.departid=t_department.id AND t_sports2department.sportsid=? AND t_department.departtype=0)+(SELECT COUNT(*) FROM t_department,t_sports2department WHERE t_sports2department.departid=t_department.id AND t_sports2department.sportsid=? AND t_department.departtype=1)*2)";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				rst = rs.getInt(1);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的Numtype数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 计算当前运动会的t_playernum总数
	 * @param sportsId
	 * @return
	 */
	public int countT_playernum(int sportsId) {
		int rst = 0;
		String sql = "SELECT COUNT(*) FROM t_playernum,t_sports2department WHERE t_playernum.sp2dpid=t_sports2department.id AND t_sports2department.sportsid=?";
		conn = db.getConn();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				rst = rs.getInt(1);
			}
			db.freeConnection(rs, pStatement, conn);
		} catch (Exception e) {
			log.error("获取此届运动会的t_playernum数量失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	/**
	 * 删除指定ID运动会的Final Item信息
	 * 
	 * @param sportsId
	 * @return
	 */
	public boolean removeFinalItem(int sportsId) {
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_finalitem WHERE sportsid=?";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rst = pStatement.executeUpdate();
			//
			if (rst > 0) {
				flag = true;
			}
			// pStatement.close();
			db.freeConnection(pStatement, conn);
		} catch (Exception e) {
			log.error("删除指定ID运动会的Final Item信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 拆分Final Item
	 * 
	 * @param sportsId
	 * @return
	 */
	public int splitFinalitem(int sportsId) {
		int rst = 0;
		// 查询指定运动会ID的gp2spid和matchtype
		String sql_QueryGp2spidAndMatchtype = "SELECT t_group2item.id,t_group.groupname ,t_item.itemname,t_group2item.matchtype FROM t_group2item,t_group2sports,t_item,t_group WHERE t_group2sports.id=t_group2item.gp2spid AND t_group2item.itemid=t_item.id AND t_group2sports.groupid=t_group.id AND t_group2item.matchtype<>'0' AND t_group2sports.sportsid=?";
		// 插入拆分后的t_finalitem
		String sql_InsertFinalitem = "INSERT INTO t_finalitem (gp2itid,finalitemname,finalitemtype,sportsid) VALUES (?,?,?,?)";
		int count[] = {};
		try {
			conn = db.getConn();
			pStatement = conn.prepareStatement(sql_QueryGp2spidAndMatchtype);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();

			// 以下为批量处理用
			PreparedStatement tempPs = null;
			Connection tempConn = null;
			tempConn = db.getConn();
			tempConn.setAutoCommit(false);// 需要用到事务，不能让他自动提交，需要手动提交
			tempPs = tempConn.prepareStatement(sql_InsertFinalitem);
			while (rs.next()) {
				if (rs.getInt(4) == 1) { // 如果检索出来matchtype是“预决赛”
					tempPs.setInt(1, rs.getInt(1));// gp2itid
					tempPs.setString(2, rs.getString(2) + rs.getString(3)
							+ "预决赛");// finalitemname
					tempPs.setString(3, "3");// finalitemtype 3代表预决赛
					tempPs.setInt(4, sportsId);// sportsid
					tempPs.addBatch();
				}
				if (rs.getInt(4) == 2) { // 如果检索出来matchtype是“预赛+决赛”,则添加两条记录
					tempPs.setInt(1, rs.getInt(1));// gp2itid
					tempPs.setString(2, rs.getString(2) + rs.getString(3)
							+ "预赛");// finalitemname
					tempPs.setString(3, "1");// finalitemtype 3代表预决赛
					tempPs.setInt(4, sportsId);// sportsid
					tempPs.addBatch();
					//
					tempPs.setInt(1, rs.getInt(1));// gp2itid
					tempPs.setString(2, rs.getString(2) + rs.getString(3)
							+ "决赛");// finalitemname
					tempPs.setString(3, "2");// finalitemtype 3代表预决赛
					tempPs.setInt(4, sportsId);// sportsid
					tempPs.addBatch();
				}
			}
			count = tempPs.executeBatch();
			tempConn.commit();

			for (int i : count) {
				if (i == 0) {
					tempConn.rollback(); // 回滚，非常重要
					log.error("分割生成Finalitem出现异常，回滚=========》");
					// System.out.println("======出现异常，回滚=========");
				}
			}
			tempPs.close();
			tempConn.close();
		} catch (SQLException e) {
			try {
				// 回滚，非常重要
				conn.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
			log.error("获取指定某届运动会的项目信息失败！splitFinalitem()");
			log.error(e.getMessage());
		} finally {
			db.freeConnection(rs, pStatement, conn);
		}

		for (int i = 0; i < count.length; i++) {
			rst += count[i];
		}
		log.debug("批量拆分的Finalitem共有：" + rst);
		return rst;
	}

}