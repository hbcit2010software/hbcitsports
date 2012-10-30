/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     
* 子模块名称：   
*
* 备注：
*
* 修改历史：
* 2012-10-30	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.services.gamesetservices.RecordServices;

/**
 * 更新赛事记录类
 * 简要说明:
 * @author 李玮
 * @version 1.00  2012-10-30下午09:50:12	新建
 */

public class UpdateRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateRecordServlet() {
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
		String id = request.getParameter("id").trim();
		String itemid = request.getParameter("item").trim();
		String sex = request.getParameter("sex").trim();
		String score = request.getParameter("score").trim();
		String playername = request.getParameter("playername").trim();
		String departname = request.getParameter("depart").trim();
		String sportsname = request.getParameter("sportsname").trim();
		String recordtime = request.getParameter("scoretime").trim();
		String recordlevel = request.getParameter("level").trim();
		
		RecordServices re = new RecordServices();
		boolean flag = false;
		flag = re.updateRecord(id, itemid, sex, score, playername, departname, sportsname, recordtime, recordlevel);
		if(flag){
			out.print("success");
		}			
		else{
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
