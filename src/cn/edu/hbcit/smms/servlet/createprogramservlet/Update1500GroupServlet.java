package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.services.createprogramservices.CreateProgramGameGroupingServices;
import cn.edu.hbcit.smms.services.createprogramservices.GameGroupingServices;

/**
 * 长跑组数修改类
 *
 *简要说明
 *
 *详细解释。
 * @author 韩鑫鹏
 * @version 1.00  2011/12/07 新規作成<br>
 */
public class Update1500GroupServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Update1500GroupServlet() {
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
		PrintWriter out = response.getWriter();
		GameGroupingServices gameGrouping = new GameGroupingServices();
		String fid = request.getAttribute("fid").toString().trim();
		int id = Integer.parseInt(fid);
		CreateProgramGameGroupingServices cpgg = new CreateProgramGameGroupingServices();
		cpgg.deletePnumByFid(id);
		ArrayList players = new ArrayList();
		players = cpgg.selectPnumByFid(id);
		int gnum = Integer.parseInt(request.getAttribute("num").toString());
		int[] group = gameGrouping.set1500GpNum(gnum, players.size());
		int count = 0;
		String sql = "INSERT INTO t_match(finalitemid,teamnum,playerid) values";
		for (int i = 0; i < group.length; i++){
			for (int j = 0; j < group[i]; j++){
				if (i > 0){ sql += ","; }
				sql += "(" + id + "," + (i+1) + "," + Integer.parseInt(players.get(count).toString()) + ")";
				count++;
			}
		}
		int flag = 0;
		flag = cpgg.addRecordBySql(sql);
		if (flag != 0){
			out.println("success");
		}
		else{
			out.println("false");
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
