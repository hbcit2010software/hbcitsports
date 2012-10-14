<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>My JSP 'cc.jsp' starting page</title>
   <link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">
//隔行变色
$(document).ready(function(){
	$(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		});
		
function jump(path){
	self.location=path;
}
</script>
<style type="text/css">
<!--
.STYLE1 {font-size: 36px}
a {
	font-size: 16px;
	color: #F00;
}
-->
</style>
   <%request.setCharacterEncoding("utf-8"); %>
  
  </head>
  
  <body>
  <form  action="" method="post"> 
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛事报名-->参赛报名</span></td>
              </tr>
            </table></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <!--内嵌表格begin-->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
    <tr class="tableTitle">
    <td colspan="2">
    <div style="font-size:2em; color:#0000FF; text-align:center;">报名结果提示</div>
   	</td>
   	</tr>
   	<tr class="tableContent">
   	<td colspan="2">
    <table width="35%" border="0" align="center" style="font-size:1em;">
    <c:if test="${empty error}">
      <tr>
        <td colspan="2"><div align="center"><img src="${pageContext.request.contextPath }/images/ok.png">&nbsp;报名成功！</div></td>
      </tr>
 	</c:if>
    	<c:if test="${not empty error}">
   	<c:forEach items="${error}" var="err" >
        <tr>
        <td width="5%"><img src="${pageContext.request.contextPath }/images/no.png"></td>
        <td>${err}</td>
      </tr>
     </c:forEach>
   	</c:if>
	</table>
     </td>
     </tr>
     <tr>
     <td width="49%" align="center"><input type="button" name="button" id="button01" value="继续报名" onClick="jump('${pageContext.request.contextPath }/servlet/GetItemGroupServlet');" /></td>
     <td width="51%" align="center"><input type="button" name="button" id="button02" value="返回查看" onClick="jump('${pageContext.request.contextPath }/servlet/ApplyInfomationServlet?action=doPost');" /></td>
     </tr>
     </table>
     <!--内嵌表格end-->
  </body>
</html>
