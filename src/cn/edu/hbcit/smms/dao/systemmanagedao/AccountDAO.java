/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	系统管理
 * 子模块名称：	帐号管理
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-10		V1.0	李玮			新建
*/
package cn.edu.hbcit.smms.dao.systemmanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Account;
import cn.edu.hbcit.smms.pojo.Department;

/**
 * 帐号处理类
 *
 * 本类的简要描述：
 * 处理帐号信息的CRUD
 *
 * @author 李玮
 * @version 1.00  2012-6-10 新建类
 */

public class AccountDAO {
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(AccountDAO.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 获取帐号信息
	 * @return
	 */
	public ArrayList selectAccountInfo(){
		ArrayList list = new ArrayList();
		String sql = "SELECT t_sysadmin.id,t_sysadmin.username,t_sysadmin.userright,t_sysadmin.realname,t_department.departshortname FROM t_sysadmin INNER JOIN t_department WHERE t_sysadmin.departid=t_department.id";
		conn = db.getConn();
		RightsDAO rd = new RightsDAO();
		String rights = "";
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Account acc = new Account();
				acc.setId(rs.getInt(1));
				acc.setUsername(rs.getString(2));
				//acc.setUserright(rs.getInt(3));
				if( rd.checkPower(rs.getInt(3), 0) == true){
					rights = "checked|";
				}else{
					rights = " |";
				}
				if( rd.checkPower(rs.getInt(3), 1) == true){
					rights = rights + "checked|";
				}else{
					rights = rights + " |";
				}
				if( rd.checkPower(rs.getInt(3), 2) == true){
					rights =  rights + "checked|";
				}else{
					rights =  rights + " |";
				}
				if( rd.checkPower(rs.getInt(3), 3) == true){
					rights =  rights + "checked|";
				}else{
					rights =  rights + " |";
				}
				if( rd.checkPower(rs.getInt(3), 4) == true){
					rights =  rights + "checked|";
				}else{
					rights =  rights + " |";
				}
				log.debug( rights );
				acc.setEveryRight(rights);
				acc.setRealname(rs.getString(4));
				acc.setDepartShortName(rs.getString(5));
				list.add(acc);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取帐号信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 更改用户权限值
	 * @param rightsValue
	 * @param userId
	 * @return
	 */
	public int updateAccountRights(int rightsValue, int userId){
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_sysadmin SET userright=? WHERE id=?";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, rightsValue);
			pStatement.setInt(2, userId);
			rst = pStatement.executeUpdate();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("更新用户权限值失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 初始化用户密码为111111
	 * @param userId
	 * @return
	 */
	public int initializeUserPassword(int userId){
		String md_111111 = "96e79218965eb72c92a549dd5a330112";
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_sysadmin SET password=? WHERE id=?";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, md_111111);
			pStatement.setInt(2, userId);
			rst = pStatement.executeUpdate();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("初始化用户密码失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	/**
	 * 删除帐号
	 * @param userId
	 * @return
	 */
	public int removeAccount(int userId){
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_sysadmin WHERE id=?";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, userId);
			rst = pStatement.executeUpdate();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("初始化用户密码失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 获取部门信息
	 * @return
	 */
	public ArrayList selectDepartment(){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,departshortname FROM t_department";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Department dp = new Department();
				dp.setId(rs.getInt(1));
				dp.setDepartmentShortName(rs.getString(2));
				list.add(dp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取部门信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 添加新帐号
	 * @param uname
	 * @param rightsVal
	 * @param realname
	 * @param departid
	 * @return
	 */
	public int addAccount(String uname, int rightsVal, String realname, int departid){
		int rst = 0;
		String md_111111 = "96e79218965eb72c92a549dd5a330112";
		String sql = "INSERT INTO t_sysadmin (username,password,userright,realname,departid) VALUES (?,?,?,?,?)";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, uname);
			pStatement.setString(2, md_111111);
			pStatement.setInt(3, rightsVal);
			pStatement.setString(4, realname);
			pStatement.setInt(5, departid);
			rst = pStatement.executeUpdate();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("添加新账户失败！");
			log.error(e.getMessage());
			System.out.println(e);
		}
		return rst;
	}
	
	/**
	 * 获取帐号信息BY id
	 * @param uid
	 * @return
	 */
	public ArrayList selectAccountInfo(int uid){
		ArrayList list = new ArrayList();
		String sql = "SELECT t_sysadmin.id,t_sysadmin.username,t_sysadmin.realname,t_sysadmin.departid FROM t_sysadmin WHERE t_sysadmin.id=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, uid);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Account acc = new Account();
				acc.setId(rs.getInt(1));
				acc.setUsername(rs.getString(2));
				acc.setRealname(rs.getString(3));
				acc.setDepartid(rs.getInt(4));
				list.add(acc);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取帐号信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 修改用户信息
	 * @param userId
	 * @param realname
	 * @param departid
	 * @return
	 */
	public int updateAccount(int userId, String realname, int departid){
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_sysadmin SET realname=?,departid=? WHERE id=?";
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, realname);
			pStatement.setInt(2, departid);
			pStatement.setInt(3, userId);
			rst = pStatement.executeUpdate();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("修改用户信息失败！");
			log.error(e.getMessage());
		}
		return rst;
	}

}
