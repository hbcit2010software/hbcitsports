package cn.edu.hbcit.smms.pojo;

//李玮 2012-07-19 修改
public class Group2itemPojo {
private int id ;
private int gp2spid;
private int itemid;
private String matchtype;
private String gp2spidItemidMatchtype; //连接三字段

public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public int getGp2spid() {
return gp2spid;
}
public void setGp2spid(int gp2spid) {
this.gp2spid = gp2spid;
}
public int getItemid() {
return itemid;
}
public void setItemid(int itemid) {
this.itemid = itemid;
}
public String getMatchtype() {
return matchtype;
}
public void setMatchtype(String matchtype) {
this.matchtype = matchtype;
}
/**
* @return the gp2spidItemidMatchtype
*/
public String getGp2spidItemidMatchtype() {
return gp2spidItemidMatchtype;
}
/**
* @param gp2spidItemidMatchtype the gp2spidItemidMatchtype to set
*/
public void setGp2spidItemidMatchtype(String gp2spidItemidMatchtype) {
this.gp2spidItemidMatchtype = gp2spidItemidMatchtype;
}

}


