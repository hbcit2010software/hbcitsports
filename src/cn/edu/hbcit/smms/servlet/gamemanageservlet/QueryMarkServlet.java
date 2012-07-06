package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gamemanageservices.QueryMarkServices;
import cn.edu.hbcit.smms.util.ChangeToChinese;

public class QueryMarkServlet extends HttpServlet {
	ChangeToChinese ctc = new ChangeToChinese();
	/**
	 * Constructor of the object.
	 */
	public QueryMarkServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		if( action.equals("QueryMarkdp")){
			this.QueryMarkBydp(request, response);
		}
		if( action.equals("QueryMarkit")){
			this.QueryMarkByitem(request, response);
		}
		out.flush();
		out.close();
	}
	
	public void QueryMarkBydp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			HttpSession sn = request.getSession();
			
			int departid = Integer.parseInt(request.getParameter("depart"));
			int sportsid =	Integer.parseInt(sn.getAttribute("currSportsId").toString());
			int grouptype = Integer.parseInt(request.getParameter("group"));
			
			QueryMarkServices qms = new QueryMarkServices();
			int sum = 0 ; 
			sum = qms.queryByGroup(departid, sportsid, grouptype);
			out.println(sum);
			out.flush();
			out.close();
}

	
	
	public void QueryMarkByitem(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int itemid = Integer.parseInt(request.getParameter("item"));
		int grouptype = Integer.parseInt(request.getParameter("role"));
		
		QueryMarkServices qms = new QueryMarkServices();
		int sum = 0 ; 
		sum = qms.queryBItem(grouptype, itemid);
		out.println(sum);
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
