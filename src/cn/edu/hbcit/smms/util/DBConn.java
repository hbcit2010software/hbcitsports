package cn.edu.hbcit.smms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	private static Connection conn;
	private static ResultSet rs;
	private static PreparedStatement ps;
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/bbs?user=root&password=123";
    /**
     * 获取数据库连接
     * @return Connection
     */
	public static Connection getConn() {
		try {
			Class.forName(driverClass); 
			conn = DriverManager.getConnection(url); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("数据库连接错误！");
		}
		return conn;
	}
	/**
	 * 关闭数据库连接
	 */
	public static  void  closeConn(){
		try{
			if( conn != null){
				conn.close();
			}
			if( rs != null){
				rs.close();
			}
			if( ps != null){
				ps.close();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
