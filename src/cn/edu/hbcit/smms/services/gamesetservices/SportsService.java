package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.SportsDAO;

public class SportsService {
	SportsDAO sp = new SportsDAO();
	
	/**
	 * 获取运动会信息
	 * @return
	 */
	public ArrayList selectSportsInfo(){
		return sp.selectSportsInfo();
	}
}
