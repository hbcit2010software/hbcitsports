package cn.edu.hbcit.smms.services.createprogramservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.createprogramdao.DataManagerDAO;
import cn.edu.hbcit.smms.pojo.T_finalitemPojo;


/**
 * 
 * @author Administrator 韩鑫鹏
 *
 */
public class DataManagerServices {

	DataManagerDAO dmd = new DataManagerDAO();
	
	/**公用
	 * 根据sql语句添加数据
	 * @param sql  要执行的sql语句
	 * @return int
	 */
	public int addRecordBySql(String sql){
		return dmd.addRecordBySql(sql);
    }
	
	/**
	 * 根据sportsid删除分组情况
	 * @param sql
	 */
	public void deleteT_matchBySid(int sportsid){	
		dmd.deleteT_matchBySid(sportsid);
	}
    
//************************************生成word**********************************************************	
    /**word
     * 根据运动会id查询最终项目表女子组信息  finalitemid(k) finalitemPojo(v)
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaGirl(int sportId){
    	return dmd.selectFlaGirl(sportId);
    }

    /**word
     * 根据运动会id查询最终项目表男子组信息
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaBoy(int sportId){
    	return dmd.selectFlaBoy(sportId);
    }

    /**word
     * 根据最终项目表的id查询项目类型
     * @param finalItemId
     * @return  String
     */
    public String selectItemTypeByFinId(int finalItemId){
    	return dmd.selectItemTypeByFinId(finalItemId);
    }
   
    /**word
     * 根据最终项目表id径赛100类查询运动员编号(接力也可用此方法查询)
     * @param int finalItemId
     * @param HashMap players
     * @return
     */
    public ArrayList selectMatchByFinId(int finalItemId, HashMap players){
    	return dmd.selectMatchByFinId(finalItemId, players);
    }

    /**word
     * 根据运动会的id查询运动员的id（k）与 号码（v）的关系
     * @param sportsId
     * @return  HashMap
     */
    public HashMap selectPlayersBySid(int sportsId){
    	return dmd.selectPlayersBySid(sportsId);
    }
    
    /**word
     * 根据最终项目表id查询组别名称
     * @param finalItemId
     * @return int
     */
    public String selectGrNameByFid(int finalItemId){
    	return dmd.selectGrNameByFid(finalItemId);
    }
    
    /**word
     * 根据最终项目表的id查询田赛运动员的编号
     * @param int finalItemId
     * @param HashMap players
     * @return ArrayList
     */
    public ArrayList selectFilePnumByFid(int finalItemId, HashMap players){
    	return dmd.selectFilePnumByFid(finalItemId, players);
    }

    /**word
     * 根据最终项目表id径赛查询1500类运动员编号 
     * @param int finalItemId
     * @param HashMap players
     * @return
     */
    public ArrayList select1500ByFinId(int finalItemId, HashMap players){
    	return dmd.select1500ByFinId(finalItemId, players);
    }

    /**word
     * 根据运动会id查询部门的id（k） 名称（V）
     * @param int  sportsid
     * @return  HashMap
     */
    public HashMap selectDepartmentBySid(int sportsid){
    	return dmd.selectDepartmentBySid(sportsid);
    }
    
//********************************赛事查看********************************************
    /**look
     * 根据finalitemid查询每个项目的每组的人数
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList selectTrackGroup(int finalitemid){
    	return dmd.selectTrackGroup(finalitemid);
    }
    
    /**look
     * 根据finalitemid查询每个径赛项目的各组运动员  编号（ArrayList）     跑道号（ArrayList）
     * @param finalitemid
     * @return ArrayList       <String>  编号+跑道号
     */
    public ArrayList slectTrackInfo(int finalitemid){
    	return dmd.slectTrackInfo(finalitemid);
    }
    
    /**look
     * 根据finalitemid查询接力的各组部门名
     * @param finalitemid 
     * @return  ArrayList
     */
    public ArrayList slectRelayInfo(int finalitemid){
    	return dmd.slectRelayInfo( finalitemid);
    }
    
    /**look
     * 根据finalitemid得到finalitemid与分组序号finalitemid + ";" + teamnum
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList slectFidRGnum(int finalitemid){
    	return dmd.slectFidRGnum(finalitemid);
    }
    
    /**look
    * 根据最终项目名查询项目名称
    * @param finalitemid
    * @return
    */
    public String selectItnameByFid(int finalitemid){
    	return dmd.selectItnameByFid(finalitemid);
    }
    
    /**
     * 根据finalitemid查询长跑类运动员信息
     * @param finalitemid
     * @return  ArrayList  编号+组号
     */
    /*public ArrayList slectTrack1500Ps(int finalitemid){
    	
    	return dmd.slectTrack1500Ps(finalitemid);
    }*/
    
    public Map getTrack1500(int finalitemid){
    	return dmd.getTrack1500(finalitemid);
    }
   
    /**look
     * 根据finalitemid查询田赛类运动员信息
     * @param finalitemid
     * @return  ArrayList  编号
     */
     public ArrayList slectFilePs(int finalitemid){
    	
    	 return dmd.slectFilePs(finalitemid);
    }
//************************************修改长跑分组数目******************************************    
     /**update1500
      * 根据finalitemid查询该届运动会报名长跑运动员的id（分组后）    以( id+"")方式存储
      * @param finalitemid
      * @return  ArrayList
      */
     public ArrayList selectPnumByFid(int finalitemid){
     	
    	 return dmd.selectPnumByFid( finalitemid);
     }
     
     /**update1500
      * 根据finalitemid删除长跑运动员的分组信息
      * @param finalitemid
      * @return
      */
     public void deletePnumByFid(int finalitemid){
      	
      	
      	
    	 dmd.deletePnumByFid(finalitemid);
             
      }
     

    
//*****************************赛事编排*********************************
     /**
      * 根据运动会id查询groupId+itemId
      * @param sportsId 
      * @param itemtype 
      * @return ArrayList   <String>groupId;itemId
      */
     public ArrayList selectItemBySid(int sportsId, String itemtype){
    	 return dmd.selectItemBySid(sportsId, itemtype);
     }
     
     /**
      * 根据sportsId查询项目的id与name
      * @param sportsId
      * @return HashMap<String,String>
      */
     public HashMap selectItemId2nameBySid(int sportsId){
    	 return dmd.selectItemId2nameBySid(sportsId);
     }
     
     /**
      * 根据sportsId查询数字+运动员id，运动员组别id+所报项目id对照HashMap
      * @param sportsId
      * @return HashMap
      */
     public HashMap selectplayer2itemBySid(int sportsId){
    	
    	 return dmd.selectplayer2itemBySid(sportsId);
     }
     
     /**
 	 * 根据运动会id查询每个项目各系的限报人数
 	 * @param 运动会id
 	 * @return int 
 	 */
 	public int selectPerDep(int sportsid){
 		
 		return dmd. selectPerDep(sportsid);
     }
 	
 	/**
 	 * 根据sportsId查询部门id集合
 	 * @param sportsId
 	 * @return  ArrayList
 	 */
 	public ArrayList selectDepidBySid(int sportsId){
 		return dmd.selectDepidBySid( sportsId);
    }
 	
 	/**
 	 * 根据sportsId查询
 	 * @param sportsId运动员id，部门id对照 HashMap
 	 * @return HashMap
 	 */
 	public HashMap slectPlaid2DepidySid(int sportsId){
 		return dmd.slectPlaid2DepidySid( sportsId);
    }
 	
 	/**
 	 * 根据sportsId查询组别id+项目id，最终项目id对应HashMap
 	 * @param sportsId
 	 * @return HashMap
 	 */
 	public HashMap slectItem2flaBySid(int sportsId){
 		return dmd.slectItem2flaBySid(sportsId);
 	    }
    
 	/**
     * 根据sportsId查询组别的id与name
     * @param sportsId
     * @return HashMap<String,String>
     */
    public HashMap slectGroupId2nameBySid(int sportsId){
    	return dmd.slectGroupId2nameBySid(sportsId);
    }
    
    /**
 	 * 根据sportsId查询学生部门id集合
 	 * @param sportsId
 	 * @return  ArrayList
 	 */
 	public ArrayList slectStuDepidBySid(int sportsId){
 		return dmd.slectStuDepidBySid(sportsId);
    }
 	
 	/**
	 * 根据sql语句修改分组情况
	 * @param sql
	 */
	public void updateGroupNumBySql(String sql){
		dmd.updateGroupNumBySql(sql);
	}
	
	/**
	 * 检查是否已经分过组，若以分过返回“true” 否则返回“flase”
	 * @param sportsId
	 * @return String 
	 */
	public String checkGroup(int sportsId){
		return dmd.checkGroup(sportsId);
	}
}
