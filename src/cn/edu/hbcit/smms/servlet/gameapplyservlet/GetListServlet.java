package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gameapplyservices.GetPlayerService;

public class GetListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetListServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		GetPlayerService gps = new GetPlayerService();
		ArrayList list_groupItem = new ArrayList();
		ArrayList list_group = new ArrayList();
		int sportsid = 0;
		if(session.getAttribute("currSportsId") != null){
			sportsid = ((Integer)session.getAttribute("currSportsId")).intValue();
		}
		list_groupItem = gps.getGroupItemStu(sportsid);
		list_group = gps.getGroupStu(sportsid);
		request.setAttribute("itemGroup", list_groupItem);
		request.setAttribute("group", list_group);
		request.getRequestDispatcher("/apply_listinfo.jsp").forward(request, response);
		//response.sendRedirect("../apply_groupitem.jsp");
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
