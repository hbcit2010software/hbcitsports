package cn.edu.hbcit.smms.services.gamemanageservices;

import cn.edu.hbcit.smms.dao.gamemanagedao.AddScore;

public class AddScoreServices {
	public boolean isAddScore( String playernum ,String score , String finalitemname ,String group ){
		return new AddScore().isAddScore(playernum, score, finalitemname , group);
	}
	public String getFormatReg( String finalitemname ){
		return new AddScore().getFormatReg(finalitemname);
		
	}
	public int getIntegral( String finalitemname  ,String group){
		return new AddScore().getIntegral(finalitemname, group);
	}
	public String getFormat( String finalitemname ){
		return new AddScore().getFormat(finalitemname);
	}
	public String getFinalitemType( String finalitemname){
		return new AddScore().getFinalitemType(finalitemname);
	}
}
