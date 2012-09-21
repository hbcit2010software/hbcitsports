package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.createprogramdao.GameGroupingUtil;
import cn.edu.hbcit.smms.services.createprogramservices.DataManagerServices;
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

	protected final Logger log = Logger.getLogger(Update1500GroupServlet.class.getName());
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
		log.debug("WWWWWWWWWWWWWWWW");
		GameGroupingServices gameGrouping = new GameGroupingServices();
		DataManagerServices cpgg = new DataManagerServices();
		GameGroupingUtil gUtil = new GameGroupingUtil();
		//String fid = request.getAttribute("fid").toString().trim();
		String fid = request.getParameter("fid").trim();
		int id = Integer.parseInt(fid);
		ArrayList players = new ArrayList();
		players = cpgg.selectPnumByFid(id);
		
		cpgg.deletePnumByFid(id);
		
		log.debug("运动员 " + players);
		int gnum = Integer.parseInt(request.getParameter("num"));
		int[] group = gUtil.update1500GpNum(gnum, players.size());
		int count = 0;
		String sql = "INSERT INTO t_match(finalitemid,teamnum,playerid) values";
		for (int i = 0; i < group.length; i++){
			for (int j = 0; j < group[i]; j++){
				if (count > 0){ sql += ","; }
				sql += "(" + id + "," + (i+1) + "," + Integer.parseInt(players.get(count).toString().trim()) + ")";
				count++;
			}
		}
		int flag = 0;
		log.debug("修改1500sql" + sql);
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
