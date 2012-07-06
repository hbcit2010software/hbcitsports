package cn.edu.hbcit.smms.servlet.createprogramservlet;
/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     生成秩序册模块
 * 子模块名称：  生成word
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2012/6/25    V1.0        田小英        整合几个生成word的servlet	
 */
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.dao.createprogramdao.SelectGameBeforInfoDao;
import cn.edu.hbcit.smms.dao.createprogramdao.SlipDataDao;
import cn.edu.hbcit.smms.dao.createprogramdao.WordDemoDao;
import cn.edu.hbcit.smms.dao.createprogramdao.WordGameRecordDao;
import cn.edu.hbcit.smms.dao.createprogramdao.WordSelectPlayer;
import cn.edu.hbcit.smms.services.createprogramservices.WordDemoService;
/**
 * 
 * @author 田小英
 *
 */
public class WordDemoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WordDemoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
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
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		//int currSportsId = Integer.parseInt(session.getAttribute("currSportsId").toString());   //运动会ID
		int currSportsId = 1;
		String modelName = "program";                //模块名称：秩序册
		String fileName = currSportsId + "-" + modelName + "-" ;
		int id = Integer.parseInt(request.getParameter("id"));

		WordDemoService wDemo = new WordDemoService();
		Map map = wDemo.SlipCharactor(currSportsId);
		Map map1 = wDemo.SlipFildCharactor(currSportsId);
		Map map2 = wDemo.getGameInfo(currSportsId);
		List list1 = wDemo.getPlayerNumber(currSportsId);
		List list2 = wDemo.getPlayerNumber2(currSportsId);
		List list3 = wDemo.getGameDataTitle(currSportsId);
		List list4 = wDemo.getAllGameInfo(currSportsId);
		List gameRecord = wDemo.getGameRecord();
		Map studentJudge = wDemo.SlipStudentJudgeMember(currSportsId);
		switch(id){
		case 1:
			fileName += "01";
			WordDemoDao wd = new WordDemoDao();                     //生成赛前的大会记录
			wd.wordDemo(map, map1, map2, list1, list2, list3, list4, fileName);
			break;
		case 2:
			fileName += "03";
			WordSelectPlayer ws = new WordSelectPlayer( );    //生成各部门的运动员号码
			ws.SelPlaWD( fileName );
			break;
		case 3:
			fileName += "04";
			WordGameRecordDao wRecord = new WordGameRecordDao();         //破记录
			wRecord.wordGameRecord(gameRecord, studentJudge, fileName);
			break;
			
		}
	
		response.sendRedirect("../group_success.jsp");
		
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
