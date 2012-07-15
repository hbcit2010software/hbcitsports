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

package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;


import cn.edu.hbcit.smms.services.gamemanageservices.GetConditonServices;


/**
 * GetConditonServlet类
 *
 *简要说明
 *
 *详细解释。
 * @author wuguofa
 * @version 1.00  2012/06/11 新建
 */

public class GetConditonServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(GetConditonServlet.class.getName());
	
	/**
	 * Constructor of the object.
	 */
	public GetConditonServlet() {
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
		String actino = request.getParameter("action");
		
		System.out.println("action="+actino);
		
		if( actino.equals("allcond")){
			this.selectAllCondition(request, response);
		}
		
		if( actino.equals("itmecondBygp")){
			this.selectItemNameByGP(request, response);
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

		this.doGet(request, response);
	}
	
	public void selectAllCondition(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession sn = request.getSession();		//获得session对象
		ArrayList list = new ArrayList();
		GetConditonServices gcs = new GetConditonServices();
		int sportsid = gcs.getSportID();		//获取运动会的id
		list = gcs.getAllGP(sportsid);
		log.debug(list);
		sn.setAttribute("conditionlist", list);
		response.sendRedirect("../inputscore.jsp");

	}
	
	/**
	 * 
	* 方法说明		查询本届运动会组别对应的项目名称
	* 方法补充说明
	* @param 参数名 参数类型
	* @return		void
	 */
	
	public void selectItemNameByGP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONArray list = new JSONArray();
		GetConditonServices gcs = new GetConditonServices();
		int sportsid = gcs.getSportID();
		String groupname = request.getParameter("option");
		list = gcs.selectItemsByGroup(groupname, sportsid);
		log.debug(list);
		out.println(list);
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
