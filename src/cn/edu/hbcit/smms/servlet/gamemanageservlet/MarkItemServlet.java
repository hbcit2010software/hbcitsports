package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.edu.hbcit.smms.pojo.ManageItemPJ;
import cn.edu.hbcit.smms.services.gamemanageservices.MarkManangerBeanServices;

public class MarkItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MarkItemServlet() {
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
		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
        ArrayList List = new ArrayList();
        MarkManangerBeanServices mark = new MarkManangerBeanServices();
       
	    List= mark.getItem();
	    
		StringBuffer sb = new StringBuffer();

		
		sb.append("{");
		sb.append("\"contents\":[");

		for (int i = 0; i < List.size(); i++) {
		ManageItemPJ n = (ManageItemPJ)List.get(i);		
			if (i > 0) {
				sb.append(",");
			}
			
			sb.append("{");
			sb.append("\"Id\":\"" + n.getId() + "\",");
			sb.append("\"ItemName\":\"" + n.getItemname() + "\"");
			sb.append("}");
		}
		sb.append("]");
		sb.append("}");
		out.println(sb);		
		out.flush();
		out.close();
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
		PrintWriter out = response.getWriter();
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
