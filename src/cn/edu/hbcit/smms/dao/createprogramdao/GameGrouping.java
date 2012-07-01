package cn.edu.hbcit.smms.dao.createprogramdao;

import java.util.ArrayList;
import java.util.Random;

import cn.edu.hbcit.smms.dao.databasedao.CreateProgramGameGrouping;

/**
 * 径赛跑道分组
 * @author 韩鑫鹏
 *
 */
public class GameGrouping {
	
	/**
	 * 分几个组              每组几人
	 * @param gameGroupNumber 项目总人数
	 * @return int[]
	 */
	public int[] setGroupSporterNumber(int sumSporters){ 
		
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
	 * 1500米 分组        groupNumber[1]就是第一组的人数       groupNumber[2]就是第2组的人
	 * @param gNum  组数
	 * @param pNum  1500米运动员数目
	 * @return
	 */
	public int[] set1500GpNum(int gNum, int pNum){ 
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
		    swap(arrayList, j, num);
		}
		return arrayList;		
	}
	
	 /**
	  * ArrayList随机交换位置
	  * @param aryList 
	  * @param index1
	  * @param index2
	  */
	 public static void swap(ArrayList aryList, int index1, int index2) {
	     ArrayList newList = new ArrayList();
	     newList.add(aryList.get(index1)); 
	     aryList.set(index1,aryList.get(index2)); 
	     aryList.set(index2,newList.get(0));  
	 }

}
