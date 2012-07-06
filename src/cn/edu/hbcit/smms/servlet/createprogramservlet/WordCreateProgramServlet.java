package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import cn.edu.hbcit.smms.pojo.T_finalitemPojo;
import cn.edu.hbcit.smms.services.createprogramservices.CreateProgramGameGroupingServices;
import cn.edu.hbcit.smms.services.createprogramservices.GameGroupingServices;
import cn.edu.hbcit.smms.services.createprogramservices.SetWordDAOServices;

/**
 * 
 * @author 韩鑫鹏
 *
 */
public class WordCreateProgramServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WordCreateProgramServlet() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		//int currSportsId =Integer.parseInt(session.getAttribute("currSportsId").toString()) ;   //运动会ID
		String modelName = "program";   
		int currSportsId = 1;//模块名称：秩序册
		String fileName = "../" + currSportsId + "-" + modelName + "-" + "02" + ".doc";
		
		GameGroupingServices grp = new GameGroupingServices();
		SetWordDAOServices swd = new SetWordDAOServices();
		CreateProgramGameGroupingServices cpgg = new CreateProgramGameGroupingServices();
		HashMap department = new HashMap();
		department = cpgg.selectDepartmentBySid(currSportsId);
		HashMap allGirlPlayers = new HashMap();
		HashMap allBoyPlayers = new HashMap();
		allGirlPlayers = cpgg.selectFlaGirl(currSportsId);
		allBoyPlayers = cpgg.selectFlaBoy(currSportsId);
		HashMap players = cpgg.selectPlayersBySid(currSportsId);
		Document docu = new Document(PageSize.A4);   
		swd.setWordPath(docu, fileName);    
		ArrayList allBoyFnId = new ArrayList();
		ArrayList allGirlFnId = new ArrayList();
		Iterator allBoy = allBoyPlayers.keySet().iterator();
        while (allBoy.hasNext()){
        	allBoyFnId.add(allBoy.next());
		}
        Iterator allGirl = allGirlPlayers.keySet().iterator();
        while (allGirl.hasNext()){
        	allGirlFnId.add(allGirl.next());
		}
        
 /*****************生成男子项目的所有表格****************************/
        int count = 1;
        String group = "男子组";
        swd.setHead(docu, group);

		for (int i = 0; i < allBoyFnId.size(); i++){
			
			
			int id = Integer.parseInt(allBoyFnId.get(i).toString());
			String itemType = cpgg.selectItemTypeByFinId(id);
			String groupName = cpgg.selectGrNameByFid(id);
			int testGname = 0;
			if (groupName.indexOf("教工") >= 0){
				testGname = 1;
			}
			if (groupName.indexOf("学生男女") >= 0){
				
				testGname = 2;
			}
			String gname = "";
			switch (testGname){
		    case 0: gname = "男子组";break;
		    case 1: gname = groupName;break;
		    case 2: gname = "学生男女混合";break;
		}
			/********田赛******/
			if (itemType.trim().equals("2")){
				
				ArrayList pnums = cpgg.selectFilePnumByFid(id, players);
				T_finalitemPojo finalInfo = (T_finalitemPojo)allBoyPlayers.get(allBoyFnId.get(i));
				
				String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
				+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
				swd.addFiledTable(docu, title, pnums, 8);
				count++;
			}
			/********径赛******/
			if (itemType.trim().equals("1")){
				
				T_finalitemPojo finalInfo = (T_finalitemPojo)allBoyPlayers.get(allBoyFnId.get(i));
				String fname = finalInfo.getFinalitemname();
				if (fname.indexOf("1500") >= 0 || fname.indexOf("5000") >= 0 || fname.indexOf("马拉松") >= 0){ //判断是否为长跑
					if (fname.indexOf("1500") >= 0){
						if (finalInfo.getGroupnum() > 0){
							ArrayList pnums = cpgg.select1500ByFinId(id, players);
							int[] groupInfo = grp.set1500GpNum(finalInfo.getGroupnum(), pnums.size());
							String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
							               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.add1500Table(docu, title, pnums, 9, groupInfo);
							count++;
						}else{
							ArrayList pnums = cpgg.selectFilePnumByFid(id, players);
							String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
							+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.addFiledTable(docu, title, pnums, 8);
							count++;
						}
					}else{
						ArrayList pnums = cpgg.selectFilePnumByFid(id, players);
						String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
						+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
						swd.addFiledTable(docu, title, pnums, 8);
						count++;
					}
					
				}else{
					ArrayList pnums = cpgg.selectMatchByFinId(id, players);
					int[] groupInfo = grp.setGroupSporterNumber(pnums.size());
					String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
					               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add100Table(docu, title, pnums, 9, groupInfo);
					count++;
				}
				
			}
			/********接力赛******/
			if (itemType.trim().equals("3")){
				T_finalitemPojo finalInfo = (T_finalitemPojo)allBoyPlayers.get(allBoyFnId.get(i));
				Integer gNum = new Integer(finalInfo.getGroupnum());
				if (gNum == null){
					ArrayList depName = new ArrayList();
					depName = cpgg.selectMatchByFinId(id, department);
					String title = count+"、" + gname + finalInfo.getFinalitemname() + "，"
		               + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.addFiledTable(docu, title, depName, 6);
					count++;
				}else{
					ArrayList depName = new ArrayList();
					int[] groupNum = grp.setGroupSporterNumber(depName.size());
					depName = cpgg.selectMatchByFinId(id, department);
					String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + depName.size() +"队），"
		               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add1500Table(docu, title, depName, 6, groupNum);
					count++;
				}
				
			}
		}
 /*****************生成女子项目的所有表格****************************/
		swd.setHead(docu, "             ");
		int count1 = 1;
        String group2 = "女子组";
        swd.setHead(docu, group2);

		for (int i = 0; i < allGirlFnId.size(); i++){
			
			
			int id = Integer.parseInt(allGirlFnId.get(i).toString());
			String itemType = cpgg.selectItemTypeByFinId(id);
			String groupName = cpgg.selectGrNameByFid(id);
			int testGname = 0;
			if (groupName.indexOf("教工") >= 0){
				testGname = 1;
			}
			String gname = "";
			switch (testGname){
		    case 0: gname = "女子组";break;
		    case 1: gname = groupName;break;
		}
			/********田赛******/
			if (itemType.trim().equals("2")){
				
				ArrayList pnums = cpgg.selectFilePnumByFid(id, players);
				T_finalitemPojo finalInfo = (T_finalitemPojo)allGirlPlayers.get(allGirlFnId.get(i));
				
				String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
				+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
				swd.addFiledTable(docu, title, pnums, 8);
			}
			/********径赛******/
			if (itemType.trim().equals("1")){
				
				T_finalitemPojo finalInfo = (T_finalitemPojo)allGirlPlayers.get(allGirlFnId.get(i));
				String fname = finalInfo.getFinalitemname();
				if (fname.indexOf("1500") >= 0 || fname.indexOf("5000") >= 0 || fname.indexOf("马拉松") >= 0){ //判断是否为长跑
					if (fname.indexOf("1500") >= 0){
						if (finalInfo.getGroupnum() > 0){
							ArrayList pnums = cpgg.select1500ByFinId(id, players);
							int[] groupInfo = grp.set1500GpNum(finalInfo.getGroupnum(), pnums.size());
							String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
							               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.add1500Table(docu, title, pnums, 9, groupInfo);
						}else{
							ArrayList pnums = cpgg.selectFilePnumByFid(id, players);
							String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
							+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.addFiledTable(docu, title, pnums, 8);
							
						}
					}else{
						ArrayList pnums = cpgg.selectFilePnumByFid(id, players);
						String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
						+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
						swd.addFiledTable(docu, title, pnums, 8);
					}
					
				}else{
					ArrayList pnums = cpgg.selectMatchByFinId(id, players);
					int[] groupInfo = grp.setGroupSporterNumber(pnums.size());
					String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
					               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add100Table(docu, title, pnums, 9, groupInfo);
				}
				
			}
			/********接力赛******/
			if (itemType.trim().equals("3")){
				T_finalitemPojo finalInfo = (T_finalitemPojo)allGirlPlayers.get(allGirlFnId.get(i));
				Integer gNum = new Integer(finalInfo.getGroupnum());
				if (gNum == null){
					ArrayList depName = new ArrayList();
					depName = cpgg.selectMatchByFinId(id, department);
					String title = count1+"、" + gname + finalInfo.getFinalitemname() + "，"
		               + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.addFiledTable(docu, title, depName, 6);
				}else{
					ArrayList depName = new ArrayList();
					int[] groupNum = grp.setGroupSporterNumber(depName.size());
					depName = cpgg.selectMatchByFinId(id, department);
					String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + depName.size() +"队），"
		               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add1500Table(docu, title, depName, 6, groupNum);
				}
				
			}
			count1++;
		}
         swd.closeDocument(docu);

         response.sendRedirect("../group_success.jsp");
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
