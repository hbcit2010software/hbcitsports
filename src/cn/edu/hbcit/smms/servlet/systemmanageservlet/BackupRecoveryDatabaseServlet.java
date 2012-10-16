/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：		系统管理
 * 子模块名称：	数据库管理
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-13		V1.0	李玮			新建
*/
package cn.edu.hbcit.smms.servlet.systemmanageservlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.util.OperateProperties;

/**
 * 数据库备份与恢复类
 *
 * 本类的简要描述：
 *
 * @author 李玮
 * @version 1.00  2012-6-13 新建类
 */

public class BackupRecoveryDatabaseServlet extends HttpServlet {

	protected final Logger log = Logger.getLogger(BackupRecoveryDatabaseServlet.class.getName());
	/**
	 * Constructor of the object.
	 */
	public BackupRecoveryDatabaseServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String condition=request.getParameter("type").trim();
		String realPath = request.getRealPath("\\WEB-INF");//绝对路径
		boolean  result  =   false ;
        File dirFile  = null ;
        
        try{
            dirFile = new File(realPath+"\\dbback");
             if( !(dirFile.exists()) && !(dirFile.isDirectory()))  {
                 boolean  creadok  =  dirFile.mkdirs();
                 if(creadok){
                 	log.debug(" ok:创建文件夹成功！ ");
                 }else{
                	log.debug(" err:创建文件夹失败！ ");
                 }
            } 
         }catch(Exception e){
            log.error(e);
        }
         if(condition.equals("0"))//备份
 		{
 			if(backup( realPath+"\\dbback" )){
 				log.debug("数据库备份成功！");
 				out.print("success");
 			}
 			else{
 				log.debug("数据库备份失败！");
 				out.print("error");
 			}
 		}
 		if(condition.equals("1"))//恢复
 		{
 			if(load(realPath+"\\dbback" )){
 				log.debug("数据库恢复成功！");
 				out.print("success");
 			}
 			else{
 				log.debug("数据库恢复失败！");
 				out.print("error");
 			}
 		}
         
		out.flush();
		out.close();
	}

	/**  
     * 备份检验一个sql文件是否可以做导入文件用的一个判断方法：把该sql文件分别用记事本和ultra  
     * edit打开，如果看到的中文均正常没有乱码，则可以用来做导入的源文件（不管sql文件的编码格式如何，也不管db的编码格式如何）  
     */  
    public  boolean backup( String path ) {   
    	boolean retuvalue=false;
    	OperateProperties op = new OperateProperties();
    	String key = op.readValue("/db.properties", "backupStr");
    	log.debug("backupStr:"+key);
    	try {   
        	Runtime rt = Runtime.getRuntime();   
            // 调用 mysql 的 cmd:   
            Process child = rt.exec(key);// 设置导出编码为utf8。这里必须是utf8   
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行   
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流   
                          
            InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码   
              
            String inStr;   
            StringBuffer sb = new StringBuffer("");   
            String outStr;   
            // 组合控制台输出信息字符串   
            BufferedReader br = new BufferedReader(xx);   
            while ((inStr = br.readLine()) != null) {   
                sb.append(inStr + "\r\n");   
            }   
            outStr = sb.toString();   
              
            //要用来做导入用的sql目标文件：   
            FileOutputStream fout = new FileOutputStream(path + "\\smms.sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");   
            writer.write(outStr);   
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
            writer.flush();   
  
            // 别忘记关闭输入输出流   
            in.close();   
            xx.close();   
            br.close();   
            writer.close();   
            fout.close();   
            retuvalue=true;
            log.debug("Output OK!");
            //System.out.println(" Output OK! "); 

        } catch (Exception e) {   
            e.printStackTrace();   
            retuvalue=false;
        }   
        return retuvalue;
    }   
  
    /**  
     * 导入  
     *  
     */  
    public  boolean load( String path) {  
    	boolean retuvalue=false;
    	OperateProperties op = new OperateProperties();
    	String key = op.readValue("/db.properties", "recoveryStr");
    	log.debug("recoveryStr:"+key);
        try {   
            String fPath = path + "\\smms.sql";   
            Runtime rt = Runtime.getRuntime();   
  
            // 调用 mysql 的 cmd:   
            Process child = rt.exec(key);   
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流   
            String inStr;   
            StringBuffer sb = new StringBuffer("");   
            String outStr;   
            BufferedReader br = new BufferedReader(new InputStreamReader(   
                    new FileInputStream(fPath), "utf8"));   
            while ((inStr = br.readLine()) != null) {   
                sb.append(inStr + "\r\n");   
            }   
            outStr = sb.toString();   
  
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");   
            writer.write(outStr);   
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
            writer.flush();   
            // 别忘记关闭输入输出流   
            out.close();   
            br.close();   
            writer.close();   
            retuvalue=true;
            log.debug("Load OK!");
            //System.out.println("/* Load OK! */");   
  
        } catch (Exception e) {   
            e.printStackTrace(); 
            retuvalue=false;
        }   
        return retuvalue;
    }
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
