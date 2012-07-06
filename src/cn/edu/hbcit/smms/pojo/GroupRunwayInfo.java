package cn.edu.hbcit.smms.pojo;
/**
 * 各组运动员跑道的pojo
 * 
 * 本类的简要信息
 * @author 田小英
 * 
 * 
 *
 */
public class GroupRunwayInfo {
	private int teamNum;
	private String playNum;
	private int runway;
	private int playerId;
	public int getTeamNum() {
		return teamNum;
	}
	public void setTeamNum(int teamNum) {
		this.teamNum = teamNum;
	}
	public String getPlayNum() {
		return playNum;
	}
	public void setPlayNum(String playNum) {
		this.playNum = playNum;
	}
	public int getRunway() {
		return runway;
	}
	public void setRunway(int runway) {
		this.runway = runway;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

}
