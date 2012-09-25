package cn.edu.hbcit.smms.services.gamesetservices;

import java.util.ArrayList;

import cn.edu.hbcit.smms.dao.gamesetdao.FieldjudgeDAO;

public class FieldjudgeService {
	FieldjudgeDAO fieldjudgeDAO=new FieldjudgeDAO();
	/**
	 * 
	 *修改小框中田赛裁判类
	 * @return
	 */
	public boolean updateFiledItemJudge( int fildJudgeId,String judge_1,String judge_2,String judge_3){
		return fieldjudgeDAO.updateFiledItemJudge(fildJudgeId,judge_1, judge_2, judge_3);
	}
	/**
	 * 根据gp2itid查询田赛裁判类显示在小框中
	 * @param itemname
	 * @return
	 */
	public ArrayList selectFiledItemJudgeByItemname(int gp2itid) {
		return fieldjudgeDAO.selectFiledItemJudgeByItemname(gp2itid);
	}
	/**
	 * 根据sportsid查询所有学生部门
	 * @return
	 */
	public ArrayList selectTeaDepartmentBySportsid(int sportsid) {
		return fieldjudgeDAO.selectStuDepartmentBySportsid(sportsid);
	}
}
