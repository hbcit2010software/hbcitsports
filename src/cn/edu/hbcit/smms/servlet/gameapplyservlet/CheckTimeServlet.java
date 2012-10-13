/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	领队报名时间验证
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-10-11			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gameapplyservices.AddTeamleaderService;
import cn.edu.hbcit.smms.services.gameapplyservices.GetPlayerService;

public class CheckTimeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckTimeServlet() {
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
		HttpSession session = request.getSession();	
		
		GetPlayerService spn = new GetPlayerService();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date registedTime = null;
		try{
		      registedTime = format.parse(spn.getRegistend());
		}catch(Exception e){
			e.printStackTrace();
		}
		Date date = new Date();
		
		//String dateTime = format.format(date);
		System.out.println("date============="+date+"registedTime========"+registedTime);
		if(date.getDate()>registedTime.getDate()){
			session.setAttribute("msg","报名日期已过！！！");
			response.sendRedirect("../apply_playershow.jsp");
		}else{
			response.sendRedirect("../apply_teamleader.jsp");
		}
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
