
package cn.edu.hbcit.smms.dao.createprogramdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.smms.pojo.FildJudgePojo;
import cn.edu.hbcit.smms.pojo.OfficialPojo;
import cn.edu.hbcit.smms.pojo.StudentJudgePojo;


public class SlipDataDao {
	SelectGameBeforInfoDao gd = new SelectGameBeforInfoDao();
	/**
	 * 对田赛中以特殊符号分隔的进行处理
	 * @return Map
	 */
	public Map SlipFildCharactor( int sportId ){
		Map map1 = new HashMap();
		List list = gd.getFildJudge(sportId);
		for( int i = 0; i < list.size(); i++ ){
			FildJudgePojo fJudge = ((FildJudgePojo)list.get(i));
			if(fJudge.getFildJudge1() != null){
				String[] judge1 = ((String)fJudge.getFildJudge1()).split(",");
				map1.put(i+"1", judge1);
			}
			if(fJudge.getFildJudge2() != null){
				String[] judge2 = ((String)fJudge.getFildJudge2()).split(",");
				map1.put(i+"2", judge2);
			}
			if(fJudge.getFildJudge3() != null){
				String[] judge3 = ((String)fJudge.getFildJudge3()).split(",");
				map1.put(i+"3", judge3);
			}
			map1.put("iname"+i, fJudge.getItemName());
		}
		map1.put("裁判长", "裁判长");
		map1.put("助理", "裁判长助理");
		map1.put("裁判员", "裁判员");
		map1.put("length",""+list.size());
		return map1;
	}
	/**
	 * 对工作人员以特殊符号分隔的进行处理
	 * @return
	 */
	
	public Map SlipCharactor( int sportId){
		Map map = new HashMap();
		List list = gd.getOfficialMember(sportId);
		OfficialPojo op = new OfficialPojo();
		for( int i = 0; i < list.size(); i++ ){
			
			//if((String)list.get(i)).indexOf(",") > 0){
			    if( i < 36 ){
				String[] officali =((String)list.get(i)).split(",");
				map.put("offical"+i, officali);
			    }
			//	}
				if( i >= 36 ){
					map.put("offical"+i,(String)list.get(i));        //大赛规程的的几个内容
				}

		}

		map.put(op.getTitle(), op.getTitle());
		map.put(op.getTitle2(), op.getTitle2());
		map.put(op.getTitle3(), op.getTitle3());
		map.put(op.getTitle4(), op.getTitle4());
		map.put(op.getTitle5(), op.getTitle5());
		map.put(op.getTitle6(), op.getTitle6());
		map.put(op.getComm1(), op.getComm1());
		map.put(op.getComm2(), op.getComm2());
		map.put(op.getComm3(), op.getComm3());
		map.put(op.getSecr1(), op.getSecr1());
		map.put(op.getSecr2(), op.getSecr2());
		map.put(op.getSecr3(), op.getSecr3());
		map.put(op.getSecr4(), op.getSecr4());
		map.put(op.getSecr5(), op.getSecr5());
		map.put(op.getSecr6(), op.getSecr6());
		map.put(op.getSecr7(), op.getSecr7());
		map.put("12", op.getChjud1());
		map.put("13", op.getChjud2());
		map.put("14", op.getTrjud14());
		map.put("15", op.getTrjud15());
		map.put("16", op.getTrjud16());
		map.put("17", op.getTrjud17());
		map.put("18", op.getTrjud18());
		map.put("19", op.getTrjud19());
		map.put("20", op.getTrjud20());
		map.put("21", op.getTrjud21());
		map.put("22", op.getTrjud22());
		map.put("23", op.getTrjud23());
		map.put("24", op.getTrjud24());
		map.put("25", op.getTrjud25());
		map.put("26", op.getTrjud26());
		map.put("27", op.getTrjud27());
		map.put("28", op.getTrjud28());
		map.put(op.getFildjud(), op.getFildjud());
		map.put("30", op.getFildjud1());
		map.put("31", op.getFildjud2());
		map.put("32", op.getFildjud3());
		map.put("33", op.getFildjud4());
		map.put("34", op.getFildjud5());
		map.put("35", op.getFildjud6());
		return map;
	}	
	/**
	 * 获取运动会的起止时间，终止时间，还有男女的项目
	 * @return
	 */
		public Map getGameInfo( int sportId ){
			Map map2 =  new HashMap();
			List list1 = gd.getGameDate(sportId);
			List list2 = gd.getItemByMale(sportId);
			List list3 = gd.getItemByFemale(sportId);
			//System.out.println(list1.size());
			String begin = (String)list1.get(0);
			//System.out.println(begin);
			String end = (String)list1.get(1);
			String address = (String)list1.get(2);
			map2.put("date", begin+"-"+end);
			map2.put("address", address);
			map2.put("男子组", list2);
			map2.put("女子组", list3);
			return map2;
		}
		/**
		 * 分隔学生裁判成员
		 * @return
		 */
		public Map SlipStudentJudgeMember( int sportId ){
			Map studentJudgeMember = new HashMap();
			List list = gd.getStudentJudge(sportId);
			for( int i = 0; i < list.size(); i++ ){
				StudentJudgePojo sJudge = ((StudentJudgePojo)list.get(i));
				if(sJudge.getMember() != null){
					String[] judgei = ((String)sJudge.getMember()).split(",");
					studentJudgeMember.put(i+"1", judgei);
				}
				
				studentJudgeMember.put("department"+i, sJudge.getDepartmentName());
				studentJudgeMember.put("contact"+i, sJudge.getContact());
				studentJudgeMember.put("tel"+i, sJudge.getTel());
				
			}
			studentJudgeMember.put("count", ""+list.size());
			return studentJudgeMember;
		}
	

}
