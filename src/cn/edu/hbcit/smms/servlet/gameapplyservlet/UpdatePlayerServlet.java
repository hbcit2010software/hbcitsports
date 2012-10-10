/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	学生报名
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

import cn.edu.hbcit.smms.dao.gameapplydao.GetPlayerDAO;
import cn.edu.hbcit.smms.services.gameapplyservices.PlayerService;
/**
 * “学生报名”页面类：
 *
 * @author 
 * @version 1.00  2012-6-21 新建类
 */
public class UpdatePlayerServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(UpdatePlayerServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public UpdatePlayerServlet() {
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
//		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		PlayerService playerService = new PlayerService();
//		int sp2dpid = Integer.parseInt(session.getAttribute("sp2dpid").toString());
//		int sportsId = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
//		int perNum = playerService.selectPerDep(sportsId);
//		String[] allstr = request.getParameterValues("hide");//获取页面上所有隐藏文本框的字符串
//		HashMap group = new HashMap();
//	    group = playerService.selectStuGroupByspSdpid(sportsId);
//	    HashMap dataInfo = playerService.selectPlayerByspSdpid(sp2dpid);
//	    ArrayList addInfo = playerService.getPageInfo(allstr, group, dataInfo, perNum, sp2dpid);
//	    String addSql = addInfo.get(0).toString();
//	    playerService.updatePlayerBySql(addSql);
//	    ArrayList error = (ArrayList)addInfo.get(1);
//	    log.debug("添加sql语句" + addSql);
//	    for(int i = 0; i < error.size();i++){
//	    	log.debug("添加sql语句" + error.get(i).toString());
//	    }
//	    log.debug("添加sql语句" + addSql);
//		response.sendRedirect("../apply_show.jsp");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		PlayerService playerService = new PlayerService();
		int flag = 0;
		String[] allstr = request.getParameterValues("hide");//获取页面上所有隐藏文本框的字符串
		
			for(int i = 0; i < allstr.length; i++ ){
				int count = 0;
				String mystr = allstr[i];//取出每一个隐藏文本框的字符串
				String[] myPlayer = mystr.split(",");//把一个字符串中的值，去","取出来放在一个数组里
				for(int k=0; k<myPlayer.length; k++){
					log.debug("K:"+k +" myPlayer[k]:"+myPlayer[k]);
					if(myPlayer[k].equals("")){
						count++;
					}
				}
				if(count > 0){
					log.debug("第"+i +"次:"+ count);
					continue;
				}
				String playerNum = myPlayer[0];//运动员号码
				//System.out.println(playerNum);
				String playerName = myPlayer[1].trim();//运动员姓名
				//System.out.println(playerName);
				boolean playerSex = true;
				String sex = myPlayer[2];
				if(sex.equals("false")){
					playerSex = false;
				}else {
					playerSex = true;
				}
				int groupId = playerService.getGroupIdBySex(playerSex);
				//String registItem = myPlayer[3];//运动员所报项目的id
				//
				String registItem = "";//myPlayer[3]:运动员所报项目的id "2+3;4+5"
				String[] itemIdArr =  myPlayer[3].split(";");  //{"2+3","4+5"}
				for(int j=0; j<itemIdArr.length; j++){
					String[] tempStr = itemIdArr[j].split("#");  //{"2","3"}
					registItem = registItem + tempStr[0];
					if(j != itemIdArr.length-1){
						registItem += ";";
					}
				}
				//
				int sp2dpid = Integer.parseInt(session.getAttribute("sp2dpid").toString());
				flag = playerService.updatePlayerByNum(playerName, playerSex, registItem, groupId, sp2dpid, playerNum);
				if( flag == 0 ){
					break;
				}
			}
		
		if(flag == 0){
			session.setAttribute("msg","添加失败！");
		}else{
			session.setAttribute("msg","添加成功！请根据条件查询");
		}
		response.sendRedirect("../apply_show.jsp");
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
