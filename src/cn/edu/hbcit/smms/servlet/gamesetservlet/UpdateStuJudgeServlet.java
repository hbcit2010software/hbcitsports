package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamesetservices.OfficialSetService;

public class UpdateStuJudgeServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(UpdateStuJudgeServlet.class.getName());
	/**修改小框中学生裁判
	 * Constructor of the object.
	 */
	public UpdateStuJudgeServlet() {
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
	 * This method is called when a form has its tag value method equals to 

get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse 

response)
			throws ServletException, IOException {
         
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OfficialSetService officialsetService=new OfficialSetService();
		int id=Integer.parseInt(request.getParameter("id"));
		log.debug("id------------------"+id);
        String contact=new String(request.getParameter("contact").getBytes("ISO-8859-1"),"UTF-8");
        String tel=request.getParameter("tel");
        String member=new String(request.getParameter("member").getBytes("ISO-8859-1"),"UTF-8");
        boolean flag = false;
        log.debug("flag------------------"+flag);
        flag = officialsetService.updateStuJudge(id,contact, tel, member);
        if (flag == true){
        	out.println("success");
        }else{
        	out.println("error");
        }
        out.flush();
		out.close();  
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to 

post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse 

response)
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