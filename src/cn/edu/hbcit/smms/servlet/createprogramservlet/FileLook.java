package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.createprogramservices.DataManagerServices;

/**
 * 田赛分组情况查看servlet
 *
 *简要说明
 *
 *详细解释。
 * @author 韩鑫鹏
 * @version 1.00  2011/12/07 新規作成<br>
 */
public class FileLook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FileLook() {
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

		HttpSession session = request.getSession();
		//int finalId = 2;
		int finalId = Integer.parseInt(request.getParameter("id"));
		DataManagerServices cpgg = new DataManagerServices(); //数据库操作类对象cpgg
		int count = 0;
		ArrayList players = new ArrayList();
		
		players = cpgg.slectFilePs(finalId);
		System.out.println("田赛运动员" + players );
		ArrayList allPlayers = new ArrayList();
		int flag = players.size()/8+1;
		if ( players != null){
			
			for (int i = 0; i < flag; i++){
				ArrayList temp = new ArrayList();
				
				while(true){
    				if(count == players.size()){
    					break;
    				}
    				temp.add(players.get(count));
    				System.out.println();
    				count++;
    				if((count != 0) && ((count + 1) % 8 ==0)){
    					break;
    				}
    				
    			}
    			if(temp.size() != 0){
    				allPlayers.add(temp);
    			}
			}
		}
		String[] qq = new String[flag];
		System.out.println("田赛的行数" + qq.length );
		for (int i = 0; i < allPlayers.size(); i++){
			System.out.println("第"+ i +"行运动员" + allPlayers.get(i) );
		}
		System.out.println("田赛的行数" + qq.length );
		session.setAttribute("filelook", allPlayers);
		response.sendRedirect("../fildlook.jsp");
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

		this.doPost(request, response);
		
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
