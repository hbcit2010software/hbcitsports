/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-21			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.services.gameapplyservices;

import cn.edu.hbcit.smms.dao.gameapplydao.UpdatePlayerDAO;

public class PlayerService {
	UpdatePlayerDAO updatePlayerDAO = new UpdatePlayerDAO();

	/**
	 * 根据性别查找组别Id
	 * @param playerSex
	 * @return
	 */
	public int GetGroupIdBySex(boolean playerSex){
		return updatePlayerDAO.GetGroupIdBySex(playerSex);
	}
	/**
	 * 根据运动员号码插入运动员信息
	 * @param PlayerNum
	 * @param PlayerName
	 * @param PlayerSex
	 * @param registItem
	 * @return
	 */
	public int UpdatePlayerByNum(String playerName,boolean playerSex,
			String registItem,int groupId,int sp2dpid,String playerNum){
		return updatePlayerDAO.UpdatePlayerByNum(playerName, playerSex, registItem, groupId, sp2dpid, playerNum);
	}
	/**
	 * 教工信息插入
	 * @param allstr
	 * @return
	 */
	public int UpdatePlayer(String[] allstr,int sp2dpid){
		return updatePlayerDAO.UpdatePlayer(allstr, sp2dpid);
	}
	

}
