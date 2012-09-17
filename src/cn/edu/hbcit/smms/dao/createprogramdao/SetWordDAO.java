package cn.edu.hbcit.smms.dao.createprogramdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cn.edu.hbcit.smms.pojo.T_finalitemPojo;
import cn.edu.hbcit.smms.services.createprogramservices.DataManagerServices;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

/**
 * 
 * @author 韩鑫鹏
 *
 */
public class SetWordDAO {

	public void AddGroupInfo(String site, HashMap allGirlPlayers, HashMap allBoyPlayers, 
			HashMap players, HashMap department ) {
		
		DataManagerDAO cpgg = new DataManagerDAO();
		SetWordUtil swd = new SetWordUtil();
		SetWordConnUtil swcu = new SetWordConnUtil();
		Document docu = new Document(PageSize.A4);  
		swd.setWordPath(docu, site);
		ArrayList allBoyFnId = new ArrayList();
		ArrayList allGirlFnId = new ArrayList();
		Iterator allBoy = allBoyPlayers.keySet().iterator();
        	while (allBoy.hasNext()){
        	allBoyFnId.add(allBoy.next());
		}
		Iterator allGirl = allGirlPlayers.keySet().iterator();
        	while (allGirl.hasNext()){
        	allGirlFnId.add(allGirl.next());
		}
		/*****************生成男子项目的所有表格****************************/
        int count = 1;
        String group = "男子组";
        swd.setHead(docu, group);

		for (int i = 0; i < allBoyFnId.size(); i++){
			
			
			int finalitemid = Integer.parseInt(allBoyFnId.get(i).toString());
			
			String itemType = cpgg.selectItemTypeByFinId(finalitemid);
			String groupName = cpgg.selectGrNameByFid(finalitemid);
			
			String gname = swd.getGroupName(groupName, group);
			/********田赛******/
			if (itemType.trim().equals("2")){
				
				ArrayList pnums = cpgg.selectFilePnumByFid(finalitemid, players);
				T_finalitemPojo finalInfo = (T_finalitemPojo)allBoyPlayers.get(allBoyFnId.get(i));
				
				String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
				+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
				swd.addFiledTable(docu, title, pnums, 8);
				count++;
			}
			/********径赛******/
			if (itemType.trim().equals("1")){
				
				T_finalitemPojo finalInfo = (T_finalitemPojo)allBoyPlayers.get(allBoyFnId.get(i));
				String fname = finalInfo.getFinalitemname();
				if (fname.indexOf("1500") >= 0 || fname.indexOf("5000") >= 0 || fname.indexOf("马拉松") >= 0){ //判断是否为长跑
					if (fname.indexOf("1500") >= 0){
						if (finalInfo.getGroupnum() > 0){
							ArrayList pnums = cpgg.select1500ByFinId(finalitemid, players);
							int[] groupInfo = swcu.slectGroupInfoByFid(finalitemid);
							String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
							               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.add1500Table(docu, title, pnums, 9, groupInfo);
							count++;
						}else{
							ArrayList pnums = cpgg.selectFilePnumByFid(finalitemid, players);
							String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
							+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.addFiledTable(docu, title, pnums, 8);
							count++;
						}
					}else{
						ArrayList pnums = cpgg.selectFilePnumByFid(finalitemid, players);
						String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
						+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
						swd.addFiledTable(docu, title, pnums, 8);
						count++;
					}
					
				}else{
					ArrayList pnums = cpgg.selectMatchByFinId(finalitemid, players);
					int[] groupInfo = swcu.slectGroupInfoByFid(finalitemid);
					String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
					               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add100Table(docu, title, pnums, 9, groupInfo);
					count++;
				}
				
			}
			/********接力赛******/
			if (itemType.trim().equals("3")){
				T_finalitemPojo finalInfo = (T_finalitemPojo)allBoyPlayers.get(allBoyFnId.get(i));
				Integer gNum = new Integer(finalInfo.getGroupnum());
				if (gNum == null){
					ArrayList depName = new ArrayList();
					depName = cpgg.selectMatchByFinId(finalitemid, department);
					String title = count+"、" + gname + finalInfo.getFinalitemname() + "，"
		               + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.addFiledTable(docu, title, depName, 6);
					count++;
				}else{
					ArrayList depName = new ArrayList();
					int[] groupNum = swcu.slectGroupInfoByFid(finalitemid);
					depName = cpgg.selectMatchByFinId(finalitemid, department);
					String title = count+"、" + gname + finalInfo.getFinalitemname() + "（" + depName.size() +"队），"
		               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add1500Table(docu, title, depName, 6, groupNum);
					count++;
				}
				
			}
		}
 /*****************生成女子项目的所有表格****************************/
		swd.setHead(docu, "             ");
		int count1 = 1;
        String group2 = "女子组";
        swd.setHead(docu, group2);

		for (int i = 0; i < allGirlFnId.size(); i++){
			
			
			int finalitemid = Integer.parseInt(allGirlFnId.get(i).toString());
			String itemType = cpgg.selectItemTypeByFinId(finalitemid);
			String groupName = cpgg.selectGrNameByFid(finalitemid);
			String gname = swd.getGroupName(groupName, group2);
			
			/********田赛******/
			if (itemType.trim().equals("2")){
				
				ArrayList pnums = cpgg.selectFilePnumByFid(finalitemid, players);
				T_finalitemPojo finalInfo = (T_finalitemPojo)allGirlPlayers.get(allGirlFnId.get(i));
				
				String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
				+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
				swd.addFiledTable(docu, title, pnums, 8);
			}
			/********径赛******/
			if (itemType.trim().equals("1")){
				
				T_finalitemPojo finalInfo = (T_finalitemPojo)allGirlPlayers.get(allGirlFnId.get(i));
				String fname = finalInfo.getFinalitemname();
				if (fname.indexOf("1500") >= 0 || fname.indexOf("5000") >= 0 || fname.indexOf("马拉松") >= 0){ //判断是否为长跑
					if (fname.indexOf("1500") >= 0){
						if (finalInfo.getGroupnum() > 0){
							ArrayList pnums = cpgg.select1500ByFinId(finalitemid, players);
							int[] groupInfo = swcu.slectGroupInfoByFid(finalitemid);
							String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
							               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.add1500Table(docu, title, pnums, 9, groupInfo);
						}else{
							ArrayList pnums = cpgg.selectFilePnumByFid(finalitemid, players);
							String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
							+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
							swd.addFiledTable(docu, title, pnums, 8);
							
						}
					}else{
						ArrayList pnums = cpgg.selectFilePnumByFid(finalitemid, players);
						String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人）"
						+ "，按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
						swd.addFiledTable(docu, title, pnums, 8);
					}
					
				}else{
					ArrayList pnums = cpgg.selectMatchByFinId(finalitemid, players);
					int[] groupInfo = swcu.slectGroupInfoByFid(finalitemid);
					String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + pnums.size() +"人），"
					               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add100Table(docu, title, pnums, 9, groupInfo);
				}
				
			}
			/********接力赛******/
			if (itemType.trim().equals("3")){
				T_finalitemPojo finalInfo = (T_finalitemPojo)allGirlPlayers.get(allGirlFnId.get(i));
				Integer gNum = new Integer(finalInfo.getGroupnum());
				if (gNum == null){
					ArrayList depName = new ArrayList();
					depName = cpgg.selectMatchByFinId(finalitemid, department);
					String title = count1+"、" + gname + finalInfo.getFinalitemname() + "，"
		               + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.addFiledTable(docu, title, depName, 6);
				}else{
					ArrayList depName = new ArrayList();
					int[] groupNum = swcu.slectGroupInfoByFid(finalitemid);
					depName = cpgg.selectMatchByFinId(finalitemid, department);
					String title = count1+"、" + gname + finalInfo.getFinalitemname() + "（" + depName.size() +"队），"
		               + finalInfo.getGroupnum() + "组" + "按成绩取前" + finalInfo.getPromotionnum() + "名参加决赛";
					swd.add1500Table(docu, title, depName, 6, groupNum);
				}
				
			}
			count1++;
		}
         swd.closeDocument(docu);
	}
}
