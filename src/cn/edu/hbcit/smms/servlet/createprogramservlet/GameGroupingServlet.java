/*
* Copyright(C) 2004, XXXXXXXX.
*
* 模块名称：     生成秩序册
* 子模块名称：  赛事分组
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2012/6/6		0.1		韩鑫鹏		新建
*/

package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mysql.jdbc.log.Log;

import cn.edu.hbcit.smms.dao.createprogramdao.DataManagerDAO;
import cn.edu.hbcit.smms.dao.createprogramdao.GameGroupingDAO;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.Group2itemPojo;
import cn.edu.hbcit.smms.services.createprogramservices.DataManagerServices;
import cn.edu.hbcit.smms.services.createprogramservices.GameGroupingServices;

public class GameGroupingServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(LoginDAO.class.getName());
	
	public GameGroupingServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOExce+ption if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		DataManagerServices dataManager = new DataManagerServices();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int sportsId = Integer.parseInt(session.getAttribute("currSportsId").toString()); 
		//int sportsId = 1;
		
		String flag = session.getAttribute("gameGroupingFlag").toString().trim();
		
		
		if (flag.equals("true")){
			dataManager.deleteT_matchBySid(sportsId);	
		}
		
		GameGroupingServices ggs = new GameGroupingServices();
		
/*************************径赛分组********************************/
		
		ArrayList trackItems = new ArrayList(); //所有径赛项目ArrayList  groupid+itemid
		trackItems = dataManager.selectItemBySid(sportsId, "1");
		log.debug("该届运动会的所有能报的项目(径赛)的    groupid+itemid :"+trackItems.size());
	    HashMap itemid2name = new HashMap(); // 项目id，name对照HashMap
	    itemid2name = dataManager.selectItemId2nameBySid(sportsId); 
	    ArrayList departments = new ArrayList(); //所有部门id  ArrayList
	    departments = dataManager.selectDepidBySid(sportsId);
	    HashMap player2item = new HashMap(); // 数字+运动员id，运动员组别id+所报项目id对照HashMap
	    player2item = dataManager.selectplayer2itemBySid(sportsId);
	    log.debug("数字+运动员id，运动员组别id+所报项目id对照HashMap:"+player2item);
	    HashMap pla2dep = new HashMap(); // 运动员id，部门id对照 HashMap
	    pla2dep = dataManager.slectPlaid2DepidySid(sportsId);
	    HashMap item2finalitem = new HashMap(); // 组别id+项目id，最终项目id对应HashMap
	    item2finalitem = dataManager.slectItem2flaBySid(sportsId);
	    int pernums = 0; //项目各系限报人数
	    pernums = dataManager.selectPerDep(sportsId);
	    ArrayList trackSql = new ArrayList(); //径赛sql语句集合，第一条是分组情况sql语句，第二条是修改分组数目sql语句
	    trackSql = ggs.trackGrouping(trackItems, itemid2name, player2item,
	    		departments, pla2dep, item2finalitem, pernums);
	    String trackAddSql = trackSql.get(0).toString();
	    log.debug("径赛的添加sql语句" + trackAddSql);
	    if (!trackAddSql.equals("") && trackAddSql != null){
	    	dataManager.addRecordBySql(trackAddSql);
	    	log.debug("径赛分组结束" );
	    }
	    String trackUpdateSql = trackSql.get(1).toString();
	    log.debug("径赛的更改组别sql语句" + trackUpdateSql);
	    if (!trackUpdateSql.equals("") && trackUpdateSql != null ){
	    	dataManager.updateGroupNumBySql(trackUpdateSql);
	    	log.debug("径赛修改分组数目结束" );
	    }
/*************************田赛分组********************************/
	    
		ArrayList fieldItems = new ArrayList(); //该届运动会的所有能报的田赛项目的    groupid+itemid
		fieldItems = dataManager.selectItemBySid(sportsId, "2");
		StringBuffer fieldSql = new StringBuffer(); //田赛分组情况sql
		fieldSql = ggs.filedGrouping(fieldItems, player2item, item2finalitem);
		log.debug("田赛的sql语句" + fieldSql);
		log.debug("fieldSql.equals（‘’）" + fieldSql.equals(""));
		log.debug("!fieldSql.equals（‘’）" + !fieldSql.equals(""));
		log.debug("fieldSql.equals(insert)" + trackAddSql.equals("INSERT INTO t_match(finalitemid,teamnum,runway,playerid) VALUES"));
		log.debug("!fieldSql.equals（insert）" + !trackAddSql.equals("INSERT INTO t_match(finalitemid,teamnum,runway,playerid) VALUES"));
		log.debug("fieldSql.equals（null）" + (fieldSql==null));
		log.debug("!fieldSql.equals（null）" + (fieldSql!=null));
		if (fieldSql != null && !fieldSql.equals("")){
			dataManager.addRecordBySql(fieldSql.toString().trim());
			log.debug("田赛分组结束" );
		}
	    
/*************************接力分组********************************/
	    
		ArrayList reailyItems = new ArrayList();
		reailyItems = dataManager.selectItemBySid(sportsId, "3");
		HashMap groupid2name = new HashMap(); //组别id与组名 对照map
		groupid2name = dataManager.slectGroupId2nameBySid(sportsId); 
		ArrayList stuDep = new ArrayList(); //学生部门id集合
		stuDep = dataManager.slectStuDepidBySid(sportsId);
		ArrayList reailySql = new ArrayList(); //sql语句集合，第一条是分组情况sql语句，第二条是修改分组数目sql语句
		reailySql = ggs.trackReailyGrouping(reailyItems, groupid2name, departments, stuDep, item2finalitem);
		String reailyAddSql = reailySql.get(0).toString();
		log.debug("接力赛的sql语句" + reailyAddSql);
	    if (!reailyAddSql.equals("") && reailyAddSql != null){
	    	dataManager.addRecordBySql(reailyAddSql);
	    	log.debug("接力赛分组结束" );
	    }
	    String reailyUpdateSql = reailySql.get(1).toString();
	    log.debug("接力赛的组数修改sql语句" + reailyUpdateSql);
	    if (!reailyUpdateSql.equals("") && reailyUpdateSql != null ){
	    	dataManager.updateGroupNumBySql(reailyUpdateSql);
	    	log.debug("接力赛修改分组数目结束" );
	    }
		
/*************************分组完成********************************/	
	    out.println("success");
		//response.sendRedirect("../show.jsp");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
