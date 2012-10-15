package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.pojo.QueryMarkPoJo;
import cn.edu.hbcit.smms.services.gamemanageservices.QueryMarkServices;

public class QueryMarkServlet extends HttpServlet {
	
	/**
	 * Constructor of the object.
	 */
	public QueryMarkServlet() {
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
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		ArrayList list3 = new ArrayList();
		ArrayList list4 = new ArrayList();
		ArrayList list5 = new ArrayList();
		QueryMarkServices qms = new QueryMarkServices();
		List<QueryMarkPoJo> depNameList = qms.getDepName();
		List<QueryMarkPoJo> studentsMarkList = qms.getStudentsMark(depNameList);
		List<QueryMarkPoJo> teacherMarkList = qms.getTeacherMark(depNameList);
		List<QueryMarkPoJo> studentsFinalMarkList = qms.getStudentsFinalMark(depNameList);
		List<QueryMarkPoJo> teacherFinalMarkList = qms.getTeacherFinalMark(depNameList);
		System.out.println("QueryMarkServlet:depNameList--studentsMarkList--teacherMarkList--teacherFinalMarkList"+depNameList.size()+teacherMarkList.size()+studentsFinalMarkList.size()+teacherFinalMarkList.size());
		list1.add(depNameList);
		list2.add(studentsMarkList);
		list3.add(teacherMarkList);
		list4.add(studentsFinalMarkList);
		list5.add(teacherFinalMarkList);
		session.setAttribute("list1", list1);
		session.setAttribute("list2", list2);
		session.setAttribute("list3", list3);
		session.setAttribute("list4", list4);
		session.setAttribute("list5", list5);
		System.out.println(studentsFinalMarkList.size());
		response.sendRedirect("../mark.jsp");
		
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
