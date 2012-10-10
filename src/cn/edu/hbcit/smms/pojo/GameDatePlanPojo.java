package cn.edu.hbcit.smms.pojo;
/**
 * 项目日程类
 * 
 * 简要说明
 * 
 * @author 田小英
 * @version 1.00  2012/06/26 新建
 *
 */
public class GameDatePlanPojo {
	private String finalDate;
	private String finalItem;
	private String groupNum;
	private String time;
	private String itemType;
	public String getFinalItem() {
		return finalItem;
	}
	public void setFinalItem(String finalItem) {
		this.finalItem = finalItem;
	}
	public String getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

}
