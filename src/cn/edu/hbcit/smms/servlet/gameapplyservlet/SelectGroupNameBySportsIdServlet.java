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


/**
 * 根据运动会id获取该届运动会组别类
 *
 * 本类的简要描述：
 * 
 * 
 *@author 陈丹凤
 *@version 15.30  2012/6/15  新建
 */
public class SelectGroupNameBySportsIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectGroupNameBySportsIdServlet() {
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");//转换字符编码集
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();	
		ArrayList groupList = new ArrayList();
		GameApplyService groupNameService = new GameApplyService();
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
		groupList = groupNameService.selectAllGroupNameBySportsId(sportsid);
		
		//获取部门名称
		String username = request.getParameter("${sessionScope.username}");
		String departName = groupNameService.getDepartmentName(username);
		session.setAttribute("departName", departName);
		session.setAttribute("grouplist", groupList);
		response.sendRedirect("../player_list.jsp");
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
