package cn.edu.hbcit.smms.dao.createprogramdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 工具类
 * @author 韩鑫鹏
 */
public class GameGroupingUtil {
	
	protected final Logger log = Logger.getLogger(DataManagerDAO.class.getName());
	
	/**
	 * 径赛类分组    组数（数组长度）  每组人数（对应索引处的值）
	 * @param gameGroupNumber 项目总人数
	 * @return int[]
	 * 设计思路：分两种情况
	 * 			第一种情况：该项目所报人数除以8，如果可以除尽，所得商即为分组的数目，每组的人数为8
	 * 			第二种情况：该项目所报人数除以8，商+1即为分组的数目，用该项目所报人数除以分组数目得余数和商（
	 * 					         此时的商为暂时的每组人数），从第一组起人数依次+1（然后余数-1），直到余数为0时停止，
	 * 					         得到最终分组情况
	 */
	public int[] trackGrouping(int sumSporters){ 
		
		int gameGroupNumber; //项目组数
		int gameGroupSportersNumber; //每组人数
		int remainder; //余数
		int[] groupSporterNumber; 
		if (sumSporters % 8 == 0){
			gameGroupNumber = sumSporters / 8;
			groupSporterNumber = new int[gameGroupNumber];
			for (int i = 0; i < gameGroupNumber; i++){
				groupSporterNumber[i] = 8;
			}
		}else{
			gameGroupNumber = sumSporters / 8 + 1;
			groupSporterNumber = new int[gameGroupNumber];
			gameGroupSportersNumber = sumSporters / gameGroupNumber;
			remainder = sumSporters % gameGroupNumber;
			for (int i = 0; i < remainder; i++){
				groupSporterNumber[i] = (gameGroupSportersNumber + 1);
			}
			for (int i = remainder ;i < gameGroupNumber; i++){
				groupSporterNumber[i] = gameGroupSportersNumber;
			}
		}
		return groupSporterNumber;
	}
	
	/**
	 * 如果1500的人数大于30则需要采用如下分组方式
	 * @param playersNum 1500运动员数目
	 * @return int[]
	 */
	public int[] set1500GpNum(int playersNum){
		int[] result = new int[2];
		int num = playersNum / 2;
		if (playersNum % 2 == 0){
			result[1] = num;
			result[2] = num;
		}else{
			result[1] = num;
			result[2] = num + 1;
		}
		return result;
	}
	
	/**
	 * 1500米 分组 （手工调整）       groupNumber[1]就是第一组的人数       groupNumber[2]就是第2组的人
	 * @param gNum  组数
	 * @param pNum  1500米运动员数目
	 * @return
	 */
	public int[] update1500GpNum(int gNum, int pNum){ 
		int eGroupPnum; //每组人数
		int remainder; //余数
		int[] groupNumber = new int[gNum]; 
		eGroupPnum = pNum / gNum;
		remainder = pNum % gNum;
		for (int i = 0; i < remainder; i++){
			groupNumber[i] = (eGroupPnum + 1);
		}
		for (int i = remainder ;i < gNum; i++){
			groupNumber[i] = eGroupPnum;
		}
		return groupNumber;
	}
	
	/**
	 * 集合里面的对象随机交换位置
	 * @param arrayList 需要随机分的组的集合名称
	 * @return  ArrayList
	 */
	public ArrayList arrayListRandom(ArrayList arrayList){
		Random ran = new Random();
		int num; //随机数
		for (int j = 0; j < arrayList.size(); j++) {
		    num = ran.nextInt(arrayList.size());
		    //swap(arrayList, j, num);
		    ArrayList newList = new ArrayList();
		    newList.add(arrayList.get(j)); 
		    arrayList.set(j,arrayList.get(num)); 
		    arrayList.set(num,newList.get(0));
		}
		return arrayList;		
	}

	/**
	 * 判断是否为长跑
	 * @param t_name(String)
	 * @return boolean
	 */
	public boolean isLongRunning(String t_name){
		boolean flag = false;
		int flag1 = t_name.indexOf("1500");
    	int flag2 = t_name.indexOf("5000");
    	int flag3 = t_name.indexOf("马拉松");
    	if ( flag1 >= 0 && flag2 >= 0 && flag3 >= 0){
    		flag = true;
    	}
		return flag;
	}
	
	/**
	 * 判断比赛是否为1500米且所报人数>30
	 * @param t_name
	 * @param playersNum
	 * @return boolean
	 */
	public boolean isBig1500(String t_name, int playersNum){
		boolean flag = false;
		int flag1 = t_name.indexOf("1500");
		if ( flag1 >= 0 && playersNum > 30){
    		flag = true;
    	} 
		return flag;
	}
	/**
	 * HashMap 据值取键
	 * @param map 被取键的map
	 * @param flag 取值的标志
	 * @param kNum 要选取数组(键分离后的)的索引，若没有则是设置为负数
	 * @return ArrayList
	 */
	public ArrayList getKeyByValue(HashMap map, String flag, int kNum){
		ArrayList tempList = new ArrayList();
		Iterator iteTemp = map.keySet().iterator();
		if (map.containsValue(flag)){
			if (kNum >= 0){
				while (iteTemp.hasNext()){
					String tempKey = iteTemp.next().toString().trim();
					if (map.get(tempKey).toString().equals(flag.trim())){
						String[] temp = tempKey.split(";");
						tempList.add(temp[kNum]);
					}
				}	
			}
			if (kNum < 0){
				while (iteTemp.hasNext()){
					String tempKey = iteTemp.next().toString().trim();
					if (map.get(tempKey).toString().equals(flag.trim())){
						tempList.add(tempKey);
					}
				}	
			}
		}
		
			
		return tempList;
	}
	
	/**
	 * HashMap 据键取值 
	 * @param map 被取值的map
	 * @param flagKey 要取的键的值
	 * @param vNum 要选取数组(值分离后的)的索引，若没有则是设置为负数
	 * @return String
	 */ 
	public String getValueByKey(HashMap map, String flagKey, int vNum){
		String result = "";
		if (vNum < 0){
			if (map.containsKey(flagKey)){
				result = map.get(flagKey).toString().trim();	
			}	
		}
		if (vNum >= 0){
			if (map.containsKey(flagKey)){
				String[] temp = map.get(flagKey).toString().trim().split(";");	
				result = temp[vNum];
			}	
		}
		if( !result.equals("") && result != null){
			return result;
		}else{
			return "0";
		}
		
	}
	
//	/**
//	 * 1500sql连接
//	 * @param sql 被连接的sql语句
//	 * @param players 1500 运动员数目
//	 * @param group 分组情况
//	 * @param finalitemid
//	 * @param count 添加记录计数
//	 */
//	public void sql1500(StringBuffer sql, ArrayList players, int[] group, int finalitemid, int count){
//		for (int q = 0; q < group[1]; q++){
//			int playerid = Integer.parseInt(players.get(q).toString().trim());
//    		if (count > 0){ sql.append(","); }
//    		sql.append("(" + finalitemid + "," + 1+ "," + null + "," + playerid + ")");
//    		++count;
//    	}
//		for (int ak = group[1]; ak < players.size(); ak++){
//    		int playerid = Integer.parseInt(players.get(ak).toString().trim());
//    		sql.append("(" + finalitemid + "," + 2 + "," + null + "," + playerid + ")");
//    		++count;
//    	}
//	}
//	
//	/**
//	 * 径赛sql连接
//	 * @param sql
//	 * @param group
//	 * @param players
//	 * @param finalitemid
//	 * @param count
//	 */
//	public void sqlTrack(StringBuffer sql, int[] group, ArrayList players, int finalitemid, int count){
//		log.debug("径赛sql连接players的长度是：" + players.size());
//		log.debug("径赛sql连接group的长度是：" + group.length);
//		int count1 = 0;
//    	for (int groupNum = 0; groupNum < group.length; groupNum++){
//    		ArrayList qw = new ArrayList();
//    		for (int eGroupNum = 0; eGroupNum < group[groupNum]; eGroupNum++){
//    			log.debug("径赛sql连接group[groupNum]的值是：" + group[groupNum]);
//    			qw.add(players.get(count1));
//    			log.debug("径赛sql连接eGroupNum的值是：" + eGroupNum);
//    			log.debug("径赛sql连接count1的值是：" + count1);
//    			++count;
//    		}
//    		arrayListRandom(qw);
//			int teamnum = groupNum + 1;
//			
//    		for (int ee = 0; ee < group[groupNum]; ee++){
//    			if (count > 0){
//    				sql.append(",");
//    			}
//    			//finalitemid,teamnum,runway,playerid 顺序
//    			int runway = ee + 1;
//    			int playerid = Integer.parseInt(qw.get(ee).toString().trim());
//    			sql.append("("+  finalitemid + "," + teamnum + "," + runway + "," + playerid + ")");
//    			++count;
//    		}
//    		
//    	}
//	}
//	
//	/**
//	 * 田赛类sql连接
//	 * @param sql
//	 * @param players
//	 * @param finalitemid
//	 * @param count
//	 */
//	public void sqlFiled(StringBuffer sql, ArrayList players, int finalitemid, int count){
//		for (int q = 0; q < players.size(); q++){
//			int playerid = Integer.parseInt(players.get(q).toString().trim());
//    		if (count > 0){ sql.append(","); }
//    		sql.append("(" + finalitemid + "," + 1+ "," + null + "," + playerid + ")");
//    		++count;
//    	}
//		
//	}
//	
//	/**
//	 * 修该分组分组数目sql连接
//	 * @param sql 被连接的sql语句
//	 * @param finalitemid  最终项目id
//	 * @param count 修改记录计数
//	 * @param groupNum分组数目
//	 */
//	public void sqlUpdateGroup(StringBuffer sql, int finalitemid, int count, int groupNum){
//		log.debug("修改分组的sql的count是：" + count);
//		if (count > 0){
//			sql.append("#");
//		}
//		sql.append("UPDATE t_finalitem SET groupnum = " + groupNum + " WHERE id = " + finalitemid);
//		++count;
//	}
    /**
     * 运动员按照计算机系、建工系——计算机系、建工系。。。排列
     * @param players 运动员id集合
     * @param pla2dep 运动员id，部门id对照HashMap
     * @param departmentid 部门id集合
     * @param perNum 项目各系限报人数
     * @return ArrayList
     */
	public ArrayList plaSortByDep(ArrayList players, HashMap pla2dep, ArrayList departmentid, int perNum){
		ArrayList arrayList = new ArrayList();
		for (int pernum = 0; pernum < perNum; pernum++){
			for (int depid = 0; depid < departmentid.size(); depid++){
				String str = departmentid.get(depid).toString().trim();
				if (pla2dep.containsValue(departmentid.get(depid))){
					for (int plaid = 0; plaid < players.size(); plaid++){
						String str1 = pla2dep.get(players.get(plaid)).toString().trim();
						if (str1.equals(str)){
							arrayList.add(str1);
							players.remove(plaid);
							break;
						}
					}
				}
				
			}
		}
		return arrayList;
	}
}
