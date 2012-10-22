package cn.edu.hbcit.smms.dao.gamequerydao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.QueryRegistitemToItems;
import cn.edu.hbcit.smms.pojo.QuerySeInfoData;

/*
 * Copyright(C) 2004, XXXXXXXX.
 *
 * 模块名称：     AAAAAAAAAAA
 * 子模块名称：   BBBBBBBBBBB
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名:袁仕杰		修改内容
 * 
 * 
 */
public class GameQuerySelectInfDAO  {
	/**
	 * GameQuerySelectInfDAO类
	 *
	 *简要说明
	 *
	 *详细解释。
	 * @author 袁仕杰
	 * @version 1.00  2011/12/07 新規作成<br>
	 */
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cs = null;
	private Connection conn = null;
	ResultSet rs = null;
	DBConn db = new DBConn();
	QuerySeInfoData qsi = new QuerySeInfoData();
	/**
	 * 
	 * @param sportsid
	 * @param sportname
	 * @param departname
	 * @param province
	 * @param itemtype
	 * @param item
	 * @param score1
	 * @param score2
	 * @param breakrecord
	 * @return	
	 * @throws SQLException
	 */
public JSONArray selectInQuestion(int sportsid,String playername,int departname,
		int province,String itemtype,int item,String score1,String score2,String breakrecord){
	Logger log = Logger.getLogger(GameQuerySelectInfDAO.class.getName());
	
	JSONArray jsonarray = new JSONArray();
	StringBuffer selectInf = new StringBuffer();
	/**
	 * if(breakrecord.equals("0")&&0 == item)判断前台页面单选框选择条件如果未选择项目和破纪录情况执行此语句
	 * */
	if( breakrecord.equals("0")&&0 == item && itemtype.equals("0")){
		selectInf.append(" SELECT  DISTINCT t_player.playername,t_player.playernum,t_group.groupname,t_department.departname"+
" FROM t_position"+ 
" JOIN t_player ON t_player.id = t_position.playerid"+
                " JOIN t_group ON t_player.groupid = t_group.id"+
                " JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.id"+
                " JOIN t_department ON t_sports2department.departid = t_department.id"+
                " JOIN t_sports ON t_sports2department.sportsid = t_sports.id"+
                " JOIN t_finalitem ON t_finalitem.id = t_position.finalitemid"+
                " JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id"+
                " JOIN t_item ON t_group2item.itemid = t_item.id"+
		 " WHERE ");
		if(0 != sportsid){
			selectInf.append(" t_sports.id = "+ sportsid +" ");
		}
		if(!playername.equals("")){
			selectInf.append("and t_player.playername = '"+ playername +"' ");
		}
		if(0 != departname){
			selectInf.append("and t_department.id = "+ departname +" ");
		}
		if(0 != province){
			selectInf.append("and t_group.id = "+ province +" ");
		}
		if(!itemtype.equals("0")){
			selectInf.append("and itemtype = '"+ itemtype +"' ");
		}
		if(0 != item){
			selectInf.append("and t_item.id ="+ item +" ");
		}
		if(!score1.equals("") && !score2.equals("")){
			selectInf.append("and t_position.score >"+ score1 +" and t_position.score <"+score2+" ");

		}
		selectInf.append(";");
	String sql = selectInf.toString();
	QueryRegistitemToItems qrti = new QueryRegistitemToItems();
	log.debug(sql);
	conn = db.getConn();
	try{
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while( rs.next()){
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("playername", rs.getString(1));
			jsonobj.put("playernum", rs.getString(2));
			jsonobj.put("groupname", rs.getString(3));
			jsonobj.put("departshortname", rs.getString(4));
			jsonobj.put("itemname", qrti.getItems(rs.getString(2)));
			jsonobj.put("score", qrti.getScores(rs.getString(2)));
			jsonobj.put("position", qrti.getPosition(rs.getString(2)));
			jsonobj.put("recordlevel",qrti.getRecordlevel(rs.getString(2)));
			jsonarray.add(jsonobj);
		}
		rs.close();
		stmt.close();
		db.freeConnection(conn);
	}catch( Exception e){
		e.getStackTrace();
	}
}else if(breakrecord.equals("1")){                      //如果选择了破纪录执行此语句
	selectInf.append("SELECT DISTINCT t_record.playername,t_player.playernum,t_group.groupname," +
			"t_record.departname,t_item.itemname,t_record.score,t_position.position,recordlevel" +
			" FROM  t_record " +
			" JOIN t_item ON t_record.itemid = t_item.id" +
			" JOIN t_player ON  t_record.playername = t_player.playername" +
			" JOIN t_group ON (t_record.playername = t_player.playername AND t_player.groupid = t_group.id)" +
			" JOIN t_position ON (t_record.playername = t_player.playername AND t_player.id = t_position.playerid)" +
			" JOIN t_department ON t_department.departname = t_record.departname" +
			" JOIN t_sports ON t_sports.sportsname = t_record.sportsname " +
			" WHERE ");
	if(0 != sportsid){
		selectInf.append(" t_sports.id = "+ sportsid +" ");
	}
	if(!playername.equals("")){
		selectInf.append("and t_record.playername = '"+ playername +"' ");
	}
	if(0 != departname){
		selectInf.append("and t_department.id = "+ departname +" ");
	}
	if(0 != province){
		selectInf.append("and t_group.id = "+ province +" ");
	}
	if(!itemtype.equals("0")){
		selectInf.append("and itemtype = '"+ itemtype +"' ");
	}
	if(0 != item){
		selectInf.append("and t_record.itemid ="+ item +" ");
	}
	if(!score1.equals("") && !score2.equals("")){
		selectInf.append("and t_record.score >"+ score1 +" and t_record.score <"+score2+" ");

	}
	selectInf.append("and t_record.score = t_position.score");
	String sql = selectInf.toString();
	QueryRegistitemToItems qrti = new QueryRegistitemToItems();
	log.debug(sql);
	conn = db.getConn();
	try{
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while( rs.next()){
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("playername", rs.getString(1));
			jsonobj.put("playernum", rs.getString(2));
			jsonobj.put("groupname", rs.getString(3));
			jsonobj.put("departshortname", rs.getString(4));
			jsonobj.put("itemname", rs.getString(5));
			jsonobj.put("score", rs.getString(6));
			jsonobj.put("position", rs.getString(7));
			String rec=null;
			if(rs.getString(8).equals("")){
				rec = "无";
			}else if(rs.getString(8).equals("0")){
				rec = "<font color='red'>院</font>";
			}else if(rs.getString(8).equals("1")){
				rec = "<font color='blue'>省</font>";
			}
			jsonobj.put("recordlevel",rec);
			jsonarray.add(jsonobj);
		}
		rs.close();
		stmt.close();
		db.freeConnection(conn);
	}catch( Exception e){
		e.getStackTrace();
	}
	}else if(breakrecord.equals("0")&&0 != item ||breakrecord.equals("0")&&!itemtype.equals("0")){//如果未选中破纪录选择了项目名称
		if(itemtype.equals("3")){
			//项目类型为接力的成绩
			selectInf.append("SELECT DISTINCT t_department.departshortname,t_finalitem.finalitemname,t_position.score,t_position.position,t_match.recordlevel" +
					" FROM t_position" +
					" JOIN t_player ON t_position.playerid = t_player.id" +
					" JOIN t_group ON t_group.id = t_player.groupid" +
					" JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.id" +
					" JOIN t_sports ON t_sports2department.sportsid = t_sports.id" +
					" JOIN t_department ON t_sports2department.departid = t_department.id" +
					" JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id" +
					" JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id" +
					" JOIN t_match ON t_finalitem.id = t_match.finalitemid"+
					" JOIN t_item ON t_group2item.itemid = t_item.id"+
					" WHERE");
		}else{
		selectInf.append("SELECT DISTINCT t_player.playername,t_player.playernum,groupname,t_department.departshortname,t_finalitem.finalitemname"+
				 " FROM t_position"+
				 " JOIN t_player ON t_position.playerid = t_player.id"+
				 " JOIN t_group ON t_group.id = t_player.groupid"+
				 " JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.id"+
				 " JOIN t_sports ON t_sports2department.sportsid = t_sports.id"+
				 " JOIN t_department ON t_sports2department.departid = t_department.id"+
				 " JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id"+
				 " JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id"+
				 " JOIN t_item ON t_group2item.itemid = t_item.id"+
				" WHERE");
		}
		if(0 != sportsid){
			selectInf.append(" t_sports.id = "+ sportsid +" ");
		}
		if(!playername.equals("")){
			selectInf.append("and t_player.playername = '"+ playername +"' ");
		}
		if(0 != departname){
			selectInf.append("and t_department.id = "+ departname +" ");
		}
		if(0 != province){
			selectInf.append("and t_group.id = "+ province +" ");
		}
		if(!itemtype.equals("0")){
			selectInf.append("and itemtype = '"+ itemtype +"' ");
		}
		if(0 != item){
			selectInf.append("and t_item.id ="+ item +" ");
		}
		if(!score1.equals("") && !score2.equals("")){
			selectInf.append(" and t_position.score >"+ score1 +" and t_position.score <"+score2+" ");
		}
		selectInf.append(";");
	String sql = selectInf.toString();
		conn = db.getConn();
	QueryRegistitemToItems qrti = new QueryRegistitemToItems();
	log.debug(sql);
	try{
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(itemtype.equals("3")){
			while(rs.next()){
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("size", 5);
				jsonobj.put("departshortname", rs.getString(1));
				jsonobj.put("finalitemname", rs.getString(2));
				jsonobj.put("score", rs.getString(3));
				jsonobj.put("position", rs.getString(4));
				String rec = "";
				if(rs.getString(5).equals("2")){
					rec = "无";
				}else if(rs.getString(5).equals("0")){
					rec = "<font color='red'>院</font>";
				}else if(rs.getString(5).equals("1")){
					rec = "<font color='blue'>省</font>";
				}
				jsonobj.put("recordlevel",rec);//有无破纪录t_match表中的t_sport.id查询出recordlevel。
				log.debug(jsonobj.size());
				jsonarray.add(jsonobj);
			}
		}else{
		while( rs.next()){
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("size", 7);
			jsonobj.put("playername", rs.getString(1));
			jsonobj.put("playernum", rs.getString(2));
			jsonobj.put("groupname", rs.getString(3));
			jsonobj.put("departshortname", rs.getString(4));
			jsonobj.put("itemname", qrti.getItem(rs.getString(2), rs.getString(5)));
			jsonobj.put("score", qrti.getScore(rs.getString(2),rs.getString(5)));
			jsonobj.put("position", qrti.getPosition1(rs.getString(2),rs.getString(5)));
			jsonobj.put("recordlevel",qrti.getRecordlevel2(rs.getString(2), rs.getString(5)));
			jsonarray.add(jsonobj);
		}
		}
		rs.close();
		stmt.close();
		db.freeConnection(conn);
	}catch( Exception e){
		e.getStackTrace();
		}
}
	return jsonarray;
}

}
