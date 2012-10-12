package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.services.gamesetservices.OfficialSetService;

public class CheckJudgeServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(LoginDAO.class.getName());
	/**
	 * Constructor of the object.
	 */
	public CheckJudgeServlet() {
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
		OfficialSetService off = new OfficialSetService();
		HttpSession session = request.getSession();
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString());  
		
		String judType = "";
		judType = request.getParameter("judType");
		log.debug("*******************************judType*"+judType);
		boolean flag = false;
		if (judType.trim().equals("stu")){
			flag = off.checkStuJudge(sportsid);
			if (flag == true){
				session.setAttribute("stuJudge", "have");
				out.println("success");
			}	
		}else{
			flag = off.checkFiledJudge(sportsid);
			if (flag == true){
				session.setAttribute("fieJudge", "have");
				out.println("success");
			}
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
