<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>My JSP 'cc.jsp' starting page</title>
   <%String msg= (String)session.getAttribute("msg");%>
    <script type="text/javascript">
    
   function show() {
     alert( "<%=msg%>"); 
     location.replace("servlet/ApplyInfomationServlet?action=doPost");
     }
     </script>
  </head>
  
  <body onLoad= "show()">
    
  </body>
</html>
