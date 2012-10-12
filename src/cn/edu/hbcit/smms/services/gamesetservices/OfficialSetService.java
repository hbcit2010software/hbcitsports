package cn.edu.hbcit.smms.services.gamesetservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.gamesetdao.OfficialSetDAO;

public class OfficialSetService {
	
	OfficialSetDAO peoplesetDAO=new OfficialSetDAO();
	/**
	 * 运动会径赛裁判员添加类
	 * @return
	 */
	public boolean addTrackjudge(int sportsid,String chiefjudge_1, String chiefjudge_2,String trackjudge, 
			String trackjudge_rollcall_1,String trackjudge_rollcall_2,String trackjudge_rollcall_3,
			String startingpoint_1,String startingpoint_2,String startingpoint_3,String timejudge_1,
			String timejudge_2,String timejudge_3,String endpoint_1,String endpoint_2,String endpoint_3,
			String endpoint_4,String endpoint_5) {
		return peoplesetDAO.addTrackjudge(sportsid, chiefjudge_1, chiefjudge_2, trackjudge, 
				trackjudge_rollcall_1, trackjudge_rollcall_2, trackjudge_rollcall_3, startingpoint_1,
				startingpoint_2, startingpoint_3, timejudge_1, timejudge_2, timejudge_3, endpoint_1, 
				endpoint_2, endpoint_3, endpoint_4, endpoint_5);
	}
	/**
	 * 运动会田赛裁判员添加类
	 * @return
	 */
	public boolean addFieldjudge(int sportsid,String fieldjudge,String fieldjudge_1,String fieldjudge_2,String fieldjudge_3,
			String fieldjudge_4,String fieldjudge_5,String fieldjudge_6){
		return peoplesetDAO.addFieldjudge(sportsid, fieldjudge, fieldjudge_1, fieldjudge_2, fieldjudge_3, 
				fieldjudge_4, fieldjudge_5, fieldjudge_6);
	}
	
	/**
	 * 根据sportsid查询所有田赛项目
	 * @return
	 */
	public ArrayList selectAllItem(int sportsid) {
		return peoplesetDAO.selectAllItem(sportsid);
	}
	/**
	 * 
	 * 大会主席团添加类
	 * @return
	 */
	public boolean addPresidium(int sportsid,String presidium,String org_committee_1,String org_committee_2,
			String org_committee_3,String secretariat_1,String secretariat_2,String secretariat_3,
			String secretariat_4,String secretariat_5,String secretariat_6,String secretariat_7,
			String arbitration){
		return peoplesetDAO.addPresidium(sportsid, presidium, org_committee_1, org_committee_2, org_committee_3,
				secretariat_1, secretariat_2, secretariat_3, secretariat_4, secretariat_5, secretariat_6, 
				secretariat_7, arbitration);
	}
	
	public ArrayList selectOfficialBySportsid(int sportsid) {
		return peoplesetDAO.selectOfficialBySportsid(sportsid);
	}
	/**
	 * 判断运动会是否存在
	 */
	public boolean spoid(int sportsid) {
		return peoplesetDAO.spoid(sportsid);
	}
	

	/**
	 * 根据sql语句添加数据
	 * @param sql  要执行的sql语句
	 * @return int
	 */
	public int addRecordBySql(String sql){
		return peoplesetDAO.addRecordBySql(sql);
	}
	
	/**
	 * 根据sportsid查询所有田赛裁判员
	 * @param sportsid
	 * @return
	 */
	public ArrayList selectAllFiledJudge(int sportsid) {
		return peoplesetDAO.selectAllFiledJudge(sportsid);
	}
	/**
	 * 根据sportsid查询学生裁判
	 * @param sportsid
	 * @return
	 */
	public ArrayList selectAllStuJudge(int sportsid) {
		return peoplesetDAO.selectAllStuJudge(sportsid);
	}
	/**
	 * 根据itemname查询学生裁判类
	 * @param itemname
	 * @return
	 */
	public ArrayList selectStuJudge(int sp2dpid) {
		return peoplesetDAO.selectStuJudge(sp2dpid);
	}
	/**
	 * 
	 *修改学生裁判类
	 * @return
	 */
	public boolean updateStuJudge(int id, String contact,String tel,String member){
		return peoplesetDAO.updateStuJudge(id,contact, tel, member);
	}
	
	/**
	 * 根据sportsid检查是否添加过学生裁判
	 * @param sportsid
	 * @return
	 */
	public boolean checkStuJudge(int sportsid) {
			return peoplesetDAO.checkStuJudge(sportsid);       
	}
	
	/**
	 * 根据sportsid检查是否添加过田赛裁判
	 * @param sportsid
	 * @return
	 */
	public boolean checkFiledJudge(int sportsid) {
		return peoplesetDAO.checkFiledJudge(sportsid);        
	}
	
	/**
	 * 根据sportsid删除田赛裁判
	 * @param sportsid
	 * @return
	 */
	public void deleteFiledJudge(int sportsid) {
		peoplesetDAO.deleteFiledJudge(sportsid);
	}
	
	/**
	 * 根据sportsid删除学生裁判
	 * @param sportsid
	 * @return
	 */
	public void deleteStuJudge(int sportsid) {
		peoplesetDAO.deleteStuJudge(sportsid);
	}
}
