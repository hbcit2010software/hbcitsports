<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注意事项</title>
    <link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" 

type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-

1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath 

}/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath 

}/js/zDrag.js"></script>
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
     <table id="stuentApply" width="100%" border="0" cellpadding="0" cellspacing="1" 

bgcolor="#a8c7ce" class="stripe_tb">
       <tr class="tableTitle">
         <td width="49%" align="center"><h2>本届参赛项目</h2></td>
         <td width="51%" align="center"><h2>参赛组别</h2></td>
       </tr>
       <c:forEach items="${requestScope.itemGroup}" var="msg">
       <tr>
         <td align="center">${msg.itemname }</td>
         <td align="center">${msg.groupname }</td>
       </tr>
       </c:forEach>
       <td width="49%" align="center"><a href="${pageContext.request.contextPath }/servlet/GetItemNameServlet" style="color:#ff0000">进入学生报名

</a></td>
         <td width="51%" align="center"><a href="${pageContext.request.contextPath }/servlet/GetPlayerServlet" style="color:#ff0000">进入教工报名

</a></td>
     </table>
  </body>
</html>
