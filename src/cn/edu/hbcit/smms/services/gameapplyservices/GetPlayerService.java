package cn.edu.hbcit.smms.services.gameapplyservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gameapplydao.GetPlayerDAO;

public class GetPlayerService {
	GetPlayerDAO getPlayerDao = new GetPlayerDAO();
	public ArrayList getPlayerNum(int sp2dpid,int numtype){
		return getPlayerDao.getPlayerNum(sp2dpid,numtype);
	}
	public ArrayList getItemName( int sportsid,int grouptype){
		return getPlayerDao.getItemName(sportsid, grouptype);
	}
	public int getItemNumber( int sportsid,int grouptype ){
		return getPlayerDao.getItemNumber(sportsid,grouptype);
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
	public int selectPlayerNum(int sp2dpid2,boolean numtype){
		return getPlayerDao.selectPlayerNum(sp2dpid2, numtype);
	}
	public int getSumNumId(int sums){
		return getPlayerDao.getSumNumId(sums);	
	}
	public int selNameByNumid(int sums){
		return getPlayerDao.selNameByNumid(sums);
	}
	public int selRule(int sportsid){
		return getPlayerDao.selRule(sportsid);
	}
	public int selRule2(int sportsid){
		return getPlayerDao.selRule2(sportsid);
	}
	/**
	 * 查询所有未分配的运动员号码
	 * @param sp2dpid
	 * @param numtype
	 * @return
	 */
	public ArrayList getPlayernum(int sp2dpid, boolean numtype){
		return getPlayerDao.getPlayernum(sp2dpid, numtype);
	}
	/**
	 * 获取当前运动会截止报名的日期
	 */
	public String getRegistend(){
		return getPlayerDao.getRegistend();
	}
	public ArrayList getGroupItem(int sportsid){
		return getPlayerDao.getGroupItem(sportsid);
	}
}
