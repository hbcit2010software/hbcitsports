package cn.edu.hbcit.smms.pojo;
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
public class QueryPageData {
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXX类
	 *
	 *简要说明
	 *
	 *详细解释。
	 * @author 袁仕杰
	 * @version 1.00  2012/6/10 <br>
	 */
		
	int departid;
	String departname;
	public int getDepartid() {
		return departid;
	}
	public void setDepartid(int departid) {
		this.departid = departid;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
		
	int id;
	String sportsname;
	public int getId() {
	return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSportsname() {
		return sportsname;
	}
	public void setSportsname(String sportsname) {
		this.sportsname = sportsname;
	}
	int itemid;
	String itemname;
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	
	int groupid;
	String groupname;
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
