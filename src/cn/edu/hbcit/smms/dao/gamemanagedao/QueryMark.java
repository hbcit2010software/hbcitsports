package cn.edu.hbcit.smms.dao.gamemanagedao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.MarkPojo;

import com.lowagie.text.DocumentException;

public class QueryMark {

	protected final Logger log = Logger.getLogger(QueryMark.class.getName());

	DBConn dbc = new DBConn();
	LoginDAO ld = new LoginDAO();
	int sportsid = ld.selectCurrentSportsId();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	/**
	 * 获取学生男子组总积分
	 * 
	 * @param depNameList
	 * @return
	 */

	public ArrayList getMaleStudentsMark() {
		ArrayList maleStudentsMarkList = new ArrayList();
		try {
			conn = dbc.getConn();

			String sql = "SELECT t_department.id,t_sports2department.id,t_department.departname,SUM(marks) AS summarks "
					+ "FROM t_position "
					+ "JOIN t_player ON t_player.id=t_position.playerid "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid "
					+ "WHERE t_sports2department.sportsid=? AND t_player.playertype=1 AND t_player.playersex=1 "
					+ "GROUP BY t_department.departname "
					+ "ORDER BY summarks DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			int sumRank = 1;
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MarkPojo qm = new MarkPojo();
				qm.setRank(sumRank++);
				qm.setDepartId(rs.getInt(1));
				qm.setSport2departId(rs.getInt(2));
				qm.setDepartName(rs.getString(3));
				qm.setMaleStuMark(rs.getInt(4));
				maleStudentsMarkList.add(qm);
				System.out.println("getmaleStudetsMarks========"
						+ qm.getMaleStuMark());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return maleStudentsMarkList;
	}

	/**
	 * 获取学生女子组总积分
	 * 
	 * @param depNameList
	 * @return
	 */

	public ArrayList getFemaleStudentsMark() {
		ArrayList femaleStudentsMarkList = new ArrayList();
		try {
			conn = dbc.getConn();

			String sql = "SELECT t_department.id,t_sports2department.id,t_department.departname,SUM(marks) AS summarks "
					+ "FROM t_position "
					+ "JOIN t_player ON t_player.id=t_position.playerid "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid "
					+ "WHERE t_sports2department.sportsid=? AND t_player.playertype=1 AND t_player.playersex=0 "
					+ "GROUP BY t_department.departname "
					+ "ORDER BY summarks DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			int sumRank = 1;

			rs = pstmt.executeQuery();

			while (rs.next()) {

				MarkPojo qm = new MarkPojo();
				qm.setRank(sumRank++);
				qm.setDepartId(rs.getInt(1));
				qm.setSport2departId(rs.getInt(2));
				qm.setDepartName(rs.getString(3));
				qm.setFemaleStuMark(rs.getInt(4));
				femaleStudentsMarkList.add(qm);
				System.out.println("getmaleStudetsMarks========"
						+ qm.getFemaleStuMark());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return femaleStudentsMarkList;
	}

	/**
	 * 计算学生组总积分
	 * 
	 * @param sportsid
	 * @return
	 */

	public ArrayList getAllStusMarks() {

		ArrayList allStuMarks = new ArrayList();
		try {
			conn = dbc.getConn();
			String sql = "SELECT t_department.id,t_sports2department.id,t_department.departname,SUM(marks) AS summarks "
					+ "FROM t_position "
					+ "JOIN t_player ON t_player.id=t_position.playerid "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid "
					+ "WHERE t_sports2department.sportsid=? AND t_player.playertype=1 "
					+ "GROUP BY t_department.departname "
					+ "ORDER BY summarks DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			int allRank = 1;
			while (rs.next()) {
				MarkPojo mp = new MarkPojo();
				mp.setRank(allRank++);
				mp.setDepartId(rs.getInt(1));
				mp.setSport2departId(rs.getInt(2));
				mp.setDepartName(rs.getString(3));
				mp.setAllStuMarks(rs.getInt(4));
				allStuMarks.add(mp);

			}

			dbc.freeConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return allStuMarks;
	}

	/**
	 * 向mark表中插入数据
	 * 
	 * @param sportsid
	 * @return
	 */
	public int insertAllStuMarks() {
		ArrayList allStuMarks = this.getAllStusMarks();
		System.out.println(allStuMarks.size());
		int flag = 0;
		try {
			conn = dbc.getConn();
			String sql = "insert into t_mark(sp2dpid,stusum) values";
			for (int i = 0; i < allStuMarks.size(); i++) {
				MarkPojo mp = (MarkPojo) allStuMarks.get(i);
				if (i == allStuMarks.size() - 1) {
					sql += "(" + mp.getSport2departId() + ","
							+ mp.getAllStuMarks() + ")";
				} else {
					sql += "(" + mp.getSport2departId() + ","
							+ mp.getAllStuMarks() + ")" + ",";
				}
			}
			pstmt = conn.prepareStatement(sql);
			flag = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return flag;
	}

	/**
	 * 查询 mark表中是否存在记录
	 * 
	 * @return
	 */

	public boolean selectAllMarks() {
		boolean flag = false;
		try {
			conn = dbc.getConn();
			String sql = "SELECT * FROM t_mark "
					+ "JOIN t_sports2department ON t_sports2department.id=t_mark.sp2dpid "
					+ "WHERE t_sports2department.sportsid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return flag;
	}

	/**
	 * 获取教工男子组总积分
	 * 
	 * @param depNameList
	 * @return
	 */

	public ArrayList getMaleTeachersMark() {
		ArrayList maleTeacherMarkList = new ArrayList();
		try {
			conn = dbc.getConn();

			String sql = "SELECT t_department.id,t_sports2department.id,t_department.departname,SUM(marks) AS summarks "
					+ "FROM t_position "
					+ "JOIN t_player ON t_player.id=t_position.playerid "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid "
					+ "WHERE t_sports2department.sportsid=? AND t_player.playertype=0 AND t_player.playersex=1 "
					+ "GROUP BY t_department.departname "
					+ "ORDER BY summarks DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			int sumRank = 1;
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MarkPojo qm = new MarkPojo();
				qm.setRank(sumRank++);
				qm.setDepartId(rs.getInt(1));
				qm.setSport2departId(rs.getInt(2));
				qm.setDepartName(rs.getString(3));
				qm.setMaleTeachMark(rs.getInt(4));
				maleTeacherMarkList.add(qm);
				System.out.println("getmaleStudetsMarks========"
						+ qm.getMaleStuMark());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return maleTeacherMarkList;
	}

	/**
	 * 获取教工女子组总积分
	 * 
	 * @param depNameList
	 * @return
	 */

	public ArrayList getFemaleTeacherMark() {
		ArrayList femaleTeacherMarkList = new ArrayList();
		try {
			conn = dbc.getConn();

			String sql = "SELECT t_department.id,t_sports2department.id,t_department.departname,SUM(marks) AS summarks "
					+ "FROM t_position "
					+ "JOIN t_player ON t_player.id=t_position.playerid "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid "
					+ "WHERE t_sports2department.sportsid=? AND t_player.playertype=0 AND t_player.playersex=0 "
					+ "GROUP BY t_department.departname "
					+ "ORDER BY summarks DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			int sumRank = 1;

			rs = pstmt.executeQuery();

			while (rs.next()) {

				MarkPojo qm = new MarkPojo();
				qm.setRank(sumRank++);
				qm.setDepartId(rs.getInt(1));
				qm.setSport2departId(rs.getInt(2));
				qm.setDepartName(rs.getString(3));
				qm.setFemaleTeachMark(rs.getInt(4));
				femaleTeacherMarkList.add(qm);
				System.out.println("getmaleStudetsMarks========"
						+ qm.getFemaleStuMark());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return femaleTeacherMarkList;
	}

	/**
	 * 计算教工组总积分
	 * 
	 * @param sportsid
	 * @return
	 */

	public ArrayList getAllTeasMarks() {

		ArrayList allTeasMarks = new ArrayList();
		try {
			conn = dbc.getConn();
			String sql = "SELECT t_department.id,t_sports2department.id,t_department.departname,SUM(marks) AS summarks "
					+ "FROM t_position "
					+ "JOIN t_player ON t_player.id=t_position.playerid "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid "
					+ "WHERE t_sports2department.sportsid=? AND t_player.playertype=0 "
					+ "GROUP BY t_department.departname "
					+ "ORDER BY summarks DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			int allRank = 1;
			while (rs.next()) {
				MarkPojo mp = new MarkPojo();
				mp.setRank(allRank++);
				mp.setDepartId(rs.getInt(1));
				mp.setSport2departId(rs.getInt(2));
				mp.setDepartName(rs.getString(3));
				mp.setAllTeasMarks(rs.getInt(4));
				allTeasMarks.add(mp);

			}

			dbc.freeConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return allTeasMarks;
	}

	/*
	 * public ArrayList selectTeachMark(){ ArrayList teachMarkResult = new
	 * ArrayList(); try{
	 * 
	 * String sql = "select "
	 * 
	 * }catch(Exception e){ e.printStackTrace(); } dbc.freeConnection(conn);
	 * return teachMarkResult; }
	 */
	/**
	 * 向mark表中更新教工数据
	 * 
	 * @param sportsid
	 * @return
	 */
	public int updateAllTeasMarks() {
		ArrayList allTeasMarks = this.getAllTeasMarks();
		int flag = 0;
		try {
			conn = dbc.getConn();
			String sql = null;
			for (int i = 0; i < allTeasMarks.size(); i++) {
				MarkPojo mp = (MarkPojo) allTeasMarks.get(i);
				sql = "UPDATE t_mark SET teasum=? WHERE sp2dpid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mp.getAllTeasMarks());
				pstmt.setInt(2, mp.getSport2departId());
				flag = pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return flag;
	}

	/**
	 * 将所有的学生组的积分和名次拼成字符串
	 * 
	 * @return
	 */

	public ArrayList allMarkInfo() {
		ArrayList maleStuMark = this.getMaleStudentsMark();
		ArrayList femaleStuMark = this.getFemaleStudentsMark();
		ArrayList allStuMark = this.getAllStusMarks();
		ArrayList allMarkInfo = new ArrayList();
		try {
			conn = dbc.getConn();

			for (int i = 0; i < allStuMark.size(); i++) {
				String[] markInfo = new String[7];
				MarkPojo mp = (MarkPojo) allStuMark.get(i);
				markInfo[0] = mp.getDepartName();
				for (int j = 0; j < maleStuMark.size(); j++) {
					MarkPojo mp1 = (MarkPojo) maleStuMark.get(j);
					if (mp.getDepartId() == mp1.getDepartId()) {
						String MaleStuMark = mp1.getMaleStuMark() + "";
						String MaleRank = mp1.getRank() + "";
						markInfo[1] = MaleStuMark;
						markInfo[2] = MaleRank;
					}
				}

				for (int m = 0; m < femaleStuMark.size(); m++) {
					MarkPojo mp2 = (MarkPojo) femaleStuMark.get(m);
					if (mp.getDepartId() == mp2.getDepartId()) {
						String femaleStuMarks = mp2.getFemaleStuMark() + "";
						String femaleRank = mp2.getRank() + "";
						markInfo[3] = femaleStuMarks;
						markInfo[4] = femaleRank;
					}
				}
				String allStuMarks = mp.getAllStuMarks() + "";
				String allRank = mp.getRank() + "";
				markInfo[5] = allStuMarks;
				markInfo[6] = allRank;
				allMarkInfo.add(markInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return allMarkInfo;
	}

	/**
	 * 将所有的教工的积分和名次拼成字符串
	 * 
	 * @return
	 */

	public ArrayList allTeachMarkInfo() {
		ArrayList maleTeachMark = this.getMaleTeachersMark();
		ArrayList femaleTeachMark = this.getFemaleTeacherMark();
		ArrayList allTeachMark = this.getAllTeasMarks();
		ArrayList allMarkInfo = new ArrayList();
		try {
			conn = dbc.getConn();

			for (int i = 0; i < allTeachMark.size(); i++) {
				String[] markInfo = new String[7];
				MarkPojo mp = (MarkPojo) allTeachMark.get(i);
				markInfo[0] = mp.getDepartName();
				for (int j = 0; j < maleTeachMark.size(); j++) {
					MarkPojo mp1 = (MarkPojo) maleTeachMark.get(j);
					if (mp.getDepartId() == mp1.getDepartId()) {
						String MaleTeachMark = mp1.getMaleTeachMark() + "";
						String MaleRank = mp1.getRank() + "";
						markInfo[1] = MaleTeachMark;
						markInfo[2] = MaleRank;
					}
				}

				for (int m = 0; m < femaleTeachMark.size(); m++) {
					MarkPojo mp2 = (MarkPojo) femaleTeachMark.get(m);
					if (mp.getDepartId() == mp2.getDepartId()) {
						String femaleTeachMarks = mp2.getFemaleTeachMark() + "";
						String femaleRank = mp2.getRank() + "";
						markInfo[3] = femaleTeachMarks;
						markInfo[4] = femaleRank;
					}
				}
				String allTeachMarks = mp.getAllTeasMarks() + "";
				String allRank = mp.getRank() + "";
				markInfo[5] = allTeachMarks;
				markInfo[6] = allRank;
				allMarkInfo.add(markInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbc.freeConnection(conn);
		return allMarkInfo;
	}

	/**
	 * 根据t_mark表中纪录排序部门，并取出所有部门
	 * 
	 * @return depName
	 */
	public ArrayList<MarkPojo> getDep() {

		ArrayList<MarkPojo> depList = new ArrayList<MarkPojo>();
		try {
			conn = dbc.getConn();
			String sql = "SELECT DISTINCT departname FROM t_department "
					+ "JOIN t_sports2department ON t_sports2department.departid=t_department.id "
					+ "JOIN t_mark ON t_mark.sp2dpid=t_sports2department.id "
					+ "ORDER BY t_mark.stusum";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("QueryMark:rs.getRow()=====" + rs.getRow());
			while (rs.next()) {
				MarkPojo mp = new MarkPojo();
				mp.setDepartName(rs.getString(1));
				depList.add(mp);

			}
			System.out.println("dep=========" + depList.size());
			conn.close();
		} catch (Exception e) {
			log.debug(e);
		}
		return depList;
	}

	/**
	 * 获取本届运动会项目
	 * 
	 * @return itemList
	 */
	public ArrayList<MarkPojo> getItem() {

		ArrayList<MarkPojo> itemList = new ArrayList<MarkPojo>();
		try {
			conn = dbc.getConn();
			String sql = "SELECT id,itemname FROM t_item";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("QueryMark:rs.getRow()=====" + rs.getRow());
			while (rs.next()) {
				MarkPojo mp = new MarkPojo();
				mp.setItemName(rs.getString("itemname"));
				mp.setItemId(rs.getInt("id"));
				itemList.add(mp);

			}
			System.out.println("dep=========" + itemList.size());
			conn.close();
		} catch (Exception e) {
			log.debug(e);
		}
		return itemList;
	}

	/**
	 * 根据部门获取学生个人姓名|成绩|积分 MarkPojo中封装了学生信息的字段
	 * 
	 * @return studentsMarksList
	 */
	public ArrayList<MarkPojo> getStudentsMarks(String depName) {
		ArrayList<MarkPojo> studentsMarksList = new ArrayList<MarkPojo>();
		try {
			conn = dbc.getConn();
			String sql = "SELECT t_player.playername,t_position.score,t_position.marks,t_item.id,t_position.position FROM t_position  "
					+ "JOIN t_player ON t_player.id=t_position.playerid  "
					+ "JOIN t_sports2department ON t_sports2department.id=t_player.sp2dpid  "
					+ "JOIN t_department ON t_department.id=t_sports2department.departid  "
					+ "JOIN t_finalitem ON t_finalitem.id=t_position.finalitemid "
					+ " JOIN t_group2item ON t_group2item.id=t_finalitem.gp2itid  "
					+ "JOIN t_item ON t_item.id=t_group2item.itemid  "
					+ "WHERE t_department.departname=? AND t_player.playertype=1 AND t_position.marks IS NOT NULL AND t_position.marks!=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, depName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MarkPojo mp = new MarkPojo();
				mp.setStuInf(rs.getString("playername") + "|"
						+ rs.getString("score") + "|" + rs.getInt("marks")); // 学生信息：姓名|分数|积分
				// mp.setStuName(rs.getString("playername"));
				// mp.setStuScore(rs.getString("score"));
				// mp.setStuMark(rs.getInt("marks"));
				// mp.setItemName(rs.getString("itemname"));
				mp.setItemId(rs.getInt("id"));
				mp.setPosition(rs.getInt("position"));
				studentsMarksList.add(mp);

				System.out
						.println("QueryMark:根据部门查询出的学生信息playername|score|marks:"
								+ rs.getString("playername")
								+ "|"
								+ rs.getString("score")
								+ "|"
								+ rs.getInt("marks"));
			}
			conn.close();
		} catch (Exception e) {
			log.debug(e);
		}
		return studentsMarksList;
	}

	/**
	 * 获取当前运动会名称
	 * 
	 * @return
	 */
	public String getSportsName() {
		String sportsName = null;
		System.out.println("QueryMark:sportsid=" + sportsid);
		try {
			conn = dbc.getConn();
			String sql = "SELECT sportsname FROM t_sports WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sportsid);
			rs = pstmt.executeQuery();
			//System.out.println("QueryMark:s.getRow()=====" + rs.getRow());
			if (rs.next()) {
				sportsName = rs.getString("sportsname");
				//System.out
						//.println("QueryMark:sportsName========================");
				//System.out.println("QueryMark:sportsName" + rs.getString(1));
			}
			// conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sportsName;
	}

	/**
	 * 打印总积分表
	 * 
	 * @param file
	 *            --------->文件保存路径
	 * @param depNameList
	 *            --------->参赛部门
	 * @param itemList
	 *            --------->比赛项目
	 * @throws DocumentException
	 * @throws IOException
	 */
	/*
	 * public void printAllPlayerMarksMessage(String file,ArrayList<MarkPojo>
	 * depNameList,ArrayList<MarkPojo> itemList)throws DocumentException,
	 * IOException{ String sportsname = this.getSportsName();
	 * 
	 * try{ //生成积分单 Document document = new Document(PageSize.B4);
	 * //建立一个书写器，与document对象关联 RtfWriter2.getInstance(document, new
	 * FileOutputStream(file + "河北工院" + sportsname + "成绩单.doc"));
	 * document.open(); //设置中文字体 BaseFont bfChinese =
	 * BaseFont.createFont("STSongStd-Light"
	 * ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); //标题字体风格 Font titleFont = new
	 * Font(bfChinese,12,Font.BOLD); //正文字体风格 Font contextFont = new
	 * Font(bfChinese,10,Font.NORMAL); Paragraph title = new
	 * Paragraph(sportsname+"学生成绩单"); //设置标题格式对齐方式
	 * title.setAlignment(Element.ALIGN_CENTER); title.setFont(titleFont);
	 * document.add(title);
	 * 
	 * //设置Table表格,创建一个5列的表格 Table table = new Table(itemList.size()+2); //int
	 * width[] = {20,15,15,15,20,15};//设置每列宽度比例 //table.setWidths(width);
	 * table.setWidth(100);//占页面宽度比例
	 * table.setAlignment(Element.ALIGN_CENTER);//居中
	 * table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中
	 * table.setAutoFillEmptyCells(true);//自动填满 table.setBorderWidth(1);//边框宽度
	 * 
	 * System.out.println("MarkPojo:depNameList.size()"+depNameList.size());
	 * for(MarkPojo dmp:depNameList) { int posi = 1;
	 * System.out.println("MarkPojo:dmp.getDepartName"+dmp.getDepartName());
	 * ArrayList<MarkPojo> studentsMarksList =
	 * this.getStudentsMarks(dmp.getDepartName());
	 * 
	 * 
	 * 
	 * System.out.println("QueryMark:studentsMarkslist:"+studentsMarksList.size()
	 * ); Cell cell1 = new Cell(dmp.getDepartName());
	 * cell1.setVerticalAlignment(Element.ALIGN_CENTER);
	 * cell1.setVerticalAlignment(Element.ALIGN_MIDDLE); cell1.setRowspan(9);
	 * table.addCell(cell1);
	 * 
	 * table.addCell(new Cell("项目")); for(MarkPojo imp:itemList){
	 * table.addCell(new Cell(imp.getItemName())); } //table.addCell(new
	 * Cell("合计"));
	 * 
	 * if(studentsMarksList.size()>0){ for(int row = 1;row<=8;row++){
	 * table.addCell(new Cell(row+"")); for(MarkPojo imp:itemList){ //MarkPojo
	 * imp = (MarkPojo)itemList.get(item); Cell cell = new Cell("");
	 * for(MarkPojo smp:studentsMarksList){ //循环判断运动员是否在此项目中
	 * 
	 * if(smp.getItemId()==imp.getItemId() && smp.getPosition()==row){
	 * //cell.add(smp.getStuInf()); cell = new Cell(smp.getStuInf()); }
	 * }table.addCell(cell);}} }else{ for(int row = 1;row<=8;row++){
	 * table.addCell(new Cell(row+"")); for(int item =
	 * 1;item<=itemList.size();item++){
	 * 
	 * //table.addCell(new Cell((posi++)+"")); table.addCell(new Cell("")); }} }
	 * Cell cell2 = new Cell("");
	 * cell2.setVerticalAlignment(Element.ALIGN_CENTER);
	 * cell2.setVerticalAlignment(Element.ALIGN_MIDDLE); cell2.setRowspan(8);
	 * table.addCell(cell2);
	 * 
	 * }
	 * 
	 * document.add(table); document.close();
	 * 
	 * }catch (Exception e) { log.debug(e); } }
	 */
//以下是韩鑫鹏生成学生积分表方法
	/**
	 * 打印总积分表
	 * 
	 * @param file
	 *            --------->文件保存路径
	 * @param depNameList
	 *            --------->参赛部门;参赛部门id
	 * @param boyItemList
	 *            --------->比赛项目;itemid
	 * @param sportsname
	 *            运动会名称
	 * @param boyGroup
	 *            组别名称
	 * @param boyMarkMap
	 *            (key:itemid;rank;depid value:name;score;mark)
	 */
	public void generateExcel2003(String file, ArrayList depNameList,
			ArrayList boyItemList, String sportsname, String boyGroup, HashMap boyMarkMap,
			ArrayList girlItemList, String girlGroup, HashMap girlMarkMap) {
		// 先创建工作簿对象
		HSSFWorkbook workbook2003 = new HSSFWorkbook();
		// 创建工作表对象并命名
		HSSFSheet boySheet = workbook2003.createSheet(boyGroup + "分数统计");
		// 创建表头
		HSSFRow topRow = boySheet.createRow(0);
		for (int i = 0; i < ((boyItemList.size()) * 3 + 5); i++) {
			if (i == 0) {
				HSSFCell denameCell = topRow.createCell(i);
				denameCell.setCellValue("河北工院" + sportsname + "" + boyGroup
						+ "成绩单");
			} else {
				HSSFCell denameCell = topRow.createCell(i);
			}
		}
		boySheet.addMergedRegion(new CellRangeAddress(0, 0, 0, ((boyItemList.size()) * 3 + 5)));
		int rowCount = 1;
		for (int deNum = 0; deNum < depNameList.size(); deNum++) {
			int narkSum = 0;
			String depName = depNameList.get(deNum).toString().split(";")[0];
			log.debug("系别："+depNameList.get(deNum).toString().split(";")[0]);
			HSSFRow row1 = boySheet.createRow(rowCount);
			rowCount++;
			HSSFCell denameCell = row1.createCell(0);
			denameCell.setCellValue(depName);
			HSSFCell itnameCell = row1.createCell(1);
			itnameCell.setCellValue("项目");
			HSSFCell nullCell = row1.createCell(2);
			// 创建项目表格
			int itemCount = 3;
			log.debug(boyItemList);
			for (int cellNum = 0; cellNum < boyItemList.size(); cellNum++) {
				
				HSSFCell eitemCell = row1.createCell(itemCount);
				String[] tempItem = boyItemList.get(cellNum).toString().split(";");
				eitemCell.setCellValue(tempItem[0]);
				//log.debug(tempItem[0]);
				itemCount++;
				//log.debug("itemCount"+itemCount);
				HSSFCell nullCell2 = row1.createCell(itemCount);
				itemCount++;
				//log.debug("itemCount"+itemCount);
				HSSFCell nullCell3 = row1.createCell(itemCount);
				itemCount++;
				//log.debug("itemCount"+itemCount);
			}
			HSSFCell hejiCell = row1.createCell(itemCount);
			hejiCell.setCellValue("合计");
			itemCount++;
			HSSFCell sumCell = row1.createCell(itemCount);
			sumCell.setCellValue("总分");
			// 创建学生姓名、成绩、积分等单元格
			
			for (int rowNum = 1; rowNum <= 8; rowNum++) {
				int markTotal = 0;
				HSSFRow row = boySheet.createRow(rowCount);
				HSSFCell nullCell1 = row.createCell(0);
				rowCount++;
				HSSFCell mingciCell1 = row.createCell(1);
				if (rowNum == 1) {
					mingciCell1.setCellValue("名次");
				}
				HSSFCell dijiCell1 = row.createCell(2);
				dijiCell1.setCellValue(rowNum);
				int itCount = 3;
				for (int cellNum = 0; cellNum < boyItemList.size(); cellNum++) {
					String[] tempItem = boyItemList.get(cellNum).toString().trim().split(";");
					String key = tempItem[1];
					key = key + ";" + rowNum;
					String[] tempDep = depNameList.get(deNum).toString().trim()
							.split(";");
					key = key + "" + tempDep[1];
					if (boyMarkMap.containsKey(key)) {
						String[] value = boyMarkMap.get(key).toString().split(";");
						HSSFCell eitemCell = row.createCell(itCount);
						eitemCell.setCellValue(value[0]);
						itCount++;
						log.debug("itCount**************************************");
						log.debug("itCount"+itCount);
						HSSFCell nullCell2 = row.createCell(itCount);
						nullCell2.setCellValue(value[1]);
						itCount++;
						log.debug("itCount"+itCount);
						HSSFCell nullCell3 = row.createCell(itCount);
						int mark = Integer.parseInt(value[2].trim());
						nullCell3.setCellValue(mark);
						markTotal += mark;
						itCount++;
						log.debug("itCount"+itCount);
					} else {
						HSSFCell eitemCell = row.createCell(itCount);
						itCount++;
						log.debug("itCount**************************************");
						log.debug("itCount"+itCount);
						HSSFCell nullCell2 = row.createCell(itCount);
						itCount++;
						log.debug("itCount"+itCount);
						HSSFCell nullCell3 = row.createCell(itCount);
						itCount++;
						log.debug("itCount"+itCount);
					}
					narkSum += markTotal;	
				}	
				HSSFCell hejiCell2 = row.createCell(itCount);
				hejiCell2.setCellValue(markTotal);
				itCount++;
				HSSFCell zongfenCell2 = row.createCell(itCount);
				if (rowNum == 8){
					zongfenCell2.setCellValue(narkSum);
				}
			}
		}
		
		//合并男子表格的单元格
		for (int depNum = 0; depNum < depNameList.size(); depNum++){
			boySheet.addMergedRegion(new CellRangeAddress((1+(depNum*9)), (9+(depNum*9)), 0, 0)); //合并系
			boySheet.addMergedRegion(new CellRangeAddress((1+(depNum*9)), (1+(depNum*9)), 1, 2)); //合并“项目”
			boySheet.addMergedRegion(new CellRangeAddress((2+(depNum*9)), (9+(depNum*9)), 1, 1)); //合并“名次”
			for (int itemNum = 0; itemNum < boyItemList.size(); itemNum++){
				boySheet.addMergedRegion(new CellRangeAddress((1+(depNum*9)), (1+(depNum*9)), (3+itemNum*3), (5+itemNum*3)));
			}//合并项目
			boySheet.addMergedRegion(new CellRangeAddress((2+(depNum*9)), (9+(depNum*9)), (4+(boyItemList.size())*3), (4+(boyItemList.size())*3)));//合并总分
		}
		HSSFSheet girlSheet = workbook2003.createSheet(girlGroup + "分数统计");
		// 创建表头
		HSSFRow girlTopRow = girlSheet.createRow(0);
		for (int i = 0; i < ((girlItemList.size()) * 3 + 5); i++) {
			if (i == 0) {
				HSSFCell denameCell = girlTopRow.createCell(i);
				denameCell.setCellValue("河北工院" + sportsname + "" + girlGroup
						+ "成绩单");
			} else {
				HSSFCell denameCell = girlTopRow.createCell(i);
			}
		}
		girlSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, ((girlItemList.size()) * 3 + 5)));
		int girlRowCount = 1;
		for (int deNum = 0; deNum < depNameList.size(); deNum++) {
			int narkSum = 0;
			String depName = depNameList.get(deNum).toString().split(";")[0];
			HSSFRow row1 = girlSheet.createRow(girlRowCount);
			girlRowCount++;
			HSSFCell denameCell = row1.createCell(0);
			denameCell.setCellValue(depName);
			HSSFCell itnameCell = row1.createCell(1);
			itnameCell.setCellValue("项目");
			HSSFCell nullCell = row1.createCell(2);
			// 创建项目表格
			int itemCount = 3;
			for (int cellNum = 0; cellNum < girlItemList.size(); cellNum++) {
				HSSFCell eitemCell = row1.createCell(itemCount);
				eitemCell.setCellValue(girlItemList.get(cellNum).toString().split(";")[0]);
				itemCount++;
				HSSFCell nullCell2 = row1.createCell(itemCount);
				itemCount++;
				HSSFCell nullCell3 = row1.createCell(itemCount);
				itemCount++;
			}
			HSSFCell hejiCell = row1.createCell(itemCount);
			hejiCell.setCellValue("合计");
			itemCount++;
			HSSFCell sumCell = row1.createCell(itemCount);
			sumCell.setCellValue("总分");
			// 创建学生姓名、成绩、积分等单元格
			for (int rowNum = 1; rowNum <= 8; rowNum++) {

				int markTotal = 0;
				HSSFRow row = girlSheet.createRow(girlRowCount);
				girlRowCount++;
				HSSFCell nullCell1 = row.createCell(0);
				HSSFCell mingciCell1 = row.createCell(1);
				if (rowNum == 1) {
					mingciCell1.setCellValue("名次");
				}
				HSSFCell dijiCell1 = row.createCell(2);
				dijiCell1.setCellValue(rowNum);
				int itCount = 3;
				for (int cellNum = 0; cellNum < girlItemList.size(); cellNum++) {
					String[] tempItem = girlItemList.get(cellNum).toString().trim()
							.split(";");
					String key = tempItem[1];
					key = key + ";" + rowNum;
					String[] tempDep = depNameList.get(deNum).toString().trim()
							.split(";");
					key = key + "" + tempDep[1];
					if (boyMarkMap.containsKey(key)) {
						String[] value = girlMarkMap.get(key).toString().split(";");
						HSSFCell eitemCell = row.createCell(itCount);
						eitemCell.setCellValue(value[0]);
						itCount++;
						HSSFCell nullCell2 = row.createCell(itCount);
						nullCell2.setCellValue(value[1]);
						itCount++;
						HSSFCell nullCell3 = row.createCell(itCount);
						int mark = Integer.parseInt(value[2].trim());
						nullCell3.setCellValue(mark);
						markTotal += mark;
						itCount++;
					} else {
						HSSFCell eitemCell = row.createCell(itCount);
						itCount++;
						HSSFCell nullCell2 = row.createCell(itCount);
						itCount++;
						HSSFCell nullCell3 = row.createCell(itCount);
						itCount++;
					}
					narkSum += markTotal;
				}
				HSSFCell hejiCell2 = row.createCell(itCount);
				hejiCell2.setCellValue(markTotal);
				itCount++;
				
				if (rowNum == 8){
					HSSFCell zongfenCell2 = row.createCell(itCount);
					zongfenCell2.setCellValue(narkSum);
				}else{
					HSSFCell zongfenCell2 = row.createCell(itCount);
				}
			}
		}
		//合并女子表格的单元格
		for (int depNum = 0; depNum < depNameList.size(); depNum++){
			girlSheet.addMergedRegion(new CellRangeAddress((1+(depNum*9)), (9+(depNum*9)), 0, 0)); //合并系
			girlSheet.addMergedRegion(new CellRangeAddress((1+(depNum*9)), (1+(depNum*9)), 1, 2)); //合并“项目”
			girlSheet.addMergedRegion(new CellRangeAddress((2+(depNum*9)), (9+(depNum*9)), 1, 1)); //合并“名次”
			for (int itemNum = 0; itemNum < girlItemList.size(); itemNum++){
				girlSheet.addMergedRegion(new CellRangeAddress((1+(depNum*9)), (1+(depNum*9)), (3+itemNum*3), (5+itemNum*3)));
			}//合并项目
			girlSheet.addMergedRegion(new CellRangeAddress((2+(depNum*9)), (9+(depNum*9)), (4+(girlItemList.size())*3), (4+(girlItemList.size())*3)));//合并总分
		}
		// 生成文件
		File newfile = new File(file);
		FileOutputStream fos = null;
		log.debug(file);
		try {
			fos = new FileOutputStream(newfile);
			workbook2003.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 查询部门集合（部门名称；id）
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectDep(int sportsId){
		ArrayList depList = new ArrayList();
		String sql = "SELECT t_department.departshortname,t_department.id FROM t_department " +
				"JOIN t_sports2department ON t_department.id = t_sports2department.departid " +
				"WHERE t_sports2department.sportsid=? AND t_department.departtype=1";
		try {
	        Connection myconn = dbc.getConn();
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(1, sportsId);
	            rs = statement.executeQuery(); 
	            while(rs.next()){
	            	String temp = rs.getString(1)+";"+rs.getInt(2);
	            	depList.add(temp);
	            }
	            
	            rs.close();
	            statement.close();
	           }
	        dbc.freeConnection(myconn);  
	        }catch (SQLException e) {                 
	        e.printStackTrace(); } 
			return depList;
	}
	
	/**
	 * 查询项目集合（项目名称；id）
	 * @param sportsId
	 * @return
	 */
	public ArrayList selectItem(int sportsId,int sex){
		ArrayList itemList = new ArrayList();
		String sql = "SELECT t_item.itemname,t_item.id FROM t_item JOIN t_group2item " +
				"ON t_item.id = t_group2item.itemid JOIN t_group2sports ON t_group2sports.id = t_group2item.gp2spid " +
				"JOIN t_group ON t_group.id = t_group2sports.groupid WHERE t_group2sports.sportsid=? " +
				"AND t_group2item.matchtype <> 0 AND t_group.groupsex=? AND t_group.grouptype=1";
		try {
	        Connection myconn = dbc.getConn();
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(1, sportsId);
	            statement.setInt(2, sex);
	            rs = statement.executeQuery(); 
	            while(rs.next()){
	            	String temp = rs.getString(1)+";"+rs.getInt(2);
	            	itemList.add(temp);
	            }
	            
	            rs.close();
	            statement.close();
	           }
	        dbc.freeConnection(myconn);  
	        }catch (SQLException e) {                 
	        e.printStackTrace(); } 
			return itemList;
	}
	
	/**
	 * 查询积分（(key:itemid;rank;depid value:name;score;mark)）（径赛和田赛）
	 * @param sportsId
	 * @param sex
	 * @return HashMap
	 */
	public HashMap selectMarkMap(int sportsId,int sex){
		HashMap markMap = new HashMap();
		String sql = "SELECT t_group2item.itemid,t_position.position,t_sports2department.departid,t_player.playername,t_position.score,t_position.marks"
		+ " FROM t_position JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id"
		+ " JOIN t_player ON t_position.playerid = t_player.id"
		+ " JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.sportsid"
		+ " JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id"
		+ " JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id"
		+ " JOIN t_group ON t_group.id = t_group2sports.groupid"
		+ " JOIN t_item ON t_group2item.itemid = t_item.id"
		+ " WHERE t_group2sports.sportsid = ? AND t_position.position < 9"
		+ " AND t_group2item.matchtype <> 0 AND t_group.grouptype = 1"
		+ " AND t_group.groupsex = ? AND t_item.itemtype <>3";
		try {
	        Connection myconn = dbc.getConn();
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(1, sportsId);
	            statement.setInt(2, sex);
	            rs = statement.executeQuery(); 
	            while(rs.next()){
	            	String key = (rs.getInt(1)+";"+rs.getInt(2)+";"+rs.getInt(3)).trim();
	            	String value = (rs.getString(4)+";"+rs.getString(5)+";"+rs.getInt(6)).trim();
	            	markMap.put(key, value);
	            }
	            
	            rs.close();
	            statement.close();
	           }
	        dbc.freeConnection(myconn);  
	        }catch (SQLException e) {                 
	        e.printStackTrace(); } 
			return markMap;
	}
	/**
	 * 查询积分（(key:itemid;rank;depid value:name;score;mark)）（接力）
	 * @param sportsId
	 * @param sex
	 * @param markMap田赛径赛
	 * @return HashMap
	 */
	public HashMap selectMarkMap(int sportsId,int sex,HashMap markMap ){
		String sql = "SELECT t_group2item.itemid,t_position.position,t_department.id,t_department.departshortname,t_position.score,t_position.marks"
		+" FROM t_position JOIN t_finalitem ON t_position.finalitemid = t_finalitem.id"
		+" JOIN t_department ON t_position.playerid = t_department.id"
		+" JOIN t_group2item ON t_finalitem.gp2itid = t_group2item.id"
		+" JOIN t_group2sports ON t_group2item.gp2spid = t_group2sports.id"
		+" JOIN t_group ON t_group.id = t_group2sports.groupid"
		+" JOIN t_item ON t_group2item.itemid = t_item.id"
		+" WHERE t_group2sports.sportsid = ? AND t_position.position < 9"
		+" AND t_group2item.matchtype <> 0 AND t_group.grouptype = 1" 
		+" AND t_group.groupsex = ? AND t_item.itemtype = 3";
		try {
	        Connection myconn = dbc.getConn();
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(1, sportsId);
	            statement.setInt(2, sex);
	            rs = statement.executeQuery(); 
	            while(rs.next()){
	            	String key = (rs.getInt(1)+";"+rs.getInt(2)+";"+rs.getInt(3)).trim();
	            	String value = (rs.getString(4)+";"+rs.getString(5)+";"+rs.getInt(6)).trim();
	            	markMap.put(key, value);
	            }
	            
	            rs.close();
	            statement.close();
	           }
	        dbc.freeConnection(myconn);  
	        }catch (SQLException e) {                 
	        e.printStackTrace(); } 
			return markMap;
	}
}
