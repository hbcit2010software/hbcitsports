package cn.edu.hbcit.smms.servlet.gamequeryservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.pojo.QueryPageData;
import cn.edu.hbcit.smms.services.gamequeryservices.GameQueryServices;

/*
 * Copyright(C) 2004, XXXXXXXX.
 *
 * 模块名称：     AAAAAAAAAAA
 * 子模块名称：   BBBBBBBBBBB
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2004/12/12		0.1		张 三		新建
 * 2005/02/05		0.1		李 四		Bug修正
 */
public class SelectDepartNameInSportsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectDepartNameInSportsServlet() {
		super();
	}

	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXX类
	 *
	 *简要说明
	 *
	 *详细解释。
	 * @author 张三
	 * @version 1.00  2011/12/07 新規作成<br>
	 */

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
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if(action==null)action = "";
		if(action.equals("departname"))departName(request,response);
		if(action.equals("province"))groupName(request,response);
	}
	public void departName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	response.setContentType("text/html");
	response.setCharacterEncoding("UTF-8");
	GameQueryServices gqs = new GameQueryServices();
	PrintWriter out = response.getWriter();
    String id = new String(request.getParameter("sportsid").getBytes("ISO-8859-1"),"utf-8");
	int sportsid = Integer.parseInt(id);
	ArrayList list = new ArrayList();
	list = gqs.selectDepartNameInSports(sportsid);
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	sb.append("\"contents\":[");
	for (int i = 0; i < list.size(); i++) {
		QueryPageData ls = (QueryPageData)list.get(i);
		if (i > 0) {
			sb.append(",");
		}
		sb.append("{");
		sb.append("\"getDepartid\":\"" +ls.getDepartid()  + "\",");
		sb.append("\"getDepartname\":\"" + ls.getDepartname() + "\"");
		sb.append("}");
	}
	sb.append("]");
	sb.append("}");
	out.println(sb);
	out.flush();
	out.close();
		
	}
	
	public void groupName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id = new String(request.getParameter("sportsid").getBytes("ISO-8859-1"),"utf-8");
		int sportsid = Integer.parseInt(id);
		GameQueryServices gqs = new GameQueryServices();
		ArrayList grouplist = new ArrayList();
		grouplist = gqs.selectGroupBySportsId(sportsid);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"contents\":[");
		for (int i = 0; i < grouplist.size(); i++) {
			QueryPageData ls = (QueryPageData)grouplist.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append("{");
			sb.append("\"groupid\":\"" +ls.getGroupid()  + "\",");
			sb.append("\"groupname\":\"" + ls.getGroupname() + "\"");
			sb.append("}");
		}
		sb.append("]");
		sb.append("}");
		out.println(sb);
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
