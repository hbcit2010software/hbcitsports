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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.dao.createprogramdao.WordGameBeforInfoDao;
import cn.edu.hbcit.smms.dao.createprogramdao.WordGameRecordDao;
import cn.edu.hbcit.smms.dao.createprogramdao.WordSelectPlayer;
import cn.edu.hbcit.smms.services.createprogramservices.DataManagerServices;
import cn.edu.hbcit.smms.services.createprogramservices.SetWordServices;
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
		
		//response.setHeader("Pragma", "No-cache");
		//response.setHeader("Cache-control", "no-cache");
		//response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int currSportsId = Integer.parseInt(session.getAttribute("currSportsId").toString());   //运动会ID
		//int currSportsId = 1;
		//String modelName = "program";                //模块名称：秩序册
		//String fileName = currSportsId + "-" + modelName + "-" ;
		int id = Integer.parseInt(request.getParameter("id".trim()));
		WordDemoService wDemo = new WordDemoService();
		String filePath = request.getSession().getServletContext().getRealPath("/");        //生成的路径
		session.setAttribute("filePath", filePath);

		
		
		try{
		switch(id){
		case 1:
			String fileName1 = "gameBeforeInfo.doc";
			Map gameInfoMap = wDemo.getSplitOfficialMember(currSportsId);
			Map fildJudgeMap =  wDemo.getSplitFildJudge(currSportsId);
			Map getGameDate = wDemo.getGameDate(currSportsId);
			Map getItemByMale = wDemo.getItemByMale(currSportsId);
			Map getItemByFemale = wDemo.getItemByFemale(currSportsId);
			List studentList = wDemo.getStudentPlayerNumber(currSportsId);
			List teacherList = wDemo.getTeacherPlayerNumber(currSportsId);
			Map getGameDateInfo = wDemo.getGameDateInfo(currSportsId);
			WordGameBeforInfoDao wInfo = new WordGameBeforInfoDao();         //生成赛前的大会记录
			wInfo.wordDocument(filePath, fileName1 ,gameInfoMap, fildJudgeMap, getGameDate, 
					getItemByMale, getItemByFemale, studentList, teacherList, getGameDateInfo);
			out.print("success");
			break;
		case 2:
			
			DataManagerServices cpgg = new DataManagerServices();
			HashMap department = new HashMap();
			department = cpgg.selectDepartmentBySid(currSportsId);
			HashMap allGirlPlayers = new HashMap();
			HashMap allBoyPlayers = new HashMap();
			
			allGirlPlayers = cpgg.selectFlaGirl(currSportsId);
			allBoyPlayers = cpgg.selectFlaBoy(currSportsId);
			
			HashMap players = cpgg.selectPlayersBySid(currSportsId);
			
			SetWordServices swss = new SetWordServices();
			String fileName2 = "createProgram.doc";
			String fileName = filePath + fileName2;
			swss.AddGroupInfo(fileName, allGirlPlayers, allBoyPlayers, players, department);
			out.print("success");
			break;
		case 3:
			String fileName3 = "departmentNumber.doc";
			WordSelectPlayer ws = new WordSelectPlayer( );    //生成各部门的运动员号码
			ws.SelPlaWD( filePath, fileName3 );
			out.print("success");
			break;
		case 4:
			String fileName4 = "record.doc";
			List gameRecord = wDemo.getGameRecord();
			Map studentJudge = wDemo.SlipStudentJudgeMember(currSportsId);
			WordGameRecordDao wRecord = new WordGameRecordDao();         //破记录
			wRecord.wordGameRecord(filePath, gameRecord, studentJudge, fileName4);
			out.print("success");
			
			break;
			
		}
		}catch(Exception e){
			out.print("error");
		}
		out.flush();
		out.close();
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
