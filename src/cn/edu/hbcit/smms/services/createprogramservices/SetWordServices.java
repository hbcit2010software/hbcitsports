package cn.edu.hbcit.smms.services.createprogramservices;

import java.util.HashMap;

import cn.edu.hbcit.smms.dao.createprogramdao.SetWordDAO;

/**
 * 
 * @author 韩鑫鹏
 *
 */
public class SetWordServices {

	SetWordDAO swd = new SetWordDAO();
	
	public void AddGroupInfo(String site, HashMap allGirlPlayers, HashMap allBoyPlayers, 
			HashMap players, HashMap department ) {
		swd.AddGroupInfo(site, allGirlPlayers, allBoyPlayers, players, department);
	}
}
