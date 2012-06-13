/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	系统管理
 * 子模块名称：	系统管理
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-12		V1.0	李玮		新建
*/
package cn.edu.hbcit.smms.services.systemmanageservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.systemmanagedao.AccountDAO;
import cn.edu.hbcit.smms.dao.systemmanagedao.RightsDAO;
import cn.edu.hbcit.smms.util.UtilTools;

/**
 * 账户管理类
 *
 * 本类的简要描述：
 *
 * @author Administrator
 * @version 1.00  2012-6-12 新建类
 */

public class AccountService {

	AccountDAO ad = new AccountDAO();
	RightsDAO rd = new RightsDAO();
	UtilTools ut = new UtilTools();
	
	public boolean checkPower(int userPurview, int optPurview){
		return rd.checkPower(userPurview, optPurview);
	}
	public int getPower(String[] purviewStr ){
		return rd.getPower(purviewStr);
	}
	public ArrayList selectAccountInfo(){
		return ad.selectAccountInfo();
	}
	public int updateAccountRights(int rightsValue, int userId){
		return ad.updateAccountRights(rightsValue, userId);
	}
	
	public int initializeUserPassword(int userId){
		return ad.initializeUserPassword(userId);
	}
	
	public int removeAccount(int userId){
		return ad.removeAccount(userId);
	}
	
	public ArrayList selectDepartment(){
		return ad.selectDepartment();
	}
	
	/**
	 * 添加新帐号
	 * @param uname
	 * @param rightsVal
	 * @param realname
	 * @param departid
	 * @return
	 */
	public int addAccount(String uname, String rightsVal, String realname, String departid){
		int rst = 0;
		if(ut.isNumeric(rightsVal) && ut.isNumeric(departid)){
			rst = ad.addAccount(uname, Integer.parseInt(rightsVal), realname, Integer.parseInt(departid));
		}
		return rst;
	}
}
