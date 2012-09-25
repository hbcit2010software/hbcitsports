package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBTest;
import cn.edu.hbcit.smms.services.gamesetservices.FieldjudgeService;
import cn.edu.hbcit.smms.services.gamesetservices.UpdateOfficialSetService;

public class UpdateFieldItemJudgeServlet extends HttpServlet {
	
	protected final Logger log = Logger.getLogger(UpdateFieldItemJudgeServlet.class.getName());

	/**修改小框中田赛项目裁判
	 * Constructor of the object.
	 */
	public UpdateFieldItemJudgeServlet() {
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

		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		FieldjudgeService fieldjudgeService=new FieldjudgeService();
		//String fieldjudge=request.getParameter("fieldjudge");
		int id=Integer.parseInt(request.getParameter("id").trim());
		log.debug("id_________________"+id);
        String judge_1=request.getParameter("judge_1").toString().trim();
        log.debug("judge_1_________________"+judge_1);
        String judge_2=request.getParameter("judge_2").toString().trim();
        log.debug("judge_2_________________"+judge_2);
        String judge_3=request.getParameter("judge_3").toString().trim();
        log.debug("judge_3_________________"+judge_3);
        if (fieldjudgeService.updateFiledItemJudge(id,judge_1, judge_2, judge_3))
		{
        	out.println("success");
		}else{
			out.println("error");
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
