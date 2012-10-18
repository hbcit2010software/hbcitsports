package cn.edu.hbcit.smms.servlet.gamequeryservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.pojo.QueryRegistitemToItems;
import cn.edu.hbcit.smms.pojo.QuerySeInfoData;
import cn.edu.hbcit.smms.services.gamequeryservices.GameQueryServices;

/*
 * Copyright(C) 2004, XXXXXXXX.
 *
 * 模块名称：     综合查询
 * 子模块名称：   综合查询
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 2004/12/12		0.1		张 三		新建
 * 2005/02/05		0.1		李 四		Bug修正
 */
public class SelectByQuestionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectByQuestionServlet() {
		super();
	}

	/**
	 * SelectByQuestionServlet类
	 *
	 *简要说明
	 *获取查询条件
	 *详细解释。
	 * @author 张三
	 * @version 1.00  2011/12/07 新規作成<br>
	 */

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
        //response.setContentType("test/html;charset=utf-8");
		response.setContentType("test/html");
		PrintWriter out = response.getWriter();
		Logger log = Logger.getLogger(SelectByQuestionServlet.class.getName());
		//ArrayList list = new ArrayList();
		JSONArray jsonarray = new JSONArray();
		//获取选的的查询条件
		int sportsid = Integer.parseInt(request.getParameter("sportsid"));//获取运动会id
		String playername = new String( request.getParameter("playername").trim().getBytes("ISO-8859-1"),"utf-8");//获取运动员姓名
		int departname = Integer.parseInt(request.getParameter("departname"));//获取部门名称
		int province = Integer.parseInt(request.getParameter("province"));//获取组别名称
		String itemtype = request.getParameter("itemtype");//获取项目类型
		int item = Integer.parseInt(request.getParameter("item"));//获取项目名称
		String score1 = request.getParameter("score1").trim();//获取数字最小值的成绩
		String score2 = request.getParameter("score2").trim();//获取数字最大值的成绩
		String breakrecord = request.getParameter("breakrecord");//获取破纪录有没有被选择
		log.debug(playername);
		log.debug(itemtype);
			GameQueryServices gqs = new GameQueryServices();
			jsonarray = gqs.selectInQuestion(sportsid, playername, departname, province, itemtype, item, score1, score2, breakrecord);
		/*向前台传递json类型数据
		 * */
		out.println(jsonarray);
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
