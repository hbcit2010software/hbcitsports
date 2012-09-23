/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   规则管理
*
* 备注：
*
* 修改历史：
* 2012-9-23	0.1		李玮		新建
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
 * 更新赛事规则
 * 简要说明:
 * @author 李玮
 * @version 1.00  2012-9-23下午11:51:35	新建
 */

public class UpdateRule extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateRule() {
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

		SportsService ss = new SportsService();
		
		String id = request.getParameter("id");
		String mark = request.getParameter("mark");
		String position = request.getParameter("position");
		String perman = request.getParameter("perman");
		String perdepartment = request.getParameter("perdepartment");
		String recordmark_low = request.getParameter("recordmark_low");
		String recordmark_high = request.getParameter("recordmark_high");
		boolean flag = false;
		
		flag = ss.updateRule(id, position, mark, perman, perdepartment, recordmark_low, recordmark_high);
		
		if(flag){
			out.println("success");
			out.flush();
			out.close();
		}else{
			out.println("error");
			out.flush();
			out.close();
		}
		
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
