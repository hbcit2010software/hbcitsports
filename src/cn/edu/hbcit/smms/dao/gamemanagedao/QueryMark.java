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
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.MarkPojo;
import cn.edu.hbcit.smms.util.UtilTools;

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
	 * 查询 mark表中是否存在当前运动会的记录
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
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dbc.freeConnection(conn);
		return flag;
	}
	
	/**
	 * 若t_marks表中已存在当前运动会的积分信息,如有修改则执行更新操作
	 * @return
	 */
	
	public int updateMarks(){
		ArrayList allStuMarks = this.getAllStusMarks();
		int flag = 0;
		try{
			conn = dbc.getConn();
			for(int i = 0; i < allStuMarks.size(); i++){
				MarkPojo mp = (MarkPojo) allStuMarks.get(i);
				String sql = "update t_mark set stusum=? where sp2dpid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,mp.getAllStuMarks());
				pstmt.setInt(2, mp.getSport2departId());
				flag = pstmt.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
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
		log.debug("男子组积分map：" +boyMarkMap);
		log.debug("女子组积分map：" +boyMarkMap);
		ArrayList boySumMark = new ArrayList();
		ArrayList girlSumMark = new ArrayList();
		// 先创建工作簿对象
		HSSFWorkbook workbook2003 = new HSSFWorkbook();
		// 创建工作表对象并命名
		HSSFSheet boySheet = workbook2003.createSheet(boyGroup + "分数统计");
		//设置单元格格式
		HSSFCellStyle boyCellStyle = workbook2003.createCellStyle();
		boyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		boyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		boyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		boyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		boyCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		// 创建表头
		HSSFRow topRow = boySheet.createRow(0);
		for (int i = 0; i < ((boyItemList.size()) * 3 + 5); i++) {
			if (i == 0) {
				HSSFCell denameCell = topRow.createCell(i);
				denameCell.setCellValue("河北工院" + sportsname + "" + boyGroup
						+ "成绩单");
				denameCell.setCellStyle(boyCellStyle);
			} else {
				HSSFCell denameCell = topRow.createCell(i);
				denameCell.setCellStyle(boyCellStyle);
				
			}
		}
		boySheet.addMergedRegion(new CellRangeAddress(0, 0, 0, ((boyItemList.size()) * 3 + 5)));
		int rowCount = 1;
		for (int deNum = 0; deNum < depNameList.size(); deNum++) {
			int narkSum = 0;
			String depName = depNameList.get(deNum).toString().split(";")[0];
			//log.debug("系别："+depNameList.get(deNum).toString().split(";")[0]);
			HSSFRow row1 = boySheet.createRow(rowCount);
			rowCount++;
			HSSFCell denameCell = row1.createCell(0);
			denameCell.setCellStyle(boyCellStyle);
			denameCell.setCellValue(depName);
			HSSFCell itnameCell = row1.createCell(1);
			itnameCell.setCellStyle(boyCellStyle);
			itnameCell.setCellValue("项目");
			HSSFCell nullCell = row1.createCell(2);
			nullCell.setCellStyle(boyCellStyle);
			// 创建项目表格
			int itemCount = 3;
			//log.debug(boyItemList);
			for (int cellNum = 0; cellNum < boyItemList.size(); cellNum++) {
				
				HSSFCell eitemCell = row1.createCell(itemCount);
				eitemCell.setCellStyle(boyCellStyle);
				String[] tempItem = boyItemList.get(cellNum).toString().split(";");
				eitemCell.setCellValue(tempItem[0]);
				//log.debug(tempItem[0]);
				itemCount++;
				//log.debug("itemCount"+itemCount);
				HSSFCell nullCell2 = row1.createCell(itemCount);
				nullCell2.setCellStyle(boyCellStyle);
				itemCount++;
				//log.debug("itemCount"+itemCount);
				HSSFCell nullCell3 = row1.createCell(itemCount);
				nullCell3.setCellStyle(boyCellStyle);
				itemCount++;
				//log.debug("itemCount"+itemCount);
			}
			HSSFCell hejiCell = row1.createCell(itemCount);
			hejiCell.setCellStyle(boyCellStyle);
			hejiCell.setCellValue("合计");
			itemCount++;
			HSSFCell sumCell = row1.createCell(itemCount);
			sumCell.setCellStyle(boyCellStyle);
			sumCell.setCellValue("总分");
			// 创建学生姓名、成绩、积分等单元格
			
			for (int rowNum = 1; rowNum <= 8; rowNum++) {
				int markTotal = 0;
				HSSFRow row = boySheet.createRow(rowCount);
				HSSFCell nullCell1 = row.createCell(0);
				nullCell1.setCellStyle(boyCellStyle);
				rowCount++;
				HSSFCell mingciCell1 = row.createCell(1);
				mingciCell1.setCellStyle(boyCellStyle);
				if (rowNum == 1) {
					mingciCell1.setCellValue("名次");
				}
				HSSFCell dijiCell1 = row.createCell(2);
				dijiCell1.setCellStyle(boyCellStyle);
				dijiCell1.setCellValue(rowNum);
				int itCount = 3;
				for (int cellNum = 0; cellNum < boyItemList.size(); cellNum++) {
					String[] tempItem = boyItemList.get(cellNum).toString().trim().split(";");
					String key = tempItem[1];
					key = key + ";" + rowNum;
					String[] tempDep = depNameList.get(deNum).toString().trim()
							.split(";");
					key = key + ";" + tempDep[1];
					//log.debug("男key：" +key);
					//log.debug("真假：" +boyMarkMap.containsKey(key));
					UtilTools ut = new UtilTools();
					if (boyMarkMap.containsKey(key)) {
						log.debug("男key：" +key);
						String[] value = boyMarkMap.get(key).toString().split(";");
						HSSFCell eitemCell = row.createCell(itCount);
						eitemCell.setCellStyle(boyCellStyle);
						eitemCell.setCellValue(value[0]);
						itCount++;
						//log.debug("itCount**************************************");
						//log.debug("itCount"+itCount);
						HSSFCell nullCell2 = row.createCell(itCount);
						nullCell2.setCellStyle(boyCellStyle);
						nullCell2.setCellValue(ut.coverToTrackScore(value[1], tempItem[2]));
						//nullCell2.setCellValue(value[1]);
						itCount++;
						//log.debug("itCount"+itCount);
						HSSFCell nullCell3 = row.createCell(itCount);
						nullCell3.setCellStyle(boyCellStyle);
						int mark = Integer.parseInt(value[2].trim());
						nullCell3.setCellValue(mark);
						markTotal += mark;
						narkSum += mark;
						itCount++;
						//log.debug("itCount"+itCount);
					} else {
						HSSFCell eitemCell = row.createCell(itCount);
						eitemCell.setCellStyle(boyCellStyle);
						itCount++;
						
						//log.debug("itCount**************************************");
						//log.debug("itCount"+itCount);
						HSSFCell nullCell2 = row.createCell(itCount);
						nullCell2.setCellStyle(boyCellStyle);
						itCount++;
						//log.debug("itCount"+itCount);
						HSSFCell nullCell3 = row.createCell(itCount);
						nullCell3.setCellStyle(boyCellStyle);
						itCount++;
						//log.debug("itCount"+itCount);
					}
					//narkSum += markTotal;	
				}	
				HSSFCell hejiCell2 = row.createCell(itCount);
				hejiCell2.setCellValue(markTotal);
				hejiCell2.setCellStyle(boyCellStyle);
				itCount++;
				
				if (rowNum == 8){
					HSSFCell zongfenCell2 = row.createCell(itCount);
					//log.debug("rowNum:总分："+rowNum);
					//zongfenCell2.setCellValue(narkSum);
					boySumMark.add(narkSum+"");
					zongfenCell2.setCellStyle(boyCellStyle);
				}else{
					HSSFCell zongfenCell2 = row.createCell(itCount);
					zongfenCell2.setCellStyle(boyCellStyle);
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
			HSSFRow markSumRow = boySheet.getRow((2+(depNum*9)));
			HSSFCell markSumCell = markSumRow.getCell((4+(boyItemList.size())*3));
			log.debug("男子总分单元格:"+markSumCell);
			markSumCell.setCellValue(boySumMark.get(depNum).toString());
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
				denameCell.setCellStyle(boyCellStyle);
			} else {
				HSSFCell denameCell = girlTopRow.createCell(i);
				denameCell.setCellStyle(boyCellStyle);
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
			denameCell.setCellStyle(boyCellStyle);
			HSSFCell itnameCell = row1.createCell(1);
			itnameCell.setCellValue("项目");
			itnameCell.setCellStyle(boyCellStyle);
			HSSFCell nullCell = row1.createCell(2);
			nullCell.setCellStyle(boyCellStyle);
			// 创建项目表格
			int itemCount = 3;
			for (int cellNum = 0; cellNum < girlItemList.size(); cellNum++) {
				HSSFCell eitemCell = row1.createCell(itemCount);
				eitemCell.setCellValue(girlItemList.get(cellNum).toString().split(";")[0]);
				eitemCell.setCellStyle(boyCellStyle);
				itemCount++;
				HSSFCell nullCell2 = row1.createCell(itemCount);
				nullCell2.setCellStyle(boyCellStyle);
				itemCount++;
				HSSFCell nullCell3 = row1.createCell(itemCount);
				nullCell3.setCellStyle(boyCellStyle);
				itemCount++;
			}
			HSSFCell hejiCell = row1.createCell(itemCount);
			hejiCell.setCellValue("合计");
			hejiCell.setCellStyle(boyCellStyle);
			itemCount++;
			HSSFCell sumCell = row1.createCell(itemCount);
			sumCell.setCellValue("总分");
			sumCell.setCellStyle(boyCellStyle);
			// 创建学生姓名、成绩、积分等单元格
			for (int rowNum = 1; rowNum <= 8; rowNum++) {

				int markTotal = 0;
				HSSFRow row = girlSheet.createRow(girlRowCount);
				girlRowCount++;
				HSSFCell nullCell1 = row.createCell(0);
				nullCell1.setCellStyle(boyCellStyle);
				HSSFCell mingciCell1 = row.createCell(1);
				mingciCell1.setCellStyle(boyCellStyle);
				if (rowNum == 1) {
					mingciCell1.setCellValue("名次");
				}
				HSSFCell dijiCell1 = row.createCell(2);
				dijiCell1.setCellValue(rowNum);
				dijiCell1.setCellStyle(boyCellStyle);
				int itCount = 3;
				for (int cellNum = 0; cellNum < girlItemList.size(); cellNum++) {
					String[] tempItem = girlItemList.get(cellNum).toString().trim()
							.split(";");
					String key = tempItem[1];
					key = key + ";" + rowNum;
					String[] tempDep = depNameList.get(deNum).toString().trim()
							.split(";");
					key = key + ";" + tempDep[1];
					//log.debug("女key：" +key);
					//log.debug("真假：" +boyMarkMap.containsKey(key));
					UtilTools ut = new UtilTools();
					if (girlMarkMap.containsKey(key)) {
						String[] value = girlMarkMap.get(key).toString().split(";");
						//log.debug("value[]:"+value);
						HSSFCell eitemCell = row.createCell(itCount);
						eitemCell.setCellValue(value[0]);
						eitemCell.setCellStyle(boyCellStyle);
						itCount++;
						HSSFCell nullCell2 = row.createCell(itCount);
						nullCell2.setCellValue(ut.coverToTrackScore(value[1], tempItem[2]));
						nullCell2.setCellStyle(boyCellStyle);
						itCount++;
						HSSFCell nullCell3 = row.createCell(itCount);
						int mark = Integer.parseInt(value[2].trim());
						nullCell3.setCellValue(mark);
						nullCell3.setCellStyle(boyCellStyle);
						markTotal += mark;
						narkSum += mark;
						itCount++;
					} else {
						HSSFCell eitemCell = row.createCell(itCount);
						eitemCell.setCellStyle(boyCellStyle);
						itCount++;
						HSSFCell nullCell2 = row.createCell(itCount);
						nullCell2.setCellStyle(boyCellStyle);
						itCount++;
						HSSFCell nullCell3 = row.createCell(itCount);
						nullCell3.setCellStyle(boyCellStyle);
						itCount++;
					}
					//narkSum += markTotal;
				}
				HSSFCell hejiCell2 = row.createCell(itCount);
				hejiCell2.setCellValue(markTotal);
				hejiCell2.setCellStyle(boyCellStyle);
				itCount++;
				
				if (rowNum == 8){
					HSSFCell zongfenCell2 = row.createCell(itCount);
					girlSumMark.add(narkSum+"");
					zongfenCell2.setCellStyle(boyCellStyle);
					//zongfenCell2.setCellValue("lllllllllllll");
					//zongfenCell2.setCellValue(narkSum);
				}else{
					HSSFCell zongfenCell2 = row.createCell(itCount);
					zongfenCell2.setCellStyle(boyCellStyle);
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
			HSSFRow markSumRow = girlSheet.getRow((2+(depNum*9)));
			HSSFCell markSumCell = markSumRow.getCell((4+(girlItemList.size())*3));
			log.debug("女子总分单元格:"+markSumCell);
			markSumCell.setCellValue(girlSumMark.get(depNum).toString());
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
		String sql = "SELECT t_item.itemname,t_item.id,t_item.itemtype FROM t_item JOIN t_group2item " +
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
	            	String temp = rs.getString(1)+";"+rs.getInt(2)+";"+rs.getString(3);
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
		+ " JOIN t_sports2department ON t_player.sp2dpid = t_sports2department.id"
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
		HashMap newmarkMap = markMap;
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
	        log.debug("myconn"+myconn);
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            log.debug("sql"+sql);
	            statement.setInt(1, sportsId);
	            statement.setInt(2, sex);
	            rs = statement.executeQuery(); 
	            while(rs.next()){
	            	String key = (rs.getInt(1)+";"+rs.getInt(2)+";"+rs.getInt(3)).trim();
	            	String value = (rs.getString(4)+";"+rs.getString(5)+";"+rs.getInt(6)).trim();
	            	log.debug("key："+key);
	            	log.debug("value："+value);
	            	newmarkMap.put(key, value);
	            }
	            
	            rs.close();
	            statement.close();
	        }
	        dbc.freeConnection(myconn);  
	    }catch (SQLException e) {                 
	        e.printStackTrace(); } 
		return newmarkMap;
	}
	
	/**
	 * 
	* 方法说明	查询记录
	* 方法补充说明
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public HashMap selectRecord( int sex){
		HashMap recordMap = new HashMap();
		String sql = "SELECT * FROM t_record WHERE sex=?";
		try {
	        Connection myconn = dbc.getConn();
	        log.debug("myconn"+myconn);
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(0, sex);
	            rs = statement.executeQuery(); 
	            while(rs.next()){
	            	String key = (rs.getString(3)+";"+rs.getInt(2)+";"+rs.getString(10)).trim();
	            	String value = rs.getString(4);
	            	recordMap.put(key, value);
	            }
	            rs.close();
	            statement.close();
	        }
	        dbc.freeConnection(myconn);  
	    }catch (SQLException e) {                 
	        e.printStackTrace(); } 
		return recordMap;
	}
	/**
	 * 
	* 方法说明	根据运动会id/性别 查询晋级数量
	* 方法补充说明
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public HashMap selectNum(int sex,int sportsid ){
		HashMap recordMap = new HashMap();
		String sql = "SELECT * FROM t_finalitem WHERE gp2itid IN (SELECT id FROM t_group2item WHERE gp2spid IN (SELECT id FROM t_group2sports WHERE groupid IN (SELECT id FROM t_group WHERE groupsex=?))) AND sportsid = ? ORDER BY promotionnum";
		try {
	        Connection myconn = dbc.getConn();
	        log.debug("myconn"+myconn);
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(1, sportsid);
	            statement.setInt(0, sex);
	            rs = statement.executeQuery(); 
	            int count = 0;
	            while(rs.next()){
	            	if (count == 0){
	            		recordMap.put("number1", rs.getInt(8)+"");
	            	}
	            	String key = rs.getInt(1)+"";
	            	String value = rs.getInt(8)+"";
	            	recordMap.put(key, value);
	            }
	            rs.close();
	            statement.close();
	        }
	        dbc.freeConnection(myconn);  
	    }catch (SQLException e) {                 
	        e.printStackTrace(); } 
		return recordMap;
	}
	
	/**
	 * 
	* 方法说明	根据运动会id/性别 查询比赛成绩
	* 方法补充说明
	* @param 参数名 参数类型 参数意义注释
	* @return 返回值的类型 意义注释
	* @exception 例外的类型 意义注释
	 */
	public String[][] selectSecore(int sex,int sportsid,HashMap map ){
		String[][] scores = new String[map.size()-1][Integer.parseInt(map.get("number1").toString())+3];
		String sql = "SELECT t_match.finalitemid,t_item.id,t_item.itemtype,t_match.playerid,t_match.score " +
				"FROM t_match JOIN t_finalitem ON t_finalitem.id=t_match.finalitemid JOIN t_group2item ON " +
				"t_group2item.id = t_finalitem.gp2itid JOIN t_item ON t_item.id = t_group2item.itemid JOIN " +
				"t_group2sports ON t_group2sports.id=t_group2item.gp2spid " +
				"JOIN t_group ON t_group2sports.groupid = t_group.id WHERE " +
				"t_finalitem.sportsid=? AND t_group.groupsex=?";
		try {
	        Connection myconn = dbc.getConn();
	        log.debug("myconn"+myconn);
	        if(myconn != null){
	        	ResultSet rs = null;
	            PreparedStatement statement = myconn.prepareStatement(sql);
	            statement.setInt(0, sportsid);
	            statement.setInt(1, sex);
	            rs = statement.executeQuery();
	            Iterator temp = map.keySet().iterator();
	            int count1=0;
	            if(temp.hasNext()){
	            	rs.beforeFirst();
	            	String old = temp.next().toString();
	            	int fid = Integer.parseInt(old);
	            	if (old != "number1"){
	            		int count = 3;
	            		scores[count1][0] = fid+"";
	            		while(rs.next()){
	            			if(rs.getInt(1) == fid){
	            				scores[count1][1] = rs.getInt(2)+"";
	    			            scores[count1][2] = rs.getInt(3)+"";
	    			            scores[count1][count] = rs.getInt(4)+";"+rs.getString(5);
	    				        count++;
	            			}
	            			
	            		}
//		            	for(int i = 0; i < (map.size()-1);i++){
//		            		
//			            }
	            	}
	            	count1++;
	            }
	            
	            rs.close();
	            statement.close();
	        }
	        dbc.freeConnection(myconn);  
	    }catch (SQLException e) {                 
	        e.printStackTrace(); } 
		return scores;
	}
	
	public ArrayList manageMark(HashMap num,String[][] score,HashMap record,int[] mark,int[] recordmark,String sex){
		ArrayList result = new ArrayList();
		Iterator temp = num.keySet().iterator();
		//int count1=0;
        if(temp.hasNext()){
        	String fid = temp.next().toString();
        	if(fid != "number1"){
        		int value = Integer.parseInt(num.get(fid).toString());
        		for(int j = 0; j<score.length;j++){
        			if (score[j][0]==fid){
        				
        				for (int i = 3; i<(value+3);i++){
	            			int newmark = mark[i];
	            			Iterator temp2 = record.keySet().iterator(); 
	            			String score2 = score[j][i];
	            			while(temp2.hasNext()){
	            				String[] value2 = temp2.next().toString().split(";");
	            				if(value2[0].equals(sex) && value2[1].equals(score[j][1])){
	            					if(value2[2].equals("0")){
	            						newmark += recordmark[0];
	            					}
	            				}
	            			}
	            			Iterator temp3 = record.keySet().iterator(); 
	            			while(temp3.hasNext()){
	            				String[] value2 = temp3.next().toString().split(";");
	            				if(value2[0].equals(sex) && value2[1].equals(score[j][1])){//还未调用比较大小的方法
	            					if(value2[2].equals("1")){
	            						newmark += recordmark[1];
	            					}
	            				}
	            			}
	            			String sql = "";
	            			result.add(sql);
        				}
        				
        			}
        		}
        	}
        }
        return result;
	}
}
