
package cn.edu.hbcit.smms.pojo;

/**
 * department表POJO类
 *
 * 本类的简要描述：
 *
 * @author lw
 * @version 1.00  2012-6-13 新建类
 */

public class Department {

	private int id;
	private String departmentName;
	private String departmentShortName;
	private int departmentType;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the departmentShortName
	 */
	public String getDepartmentShortName() {
		return departmentShortName;
	}
	/**
	 * @param departmentShortName the departmentShortName to set
	 */
	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}
	/**
	 * @return the departmentType
	 */
	public int getDepartmentType() {
		return departmentType;
	}
	/**
	 * @param departmentType the departmentType to set
	 */
	public void setDepartmentType(int departmentType) {
		this.departmentType = departmentType;
	}
}
