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
 * 添加更改大会主席团
 * @author Administrator
 *
 */
public class AddOfficialSetServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddOfficialSetServlet() {
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
		int sportsid=Integer.parseInt(session.getAttribute("currSportsId").toString());
		String presidium=request.getParameter("presidium").trim();
        String org_committee_1=request.getParameter("org_committee_1").trim();
        String org_committee_2=request.getParameter("org_committee_2").trim();
        String org_committee_3=request.getParameter("org_committee_3").trim();
        String secretariat_1=request.getParameter("secretariat_1").trim();
        String secretariat_2=request.getParameter("secretariat_2").trim();
        String secretariat_3=request.getParameter("secretariat_3").trim();
        String secretariat_4=request.getParameter("secretariat_4").trim();
        String secretariat_5=request.getParameter("secretariat_5").trim();
        String secretariat_6=request.getParameter("secretariat_6").trim();
        String secretariat_7=request.getParameter("secretariat_7").trim();
        String arbitration=request.getParameter("arbitration").trim();
        
        boolean flag=false;
        //System.out.println(presidium);
        //System.out.println(org_committee_1);
        //System.out.println(org_committee_2);
        //System.out.println(org_committee_3);
        //System.out.println(secretariat_1);
        //System.out.println(secretariat_2);
       // System.out.println(secretariat_3);
       // System.out.println(secretariat_4);
       // System.out.println(secretariat_5);
       // System.out.println(secretariat_6);
        //System.out.println(secretariat_7);
        //System.out.println(arbitration);
        OfficialSetService officialsetService=new OfficialSetService();
        UpdateOfficialSetService updateofficialsetService=new UpdateOfficialSetService();
        flag=officialsetService.spoid(sportsid);
        if(flag){
        	
        		if(officialsetService.addPresidium(sportsid,presidium, org_committee_1, 
                		org_committee_2, org_committee_3, secretariat_1, secretariat_2,
                		secretariat_3, secretariat_4, secretariat_5, secretariat_6, 
                		secretariat_7, arbitration)){
        		
    			out.println("success");
    		}			
    		else{
    			out.println("error");
    		}
        }else{
        	
        	if(updateofficialsetService.updatePresidiumBySportsid(sportsid, presidium, org_committee_1,
        			org_committee_2, org_committee_3, secretariat_1, secretariat_2, secretariat_3, 
        			secretariat_4, secretariat_5, secretariat_6, secretariat_7, arbitration)){
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
