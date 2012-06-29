package cn.edu.hbcit.smms.services.gameapplyservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gameapplydao.GameApplyDAO;

public class GameApplyService {
	GameApplyDAO gas = new GameApplyDAO();
	
	/**
	 * 获取部门名称
	 * @param username
	 * @return
	 */
	public String getDepartmentName(String username){
		return gas.getDepartmentName(username);
	}
	/**
	 * 获取组别类型
	 * @param groupid
	 * @return
	 */
	public int getGroupType(int groupid){
		return gas.getGroupType(groupid);
	}
	/**
	 * 根据运动会id获取分组
	 * @param sportsid
	 * @return
	 */
	public ArrayList selectAllGroupNameBySportsId(int sportsid){
		return gas.selectAllGroupNameBySportsId(sportsid);
	}
	/**
	 * 根据组别类型确定分组
	 * @param sportsid
	 * @param grouptype
	 * @return
	 */
	public ArrayList selectGroupNameByGroupType(int sportsid , int grouptype){
		return gas.selectGroupNameByGroupType(sportsid, grouptype);
	}
	/**
	 * 分组查询运动员信息
	 * @param groupid
	 * @return
	 */
	public ArrayList selectPlayerByGroupName(int groupid,int departid){
		return gas.selectPlayerByGroupName(groupid,departid);
	}
	/**
	 * 分组查询该届运动员可报项目
	 * @param sportsid
	 * @param grouptype
	 * @return
	 */
	public ArrayList selectAllItemsByGroupType(int sportsid , int grouptype){
		return gas.selectAllItemsByGroupType(sportsid, grouptype);
	}
	/**
	 * 根据号码查询所有运动员信息
	 * @param num
	 * @return
	 */
	public ArrayList selectAllPlayerByNum(String num){
		return gas.selectAllPlayerByNum(num);
	}
	/**
	 * 根据号码判断是否存在该运动员
	 * @param num
	 * @return
	 */
	public boolean selectPlayerByNum(String num){
		return gas.selectPlayerByNum(num);
	}
	/**
	 * 修改
	 * @param num
	 * @param playername
	 * @param playersex
	 * @param registitem
	 * @param groupid
	 * @param sp2dpid
	 * @return
	 */
	public boolean updatePlayerByNum(String num,String playername,int  playersex,String registitem ,int groupid){
		return gas.updatePlayerByNum(num, playername, playersex, registitem, groupid);
	}
}
