package cn.edu.hbcit.smms.services.createprogramservices;

import java.util.List;
import java.util.Map;

import cn.edu.hbcit.smms.dao.createprogramdao.SelectGameInfoDao;

public class WordDemoService {
	
	SelectGameInfoDao selectInfo = new SelectGameInfoDao();
	public Map getSplitOfficialMember(int sportId) {
		return selectInfo.getSplitOfficialMember(sportId);
	}
	public Map getSplitFildJudge(int sportId){
		return selectInfo.getSplitFildJudge(sportId);
	}
	public Map getGameDate(int sportId) {
		return selectInfo.getGameDate(sportId);
	}
	public Map getItemByMale(int sportId) {
		return selectInfo.getItemByMale(sportId);
	}
	public Map getItemByFemale(int sportId) {
		return selectInfo.getItemByFemale(sportId);
	}
	public List getStudentPlayerNumber(int sportId){
		return selectInfo.getStudentPlayerNumber(sportId);
	}
	public List getTeacherPlayerNumber(int sportId){
		return selectInfo.getTeacherPlayerNumber(sportId);
	}
	public Map getGameDateInfo(int sportId){
		return selectInfo.getGameDateInfo(sportId);
	}
	public List getGameRecord(){
		return selectInfo.getGameRecord();
	}
	public Map SlipStudentJudgeMember(int sportId){
		return selectInfo.SlipStudentJudgeMember(sportId);
	}

	

}
