/*
* Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
*/

package cn.edu.hbcit.smms.services.gamemanageservices;

import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.DocumentException;

import cn.edu.hbcit.smms.dao.gamemanagedao.GameManageCreateWordOfAthleteInfDao;
import cn.edu.hbcit.smms.dao.gamemanagedao.GameManageDao;
import cn.edu.hbcit.smms.pojo.GameManagePoJo;

/**
 * 赛中管理------------>成绩管理------------>service类
 * @author 刘然
 * @version 1.00  2012/06/13 新規作成<br>
 */
public class GameManageServices {

	GameManageDao gm = new GameManageDao();
	GameManageCreateWordOfAthleteInfDao cw = new GameManageCreateWordOfAthleteInfDao();
	
	public ArrayList<GameManagePoJo> getGroup()
	{
		return gm.getGroup();
	}

	public String getItemType(int finalItemId)
	{
		return gm.getItemType(finalItemId);
	}
	
	public ArrayList<GameManagePoJo> getFinalItem(int groupid)
	{
		return gm.getFinalItem(groupid);
	}
	
	public ArrayList<GameManagePoJo> getAthleteList(int finalItemId,String itemType)
	{
		return gm.getAthleteList(finalItemId,itemType);
	}
	
	
	public ArrayList<GameManagePoJo> getPlayerList(int playerNum,int finalItemId)
	{
		return gm.getPlayerList(playerNum,finalItemId);
	}
	
	public String finalItemName(int matchid){
		return new GameManageDao().finalItemName(matchid);
	}
	
	public String groupName(int matchid){
		return new GameManageDao().groupName(matchid);
	}
	
	public void deletePositionPlayer(String finalitemname,int sportsid,String groupname){
		 new GameManageDao().deletePositionPlayer(finalitemname, sportsid, groupname);
	}
	
	public boolean updateMatch(int playerNum,String score,int foul,int recordlevel)
	{
		return gm.updateMatch(playerNum, score, foul, recordlevel);
	}
	
	public void deleteRecordPlayer(String finalitemname,int matchid){
		new GameManageDao().deleteRecordPlayer(finalitemname, matchid);
	}
	
	public ArrayList<GameManagePoJo> createWordOfAthleteInf(int finalItemId,String itemType)
	{
		return gm.createWordOfAthleteInf(finalItemId,itemType);
	}
	
	public void createDocContext(String file,String titles,ArrayList<GameManagePoJo> athleteList) throws DocumentException, IOException
	{
		cw.createDocContext(file,titles,athleteList);
	}
	
	public String createWordOfSportsName(int finalItemId)
	{
		return gm.createWordOfSportsName(finalItemId);
	}
}









