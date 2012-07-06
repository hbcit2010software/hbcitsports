package cn.edu.hbcit.smms.services.createprogramservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.smms.dao.createprogramdao.SelectGameBeforInfoDao;
import cn.edu.hbcit.smms.dao.createprogramdao.SelectPlayerDAO;
import cn.edu.hbcit.smms.dao.createprogramdao.SlipDataDao;

public class WordDemoService {
	
	SlipDataDao sd = new SlipDataDao();
	SelectGameBeforInfoDao gd = new SelectGameBeforInfoDao();
	SelectPlayerDAO sp = new SelectPlayerDAO();
	public Map SlipFildCharactor( int sportId ){
		return sd.SlipFildCharactor( sportId );
	}
	public Map SlipCharactor( int sportId){
		return sd.SlipCharactor( sportId);
	}
	public Map getGameInfo( int sportId ){
		return sd.getGameInfo( sportId );
	}
	public Map SlipStudentJudgeMember( int sportId ){
		return sd.SlipStudentJudgeMember( sportId );
	}
	
	public List getFildJudge( int sportId ) {
		return gd.getFildJudge( sportId );
	}
	public List getOfficialMember(int sportId){
		return gd.getOfficialMember( sportId);
	}
	public List getItemByMale( int sportId ){
		return gd.getItemByMale( sportId);
	}
	public List getItemByFemale(int sportId){
		return gd.getItemByFemale(sportId);
	}
	public List getGameDate(int sportId){
		return gd.getGameDate( sportId);
	}
	public List getPlayerNumber(int sportId){
		return gd.getPlayerNumber( sportId);
	}
	public List getPlayerNumber2(int sportId){
		return gd.getPlayerNumber2( sportId);
	}
	public List getGameDataTitle(int sportId){
		return gd.getGameDataTitle(sportId);
	}
	public List getAllGameInfo(int sportId) {
		return gd.getAllGameInfo( sportId);
	}
	public List getGameRecord(){
		return gd.getGameRecord();
	}
	public List getStudentJudge(int sportId){
		return gd.getStudentJudge( sportId);
	}
	

}
