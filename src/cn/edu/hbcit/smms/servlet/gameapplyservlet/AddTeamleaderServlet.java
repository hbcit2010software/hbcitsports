/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	领队报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-27			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gameapplyservices.AddTeamleaderService;
import cn.edu.hbcit.smms.services.gameapplyservices.GetPlayerService;
/**
 * “领队报名”页面类：
 *
 * @author 
 * @version 1.00  2012-6-27 新建类
 */
public class AddTeamleaderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddTeamleaderServlet() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		AddTeamleaderService addTeamleaderService =new AddTeamleaderService();		
		int flag = 0;
		String username = (String)session.getAttribute("username");//获取用户名
		GetPlayerService player = new GetPlayerService();
		int sportsId = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
		int departId = player.getDepartid(username);//获取部门id
		int sum = addTeamleaderService.updatePlayerNum(sportsId, departId);
		if(sum==0){
			String teamLeader = request.getParameter("teamleader");//获得领队名字
			String coach = request.getParameter("coach");//获得教练名字
			String doctor = request.getParameter("doctor");//获得队医名字
			flag = addTeamleaderService.addSports2Department(sportsId, departId, teamLeader, coach, doctor);	
		}
		if(flag == 1){
			session.setAttribute("msg","添加成功！");
		}else{
			session.setAttribute("msg","您已添加，若要修改请跳转查询修改界面！");
		}
		response.sendRedirect("../apply_show.jsp");
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
