/*
* Copyright(C) 2004, XXXXXXXX.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2004/12/12		0.1		张 三		新建
* 2005/02/05		0.1		李 四		Bug修正
*/

package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;


import cn.edu.hbcit.smms.dao.databasedao.DBConn;
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
		int flag = 0;
		String sql = "SELECT id FROM t_sports ORDER BY id DESC LIMIT 1";  //查询最新的运动会id
		conn = dbc.getConn();	//获得连接
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				flag = rs.getInt(1);
			}
			dbc.freeConnection(conn);	//释放连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("getSportID"+e.getMessage());
		}
		return flag ;
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
			}
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
