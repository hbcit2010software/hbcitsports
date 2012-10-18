/** Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：    赛中管理
* 子模块名称：   
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
*/

package cn.edu.hbcit.smms.pojo;

/**
 * 赛中管理--------->成绩管理--------->PoJo类
 * 
 * 
 *简要说明
 * @author 刘然
 * 
 * @version 1.00  2012/06/13 新規作成<br>
 */
public class GameManagePoJo {
	
	private int itemid;           //项目id
	private String itemname;      //项目名称
	private String matchtype;     //赛次类型
	private int groupid;          //组别id
	private String groupname;     //组别名称
	private int gp2spid;          //届次与组别对应关系id
	
	private int playerid;         //运动员id
	public int getPlayerid() {
		return playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	private String playernum;     //运动员编号
	private String playername;    //运动员姓名
	private String playersex;     //运动员性别
	private String score;         //分数
	private int matchid;          //竞赛表id
	private String departname;    //部门名称
	private String recordlevel;   //破纪录级别
	private String foul;          //是否违纪
	private int foul2;
	private int recordlevel2;
	private String finalitemname; //拆分后的项目名称
	
	
	public String getFinalitemname() {
		return finalitemname;
	}
	public void setFinalitemname(String finalitemname) {
		this.finalitemname = finalitemname;
	}
	public int getFoul2() {
		return foul2;
	}
	public void setFoul2(int foul2) {
		this.foul2 = foul2;
	}
	public int getRecordlevel2() {
		return recordlevel2;
	}
	public void setRecordlevel2(int recordlevel2) {
		this.recordlevel2 = recordlevel2;
	}		
	public String getFoul() {
		return foul;
	}
	public void setFoul(String foul) {
		this.foul = foul;
	}
	public int getMatchid() {
		return matchid;
	}
	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}
	
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
	public String getPlayersex() {
		return playersex;
	}
	public void setPlayersex(String playersex) {
		this.playersex = playersex;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getRecordlevel() {
		return recordlevel;
	}
	public void setRecordlevel(String recordlevel) {
		this.recordlevel = recordlevel;
	}
	
	
	public String getMatchtype() {
		return matchtype;
	}
	public void setMatchtype(String matchtype) {
		this.matchtype = matchtype;
	}
	public int getGp2spid() {
		return gp2spid;
	}
	public void setGp2spid(int gp2spid) {
		this.gp2spid = gp2spid;
	}
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
		
}








