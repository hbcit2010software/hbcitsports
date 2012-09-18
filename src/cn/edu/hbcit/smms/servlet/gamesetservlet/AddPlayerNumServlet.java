/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     
* 子模块名称：   
*
* 备注：
*
* 修改历史：
* 2012-9-18	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;

public class AddPlayerNumServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(AddPlayerNumServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public AddPlayerNumServlet() {
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

		response.setContentType("text/html");
		SportsService ss = new SportsService();
		ArrayList list = new ArrayList();
		String insertSQL = "";
		boolean flag_del = false;
		boolean flag_insert = false;
		int countPlayerNum = 0;
		int countNumType = 0;
		
		HttpSession session = request.getSession();
		int sportsId = 0;
		if(session.getAttribute("currSportsId") != null){
			sportsId = ((Integer)session.getAttribute("currSportsId")).intValue();
		}
		countPlayerNum = ss.countT_playernum(sportsId);
		countNumType = ss.countNumtype(sportsId);
		if(countPlayerNum == countNumType){
			//如果相等，说明已有数据
			log.debug("Player Num正常!");
		}else if(countPlayerNum == 0){
			//值为0，说明尚未插入数据
			list = ss.selectSp2DpID(sportsId);
			insertSQL = ss.getSqlOfInsertT_playernum(list); //构建的插入语句
			flag_insert = ss.addT_playernum(insertSQL);//添加T_playernum
		}else if(countPlayerNum != 0 && countPlayerNum != countNumType){
			//否则说明数据有异常，删除原有数据后，重新插入数据
			flag_del = ss.removePlayernum(sportsId);
			if(flag_del){
				list = ss.selectSp2DpID(sportsId);
				insertSQL = ss.getSqlOfInsertT_playernum(list); //构建的插入语句
				flag_insert = ss.addT_playernum(insertSQL);//添加T_playernum
			}
		}
		if(flag_del == false && flag_insert == true){
			request.setAttribute("msg", "参赛号段初始化成功！");
		}else if(flag_del && flag_insert){
			request.setAttribute("msg", "数据处理异常，可能是未按照顺序操作的缘故。请检查后续操作数据是否正常！");
		}
		request.getRequestDispatcher("ViewPlayerNumServlet").forward(request, response);
		
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
