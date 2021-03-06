/**
 * Copyright(C) 2012, liwei.
 *
 * 模块名称：	系统设置
 * 子模块名称：	密码修改
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-9-25		V1.0		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.systemmanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.services.loginservices.LoginService;
import cn.edu.hbcit.smms.services.systemmanageservices.AccountService;
import cn.edu.hbcit.smms.util.MD5;

/**
 * 修改密码类
 *
 * 本类的简要描述：
 *
 * @author Administrator
 * @version 1.00  2012-9-25 新建类
 */

public class UpdatePasswordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdatePasswordServlet() {
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
		LoginService ls = new LoginService();
		AccountService as = new AccountService();
		MD5 md5 = new MD5();
		boolean flag = false;
		
		String username,password,againPwd,oldPwd;
		username = request.getParameter("username");
		oldPwd = request.getParameter("old");
		password = request.getParameter("new1");
		againPwd = request.getParameter("new2");
		if(password.equals(againPwd)){
			if(ls.canLogin(username, md5.MD5Encode(oldPwd))){
				flag = as.updatePassword(username, md5.MD5Encode(password));
			}
		}
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
