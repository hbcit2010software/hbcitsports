/**
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     赛前设置
* 子模块名称：   规则设置
*
* 备注：
*
* 修改历史：
* 2012-9-23	0.1		李玮		新建
*/
package cn.edu.hbcit.smms.servlet.gamesetservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.services.gamesetservices.SportsService;

public class GetRuleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetRuleServlet() {
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

	/*
触发器1：

DELIMITER $$

CREATE
    TRIGGER `AddT_rule` AFTER INSERT ON `t_sports` 
    FOR EACH ROW BEGIN
    INSERT INTO t_rule (sportsid,POSITION,mark,recordmark_low,recordmark_high,perman,perdepartment) VALUES (new.id,8,'9,7,6,5,4,3,2,1',9,18,2,6);
    END$$

DELIMITER ;

触发器2：
DELIMITER $$

CREATE

    TRIGGER `smms`.`DelT_rule` AFTER DELETE
    ON `smms`.`t_sports`
    FOR EACH ROW BEGIN
    DELETE FROM t_rule WHERE sportsid=old.id;
    END$$

DELIMITER ;

	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		SportsService ss = new SportsService();
		ArrayList rule = new ArrayList();
		HttpSession session = request.getSession();
		int sportsId = 0;
		if(session.getAttribute("currSportsId") != null){
			sportsId = ((Integer)session.getAttribute("currSportsId")).intValue();
		}
		
		rule = ss.selectRule(sportsId);
		request.setAttribute("rule", rule);
		request.setAttribute("isAlreadyRegist", ss.isAlreadyRegist(sportsId));//是否已经有单位报名
		request.getRequestDispatcher("/set_rule.jsp").forward(request, response);
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
