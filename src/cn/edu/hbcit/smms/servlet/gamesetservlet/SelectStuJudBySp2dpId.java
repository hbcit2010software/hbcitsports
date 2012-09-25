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

import cn.edu.hbcit.smms.services.gamesetservices.OfficialSetService;

public class SelectStuJudBySp2dpId extends HttpServlet {
	
	/**查询到的各系部学生裁判传到前台以便修改查看
	 * Constructor of the object.
	 */
	protected final Logger log = Logger.getLogger(SelectStuJudBySp2dpId.class.getName());
	/**
	 * Constructor of the object.
	 */
	public SelectStuJudBySp2dpId() {
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

		log.debug("修改小框中_______________________________");
		
		OfficialSetService officialsetService=new OfficialSetService();
        HttpSession session = request.getSession();
		ArrayList list1 = new ArrayList();
		//int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
		int sp2dpid = Integer.parseInt(request.getParameter("sp2dpid"));
		log.debug("修改小框中的学生裁判sp2dpid "+sp2dpid);
		list1=officialsetService.selectStuJudge(sp2dpid);
		log.debug("修改小框中的学生裁判 "+list1);
		session.setAttribute("stuJudge", list1);
		response.sendRedirect("../set_alterstuson.jsp");
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
