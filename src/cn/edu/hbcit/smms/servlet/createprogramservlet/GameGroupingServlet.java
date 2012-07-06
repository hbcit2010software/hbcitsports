/*
* Copyright(C) 2004, XXXXXXXX.
*
* 模块名称：     生成秩序册
* 子模块名称：  赛事分组
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2012/6/6	0.1		韩鑫鹏		新建
*/

package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mysql.jdbc.log.Log;

import cn.edu.hbcit.smms.dao.createprogramdao.GameGrouping;
import cn.edu.hbcit.smms.dao.databasedao.CreateProgramGameGrouping;
import cn.edu.hbcit.smms.dao.logindao.LoginDAO;
import cn.edu.hbcit.smms.pojo.Group2itemPojo;
import cn.edu.hbcit.smms.services.createprogramservices.CreateProgramGameGroupingServices;
import cn.edu.hbcit.smms.services.createprogramservices.GameGroupingServices;

public class GameGroupingServlet extends HttpServlet {
	protected final Logger log = Logger.getLogger(LoginDAO.class.getName());
	
	public GameGroupingServlet() {
		super();
	}

	
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
	 * @throws IOExce+ption if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		int sportsid = 1; //运动会id
		GameGroupingServices gameGrouping = new GameGroupingServices();
		CreateProgramGameGroupingServices cpgg = new CreateProgramGameGroupingServices(); 
		int departmentCount = 0;
		departmentCount = cpgg.selectDepartmentSum(sportsid); //部门总数
		int[] d2sid = null;
		if (new Integer(departmentCount) != null){
			d2sid = cpgg.selectSp2dpid(sportsid, departmentCount); //部门和运动会联系id
		}
		int perMan = cpgg.selectPerDepartment(sportsid); //每个项目各系的限报人数
		HashMap t_item = new HashMap(); // itemid(k) itemName(v)
		t_item = cpgg.selectT_item(sportsid);
		HashMap fidRg2sid2iid = new HashMap(); // 组别与运动会关系id_项目id（k）  与      最终项目表id（v）    的对应关系
	    fidRg2sid2iid = cpgg.selectFidRg2s2IiD(sportsid);
		log.debug("组别与运动会关系id_项目id（k）  与      最终项目表id（v）    的对应关系"+fidRg2sid2iid);
		
/*************************径赛分组********************************/
		
	    
		ArrayList allTrackItems = new ArrayList(); //该届运动会的所有能报的项目(径赛)的    组别与运动会联系表id 项目id
		allTrackItems = cpgg.selectGroupItem(sportsid, "1");
		log.debug("该届运动会的所有能报的项目(径赛)的    组别与运动会联系表id 项目id"+allTrackItems.size());
	    HashMap gRg2sHashMap = new HashMap(); // HashMap<组别id,组别与运动会联系表id>
	    gRg2sHashMap = cpgg.selectGroupItem(sportsid);
	    log.debug("<组别id,组别与运动会联系表id>"+gRg2sHashMap.size());
	    HashMap pRg2s_iHashMap = new HashMap(); //HashMap<运动员(以数字+运动员id的形式储存), 组别与运动会表的id和项目id的结合>
	    if (gRg2sHashMap != null){
	    	 pRg2s_iHashMap = cpgg.selectPlarerIdAndG2IId(sportsid, gRg2sHashMap);
	    }
	   
	    HashMap pRd2sHashMap = new HashMap(); // HashMap<运动员id,部门与运动会联系表id>
	    pRd2sHashMap = cpgg.selectPlayerIdD2SId(sportsid);
	    
	    //HashMap g2s_iRg2iHMap = new HashMap(); //  组别与项目联系表的id(v) 与     组别与运动会联系表的id与项目id联合起来(k)   的关系
	    //g2s_iRg2iHMap = cpgg.selectGp2itid(sportsid);
	    /****************************************************/
	    if (allTrackItems != null){
	    	for (int i= 0 ; i < allTrackItems.size(); i++){
		    	ArrayList eDepartmentPlayers = new ArrayList(); //单个项目各部门所有运动员id集合
		    	Group2itemPojo g2i = (Group2itemPojo)allTrackItems.get(i); //所有项目
		    	int gp2spid = g2i.getGp2spid();
		    	int itemid = g2i.getItemid();
		    	String gRi = gp2spid + ";" + itemid; //组别与运动会表的id和项目id的结合
		    	log.debug(i+"径赛组别与运动会表的id和项目id的结合"+gRi);
		    	Integer t_itemid = new Integer(itemid);
		    	log.debug(i+"径赛组别与运动会表的id和项目id的结合"+gRi);
		    	String t_name = t_item.get(t_itemid).toString();
		    	int flag1 = t_name.indexOf("1500");
		    	int flag2 = t_name.indexOf("5000");
		    	int flag3 = t_name.indexOf("马拉松");
		    	
		    	/*********获取finalitemid************/
		    	
	    	    int finalitemid = 0;
	    	    if(fidRg2sid2iid != null && gRi != null && gRi != ""){
	    	    	finalitemid = Integer.parseInt(fidRg2sid2iid.get(gRi).toString());
	    	    }
	    	    
	    	   
		    	if ( flag1 >= 0 && flag2 >= 0 && flag3 >= 0){  //判断是否为长跑
		    		Iterator ePlAll = pRg2s_iHashMap.keySet().iterator();
	    			while (ePlAll.hasNext()){
	    				String pid = ePlAll.next().toString();
	    				if (pRg2s_iHashMap.get(pid).toString().equals(gRi)){
	    					String[] temp = pid.split(";");
		    				String temp3 = temp[1];
		    				Integer playerid = new Integer(Integer.parseInt(temp3));
		    				eDepartmentPlayers.add(playerid);
	    				}	
	    			}
	    			eDepartmentPlayers = gameGrouping.arrayListRandom(eDepartmentPlayers);
		    		if ( flag1 >= 0 && flag2 < 0 && flag2 < 0 &&eDepartmentPlayers.size() >= 30){
		    			
		    			//if (eDepartmentPlayers.size() >= 30){
		    				String sql = "INSERT INTO t_match(finalitemid,teamnum,playerid) values";
		    				int[] group1500 = new int[2];
		    				int num = eDepartmentPlayers.size() / 2;
		    				if (eDepartmentPlayers.size() % 2 == 0){
		    					group1500[1] = num;
		    					group1500[2] = num;
		    				}else{
		    					group1500[1] = num;
		    					group1500[2] = num + 1;
		    				}
		    				int finalitemid1 = finalitemid;
			        		int teanum1 = 1;
			        		int teanum2 = 2;
		    				for (int q = 0; q < group1500[1]; q++){
				        		if (q > 0){ sql = sql + ","; }
				        		
				        		int playerid1 = Integer.parseInt(eDepartmentPlayers.get(q).toString());
				        		sql = sql + "(" + finalitemid1 + "," + teanum1+ "," + playerid1 + ")";
				        	}
		    				for (int ak = group1500[1]; ak < eDepartmentPlayers.size(); ak++){
				        		if (ak > 0){ sql = sql + ","; }
				        		
				        		int playerid1 = Integer.parseInt(eDepartmentPlayers.get(ak).toString());
				        		sql = sql + "(" + finalitemid1 + "," + teanum2+ "," + playerid1 + ")";
				        	}
		    				if (!sql.equals("INSERT INTO t_match(finalitemid,teamnum,playerid) values")){
		    					cpgg.addRecordBySql(sql);
		    				}
		    				cpgg.uodateGroupnumByFid(2, finalitemid1);
				        	
		    			}else{
		    				String sql = "INSERT INTO t_match(finalitemid,teamnum,playerid) values";
		    				int pSum = eDepartmentPlayers.size(); //单个项目运动员数目
		    				int finalitemid1 = finalitemid;
				        	for (int q = 0; q < pSum; q++){
				        		if (q > 0){ sql = sql + ","; }
				        		int playerid1 = Integer.parseInt(eDepartmentPlayers.get(q).toString());
				        		sql = sql + "(" + finalitemid1 + "," + 1 + ","+ playerid1 + ")";
				        	}
				        	if (!sql.equals("INSERT INTO t_match(finalitemid,teamnum,playerid) values")){
		    					cpgg.addRecordBySql(sql);
		    				}
				        	cpgg.uodateGroupnumByFid(1, finalitemid1);
		    			}
		    			
		    		//}	
//		    		else{
//		    			String sql = "INSERT INTO t_match(finalitemid,playerid) values";
//		    			
//		    			//finalitemid,teamnum,runway,playerid 顺序
//		    			int pSum = eDepartmentPlayers.size(); //单个项目运动员数目
//		    			int finalitemid1 = finalitemid;
//			        	for (int q = 0; q < pSum; q++){
//			        		if (q > 0){ sql = sql + ","; }
//			        		
//			        		int playerid1 = Integer.parseInt(eDepartmentPlayers.get(q).toString());
//			        		sql = sql + "(" + finalitemid1 + "," + playerid1 + ")";
//			        	}
//			        	if (!sql.equals("INSERT INTO t_match(finalitemid,playerid) values")){
//	    					cpgg.addRecordBySql(sql);
//	    				}
//		    		}
		    	}
		    	else{ //短跑
		    	    HashMap epRd2sHashMap = new HashMap(); // 单个项目HashMap<运动员id,部门与运动会联系表id>
		    	    ArrayList eItemPlayers = new ArrayList(); //单个项目的所有运动员按     计算机、环化、材料-计算机、环化、材料。。。 排列	
		    	    String sql = "INSERT INTO t_match(finalitemid,teamnum,runway,playerid) VALUES"; 
		    
		    	    /*****************epdHashMap添加值   单个项目HashMap<运动员id,部门与运动会联系表id>******************/
		        	Iterator epid = pRg2s_iHashMap.keySet().iterator();
		        	while (epid.hasNext()){
		        		String temp = epid.next().toString();
		        		if (pRg2s_iHashMap.get(temp).toString().equals(gRi)){
		    	    		
			        		String[] temp2 = temp.split(";");
			        		Integer pNum = new Integer(Integer.parseInt(temp2[1])); //运动员id
			        		Integer temp3 = new Integer(Integer.parseInt(pRd2sHashMap.get(pNum).toString()));
			        		epRd2sHashMap.put(pNum, temp3);
		        		}
		        	}
		    	
		        	/***************** eDepartmentPlayers  添加值   集合形式添加 ******************/
		        	for (int j = 0; j < d2sid.length ; j++){
		        		Integer temp = new Integer(d2sid[j]);
		        		ArrayList eDepList = new ArrayList();
		        		Iterator pid = pRd2sHashMap.keySet().iterator();
		        		while (pid.hasNext()){
		        			Integer pidNext = new Integer(Integer.parseInt(pid.next().toString()));
		        			Integer d2sID = new Integer(Integer.parseInt(pRd2sHashMap.get(pidNext).toString()));
			    		
		        			if (temp.intValue() == d2sID.intValue()){
		        				eDepList.add(pidNext);
		        			}
		        		}
		        		eDepartmentPlayers.add(eDepList);
		        	
		        	}
		        
		        	/***************** eItemPlayers  单个项目的所有运动员按     计算机、环化、材料-计算机、环化、材料。。。 排列***/
		        	for (int h = 0; h < perMan; h++){
		        		for (int k = 0; k < eDepartmentPlayers.size(); k++){
		        		ArrayList dePls = (ArrayList)eDepartmentPlayers.get(k);
		        			if (dePls.size() > h){
		        				Integer temp = new Integer(Integer.parseInt(dePls.get(h).toString()));
		        				eItemPlayers.add(temp);
		        			}
		        		}
		        	}
		        
		        	/***************** 选人分组   分赛道    ******************/
		        	int pSum = eItemPlayers.size(); //单个项目运动员数目
		        	int[] groups = gameGrouping.setGroupSporterNumber(pSum); //单个项目分几组，每组几个人
		        	int count = 0;
		        	int count2 = 0;
		        	for (int q = 0; q < groups.length; q++){
		        		ArrayList qw = new ArrayList();
		        		for (int w = 0; w < groups[q]; w++){
		        			qw.add(eItemPlayers.get(count));
		        			count++;
		        		}
		        		qw = gameGrouping.arrayListRandom(qw);
		        		int finalitemid1 = finalitemid;
	        			int teamnum1 = q + 1;
	        			
		        		for (int e = 0; e < groups[q]; e++){
		        			if (count2 > 0){
		        				sql = sql + ",";
		        			}
		        			//finalitemid,teamnum,runway,playerid 顺序
		        			int runway1 = e + 1;
		        			int playerid1 = Integer.parseInt(qw.get(e).toString());
		        			sql = sql + "("+  finalitemid1 + "," + teamnum1 + "," + runway1 + "," + playerid1 + ")";
		        			count2++;
		        		}
		        		
		        	}
		        	log.debug("径赛SQl语句是"+sql);
		        	if (!sql.equals("INSERT INTO t_match(finalitemid,teamnum,runway,playerid) VALUES")){
		        		cpgg.addRecordBySql(sql);
		        	}
		        	cpgg.uodateGroupnumByFid(groups.length, finalitemid);
		    	}
		    }
	    }
	    
	    
	    
/*************************田赛分组********************************/
		ArrayList allFieldItems = new ArrayList(); //该届运动会的所有能报的田赛项目的    组别与运动会联系表id 项目id
		
		allFieldItems = cpgg.selectGroupItem(sportsid, "2");
		log.debug("所有田赛能报的项目的    组别与运动会联系表id 项目id"+ allFieldItems.size());
	    HashMap g2sidHashMap = new HashMap(); // HashMap<组别id,组别与运动会联系表id>
	    g2sidHashMap = cpgg.selectGroupItem(sportsid);
	    log.debug("HashMap<组别id,组别与运动会联系表id>"+ g2sidHashMap.size());
	    HashMap plfHashMap = new HashMap(); //HashMap<运动员(以数字+运动员id的形式储存), 组别与运动会表的id和项目id的结合>
	    plfHashMap = cpgg.selectPlarerIdAndG2IId(sportsid, g2sidHashMap);
	    log.debug("HashMap<运动员(以数字+运动员id的形式储存), 组别与运动会表的id和项目id的结合>"+ plfHashMap.size());
	    //HashMap fit2G2sid = new HashMap(); // 最终项目表id（v）  与       组别与项目联系表的id（k）    的对应关系
	    //fit2G2sid = cpgg.selectG2itidVSd2s2gID(sportsid);
	    //HashMap g2itG2sRi = new HashMap(); //  组别与项目联系表的id(v) 与     组别与运动会联系表的id与项目id联合起来(k)   的关系
	    //g2itG2sRi = cpgg.selectGp2itid(sportsid);
	    
	    for (int i= 0 ; i < allFieldItems.size(); i++){
	    	ArrayList eDepartmentPlayers = new ArrayList(); //单个项目各部门所有运动员id集合
	    	log.debug("田赛运动员id集合"+ eDepartmentPlayers.size());
	    	Group2itemPojo g2i = (Group2itemPojo)allFieldItems.get(i); //所有项目
	    	int gp2spid = g2i.getGp2spid();
	    	int itemid = g2i.getItemid();
	    	String gsi = gp2spid + ";" + itemid; //组别与运动会表的id和项目id的结合
	    	log.debug("组别与运动会表的id和项目id的结合" + gsi);
	    	/*********获取finalitemid************/
	    	 int finalitemid = 0;
	    	    if(fidRg2sid2iid != null && gsi != null){
	    	    	finalitemid = Integer.parseInt(fidRg2sid2iid.get(gsi).toString());
	    	    }
	    	    
    	    
	    	
    	    String sql = "INSERT INTO t_match(finalitemid,playerid) values";
			Iterator pfKeySet = plfHashMap.keySet().iterator();
			while (pfKeySet.hasNext()){
				String temp = pfKeySet.next().toString();
				if (plfHashMap.get(temp).toString().equals(gsi)){
					String[] temp1 = temp.split(";");
    				String temp3 = temp1[1];
    				Integer playerid = new Integer(Integer.parseInt(temp3));
    				eDepartmentPlayers.add(playerid);
				}	
			}
			log.debug("********************原田赛sql"+sql);
			if (eDepartmentPlayers != null){
				eDepartmentPlayers = gameGrouping.arrayListRandom(eDepartmentPlayers);
				//finalitemid,teamnum,runway,playerid 顺序
				int pSum = eDepartmentPlayers.size(); //单个项目运动员数目
				int finalitemid1 = finalitemid;
	        	for (int q = 0; q < pSum; q++){
	        		if (q > 0){ sql = sql + ","; }
	        		
	        		int playerid1 = Integer.parseInt(eDepartmentPlayers.get(q).toString());
	        		sql = sql + "(" + finalitemid1 + "," + playerid1 + ")";
	        	}
	        	log.debug("********************田赛sql"+sql);
	        	if (!sql.equals("INSERT INTO t_match(finalitemid,playerid) values")){
	        		cpgg.addRecordBySql(sql);
	        	}
			}
			
        	
	    }
	    log.debug("田赛分组结束");
	    
/*************************接力分组********************************/
		ArrayList allRelayItems = new ArrayList();
		allRelayItems = cpgg.selectGroupItem(sportsid, "3");
		log.debug("所有接力项目"+allRelayItems.size());
		HashMap didRgtype = new HashMap(); //部门id（k）与部门类型（v）
		didRgtype = cpgg.selectT_epartment(sportsid);
		log.debug("部门id（k）与部门类型（v）"+didRgtype.size());
		HashMap gidRgtype = new HashMap(); //组别与运动会表id（k）与 组别类型（v）
		gidRgtype = cpgg.selectGtype(sportsid);
		log.debug("组别与运动会表id（k）与 组别类型（v）"+gidRgtype.size());
		
		for (int i = 0; i < allRelayItems.size(); i++){
			ArrayList eGrouopDid = new ArrayList(); //单个项目各部门id集合
	    	Group2itemPojo g2i = (Group2itemPojo)allFieldItems.get(i); //所有项目
	    	int gp2spid = g2i.getGp2spid();
	    	int itemid = g2i.getItemid();
	    	String gsi = gp2spid + ";" + itemid; //组别与运动会表的id和项目id的结合
	    	log.debug("组别与运动会表的id和项目id的结合"+gsi);
	    	/*********获取finalitemid************/
	        int finalitemid = 0;
	        if(fidRg2sid2iid != null && gsi != null){
	    	   	finalitemid = Integer.parseInt(fidRg2sid2iid.get(gsi).toString());
	    	}   
	        
    	    int type = Integer.parseInt(gidRgtype.get(new Integer(gp2spid)).toString());
    	    
    	    if (type == 1){ //学生组
    	    	Iterator temp = didRgtype.keySet().iterator();
    	    	Integer flag = new Integer(1);
    	    	while (temp.hasNext()){
    	    		Integer deid =new Integer(Integer.parseInt(temp.next().toString())); 
    	    		if (didRgtype.get(deid).equals(flag)){
    	    			eGrouopDid.add(deid);
    	    		}
    	    	}
    	    }else{ //教工组
    	    	Iterator temp = didRgtype.keySet().iterator();
    	    	//Integer flag = new Integer(1);
    	    	while (temp.hasNext()){
    	    		eGrouopDid.add(temp.next());
    	    	}
    	    }
//    	    if (eGrouopDid.size() <= 8){
//    	    	String sql = "INSERT INTO t_match(finalitemid,runway,playerid) values";
//    	    	eGrouopDid = gameGrouping.arrayListRandom(eGrouopDid);
//    	    	for (int j = 0; j < eGrouopDid.size(); j++){
//    	    		if (j > 0){ sql = sql + ","; }
//    	    		//finalitemid,teamnum,runway,playerid 顺序
//    	    		sql = sql + "(" + finalitemid + "," + (j+1) + "," + Integer.parseInt(eGrouopDid.get(j).toString()) + ")";
//    	    		
//    	    	}
//    	    	if (!sql.equals("INSERT INTO t_match(finalitemid,runway,playerid) values")){
//    	    		cpgg.addRecordBySql(sql);
//    	    	}
//    	    	
//    	    }else{
    	    	String sql = "INSERT INTO t_match(finalitemid,teamnum,runway,playerid) values";
    	    	eGrouopDid = gameGrouping.arrayListRandom(eGrouopDid);
    	    	int[] gp = gameGrouping.setGroupSporterNumber(eGrouopDid.size());
    	    	int count = 0;
    	    	for (int j = 0; j < gp.length; j++){
    	    		for (int k = 0; k < gp[i]; k++){
    	    			if (count > 0){ sql = sql + ","; }
        	    		//finalitemid,teamnum,runway,playerid 顺序
        	    		sql = sql + "(" + finalitemid + "," + (j + 1) + "," + (k+1) + "," + Integer.parseInt(eGrouopDid.get(count).toString()) + ")";
        	    		count++;
    	    		}
    	    		
    	    	}
    	    	if (!sql.equals("INSERT INTO t_match(finalitemid,teamnum,runway,playerid) values")){
    	    		cpgg.addRecordBySql(sql);
    	    	}
    	    	cpgg.uodateGroupnumByFid(gp.length, finalitemid);
    	    //}
		}
		
/*************************分组完成********************************/		
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
