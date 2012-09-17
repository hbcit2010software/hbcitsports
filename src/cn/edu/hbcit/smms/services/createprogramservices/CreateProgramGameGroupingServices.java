package cn.edu.hbcit.smms.services.createprogramservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.edu.hbcit.smms.dao.createprogramdao.CreateProgramGameGrouping;


/**
 * 用于比赛分组的  servlet
 * @author Administrator 韩鑫鹏
 *
 */
public class CreateProgramGameGroupingServices {

	CreateProgramGameGrouping cpgg = new CreateProgramGameGrouping();
	/**
	 * 根据运动会id查询部门的总数
	 * @param sportsid
	 * @return int
	 */
	public int selectDepartmentSum(int sportsid){
		
		return cpgg.selectDepartmentSum(sportsid);
    }
	
	/**
	 * 根据   运动会id 部门总数    查询部门和运动会联系id 
	 * @param sportsid  运动会id
	 * @param departmentCount  部门数目
	 * @return
	 */
	public int[] selectSp2dpid(int sportsid, int departmentCount){
		
		return cpgg.selectSp2dpid(sportsid, departmentCount);
    }
	
	/**
	 * 根据运动会id查询每个项目各系的限报人数
	 * @param 运动会id
	 * @return int 
	 */
	public int selectPerDepartment(int sportsid){
		
		return cpgg.selectPerDepartment(sportsid);
    }
	
	/**
	 * 根据sql语句添加数据
	 * @param sql  要执行的sql语句
	 * @return int
	 */
	public int addRecordBySql(String sql){
		
		return cpgg.addRecordBySql(sql);
    }
	
	/**
	 * 根据     运动会的id、项目类型         查询该届运动会的所有能报的项目的    组别与运动会联系表id 项目id
	 * @param sportId  运动会id
	 * @param itemtype  项目类型(田赛和径赛还有接力)
	 * @return   
	 */
	public ArrayList selectGroupItem(int sportId, String itemtype){
		
		return cpgg.selectGroupItem(sportId, itemtype);
	}

/**
 * 根据    运动会id  查询    组别id  组别与运动会联系表id  
 * @param sportId    运动会id 
 * @return  HashMap
 */
    public HashMap selectGroupItem(int sportId){
		
    	return cpgg.selectGroupItem(sportId);
	}

    /**
 * 根据运动会的id    查询运动员(以数字+运动员id的形式储存)、  组别与运动会表的id和项目id的结合  HashMap
 * @param sportId
 * @param g2sHashMap  
 * @return
 */
public HashMap selectPlarerIdAndG2IId(int sportId, HashMap g2sHashMap){
	return cpgg.selectPlarerIdAndG2IId(sportId, g2sHashMap);
}

/**
 * 根据运动会的id查询       运动员id、 部门与运动会联系表id   HashMap<运动员id,部门与运动会联系表id>
 * @param sportId 运动会id
 * @return  HashMap
 */
public HashMap selectPlayerIdD2SId(int sportId){
	return cpgg.selectPlayerIdD2SId(sportId);
}

    /**
     * 根据运动会的id查询      最终项目表id（v）  与       组别与项目联系表的id（k）    的对应关系    
     * @param sportId
     * @return  HashMap
     */
    public HashMap selectG2itidVSd2s2gID(int sportId){
    	return cpgg.selectG2itidVSd2s2gID(sportId);
    }
    
    /**
     * 根据运动会的id查询   组别与项目联系表的id<v> 与     组别与运动会联系表的id与项目id联合起来 <k>      的关系
     * @param sportId
     * @return HashMap
     */
    public HashMap selectGp2itid(int sportId){
    	return cpgg.selectGp2itid(sportId);
    }

    /**
     * 根据运动会的id 查询该届运动会的    itemid(k)  itemName(v)
     * @param sportId
     * @return  HashMap
     */
    public HashMap selectT_item(int sportId){
    	return cpgg.selectT_item(sportId);
    }
    
    /**
     * 根据运动会id查询最终项目表女子组信息  finalitemid(k) finalitemPojo(v)
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaGirl(int sportId){
    	return cpgg.selectFlaGirl(sportId);
    }

    /**
     * 根据运动会id查询最终项目表男子组信息
     * @param sportId
     * @return HashMap finalitemid(k) finalitemPojo(v)
     */
    public HashMap selectFlaBoy(int sportId){
    	return cpgg.selectFlaBoy(sportId);
    }

    /**
     * 根据最终项目表的id查询项目类型
     * @param finalItemId
     * @return  String
     */
    public String selectItemTypeByFinId(int finalItemId){
    	return cpgg.selectItemTypeByFinId(finalItemId);
    }
   
    /**
     * 根据最终项目表id径赛100类查询运动员编号(接力也可用此方法查询)
     * @param int finalItemId
     * @param HashMap players
     * @return
     */
    public ArrayList selectMatchByFinId(int finalItemId, HashMap players){
    	return cpgg.selectMatchByFinId(finalItemId, players);
    }

    /**
     * 根据运动会的id查询运动员的id（k）与 号码（v）的关系
     * @param sportsId
     * @return  HashMap
     */
    public HashMap selectPlayersBySid(int sportsId){
    	return cpgg.selectPlayersBySid(sportsId);
    }
    
    /**
     * 根据最终项目表id查询组别名称
     * @param finalItemId
     * @return int
     */
    public String selectGrNameByFid(int finalItemId){
    	return cpgg.selectGrNameByFid(finalItemId);
    }
    
    /**
     * 根据最终项目表的id查询田赛运动员的编号
     * @param int finalItemId
     * @param HashMap players
     * @return ArrayList
     */
    public ArrayList selectFilePnumByFid(int finalItemId, HashMap players){
    	return cpgg.selectFilePnumByFid(finalItemId, players);
    }

    /**
     * 根据最终项目表id径赛查询1500类运动员编号 
     * @param int finalItemId
     * @param HashMap players
     * @return
     */
    public ArrayList select1500ByFinId(int finalItemId, HashMap players){
    	return cpgg.select1500ByFinId(finalItemId, players);
    }

    /**
     * 根据运动会id查询部门的id（k） 名称（V）
     * @param int  sportsid
     * @return  HashMap
     */
    public HashMap selectDepartmentBySid(int sportsid){
    	return cpgg.selectDepartmentBySid(sportsid);
    }
    
    /**
     * 根据运动会id查询T_group表信息 
     * @param sportsid 
     * @return  ArrayList
     */
    public ArrayList selectT_group(int sportsid){
    	return cpgg.selectT_group(sportsid);
    }
    
    /**
     *根据运动会id查询组别与运动会表id（k）与 组别类型（v）
     * @param sportsid
     * @return
     */
    public HashMap selectGtype(int sportsid){
    	return cpgg.selectGtype(sportsid);
    }
    
    /**
     * 根据运动会id查询部门id（k）与部门类型（v）的关系
     * @param sportsid
     * @return HashMap
     */
    public HashMap selectT_epartment(int sportsid){
    	return cpgg.selectT_epartment(sportsid);
    }
    
    /**
     * 根据运动会的id查询运动员id（k）与部门id（v）的关系
     * @param sportsid
     * @return HashMap
     */
    public HashMap selectPid2DidBySid(int sportsid){
    	return cpgg.selectPid2DidBySid(sportsid);
    }
    
    /**
     * 根据运动会id查询最终项目表id（v） 与  组别与运动会联系表id_项目表id的关系（k）
     * @param sportsid
     * @return HashMap
     */
    public HashMap selectFidRg2s2IiD(int sportsid){
    	return cpgg.selectFidRg2s2IiD(sportsid);
    }
    
    /**
     * 根据最终项目id 更改最终项目表里面的groupnum
     * @param groupnum int
     * @param finalitemid int
     */
    public void uodateGroupnumByFid(int groupnum, int finalitemid){
    	cpgg.uodateGroupnumByFid(groupnum, finalitemid);
    }
   
    /**
     * 根据finalitemid查询每个项目的每组的人数
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList selectTrackGroup(int finalitemid){
    	return cpgg.selectTrackGroup(finalitemid);
    }
    
    /**
     * 根据finalitemid查询每个径赛项目的各组运动员  编号（ArrayList）     跑道号（ArrayList）
     * @param finalitemid
     * @return ArrayList       <String>  编号+跑道号
     */
    public ArrayList slectTrackInfo(int finalitemid){
    	return cpgg.slectTrackInfo(finalitemid);
    }
    
    /**
     * 根据finalitemid查询接力的各组部门名
     * @param finalitemid 
     * @return  ArrayList
     */
    public ArrayList slectRelayInfo(int finalitemid){
    	return cpgg.slectRelayInfo(finalitemid);
    }
    
    /**
     * 根据finalitemid得到finalitemid与分组序号finalitemid + ";" + teamnum
     * @param finalitemid
     * @return ArrayList
     */
    public ArrayList slectFidRGnum(int finalitemid){
    	return cpgg.slectFidRGnum(finalitemid);
    }
    
    /**
    * 根据最终项目名查询项目名称
    * @param finalitemid
    * @return
    */
    public String selectItnameByFid(int finalitemid){
    	return cpgg.selectItnameByFid(finalitemid);
    }
    
    /**
     * 根据finalitemid查询长跑类运动员信息
     * @param finalitemid
     * @return  ArrayList  编号+组号
     
    public ArrayList slectTrack1500Ps(int finalitemid){
    	
    	return cpgg.slectTrack1500Ps(finalitemid);
    }*/
   
    
    public Map getTrack1500(int finalitemid){
    	return cpgg.getTrack1500(finalitemid);
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * 根据finalitemid查询田赛类运动员信息
     * @param finalitemid
     * @return  ArrayList  编号
     */
     public ArrayList slectFilePs(int finalitemid){
    	 return cpgg.slectFilePs(finalitemid);
    }
     
     /**
      * 根据finalitemid查询该届运动会报名长跑运动员的id（分组后）    以( id+"")方式存储
      * @param finalitemid
      * @return  ArrayList
      */
     public ArrayList selectPnumByFid(int finalitemid){
          return cpgg.selectPnumByFid(finalitemid);
     }
     
     /**
      * 根据finalitemid删除长跑运动员的分组信息
      * @param finalitemid
      * @return
      */
     public void deletePnumByFid(int finalitemid){
      	
          cpgg.deletePnumByFid(finalitemid);
      }
}
