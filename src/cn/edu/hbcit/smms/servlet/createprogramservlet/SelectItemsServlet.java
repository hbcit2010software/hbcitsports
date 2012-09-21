package cn.edu.hbcit.smms.servlet.createprogramservlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.dao.createprogramdao.SelectItems;
import cn.edu.hbcit.smms.pojo.FinalitemGroup;
/**
 * 
 * @author lenovo
 *
 */
public class SelectItemsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectItemsServlet() {
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

		HttpSession session = request.getSession();
		SelectItems si=new SelectItems();
		ArrayList ItemsList1=new ArrayList();
		ArrayList ItemsList2=new ArrayList();
		ArrayList ItemsList3=new ArrayList();
		int sportsid = Integer.parseInt(session.getAttribute("currSportsId").toString()); 
		//int sportsid=1;
	    String itemtype="1";
	    String itemtype1="2";
	    String itemtype2="3";
	    ItemsList1 = si.selectItemsById(sportsid, itemtype);
	    ItemsList2 = si.selectItemsById(sportsid, itemtype1);
	    ItemsList3 = si.selectItemsById(sportsid, itemtype2);
	   // System.out.println("hello"+ItemsList1.size());
	    session.setAttribute("ItemsList", ItemsList1);
	    session.setAttribute("ItemsList1", ItemsList2);
	    session.setAttribute("ItemsList2", ItemsList3);
	   //request.getRequestDispatcher("View.jsp").forward(request, response);
	   response.sendRedirect("../createprogram.jsp");
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
