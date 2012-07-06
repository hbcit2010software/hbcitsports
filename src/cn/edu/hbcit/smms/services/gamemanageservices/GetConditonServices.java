/*
* Copyright(C) 2004, XXXXXXXX.
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

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamemanagedao.GetCondition;

/**
 * GetConditonServices类
 *
 *简要说明	对应 GetConditon  dao
 *
 *详细解释。
 * @author wuguofa
 * @version 1.00  2012/06/11 新建
 */

public class GetConditonServices {
	
	public int getSportID(){
		return new GetCondition().getSportID();
	}
	
	public ArrayList getAllGP( int sportsid ){
		return new GetCondition().getAllGP(sportsid);
	}
	
	public ArrayList getAllItem( int sportsid ){
		return new GetCondition().getAllItem(sportsid);
	}
	
	public ArrayList selectItemsByGroup( String groupname ,int sportsid ){
		return new GetCondition().selectItemsByGroup(groupname, sportsid);
	}
}
