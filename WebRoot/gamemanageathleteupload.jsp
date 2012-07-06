<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'gamemanageathleteupload.jsp' starting page</title>


  </head>
  
  <body>
    <jsp:useBean id="download" scope = "page" class="com.jspsmart.upload.SmartUpload">
    </jsp:useBean>
  <%
  try{
  
  response.reset();
  out.clear();
  out=pageContext.pushBody();
  download.initialize(pageContext);
  download.setContentDisposition(null);
  String filePath=new String(request.getParameter("filePath").getBytes("gb2312"),"ISO-8859-1");
  download.downloadFile(session.getAttribute("file")+filePath);
  }catch(Exception e){
  out.println("<script>alert('文件下载失败：请检查选择的文件是否存在?')</script>");
  }
  %>  
  </body>
</html>
