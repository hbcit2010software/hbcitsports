package cn.edu.hbcit.smms.dao.createprogramdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;

/**
 * 
 * @author 韩鑫鹏
 *
 */
public class SetWordConnUtil {

	protected final Logger log = Logger.getLogger(DataManagerDAO.class.getName());
	DBConn db = new DBConn();
	
	/**
	 * 根据finalitemid查询每个组的分组情况
	 * @param finalitemid
	 * @return int[] 
	 */
    public int[] slectGroupInfoByFid(int finalitemid){
    	ArrayList groupInfo = new ArrayList();
    	String sql = "SELECT COUNT(*) FROM t_match  WHERE finalitemid = ? GROUP BY teamnum ORDER BY teamnum";
	    try {
            Connection conn = db.getConn();
            if(conn != null){
            	ResultSet rs = null;
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, finalitemid);
                rs = statement.executeQuery(); 
                while(rs.next()){
                   String pnum= rs.getInt(1)+"";
                   groupInfo.add(pnum);
                }
                rs.close();
            }
        
            db.freeConnection(conn);  
            }catch (SQLException e) {                 
            e.printStackTrace(); } 
            int[] newgroupInfo = new int[groupInfo.size()];
            for (int i = 0; i < groupInfo.size(); i++){
            	newgroupInfo[i] = Integer.parseInt(groupInfo.get(i).toString().trim());
            }
            return newgroupInfo;
    }
    
}
