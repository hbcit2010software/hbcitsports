package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamemanageservices.AddScoreServices;
import cn.edu.hbcit.smms.util.ChangeToChinese;

public class AddScoreServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(AddScoreServlet.class.getName());
	ChangeToChinese ctc = new ChangeToChinese();
	/**
	 * Constructor of the object.
	 */
	public AddScoreServlet() {
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
		String action = request.getParameter("action");
		if( action.equals("addscore")){
			log.debug("addscore="+action);
			this.doPost(request, response);
		}
		if( action.equals("checkFormat")){
			log.debug("checkFormat="+action);
			this.getFormatReg(request, response);
		}
		
		if( action.equals("getFormat")){
			log.debug("getFormat="+action);
			this.getFormat(request, response);
		}
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
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String playername = request.getParameter("playername");
		String score = request.getParameter("score");
		String group = request.getParameter("group");
		String item = null;
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString());
		item = ctc.toChinese(request.getParameter("item"));
		
		log.debug(playername+","+score+","+item);
		
		AddScoreServices ass = new AddScoreServices();
		
		boolean flag = false;
		String str = "提交失败！";
		
		flag = ass.isAddScore(playername, score, item , group);			//成绩添加是否成功
		int num = ass.getIntegral(item, 1, group);		//积分添加是否成功
		
		if( flag ){
			str = "提交成功！";
		}
		
		log.debug("doPost="+str);
		log.debug("doPost---ass="+num);
		out.println(str);
		out.flush();
		out.close();
	}

	
	public void getFormatReg(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		log.debug("getFormatReg=");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String finalitemname = null;
		finalitemname = ctc.toChinese(request.getParameter("finalitemname"));
		log.debug("getFormat：finalitemname="+finalitemname);
		AddScoreServices ass = new AddScoreServices();
		String reg = null;
		reg = ass.getFormatReg(finalitemname);
		log.debug("getFormat="+reg);
		HttpSession session = request.getSession();
		session.setAttribute("reg", reg);
		out.println(reg);
		out.flush();
		out.close();
	}
	
	
	public void getFormat(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	log.debug("getFormat=");
	response.setCharacterEncoding("utf-8");
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String finalitemname = null;
	finalitemname = ctc.toChinese(request.getParameter("finalitemname"));
	log.debug("getFormat：finalitemname="+finalitemname);
	AddScoreServices ass = new AddScoreServices();
	String str = null;
	str = ass.getFormat(finalitemname);
	log.debug("getFormat="+str);
	out.println(str);
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
