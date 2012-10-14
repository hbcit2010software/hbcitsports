/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	教工报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-21			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.gameapplydao.UpdatePlayerDAO;
import cn.edu.hbcit.smms.services.gameapplyservices.PlayerService;

/**
 * “教工报名”页面类：
 *
 * @author 
 * @version 1.00  2012-6-21 新建类
 */

public class UpdatePlayerTeacherServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(UpdatePlayerTeacherServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public UpdatePlayerTeacherServlet() {
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
		
		//号码，姓名，组别id，项目id#type；项目id#type
//**************************韩鑫鹏代码***********************************
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PlayerService playerService = new PlayerService();
		int sp2dpid = Integer.parseInt(session.getAttribute("sp2dpid").toString());
		int sportsId = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
		int perNum = playerService.selectPerDep(sportsId);//各系各项限报人数
		log.debug("各系各项限报人数" + perNum);
		String[] allstr = request.getParameterValues("hide");//获取页面上所有隐藏文本框的字符串:号码，姓名，性别，id#type；id#type
		HashMap group = new HashMap();//v：性别(男1,女2)   k：组别id
	    group = playerService.selectTeaGroupByspSdpid(sportsId);
	    log.debug("v：性别(男1,女2)   k：组别id**********"+group);
	    HashMap dataInfo = playerService.selectTeaPlayerByspSdpid(sp2dpid);//k：groupid+项目id  v：人数string
	    log.debug("k：groupid+项目id  v：人数string"+dataInfo.size() + "" + dataInfo );
	    ArrayList addInfo = playerService.getTeaPageInfo(allstr, group, dataInfo, perNum, sp2dpid);
	    String addSql = addInfo.get(0).toString();
	    log.debug("添加运动员报名信息sal：" + addSql);
	    if (!addSql.equals("") && addSql!=null){
	    	playerService.updatePlayerBySql(addSql);
	    }
	    ArrayList error = (ArrayList)addInfo.get(1);
	    log.debug("添加sql语句" + addSql);
	    for(int i = 0; i < error.size();i++){
	    	log.debug("报名出错人员名字：" + error.get(i).toString());
	    }
	    log.debug("添加sql语句" + addSql);
	    request.setAttribute("error", error);
	    request.getRequestDispatcher("/apply_show.jsp").forward(request, response);
//**************************结束***********************************
		
//********************************************以下是陈系晶所写代码******************************************************
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		PlayerService playerService = new PlayerService();
//		int flag = 0;
//		String[] allstr = request.getParameterValues("hide");
//		int sp2dpid = Integer.parseInt(session.getAttribute("flag1").toString());//获取sp2dpid
//		flag = playerService.updatePlayer(allstr, sp2dpid);
//		
//		if(flag ==0){
//			session.setAttribute("msg","添加失败！");
//		}else{
//			session.setAttribute("msg","添加成功！请根据条件查询");
//		}
//		response.sendRedirect("../apply_show.jsp");
//*****************************************************************************************************
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
