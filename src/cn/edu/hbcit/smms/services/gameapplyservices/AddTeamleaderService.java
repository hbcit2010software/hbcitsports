/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	领队报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-27			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.services.gameapplyservices;

import cn.edu.hbcit.smms.dao.gameapplydao.AddTeamleaderDAO;

public class AddTeamleaderService {
	AddTeamleaderDAO addTeamleaderDAO = new AddTeamleaderDAO();
	/**
	 * 插入t_ports2Department表信息
	 */
	public int AddSports2Department(int sportsId,int departId,String teamLeader,String coach,String doctor){
		return addTeamleaderDAO.AddSports2Department(sportsId, departId, teamLeader, coach, doctor);
	
	}
	public int updatePlayerNum( int sportsId,int departId){
		return addTeamleaderDAO.updatePlayerNum(sportsId, departId);
	}

}
