package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.pojo.Admin;
import cn.edu.hbcit.smms.services.gamesetservices.SetServices;

public class GuiChengServlet extends HttpServlet {

	/**添加大会基本规程
	 * Constructor of the object.
	 */
	public GuiChengServlet() {
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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action").trim();
		String conts = request.getParameter("conts").trim();
		String pionts = request.getParameter("pionts").trim();
		String others = request.getParameter("others").trim();
		//String  sportsname = request.getParameter(" sportsname").trim();
		SetServices gg = new SetServices();
		Admin admin = new Admin();
		 
		int sportsid=Integer.parseInt(request.getSession().getAttribute("currSportsId").toString());


		if(gg.getQuery(sportsid))
		{
			if(gg.update(sportsid, action, conts, pionts, others)){
				
				out.println("success");
			}			
			else{
				out.println("error");
			}

		}
		else
		{
			if(gg.add(sportsid, action, conts, pionts, others)){
		
			out.println("success");
		}			
		else{
			out.println("error");
		}
		}
		
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
