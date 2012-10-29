package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.gamemanagedao.QueryMark;
import cn.edu.hbcit.smms.pojo.MarkPojo;
import cn.edu.hbcit.smms.pojo.QueryMarkPoJo;
import cn.edu.hbcit.smms.services.gamemanageservices.QueryMarkServices;

public class PrintAllMarkSerlvet extends HttpServlet {

	protected final Logger log = Logger.getLogger(PrintAllMarkSerlvet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public PrintAllMarkSerlvet() {
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


		System.out.println("printServlet============");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int sportsId = Integer.parseInt(session.getAttribute("currSportsId").toString()); 
		log.debug("当前运动会id："+sportsId);
		QueryMarkServices qms = new QueryMarkServices();
		String filePath1 = request.getSession().getServletContext().getRealPath("/")+"excel/";
		String sportsName = qms.getSportsName();
		String filePath = filePath1 + "河北工院"+sportsName+"成绩单.xls";
		ArrayList depNameList = new ArrayList();
		depNameList = qms.selectDep(sportsId);
		ArrayList boyItemList = new ArrayList();
		boyItemList = qms.selectItem(sportsId, 1);
		ArrayList girlItemList = new ArrayList();
		girlItemList = qms.selectItem(sportsId, 0);
		String boyGroup = "男子组";
		String girlGroup = "女子组";
		HashMap boyMarkMap = null;
		boyMarkMap = qms.selectMarkMap(sportsId, 1);
		boyMarkMap = qms.selectMarkMap(sportsId, 1, boyMarkMap);
		log.debug("男子积分map："+boyMarkMap);
		HashMap girlMarkMap = null;
		girlMarkMap = qms.selectMarkMap(sportsId, 0);
		girlMarkMap = qms.selectMarkMap(sportsId, 0, girlMarkMap);
		log.debug("女子积分map："+girlMarkMap);
		try{
			qms.generateExcel2003(filePath, depNameList, boyItemList, sportsName, boyGroup, boyMarkMap, girlItemList, girlGroup, girlMarkMap);

		}catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"contents\":[");
		buffer.append("{");
		buffer.append("\"file\":\"" + URLEncoder.encode(filePath1,"UTF-8") + "\",");
	    buffer.append("\"fileName\":\"" + ("河北工院"+sportsName+"成绩单.xls" )+ "\"");
	    buffer.append("}");
	    buffer.append("]");
		buffer.append("}");
		out.println(buffer);
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
