package cn.edu.hbcit.smms.services.gamesetservices;

import cn.edu.hbcit.smms.dao.gamesetdao.UpdateOfficialSetDAO;

public class UpdateOfficialSetService {
	UpdateOfficialSetDAO updateofficialsetDAO=new UpdateOfficialSetDAO();
	/**
	 * 更改大会主席团人员
	 * @return
	 */
	public boolean updatePresidiumBySportsid(int sportsid,String presidium,String org_committee_1,String org_committee_2,
			String org_committee_3,String secretariat_1,String secretariat_2,String secretariat_3,
			String secretariat_4,String secretariat_5,String secretariat_6,String secretariat_7,
			String arbitration){
		return updateofficialsetDAO.updatePresidiumBySportsid(sportsid, presidium, org_committee_1, org_committee_2, org_committee_3, 
				secretariat_1, secretariat_2, secretariat_3, secretariat_4, secretariat_5, secretariat_6, 
				secretariat_7, arbitration);
	}
	/**
	 * 
	 *更改径赛裁判员类
	 * @return
	 */
	public boolean updateTrackjudgeBySportsid(int sportsid,String chiefjudge_1, String chiefjudge_2,String trackjudge, 
			String trackjudge_rollcall_1,String trackjudge_rollcall_2,String trackjudge_rollcall_3,
			String startingpoint_1,String startingpoint_2,String startingpoint_3,String timejudge_1,
			String timejudge_2,String timejudge_3,String endpoint_1,String endpoint_2,String endpoint_3,
			String endpoint_4,String endpoint_5) {
		return updateofficialsetDAO.updateTrackjudgeBySportsid(sportsid, chiefjudge_1, chiefjudge_2, trackjudge, trackjudge_rollcall_1,
				trackjudge_rollcall_2, trackjudge_rollcall_3, startingpoint_1, startingpoint_2, startingpoint_3, timejudge_1, 
				timejudge_2, timejudge_3, endpoint_1, endpoint_2, endpoint_3, endpoint_4, endpoint_5);
	}
	/**
	 * 更改田赛裁判员类
	 * @return
	 */
	public boolean updateFieldjudgeBySportsid(int sportsid,String fieldjudge,String fieldjudge_1,String fieldjudge_2,String fieldjudge_3,
			String fieldjudge_4,String fieldjudge_5,String fieldjudge_6){
		return updateofficialsetDAO.updateFieldjudgeBySportsid(sportsid, fieldjudge, fieldjudge_1, fieldjudge_2, fieldjudge_3, fieldjudge_4,
				fieldjudge_5, fieldjudge_6);
	}
}
