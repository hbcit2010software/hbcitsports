package cn.edu.hbcit.smms.services.gamequeryservices;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONArray;

import cn.edu.hbcit.smms.dao.gamequerydao.GameQueryDAO;
import cn.edu.hbcit.smms.dao.gamequerydao.GameQuerySelectInfDAO;
import cn.edu.hbcit.smms.dao.gamequerydao.SelectTeamLeaderDao;
import cn.edu.hbcit.smms.pojo.QueryRegistitemToItems;

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
 * 
 * 
 */
public class GameQueryServices {
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXX类
	 *
	 *简要说明
	 *
	 *详细解释。
	 * @author 张三
	 * @version 1.00  2011/12/07 新規作成<br>
	 */
	GameQueryDAO gqd = new GameQueryDAO();
	GameQuerySelectInfDAO gqs = new GameQuerySelectInfDAO();
	SelectTeamLeaderDao stld = new SelectTeamLeaderDao();
	public ArrayList getSportsName(){
		return gqd.getSportsName();
	}
	public ArrayList selectGroupBySportsId( int sportsid){
		return gqd.selectGroupBySportsId(sportsid);
	}
	public ArrayList selectDepartNameInSports( int sportsid ){
		return gqd.selectDepartNameInSports(sportsid);
	}
	/*
	public ArrayList selectItemName(){
		return gqd.selectItemName();
	}*/
	public ArrayList selectItemNameByType( int sportsid,String itemtype){
		return gqd.selectItemNameByType(sportsid, itemtype);
	}
	public JSONArray selectInQuestion(int sportsid,String sportname,int departname,int province,String itemtype,int item,String score1,String score2,String breakrecord){
		return gqs.selectInQuestion(sportsid, sportname, departname, province, itemtype, item, score1, score2, breakrecord);
	}
	public JSONArray SelectTeamLeader(int sportid,int departid){
		return stld.SelectTeamLeader(sportid, departid);
	}
}
