
/**
 * 
 */
package cn.edu.hbcit.smms.dao.createprogramdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.FildJudgePojo;
import cn.edu.hbcit.smms.pojo.GameDatePlanPojo;
import cn.edu.hbcit.smms.pojo.OfficialPojo;

import cn.edu.hbcit.smms.pojo.SportRecordPojo;
import cn.edu.hbcit.smms.pojo.StudentJudgePojo;




public class SelectGameInfoDao {
	protected final Logger log = Logger.getLogger(SelectGameInfoDao.class
			.getName());

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	
	

	/**
	 * 获取大会主席团及工作人员的信息
	 * 
	 * @return list 所有的工作人员
	 */
	
	 public List getOfficialInfo(int sportId) {
		 log.debug("当前运动会为"+sportId);
		 List list = new ArrayList();
			DBConn db = new DBConn();

			try {
				conn = db.getConn();
				String select = "select * from t_official where sportsid=?";
				//System.out.println(select);
				ps = conn.prepareStatement(select);
				ps.setInt(1, sportId);
				rs = ps.executeQuery();

				if (rs.next()) {
					for(int i = 3; i < 46; i++ ){
						if(rs.getString(i) != null){
							list.add(rs.getString(i));
						}else{
							list.add("null");           //若字段为空，则赋值为空字符串
						}
						
					}
				}else{
					for(int i = 3; i < 46; i++){
						list.add("null");
					}
				}
				
				db.freeConnection(conn);
			} catch (Exception e) {
				e.getStackTrace();
			}
			
			return list;
	 }
	
	 
	public List getOfficialMember(int sportId) {
		List listOfficial = new ArrayList();
		List list = this.getOfficialInfo(sportId);
		

		try {

			
				OfficialPojo official = new OfficialPojo();
				official.setPresidium(list.get(0).toString());
				official.setCommittee1(list.get(1).toString());
				official.setCommittee2(list.get(2).toString());
				official.setCommittee3(list.get(3).toString());
				official.setSecretariat1(list.get(4).toString());
				official.setSecretariat2(list.get(5).toString());
				official.setSecretariat3(list.get(6).toString());
				official.setSecretariat4(list.get(7).toString());
				official.setSecretariat5(list.get(8).toString());
				official.setSecretariat6(list.get(9).toString());
				official.setSecretariat7(list.get(10).toString());
				official.setArbitration(list.get(11).toString());
				official.setChiefjudge1(list.get(12).toString());
				official.setChiefjudge2(list.get(13).toString());
				official.setTrackjudge(list.get(14).toString());
				official.setTrackjudge1(list.get(15).toString());
				official.setTrackjudge2(list.get(16).toString());
				official.setTrackjudge3(list.get(17).toString());
				official.setStartingpoint1(list.get(18).toString());
				official.setStartingpoint2(list.get(19).toString());
				official.setStartingpoint3(list.get(20).toString());
				official.setTimejudge1(list.get(21).toString());
				official.setTimejudge2(list.get(22).toString());
				official.setTimejudge3(list.get(23).toString());
				official.setEndpoint1(list.get(24).toString());
				official.setEndpoint2(list.get(25).toString());
				official.setEndpoint3(list.get(26).toString());
				official.setEndpoint4(list.get(27).toString());
				official.setEndpoint5(list.get(28).toString());
				official.setFieldjudge(list.get(29).toString());
				official.setFieldjudge1(list.get(30).toString());
				official.setFieldjudge2(list.get(31).toString());
				official.setFieldjudge3(list.get(32).toString());
				official.setFieldjudge4(list.get(33).toString());
				official.setFieldjudge5(list.get(34).toString());
				official.setFieldjudge6(list.get(35).toString());
				official.setRemarks1(list.get(36).toString());
				official.setRemarks2(list.get(37).toString());
				official.setRemarks3(list.get(38).toString());
				official.setRemarks4(list.get(39).toString());
				official.setRemarks5(list.get(40).toString());
				official.setOpeningceremony(list.get(41).toString());
				official.setClosingceremony(list.get(42).toString());
				listOfficial.add(official);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listOfficial;
	}
	
/**
 * 将大会工作人员以特殊符号分隔开便于显示
 * @return map 放置了所有的工作人员
 */
	public Map getSplitOfficialMember(int sportId) {
		Map officialMap = new HashMap();
		List list = this.getOfficialMember(sportId);
		for(int i = 0; i < list.size(); i++){
			OfficialPojo official = (OfficialPojo)list.get(i);
			String[] presidium = official.getPresidium().split(",");
			officialMap.put("presidium", presidium);
			String[] committee1 = official.getCommittee1().split(",");
			officialMap.put("committee1", committee1);
			String[] committee2 = official.getCommittee2().split(",");
			officialMap.put("committee2", committee2);
			String[] committee3 = official.getCommittee3().split(",");
			officialMap.put("committee3", committee3);
			String[] secretariat1 = official.getSecretariat1().split(",");
			officialMap.put("secretariat1", secretariat1);
			String[] secretariat2 = official.getSecretariat2().split(",");
			officialMap.put("secretariat2", secretariat2);
			String[] secretariat3 = official.getSecretariat3().split(",");
			officialMap.put("secretariat3", secretariat3);
			String[] secretariat4 = official.getSecretariat4().split(",");
			officialMap.put("secretariat4", secretariat4);
			String[] secretariat5 = official.getSecretariat5().split(",");
			officialMap.put("secretariat5", secretariat5);
			String[] secretariat6 = official.getSecretariat6().split(",");
			officialMap.put("secretariat6", secretariat6);
			String[] secretariat7 = official.getSecretariat7().split(",");
			officialMap.put("secretariat7", secretariat7);
			String[] arbitration = official.getArbitration().split(",");
			officialMap.put("arbitration", arbitration);
			String[] chiefjudge1 = official.getChiefjudge1().split(",");
			officialMap.put("chiefjudge1", chiefjudge1);
			String[] chiefjudge2 = official.getChiefjudge2().split(",");
			officialMap.put("chiefjudge2", chiefjudge2);
			String[] trackjudge = official.getTrackjudge().split(",");
			officialMap.put("trackjudge", trackjudge);
			String[] trackjudge1 = official.getTrackjudge1().split(",");
			officialMap.put("trackjudge1", trackjudge1);
			String[] trackjudge2 = official.getTrackjudge2().split(",");
			officialMap.put("trackjudge2", trackjudge2);
			String[] trackjudge3 = official.getTrackjudge3().split(",");
			officialMap.put("trackjudge3", trackjudge3);
			String[] startingpoint1 = official.getStartingpoint1().split(",");
			officialMap.put("startingpoint1", startingpoint1);
			String[] startingpoint2 = official.getStartingpoint2().split(",");
			officialMap.put("startingpoint2", startingpoint2);
			String[] startingpoint3 = official.getStartingpoint3().split(",");
			officialMap.put("startingpoint3", startingpoint3);
			String[] timejudge1 = official.getTimejudge1().split(",");
			officialMap.put("timejudge1", timejudge1);
			String[] timejudge2 = official.getTimejudge2().split(",");
			officialMap.put("timejudge2", timejudge2);
			String[] timejudge3 = official.getTimejudge3().split(",");
			officialMap.put("timejudge3", timejudge3);
			String[] endpoint1 = official.getEndpoint1().split(",");
			officialMap.put("endpoint1", endpoint1);
			String[] endpoint2 = official.getEndpoint2().split(",");
			officialMap.put("endpoint2", endpoint2);
			String[] endpoint3 = official.getEndpoint3().split(",");
			officialMap.put("endpoint3", endpoint3);
			String[] endpoint4 = official.getEndpoint4().split(",");
			officialMap.put("endpoint4", endpoint4);
			String[] endpoint5 = official.getEndpoint5().split(",");
			officialMap.put("endpoint5", endpoint5);
			String[] fieldjudge = official.getFieldjudge().split(",");
			officialMap.put("fieldjudge", fieldjudge);
			String[] fieldjudge1 = official.getFieldjudge1().split(",");
			officialMap.put("fieldjudge1", fieldjudge1);
			String[] fieldjudge2 = official.getFieldjudge2().split(",");
			officialMap.put("fieldjudge2", fieldjudge2);
			String[] fieldjudge3 = official.getFieldjudge3().split(",");
			officialMap.put("fieldjudge3", fieldjudge3);
			String[] fieldjudge4 = official.getFieldjudge4().split(",");
			officialMap.put("fieldjudge4", fieldjudge4);
			String[] fieldjudge5 = official.getFieldjudge5().split(",");
			officialMap.put("fieldjudge5", fieldjudge5);
			String[] fieldjudge6 = official.getFieldjudge6().split(",");
			officialMap.put("fieldjudge6", fieldjudge6);
			officialMap.put("remarks1", official.getRemarks1() );
			officialMap.put("remarks2", official.getRemarks2());
			officialMap.put("remarks3", official.getRemarks3());
			officialMap.put("remarks4", official.getRemarks4());
			officialMap.put("remarks5", official.getRemarks5());
			officialMap.put("openingceremony", official.getOpeningceremony());
			officialMap.put("closingceremony", official.getClosingceremony());
		}
		
		return officialMap;
	}
	
	/**
	 * 获取田赛裁判员的数据
	 * @param sportId
	 * @return  List
	 */
	public List getFildJudge(int sportId) {
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
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
		
			//System.out.println(select);
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
	 * 将田赛裁判员的数据分隔成独立的数据
	 * @param sportId
	 * @return  Map
	 */
	public Map getSplitFildJudge(int sportId){
		Map officialMap = new HashMap();
		List list = this.getFildJudge(sportId);
		for( int i = 0; i < list.size(); i++ ){
			FildJudgePojo fJudge = ((FildJudgePojo)list.get(i));
			if(fJudge.getFildJudge1() != null){
				String[] judge1 = ((String)fJudge.getFildJudge1()).split(",");
				officialMap.put(i+"1", judge1);
			}
			if(fJudge.getFildJudge2() != null){
				String[] judge2 = ((String)fJudge.getFildJudge2()).split(",");
				officialMap.put(i+"2", judge2);
			}
			if(fJudge.getFildJudge3() != null){
				String[] judge3 = ((String)fJudge.getFildJudge3()).split(",");
				officialMap.put(i+"3", judge3);
			}
			officialMap.put("itemName"+i, fJudge.getItemName());
		}
		
		officialMap.put("length",""+list.size());
		return officialMap;
	}
	
	
	/**
	 * 获取运动会竞赛的日期和地点
	 * @param sportId
	 * @return Map
	 */
	public Map getGameDate(int sportId) {
		Map officialMap = new HashMap();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT sportsbegin,sportsend,address FROM t_sports where id=?";
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			//System.out.println(select);
			rs = ps.executeQuery();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				officialMap.put("sportsbegin", rs.getString(1));
				officialMap.put("sportsend", rs.getString(2));
				officialMap.put("address", rs.getString(3));
				
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return officialMap;
	}
	
	/**
	 * 获取男子组的项目
	 * @param sportId
	 * @return 
	 */
	public Map getItemByMale(int sportId) {
		Map officialMap = new HashMap();
		List maleList = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT DISTINCT (t_item.itemname) FROM t_item" +
					" INNER JOIN t_group2item ON t_group2item.itemid=t_item.id" +
					" INNER JOIN t_finalitem ON t_finalitem.gp2itid=t_group2item.id " +
					"INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid" +
					" INNER JOIN t_group ON t_group.id=t_group2sports.groupid" +
					" WHERE t_group.groupsex=1 AND t_group2sports.sportsid=?";
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			//System.out.println(select);
			rs = ps.executeQuery();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				maleList.add(rs.getString(1));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		officialMap.put("男子组", maleList);
		return officialMap;
	}
	
	/**
	 * 获取女子组的项目
	 * @param sportId
	 * @return
	 */
	public Map getItemByFemale(int sportId) {
		Map officialMap = new HashMap();
		List FemaleList = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT DISTINCT (t_item.itemname) FROM t_item" +
					" INNER JOIN t_group2item ON t_group2item.itemid=t_item.id" +
					" INNER JOIN t_finalitem ON t_finalitem.gp2itid=t_group2item.id" +
					" INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid" +
					" INNER JOIN t_group ON t_group.id=t_group2sports.groupid" +
					" WHERE t_group.groupsex=0 AND t_group2sports.sportsid=?";
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				FemaleList.add(rs.getString(1));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		officialMap.put("女子组", FemaleList);
		return officialMap;
	}
	
	/**
	 * 获取学生运动员的号码段
	 * @param sportId
	 * @return List
	 */
	public List getStudentPlayerNumber(int sportId) {
		List studentList = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT	t_playernum.beginnum,t_playernum.endnum," +
					"t_department.departshortname FROM t_playernum" +
					" INNER JOIN t_sports2department ON" +
					" t_sports2department.id=t_playernum.sp2dpid" +
					" INNER JOIN t_department ON t_department.id=t_sports2department.departid" +
					" INNER JOIN t_sports ON t_sports.id=t_sports2department.sportsid" +
					" WHERE t_sports.id=? AND t_playernum.numtype=1";
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				studentList.add(rs.getString(3)+":"+rs.getString(1)+"——"+rs.getString(2));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return studentList;
	}
	
	/**
	 * 获取教师运动员的号码段
	 * @param sportId
	 * @return List
	 */
	public List getTeacherPlayerNumber(int sportId) {
		List teacherList = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = "SELECT	t_playernum.beginnum,t_playernum.endnum," +
					"t_department.departshortname FROM t_playernum" +
					" INNER JOIN t_sports2department ON" +
					" t_sports2department.id=t_playernum.sp2dpid" +
					" INNER JOIN t_department ON t_department.id=t_sports2department.departid" +
					" INNER JOIN t_sports ON t_sports.id=t_sports2department.sportsid" +
					" WHERE t_sports.id=? AND t_playernum.numtype=0";
			ps = conn.prepareStatement(select);
			ps.setInt(1, sportId);
			rs = ps.executeQuery();
			int c = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				teacherList.add(rs.getString(3)+":"+rs.getString(1)+"——"+rs.getString(2));
				for(int i=1;i<=c;i++){
                	log.debug(rs.getObject(i));
                }
			}
			db.freeConnection(conn);

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return teacherList;
	}
	
	/**
	 * 获取竞赛日程的日期
	 * @return
	 */
	
	public List getGameDataTitle(int sportId) {
		List list = new ArrayList();
		DBConn db = new DBConn();
		try {
			conn = db.getConn();
			String select = " SELECT DISTINCT t_finalitem.date FROM t_finalitem " +
					"INNER JOIN t_group2item ON t_group2item.id=t_finalitem.gp2itid " +
					"INNER JOIN t_group2sports ON t_group2sports.id=t_group2item.gp2spid " +
					"INNER JOIN t_sports ON t_group2sports.sportsid=t_sports.id " +
					"WHERE t_sports.id=? ";
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
	
	public Map getGameDateInfo(int sportId) {
	    Map gameDateMap = new HashMap();
		List list = new ArrayList();
		DBConn db = new DBConn();
		List gameDataTitle = this.getGameDataTitle(sportId);
		gameDateMap.put("dateList", gameDataTitle);
		try {
			StringBuffer sql = new StringBuffer();
			
			
			for( int j = 0; j < gameDataTitle.size(); j++){
			conn = db.getConn();
			sql.append(" SELECT t_finalitem.date,t_finalitem.time,t_finalitem.finalitemname,t_finalitem.groupnum,t_item.itemtype ");
			sql.append(" FROM t_finalitem ");
			sql.append(" INNER JOIN t_group2item ON t_group2item.id=t_finalitem.gp2itid ");
			sql.append(" INNER JOIN t_item ON t_item.id=t_group2item.itemid ");
			sql.append(" WHERE t_finalitem.date='"+gameDataTitle.get(j)+"'");
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			sql.setLength(0);
			int c = rs.getMetaData().getColumnCount();
			if(rs.next()){
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
		}else{
			GameDatePlanPojo gd = new GameDatePlanPojo();
			gd.setFinalDate("null");
			gd.setTime("null");
			gd.setFinalItem("null");
			gd.setGroupNum("null");
			gd.setItemType("null");
			
			
			list.add(gd);
		}
			db.freeConnection(conn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		gameDateMap.put("gameInfoList", list);
		return gameDateMap;
	}
	
	/**
	 * 河北工院田径运动会的记录
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
	
	public List getStudentJudge(int sportId) {
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
				if(rs.getString(1) != null){
					sj.setDepartmentName(rs.getString(1));
				}else{
					sj.setDepartmentName("null");
				}
				if(rs.getString(2) != null){
					sj.setContact(rs.getString(2));
				}else{
					sj.setContact("null");
				}
				if(rs.getString(3) != null){
					sj.setTel(rs.getString(3));
				}else{
					sj.setTel("null");
				}
				if(rs.getString(4) != null){
					sj.setMember(rs.getString(4));
				}else{
					sj.setMember("null");
				}
				
				
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
	
	/**
	 * 分隔学生裁判成员
	 * @return
	 */
	public Map SlipStudentJudgeMember(int sportId){
		Map studentJudgeMember = new HashMap();
		List list = this.getStudentJudge(sportId);
		for( int i = 0; i < list.size(); i++ ){
			StudentJudgePojo sJudge = ((StudentJudgePojo)list.get(i));
			if(sJudge.getMember() != null){
				String[] judgei = ((String)sJudge.getMember()).split(",");
				studentJudgeMember.put(i+"1", judgei);
			}
			
			studentJudgeMember.put("department"+i, sJudge.getDepartmentName());
			studentJudgeMember.put("contact"+i, sJudge.getContact());
			studentJudgeMember.put("tel"+i, sJudge.getTel());
			
		}
		studentJudgeMember.put("count", ""+list.size());
		return studentJudgeMember;
	}

}
