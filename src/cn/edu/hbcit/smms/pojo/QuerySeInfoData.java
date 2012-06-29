package cn.edu.hbcit.smms.pojo;

import java.util.ArrayList;

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
public class QuerySeInfoData {
	/**
	 * QuerySeInfoData类
	 * 查询字段内容
	 *
	 *简要说明
	 *
	 *详细解释。
	 * @author 袁仕杰
	 * @version 1.00  2011/12/07 新規作成<br>
	 */
	String playername;
	String playernum;
	String groupname;
	String departshortname;
	String registitem;
	String item;
	String score;
	String position;
	String recordlevel;
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getPlayernum() {
		return playernum;
	}
	public void setPlayernum(String playernum) {
		this.playernum = playernum;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getDepartshortname() {
		return departshortname;
	}
	public void setDepartshortname(String departshortname) {
		this.departshortname = departshortname;
	}
	public String getRegistitem() {
		return registitem;
	}
	public void setRegistitem(String registitem) {
		this.registitem = registitem;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRecordlevel() {
		return recordlevel;
	}
	public void setRecordlevel(String recordlevel) {
		this.recordlevel = recordlevel;
	}
	
	ArrayList items = new ArrayList();
	public ArrayList getItems() {
		return items;
	}
	public void setItems(ArrayList items) {
		this.items = items;
	}
}
