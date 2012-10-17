package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.GuiChengDao;

public class SetServices {
	GuiChengDao gg = new GuiChengDao();

	public ArrayList seleteBySportsId( int sportsId) {
		return gg.seleteBySportsId(sportsId);
	}
	public boolean getQuery(int sportsid){
		return gg.getQuery(sportsid);
	}
	public boolean add(int sportsid,String action, String conts, String pionts, String others){	
		return gg.add(sportsid, action, conts, pionts, others);
	}
	public boolean update(int sportsid,String action, String conts, String pionts, String others){
		return gg.update(sportsid, action, conts, pionts, others);
	}
	public void getSportId(String sportsname ){
		 gg.getSportId(sportsname);
	}
}
