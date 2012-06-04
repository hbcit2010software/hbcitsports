/**
 * 
 */
package cn.edu.hbcit.smms.dao.databasedao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Copyright(C) 2012, 河北工业职业技术学院计算机系2010软件专业.
 *
 * 模块名称：     数据库连接池操作测试
 * 子模块名称：   
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号	姓名		修改内容
 * 
 */
/**
 * @author liwei
 *
 */
public class DBTest {

	/**
	 * 数据库测试类
	 *
	 *简要说明
	 *
	 *详细解释。
	 * @author 李玮
	 * @version 1.00  2012/6/4  新建
	 */
	public void getQuery() {
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select * from t_sysadmin"); 
                int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                    System.out.println(); 
                    for(int i=1;i<=c;i++){       
                        System.out.print(rs.getObject(i));
                    }
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      
    }
	
}
