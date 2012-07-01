package cn.edu.hbcit.smms.pojo;
/**
 * player表POJO类
 *
 * 本类的简要描述：
 *
 * @author 李兆珠
 * @version 1.00  2012-6-20 新建类
 */
public class PlayerPojo {
	private int id;
	private int sp2dpid; 
	private String playernum;
	private String playername;
	private short playersex;
	private int groupid;
	private String registitem;
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
	public short getPlayersex() {
		return playersex;
	}
	public void setPlayersex(short playersex) {
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

}
