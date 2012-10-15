/*
* Copyright(C) 2004, XXXXXXXX.
*
* 模块名称：    运动会管理系统
* 子模块名称：   赛中管理
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2004/12/12		0.1		吴国法		新建
*/

package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;


import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.ManageGroupPJ;


/**
 * 获取本次运动会的查询条件类
 *
 *简要说明
 *
 *详细解释。
 * @author 吴国法
 * @version 1.00  2012/06/11 新建
 */

public class GetCondition {
	protected final Logger log = Logger.getLogger(GetCondition.class.getName());
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConn dbc = new DBConn();
	/**
	 * 
	* 方法说明  	获得本次运动会的id
	* 方法补充说明
	* @param 参数名 参数类型
	* @return 	int
	 */
	public int getSportID(){
		return new LoginDAO().selectCurrentSportsId() ;
	}
	
	/**
	 * 
	* 方法说明	获取组别
	* 方法补充说明
	* @param sportsid int
	* @return	ArrayList
	 */
	public ArrayList getAllGP( int sportsid ){
		ArrayList list = new ArrayList();
		conn = dbc.getConn();
		String sql ="SELECT groupname FROM t_group WHERE id IN " +
			"(SELECT groupid FROM t_group2sports WHERE sportsid = ?)";	//获取组别名称
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				ManageGroupPJ mgpj = new ManageGroupPJ();
				mgpj.setGroupname( rs.getString(1) );
				list.add(mgpj);
				log.debug("getAllGroup"+rs.getString(1));
			}
			rs.close();
			pstmt.close();
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getAllGP"+e.getMessage());
		}
		return list;
	}
	
	
	/**
	 * 获取对应组别的项目
	 * @param groupname
	 * @param sportsid
	 * @return	JSONArray
	 */
	public JSONArray selectItemsByGroup( String groupname ,int sportsid ){
		JSONArray finalJson = new JSONArray();
		conn = dbc.getConn();
		String sql = "SELECT finalitemname FROM t_finalitem WHERE gp2itid IN ("+

						"SELECT id FROM t_group2item WHERE gp2spid IN ("+
	
							"SELECT id FROM t_group2sports WHERE sportsid = ? AND groupid IN ("+
		
								"SELECT id FROM t_group WHERE groupname = ?)))";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			pstmt.setString(2, groupname);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				JSONArray mipj = new JSONArray();
				mipj.add(rs.getString(1));
				finalJson.add(mipj);
				log.debug("selectItemsByGroup"+rs.getString(1));
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("selectItemsByGroup"+e.getMessage());
		}
		return finalJson;
		
	}
	
	
}
