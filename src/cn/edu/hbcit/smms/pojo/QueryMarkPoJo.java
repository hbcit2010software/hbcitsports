package cn.edu.hbcit.smms.pojo;

import java.util.ArrayList;

public class QueryMarkPoJo {

	private int playerid;//运动员id
	private int studetsMarks;//学生实得积分
	private int teacherMarks;//教工实得积分 
	private String depName;//系别名称
	private int finalStudentsSum;//学生最终积分
	private int finalTeacherSum;//教工最终积分
	private int departid;

	public int getStudetsMarks() {
		return studetsMarks;
	}
	public void setStudetsMarks(int studetsMarks) {
		this.studetsMarks = studetsMarks;
	}
	public int getTeacherMarks() {
		return teacherMarks;
	}
	public void setTeacherMarks(int teacherMarks) {
		this.teacherMarks = teacherMarks;
	}
	
	public int getFinalStudentsSum() {
		return finalStudentsSum;
	}
	public void setFinalStudentsSum(int finalStudentsSum) {
		this.finalStudentsSum = finalStudentsSum;
	}
	public int getFinalTeacherSum() {
		return finalTeacherSum;
	}
	public void setFinalTeacherSum(int finalTeacherSum) {
		this.finalTeacherSum = finalTeacherSum;
	}
	public int getPlayerid() {
		return playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public int getDepartid() {
		return departid;
	}
	public void setDepartid(int departid) {
		this.departid = departid;
	}

}
