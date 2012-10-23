package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.ItemDAO;
import cn.edu.hbcit.smms.dao.gamesetdao.SportsDAO;
import cn.edu.hbcit.smms.util.UtilTools;

public class SportsService {
	SportsDAO sp = new SportsDAO();
	ItemDAO item = new ItemDAO();
	UtilTools ut = new UtilTools();
	
	/**
	 * 获取所有运动会信息
	 * @return ArrayList
	 */
	public ArrayList selectSportsInfo(){
		return sp.selectSportsInfo();
	}
	
	/**
	 * 获取指定ID的运动会信息
	 * @param String sportsId
	 * @return
	 */
	public ArrayList selectSportsInfoById(String sportsId){
		if(ut.isNumeric(sportsId)){
			return sp.selectSportsInfoById(Integer.parseInt(sportsId));
		}else{
			return null;
		}
	}
	/**
	 * 获取指定ID的部门信息
	 * @param int departmentId
	 * @return
	 */
	public ArrayList selectDepartmentInfoById(String departmentId){
		if(ut.isNumeric(departmentId)){
			return sp.selectDepartmentInfoById(Integer.parseInt(departmentId));
		}else{
			return null;
		}
	}
	/**
	 * 获取指定ID的分组信息
	 * @param int groupId
	 * @return
	 */
	public ArrayList selectGroupInfoById(String groupId){
		if(ut.isNumeric(groupId)){
			return sp.selectGroupInfoById(Integer.parseInt(groupId));
		}else{
			return null;
		}
	}
	/**
	 * 设置当前运动会
	 * @param userId
	 * @return boolean
	 */
	public boolean setCurrSports(String spId){
		boolean flag = false;
		if(ut.isNumeric(spId)){
			flag = sp.setCurrSports(Integer.parseInt(spId));
		}
		return flag;
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
		return sp.addSports(sportsName, begin, end, registEnd, address);
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
	public boolean updateSports(String id,String sportsName, String begin, String end, String registEnd, String address){
		if(ut.isNumeric(id)){
			return sp.updateSports(Integer.parseInt(id), sportsName, begin, end, registEnd, address);
		}else{
			return false;
		}
	}
	/**
	 * 修改部门信息By id
	 * @param departId
	 * @param departName
	 * @param departShortName
	 * @param departType
	 * @return
	 */
	public boolean updateDepartment(String departId, String departName, String departShortName, String departType){
		if(ut.isNumeric(departId) && ut.isNumeric(departType)){
			return sp.updateDepartment(Integer.parseInt(departId), departName, departShortName, Integer.parseInt(departType));
		}else{
			return false;
		}
	}
	/**
	 * 修改组别信息By id
	 * @param groupId
	 * @param groupName
	 * @param groupType
	 * @param groupSex
	 * @return
	 */
	public boolean updateGroup(String groupId, String groupName, String groupType, String groupSex){
		if(ut.isNumeric(groupId) && ut.isNumeric(groupType) && ut.isNumeric(groupSex)){
			return sp.updateGroup(Integer.parseInt(groupId), groupName, Integer.parseInt(groupType), Integer.parseInt(groupSex));
		}else{
			return false;
		}
	}
	
	/**
	 * 删除指定id的运动会
	 * @param id
	 * @return
	 */
	public boolean removeSports(String id){
		if(ut.isNumeric(id)){
			return sp.removeSports(Integer.parseInt(id));
		}else{
			return false;
		}
	}
	/**
	 * 删除指定ID部门
	 * @param departmentId
	 * @return
	 */
	public boolean removeDepartment(String departmentId){
		if(ut.isNumeric(departmentId)){
			return sp.removeDepartment(Integer.parseInt(departmentId));
		}else{
			return false;
		}
	}
	/**
	 * 删除指定ID组别
	 * @param groupId
	 * @return
	 */
	public boolean removeGroup(String groupId){
		if(ut.isNumeric(groupId)){
			return sp.removeGroup(Integer.parseInt(groupId));
		}else{
			return false;
		}
	}
	/**
	 * 获取所有部门信息
	 * @return
	 */
	public ArrayList selectDepartmentInfo(){
		return sp.selectDepartmentInfo();
	}
	/**
	 * 获取所有组别信息
	 * @return
	 */
	public ArrayList selectGroupInfo(){
		return sp.selectGroupInfo();
	}
	/**
	 * 获取指定届次的参赛部门ID
	 * @return
	 */
	public ArrayList selectDepartmentInfo(int sportsId){
		return sp.selectDepartmentInfo(sportsId);
	}
	/**
	 * 获取指定届次的参赛组别ID
	 * @return
	 */
	public ArrayList selectGroupInfo(int sportsId){
		return sp.selectGroupInfo(sportsId);
	}
	
	/**
	 * 新增单位/部门
	 * @param departName
	 * @param departShortName
	 * @param departType
	 * @return
	 */
	public boolean addDepartment(String departName, String departShortName, String departType){
		if(ut.isNumeric(departType)){
			return sp.addDepartment(departName, departShortName, Integer.parseInt(departType));
		}else{
			return false;
		}
	}
	/**
	 * 新增组别
	 * @param groupName
	 * @param groupType
	 * @param groupSex
	 * @return
	 */
	public boolean addGroup(String groupName, String groupType, String groupSex){
		if(ut.isNumeric(groupType) && ut.isNumeric(groupSex)){
			return sp.addGroup(groupName, Integer.parseInt(groupType), Integer.parseInt(groupSex));
		}else{
			return false;
		}
	}
	/**
	 * 将指定部门添加到指定运动会
	 * @param sportsId
	 * @param departmentId
	 * @return
	 */
	public boolean addDepartmentToSports(String sportsId, String departmentId){
		if(ut.isNumeric(sportsId) && ut.isNumeric(departmentId)){
			return sp.addDepartmentToSports(Integer.parseInt(sportsId), Integer.parseInt(departmentId));
		}else{
			return false;
		}
	}
	/**
	 * 将指定部门从指定运动会移除
	 * @param sportsId
	 * @param departmentId
	 * @return
	 */
	public boolean removeDepartmentToSports(String sportsId, String departmentId){
		if(ut.isNumeric(sportsId) && ut.isNumeric(departmentId)){
			return sp.removeDepartmentToSports(Integer.parseInt(sportsId), Integer.parseInt(departmentId));
		}else{
			return false;
		}
	}
	/**
	 * 将指定组别添加到指定运动会
	 * @param sportsId
	 * @param groupId
	 * @return
	 */
	public boolean addGroupToSports(int sportsId, String groupId){
		if(ut.isNumeric(groupId)){
			return sp.addGroupToSports(sportsId, Integer.parseInt(groupId));
		}else{
			return false;
		}
	}
	/**
	 * 将指定组别从指定运动会移除
	 * @param sportsId
	 * @param groupId
	 * @return
	 */
	public boolean removeGroupToSports(int sportsId, String groupId){
		if(ut.isNumeric(groupId)){
			return sp.removeGroupToSports(sportsId, Integer.parseInt(groupId));
		}else{
			return false;
		}
	}
	//2012-07-18 完成：运动会管理、参赛部门管理、分组管理 By 李玮
	/**
	 * 获取所有项目信息
	 * @return
	 */
	public ArrayList selectItemInfo(){
		return item.selectItemInfo();
	}
	/**
	 * 获取指定某届运动会的组别信息:
	 * 当届group2sports的id值、组别名称groupname
	 * @return
	 */
	public ArrayList selectGroupOfSports(int sportsId){
		return item.selectGroupOfSports(sportsId);
	}
	/**
	 * 删除指定ID运动会的项目信息
	 * @param sportsId
	 * @return
	 */
	public boolean removeGroupToItem(int sportsId){
		return item.removeGroupToItem(sportsId);
	}
	/**
	 * 为t_group2item表插入数据构建SQL语句
	 * INSERT INTO t_group2item (gp2spid,itemid,matchtype) VALUES (1,1,'3'), (1,2,'3')  插入多行
	 * @param sourceArray 源字符串数组
	 * @return
	 */
	public String getSqlOfInsertT_group2item(String[] sourceArray){
		return item.getSqlOfInsertT_group2item(sourceArray);
	}

	/**
	 * 插入项目与组别的关联信息（t_group2item）
	 * @param sql 已构建好的insert语句
	 * @return
	 */
	public boolean addGroupToItem(String sql){
		return item.addGroupToItem(sql);
	}
	/**
	 * 获取t_group2item中符合本届运动会的数量
	 * @param sportsId
	 * @return
	 */
	public int countGroupToItem(int sportsId){
		return item.countGroupToItem(sportsId);
	}
	/**
	 * 获取指定某届运动会的项目信息:
	 * 当届gp2spid、itemid、matchtype，用,连接
	 * 如："9,1,2"
	 * @return
	 */
	public ArrayList selectItemStringOfSports(int sportsId){
		return item.selectItemStringOfSports(sportsId);
	}
	/**
	 * 更新t_group2item数据
	 * @param itemInfo[i] 格式为：gp2spid,itemid,matchtype 如：4,2,5
	 * @return
	 */
	public boolean updateGroup2Item(String[] itemInfo){
		return item.updateGroup2Item(itemInfo);
	}
	
	/**
	 * 根据Matchtype获取应拆分成的Finalitem数量
	 * Matchtype='1'预决赛计数1；Matchtype='2'预赛+决赛 计数2
	 * @param sportsId
	 * @return
	 */
	public int countMatchtype(int sportsId){
		return item.countMatchtype(sportsId);
	}
	
	/**
	 * 获取此届运动会的FinalItem数量
	 * 本方法可用来验证Finalitem是否已经拆分(判断与countMatchtype()方法返回值是否相等)
	 * @param sportsId
	 * @return
	 */
	public int countFinalItem(int sportsId){
		return item.countFinalItem(sportsId);
	}
	
	/**
	 * 拆分Final Item
	 * @param sportsId
	 * @return
	 */
	public int splitFinalitem(int sportsId){
		return item.splitFinalitem(sportsId);
	}
	/**
	 * 删除指定ID运动会的Final Item信息
	 * @param sportsId
	 * @return
	 */
	public boolean removeFinalItem(int sportsId){
		return item.removeFinalItem(sportsId);
	}
	
	/**
	 * 获取本届运动会的举办日期（每天）
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectSportsDays(int sportsId){
		return item.selectSportsDays(sportsId);
	}
	/**
	 * 为前台页面显示而获取FinalItem信息
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectFinalItem(int sportsId){
		return item.selectFinalItem(sportsId);
	}
	/**
	 * 更新t_finalitem时间、日期、晋级数量
	 * @param finalItem 格式：id,date,time,promotionnum
	 * @return
	 */
	public boolean updateFinalItem(String[] finalItem){
		return item.updateFinalItem(finalItem);
	}
	/**
	 * 查询指定运动会的sp2dpid
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectSp2DpID(int sportsId){
		return item.selectSp2DpID(sportsId);
	}
	/**
	 * 构建插入T_playernum表的SQL语句
	 * @param sourceArray
	 * @return
	 */
	public String getSqlOfInsertT_playernum(ArrayList sourceArray){
		return item.getSqlOfInsertT_playernum(sourceArray);
	}
	/**
	 * 获取此届运动会的sports2department数量
	 * 
	 * @param sportsId
	 * @return
	 */
	public int countSports2Department(int sportsId){
		return item.countSports2Department(sportsId);
	}
	/**
	 * 计算Numtype数量
	 * @param sportsId
	 * @return
	 */
	public int countNumtype(int sportsId){
		return item.countNumtype(sportsId);
	}
	/**
	 * 计算当前运动会的t_playernum总数
	 * @param sportsId
	 * @return
	 */
	public int countT_playernum(int sportsId){
		return item.countT_playernum(sportsId);
	}
	/**
	 * 删除指定ID运动会的运动员编号信息
	 */
	public boolean removePlayernum(int sportsId){
		return item.removePlayernum(sportsId);
	}
	/**
	 * 插入t_playernum
	 * 
	 * @param sql
	 *            已构建好的insert语句
	 * @return
	 */
	public boolean addT_playernum(String sql){
		return item.addT_playernum(sql);
	}
	/**
	 * 为前台页面显示而获取playernum信息
	 * 
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectPlayernum(int sportsId) {
		return item.selectPlayernum(sportsId);
	}
	/**
	 * 更新t_playernum起始号段、结束号段
	 * 
	 * @param finalItem
	 *            格式：id,beginnum,endnum
	 * @return
	 */
	public boolean updatePlayerNum(String[] playerNum){
		return item.updatePlayerNum(playerNum);
	}
	/**
	 * 是否存在当届运动会规则
	 * @param sportsId
	 * @return
	 */
	public boolean isRuleExist(int sportsId){
		return sp.isRuleExist(sportsId);
	}
	/**
	 * 获取指定届次的运动会规则
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectRule(int sportsId){
		return sp.selectRule(sportsId);
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
	public boolean updateRule(String id ,String position, String mark, String perman, String perdepartment, String recordmark_low, String recordmark_high){
		if(ut.isNumeric(id) && ut.isNumeric(position) && ut.isNumeric(perman) && ut.isNumeric(perdepartment) && ut.isNumeric(recordmark_low) && ut.isNumeric(recordmark_high)){
			return sp.updateRule(Integer.parseInt(id), Integer.parseInt(position), mark, Integer.parseInt(perman), Integer.parseInt(perdepartment), Integer.parseInt(recordmark_low), Integer.parseInt(recordmark_high));
		}else{
			return false;
		}
		
	}
	/**
	 * 是否已经开始报名
	 * @param sportsId
	 * @return
	 */
	public boolean isAlreadyRegist(int sportsId){
		return sp.isAlreadyRegist(sportsId);
	}
}
