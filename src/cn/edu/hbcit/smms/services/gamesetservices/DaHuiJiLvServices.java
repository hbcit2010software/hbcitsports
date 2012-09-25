package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.DaHuiJiLvDao;

public class DaHuiJiLvServices {
	DaHuiJiLvDao dd = new DaHuiJiLvDao();
	
	public ArrayList seleteBySportsId( int sportsId) {
		return dd.seleteBySportsId(sportsId);
	}
	public boolean getQuery( int sportsid){
		return dd.getQuery(sportsid);
	}
	public boolean add(int sportsid,String open, String close, String rule){
		return dd.add(sportsid, open, close, rule);
	}
	public boolean update( int sportsid,String open, String close, String rule){
		return dd.update(sportsid, open, close, rule);
	}
}
