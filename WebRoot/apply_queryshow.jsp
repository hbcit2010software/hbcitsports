<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>My JSP 'cc.jsp' starting page</title>
   <%String msg= (String)session.getAttribute("msg");%>
    <script type="text/javascript">
   
   function show() {
   	<%
   		String grouptypes = (String)session.getAttribute("match");
   	%>
     alert( "<%=msg%>");
     location.replace("${pageContext.request.contextPath }/servlet/ApplyInfomationServlet?action=pageinf&matchgroup="+<%=grouptypes%>);
     }
     </script>
  </head>
  <body onLoad= "show()">
    
  </body>
</html>
