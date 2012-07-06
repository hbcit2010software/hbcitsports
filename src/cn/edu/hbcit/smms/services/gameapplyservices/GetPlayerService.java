package cn.edu.hbcit.smms.services.gameapplyservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gameapplydao.GetPlayerDAO;

public class GetPlayerService {
	GetPlayerDAO getPlayerDao = new GetPlayerDAO();
	public ArrayList getPlayerNum(int sp2dpid,int numtype){
		return getPlayerDao.getPlayerNum(sp2dpid,numtype);
	}
	public ArrayList getItemName(String sportsname,int grouptype){
		return getPlayerDao.getItemName(sportsname, grouptype);
	}
	public int getItemNumber( String sportsname,int grouptype ){
		return getPlayerDao.getItemNumber(sportsname,grouptype);
	}
	public String getSportsName(){
		return getPlayerDao.getSportsName();
	}
	public int getDepartid(String username){
		return getPlayerDao.getDepartid(username);
	}
	public int getSp2dpid(int flag){
		return getPlayerDao.getSp2dpid(flag);
	}
		public ArrayList selectAllGroupName(){
			return getPlayerDao.selectAllGroupName();
	}
	public int getSportID(){
		return getPlayerDao.getSportID(); 
	}
	public void addPlayerBySql(String sql){
		getPlayerDao.addPlayerBySql(sql);
	}
}
