package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.dao.gamesetdao.OfficialSetDAO;
import cn.edu.hbcit.smms.services.gamesetservices.OfficialSetService;
import cn.edu.hbcit.smms.services.gamesetservices.UpdateOfficialSetService;

/**
 * 添加更改田赛裁判
 * @author Administrator
 *
 */
public class AddFieldjudgeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddFieldjudgeServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String fieldjudge=request.getParameter("fieldjudge").trim();
        String fieldjudge_1=request.getParameter("fieldjudge_1").trim();
        String fieldjudge_2=request.getParameter("fieldjudge_2").trim();
        String fieldjudge_3=request.getParameter("fieldjudge_3").trim();
        String fieldjudge_4=request.getParameter("fieldjudge_4").trim();
        String fieldjudge_5=request.getParameter("fieldjudge_5").trim();
        String fieldjudge_6=request.getParameter("fieldjudge_6").trim();
        int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
        boolean flag=false;
       // System.out.println(fieldjudge);
       // System.out.println(fieldjudge_1);
       // System.out.println(fieldjudge_2);
       // System.out.println(fieldjudge_3);
       // System.out.println(fieldjudge_4);
       // System.out.println(fieldjudge_5);
        //System.out.println(fieldjudge_6);
        OfficialSetService officialsetService=new OfficialSetService();
        UpdateOfficialSetService updateofficialsetService=new UpdateOfficialSetService();
        flag=officialsetService.spoid(sportsid);
        if(flag){
        	
    		if(officialsetService.addFieldjudge(sportsid, fieldjudge, fieldjudge_1,
    				fieldjudge_2, fieldjudge_3, fieldjudge_4, fieldjudge_5, fieldjudge_6)){
    		
			out.println("success");
		}			
		else{
			out.println("error");
		}
    }else{
    	
    	if(updateofficialsetService.updateFieldjudgeBySportsid(sportsid, fieldjudge, fieldjudge_1,
    			fieldjudge_2, fieldjudge_3, fieldjudge_4, fieldjudge_5, fieldjudge_6)){
    				out.println("success");
        		}			
        		else{
        			out.println("error");
        		}
    
    }
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
