/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	系统管理
 * 子模块名称：	帐号管理
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-13		V1.0	lw 		新建
*/
package cn.edu.hbcit.smms.servlet.systemmanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.systemmanagedao.AccountDAO;
import cn.edu.hbcit.smms.services.systemmanageservices.AccountService;

/**
 * 新建帐号类
 *
 * 本类的简要描述：
 *
 * @author Administrator
 * @version 1.00  2012-6-13 新建类
 */

public class AddAccountServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(AddAccountServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public AddAccountServlet() {
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
		AccountService as = new AccountService();
		int rst = 0;
		String uname,rightsVal,realname,departid;
		
		uname = request.getParameter("uname");
		rightsVal = request.getParameter("rightsVal");
		realname = request.getParameter("realname");
		departid = request.getParameter("departid");
		
		log.debug(uname+";"+rightsVal+";"+realname+";"+departid);
		rst = as.addAccount(uname, rightsVal, realname, departid);
		
		if(rst > 0){
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
