package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
import cn.edu.hbcit.smms.services.gamesetservices.RecordServices;

public class RecordSelectByNameTimeServlet extends HttpServlet {

	/**根据时间和项目名称查询历史记录
	 * Constructor of the object.
	 */
	public RecordSelectByNameTimeServlet() {
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
		response.setCharacterEncoding("utf-8");
		 
		 request.setCharacterEncoding("utf-8");
		 HttpSession session = request.getSession();
 
		int recordId =  Integer.parseInt(request.getParameter("recordId"));
		   
		System.out.println(recordId);
		 
		 
		RecordServices bs = new RecordServices();
		//
		ArrayList adminList=new ArrayList();
		 
		adminList = bs.seleteByRecordId(recordId);
			//HttpSession session=request.getSession();
		//request.setAttribute("adminList", adminList);
			//out.println(adminList);
		//request.getRequestDispatcher("../set_recordalter.jsp").forward(request, response);
		session.setAttribute("adminList", adminList);
		response.sendRedirect("../set_recordalter.jsp");
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
