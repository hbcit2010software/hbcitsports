/*
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
*/

package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.dao.gamemanagedao.AddScore;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.services.gamemanageservices.GameManageServices;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
/**
 * XXXXXXXXXXXXXXXXXXXXXXXX类
 *
 *简要说明
 *
 *详细解释。
 * @author 刘然
 * @version 1.00  2012/06/13 新規作成<br>
 */
public class GameManageUpdateMatchByPlayerNum extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GameManageUpdateMatchByPlayerNum() {
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
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		StringBuffer buffer = new StringBuffer();
		String playerNum = request.getParameter("playerNum");
		String score = request.getParameter("score");
		String foul = request.getParameter("foul");
		String recordlevel = request.getParameter("recordlevel");
		String matchid = request.getParameter("matchid");
		System.out.println("================================"+playerNum+score+foul+recordlevel+matchid);
		GameManageServices gm = new GameManageServices();
		
		String finalItemName = gm.finalItemName(Integer.parseInt(matchid));
		String groupName = gm.groupName(Integer.parseInt(matchid));
		
		boolean flag = false;
	    flag = gm.updateMatch(Integer.parseInt(matchid),score,Integer.parseInt(foul),Integer.parseInt(recordlevel));
	           
		LoginDAO ld = new LoginDAO();
		int sportsid = ld.selectCurrentSportsId();
		
		//**********************更改t_position中指定数据***********************/
		AddScore aScore = new AddScore();
		gm.deletePositionPlayer(finalItemName, sportsid, groupName);//先根据指定参数删除t_position中数据
		aScore.getGpItPlayerMessage(finalItemName, sportsid, groupName);//根据指定参数向t_position数据
		
		//更改t_position(积分)同时更改t_record表中记录
		gm.deleteRecordPlayer(finalItemName, Integer.parseInt(matchid));
		aScore.getIntegral(finalItemName, groupName);
       
        if(flag)
        {
        	out.println("success");
        }
        else
        {
        	out.println("error");
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
