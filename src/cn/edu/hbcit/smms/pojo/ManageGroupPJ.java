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

package cn.edu.hbcit.smms.pojo;
/**
 * 组别类
 *
 *简要说明
 *
 *详细解释。
 * @author 吴国法
 * @version 1.00  2012/06/11 新建
 */

public class ManageGroupPJ {
	private int id;
	private String groupname;
	private String grouptype;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

}
