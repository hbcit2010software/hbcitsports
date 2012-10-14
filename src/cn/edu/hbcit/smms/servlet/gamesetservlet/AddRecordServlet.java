package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.pojo.Admin;
 
import cn.edu.hbcit.smms.services.gamesetservices.RecordServices;

public class AddRecordServlet extends HttpServlet {

	/**添加项目记录
	 * Constructor of the object.
	 */
	public AddRecordServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		
		String plaName = request.getParameter("plaName").trim();
		String sportsName = request.getParameter("sportsName").trim();
		String recTime = request.getParameter("recTime").trim();
		String recLevel = request.getParameter("recLevel").trim();
		String sor = request.getParameter("sor").trim();
		String depName = request.getParameter("depName").trim();
		String itemName = request.getParameter("itemName").trim();
		int plaSex = Integer.parseInt(request.getParameter("plaSex").trim());

		RecordServices re = new RecordServices();
		int itemId = Integer.parseInt(itemName);
		if(re.addRecord(plaName, sportsName, recTime, recLevel, plaSex, sor, depName, itemId)){
		
			out.println("success");
		}			
		else{
			out.println("error");
		}
		out.flush();
		out.close();
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

		doGet(request, response);
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
