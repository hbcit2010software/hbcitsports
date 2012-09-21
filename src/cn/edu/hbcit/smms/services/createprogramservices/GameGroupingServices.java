package cn.edu.hbcit.smms.services.createprogramservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cn.edu.hbcit.smms.dao.createprogramdao.GameGroupingDAO;

/**
 * 径赛分道Services
 *
 *简要说明
 *
 *详细解释。
 * @author 韩鑫鹏
 * @version 1.00  2011/12/07 新規作成<br>
 */
public class GameGroupingServices {
	
	GameGroupingDAO grp = new GameGroupingDAO();
	
	/**
	 * 径赛编排
	 * @param trackItems 所有径赛项目ArrayList  groupid+itemid
	 * @param itemid2name 项目id，name对照HashMap 
	 * @param player2item 数字+运动员id，运动员组别id+所报项目id对照HashMap
	 * @param departments 所有部门id  ArrayList
	 * @param pla2dep 运动员id，部门id对照 HashMap
	 * @param item2finalitem 组别id+项目id，最终项目id对应HashMap
	 * @param pernums 项目各系限报人数
	 * @return ArrayList sql语句集合，第一条是分组情况sql语句，第二条是修改分组数目sql语句
	 */
	public ArrayList trackGrouping(ArrayList trackItems, HashMap itemid2name, HashMap player2item, 
			ArrayList departments, HashMap pla2dep, HashMap item2finalitem,int pernums){
		
		return grp.trackGrouping(trackItems, itemid2name, player2item, 
				departments, pla2dep, item2finalitem, pernums);
	}
	
	/**
	 * 田赛赛事编排
	 * @param filedItems
	 * @param player2item
	 * @param item2finalitem
	 * @return StringBuffer
	 */
	public StringBuffer filedGrouping(ArrayList fieldItems, HashMap player2item, HashMap item2finalitem){
		
		return grp.filedGrouping(fieldItems, player2item, item2finalitem);
	}
	
	/**
	 * 接力赛赛事编排
	 * @param reailyItems
	 * @param groupid2name
	 * @param departments
	 * @param stuDep
	 * @param item2finalitem
	 * @return ArrayList
	 */
	public ArrayList trackReailyGrouping(ArrayList reailyItems, HashMap groupid2name, 
			ArrayList departments, ArrayList stuDep, HashMap item2finalitem){
		
		return grp.trackReailyGrouping(reailyItems, groupid2name, departments, stuDep, item2finalitem);
	}
}
