package cn.edu.hbcit.smms.services.gamemanageservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamemanagedao.MarkManagerBean;
/**
 * 积分管理
 * @author 霍立芳
 *
 */
public class MarkManangerBeanServices {
	/**
	 * 获取部门名称
	 * @return
	 */
	public ArrayList getDepart() {
		return new MarkManagerBean().getDepart();
	}
	
	/**
	 * 获取项目名称
	 * @return
	 */
	public ArrayList getItem() {
		return new MarkManagerBean().getItem();
	}
	/**
	 * 获取按部门和组别查询得到的总积分
	 * @param departId
	 * @param groupId
	 * @return
	 */
	public ArrayList getQuery(int departId,int groupId ) {
		return new MarkManagerBean().getQuery(departId, groupId);
	}
	/**
	 * 获取按项目和组别查询得到的总积分 
	 * @param itemId
	 * @param roleId
	 * @return
	 */
	public ArrayList getItemQuery(int itemId,int roleId ) {	
		return new MarkManagerBean().getItemQuery(itemId, roleId);
	}
}
