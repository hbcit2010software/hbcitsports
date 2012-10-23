/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     
* 子模块名称：   
*
* 备注：
*
* 修改历史：
* 2012-10-23	0.1		李玮		新建
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
 * 显示所有历届记录类
 * 简要说明:
 * @author 李玮
 * @version 1.00  2012-10-23下午11:37:33	新建
 */

public class ViewAllRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ViewAllRecordServlet() {
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
		RecordServices record = new RecordServices();
		ArrayList list_man = new ArrayList();
		ArrayList list_woman = new ArrayList();
		list_man = record.selectAllRecords(1);  //1：男
		list_woman = record.selectAllRecords(0);  //2：女
		request.setAttribute("man", list_man);
		request.setAttribute("woman", list_woman);
		request.getRequestDispatcher("/set_recordall.jsp").forward(request, response);
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
