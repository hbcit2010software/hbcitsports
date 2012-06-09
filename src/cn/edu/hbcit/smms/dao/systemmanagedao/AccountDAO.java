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
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Account acc = new Account();
				acc.setId(rs.getInt(1));
				acc.setUsername(rs.getString(2));
				acc.setUserright(rs.getInt(3));
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

}
