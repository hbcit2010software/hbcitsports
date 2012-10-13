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

import cn.edu.hbcit.smms.dao.gameapplydao.GetPlayerDAO;
import cn.edu.hbcit.smms.pojo.Player;
import cn.edu.hbcit.smms.pojo.PlayerNum;
import cn.edu.hbcit.smms.services.gameapplyservices.*;
import cn.edu.hbcit.smms.util.UtilTools;


/**
 * 2012, 河北工业职业技术学院计算机系2010软件专业.
 * 模块名称： 赛事报名
 * 子模块名称：学生报名
 *
 *备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 */
/**
 * @author 吕志瑶
 *
 */


public class GetItemNameServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetItemNameServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		GetPlayerService itemName  = new GetPlayerService();
		GetPlayerService spn = new GetPlayerService();
		//
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date registedTime = null;
		try{
		      registedTime = format.parse(spn.getRegistend());
		}catch(Exception e){
			e.printStackTrace();
		}
		Date date = new Date();
		
		//String dateTime = format.format(date);
		System.out.println("date============="+date+"registedTime========"+registedTime);
		if(date.getDate()>registedTime.getDate()){
			session.setAttribute("msg","报名日期已过！！！");
			response.sendRedirect("../apply_playershow.jsp");
		}else{
		//String sportsname = spn.getSportsName();//获取当前运动会名称
		int sportsId = 0;
		if(session.getAttribute("currSportsId") != null){
			sportsId = ((Integer)session.getAttribute("currSportsId")).intValue();
		}
		int grouptype = 1;//组别类型设为学生
		//int itemtype = itemName.getItemName(sportsname, grouptype);
		int sum = spn.getItemNumber(sportsId,grouptype);
		ArrayList list = new ArrayList();
		list = itemName.getItemName(sportsId,grouptype);
		session.setAttribute("mylist", list);//获取当前页面运动会所有项目
		session.setAttribute("num", Integer.valueOf(sum));//获取当前页面运动会项目总数量
		//
		GetPlayerService player = new GetPlayerService();
		int flag = 0;
		String username = (String)session.getAttribute("username");//获取用户名
		flag = player.getDepartid(username);//根据用户名获取部门id
		int sp2dpid = player.getSp2dpid(flag);//获取当前部门id及运动会的id
		session.setAttribute("sp2dpid",sp2dpid);
		//	
		//GetPlayerDAO getPlayerDao = new GetPlayerDAO();
		GetPlayerService playernum = new GetPlayerService(); 
		ArrayList list1 = new ArrayList();
		ArrayList playerNumList = new ArrayList();
		list1 = playernum.getPlayerNum(sp2dpid,1);//根据部门id及运动会id获取运动员号码簿
		session.setAttribute("playernum",list1);
		
		//
		int sp2dpid2 = Integer.parseInt(session.getAttribute("sp2dpid").toString());//得到sp2dpid
		boolean numtype = true;//定义号码的类型
		int sums = playernum.selectPlayerNum(sp2dpid2,numtype);//根据号码的类型得知数据库中的numid
		
		if(sums==0){
			session.setAttribute("msg","还未分配号码簿！！！");
			response.sendRedirect("../apply_playershow.jsp");
		}else{
		if(!list.isEmpty()){
		PlayerNum p = (PlayerNum)list1.get(0);
		int begin = Integer.parseInt(p.getBeginnum());//得到起始号码
		int end = Integer.parseInt(p.getEndnum());//得到终止号码
		if(!list.isEmpty()){
		//if(sums!=0){
		int namesum = playernum.selNameByNumid(sums);//根据numid获取报名人数
		session.setAttribute("namesum", Integer.valueOf(namesum));
		int sumnumid  = playernum.getSumNumId(sums);//计算数据库中号码id的总数量
		session.setAttribute("sumnumid",Integer.valueOf(sums));
		
		if(sumnumid==0){//如果数量为0则执行以下代码	
		String sql = "insert into t_player (sp2dpid,playernum,numid) values ";//插入sql语句
		for(int i = begin;i <= end;i++){//循环插入到库中的起始到终止号码
			
			if(i > begin){
				sql = sql + ",";	
			}
			sql = sql + "(" + sp2dpid + "," + (i + "") + "," + sums + ")";
			
		}
		playernum.addPlayerBySql(sql);
		}
		}
		}
		playerNumList = playernum.getPlayernum(sp2dpid, numtype);  //查询未分配的号段
		GetPlayerService getGroupService = new GetPlayerService();
		int perMan = getGroupService.selRule(sportsId);//获得每人限报的项目数量
		int perDepartment = getGroupService.selRule2(sportsId);//获得每一组每个项目限报的数量
		session.setAttribute("perMan", Integer.valueOf(perMan));
		session.setAttribute("perDepartment", Integer.valueOf(perDepartment));
		request.setAttribute("playerNumList", playerNumList);
		request.getRequestDispatcher("/apply_student.jsp").forward(request, response);
		//response.sendRedirect("../apply_student.jsp");
	}
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
