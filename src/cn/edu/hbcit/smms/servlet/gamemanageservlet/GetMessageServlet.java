package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import cn.edu.hbcit.smms.services.gamemanageservices.GetMessageservices;
import cn.edu.hbcit.smms.util.UtilTools;

public class GetMessageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(GetMessageServlet.class.getName());
	UtilTools ctc = new UtilTools();
	GetMessageservices gms = new GetMessageservices();
	/**
	 * Constructor of the object.
	 */
	public GetMessageServlet() {
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
		String action = request.getParameter("action");
		if( action.equals("playermse")){
			this.getPlayerMessage(request, response);
		}
		
		if( action .equals("itemtype")){
			this.getItemType(request, response);
		}
		
		if( action.equals("checkFormat")){
			log.debug("checkFormat="+action);
			this.getFormatReg(request, response);
		}
	}
	/**
	 * 获得某个项目的所有小组运动员的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPlayerMessage(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String finalitemname = ctc.toUTF8(request.getParameter("item"));
		JSONArray list = gms.getPlayerMessage(finalitemname);
		log.debug("getPlayerMessage="+list);
		out.println(list);
		out.flush();
		out.close();
	}
	/**
	 * 指定项目的比赛类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getItemType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//GetMessageservices gms = new GetMessageservices();
		String finalitemname = ctc.toUTF8(request.getParameter("item"));
		String list = gms.getItemType(finalitemname);
		log.debug("getItemType="+list);
		out.println(list);
		out.flush();
		out.close();
	}
	
	/**
	 * 获取成绩格式的正则表达式
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getFormatReg(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String finalitemname = ctc.toUTF8(request.getParameter("item"));
		log.debug("getFormat：finalitemname="+finalitemname);
		String reg = gms.getFormatReg(finalitemname);
		//reg =  reg.substring(1, reg.length());
		log.debug("getFormat="+reg);
		out.println(reg);
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
