/**
 * 
 */
package cn.edu.hbcit.smms.servlet.loginservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.databasedao.DBTest;
import cn.edu.hbcit.smms.services.loginservices.LoginService;
import cn.edu.hbcit.smms.servlet.systemmanageservlet.UpdateAccountRightsServlet;
import cn.edu.hbcit.smms.util.MD5;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     登录登出管理模块
 * 子模块名称：   登录
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 
 */
/**
 * @author 李玮
 *
 */
public class LoginServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(LoginServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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

		//response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		
		String chknumber = request.getParameter("chknumber");
		String username = request.getParameter("user");
		String password = request.getParameter("pwd");
		
		boolean flag = false;
		int userRights = 0, currSportsId = 0;
		String currSportsName = "";
		LoginService ls = new LoginService();
		MD5 md5 = new MD5();
		HttpSession session = request.getSession();
		String captcha = (String)session.getAttribute("captcha");
		if( captcha!= null && chknumber != null){
			if(captcha.equals(chknumber)){
				log.debug(md5.MD5Encode(password));
				flag = ls.canLogin(username, md5.MD5Encode(password));	//登录验证
				if(flag){
					userRights = ls.selectUserRights(username);		//获取用户权限
					currSportsId = ls.selectCurrentSportsId();		//获取当前运动会id
					currSportsName = ls.selectCurrentSportsName();	//获取当前运动会名称
					session.setAttribute("userrights", Integer.valueOf(userRights));
					session.setAttribute("currSportsId", Integer.valueOf(currSportsId));
					session.setAttribute("currSportsName", currSportsName);
					session.setAttribute("username", username);
					response.sendRedirect("../main.jsp");
				}else{
					out.print("loginerr");
					out.flush();
			        out.close();
				}
			}else{
				out.print("captchaerr");
				out.flush();
		        out.close();
			}
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
