/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   项目设置
*
* 备注：
*
* 修改历史：
* 2012-7-19	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;
import cn.edu.hbcit.smms.util.UtilTools;

/**
 * 项目设置类
 * 简要说明:
 * 根据选择，插入t_group2item表。
 * 先将原有相关数据全部删除，然后插入数据。
 * @author 李玮
 * @version 1.00  2012-7-19上午12:56:27	新建
 */

public class AddGroupToItemServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(AddGroupToItemServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public AddGroupToItemServlet() {
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

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		boolean flag_del = false, flag_add = false, flag_update = false;;
		int sportsId = 0;
		int count_t_group2item = 0; //获取t_group2item中，本届运动会的项目数量
		String[] itemInfo;
//		String[] currentItem = {""};
		String insertSQL = "";
		SportsService ss = new SportsService();
		UtilTools ut = new UtilTools();
		
		itemInfo = request.getParameterValues("iteminfo");
		
//		for(int i=0;i<itemInfo.length;i++){
//			log.debug(itemInfo[i]);
//		}
		
//		if(itemInfo != null && itemInfo.length!=0){
//			//将前台传来的所有信息中的“未选择”（"0"）过滤掉
//			currentItem = ut.removeElementsFromStringArray(itemInfo, "0");
//		}
				
		if(session.getAttribute("currSportsId") != null){
			sportsId = ((Integer)session.getAttribute("currSportsId")).intValue();
		}
//		insertSQL = ss.getSqlOfInsertT_group2item(currentItem); //构建插入SQL语句
//		count_t_group2item = ss.countGroupToItem(sportsId);		//获取获取t_group2item中，本届运动会的项目数量。
//		
//		flag_del = ss.removeGroupToItem(sportsId);  //先将相关记录删除
//		if(flag_del){
//			flag_add = ss.addGroupToItem(insertSQL);	//当上一步删除成功后，再重新插入前台所选择的记录
//		}else if(flag_del==false && count_t_group2item==0){
//			//flag_del==false可能是由于本届运动会的项目数为0.本if是为判断flag_del==false的原因
//			//若flag_del==false && count_t_group2item==0则说明本届运动会的项目数为0
//			//若flag_del==false 但  count_t_group2item!=0则说明删除时出现异常
//			flag_add = ss.addGroupToItem(insertSQL);	//当上一步删除成功后，再重新插入前台所选择的记录
//		}
//		
//		
//		if((flag_del && flag_add) || (flag_del==false && flag_add==true)){
//			request.setAttribute("msg", "项目设置成功！");
//		}else if(flag_del==false && flag_add==false){
//			request.setAttribute("msg", "项目初始化清除失败：您可能未选择任何项目！");
//		}else if(flag_del==true && flag_add==false){
//			request.setAttribute("msg", "您可能未选择任何项目！");
//		}
//		request.getRequestDispatcher("GetItemInfoServlet").forward(request, response);
		
		//SELECT COUNT(*) FROM t_group2item WHERE t_group2item.gp2spid IN (SELECT t_group2sports.id FROM t_group2sports WHERE t_group2sports.sportsid=2)
		//修改原算法，以上思路作废
		count_t_group2item = ss.countGroupToItem(sportsId);		//获取获取t_group2item中，本届运动会的项目数量。
		log.debug("前台传来数量：" + itemInfo.length + "，本届数量：" + count_t_group2item);
		if(count_t_group2item == itemInfo.length){
			//若两值相等，说明已经存在数据，update即可
			flag_update = ss.updateGroup2Item(itemInfo);
		}else if(count_t_group2item == 0){
			//值为0，说明尚未插入数据
			insertSQL = ss.getSqlOfInsertT_group2item(itemInfo); //构建插入SQL语句
			flag_add = ss.addGroupToItem(insertSQL);	//插入前台所选择的记录
		}else if(count_t_group2item != itemInfo.length){
			//count_t_group2item不为0，且两值不等，说明原始数据存在异常，需清空后再重新插入
			insertSQL = ss.getSqlOfInsertT_group2item(itemInfo); //构建插入SQL语句
			flag_del = ss.removeGroupToItem(sportsId);  //先将相关记录删除
			flag_add = ss.addGroupToItem(insertSQL);	//插入前台所选择的记录
		}
		if(flag_update){
			request.setAttribute("msg", "项目设置成功！");
		}else if(flag_update == false && flag_add == true && flag_del==false){
			request.setAttribute("msg", "项目初始化并设置成功！");
		}else if(flag_del==true && flag_add==true){
			request.setAttribute("msg", "项目信息出现异常，请检查后续操作数据！");
		}else if(flag_update== false && flag_del==false && flag_add==false){
			request.setAttribute("msg", "项目设置失败，请检查操作！");
		}
		request.getRequestDispatcher("GetItemInfoServlet").forward(request, response);
		
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
