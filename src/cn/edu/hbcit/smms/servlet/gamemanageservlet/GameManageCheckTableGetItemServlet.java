/*
* Copyright(C) 2012, XXXXXXXX.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2004/12/12		0.1		张 三		新建
* 2005/02/05		0.1		李 四		Bug修正
*/
package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hbcit.smms.pojo.GameManageCheckTablePojo;
import cn.edu.hbcit.smms.pojo.GameManagePoJo;
import cn.edu.hbcit.smms.services.gamemanageservices.GameManageCheckTableServices;
import cn.edu.hbcit.smms.services.gamemanageservices.GameManageServices;
import cn.edu.hbcit.smms.util.UtilTools;

/**
 * XXXXXXXXXXXXXXXXXXXXXXXX类
 *
 *简要说明
 *
 *详细解释。
 * @author 张三
 * @version 1.00  2011/12/07 新規作成<br>
 */

public class GameManageCheckTableGetItemServlet extends HttpServlet {
	private UtilTools tools;
	/**
	 * Constructor of the object.
	 */
	public GameManageCheckTableGetItemServlet() {
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

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if( "checkDate".equals(action)){
			checkData(request, response);
		}else{
			StringBuffer buffer = new StringBuffer();
			String groupid = request.getParameter("groupid");
			ArrayList itemList = new ArrayList();
			GameManageCheckTableServices gm = new GameManageCheckTableServices();
			System.out.println("groupid="+groupid);
			if(!groupid.equals("")){
				    itemList = gm.getItemList(Integer.parseInt(groupid));
					buffer.append("{");
					buffer.append("\"contents\":[");

					for (int i = 0; i < itemList.size(); i++) {
						GameManageCheckTablePojo pj = (GameManageCheckTablePojo)itemList.get(i);
						if (i > 0) {
							buffer.append(",");
						}

						buffer.append("{");
						buffer.append("\"itemtype\":\"" + pj.getItemType() + "\",");
						buffer.append("\"itemname\":\"" + pj.getItemName() + "\"");// 项目内容
						buffer.append("}");
					}
					buffer.append("]");
					buffer.append("}");
					out.println(buffer);
					out.flush();
					out.close();	
			}}
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
		

		
	}
	public void checkData(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		boolean flag = false;
		int a = 0;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("---------------------------+finalitemname+finalitemtype");
		String finalitemname = new String(request.getParameter("itemname").getBytes("ISO-8859-1"),"utf-8");
		
		String finalitemtype = new String(request.getParameter("itemtype").getBytes("ISO-8859-1"),"utf-8");
		System.out.println("---------------------------"+finalitemname+finalitemtype);
		GameManageCheckTableServices gm = new GameManageCheckTableServices();
		if(gm.getdate(finalitemname, finalitemtype)){
			
			a = 1;
		}else{
			
			a = 0;
		}
		
		
		out.println(a);
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
