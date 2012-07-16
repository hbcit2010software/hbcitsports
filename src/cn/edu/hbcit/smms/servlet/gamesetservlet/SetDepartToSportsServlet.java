/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   部门管理
*
* 备注：
*
* 修改历史：
* 2012-7-17	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;

/**
 * 设置部门是否属于某届运动会类
 * 简要说明:
 * @author 李玮
 * @version 1.00  2012-7-17上午02:30:14	新建
 */

public class SetDepartToSportsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SetDepartToSportsServlet() {
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
		HttpSession session = request.getSession();
		SportsService ss = new SportsService();
		boolean flag = false;
		String sportsId, departmentId, param;//运动会ID，部门ID，增删标识（add/del）
		
		sportsId = ((Integer)session.getAttribute("currSportsId")).toString(); //获取登录时的当前运动会ID
		departmentId = request.getParameter("dpid");
		param = request.getParameter("param");
		//如果增删标识是add，则添加记录；若为del，则删除记录
		if(param.equalsIgnoreCase("add")){
			flag = ss.addDepartmentToSports(sportsId, departmentId);
		}else if(param.equalsIgnoreCase("del")){
			flag = ss.removeDepartmentToSports(sportsId, departmentId);
		}
		//
		if(flag){
			out.print("success");
		}else{
			out.print("error");
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
