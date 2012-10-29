package cn.edu.hbcit.smms.servlet.gameapplyservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.services.gameapplyservices.GetPlayerService;
import cn.edu.hbcit.smms.services.gameapplyservices.PlayerService;
import cn.edu.hbcit.smms.services.gameapplyservices.SelGameApplyService;

/*
 *
 * 模块名称：     赛事报名
 * 子模块名称：  报名情况查询
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 袁仕杰
 */
public class ApplyInfomationServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(ApplyInfomationServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public ApplyInfomationServlet() {
		super();
	}
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();	
		GetPlayerService spn = new GetPlayerService();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date registedTime = null;
		try{
		      registedTime = format.parse(spn.getRegistend());
		}catch(Exception e){
			e.printStackTrace();
		}
		Date date = new Date();
		
		//String dateTime = format.format(date);
		//System.out.println("date============="+date+"registedTime========"+registedTime);
		if(date.getDate()>registedTime.getDate()){
			session.setAttribute("msg","报名日期已过！！！");
			response.sendRedirect("../apply_playershow.jsp");
		}else{
		String action = request.getParameter("action");
		if(action==null)action = "";
		if(action.equals("doPost"))applyPageAgin(request,response);//页面信息
		if(action.equals("leader"))applyLeader(request,response);//教练信息
		if(action.equals("pageinf"))applyItems(request,response);//按条件查询出的信息
		if(action.equals("updateinf"))upDateInfo(request,response);//修改
		if(action.equals("delete"))infoDelete(request,response);
	}
	}
	
	public void applyPageAgin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");//转换字符编码集		
		HttpSession session = request.getSession();	
		SelGameApplyService gas = new SelGameApplyService();
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
		GetPlayerService getGroupService = new GetPlayerService();
		int perMan = getGroupService.selRule(sportsid);
		int perDepartment = getGroupService.selRule2(sportsid);
		session.setAttribute("perMan", Integer.valueOf(perMan));
		session.setAttribute("perDepartment", Integer.valueOf(perDepartment));
		String username = (String)session.getAttribute("username");//获取用户名
		String departName = gas.getDepartmentName(username);//获取部门名称
		ArrayList grouptypelist = new ArrayList();
        grouptypelist=gas.selectAllGroupTypes(sportsid ,username);
		request.setAttribute("typeList", grouptypelist);
		request.setAttribute("departName", departName);
		String matchgroup = "";
		try{
			matchgroup = (String)request.getAttribute("grouptypes");
			if(matchgroup==null){
				matchgroup = "Y";
			}else{
				matchgroup = (String)request.getAttribute("grouptypes");
			}
			request.setAttribute("grouptypes", matchgroup);
		}catch( Exception e){
			e.getStackTrace();
		}
		request.getRequestDispatcher("../apply_infomation.jsp").forward(request, response);
		
	}
	public void applyItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");//转换字符编码集
		HttpSession session = request.getSession();
		GetPlayerService spn = new GetPlayerService();
		//查询每一组别类型的所有运动员信息
		SelGameApplyService sgas = new SelGameApplyService();
        String group = request.getParameter("matchgroup");//获取项目类型id
        int grouptype = Integer.parseInt(group);
        request.setAttribute("grouptypes", group);
        session.setAttribute("grouptypes1", group);
    	String username = (String)session.getAttribute("username");//获取用户名
		int sportsId = Integer.parseInt(session.getAttribute("currSportsId").toString());//获取当前运动会id
		ArrayList playerinf = new ArrayList();
		playerinf = sgas.selectPlayerItemsMessage(username, sportsId, grouptype);
		//获取组别名
		ArrayList groupNameList = new ArrayList();
		groupNameList = sgas.selectGroupNameByGroupType(sportsId, grouptype);
		//获取运动项目名
		//-----------------------------------------------------------------------------------------------
		ArrayList itemList = new ArrayList();
		itemList = sgas.selectAllItemsByGroupType(sportsId, grouptype);
		request.setAttribute("groupPlayer",playerinf);//运动员信息
		request.setAttribute("groupList", groupNameList);//组别信息
		request.setAttribute("itemList", itemList);//项目名称信息
		//*******************************韩鑫鹏******************************
		GetPlayerService player = new GetPlayerService(); 
		int flag1 = 0;
		flag1 = player.getDepartid(username);//根据用户名获取部门id
		int sp2dpid = player.getSp2dpid(flag1);//获取当前部门id及运动会的id
		//int sp2dpid2 = Integer.parseInt(session.getAttribute("flag1").toString());//得到sp2dpid
		log.debug("grouptype############"+grouptype);
		log.debug("sp2dpid############"+sp2dpid);
		if(grouptype==1){
			log.debug("学生");
			int[][] itemInfo = null;
			itemInfo = spn.selectSItemByspSdpid(sportsId);
			itemInfo = spn.selectSPlayerByspSdpid(sp2dpid, itemInfo);
			request.setAttribute("itemInfo", itemInfo);
		}else{
			log.debug("教工");
			int[][] itemInfo = null;
			itemInfo = spn.selectTItemByspSdpid(sportsId);
			itemInfo = spn.selectTPlayerByspSdpid(sp2dpid, itemInfo);
			request.setAttribute("itemInfo", itemInfo);
		}
		
		
//*******************************韩鑫鹏******************************
		this.applyPageAgin(request, response);

		//request.getRequestDispatcher("../apply_infomation.jsp").forward(request, response);
}
	public void upDateInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PlayerService playerService = new PlayerService();
		GetPlayerService player = new GetPlayerService(); 
		int flag = 0;
		
		String[] allstr = request.getParameterValues("hide");//获取页面上所有隐藏文本框的字符串
		//System.out.println("upDateInfo"+allstr[0]);
		int flag1 = 0;
		String username = (String)session.getAttribute("username");//获取用户名
		flag1 = player.getDepartid(username);//根据用户名获取部门id
		int sp2dpid = player.getSp2dpid(flag1);//获取当前部门id及运动会的id
		flag = playerService.updatePlayer(allstr, sp2dpid);
		if(flag ==0){
			session.setAttribute("msg","修改失败！");
		}else{
			session.setAttribute("msg","修改成功！请根据条件查询");
		}
		String matchgroup = (String)session.getAttribute("grouptypes1");
		request.setAttribute("match", matchgroup);
		session.removeAttribute("grouptypes1");
		request.getRequestDispatcher("/apply_queryshow.jsp").forward(request, response);

}
	public void applyLeader(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");//转换字符编码集		
		HttpSession session = request.getSession();	
		SelGameApplyService gas = new SelGameApplyService();
		String username = (String)session.getAttribute("username");//获取用户名			
		//查询本系领队
		ArrayList leaderList = new ArrayList();
		leaderList = gas.selectAllLeaderByUserName(username);
		request.setAttribute("leader", leaderList);
		request.getRequestDispatcher("/apply_alterleaderinfo.jsp").forward(request, response);
}
	public void infoDelete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");//转换字符编码集		
		HttpSession session = request.getSession();	
		SelGameApplyService gas = new SelGameApplyService();
		GetPlayerService player = new GetPlayerService(); 
		int flag1 = 0;
		String username = (String)session.getAttribute("username");//获取用户名
		flag1 = player.getDepartid(username);//根据用户名获取部门id
		int sp2dpid = player.getSp2dpid(flag1);//获取当前部门id及运动会的id
		String playernum = request.getParameter("playernum");//获取号码簿
		int flag = 0;
		//System.out.println("sp2dpid:"+sp2dpid+"playernum:"+playernum);
		flag = gas.infoDelete(sp2dpid, playernum);
		if(flag!=0){
			session.setAttribute("msg","删除成功！");
		}else{
			session.setAttribute("msg","删除失败！");
		}
		String matchgroup = (String)session.getAttribute("grouptypes1");
		request.setAttribute("match", matchgroup);
		session.removeAttribute("grouptypes1");
		request.getRequestDispatcher("/apply_queryshow.jsp").forward(request, response);
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
