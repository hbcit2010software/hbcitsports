package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.SportsDAO;
import cn.edu.hbcit.smms.util.UtilTools;

public class SportsService {
	SportsDAO sp = new SportsDAO();
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
}
