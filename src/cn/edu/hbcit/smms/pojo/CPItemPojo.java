package cn.edu.hbcit.smms.pojo;
/**
 * 运功会项目pojo  为了方便分组而建立的pojo
 * @author 韩鑫鹏
 * 创建时间：2012-06-14 09:00:00
 */
public class CPItemPojo {
	private int itemId; //项目id
	private int gp2spid; //组别与运动会id
	private String itemType; //项目类型
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getGp2spid() {
		return gp2spid;
	}
	public void setGp2spid(int gp2spid) {
		this.gp2spid = gp2spid;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
