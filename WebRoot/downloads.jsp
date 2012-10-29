<%@ page language="java" import="java.util.*,java.io.*,java.net.*" contentType="application/x-msdownload"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>下载</title>
  </head>
  <body>
 
  
  <%
    response.reset();//可以加也可以不加
    response.setContentType("application/x-download");
    String file =(String) session.getAttribute("file");
    String nihao = new String(request.getParameter("nihao").getBytes("ISO-8859-1"),"UTF-8");
   // System.out.println("nihao=============="+nihao);
    //System.out.println("file=============="+file);
      
      //nihao = URLEncoder.encode(nihao,"utf-8");
      response.addHeader("Content-Disposition","attachment;filename=" + new String(nihao.getBytes("UTF-8"),"ISO-8859-1")+".doc");

      OutputStream outp = null;
      FileInputStream in = null;
      try
      {
          outp = response.getOutputStream();
          in = new FileInputStream(file+nihao+".doc");

          byte[] b = new byte[1024];
          int i = 0;

          while((i = in.read(b)) > 0)
          {
              outp.write(b, 0, i);
          }
          outp.flush();
      }
      catch(Exception e)
      {
         // System.out.println("Error!");
          e.printStackTrace();
      }
      finally
      {
          if(in != null)
          {
              in.close();
              in = null;
          }
          if(outp != null)
          {
              outp.close();
              outp = null;
          }
      }
%>

 
 
  </body>
</html>
