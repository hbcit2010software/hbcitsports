package cn.edu.hbcit.smms.servlet.createprogramservlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.createprogramdao.DataManagerDAO;
import cn.edu.hbcit.smms.services.createprogramservices.DataManagerServices;


public class CheckGroupServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(CheckGroupServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public CheckGroupServlet() {
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

		log.debug("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		DataManagerServices dms = new DataManagerServices();
		int sportsId = 1;
		String flag = dms.checkGroup(sportsId);
		log.debug("flag"+ flag);
		if (flag.trim().equals("true")){
			out.println("success");
		}else{
			out.println("flase");
		}
		session.setAttribute("gameGroupingFlag", flag);
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
