package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gameapplyservices.GameApplyService;


/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：   赛事报名
 * 子模块名称：   报名情况查询
 *
 * 备注：根据号码查询运动员信息
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2012/6/25            
 */
/**
 * @author 陈丹凤
 */
public class SelectAllPlayerByNumServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectAllPlayerByNumServlet() {
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

		//查询运动员信息
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		GameApplyService allPlayerService = new GameApplyService();
		HttpSession session = request.getSession();	
		ArrayList playerList = new ArrayList();
		String num = request.getParameter("playernum");
		playerList = allPlayerService.selectAllPlayerByNum(num);
		session.setAttribute("playerList", playerList);

		//根据组别类型查询该届运动会所有项目
		ArrayList itemList = new ArrayList();
		GameApplyService groupItemService = new GameApplyService();
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
		String id = request.getParameter("groupid");	
		int groupid = Integer.parseInt(id);	
		int grouptype = groupItemService.getGroupType(groupid);
		//根据组别类型获取该组的分组
		ArrayList groupList = new ArrayList();
		groupList = groupItemService.selectGroupNameByGroupType(sportsid, grouptype);		
		itemList = groupItemService.selectAllItemsByGroupType(sportsid, grouptype);
		session.setAttribute("groupList", groupList);		
		session.setAttribute("itemList", itemList);
		response.sendRedirect("../update_player.jsp");	
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

		this.doGet(request, response);
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
