/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛事记录
* 子模块名称：   
*
* 备注：
*
* 修改历史：
* 2012-10-15	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.pojo;

/**
 * @author liwei
 * @version 1.00  2012-10-15上午12:05:42	新建
 */

public class Record {

	private int id;
	private int itemid;
	private String itemname;
	private int sex;
	private String score;
	private String departname;
	private String sportsname;
	private String playername;
	private String recordtime;
	private String recordlevel;
	private String itemtype;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the itemid
	 */
	public int getItemid() {
		return itemid;
	}
	/**
	 * @param itemid the itemid to set
	 */
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	/**
	 * @return the itemname
	 */
	public String getItemname() {
		return itemname;
	}
	/**
	 * @param itemname the itemname to set
	 */
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return the departname
	 */
	public String getDepartname() {
		return departname;
	}
	/**
	 * @param departname the departname to set
	 */
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	/**
	 * @return the sportsname
	 */
	public String getSportsname() {
		return sportsname;
	}
	/**
	 * @param sportsname the sportsname to set
	 */
	public void setSportsname(String sportsname) {
		this.sportsname = sportsname;
	}
	/**
	 * @return the playername
	 */
	public String getPlayername() {
		return playername;
	}
	/**
	 * @param playername the playername to set
	 */
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	/**
	 * @return the recordtime
	 */
	public String getRecordtime() {
		return recordtime;
	}
	/**
	 * @param recordtime the recordtime to set
	 */
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	/**
	 * @return the recordlevel
	 */
	public String getRecordlevel() {
		return recordlevel;
	}
	/**
	 * @param recordlevel the recordlevel to set
	 */
	public void setRecordlevel(String recordlevel) {
		this.recordlevel = recordlevel;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
}
