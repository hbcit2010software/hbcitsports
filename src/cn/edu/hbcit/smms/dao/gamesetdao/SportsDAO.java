/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛前管理
 * 子模块名称：	运动会管理
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-19		V1.0		李玮 		新建
*/
package cn.edu.hbcit.smms.dao.gamesetdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Sports;

/**
 * 运动会管理类
 *
 * 本类的简要描述：
 * 负责运动会赛前管理
 *
 * @author 李玮
 * @version 1.00  2012-6-19 新建类
 */
public class SportsDAO {
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	protected final Logger log = Logger.getLogger(SportsDAO.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 获取运动会信息
	 * @return
	 */
	public ArrayList selectSportsInfo(){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,sportsname,sportsbegin,sportsend,registend,address,current FROM t_sports ORDER BY sportsbegin DESC";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Sports sp = new Sports();
				sp.setId(rs.getInt(1));
				sp.setSportsname(rs.getString(2));
				sp.setSportsbegin(rs.getString(3));
				sp.setSportsend(rs.getString(4));
				sp.setRegistend(rs.getString(5));
				sp.setAddress(rs.getString(6));
				sp.setCurrent(rs.getInt(7));
				list.add(sp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取运动会信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
}
