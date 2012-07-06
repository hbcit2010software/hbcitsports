package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.createprogramservices.CreateProgramGameGroupingServices;

/**
 * 接力分组信息查看servlet
 *
 *简要说明
 *
 *详细解释。
 * @author 韩鑫鹏
 * @version 1.00  2011/12/07 新規作成<br>
 */
public class ReailyLook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReailyLook() {
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

		HttpSession session = request.getSession();
		int finalId = 1;
		CreateProgramGameGroupingServices cpgg = new CreateProgramGameGroupingServices(); //数据库操作类对象cpgg
		ArrayList nextFlag = new ArrayList();
		nextFlag = cpgg.slectFidRGnum(finalId);
		ArrayList group = cpgg.selectTrackGroup(finalId);
		ArrayList players = cpgg.slectRelayInfo(finalId);
		ArrayList allPlayers = new ArrayList();
		int count = 0;
		ArrayList groupNum = new ArrayList();
		if (nextFlag != null && group != null && players != null){
			for (int i = 0; i < group.size(); i++){
				String aa = "第" +(i+1)+ "组";
				groupNum.add(aa);
				int num = Integer.parseInt(group.get(i).toString());
				String[] temp = new String[num];
				for (int j = 0; j < num; j++){
					temp[j] = players.get(count).toString();
					count++;
				}
				allPlayers.add(temp);
			}
		}
		
		session.setAttribute("groupnum", groupNum);
		session.setAttribute("trackPlayers", allPlayers);
		session.setAttribute("trackFlag", nextFlag);
		response.sendRedirect("../tracklock.jsp");
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

		this.doPost(request, response);
		
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
