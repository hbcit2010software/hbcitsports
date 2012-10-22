package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamemanageservices.AddScoreServices;
import cn.edu.hbcit.smms.util.UtilTools;

public class AddScoreServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = Logger.getLogger(AddScoreServlet.class.getName());
	UtilTools ctc = new UtilTools();
	AddScoreServices ass = new AddScoreServices();
	/**
	 * Constructor of the object.
	 */
	public AddScoreServlet() {
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
		String action = request.getParameter("action");
		if( action.equals("addscore")){
			log.debug("addscore="+action);
			this.doPost(request, response);
		}
	}

	/**
	 * 提交成绩
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String playernum = ctc.toUTF8( request.getParameter("playernum") );
		String score = request.getParameter("score");
		String group = ctc.toUTF8( request.getParameter("group") );
		String item = ctc.toUTF8(request.getParameter("item"));;
		
		log.debug(playernum+","+score+","+item);
		
		
		boolean flag = false;
		String str = "提交失败！";
		flag = ass.isAddScore(playernum, score, item , group);			//成绩添加是否成功
		
		String finalitemType = ass.getFinalitemType(item);
		int num = 0 ;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>finalitemType="+finalitemType);
		if( !"1".equals(finalitemType)){
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>finalitemType="+finalitemType);
			num = ass.getIntegral(item, group);		//积分添加是否成功
		}
		
		
		if( flag ){
			str = "提交成功！";
		}
		
		log.debug("doPost="+str);
		log.debug("doPost---ass="+num);
		out.println(str);
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
