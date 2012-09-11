/**
 * Copyright(C) 2012, liwei.
 *
 * 模块名称：	
 * 子模块名称：	
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-9-11		V1.0		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;

/**
 * 拆分Finalitem类
 *
 * 本类的简要描述：
 *
 * @author 李玮
 * @version 1.00  2012-9-11 新建类
 */

public class AddFinalItemServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(AddFinalItemServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public AddFinalItemServlet() {
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
		HttpSession session = request.getSession();
		int sportsId = 0;
		int countMatchtype = 0, countFinalItem = 0, countSplited = 0;
		boolean isDelFinalItem = false;
		SportsService ss = new SportsService();
		
		if(session.getAttribute("currSportsId") != null){
			sportsId = ((Integer)session.getAttribute("currSportsId")).intValue();
		}
		
		countMatchtype = ss.countMatchtype(sportsId);        //获取的Matchtype数量值
		countFinalItem = ss.countFinalItem(sportsId);        //获取的FinalItem数量
		
		if(countMatchtype == countFinalItem){
			//如果相等，说明已经拆分好
			log.debug("一切正常");
		}else if(countFinalItem == 0){
			//countFinalItem为0，说明从未被拆分过
			countSplited = ss.splitFinalitem(sportsId);
			if(countSplited == countMatchtype){
				log.debug("拆分成功！");
			}else{
				log.error("拆分出现异常，拆分出了" + countSplited + "条，应拆分出" + countMatchtype +"条");
				request.setAttribute("msg", "数据处理异常，FinalItem拆分出了" + countSplited + "条，应拆分出" + countMatchtype +"条！");
			}
		}else if(countFinalItem != 0 && countMatchtype != countFinalItem){
			//说明应拆分的数量与已拆分的数量不等，拆分存在问题，需删除原数据，重新拆分
			isDelFinalItem = ss.removeFinalItem(sportsId);  //先删
			countSplited = ss.splitFinalitem(sportsId);     //后分
			if(countSplited == countMatchtype){
				log.debug("先删后拆操作成功！");
				request.setAttribute("msg", "数据处理异常，请检查后续操作数据是否正常！");
			}else{
				log.error("先删后拆出现异常，拆分出了" + countSplited + "条，应拆分出" + countMatchtype +"条");
				request.setAttribute("msg", "FinalItem先删后拆出现异常，拆分出了" + countSplited + "条，应拆分出" + countMatchtype +"条！");
			}
		}
		request.getRequestDispatcher("ViewScheduleServlet").forward(request, response);
		
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
