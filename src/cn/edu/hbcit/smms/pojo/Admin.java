package cn.edu.hbcit.smms.pojo;

public class Admin {
	String groupname;  //组名称
	
	int grouptype;     //组类型
	int iteId;         //项目id
	
	String plaName;    //运动员姓名
	int plaSex;        //运动员性别
	String sor;        //成绩
	String depName;    //系别
	String recTime;    //破记录时间
	String recLevel;   //记录级别
	String sportsName; //运动会名称
	String sportsName1;//记录运动会名称
	String firstDate;  //起始日期
	String lastDate;   //结束日期
	String zDate;      //报名截止日期
	String adress;     //大会地址
	int sportsid;	   //运动会id
	String itemName;   //项目名称
	
	int recordId;		//学生记录Id
	String meetingJl;   //大会纪律
	String openDh;      //开幕式
	String closeDh;     //闭幕式
	String rems1;		//参加办法
	String rems2;		//竞赛说明
	String rems3;		//计分方法
	String rems4;		//其他
	
	String finalItemType;  //拆分后的项目类型
	int gp2ItId;        //group2item 分组与项目对应关系表Id
	String itemPsType;    //项目比赛类型
	int finalItemId;      // t_finalitem分项竞赛项目表 Id 
	String finalItemName;  //拆分后的项目名称
	String itemDate;       //项目比赛日期
	String itemTime;       //项目比赛时间
	int promotionNum;      //晋级数量
	public int getPromotionNum() {
		return promotionNum;
	}
	public void setPromotionNum(int promotionNum) {
		this.promotionNum = promotionNum;
	}
	public String getItemDate() {
		return itemDate;
	}
	public void setItemDate(String itemDate) {
		this.itemDate = itemDate;
	}
	public String getItemTime() {
		return itemTime;
	}
	public void setItemTime(String itemTime) {
		this.itemTime = itemTime;
	}

	public String getFinalItemName() {
		return finalItemName;
	}
	public void setFinalItemName(String finalItemName) {
		this.finalItemName = finalItemName;
	}
	public int getFinalItemId() {
		return finalItemId;
	}
	public void setFinalItemId(int finalItemId) {
		this.finalItemId = finalItemId;
	}
	public String getItemPsType() {
		return itemPsType;
	}
	public void setItemPsType(String itemPsType) {
		this.itemPsType = itemPsType;
	}
	public int getGp2ItId() {
		return gp2ItId;
	}
	public void setGp2ItId(int gp2ItId) {
		this.gp2ItId = gp2ItId;
	}
	public String getFinalItemType() {
		return finalItemType;
	}
	public void setFinalItemType(String finalItemType) {
		this.finalItemType = finalItemType;
	}
	public String getRems1() {
		return rems1;
	}
	public void setRems1(String rems1) {
		this.rems1 = rems1;
	}
	public String getRems2() {
		return rems2;
	}
	public void setRems2(String rems2) {
		this.rems2 = rems2;
	}
	public String getRems3() {
		return rems3;
	}
	public void setRems3(String rems3) {
		this.rems3 = rems3;
	}
	public String getRems4() {
		return rems4;
	}
	public void setRems4(String rems4) {
		this.rems4 = rems4;
	}
 
	public String getMeetingJl() {
		return meetingJl;
	}
	public void setMeetingJl(String meetingJl) {
		this.meetingJl = meetingJl;
	}
	public String getOpenDh() {
		return openDh;
	}
	public void setOpenDh(String openDh) {
		this.openDh = openDh;
	}
	public String getCloseDh() {
		return closeDh;
	}
	public void setCloseDh(String closeDh) {
		this.closeDh = closeDh;
	}
 
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getzDate() {
		return zDate;
	}
	public void setzDate(String zDate) {
		this.zDate = zDate;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
 
	public String getSportsName1() {
		return sportsName1;
	}
	public void setSportsName1(String sportsName1) {
		this.sportsName1 = sportsName1;
	}
	public String getSportsName() {
		return sportsName;
	}
	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}
	 
	 
	public int getSportsid() {
		return sportsid;
	}
	public void setSportsid(int sportsid) {
		this.sportsid = sportsid;
	}
 
	public int getIteId() {
		return iteId;
	}
	public void setIteId(int iteId) {
		this.iteId = iteId;
	}
	public String getPlaName() {
		return plaName;
	}
	public void setPlaName(String plaName) {
		this.plaName = plaName;
	}
	public int getPlaSex() {
		return plaSex;
	}
	public void setPlaSex(int plaSex) {
		this.plaSex = plaSex;
	}
	public String getSor() {
		return sor;
	}
	public void setSor(String sor) {
		this.sor = sor;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getRecTime() {
		return recTime;
	}
	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}
	public String getRecLevel() {
		return recLevel;
	}
	public void setRecLevel(String recLevel) {
		this.recLevel = recLevel;
	}
 
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}
	 
	 
}

 