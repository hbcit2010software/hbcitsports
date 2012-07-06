package cn.edu.hbcit.smms.pojo;

public class Player {
	int id;
	int sp2dpid;
	String playernum;
	String playername;
	boolean playersex;
	int groupid ;
	String registitem;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSp2dpid() {
		return sp2dpid;
	}
	public void setSp2dpid(int sp2dpid) {
		this.sp2dpid = sp2dpid;
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
	public boolean isPlayersex() {
		return playersex;
	}
	public void setPlayersex(boolean playersex) {
		this.playersex = playersex;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getRegistitem() {
		return registitem;
	}
	public void setRegistitem(String registitem) {
		this.registitem = registitem;
	}
	String groupname;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	boolean grouptype;
	public boolean isGrouptype() {
		return grouptype;
	}
	public void setGrouptype(boolean grouptype) {
		this.grouptype = grouptype;
	}
	
	
	
	

}
