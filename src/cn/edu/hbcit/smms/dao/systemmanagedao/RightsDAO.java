/**
* Copyright(C) 2012, 河北工业职业技术学院.
*
* 模块名称：		系统设置
* 子模块名称：	权限管理
*
* 备注：
*
* 修改历史：
* 时间			版本号		姓名			修改内容
* 2012-06-06	V1.0		李玮			新建
*/
package cn.edu.hbcit.smms.dao.systemmanagedao;

import cn.edu.hbcit.smms.util.UtilTools;
/**
 * 权限控制类
 *
 * 本类的简要描述：
 * 控制系统权限
 * 0-系统设置
 * 1-赛前设置
 * 2-秩序册生成
 * 3-赛中管理
 * 4-赛事报名
 *
 * @author Administrator
 * @version 1.00  2012-6-6 新建类
 */

public class RightsDAO {
	
	/**
	 * 检查权限
	 * @param userPurview
	 * @param optPurview
	 * @return boolean
	 * userPurview是用户具有的总权限
	 * optPurview是一个操作要求的权限为一个整数（没有经过权的！）
	 */
	public boolean checkPower(int userPurview, int optPurview){     
		int purviewValue = (int)Math.pow(2, optPurview);     
		return (userPurview & purviewValue) == purviewValue;     
	}
	
	/**
	 * 获取权限加权和
	 * @param purviewStr
	 * @return
	 */
	public int getPower(String[] purviewStr ){
		UtilTools ut = new UtilTools();
		int sum = 0;
		for(int i=0; i < purviewStr.length; i++){
			//如果purviewStr[i]内的元素为数字类型
			if(ut.isNumeric(purviewStr[i])){
				sum += (int)Math.pow(2, Integer.parseInt(purviewStr[i])); 
			}
		}
		return sum;
	}

}
