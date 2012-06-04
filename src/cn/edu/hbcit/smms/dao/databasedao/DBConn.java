package cn.edu.hbcit.smms.dao.databasedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolException;       
import org.logicalcobwebs.proxool.ProxoolFacade;       
import org.logicalcobwebs.proxool.admin.SnapshotIF;
/**
 * 数据库操作类
 * @author 田小英
 * 修改：2012-06-04 修改数据库连接池  BY liwei
 *
 */
public class DBConn {
	private static Connection conn;
	private static ResultSet rs;
	private static PreparedStatement ps;
	private static String driverClass = "org.logicalcobwebs.proxool.ProxoolDriver";//proxool驱动类
	//private static String url = "jdbc:mysql://localhost:3306/bbs?user=root&password=123";
	private static int activeCount = 0; //活动连接数
	
    /**
     * 获取数据库连接
     * @return Connection
     */
	public Connection getConn() {
		try {
			Class.forName(driverClass); 
			conn = DriverManager.getConnection("proxool.smms"); //此处的smms是在proxool.xml中配置的连接池别名
			this.showSnapshotInfo(); //查看连接池信息
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("数据库连接错误！");
		}
		return conn;
	}
	
	/**     
     * 释放连接     
     * freeConnection     
     * @param conn     
     */
	public void freeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	/**
	 * 关闭数据库连接
	 */
	/* 改用数据库连接池关闭
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
	*/
	/**
	 * 获取连接池中的连接信息
	 */
	private void showSnapshotInfo(){
        try{       
            SnapshotIF snapshot = ProxoolFacade.getSnapshot("smms", true);       
            int curActiveCount=snapshot.getActiveConnectionCount();//获得活动连接数       
            int availableCount=snapshot.getAvailableConnectionCount();//获得可得到的连接数       
            int maxCount=snapshot.getMaximumConnectionCount() ;//获得总连接数       
            if(curActiveCount!=activeCount)//当活动连接数变化时输出的信息       
            {       
             System.out.println("活动连接数:"+curActiveCount+"(active)；可得到的连接数:"+availableCount+"(available)；总连接数:"+maxCount+"(max)");                    
             activeCount=curActiveCount;       
            }
        }catch(ProxoolException e){       
            e.printStackTrace();       
        }       
    }  

}
