/*
* Copyright(C) 2012, XXXXXXXX.
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
package cn.edu.hbcit.smms.pojo;
/**
 * 赛中管理--检录表字段操作类
 *
 *封装字段
 *
 *详细解释。
 * @author 杨春华
 * @version 1.00  2011/12/07 新規作成<br>
 */

public class GameManageCheckTablePojo {
	
	private String itemType;
	private String groupName;
	private int groupId;
	private String itemName;
	private String playernum;
	private String playername;
	private String playerrunway;
	private String score;
	public String getPlayernum() {
		return playernum;
	}
	public void setPlayernum(String playernum) {
		this.playernum = playernum;
	}
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getPlayerrunway() {
		return playerrunway;
	}
	public void setPlayerrunway(String playerrunway) {
		this.playerrunway = playerrunway;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getGroupName() {
		return groupName; 
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
}
