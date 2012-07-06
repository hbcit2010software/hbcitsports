package cn.edu.hbcit.smms.dao.createprogramdao;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     秩序册生成模块
 * 子模块名称：  手动调整
 *
 * 备注：
 *
 * 修改历史：
 * 时间		   版本号     	姓名		修改内容
 * 2012-6-25 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.FinalitemGroup;



public class SelectItems {

	DBConn db = new DBConn();
	ResultSet rs = null;
	Connection conn = null;
	private PreparedStatement ps;

	public ArrayList selectItemsById(int sportsid, String itemtype) {
		ArrayList ItemsList = new ArrayList();
		
//		ArrayList ItemsId = new ArrayList();
//		ArrayList List = new ArrayList();
		// FinalitemGroup fg=new FinalitemGroup();
		try {
			conn = db.getConn();
			ps = conn.prepareStatement("SELECT t_group.groupname,t_finalitem.finalitemname,t_finalitem.id "
							+ "FROM t_finalitem "
							+ "JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id "
							+ "JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id "
							+ "JOIN t_group ON t_group2sports.groupid = t_group.id "
							+ "JOIN t_item ON t_group2item.itemid = t_item.id "
							+ "WHERE t_group2sports.sportsid = ? AND t_item.itemtype = ?");
			ps.setInt(1, sportsid);
			ps.setString(2, itemtype);
			rs = ps.executeQuery();

			while (rs.next()) {
				FinalitemGroup fg=new FinalitemGroup();
				fg.setGroupname(rs.getString(1));
				fg.setFinalitemname(rs.getString(2));
				fg.setFmid(rs.getInt(3));
				ItemsList.add(fg);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		db.freeConnection(conn);
		return ItemsList;

	}

}
