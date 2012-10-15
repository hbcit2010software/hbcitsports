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

/**添加学生裁判集合
 * 
 * @author Administrator
 *
 */
public class AddStuJudgeServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(AddStuJudgeServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public AddStuJudgeServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		OfficialSetService off = new OfficialSetService();
		String insertSql = request.getParameter("insertString");
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString());  
		log.debug("insertSql:"+insertSql);
		String type = request.getParameter("judType");
		if (type.equals("stu")){
			if (session.getAttribute("stuJudge") != null){
				off.deleteStuJudge(sportsid);
			}
		}
		if (type.equals("fil")){
			if (session.getAttribute("fieJudge") != null){
				off.deleteFiledJudge(sportsid);
			}
		}
		int flag = off.addRecordBySql(insertSql);
		if(flag > 0){
			out.println("success");
		}else{
			out.println("false");
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
