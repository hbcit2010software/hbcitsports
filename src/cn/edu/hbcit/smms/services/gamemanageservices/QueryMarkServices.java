package cn.edu.hbcit.smms.services.gamemanageservices;

import net.sf.json.JSONArray;
import cn.edu.hbcit.smms.dao.gamemanagedao.QueryMark;


public class QueryMarkServices {
	public int queryByGroup( int departid , int sportsid ,int grouptype ){
		return new QueryMark().queryByGroup(departid, sportsid, grouptype);
	}
	
	public int queryBItem( int grouptype, int itemid ){
		return new QueryMark().queryBItem(grouptype, itemid);
	}
	
	public JSONArray getMarkDocMessage( int sportsid ){
		return new QueryMark().getMarkDocMessage(sportsid);
	}
}
