package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.pojo.GameApplyPJ;
import cn.edu.hbcit.smms.services.gameapplyservices.GameApplyService;
import cn.edu.hbcit.smms.services.gameapplyservices.GetPlayerService;

/**
 * 分组查询本系运动员信息类
 *
 * 本类的简要描述：
 * 
 * 
 *@author 陈丹凤
 *@version 11.12  2012/6/15  新建
 */
public class SelectPlayerByGroupNameServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectPlayerByGroupNameServlet() {
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");//转换字符编码集		
		PrintWriter out = response.getWriter();
		
		GameApplyService groupPlayerListService = new GameApplyService();
		ArrayList groupPlayerList = new ArrayList();
		GetPlayerService player = new GetPlayerService();
		String id = request.getParameter("groupid");
		int groupid = Integer.parseInt(id);	
		String username = request.getParameter("${sessionScope.username}");
		int departid = player.getDepartid(username);//获取部门id
		groupPlayerList = groupPlayerListService.selectPlayerByGroupName(groupid, departid);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"contents\":[");
		for (int i = 0; i < groupPlayerList.size(); i++) {
			GameApplyPJ ad = (GameApplyPJ) groupPlayerList.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append("{");
			sb.append("\"num\":\"" + ad.getPlayernum() + "\",");// 运动员号码
			sb.append("\"name\":\"" + ad.getPlayername() + "\",");// 运动员姓名
			sb.append("\"sex\":\"" + ad.getPlayersex() + "\",");// 运动员性别
			sb.append("\"item\":\"" + ad.getRegistitem() + "\"");// 参赛项目
			sb.append("}");
		}
		sb.append("]");
		sb.append("}");
		out.println(sb);
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

}
