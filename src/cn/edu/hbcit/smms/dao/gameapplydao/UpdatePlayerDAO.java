/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	赛事报名
 * 子模块名称：	报名
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-21			        陈系晶                          新建
*/
package cn.edu.hbcit.smms.dao.gameapplydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.pojo.Group;

public class UpdatePlayerDAO {
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	private ResultSet rs = null;
	protected final Logger log = Logger.getLogger(UpdatePlayerDAO.class.getName());
	DBConn db = new DBConn();
	/**
	 * 根据性别和组别类型查找出组别id
	 * @param groupSex
	 * @return
	 */
	public int getGroupIdBySex(boolean playerSex){
		conn = db.getConn();
		String sql = "SELECT id FROM t_group WHERE groupsex=? " +
				"AND grouptype=TRUE";
		int flag = 0;
		 try {
	        	pStatement = conn.prepareStatement(sql);
	        	pStatement.setBoolean(1, playerSex);
	        	rs = pStatement.executeQuery();
	        	while(rs.next()){
					flag = rs.getInt(1);
	        	}
	        	db.freeConnection(conn);
	     }catch(SQLException e){
	    	 e.printStackTrace();
	     }
	     return flag;
	}
	
	/**
	 * 根据运动员号码插入运动员信息
	 * @param playerName
	 * @param playerSex
	 * @param registItem
	 * @param playerNum
	 * @return
	 */
	public int updatePlayerByNum(String playerName,boolean playerSex,
			String registItem,int groupId,int sp2dpid,String playerNum){
		conn = db.getConn();
		//GetPlayerDAO getPlayerDao = new GetPlayerDAO();
		String sql = "update t_player set playername=?,playersex=?,registitem=?," +
				"groupid=? where sp2dpid=? and playernum=?";
		int flag=0;
        try {
        	pStatement = conn.prepareStatement(sql);
        	pStatement.setString(1, playerName);
        	pStatement.setBoolean(2, playerSex);
        	pStatement.setString(3, registItem);
        	pStatement.setInt(4, groupId);
        	pStatement.setInt(5, sp2dpid);
        	pStatement.setString(6, playerNum); 
        	flag = pStatement.executeUpdate(); 
            conn.close();
            db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("添加运动员失败！");
    		log.error(e.getMessage());
    		System.out.println(e);       
        }
        return flag;
	}
	
	/**
	 * 插入（修改）教工（学生）信息
	 */
	public int updatePlayer(String[] allstr,int sp2dpid){
		conn = db.getConn();
		String sql = "update t_player set playername=?,playersex=?,registitem=?," +
				"groupid=? where sp2dpid=? and playernum=?";
		int flag = 0;
		
		try {
			
			for(int i = 0; i < allstr.length; i++ ){
				int count = 0;
				String mystr = allstr[i];
				String[] myPlayer = mystr.split(",");
				for(int k=0; k<myPlayer.length; k++){
					log.debug("K:"+k +" myPlayer[k]:"+myPlayer[k]);
					if(myPlayer[k].equals("")){        //如果有一条记录中有为空的，停止本次循环
						count++;
					}
				}
				if(count > 0){
					log.debug("第"+i +"次:"+ count);
					continue;
				}
				String playerNum = myPlayer[0];//运动员号码
				String playerName = myPlayer[1].trim();//运动员姓名
				boolean playerSex = true;
				int groupid = Integer.parseInt(myPlayer[2]);//组别的id
				System.out.println(groupid);
				String sql1 = "select groupsex from t_group where id="+groupid;//根据组别的id查出性别
				pStatement = conn.prepareStatement(sql1);
				rs = pStatement.executeQuery();
				String sex = null;
				if(rs.next()){
					 sex = rs.getString(1);
				}
				//System.out.println(sex);
				if(sex.equals("1")){
					playerSex = true;
				}else {
					playerSex = false;
				}
				//System.out.println(playerSex);
/*************吕志瑶* 去掉项目类型分隔符* ******************/
				String registItem = "";//myPlayer[3]:运动员所报项目的id "2+3;4+5"
				String[] itemIdArr =  myPlayer[3].split(";");  //{"2+3","4+5"}
				for(int j=0; j<itemIdArr.length; j++){
					String[] tempStr = itemIdArr[j].split("#");  //{"2","3"}
					registItem = registItem + tempStr[0];
					if(j != itemIdArr.length-1){
						registItem += ";";
					}
				}
				//System.out.println("QQQQQQQQQQQQQ:"+registItem);
				
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, playerName);
				pStatement.setBoolean(2, playerSex);
				pStatement.setString(3, registItem);
				pStatement.setInt(4, groupid);
				pStatement.setInt(5, sp2dpid);
				pStatement.setString(6, playerNum); 
				
				flag = pStatement.executeUpdate(); 
					if( flag == 0){
						break;
					}
				pStatement.close();
			}
			conn.close();
			db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("添加运动员失败！");
    		log.error(e.getMessage());
    		System.out.println(e);       
		}
		return flag;
	}
	
	/**
	 * 限制六项
	 * 把学生报名前台传过来的值整理后放进ArrayList中,第一个集合存放要更改数据库的信息，第二个集合存报名出错的运动员的名字
	 * @param pageString 前台隐藏域里面的值
	 * @param sex 学生性别
	 * @param dataInfo 已报项目的运动员的数量Map	
	 * @param perNum 限报人数
	 * @param sp2dpid 组别与运动会id
	 * @return  ArrayList
	 */
	public ArrayList getPageInfo(String[] pageString, HashMap sex, HashMap dataInfo, int perNum,int sp2dpid){
		ArrayList addInfo = new ArrayList(); //要返回的信息
		ArrayList errorInfo = new ArrayList(); //报名超过限制的人的姓名
		HashMap pageInfo = new HashMap(); //前台的信息，<组别+项目id，项目人数>
		StringBuffer addSql = new StringBuffer(); //添加sql语句
		int addCount = 0;
		for (int i = 0; i < pageString.length; i++){
//			log.debug("pageString["+i+"]**********"+pageString[i]);
//			
//			log.debug("pageString[i].length()**********"+pageString[i].length());
//			log.debug("****真真假假******"+(pageString.length < 4 || pageString[1] == null || pageString[1].trim().equals("") || pageString[3] == null 
//					|| pageString[3].trim().equals("")));
			String[] plarerInfo = pageString[i].trim().split(",");
			if (plarerInfo.length < 4 || plarerInfo[1] == null || plarerInfo[1].trim().equals("") || plarerInfo[3] == null 
					|| plarerInfo[3].trim().equals("")){
				errorInfo.add("号码为"+ plarerInfo[0]+ "运动员报名信息不完整，报名不成功");
				continue;
			}
			String playerNum = plarerInfo[0].trim();
			String playerName = plarerInfo[1].trim();
			String playerSex1 = plarerInfo[2].trim();
			log.debug("运动员性别："+playerSex1);
			//int newsex = Integer.parseInt(playerSex);
			int playerSex = 0;
			if (playerSex1.trim().equals("true")){
				playerSex = 1;
			}
			log.debug("运动员性别："+playerSex);
			int group = Integer.parseInt(sex.get((playerSex+"").trim()).toString().trim());
			log.debug("运动员组别别id："+group);
			String[] playerItem = plarerInfo[3].trim().split(";");
			boolean flag = false;
			String pitem = "";
			log.debug("运动员所报项目个数："+playerItem.length);
			for (int j = 0; j < playerItem.length; j++){
				String[] item1 = playerItem[j].trim().split("#");
				String key = (playerSex +""+ item1[0]).trim();
				if (pageInfo.containsKey(key)){
					int number = Integer.parseInt(pageInfo.get(key).toString());
					int newNmuber = number + 1;
					int oldNumber = 0;
					if (dataInfo.containsKey(key)){
						oldNumber = Integer.parseInt(dataInfo.get(key).toString());
					}
					if (newNmuber + oldNumber > perNum){
						flag = true;
					}
				}else{
					int oldNumber = 0;
					if (dataInfo.containsKey(key)){
						oldNumber = Integer.parseInt(dataInfo.get(key).toString());
						if (oldNumber >= perNum){
							flag = true;
						}
					}	
				}
			}
			if (flag == false){
				 for (int playerItemNum = 0; playerItemNum < playerItem.length; playerItemNum++){
					 String[] item1 = playerItem[playerItemNum].trim().split("#");
					 String key = (playerSex +""+ item1[0]).trim();
					 if (pageInfo.containsKey(key)){
						 int newNum = Integer.parseInt(pageInfo.get(key).toString().trim())+1;
						 pageInfo.put(key, newNum+"");
					 }else{
						 pageInfo.put(key, "1");
					 }
					 
				 }
				 for (int j = 0; j < playerItem.length; j++){
					    if (j > 0){
					    	pitem += ";";
					    }
						String[] item1 = playerItem[j].trim().split("#");
						pitem += item1[0];
				 }
				 String temp = "UPDATE t_player SET playername='"+playerName+"',playersex="
				 +playerSex+",groupid="+group+",playertype="+1+",registitem='"+pitem+"' WHERE playernum='"+playerNum+"' AND sp2dpid="+sp2dpid;
				 if (addCount > 0){
					 addSql.append("#");
				 }
				 addSql.append(temp);
				 addCount++;
			 }else{
				    errorInfo.add("姓名为"+ playerName+ "运动员所报项目中有超出限制的项目，报名不成功");
			}
		}
		
		addInfo.add(addSql);
		addInfo.add(errorInfo);
		return addInfo;
	}

	/**
	 * 限制六项
	 * 把教工报名前台传过来的值整理后放进ArrayList中,第一个集合存放要更改数据库的信息，第二个集合存报名出错的运动员的名字
	 * @param pageString 前台隐藏域里面的值
	 * @param group groupid VS sex
	 * @param dataInfo 已报项目的运动员的数量Map	
	 * @param perNum 限报人数
	 * @param sp2dpid 组别与运动会id
	 * @return  ArrayList
	 */
	public ArrayList getTeaPageInfo(String[] pageString, HashMap group, HashMap dataInfo, int perNum,int sp2dpid){
		ArrayList addInfo = new ArrayList(); //要返回的信息
		ArrayList errorInfo = new ArrayList(); //报名超过限制的人的姓名
		HashMap pageInfo = new HashMap(); //前台的信息，<组别+项目id，项目人数>
		StringBuffer addSql = new StringBuffer(); //添加sql语句
		int addCount = 0;
		for (int i = 0; i < pageString.length; i++){
//			log.debug("pageString["+i+"]**********"+pageString[i]);
//			
//			log.debug("pageString[i].length()**********"+pageString[i].length());
//			log.debug("****真真假假******"+(pageString.length < 4 || pageString[1] == null || pageString[1].trim().equals("") || pageString[3] == null 
//					|| pageString[3].trim().equals("")));
			String[] plarerInfo = pageString[i].trim().split(",");
			if (plarerInfo.length < 4 || plarerInfo[1] == null || plarerInfo[1].trim().equals("") || plarerInfo[3] == null 
					|| plarerInfo[3].trim().equals("")){
				errorInfo.add("号码为"+ plarerInfo[0]+ "运动员报名信息不完整，报名不成功");
				continue;
			}
			String playerNum = plarerInfo[0].trim();
			String playerName = plarerInfo[1].trim();
			int groupid = Integer.parseInt(plarerInfo[2].trim());
			//log.debug("运动员性别："+playerSex1);
			//int newsex = Integer.parseInt(playerSex);
			int playerSex = Integer.parseInt(group.get((groupid+"").trim()).toString().trim());
			//log.debug("运动员组别别id："+group);
			String[] playerItem = plarerInfo[3].trim().split(";");
			boolean flag = false;
			String pitem = "";
			log.debug("运动员所报项目个数："+playerItem.length);
			for (int j = 0; j < playerItem.length; j++){
				String[] item1 = playerItem[j].trim().split("#");
				String key = (groupid +""+ item1[0]).trim();
				if (pageInfo.containsKey(key)){
					int number = Integer.parseInt(pageInfo.get(key).toString());
					int newNmuber = number + 1;
					int oldNumber = 0;
					if (dataInfo.containsKey(key)){
						oldNumber = Integer.parseInt(dataInfo.get(key).toString());
					}
					if (newNmuber + oldNumber > perNum){
						flag = true;
					}
				}else{
					int oldNumber = 0;
					if (dataInfo.containsKey(key)){
						oldNumber = Integer.parseInt(dataInfo.get(key).toString());
						if (oldNumber >= perNum){
							flag = true;
						}
					}	
				}
			}
			if (flag == false){
				 for (int playerItemNum = 0; playerItemNum < playerItem.length; playerItemNum++){
					 String[] item1 = playerItem[playerItemNum].trim().split("#");
					 String key = (groupid +""+ item1[0]).trim();
					 if (pageInfo.containsKey(key)){
						 int newNum = Integer.parseInt(pageInfo.get(key).toString().trim())+1;
						 pageInfo.put(key, newNum+"");
					 }else{
						 pageInfo.put(key, "1");
					 }
					 
				 }
				 for (int j = 0; j < playerItem.length; j++){
					    if (j > 0){
					    	pitem += ";";
					    }
						String[] item1 = playerItem[j].trim().split("#");
						pitem += item1[0];
				 }
				 String temp = "UPDATE t_player SET playername='"+playerName+"',playersex="
				 +playerSex+",playertype="+0+",groupid="+groupid+",registitem='"+pitem+"' WHERE playernum='"+playerNum+"' AND sp2dpid="+sp2dpid;
				 if (addCount > 0){
					 addSql.append("#");
				 }
				 addSql.append(temp);
				 addCount++;
			 }else{
				    errorInfo.add("姓名为"+ playerName+ "运动员所报项目中有超出限制的项目，报名不成功");
			}
		}
		
		addInfo.add(addSql);
		addInfo.add(errorInfo);
		return addInfo;
	}
	/**
	 * 根据sp2dpid查询已报运动员的信息
	 * @param sp2dpid
	 * @return
	 */
	public HashMap selectPlayerByspSdpid(int sp2dpid){
		HashMap dataInfo = new HashMap();
		conn = db.getConn();
		String sql = "SELECT t_player.playersex,t_player.registitem FROM t_player JOIN t_group ON t_player.groupid = t_group.id WHERE" +
				" t_group.grouptype=1 AND t_player.sp2dpid=? AND registitem IS NOT null";
        try {
        	pStatement = conn.prepareStatement(sql);
        	pStatement.setInt(1, sp2dpid);
        	rs = pStatement.executeQuery();
        	while(rs.next()){
        		String[] items = rs.getString(2).trim().split(";");
        		int sex = rs.getInt(1);
        		for (int i = 0; i < items.length; i++){
        			String key = (sex + items[i]).trim();
        			if (dataInfo.containsKey(key)){
        				String newNum = (Integer.parseInt(dataInfo.get(key).toString().trim())+1)+"";
        				log.debug("数据库中旧人数："+Integer.parseInt(dataInfo.get(key).toString().trim()));
        				log.debug("数据库中新人数："+newNum);
        				dataInfo.put(key, newNum);
        			}else{
        				dataInfo.put(key, "1");
        			}
        		}
        	}
        	pStatement.close();
            db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("添加hashmap已报运动员失败！");
    		log.error(e.getMessage());   
        }
        return dataInfo;
	}
	
	/**
	 * 根据sp2dpid查询已报教工运动员的信息
	 * @param sp2dpid
	 * @return HashMap k:groupid v:num
	 */
	public HashMap selectTeaPlayerByspSdpid(int sp2dpid){
		HashMap dataInfo = new HashMap();
		conn = db.getConn();
		String sql = "SELECT t_player.groupid,t_player.registitem FROM t_player JOIN t_group ON t_player.groupid = t_group.id WHERE" +
				" t_group.grouptype=0 AND t_player.sp2dpid=? AND registitem IS NOT null";
        try {
        	pStatement = conn.prepareStatement(sql);
        	pStatement.setInt(1, sp2dpid);
        	rs = pStatement.executeQuery();
        	while(rs.next()){
        		String[] items = rs.getString(2).trim().split(";");
        		int sex = rs.getInt(1);
        		for (int i = 0; i < items.length; i++){
        			String key = (sex + items[i]).trim();
        			if (dataInfo.containsKey(key)){
        				String newNum = (Integer.parseInt(dataInfo.get(key).toString().trim())+1)+"";
        				log.debug("数据库中旧人数："+Integer.parseInt(dataInfo.get(key).toString().trim()));
        				log.debug("数据库中新人数："+newNum);
        				dataInfo.put(key, newNum);
        			}else{
        				dataInfo.put(key, "1");
        			}
        		}
        	}
        	pStatement.close();
            db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("添加hashmap已报运动员失败！");
    		log.error(e.getMessage());   
        }
        return dataInfo;
	}
	/**
	 * 根据sportsid查询学生组别信息
	 * @param sportsid
	 * @return
	 */
	public HashMap selectStuGroupByspSdpid(int sportsid){
		HashMap group = new HashMap();
		conn = db.getConn();
		String sql = "SELECT id,groupsex FROM t_group WHERE grouptype=1 AND id IN(SELECT groupid FROM t_group2sports WHERE sportsid=?)";
        try {
        	pStatement = conn.prepareStatement(sql);
        	pStatement.setInt(1, sportsid);
        	rs = pStatement.executeQuery();
        	while(rs.next()){
        		int id = rs.getInt(1);
        		int sex = rs.getInt(2);
        		String key = (sex + "").trim();
        		group.put(key, id+"");
        	}
        	pStatement.close();
            db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("查询学生组别信息失败！");
    		log.error(e.getMessage());   
        }
        return group;
	}
	
	/**
	 * 根据sportsid查询学生组别信息
	 * @param sportsid
	 * @return
	 */
	public HashMap selectTeaGroupByspSdpid(int sportsid){
		HashMap group = new HashMap();
		conn = db.getConn();
		String sql = "SELECT id,groupsex FROM t_group WHERE grouptype=0 AND id IN(SELECT groupid FROM t_group2sports WHERE sportsid=?)";
        try {
        	pStatement = conn.prepareStatement(sql);
        	pStatement.setInt(1, sportsid);
        	rs = pStatement.executeQuery();
        	while(rs.next()){
        		int id = rs.getInt(1);
        		int sex = rs.getInt(2);
        		String value = (sex + "").trim();
        		group.put((id+"").trim(), value);
        	}
        	pStatement.close();
            db.freeConnection(conn);
        }catch (SQLException e) {                 
            log.error("查询教工组别信息失败！");
    		log.error(e.getMessage());   
        }
        return group;
	}
	/**
	 * 根据sql语句修改运动员报名信息
	 * @param sql
	 * @return int
	 */
	public int updatePlayerBySql(String sql){
		conn = db.getConn();
		int flag=0;
		String[] newSql = sql.split("#");
		for(int i = 0; i < newSql.length; i++){
			try {
	        	pStatement = conn.prepareStatement(newSql[i]);
	        	flag = pStatement.executeUpdate(); 
	        	log.debug("????????????????"+flag);
	        	log.debug("????????????????sql:"+newSql[i]);
	        	pStatement.close();
	        	log.error("添加运动员第"+(i+1)+"条运动员信息成功");
	        }catch (SQLException e) {                 
	            log.error("添加运动员失败！");
	    		log.error(e.getMessage());   
	        }
		}
		db.freeConnection(conn);
        return flag;
	}
	 /**
 	 * 根据运动会id查询每个项目各系的限报人数
 	 * @param 运动会id
 	 * @return int 
 	 */
 	public int selectPerDep(int sportsid){
 		
 		int confineNumber = 0;
 		String sql = "SELECT perdepartment FROM t_rule WHERE sportsid=?";
         try {
             Connection conn = db.getConn();
             if(conn != null){
             	
                 PreparedStatement statement = conn.prepareStatement(sql); 
                 statement.setInt(1, sportsid);
                 ResultSet rs = statement.executeQuery(); 
                 while(rs.next()){
                 	confineNumber = rs.getInt(1);
                     }
                 //db.closeRsAll(rs,conn);
                 rs.close();
                 }  
             
             db.freeConnection(conn);  
             }catch (SQLException e) {                 
             e.printStackTrace(); } 
             return confineNumber;
     }
}
