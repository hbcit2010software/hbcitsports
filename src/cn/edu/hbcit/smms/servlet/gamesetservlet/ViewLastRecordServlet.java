/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   赛会纪录
*
* 备注：
*
* 修改历史：
* 2012-10-15	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.services.gamesetservices.RecordServices;

/**
 * 查看最近的运动会记录 类
 * 简要说明:
 * @author 李玮
 * @version 1.00  2012-10-15上午12:40:13	新建
 */

public class ViewLastRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ViewLastRecordServlet() {
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
		RecordServices rs = new RecordServices();
		ArrayList lastRecord_man = new ArrayList();
		ArrayList lastRecord_woman = new ArrayList();
		lastRecord_man = rs.selectLastRecords(rs.selectAllItemId(), 1);		//男子最新记录
		lastRecord_woman = rs.selectLastRecords(rs.selectAllItemId(), 0);	//女子最新记录
		request.setAttribute("lastRecord_man", lastRecord_man);
		request.setAttribute("lastRecord_woman", lastRecord_woman);
		request.getRequestDispatcher("/set_record.jsp").forward(request, response);
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
