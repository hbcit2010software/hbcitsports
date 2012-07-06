package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;


/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     赛前设置
 * 子模块名称：   运动会管理
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2012/06/20	0.1		李玮		新建
 */
/**
 * @author 李玮
 *
 */
public class AddSportsServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(AddSportsServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public AddSportsServlet() {
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

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		SportsService ss = new SportsService();
		String sportsName,  begin,  end,  registEnd,  address ;
		boolean flag = false;
		
		sportsName = request.getParameter("spname");
		begin = request.getParameter("begin");
		end = request.getParameter("end");
		registEnd = request.getParameter("registend");
		address = request.getParameter("address");
		
		log.debug("sportsName,begin,end,registEnd,address："+sportsName+"~~"+begin+"~~"+end+"~~"+registEnd+"~~"+address);
		flag = ss.addSports(sportsName, begin, end, registEnd, address);
		
		if(flag){
			out.print("success");
		}else{
			out.print("error");
		}
		out.flush();
		out.close();
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

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * XX类
	 *
	 * 简要说明:
	 *
	 * @author Administrator
	 * @version 1.00  2012-6-20上午11:04:07	新建
	 */
}
