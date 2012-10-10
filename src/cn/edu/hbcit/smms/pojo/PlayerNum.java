package cn.edu.hbcit.smms.pojo;
//李玮 2012.9.18添加三个字段
public class PlayerNum {
	private String beginnum;
	private String endnum;
	private String departshortname;
	private int numtype;
	private int id;
	private String playerNum;

	public String getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(String playerNum) {
		this.playerNum = playerNum;
	}
	public String getBeginnum() {
		return beginnum;
	}
	public void setBeginnum(String beginnum) {
		this.beginnum = beginnum;
	}
	public String getEndnum() {
		return endnum;
	}
	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}
	/**
	 * @return the departshortname
	 */
	public String getDepartshortname() {
		return departshortname;
	}
	/**
	 * @param departshortname the departshortname to set
	 */
	public void setDepartshortname(String departshortname) {
		this.departshortname = departshortname;
	}
	/**
	 * @return the numtype
	 */
	public int getNumtype() {
		return numtype;
	}
	/**
	 * @param numtype the numtype to set
	 */
	public void setNumtype(int numtype) {
		this.numtype = numtype;
	}
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
	

}
