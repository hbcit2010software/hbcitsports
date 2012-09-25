package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gamesetservices.UpdateOfficialSetService;

public class UpdateTrackJudgeServlet extends HttpServlet {

	/**获取前台传来的径赛裁判修改径赛裁判信息
	 * Constructor of the object.
	 */
	public UpdateTrackJudgeServlet() {
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UpdateOfficialSetService updateofficialsetService=new UpdateOfficialSetService();
		String chiefjudge_1=request.getParameter("chiefjudge_1").trim();
        String chiefjudge_2=request.getParameter("chiefjudge_2").trim();
        String trackjudge=request.getParameter("trackjudge").trim();
        String trackjudge_rollcall_1=request.getParameter("trackjudge_rollcall_1").trim();
        String trackjudge_rollcall_2=request.getParameter("trackjudge_rollcall_2").trim();
        String trackjudge_rollcall_3=request.getParameter("trackjudge_rollcall_3").trim();
        String startingpoint_1=request.getParameter("startingpoint_1").trim();
        String startingpoint_2=request.getParameter("startingpoint_2").trim();
        String startingpoint_3=request.getParameter("startingpoint_3").trim();
        String timejudge_1=request.getParameter("timejudge_1").trim();
        String timejudge_2=request.getParameter("timejudge_2").trim();
        String timejudge_3=request.getParameter("timejudge_3").trim();
        String endpoint_1=request.getParameter("endpoint_1").trim();
        String endpoint_2=request.getParameter("endpoint_2").trim();
        String endpoint_3=request.getParameter("endpoint_3").trim();
        String endpoint_4=request.getParameter("endpoint_4").trim();
        String endpoint_5=request.getParameter("endpoint_5").trim();
        //System.out.println(endpoint_4);
        int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
        if (updateofficialsetService.updateTrackjudgeBySportsid(sportsid, chiefjudge_1, 
        		chiefjudge_2, trackjudge, trackjudge_rollcall_1, trackjudge_rollcall_2, 
        		trackjudge_rollcall_3, startingpoint_1, startingpoint_2, startingpoint_3,
        		timejudge_1, timejudge_2, timejudge_3, endpoint_1, endpoint_2, endpoint_3,
        		endpoint_4, endpoint_5))
			out.println("success");
		else
			out.println("error");

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
