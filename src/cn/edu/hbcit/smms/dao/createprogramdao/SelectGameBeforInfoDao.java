/**
 * 
 */
package cn.edu.hbcit.smms.dao.createprogramdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.FildJudgePojo;
import cn.edu.hbcit.smms.pojo.GameDatePlanPojo;
import cn.edu.hbcit.smms.pojo.SportRecordPojo;
import cn.edu.hbcit.smms.pojo.StudentJudgePojo;




public class SelectGameBeforInfoDao {
protected final Logger log = Logger.getLogger(SelectGameBeforInfoDao.class.getName());
	
	private Connection conn;
	private PreparedStatement  ps;
	private ResultSet rs;

	/**
	 * 获取田赛裁判的人员
	 * @return
	 */
	public List getFildJudge( int sportId ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT t_item.id,judge_1,judge_2,judge_3,t_item.itemname" +
					" FROM t_fieldjudge INNER JOIN t_group2item" +
					" ON t_fieldjudge.gp2itid = t_group2item.id INNER JOIN t_item" +
					" ON t_group2item.itemid = t_item.id INNER JOIN" +
					" t_group2sports ON t_group2sports.id=t_group2item.gp2spid" +
					" INNER JOIN t_sports ON t_group2sports.sportsid=t_sports.id" +
					" WHERE t_sports.id=?";
			System.out.println(select);
			ps = conn.prepareStatement(select);
		
			System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				FildJudgePojo fj = new FildJudgePojo();
				fj.setId(rs.getInt(1));
				fj.setFildJudge1(rs.getString(2));
				fj.setFildJudge2(rs.getString(3));
				fj.setFildJudge3(rs.getString(4));
				fj.setItemName(rs.getString(5));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				list.add(fj);
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取工作人员的信息
	 * @return
	 */
	public List getOfficialMember( int sportId ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = " SELECT * FROM t_official " +
					" INNER JOIN t_sports ON t_sports.id=t_official.sportsid " +
					" WHERE t_sports.id=?";
			ps = conn.prepareStatement(select);
			//System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			//int c = rs.getMetaData().getColumnCount();
			if (rs.next()) {
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getString(9));
				list.add(rs.getString(10));
				list.add(rs.getString(11));
				list.add(rs.getString(12));
				list.add(rs.getString(13));
				list.add(rs.getString(14));
				list.add(rs.getString(15));
				list.add(rs.getString(16));
				list.add(rs.getString(17));
				list.add(rs.getString(18));
				list.add(rs.getString(19));
				list.add(rs.getString(20));
				list.add(rs.getString(21));
				list.add(rs.getString(22));
				list.add(rs.getString(23));
				list.add(rs.getString(24));
				list.add(rs.getString(25));
				list.add(rs.getString(26));
				list.add(rs.getString(27));
				list.add(rs.getString(28));
				list.add(rs.getString(29));
				list.add(rs.getString(30));
				list.add(rs.getString(31));
				list.add(rs.getString(32));
				list.add(rs.getString(33));
				list.add(rs.getString(34));
				list.add(rs.getString(35));
				list.add(rs.getString(36));
				list.add(rs.getString(37));
				list.add(rs.getString(38));
				list.add(rs.getString(39));
				list.add(rs.getString(40));
				list.add(rs.getString(41));
				list.add(rs.getString(42));
				list.add(rs.getString(43));
				list.add(rs.getString(44));
				list.add(rs.getString(45));
				
//				for(int i=1;i<=c;i++){
//                	log.debug(rs.getObject(i));
//                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	/**
	 * 获取男子组的项目
	 * @return
	 */
	public List getItemByMale( int sportId ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT t_item.itemname FROM t_item" +
					" INNER JOIN t_group2item ON t_group2item.itemid=t_item.id" +
					" INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid" +
					" INNER JOIN t_group ON t_group2sports.groupid=t_group.id" +
					" INNER JOIN t_sports ON t_sports.id=t_group2sports.sportsid" +
					" WHERE t_group.sex=1 AND t_sports.id=?" +
					" ORDER BY t_item.itemtype";
			ps = conn.prepareStatement(select);
			//System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list.add(rs.getString(1));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取女子组的项目
	 * @return
	 */
	public List getItemByFemale( int sportId ) {
		List list1 = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT t_item.itemname FROM t_item" +
					" INNER JOIN t_group2item ON t_group2item.itemid=t_item.id" +
					" INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid" +
					" INNER JOIN t_group ON t_group2sports.groupid=t_group.id" +
					" INNER JOIN t_sports ON t_sports.id=t_group2sports.sportsid" +
					" WHERE t_group.sex=0 AND t_sports.id=?" +
					" ORDER BY t_item.itemtype";
			ps = conn.prepareStatement(select);
			//System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list1.add(rs.getString(1));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list1;
	}
	/**
	 * 获取竞赛日期和地点
	 * @return
	 */
	public List getGameDate(int sportId) {
		List list2 = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT sportsbegin,sportsend,address FROM t_sports where id=?";
			ps = conn.prepareStatement(select);
			//System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list2;
	}
	/**
	 * 获取学生的号码段
	 * @return
	 */
	public List getPlayerNumber( int sportId ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT	t_playernum.beginnum,t_playernum.endnum," +
					"t_department.departshortname FROM t_playernum" +
					" INNER JOIN t_sports2department ON" +
					" t_sports2department.id=t_playernum.sp2dpid" +
					" INNER JOIN t_department ON t_department.id=t_sports2department.departid" +
					" INNER JOIN t_sports ON t_sports.id=t_sports2department.sportsid" +
					" WHERE t_sports.id=? AND t_department.departtype=1";
			ps = conn.prepareStatement(select);
			//System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list.add(rs.getString(3)+":"+rs.getString(1)+"——"+rs.getString(2));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	/**
	 * 获取教职工的号码段
	 * @return
	 */
	public List getPlayerNumber2( int sportId ) {
		List list1 = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT	t_playernum.beginnum,t_playernum.endnum," +
					"t_department.departshortname FROM t_playernum" +
					" INNER JOIN t_sports2department ON" +
					" t_sports2department.id=t_playernum.sp2dpid" +
					" INNER JOIN t_department ON t_department.id=t_sports2department.departid" +
					" INNER JOIN t_sports ON t_sports.id=t_sports2department.sportsid" +
					" WHERE t_sports.id=? AND t_department.departtype=0";
			ps = conn.prepareStatement(select);
			//System.out.println(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list1.add(rs.getString(3)+":"+rs.getString(1)+"——"+rs.getString(2));
				//System.out.println(rs.getString(1)+":"+rs.getString(2)+"-"+rs.getString(3));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list1;
	}
	/**
	 * 获取竞赛日程的日期
	 * @return
	 */
	
	public List getGameDataTitle( int sportId ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = " SELECT DISTINCT t_finalitem.date FROM t_finalitem " +
					" INNER JOIN t_group2item ON t_group2item.id=t_finalitem.gp2itid " +
					" INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid " +
					" INNER JOIN t_sports ON t_sports.id=t_group2sports.sportsid " +
					" WHERE t_sports.id=? ";
			System.out.println(conn);
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				list.add(rs.getString(1));
				
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	/**
	 * 获取全部竞赛日程信息
	 * @return
	 */
	
	public List getAllGameInfo( int sportId ) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		List gameDataTitle = this.getGameDataTitle( sportId );
		try {
			StringBuffer sql = new StringBuffer();
			
			
			for( int j = 0; j < gameDataTitle.size(); j++){
			conn = db.getConn();
			sql.append(" SELECT t_finalitem.date,t_finalitem.time,t_finalitem.finalitemname,t_finalitem.groupnum,t_item.itemtype ");
			sql.append(" FROM t_finalitem  ");
			sql.append(" INNER JOIN t_group2item ON t_group2item.id=t_finalitem.gp2itid ");
			sql.append(" INNER JOIN t_item ON t_item.id=t_group2item.itemid ");
			sql.append(" INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid ");
			sql.append(" INNER JOIN t_sports ON t_sports.id=t_group2sports.sportsid ");
			sql.append(" WHERE t_sports.id=? AND t_finalitem.date='"+gameDataTitle.get(j)+"'");
			System.out.println(sql);
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			sql.setLength(0);
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				GameDatePlanPojo gd = new GameDatePlanPojo();
				gd.setFinalDate(rs.getString(1));
				gd.setTime(rs.getString(2));
				gd.setFinalItem(rs.getString(3));
				gd.setGroupNum(rs.getString(4));
				gd.setItemType(rs.getString(5));
				
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				list.add(gd);
			}
			db.freeConnection(conn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 田径运动会破记录的记录
	 * @return List
	 */
	
	
	public List getGameRecord() {
		List gameRecord = new ArrayList();
		DBConn db = new DBConn();
		try {
			StringBuffer sql = new StringBuffer();
			
			
			for( int j = 0; j < 2; j++){
			conn = db.getConn();
			sql.append(" SELECT t_record.sex,t_item.itemname,t_record.score,");
			sql.append("t_record.playername,t_record.departname,t_record.sportsname,t_record.recordtime ");
			sql.append(" FROM t_record ");
			sql.append(" INNER JOIN t_item ON t_item.id=t_record.itemid ");
			sql.append(" WHERE sex='"+j+"' ");
			//System.out.println(sql);
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			sql.setLength(0);
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				SportRecordPojo sr = new SportRecordPojo();
				sr.setSex(rs.getString(1));
				sr.setItemName(rs.getString(2));
				sr.setScore(rs.getString(3));
				sr.setSporter(rs.getString(4));
				sr.setDepartName(rs.getString(5));
				sr.setSportName(rs.getString(6));
				sr.setRecordTime(rs.getString(7));
				
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				gameRecord.add(sr);
			}
			db.freeConnection(conn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return gameRecord;
	}
	/**
	 * 获取学生裁判名单
	 * @return List
	 */
	
	public List getStudentJudge( int sportId ) {
		List studentJudge = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = " SELECT t_department.departname,t_stujudge.contact," +
					"t_stujudge.tel,t_stujudge.member " +
					"FROM t_stujudge " +
					"INNER JOIN t_sports2department ON t_sports2department.id = t_stujudge.sp2dpid " +
					"INNER JOIN t_department ON t_department.id = t_sports2department.departid " +
					"INNER JOIN t_sports ON t_sports2department.sportsid = t_sports.id " +
					"WHERE t_sports.id=? ";
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				StudentJudgePojo sj = new StudentJudgePojo();
				sj.setDepartmentName(rs.getString(1));
				sj.setContact(rs.getString(2));
				sj.setTel(rs.getString(3));
				sj.setMember(rs.getString(4));
				
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
				studentJudge.add(sj);
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return studentJudge;
	}
	
	
	
}
