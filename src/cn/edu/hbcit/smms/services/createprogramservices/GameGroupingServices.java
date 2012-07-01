package cn.edu.hbcit.smms.services.createprogramservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.createprogramdao.GameGrouping;

/**
 * 径赛分道Services
 *
 *简要说明
 *
 *详细解释。
 * @author 韩鑫鹏
 * @version 1.00  2011/12/07 新規作成<br>
 */
   public class GameGroupingServices {
   GameGrouping grp = new GameGrouping();
	
	/**
	 * 分几个组              每组几人
	 * @param gameGroupNumber 项目总人数
	 * @return int[]
	 */
	public int[] setGroupSporterNumber(int sumSporters){ 
		return grp.setGroupSporterNumber(sumSporters);
	}
	
	/**
	 * 1500米 分组        groupNumber[1]就是第一组的人数       groupNumber[2]就是第2组的人
	 * @param gNum  组数
	 * @param pNum  1500米运动员数目
	 * @return
	 */
	public int[] set1500GpNum(int gNum, int pNum){ 
		
		return grp.set1500GpNum(gNum, pNum);
	}
	
	/**
	 * 集合里面的对象随机交换位置
	 * @param arrayList 需要随机分的组的集合名称
	 * @return  ArrayList
	 */
	public ArrayList arrayListRandom(ArrayList arrayList){
		
		return grp.arrayListRandom(arrayList);		
	}

}
