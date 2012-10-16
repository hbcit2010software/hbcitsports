package cn.edu.hbcit.smms.services.gamemanageservices;

import cn.edu.hbcit.smms.dao.gamemanagedao.GetMessage;
import net.sf.json.JSONArray;

public class GetMessageservices {
	public JSONArray getPlayerMessage( String finalitemname ){
			return new GetMessage().getPlayerMessage(finalitemname);
	}
	
	public String getItemType( String finalitemname ){
		return new GetMessage().getItemType(finalitemname);
	}
	public String getFormatReg( String finalitemname ){
		return new GetMessage().getFormatReg(finalitemname);
		
	}
}
