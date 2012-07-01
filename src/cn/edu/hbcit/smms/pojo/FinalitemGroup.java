package cn.edu.hbcit.smms.pojo;

public final class FinalitemGroup {
	public String getFinalitemname() {
		return finalitemname;
	}
	public void setFinalitemname(String finalitemname) {
		this.finalitemname = finalitemname;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	private String finalitemname;
	private String groupname;
	private int fmid;
	public int getFmid() {
		return fmid;
	}
	public void setFmid(int fmid) {
		this.fmid = fmid;
	}

}
