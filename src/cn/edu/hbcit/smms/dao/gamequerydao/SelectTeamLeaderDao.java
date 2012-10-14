package cn.edu.hbcit.smms.dao.gamequerydao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;

public class SelectTeamLeaderDao {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cs = null;
	private Connection conn = null;
	ResultSet rs = null;
	DBConn db = new DBConn();
	JSONArray jsonarray = new JSONArray();
	public JSONArray SelectTeamLeader(int sportid,int departid){
		String sql = null;
		if(departid==0){
			sql = " SELECT t_sports.sportsname,t_department.departshortname,teamleader,coach,doctor" +
			" FROM t_sports2department" +
			" JOIN t_sports ON t_sports2department.sportsid = t_sports.id" +
			" JOIN t_department ON t_sports2department.departid = t_department.id" +
			" WHERE  t_sports.id = ? OR t_department.id = ?";
		}else{
			sql = " SELECT t_sports.sportsname,t_department.departshortname,teamleader,coach,doctor" +
			" FROM t_sports2department" +
			" JOIN t_sports ON t_sports2department.sportsid = t_sports.id" +
			" JOIN t_department ON t_sports2department.departid = t_department.id" +
			" WHERE  t_sports.id = ? AND t_department.id = ?";
		}
		conn = db.getConn();
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportid);
			pstmt.setInt(2, departid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("sportsname", rs.getString(1));
				jsonobj.put("departshortname", rs.getString(2));
				jsonobj.put("teamleader", rs.getString(3));
				jsonobj.put("coach", rs.getString(4));
				jsonobj.put("doctor", rs.getString(5));
				jsonarray.add(jsonobj);
			}
			rs.close();
			pstmt.close();
			db.freeConnection(conn);
		}catch(Exception e){
			e.getStackTrace();
			e.getMessage();
		}
		return jsonarray;
	}
	}
