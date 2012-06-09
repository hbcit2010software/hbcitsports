package cn.edu.hbcit.smms.pojo;

public class Account {
	private int id;
	private	String username ;
	private String password;
	private int userright;
	private String realname;
	private int departid;
	private String departname;
	private String departShortName;
	/**
	 * @return the departShortName
	 */
	public String getDepartShortName() {
		return departShortName;
	}
	/**
	 * @param departShortName the departShortName to set
	 */
	public void setDepartShortName(String departShortName) {
		this.departShortName = departShortName;
	}
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userright
	 */
	public int getUserright() {
		return userright;
	}
	/**
	 * @param userright the userright to set
	 */
	public void setUserright(int userright) {
		this.userright = userright;
	}
	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * @return the departid
	 */
	public int getDepartid() {
		return departid;
	}
	/**
	 * @param departid the departid to set
	 */
	public void setDepartid(int departid) {
		this.departid = departid;
	}
	/**
	 * @return the departname
	 */
	public String getDepartname() {
		return departname;
	}
	/**
	 * @param departname the departname to set
	 */
	public void setDepartname(String departname) {
		this.departname = departname;
	}
}
