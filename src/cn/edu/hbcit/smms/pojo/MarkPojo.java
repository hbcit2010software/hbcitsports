package cn.edu.hbcit.smms.pojo;

public class MarkPojo {
	private int departId;
	private int sport2departId;
	private int rank;  //名次
	private String departName;  //部门名称
	private int maleStuMark;   //学生男子组总积分
	private int femaleStuMark;  //学生女子组总积分
	private int maleTeachMark;  //教工男子组总积分
	private int femaleTeachMark;  //教工女子组总积分
	private int allStuMarks;    //学生组总积分
	private int allTeasMarks;   //教工组总积分
	
	private String stuName;   //学生姓名
	private String stuScore;  //学生成绩
	private int stuMark;      //学生个人积分
	private String itemName;  //项目名称
	private int itemId;
	private String stuInf;
	private int position;
	
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getSport2departId() {
		return sport2departId;
	}
	public void setSport2departId(int sport2departId) {
		this.sport2departId = sport2departId;
	}
	public int getMaleStuMark() {
		return maleStuMark;
	}
	public void setMaleStuMark(int maleStuMark) {
		this.maleStuMark = maleStuMark;
	}
	public int getFemaleStuMark() {
		return femaleStuMark;
	}
	public void setFemaleStuMark(int femaleStuMark) {
		this.femaleStuMark = femaleStuMark;
	}
	public int getMaleTeachMark() {
		return maleTeachMark;
	}
	public void setMaleTeachMark(int maleTeachMark) {
		this.maleTeachMark = maleTeachMark;
	}
	public int getFemaleTeachMark() {
		return femaleTeachMark;
	}
	public void setFemaleTeachMark(int femaleTeachMark) {
		this.femaleTeachMark = femaleTeachMark;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public int getAllStuMarks() {
		return allStuMarks;
	}
	public void setAllStuMarks(int allStuMarks) {
		this.allStuMarks = allStuMarks;
	}
	public int getAllTeasMarks() {
		return allTeasMarks;
	}
	public void setAllTeasMarks(int allTeasMarks) {
		this.allTeasMarks = allTeasMarks;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuScore() {
		return stuScore;
	}
	public void setStuScore(String stuScore) {
		this.stuScore = stuScore;
	}
	public int getStuMark() {
		return stuMark;
	}
	public void setStuMark(int stuMark) {
		this.stuMark = stuMark;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getStuInf() {
		return stuInf;
	}
	public void setStuInf(String stuInf) {
		this.stuInf = stuInf;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

}
