
package cn.edu.hbcit.smms.services.loginservices;

import cn.edu.hbcit.smms.dao.logindao.LoginDAO;

public class LoginService {

	LoginDAO login = new LoginDAO();
	
	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean canLogin(String username, String password){
		return login.canLogin(username, password);
	}
	
	/**
	 * 获取用户权限
	 * @param username
	 * @return
	 */
	public int selectUserRights(String username){
		return login.selectUserRights(username);
	}
	
	/**
	 * 获取当前运动会ID
	 * @return
	 */
	public int selectCurrentSportsId(){
		return login.selectCurrentSportsId();
	}
	
	/**
	 * 获取当前运动会名称
	 * @return
	 */
	public String selectCurrentSportsName(){
		return login.selectCurrentSportsName();
	}
}
