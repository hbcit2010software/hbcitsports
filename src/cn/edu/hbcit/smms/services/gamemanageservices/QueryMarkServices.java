package cn.edu.hbcit.smms.services.gamemanageservices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lowagie.text.DocumentException;

import cn.edu.hbcit.smms.dao.gamemanagedao.QueryMark;
import cn.edu.hbcit.smms.pojo.MarkPojo;
import cn.edu.hbcit.smms.pojo.QueryAllMarksPlayerMsg;
import cn.edu.hbcit.smms.pojo.QueryMarkPoJo;


public class QueryMarkServices {


	 public ArrayList allMarkInfo(){
		 return new QueryMark().allMarkInfo();
	 }
	 
	 public ArrayList allTeachMarkInfo(){
		 return new QueryMark().allTeachMarkInfo();
	 }
	
	 public int insertAllStuMarks(){
		 return new QueryMark().insertAllStuMarks();
	 }
	 
	 public int updateAllTeasMarks(){
		 return new QueryMark().updateAllTeasMarks();
	 }
	 public boolean selectAllMarks(){
		 return new QueryMark().selectAllMarks();
	 }
	 
	
	 /**
	  * 生成积分表
	  * @param file 保存路径
	  * @param depNameList 部门list（部门名称；id）
	  * @param boyItemList 男子项目list（项目名称；id）
	  * @param sportsname 运动会名称
	  * @param boyGroup “男子组”
	  * @param boyMarkMap 男子积分map(key:itemid;rank;depid value:name;score;mark)
	  * @param girlItemList 女子项目list（项目名称；id）
	  * @param girlGroup “女子组”
	  * @param girlMarkMap 女子积分map(key:itemid;rank;depid value:name;score;mark)
	  */
	 public void generateExcel2003(String file, ArrayList depNameList,
				ArrayList boyItemList, String sportsname, String boyGroup, HashMap boyMarkMap,
				ArrayList girlItemList, String girlGroup, HashMap girlMarkMap) {
			new QueryMark().generateExcel2003(file, depNameList, boyItemList, sportsname, boyGroup, boyMarkMap, girlItemList, girlGroup, girlMarkMap);
	}

		/**
		 * 查询部门集合（部门名称；id）
		 * @param sportsId
		 * @return
		 */
		public ArrayList selectDep(int sportsId){
			
				return new QueryMark().selectDep(sportsId);
		}
		
		/**
		 * 查询项目集合（项目名称；id）
		 * @param sportsId
		 * @return
		 */
		public ArrayList selectItem(int sportsId,int sex){
			return new QueryMark().selectItem(sportsId, sex);
		}
		
		/**
		 * 查询积分（(key:itemid;rank;depid value:name;score;mark)）（径赛和田赛）
		 * @param sportsId
		 * @param sex
		 * @return HashMap
		 */
		public HashMap selectMarkMap(int sportsId,int sex){
			return new QueryMark().selectMarkMap(sportsId, sex);
		}
		/**
		 * 查询所有积分（(key:itemid;rank;depid value:name;score;mark)）（接力）
		 * @param sportsId
		 * @param sex
		 * @param markMap田赛径赛
		 * @return HashMap
		 */
		public HashMap selectMarkMap(int sportsId,int sex,HashMap markMap ){
			return new QueryMark().selectMarkMap(sportsId, sex,markMap);
		}
}
