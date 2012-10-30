package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.RecordDAO;
import cn.edu.hbcit.smms.util.UtilTools;

public class RecordServices {

	RecordDAO re = new RecordDAO();
	UtilTools ut = new UtilTools();
	/**
	 * 查询所有记录
	 * 
	 * @return air（ArrayList）
	 */
	public ArrayList getQuery() {
		return re.getQuery();
	}

	/**
	 * 查询所有项目名称
	 * 
	 * @return ArrayList
	 */
	public ArrayList getItemRecord() {
		return re.getItemRecord();
	}

	/**
	 * 记录是否存在的查询
	 * 
	 * @param itemId
	 *            项目Id
	 * @param recTime
	 *            创记录时间
	 * @return boolean
	 * @version 2012/6/20 新建
	 */
	public boolean getQuery(int itemId, String recTime) {
		return re.getQuery(itemId, recTime);
	}

	/**
	 * 根据项目名称及破记录时间的查询
	 * 
	 * @param itemId
	 *            项目ID
	 * @return air （ArrayList）
	 */
	public ArrayList seleteByNameTime(int itemId, String recordTime,
			boolean flag) {
		return re.seleteByNameTime(itemId, recordTime, flag);
	}

	/**
	 * 根据项目名称查询
	 * 
	 * @param itemId
	 *            项目ID
	 * @return air （ArrayList)
	 * @version 1.00 2012/6/15 新建
	 */
	public ArrayList seleteByName(int itemId) {
		return re.seleteByName(itemId);
	}

	/**
	 * 获取项目Id
	 * 
	 * @param itemName
	 *            项目名称
	 * @version 1.00 2012/6/15 新建
	 */
	public int getItemId(String itemName) {
		return re.getItemId(itemName);
	}

	/**
	 * 获取项目Id
	 * 
	 * @param itemName
	 *            项目名称
	 * @version 1.00 2012/6/15 新建
	 */
	public ArrayList seleteByRecordId(int recordId) {
		return re.seleteByRecordId(recordId);
	}

	/**
	 * 添加新记录
	 * 
	 * @param plaName
	 *            运动员名
	 * @param sportsName1
	 *            运动会名
	 * @param recTime
	 *            记录时间
	 * @param recLevel
	 *            级别
	 * @param plaSex
	 *            运动员性别
	 * @param sor
	 *            成绩
	 * @param depName
	 *            系别
	 * @param iteId
	 *            项目ID
	 * @return retuValue
	 * @version 1.00 2012/6/15 新建
	 */
//	public boolean addRecord(String plaName, String sportsName1,
//			String recTime, String recLevel, int plaSex, String sor,
//			String depName, int iteId) {
//		return re.addRecord(plaName, sportsName1, recTime, recLevel, plaSex,
//				sor, depName, iteId);
//	}

	/**
	 * 修改记录
	 * 
	 * @param plaName
	 *            运动员名
	 * @param newIteId
	 *            项目ID
	 * @param recordId
	 *            学生记录ID
	 * @param sportsName1
	 *            运动会名
	 * @param newRecTime
	 *            记录时间
	 * @param recLevel
	 *            级别
	 * @param plaSex
	 *            运动员性别
	 * @param sor
	 *            成绩
	 * @param depName
	 *            系部
	 * @return boolean
	 * @version 1.00 2012/6/15 新建
	 */
//	public boolean updateRecord(String plaName, String sportsName1,
//			String newRecTime, String recLevel, int plaSex, String sor,
//			String depName, int recordId) {
//		return re.updateRecord(plaName, sportsName1, newRecTime, recLevel,
//				plaSex, sor, depName, recordId);
//	}

	/**
	 * 删除记录
	 * 
	 * @param recordtime
	 *            记录时间
	 * @param recordId
	 *            学生记录Id
	 * @return boolean
	 * @version 1.00 2012/6/15 新建
	 */
	public boolean deleteRecord(int recordId) {
		return re.deleteRecord(recordId);
	}

	/**
	 * 获取项目名称
	 * 
	 * @param itemId
	 *            项目Id
	 * @version 1.00 2012/6/15 新建
	 */
	public String getItemName(int itemId) {
		return re.getItemName(itemId);
	}

	/******************** 以下为重做的赛会纪录 ***********************/
	/**
	 * 查询最新的运动会记录 李玮 2012-10-15
	 * 
	 * @param itemIdList
	 * @return ArrayList
	 */
	public ArrayList selectLastRecords(ArrayList itemIdList, int sex) {
		return re.selectLastRecords(itemIdList, sex);
	}
	/**
	 * 获取所有项目的ID
	 * @return
	 */
	public ArrayList selectAllItemId(){
		return re.selectAllItemId();
	}
	/**
	 * 查询历届的运动会记录（按性别区分）
	 * 李玮 2012-10-23
	 * @param sex
	 * @return ArrayList
	 */
	public ArrayList selectAllRecords(int sex){
		return re.selectAllRecords(sex);
	}
	/**
	 * 获取所有项目及格式
	 * @return
	 */
	public ArrayList selectItemAndScoreFormat(){
		return re.selectItemAndScoreFormat();
	}
	/**
	 * 手动插入新赛事记录
	 * @param itemid
	 * @param sex
	 * @param score
	 * @param playername
	 * @param departname
	 * @param sportsname
	 * @param recordtime
	 * @param recordlevel
	 * @return
	 * 2012-10-30 by LeeWei
	 */
	public boolean addRecord(String itemid, String sex, String score, String playername, String departname, String sportsname, String recordtime, String recordlevel){
		if(ut.isNumeric(itemid) && ut.isNumeric(sex)){
			return re.addRecord(Integer.parseInt(itemid), Integer.parseInt(sex), score, playername, departname, sportsname, recordtime, recordlevel);
		}else{
			return false;
		}
	}
	/**
	 * 获取指定ID的运动会记录
	 * @param id
	 * @return
	 */
	public ArrayList selectRecordsById(String id){
		if(ut.isNumeric(id)){
			return re.selectRecordsById(Integer.parseInt(id));
		}else{
			return null;
		}
	}
	/**
	 * 更新运动会记录
	 * @param id
	 * @param itemid
	 * @param sex
	 * @param score
	 * @param playername
	 * @param departname
	 * @param sportsname
	 * @param recordtime
	 * @param recordlevel
	 * @return
	 */
	public boolean updateRecord(String id, String itemid, String sex, String score, String playername, String departname, String sportsname, String recordtime, String recordlevel){
		if(ut.isNumeric(itemid) && ut.isNumeric(sex) && ut.isNumeric(id)){
			return re.updateRecord(Integer.parseInt(id), Integer.parseInt(itemid), Integer.parseInt(sex), score, playername, departname, sportsname, recordtime, recordlevel);
		}else{
			return false;
		}
	}
	/**
	 * 删除赛事记录
	 * @param id
	 * @return
	 */
	public boolean removeRecord(String id){
		if(ut.isNumeric(id)){
			return re.removeRecord(Integer.parseInt(id));
		}else{
			return false;
		}
	}
}
