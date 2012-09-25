package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gamesetservices.UpdateOfficialSetService;

public class UpdateFiledJudgeServlet extends HttpServlet {

	/**获取前台传来的田赛裁判修改信息
	 * Constructor of the object.
	 */
	public UpdateFiledJudgeServlet() {
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
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UpdateOfficialSetService updateofficialsetService=new UpdateOfficialSetService();
		String fieldjudge=request.getParameter("fieldjudge");
        String fieldjudge_1=request.getParameter("fieldjudge_1");
        String fieldjudge_2=request.getParameter("fieldjudge_2");
        String fieldjudge_3=request.getParameter("fieldjudge_3");
        String fieldjudge_4=request.getParameter("fieldjudge_4");
        String fieldjudge_5=request.getParameter("fieldjudge_5");
        String fieldjudge_6=request.getParameter("fieldjudge_6");
        
        int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
        if (updateofficialsetService.updateFieldjudgeBySportsid(sportsid, fieldjudge, 
        		fieldjudge_1, fieldjudge_2, fieldjudge_3, fieldjudge_4, 
        		fieldjudge_5, fieldjudge_6))
			out.println("success");
		else
			out.println("error");

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
