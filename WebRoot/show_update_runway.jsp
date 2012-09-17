<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
  <head>
    
    <title>修改运动员跑道</title>
  		<link href="css/subcss.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
		<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
</script>

  </head>
  
  <body>
  
  <table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#a8c7ce" class="stripe_tb">
							<tr><td>第几组</td><td>部门名称(跑道)</td></tr>
             <c:forEach items="${ trackPlayers }" var="temp">
		 		<tr>
		     	   <td>${ temp.groupNum }</td><td>${ temp.players }</td>
		     	</tr>
	          </c:forEach>
 		</table> 
 		<a href="servlet/SelectItemsServlet">返回</a>
 		
    
  </body>
</html>
