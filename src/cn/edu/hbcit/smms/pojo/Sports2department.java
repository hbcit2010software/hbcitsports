package cn.edu.hbcit.smms.pojo;

public class Sports2department {
	private int id;
	private int sportsid;
	private int departid;
	private int departtype;
	private String teamleader;
	private String coach;
	private String doctor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSportsid() {
		return sportsid;
	}
	public void setSportsid(int sportsid) {
		this.sportsid = sportsid;
	}
	public int getDepartid() {
		return departid;
	}
	public void setDepartid(int departid) {
		this.departid = departid;
	}
	public String getTeamleader() {
		return teamleader;
	}
	public void setTeamleader(String teamleader) {
		this.teamleader = teamleader;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the departtype
	 */
	public int getDeparttype() {
		return departtype;
	}
	/**
	 * @param departtype the departtype to set
	 */
	public void setDeparttype(int departtype) {
		this.departtype = departtype;
	}

}
