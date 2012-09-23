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
 * 2012-6-19	V1.0		李玮 		新建
 * 2012-7-15	V1.1		李玮			添加功能
*/
package cn.edu.hbcit.smms.dao.gamesetdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Department;
import cn.edu.hbcit.smms.pojo.Group;
import cn.edu.hbcit.smms.pojo.Group2Sports;
import cn.edu.hbcit.smms.pojo.Rule;
import cn.edu.hbcit.smms.pojo.Sports;
import cn.edu.hbcit.smms.pojo.Sports2department;

/**
 * 运动会管理类
 *
 * 本类的简要描述：
 * 负责运动会赛前管理——运动会管理、参赛部门管理、分组管理
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
	 * 获取所有运动会信息
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
	/**
	 * 获取指定ID的运动会信息
	 * @param int sportsId
	 * @return
	 */
	public ArrayList selectSportsInfoById(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,sportsname,sportsbegin,sportsend,registend,address,current FROM t_sports WHERE id=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
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
			log.error("获取运动会信息By ID失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 获取指定ID的部门信息
	 * @param int departmentId
	 * @return
	 */
	public ArrayList selectDepartmentInfoById(int departmentId){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,departname,departshortname,departtype FROM t_department WHERE id=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, departmentId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Department dp = new Department();
				dp.setId(rs.getInt(1));
				dp.setDepartmentName(rs.getString(2));
				dp.setDepartmentShortName(rs.getString(3));
				dp.setDepartmentType(rs.getInt(4));
				list.add(dp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取部门信息By ID失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 获取指定ID的分组信息
	 * @param int groupId
	 * @return
	 */
	public ArrayList selectGroupInfoById(int groupId){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,groupname,grouptype,groupsex FROM t_group WHERE id=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, groupId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Group gp = new Group();
				gp.setId(rs.getInt(1));
				gp.setGroupname(rs.getString(2));
				gp.setGrouptype(rs.getInt(3));
				gp.setGroupsex(rs.getInt(4));
				list.add(gp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取组别信息By ID失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 设置当前运动会
	 * @param userId
	 * @return
	 */
	public boolean setCurrSports(int spId){
		boolean rst = false;
		int flag_sql_1 = 0;
		int flag_sql_2 = 0;
		conn = db.getConn();
		String sql_1 = "UPDATE t_sports SET current=0 WHERE current=1"; //将运动会为“当前”的全部设为“非当前”
		String sql_2 = "UPDATE t_sports SET current=1 WHERE id=?";	//将指定ID的运动会设为“当前”
		try{
			//将所有current为1的重置为0
			pStatement = conn.prepareStatement(sql_1);
			flag_sql_1 = pStatement.executeUpdate();
			pStatement.close();
			//将指定ID的记录设为1
			pStatement = conn.prepareStatement(sql_2);
			pStatement.setInt(1, spId);
			flag_sql_2 = pStatement.executeUpdate();
			//
			log.debug("flag_sql_1:"+flag_sql_1+"\t flag_sql_2:"+flag_sql_2);
			if( flag_sql_1>0 && flag_sql_2>0){
				rst = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("设置当前运动会失败！");
			log.error(e.getMessage());
		}
		return rst;
	}
	
	/**
	 * 新增运动会
	 * @param sportsName
	 * @param begin
	 * @param end
	 * @param registEnd
	 * @param address
	 * @return
	 */
	public boolean addSports(String sportsName, String begin, String end, String registEnd, String address){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "INSERT INTO t_sports (sportsname,sportsbegin,sportsend,registend,address,current) VALUES(?,?,?,?,?,?)"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, sportsName);
			pStatement.setString(2, begin);
			pStatement.setString(3, end);
			pStatement.setString(4, registEnd);
			pStatement.setString(5, address);
			pStatement.setInt(6, 0);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("添加运动会失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 新增单位/部门
	 * @param departName
	 * @param departShortName
	 * @param departType
	 * @return
	 */
	public boolean addDepartment(String departName, String departShortName, int departType){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "INSERT INTO t_department (departname,departshortname,departtype) VALUES(?,?,?)"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, departName);
			pStatement.setString(2, departShortName);
			pStatement.setInt(3, departType);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("添加新单位部门失败！");
			log.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 新增组别
	 * @param groupName
	 * @param groupType
	 * @param groupSex
	 * @return
	 */
	public boolean addGroup(String groupName, int groupType, int groupSex){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "INSERT INTO t_group (groupname,grouptype,groupsex) VALUES(?,?,?)"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, groupName);
			pStatement.setInt(2, groupType);
			pStatement.setInt(3, groupSex);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("添加新组别失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 修改运动会By id
	 * @param id
	 * @param sportsName
	 * @param begin
	 * @param end
	 * @param registEnd
	 * @param address
	 * @return
	 */
	public boolean updateSports(int id,String sportsName, String begin, String end, String registEnd, String address){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_sports SET sportsname=?,sportsbegin=?,sportsend=?,registend=?,address=? WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, sportsName);
			pStatement.setString(2, begin);
			pStatement.setString(3, end);
			pStatement.setString(4, registEnd);
			pStatement.setString(5, address);
			pStatement.setInt(6, id);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("添加运动会失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 修改部门信息By id
	 * @param departId
	 * @param departName
	 * @param departShortName
	 * @param departType
	 * @return
	 */
	public boolean updateDepartment(int departId, String departName, String departShortName, int departType){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_department SET departname=?,departshortname=?,departtype=? WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, departName);
			pStatement.setString(2, departShortName);
			pStatement.setInt(3, departType);
			pStatement.setInt(4, departId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("修改部门信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 修改组别信息By id
	 * @param groupId
	 * @param groupName
	 * @param groupType
	 * @param groupSex
	 * @return
	 */
	public boolean updateGroup(int groupId, String groupName, int groupType, int groupSex){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_group SET groupname=?,grouptype=?,groupsex=? WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, groupName);
			pStatement.setInt(2, groupType);
			pStatement.setInt(3, groupSex);
			pStatement.setInt(4, groupId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("修改分组信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 修改赛事规则
	 * @param id
	 * @param position
	 * @param mark
	 * @param perman
	 * @param perdepartment
	 * @param recordmark_low
	 * @param recordmark_high
	 * @return
	 */
	public boolean updateRule(int id ,int position, String mark, int perman, int perdepartment, int recordmark_low, int recordmark_high){

		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "UPDATE t_rule SET position=?,mark=?,recordmark_low=?,recordmark_high=?,perman=?,perdepartment=? WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, position);
			pStatement.setString(2, mark);
			pStatement.setInt(3, recordmark_low);
			pStatement.setInt(4, recordmark_high);
			pStatement.setInt(5, perman);
			pStatement.setInt(6, perdepartment);
			pStatement.setInt(7, id);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			db.freeConnection(pStatement,conn);
		}catch(Exception e){
			log.error("修改赛事规则rule失败！");
			log.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 删除指定ID运动会
	 * @param id
	 * @return
	 */
	public boolean removeSports(int id){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_sports WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("删除运动会失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 删除指定ID部门
	 * @param departmentId
	 * @return
	 */
	public boolean removeDepartment(int departmentId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_department WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, departmentId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("删除单位部门失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 删除指定ID组别
	 * @param groupId
	 * @return
	 */
	public boolean removeGroup(int groupId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_group WHERE id=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, groupId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("删除组别信息失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 获取所有部门信息
	 * @return
	 */
	public ArrayList selectDepartmentInfo(){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,departname,departshortname,departtype FROM t_department";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Department dp = new Department();
				dp.setId(rs.getInt(1));
				dp.setDepartmentName(rs.getString(2));
				dp.setDepartmentShortName(rs.getString(3));
				dp.setDepartmentType(rs.getInt(4));
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
	 * 获取所有组别信息
	 * @return
	 */
	public ArrayList selectGroupInfo(){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,groupname,grouptype,groupsex FROM t_group ORDER BY groupname ASC";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Group gp = new Group();
				gp.setId(rs.getInt(1));
				gp.setGroupname(rs.getString(2));
				gp.setGrouptype(rs.getInt(3));
				gp.setGroupsex(rs.getInt(4));
				list.add(gp);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取组别信息失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 获取指定届次的参赛部门ID
	 * @return
	 */
	public ArrayList selectDepartmentInfo(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,departid FROM t_sports2department WHERE sportsid=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Sports2department s2d = new Sports2department();
				s2d.setId(rs.getInt(1));
				s2d.setDepartid(rs.getInt(2));
				list.add(s2d);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取指定届次的参赛部门ID失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 获取指定届次的参赛组别ID
	 * @return
	 */
	public ArrayList selectGroupInfo(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,groupid FROM t_group2sports WHERE sportsid=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Group2Sports g2s = new Group2Sports();
				g2s.setId(rs.getInt(1));
				g2s.setGroupid(rs.getInt(2));
				list.add(g2s);
			}
			rs.close();
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("获取指定届次的参赛组别ID失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 获取指定届次的运动会规则
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectRule(int sportsId){
		ArrayList list = new ArrayList();
		String sql = "SELECT id,position,mark,recordmark_low,recordmark_high,perman,perdepartment FROM t_rule WHERE sportsid=?";
		conn = db.getConn();
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				Rule rule = new Rule();
				rule.setId(rs.getInt(1));
				rule.setPosition(rs.getInt(2));
				rule.setMark(rs.getString(3));
				rule.setRecordmark_low(rs.getInt(4));
				rule.setRecordmark_high(rs.getInt(5));
				rule.setPerman(rs.getInt(6));
				rule.setPerdepartment(rs.getInt(7));
				list.add(rule);
			}
			db.freeConnection(rs,pStatement,conn);
		}catch(Exception e){
			log.error("获取指定届次的运动会规则失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	/**
	 * 将指定部门添加到指定运动会
	 * @param sportsId
	 * @param departmentId
	 * @return
	 */
	public boolean addDepartmentToSports(int sportsId, int departmentId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "INSERT INTO t_sports2department (sportsid,departid) VALUES(?,?)"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, departmentId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("将指定部门添加到指定运动会失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 将指定组别添加到指定运动会
	 * @param sportsId
	 * @param groupId
	 * @return
	 */
	public boolean addGroupToSports(int sportsId, int groupId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "INSERT INTO t_group2sports (sportsid,groupid) VALUES(?,?)"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, groupId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("将指定组别添加到指定运动会失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 将指定部门从指定运动会移除
	 * @param sportsId
	 * @param departmentId
	 * @return
	 */
	public boolean removeDepartmentToSports(int sportsId, int departmentId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_sports2department WHERE sportsid=? AND departid=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, departmentId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("将指定部门从指定运动会移除失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 将指定组别从指定运动会移除
	 * @param sportsId
	 * @param groupId
	 * @return
	 */
	public boolean removeGroupToSports(int sportsId, int groupId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "DELETE FROM t_group2sports WHERE sportsid=? AND groupid=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			pStatement.setInt(2, groupId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			pStatement.close();
			db.freeConnection(conn);
		}catch(Exception e){
			log.error("将指定组别从指定运动会移除失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * 是否存在当届运动会规则
	 * @param sportsId
	 * @return
	 */
	public boolean isRuleExist(int sportsId){
		boolean flag = false;
		int rst = 0;
		conn = db.getConn();
		String sql = "SELECT COUNT(*) FROM t_rule WHERE sportsid=?"; 
		try{
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, sportsId);
			rst = pStatement.executeUpdate();
			//
			if( rst>0 ){
				flag = true;
			}
			db.freeConnection(pStatement,conn);
		}catch(Exception e){
			log.error("获取是否存在当届运动会规则失败！");
			log.error(e.getMessage());
		}
		return flag;
	}
}
