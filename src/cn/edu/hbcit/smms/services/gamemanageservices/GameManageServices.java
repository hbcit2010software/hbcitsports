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

/**
 * 赛中管理------------>成绩管理------------>service类
 * @author 刘然
 * @version 1.00  2012/06/13 新規作成<br>
 */
public class GameManageServices {

	GameManageDao gm = new GameManageDao();
	GameManageCreateWordOfAthleteInfDao cw = new GameManageCreateWordOfAthleteInfDao();
	
	public ArrayList getGroup()
	{
		return gm.getGroup();
	}

	public String getItemType(int finalItemId)
	{
		return gm.getItemType(finalItemId);
	}
	
	public ArrayList getFinalItem(int groupid)
	{
		return gm.getFinalItem(groupid);
	}
	
	public ArrayList getAthleteList(int finalItemId,String itemType)
	{
		return gm.getAthleteList(finalItemId,itemType);
	}
	
	public boolean deletePlayer(int playerNum)
	{
		return gm.deletePlayer(playerNum);
	}
	
	public ArrayList getPlayerList(int playerNum,int finalItemId)
	{
		return gm.getPlayerList(playerNum,finalItemId);
	}
	
	public boolean updateMatch(int playerNum,String score,int foul,int recordlevel)
	{
		return gm.updateMatch(playerNum, score, foul, recordlevel);
	}
	
	public ArrayList createWordOfAthleteInf(int finalItemId,String itemType)
	{
		return gm.createWordOfAthleteInf(finalItemId,itemType);
	}
	
	public void createDocContext(String file,String titles,ArrayList athleteList) throws DocumentException, IOException
	{
		cw.createDocContext(file,titles,athleteList);
	}
	
	public String createWordOfSportsName(int finalItemId)
	{
		return gm.createWordOfSportsName(finalItemId);
	}
}









