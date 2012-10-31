package cn.edu.hbcit.smms.dao.createprogramdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;


/**
 * 赛事编排
 * @author 韩鑫鹏
 *
 */
public class GameGroupingDAO {
	
	protected final Logger log = Logger.getLogger(GameGroupingDAO.class.getName());
	GameGroupingUtil mud = new GameGroupingUtil(); 
	
	/**
	 * 径赛编排
	 * @param trackItems 所有径赛项目ArrayList  groupid+itemid
	 * @param itemid2name 项目id，name对照HashMap 
	 * @param player2item 数字+运动员id，运动员组别id+所报项目id对照HashMap
	 * @param departments 所有部门id  ArrayList
	 * @param pla2dep 运动员id，部门id对照 HashMap
	 * @param item2finalitem 组别id+项目id，最终项目id对应HashMap
	 * @param pernums 项目各系限报人数
	 * @return ArrayList sql语句集合，第一条是分组情况sql语句，第二条是修改分组数目sql语句
	 * 百米径赛设计理念：根据项目的groupid+itemid从HashMap（player2item）中得到所有报该项目的运动员id号 
	 *                  放入一个ArrayList里面并将其随机排序，根据各部门的顺序依次从该集合中取一个id（循环操作，
	 *                  id被取出之后就从该集合中删除，直到集合为空时停止循环）放到另外一个ArrayList里面...
	 */
	public ArrayList trackGrouping(ArrayList trackItems, HashMap itemid2name, HashMap player2item, 
			ArrayList departments, HashMap pla2dep, HashMap item2finalitem,int pernums){
		ArrayList resultSqlList = new ArrayList();
		StringBuffer addSql = new StringBuffer("INSERT INTO t_match(finalitemid,teamnum,runway,playerid) VALUES");
		StringBuffer updateSql = new StringBuffer();
		int acount = 0; //用于记录添加记录的条数
		int ucount = 0; //用于记录修改分组数目的条数
		for (int traItemNum = 0; traItemNum < trackItems.size(); traItemNum++){
			
			ArrayList eItemPlayers = new ArrayList();
			ArrayList eItemPlayersAgo = new ArrayList();
			eItemPlayersAgo = mud.getKeyByValue(player2item, trackItems.get(traItemNum).toString().trim(), 1, pla2dep);
			eItemPlayers = (ArrayList)eItemPlayersAgo.get(0);
			HashMap dep2planum = (HashMap)eItemPlayersAgo.get(1);
			String[] groupid2itemid = trackItems.get(traItemNum).toString().trim().split(";");
			String itemName = "";
			itemName = mud.getValueByKey(item2finalitem, groupid2itemid[1], -1);
            int finalitemid = 0;
            finalitemid = Integer.parseInt(mud.getValueByKey(item2finalitem, trackItems.get(traItemNum).toString().trim(), -1));
			if (finalitemid == 0){
				continue;
			}
            if (itemName == null || itemName == ""){
				continue;
			}
			if (mud.isLongRunning(itemName)){
				if(mud.isBig1500(itemName, eItemPlayers.size())){
					int[] group = mud.set1500GpNum(eItemPlayers.size());
					eItemPlayers = mud.arrayListRandom(eItemPlayers);
					//mud.sql1500(addSql, eItemPlayers, group, finalitemid, acount);
					for (int q = 0; q < group[1]; q++){
						int playerid = Integer.parseInt(eItemPlayers.get(q).toString().trim());
				    	if (acount > 0){ addSql.append(","); }
				    	addSql.append("(" + finalitemid + "," + 1+ "," + null + "," + playerid + ")");
				    	++acount;
				    }
					for (int ak = group[1]; ak < eItemPlayers.size(); ak++){
				    	int playerid = Integer.parseInt(eItemPlayers.get(ak).toString().trim());
				    	if (acount > 0){ addSql.append(","); }
				    	addSql.append("(" + finalitemid + "," + 2 + "," + null + "," + playerid + ")");
				    	++acount;
				    }
					//mud.sqlUpdateGroup(updateSql, finalitemid, ucount, group.length);
					if (ucount > 0){
						updateSql.append("#");
					}
					updateSql.append("UPDATE t_finalitem SET groupnum = " + group.length + " WHERE id = " + finalitemid);
					++ucount;
					log.debug("修改分组的sql连接的ucount值是：" + ucount);
				}else{
					eItemPlayers = mud.arrayListRandom(eItemPlayers);
					//mud.sqlFiled(addSql, eItemPlayers, finalitemid, acount);
					for (int q = 0; q < eItemPlayers.size(); q++){
		        		int playerid = Integer.parseInt(eItemPlayers.get(q).toString().trim());
		           		if (acount > 0){ addSql.append(","); }
		           		addSql.append("(" + finalitemid + "," + 1+ "," + null + "," + playerid + ")");
		           		++acount;
		           	}
				}
			}else{
				ArrayList playersPailei = new ArrayList();
				//playersPailei = mud.arrayListRandom(playersPailei);
				int[] group = mud.trackGrouping(eItemPlayers.size());
				log.debug("径赛运动员排列前id"+eItemPlayers.size());
				playersPailei = mud.plaSortByDep(eItemPlayers, pla2dep, departments, pernums, group, dep2planum);
				log.debug("径赛运动员排列前id"+eItemPlayers.size());
				log.debug("径赛运动员排列后id"+playersPailei.size());
				//mud.sqlTrack(addSql, group, playersPailei, finalitemid, acount);
				
				log.debug("径赛sql连接players的长度是：" + playersPailei.size());
				log.debug("径赛sql连接group的长度是：" + group.length);
				int count1 = 0;
			    for (int groupNum = 0; groupNum < group.length; groupNum++){
			    	ArrayList qw = new ArrayList();
			    	for (int eGroupNum = 0; eGroupNum < group[groupNum]; eGroupNum++){
			    		log.debug("径赛sql连接group[groupNum]的值是：" + group[groupNum]);
			    		qw.add(playersPailei.get(count1));
			    		log.debug("径赛sql连接eGroupNum的值是：" + eGroupNum);
			    		log.debug("径赛sql连接count1的值是：" + count1);
			    		++count1;
			    	}
			    	qw = mud.arrayListRandom(qw);
					int teamnum = groupNum + 1;
					
			    	for (int ee = 0; ee < group[groupNum]; ee++){
			    		if (acount > 0){
			    			addSql.append(",");
			    		}
			    		//finalitemid,teamnum,runway,playerid 顺序
			    		int runway = ee + 1;
			    		int playerid = Integer.parseInt(qw.get(ee).toString().trim());
			    		log.debug("sql连接前运动员id："+playerid);
			    		addSql.append("("+  finalitemid + "," + teamnum + "," + runway + "," + playerid + ")");
			    		++acount;
			    	}
			    	
			   	}
				
				//mud.sqlUpdateGroup(updateSql, finalitemid, ucount, group.length);
				if (ucount > 0){
					updateSql.append("#");
				}
				updateSql.append("UPDATE t_finalitem SET groupnum = " + group.length + " WHERE id = " + finalitemid);
				++ucount;
				log.debug("修改分组的sql连接的ucount值是：" + ucount);
			}
		}
		log.debug("径赛分组的sql是：" + addSql);
		if(acount == 0){
			addSql.setLength(0);
		}
		resultSqlList.add(addSql);
		resultSqlList.add(updateSql);
		return resultSqlList;
	}
	
	/**
	 * 田赛赛事编排
	 * @param fieldItems 所有田赛项目ArrayList  groupid+itemid
	 * @param player2item 数字+运动员id，运动员组别id+所报项目id对照HashMap
	 * @param item2finalitem 组别id+项目id，最终项目id对应HashMap
	 * @return StringBuffer 田赛分组情况sql
	 */
	public StringBuffer filedGrouping(ArrayList fieldItems, HashMap player2item, HashMap item2finalitem){
		StringBuffer resultSql = new StringBuffer("INSERT INTO t_match(finalitemid,teamnum,runway,playerid)" +
				" VALUES");
		int count = 0; //用于记录添加记录的条数
		for (int filItemNum = 0; filItemNum < fieldItems.size(); filItemNum++){
			ArrayList eItemPlayers = new ArrayList();
			eItemPlayers = mud.getKeyByValue(player2item, fieldItems.get(filItemNum).toString().trim(), 1);
			eItemPlayers = mud.arrayListRandom(eItemPlayers);
			int finalitemid = 0;
            finalitemid = Integer.parseInt(mud.getValueByKey(item2finalitem, fieldItems.get(filItemNum).toString().trim(), -1));
            if (finalitemid == 0){
				continue;
			}
            //mud.sqlFiled(resultSql, eItemPlayers, finalitemid, count);
            
        	for (int q = 0; q < eItemPlayers.size(); q++){
        		int playerid = Integer.parseInt(eItemPlayers.get(q).toString().trim());
           		if (count > 0){ resultSql.append(","); }
           		resultSql.append("(" + finalitemid + "," + 1+ "," + null + "," + playerid + ")");
           		++count;
           	}
       		
       	}
			
		if(count == 0){
			resultSql.setLength(0);
		}
		return resultSql;
	}
	
	/**
	 * 接力赛赛事编排
	 * @param reailyItems 所有接力赛项目ArrayList  groupid+itemid
	 * @param groupid2name 组别id与组名 对照map
	 * @param departments 所有部门id集合
	 * @param stuDep 学生部门id集合
	 * @param item2finalitem 组别id+项目id，最终项目id对应HashMap
	 * @return ArrayList sql语句集合，第一条是分组情况sql语句，第二条是修改分组数目sql语句
	 */
	public ArrayList trackReailyGrouping(ArrayList reailyItems, HashMap groupid2name, 
			ArrayList departments, ArrayList stuDep, HashMap item2finalitem){
		ArrayList resultSqlList = new ArrayList();
		StringBuffer addSql = new StringBuffer("INSERT INTO t_match(finalitemid,teamnum,runway,playerid) VALUES");
		StringBuffer updateSql = new StringBuffer();
		int acount = 0; //用于记录添加记录的条数
		int ucount = 0; //用于记录修改分组数目的条数
		
		for (int reaItemNum = 0; reaItemNum < reailyItems.size(); reaItemNum++){
			int finalitemid = 0;
            finalitemid = Integer.parseInt(mud.getValueByKey(item2finalitem, reailyItems.get(reaItemNum).toString().trim(), -1));
            if (finalitemid == 0){
				continue;
			}
            String[] tempItem = reailyItems.get(reaItemNum).toString().trim().split(";"); 
            String groupId = tempItem[0];
			String gName = "";
			gName = mud.getValueByKey(groupid2name, groupId, -1);
			if (gName.equals("") || gName == null){
				continue;
			}
			int flag = gName.indexOf("教工");
			if (flag >= 0){
				int[] group = mud.trackGrouping(departments.size());
				//mud.sqlTrack(addSql, group, departments, finalitemid, acount);
				log.debug("接力赛sql连接players的长度是：" + departments.size());
				log.debug("接力赛sql连接group的长度是：" + group.length);
				int count1 = 0;
			    for (int groupNum = 0; groupNum < group.length; groupNum++){
			    	ArrayList qw = new ArrayList();
			    	for (int eGroupNum = 0; eGroupNum < group[groupNum]; eGroupNum++){
			    		log.debug("接力赛sql连接group[groupNum]的值是：" + group[groupNum]);
			    		qw.add(departments.get(count1));
			    		log.debug("接力赛sql连接eGroupNum的值是：" + eGroupNum);
			    		log.debug("接力赛sql连接count1的值是：" + count1);
			    		++count1;
			    	}
			    	qw = mud.arrayListRandom(qw);
					int teamnum = groupNum + 1;
					
			    	for (int ee = 0; ee < group[groupNum]; ee++){
			    		if (acount > 0){
			    			addSql.append(",");
			    		}
			    		//finalitemid,teamnum,runway,playerid 顺序
			    		int runway = ee + 1;
			    		int playerid = Integer.parseInt(qw.get(ee).toString().trim());
			    		addSql.append("("+  finalitemid + "," + teamnum + "," + runway + "," + playerid + ")");
			    		++acount;
			    	}
			    	
			   	}
				
				//mud.sqlUpdateGroup(updateSql, finalitemid, ucount, group.length);
				
				log.debug("修改分组的sql的count是：" + ucount);
				if (ucount > 0){
					updateSql.append("#");
				}
				updateSql.append("UPDATE t_finalitem SET groupnum = " + group.length + " WHERE id = " + finalitemid);
				++ucount;
				
			}else{
				int[] group = mud.trackGrouping(stuDep.size());
				//mud.sqlTrack(addSql, group, stuDep, finalitemid, acount);
				log.debug("接力赛sql连接players的长度是：" + stuDep.size());
				log.debug("接力赛sql连接group的长度是：" + group.length);
				int count1 = 0;
			    for (int groupNum = 0; groupNum < group.length; groupNum++){
			    	ArrayList qw = new ArrayList();
			    	for (int eGroupNum = 0; eGroupNum < group[groupNum]; eGroupNum++){
			    		log.debug("接力赛sql连接group[groupNum]的值是：" + group[groupNum]);
			    		qw.add(stuDep.get(count1));
			    		log.debug("接力赛sql连接eGroupNum的值是：" + eGroupNum);
			    		log.debug("接力赛sql连接count1的值是：" + count1);
			    		++count1;
			    	}
			    	qw = mud.arrayListRandom(qw);
					int teamnum = groupNum + 1;
					
			    	for (int ee = 0; ee < group[groupNum]; ee++){
			    		if (acount > 0){
			    			addSql.append(",");
			    		}
			    		//finalitemid,teamnum,runway,playerid 顺序
			    		int runway = ee + 1;
			    		int playerid = Integer.parseInt(qw.get(ee).toString().trim());
			    		addSql.append("("+  finalitemid + "," + teamnum + "," + runway + "," + playerid + ")");
			    		++acount;
			    	}
			    	
			   	}
				log.debug("修改分组的sql的count是：" + ucount);
				if (ucount > 0){
					updateSql.append("#");
				}
				updateSql.append("UPDATE t_finalitem SET groupnum = " + group.length + " WHERE id = " + finalitemid);
				++ucount;
			}
		}
		if(acount == 0){
			addSql.setLength(0);
		}
		resultSqlList.add(addSql);
		resultSqlList.add(updateSql);
		return resultSqlList;
	}
}
