package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.pojo.Admin;
 
import cn.edu.hbcit.smms.services.gamesetservices.RecordServices;

public class AlterRecordServlet extends HttpServlet {

	/**修改创纪录内容
	 * Constructor of the object.
	 */
	public AlterRecordServlet() {
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
		//System.out.println("qqqqqqqqqqqq");
		RecordServices re = new RecordServices();
		 
		PrintWriter out = response.getWriter();

		 
		
		String plaName = request.getParameter("plaName").trim();
	 
		String sportsName1 = request.getParameter("sportsName1").trim();
		String recTime = request.getParameter("recTime").trim().substring(0, 7);
		String recLevel = request.getParameter("recLevel").trim();
		String sor = request.getParameter("sor").trim();
		String depName = request.getParameter("depName").trim();
		int recordId = Integer.parseInt(request.getParameter("recordId").trim());
		//String itemName = request.getParameter("itemName").trim();
		int plaSex = Integer.parseInt(request.getParameter("plaSex").trim());
		 
 
		//int newItemId = re.getItemId(itemName);


		if (re.updateRecord(plaName,sportsName1, recTime, recLevel, plaSex, sor, depName, recordId)
				){
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
