/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   组别管理
*
* 备注：
*
* 修改历史：
* 2012-7-18	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;

/**
 * 修改组别信息类
 * 简要说明:
 * @author 李玮
 * @version 1.00  2012-7-18下午08:05:52	新建
 */

public class UpdateGroupServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateGroupServlet() {
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
		SportsService ss = new SportsService();
		boolean flag = false;
		String groupId, groupName, groupType, groupSex; //groupId组别id, groupName组别名称, groupType组别类型（教工/学生）, groupSex组别性别
		groupId = request.getParameter("gpid");
		groupName = request.getParameter("gn");
		groupType = request.getParameter("gt");
		groupSex = request.getParameter("gs");
		
		flag = ss.updateGroup(groupId, groupName, groupType, groupSex);
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
