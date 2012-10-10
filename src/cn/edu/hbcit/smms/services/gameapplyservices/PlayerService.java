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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.hbcit.smms.dao.gameapplydao.UpdatePlayerDAO;

public class PlayerService {
	UpdatePlayerDAO updatePlayerDAO = new UpdatePlayerDAO();

	/**
	 * 根据性别查找组别Id
	 * @param playerSex
	 * @return
	 */
	public int getGroupIdBySex(boolean playerSex){
		return updatePlayerDAO.getGroupIdBySex(playerSex);
	}
	/**
	 * 根据运动员号码插入运动员信息
	 * @param PlayerNum
	 * @param PlayerName
	 * @param PlayerSex
	 * @param registItem
	 * @return
	 */
	public int updatePlayerByNum(String playerName,boolean playerSex,
			String registItem,int groupId,int sp2dpid,String playerNum){
		return updatePlayerDAO.updatePlayerByNum(playerName, playerSex, registItem, groupId, sp2dpid, playerNum);
	}
	/**
	 * 教工信息插入
	 * @param allstr
	 * @return
	 */
	public int updatePlayer(String[] allstr,int sp2dpid){
		return updatePlayerDAO.updatePlayer(allstr, sp2dpid);
	}
	
	/**
	 * 限制六项
	 * 把学生报名前台传过来的值整理后放进ArrayList中,第一个集合存放要更改数据库的信息，第二个集合存报名出错的运动员的名字
	 * @param pageString 前台隐藏域里面的值
	 * @param sex 学生性别
	 * @param dataInfo 已报项目的运动员的数量Map	
	 * @param perNum 限报人数
	 * @param sp2dpid 组别与运动会id
	 * @return  ArrayList
	 */
	public ArrayList getPageInfo(String[] pageString, HashMap sex, HashMap dataInfo, int perNum,int sp2dpid){
		return updatePlayerDAO.getPageInfo(pageString, sex, dataInfo, perNum, sp2dpid);
	}

	/**
	 * 根据sp2dpid查询已报运动员的信息
	 * @param sp2dpid
	 * @return
	 */
	public HashMap selectPlayerByspSdpid(int sp2dpid){
        return updatePlayerDAO.selectPlayerByspSdpid(sp2dpid);
	}
	
	/**
	 * 根据sportsid查询学生组别信息
	 * @param sportsid
	 * @return
	 */
	public HashMap selectStuGroupByspSdpid(int sportsid){
        return updatePlayerDAO.selectStuGroupByspSdpid(sportsid);
	}
	
	/**
	 * 根据sql语句修改运动员报名信息
	 * @param sql
	 * @return int
	 */
	public int updatePlayerBySql(String sql){
        return updatePlayerDAO.updatePlayerBySql(sql);
	}

	 /**
 	 * 根据运动会id查询每个项目各系的限报人数
 	 * @param 运动会id
 	 * @return int 
 	 */
 	public int selectPerDep(int sportsid){
 		return updatePlayerDAO.selectPerDep(sportsid);
 	}
}
