
package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.pojo.GameManagePoJo;
import cn.edu.hbcit.smms.services.gamemanageservices.GameManageServices;

public class GameManageGetPlayerInfByPlayerNum extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GameManageGetPlayerInfByPlayerNum() {
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
		ArrayList playerInfList = new ArrayList();
		
		String playerNum = request.getParameter("playerNum");		
		String finalItemId = request.getParameter("finalItemId");
		String infNull = " ";
		
		if(!playerNum.equals(""))
		{
		  GameManageServices gm = new GameManageServices();
		  playerInfList = gm.getPlayerList(Integer.parseInt(playerNum),Integer.parseInt(finalItemId));
		    
		    buffer.append("{");
			buffer.append("\"contents\":[");		   
		    if(playerInfList.size()>0)
		    {
		    for(int i = 0;i<playerInfList.size();i++)
		    {
		    	GameManagePoJo pj = (GameManagePoJo)playerInfList.get(i);
		    	if(i>0)
		    	{
		    		buffer.append(",");
		    	}
		    	
		    	buffer.append("{");
				buffer.append("\"playernum\":\"" + pj.getPlayernum() + "\",");
				buffer.append("\"playername\":\"" + pj.getPlayername() + "\",");
				buffer.append("\"playersex\":\"" + pj.getPlayersex() + "\",");
				buffer.append("\"score\":\"" + pj.getScore() + "\",");
				buffer.append("\"matchid\":\"" + pj.getMatchid() + "\",");
				buffer.append("\"finalitemname\":\"" + pj.getFinalitemname() + "\",");
				buffer.append("\"foul\":\"" + pj.getFoul2() + "\",");
				buffer.append("\"recordlevel\":\"" + pj.getRecordlevel2() + "\",");
				buffer.append("\"departname\":\"" + pj.getDepartname() + "\"");
				System.out.println("Departname==============================="+pj.getDepartname());
				buffer.append("}");
		    	
		    }}
        else{
        	  buffer.append("数据库中无此记录");
      	      out.println(buffer);
		       }
		    buffer.append("]");
			buffer.append("}");
			out.println(buffer);
			out.flush();
			out.close();
		}
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
