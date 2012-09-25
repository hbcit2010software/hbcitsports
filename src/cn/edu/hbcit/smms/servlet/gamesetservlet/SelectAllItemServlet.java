package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import cn.edu.hbcit.smms.dao.gamesetdao.OfficialSetDAO;
import cn.edu.hbcit.smms.pojo.Item;
import cn.edu.hbcit.smms.services.gamesetservices.FieldjudgeService;
import cn.edu.hbcit.smms.services.gamesetservices.OfficialSetService;



public class SelectAllItemServlet extends HttpServlet {

	/**查询项目和参赛部门
	 * Constructor of the object.
	 */
	public SelectAllItemServlet() {
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
		OfficialSetService official= new OfficialSetService();
		FieldjudgeService   playernum=new FieldjudgeService();
		HttpSession session = request.getSession();
		ArrayList slist = new ArrayList();
		ArrayList list = new ArrayList();
		int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
		slist = official.selectAllItem(sportsid);
		session.setAttribute("itemlist", slist);
		list = playernum.selectTeaDepartmentBySportsid(sportsid);
		session.setAttribute("departmentlist1", list);
		ArrayList list1 = new ArrayList();
		//list1 = official.selectGp2itid(sportsid);
		//session.setAttribute("gp2itidlist", list1);
		response.sendRedirect("../set_officialjudge.jsp");
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
