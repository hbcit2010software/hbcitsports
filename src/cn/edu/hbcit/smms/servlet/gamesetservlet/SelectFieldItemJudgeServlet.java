package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.gamesetdao.OfficialSetDAO;
import cn.edu.hbcit.smms.services.gamesetservices.OfficialSetService;

public class SelectFieldItemJudgeServlet extends HttpServlet {
	
	protected final Logger log = Logger.getLogger(SelectFieldItemJudgeServlet.class.getName());

	/**查询的所有田赛裁判员集合输出在前台以便查看
	 * Constructor of the object.
	 */
	public SelectFieldItemJudgeServlet() {
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
		HttpSession session = request.getSession();
		int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
		ArrayList judgeList = new ArrayList();
		OfficialSetService official= new OfficialSetService();
		judgeList = official.selectAllFiledJudge(sportsid);
		log.debug("judgeList"+judgeList);
		
		session.setAttribute("fieldJudge", judgeList);
		response.sendRedirect("../set_fieldJudgeLook.jsp");
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
