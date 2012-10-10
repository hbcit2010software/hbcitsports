<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
    <title>My JSP 'apply_alterleaderinfo.jsp' starting page</title>
<script type="text/javascript">
//隔行变色
$(document).ready(function(){
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt		
		});
//修改领队、教练、队医
function updateLeader(){
	var teamleader = $("#TeamLeader").val();
	var coach = $("#Coach").val();
	var doctor =$("#Doctor").val();
	if (teamleader.length == 0) {
		alert("请填写领队姓名!");
		return false;
	}
		if (coach.length == 0) {
		alert("请填写教练姓名!");
		return false;
	}
		if (doctor.length == 0) {
		alert("请填写队医姓名!");
		return false;
	}
	$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/UpdateLeaderByUserNameServlet",
		type : 'post',
		data : 'teamleader=' + teamleader +'&coach=' + coach + '&doctor='+ doctor,
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');
			if (revalue == "success"){
				alert("修改成功!",function(){window.location.reload();});
			} else
				alert("修改失败!");
		}
	});
}
</script>
  </head>
  
  <body>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td height="30">
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
     <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
          <td width="6%" height="19" valign="bottom"><div align="center">t<img src="images/tb.gif" width="14" height="14" /></div></td>
           <td width="94%" valign="bottom"><span class="pageTitle">赛事报名-->报名情况查询</span></td>
        </tr>
     </table>
     </td>
       <td>
      <div align="right"><span class="pageTitle">
         <input type="checkbox" name="checkbox11" id="checkbox11" />全选      &nbsp;&nbsp;
         <img src="images/add.gif" width="10" height="10" /> 添加   &nbsp; 
         <img src="images/del.gif" width="10" height="10" /> 删除    &nbsp;&nbsp;
         <img src="images/edit.gif" width="10" height="10" /> 编辑   &nbsp;</span><span class="pageTitle"> &nbsp;</span>
      </div>
      </td>
       </tr>
     </table>
     </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td>
<div><font color="blue">${sessionScope.departName }</font></div>  

<!-- 查询领队的信息begin -->
 <table width="35%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">
 <tr class="tableTitle">
     <td width="4%" height="20"><div align="center"><span>领队</span></div></td>
     <td width="4%" height="20"><div align="center"><span>教练</span></div></td>
     <td width="4%" height="20"><div align="center"><span>队医</span></div></td>
     <td width="4%" height="20"><div align="center"><span>操作</span></div></td>
 </tr>
 <c:forEach items="${requestScope.leader}" var="leader">
 <tr class="tableContent">
     <td width="4%" height="20"><div align="center"><span><input id="TeamLeader" type="text" value="${leader.teamleader}" size="8"/></span></div></td>
     <td width="4%" height="20"><div align="center"><span><input id="Coach" type="text" value="${leader.coach}" size="8"/></span></div></td>
     <td width="4%" height="20"><div align="center"><span><input id="Doctor" type="text" value="${leader.doctor}" size="8"/></span></div></td>
     <td width="4%" height="20"><div align="center"><span><a href="#" onClick="updateLeader()">保存修改</a></span></div></td>
 </tr>
 </c:forEach>
 </table>
 <!-- 查询领队的信息end -->
</td>
</tr>
</table>
  </body>
</html>
