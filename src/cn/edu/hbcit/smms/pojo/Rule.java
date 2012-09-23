/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     
* 子模块名称：   
*
* 备注：
*
* 修改历史：
* 2012-9-23	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.pojo;


public class Rule {

	private int id;
	private int sportsid;
	private int position;
	private String mark;
	private int recordmark_low;
	private int recordmark_high;
	private int perman;
	private int perdepartment;
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
	 * @return the sportsid
	 */
	public int getSportsid() {
		return sportsid;
	}
	/**
	 * @param sportsid the sportsid to set
	 */
	public void setSportsid(int sportsid) {
		this.sportsid = sportsid;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}
	/**
	 * @param mark the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * @return the recordmark_low
	 */
	public int getRecordmark_low() {
		return recordmark_low;
	}
	/**
	 * @param recordmarkLow the recordmark_low to set
	 */
	public void setRecordmark_low(int recordmarkLow) {
		recordmark_low = recordmarkLow;
	}
	/**
	 * @return the recordmark_high
	 */
	public int getRecordmark_high() {
		return recordmark_high;
	}
	/**
	 * @param recordmarkHigh the recordmark_high to set
	 */
	public void setRecordmark_high(int recordmarkHigh) {
		recordmark_high = recordmarkHigh;
	}
	/**
	 * @return the perman
	 */
	public int getPerman() {
		return perman;
	}
	/**
	 * @param perman the perman to set
	 */
	public void setPerman(int perman) {
		this.perman = perman;
	}
	/**
	 * @return the perdepartment
	 */
	public int getPerdepartment() {
		return perdepartment;
	}
	/**
	 * @param perdepartment the perdepartment to set
	 */
	public void setPerdepartment(int perdepartment) {
		this.perdepartment = perdepartment;
	}
}
