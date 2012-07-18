package cn.edu.hbcit.smms.pojo;
/**
 * @version 2012-7-18下午10:32:07	修改 BY 李玮
 */
public class Item {
	private String itemname;
	private int itemtype;
	private int scoreformatid;
	private int id;
	private int itemid;
	
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the itemtype
	 */
	public int getItemtype() {
		return itemtype;
	}
	/**
	 * @param itemtype the itemtype to set
	 */
	public void setItemtype(int itemtype) {
		this.itemtype = itemtype;
	}
	/**
	 * @return the scoreformatid
	 */
	public int getScoreformatid() {
		return scoreformatid;
	}
	/**
	 * @param scoreformatid the scoreformatid to set
	 */
	public void setScoreformatid(int scoreformatid) {
		this.scoreformatid = scoreformatid;
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

}
