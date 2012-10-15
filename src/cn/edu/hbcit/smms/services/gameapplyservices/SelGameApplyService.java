package cn.edu.hbcit.smms.services.gameapplyservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gameapplydao.SelGameApplyDAO;

public class SelGameApplyService {

	SelGameApplyDAO sgas = new SelGameApplyDAO();
	/**
	 * 查询运动员
	 * @param username
	 * @param sportsId
	 * @param grouptype
	 * @return
	 */
	public ArrayList selectPlayerItemsMessage( String username,int sportsId ,int grouptype ){
		return sgas.selectPlayerItemsMessage(username, sportsId, grouptype);
	}
	/**
	 * 获取系别名称
	 * @param username
	 * @return
	 */
	public String getDepartmentName(String username){
		return sgas.getDepartmentName(username);
	}
	/**
	 * 查询组别类型
	 * @param sportsid
	 * @return
	 */
	public ArrayList selectAllGroupTypes(int sportsid ,String username){
		return sgas.selectAllGroupTypes(sportsid, username);
	}
	/**
	 * 各组可报项目
	 * @param sportsid
	 * @param grouptype
	 * @return
	 */
	public ArrayList selectAllItemsByGroupType(int sportsid , int grouptype){
		return sgas.selectAllItemsByGroupType(sportsid, grouptype);
	}
	/**
	 * 各个组别名称
	 * @param sportsid
	 * @param grouptype
	 * @return
	 */
	public ArrayList selectGroupNameByGroupType(int sportsid , int grouptype){
		return sgas.selectGroupNameByGroupType(sportsid, grouptype);
	}
	/**
	 * 领队查询
	 * @param username
	 * @return
	 */
	public ArrayList selectAllLeaderByUserName(String username){
		return sgas.selectAllLeaderByUserName(username);
	}
	/**
	 * 领队修改
	 * @param username
	 * @param teamleader
	 * @param coach
	 * @param doctor
	 * @return
	 */
	public boolean updateLeaderByUserName(String username,String teamleader,String coach,String doctor){
		return sgas.updateLeaderByUserName(username, teamleader, coach, doctor);
	}
	public int infoDelete(int sp2dpid,String playernum){
		return sgas.infoDelete(sp2dpid, playernum);
	}
}
