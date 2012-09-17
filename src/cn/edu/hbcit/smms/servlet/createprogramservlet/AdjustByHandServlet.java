package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.createprogramservices.AdjustByHandServices;
/**
 * 手工调整类
 *
 * 本类的简要描述：
 *
 * @author 田小英
 * @version 1.00  2012-6-18 新建类
 */

public class AdjustByHandServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdjustByHandServlet() {
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
		String str = request.getParameter("str");
		String[] splitStr = str.split(";");
		int finalItemId = Integer.parseInt(splitStr[0]);
		int teamNum = Integer.parseInt(splitStr[1]);
		AdjustByHandServices aByHand = new AdjustByHandServices();
		List runwayInfo = aByHand.getRunwayInfo(finalItemId, teamNum);
		session.setAttribute("finalItemId", ""+finalItemId);
		session.setAttribute("teamNum", ""+teamNum);
		session.setAttribute("info",runwayInfo );
		response.sendRedirect("../update_runway.jsp");
   
		
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
