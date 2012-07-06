/**
 * Copyright(C) 2012, 河北工业职业技术学院.
 *
 * 模块名称：	登录管理模块
 * 子模块名称：	字符串加密
 *
 * 备注：
 *
 * 修改历史：
 * 时间			版本号		姓名			修改内容
 * 2012-6-8		V1.0		李玮			新建
*/
package cn.edu.hbcit.smms.util;

import java.security.MessageDigest;

/**
 * 字符串加密类
 *
 * 本类的简要描述：
 * 使用MD5进行加密算法
 *
 * @author 李玮
 * @version 1.00  2012-6-8 新建类
 */

public class MD5 {
	private final static String[] hexDigits = {
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};
	/**
    *
    * @param
    * @return 16
    */
   public static String byteArrayToHexString(byte[] b) {
     StringBuffer resultSb = new StringBuffer();
     for (int i = 0; i < b.length; i++) {
       resultSb.append(byteToHexString(b[i]));
     }
     return resultSb.toString();
   }

   private static String byteToHexString(byte b) {
     int n = b;
     if (n < 0)
       n = 256 + n;
     int d1 = n / 16;
     int d2 = n % 16;
     return hexDigits[d1] + hexDigits[d2];
   }
/**
* 使用MD5加密
* @param origin
* @return
*/
   public static String MD5Encode(String origin) {
     String resultString = null;
     try {
       resultString=new String(origin);
       MessageDigest md = MessageDigest.getInstance("MD5");
       resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
     }
     catch (Exception ex) {
     }
     return resultString;
   }

   /**
    * 设置MD5
    * @return <code>String[]</code> 
    * @author Administrator
    * @since 1.0 2005/11/28
    */
   public static String setEncrypt(String str){
       String sn="ziyu"; //密钥
       int[] snNum=new int[str.length()];
       String result="";
       String temp="";

       for(int i=0,j=0;i<str.length();i++,j++){
           if(j==sn.length())
               j=0;
           snNum[i]=str.charAt(i)^sn.charAt(j);
       }

       for(int k=0;k<str.length();k++){

           if(snNum[k]<10){
               temp="00"+snNum[k];
           }else{
               if(snNum[k]<100){
                   temp="0"+snNum[k];
               }
           }
           result+=temp;
       }
       return result;
   }

   /**
    * 得到md5密码
    * @return <code>String[]</code> 
    * @author Administrator
    * @since 1.0 2005/11/28
    */
   public static String getEncrypt(String str){
       String sn="ziyu"; //密钥
       char[] snNum=new char[str.length()/3];
       String result="";

       for(int i=0,j=0;i<str.length()/3;i++,j++){
           if(j==sn.length())
               j=0;
           int n=Integer.parseInt(str.substring(i*3,i*3+3));
           snNum[i]=(char)((char)n^sn.charAt(j));
       }

       for(int k=0;k<str.length()/3;k++){
           result+=snNum[k];
       }
       return result;
   }

}
