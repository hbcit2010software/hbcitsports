/*
* Copyright(C) 2012, XXXXXXXX.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2004/12/12		0.1		张 三		新建
* 2005/02/05		0.1		李 四		Bug修正
*/
package cn.edu.hbcit.smms.services.gamemanageservices;

import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.DocumentException;

import net.sf.json.JSONArray;

import cn.edu.hbcit.smms.dao.gamemanagedao.GameManageCheckTableDao;
import cn.edu.hbcit.smms.dao.gamemanagedao.GameManageCheckTablePrintScanDao;

/**
 * 赛中管理--生成检录表Services类
 *
 *暂时保存Dao里的方法
 *
 *详细解释。
 * @author 杨春华
 * @version 1.00  2011/12/07 新規作成<br>
 */

public class GameManageCheckTableServices {
	GameManageCheckTableDao GCTD = new GameManageCheckTableDao();
	GameManageCheckTablePrintScanDao printScan = new GameManageCheckTablePrintScanDao();
	public ArrayList getGroupList()
	{
		return GCTD.getGroupList();
	}
	public ArrayList  getItemList(int groupid)
	{
		return GCTD.getItemList(groupid);
	}
	
	public boolean isScoreNull( String finalitemname )
	{
		return GCTD.isScoreNull( finalitemname );
	}
	public JSONArray getItemPlayerMessageAllTeam( String finalitemname ,String itemtype  ){
		return GCTD.getItemPlayerMessageAllTeam(finalitemname, itemtype );
	}
	public boolean createDocContext(String file,String finalitemname,String itemType,String groupname) throws Exception{
		
			return printScan.createDocContext(file, finalitemname, itemType,groupname);
	}	
	public String getSportsName(){
		return GCTD.getSportsName();
	}
	
}
