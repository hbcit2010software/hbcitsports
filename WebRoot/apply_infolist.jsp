<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注意事项</title>
    <link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
<script type="text/javascript">
//隔行变色
$(document).ready(function(){
	$(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		});
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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
  
  <body>
     <br>
     <table id="stuentApply" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
       <tr class="tableTitle">
         <td width="49%" align="center">本届参赛组别</td>
         <td width="51%" align="center">本届参赛项目</td>
       </tr>
       <c:forEach items="${requestScope.group}" var="msg">
       <tr class="tableContent">
         <td align="center">${msg.groupname }</td>
         <td align="center">
         <c:forEach items="${requestScope.itemGroup}" var="td">
         	<c:if test="${msg.groupname eq td.groupname}">
         	${td.itemname} <br/>
         	</c:if>
         </c:forEach>
         </td>
       </tr>
       </c:forEach>
     </table>
  </body>
</html>
