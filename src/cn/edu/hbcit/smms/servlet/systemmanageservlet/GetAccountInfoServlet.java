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
 * 2012-6-9		V1.0		李玮			新建
*/
package cn.edu.hbcit.smms.servlet.systemmanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.dao.systemmanagedao.AccountDAO;
import cn.edu.hbcit.smms.dao.systemmanagedao.RightsDAO;

/**
 * 获取帐号信息类
 *
 * 本类的简要描述：
 * 获取帐号及权限信息，负责显示帐号管理页面
 *
 * @author 李玮
 * @version 1.00  2012-6-9 新建类
 */

public class GetAccountInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAccountInfoServlet() {
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
		RightsDAO rd = new RightsDAO();
		AccountDAO ad = new AccountDAO();
		HttpSession session = request.getSession();
		ArrayList list = new ArrayList();
		
		//获取登录时的用户权限
		int userrights = ((Integer)session.getAttribute("userrights")).intValue();
		if(!rd.checkPower(userrights, 0)){
			response.sendRedirect("../main.jsp");
		}else{
			list = ad.selectAccountInfo();
			request.setAttribute("account", list);
			request.getRequestDispatcher("/admin_rights.jsp").forward(request, response);
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
