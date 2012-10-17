package cn.edu.hbcit.smms.services.createprogramservices;

import java.util.List;

import cn.edu.hbcit.smms.dao.createprogramdao.AdjustByHandDao;

public class AdjustByHandServices {
	AdjustByHandDao  adjust = new AdjustByHandDao();
	public List getRunwayInfo(int finalitemid, int teamnum){
		return adjust.getRunwayInfo( finalitemid, teamnum);
	}
	public int  updatePlayerRunway(int finalitemid , int teamnum, String[] playerid, String[] runway ){
		return adjust.updatePlayerRunway(finalitemid, teamnum, playerid, runway);
	}

}
