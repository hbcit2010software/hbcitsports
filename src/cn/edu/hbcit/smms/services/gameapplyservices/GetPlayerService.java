package cn.edu.hbcit.smms.services.gameapplyservices;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	/**
	 * 查询当届运动会的所有参赛组别
	 * @param sportsid
	 * @return
	 */
	public ArrayList getGroup(int sportsid){
		return getPlayerDao.getGroup(sportsid);
	}
	/**
	 * 根据运动会id获取学生组所能报名的项目
	 * @param sportsid
	 * @return
	 */
	public ArrayList getGroupItemStu(int sportsid){
		return getPlayerDao.getGroupItemStu(sportsid);
	}
	/**
	 * 查询当届运动会的学生参赛组别
	 * @param sportsid
	 * @return
	 */
	public ArrayList getGroupStu(int sportsid){
		return getPlayerDao.getGroupStu(sportsid);
	}
	/**
	 * 根据运动会id获取教工组所能报名的项目
	 * @param sportsid
	 * @return
	 */
		
	public ArrayList getGroupItemTea(int sportsid){
		return getPlayerDao.getGroupItemTea(sportsid);
	}
	/**
	 * 查询当届运动会的教工参赛组别
	 * @param sportsid
	 * @return
	 */
	public ArrayList getGroupTea(int sportsid){
		return getPlayerDao.getGroupTea(sportsid);
	}
	/**
	 * 根据sp2dpid查询学生已报运动员信息
	 * @param sp2dpid
	 * @param group2item
	 * @return
	 */
	public int[][] selectPlayerByspSdpid(int sp2dpid, int[][] group2item){
        return getPlayerDao.selectPlayerByspSdpid(sp2dpid, group2item);
	}
	
	/**
	 * 根据sportsid查询学生组性别+项目id
	 * @param sportsid
	 * @return
	 */
	public int[][] selectItemByspSdpid(int sportsid){
        return getPlayerDao.selectItemByspSdpid(sportsid);
	}
	/**
	 * 根据sp2dpid查询教工已报运动员信息
	 * @param sp2dpid
	 * @param group2item
	 * @return
	 */
	public int[][] selectTPlayerByspSdpid(int sp2dpid, int[][] group2item){
        return getPlayerDao.selectTPlayerByspSdpid(sp2dpid, group2item);
	}
	
	/**
	 * 根据sportsid查询教工组别+项目id
	 * @param sportsid
	 * @return
	 */
	public int[][] selectTItemByspSdpid(int sportsid){
        return getPlayerDao.selectTItemByspSdpid(sportsid);
	}
	
	/**
	 * 根据sp2dpid查询学生已报运动员信息
	 * @param sp2dpid
	 * @param group2item
	 * @return
	 */
	public int[][] selectSPlayerByspSdpid(int sp2dpid, int[][] group2item){
        return getPlayerDao.selectSPlayerByspSdpid(sp2dpid, group2item);
	}
	
	/**
	 * 根据sportsid查询学生组别+项目id
	 * @param sportsid
	 * @return
	 */
	public int[][] selectSItemByspSdpid(int sportsid){
        return getPlayerDao.selectSItemByspSdpid(sportsid);
	}
}
