package cn.edu.hbcit.smms.pojo;

public class Stujudge {
	private int id;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	private int sp2dpid;
	private String contact;
	private String tel;
	private String member;
}
