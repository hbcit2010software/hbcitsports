<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>下载</title>
  </head>
  <body>
  <jsp:useBean id="hehe" class="com.jspsmart.upload.SmartUpload">
  
  </jsp:useBean>
  
  <%
   if(request.getParameter("name")!=null){ 
  try{
 
  hehe.initialize(pageContext);
  hehe.setContentDisposition(null);
  String file=""+request.getSession().getServletContext().getAttribute("path");
  hehe.downloadFile(file);
  }catch(Exception e){
  out.println("<script>alert('文件下载失败：请检查选择的文件是否存在?')</script>");
  }
    }
   %> 
 
   <input type="button" value="下载" name="download"/>  
  </body>
</html>
