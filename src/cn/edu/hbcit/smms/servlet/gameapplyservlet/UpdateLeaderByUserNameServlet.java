package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gameapplyservices.SelGameApplyService;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：   赛事报名
 * 子模块名称：   报名情况查询
 *
 * 备注：根据号码修改运动员信息,重新报名
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2012/9/6
 */
/**
 * @author 陈丹凤
 */
public class UpdateLeaderByUserNameServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateLeaderByUserNameServlet() {
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		SelGameApplyService gas = new SelGameApplyService();
		String username = (String)session.getAttribute("username");//获取用户名
		String teamleader = request.getParameter("teamleader").trim();
		String coach = request.getParameter("coach").trim();
		String doctor = request.getParameter("doctor").trim();	
		if (gas.updateLeaderByUserName(username, teamleader, coach, doctor))
			out.println("success");
		else
			out.println("error");
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
