package cn.edu.hbcit.smms.pojo;

public class GameApplyPJ {

	private int id ;
	private String groupname;
	private int grouptype;
	private String playernum;
	private String playername;
	private int  playersex;
	private int groupid;
	private String registitem;
	private int sp2dpid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public String getRegistitem() {
		return registitem;
	}
	public void setRegistitem(String registitem) {
		this.registitem = registitem;
	}
	public int getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}
	public int getPlayersex() {
		return playersex;
	}
	public void setPlayersex(int playersex) {
		this.playersex = playersex;
	}
	public int getSp2dpid() {
		return sp2dpid;
	}
	public void setSp2dpid(int sp2dpid) {
		this.sp2dpid = sp2dpid;
	}
	
	
	private int departid;
	private String departname;
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
	private int itemid;
	private String itemname;
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
