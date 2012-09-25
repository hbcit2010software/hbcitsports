<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">

$(document).ready(function() {

	$(".stripe_tb tr").mouseover(function() { //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
				$(this).addClass("over");
			}).mouseout(function() { //给这行添加class值为over，并且当鼠标一出该行时执行函数
				$(this).removeClass("over");
			}) //移除该行的class
		$(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt

	});

function uadateJudge(obj) {
	var gp2itid = obj.id;
	var diag = new Dialog();
	diag.Top="50%";
	diag.Left="50%";

	diag.Modal = false;
	
	diag.Width = 350;
	diag.Height = 250;
	diag.Title = "修改田赛裁判";
	diag.URL = "${pageContext.request.contextPath }/servlet/SelectFieldItemByItemnameServlet?gp2itid="+gp2itid;
	diag.OKEvent = function() {
		window.location.reload();

	};

	diag.ShowCloseButton = false;
	diag.MessageTitle = "提示：";
	diag.Message = "修改田赛裁判";
	diag.show();
	diag.okButton.value = "结果刷新";
	diag.cancelButton.value = "关闭";
}
</script>
  </head>
  
  <body>
  <table width="100%" height="24" bgcolor="#353c44" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
         <table width="100%" height="19"  border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置--修改田赛项目裁判</span></td>
			</tr>
		</table> 
		</td>
	</tr>
</table> 
  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
    
    <tr class="tableContent" >
					<td width="20%">
					<div align="center">项目名称</div></td>
					<td>裁判长</td>
					<td>裁判长助理</td>
					<td>裁判员</td>
					<td>修改</td>
				</tr> 
				<c:forEach items="${fieldJudge}" var="item">
				<tr class="tableContent" >
					<td width="20%">
					<div align="center">${item[0]}</div></td>
					<td>${item[2]}</td>
					<td>${item[3]}</td>
					<td>${item[4]}</td>
					<td>
						<a href="#" id="${item[1]}" onclick="uadateJudge(this)">修改</a>
					</td>
				</tr> 
				
	</c:forEach>
	<tr class="tableContent" ><td></td>
	<td></td>
	<td><input type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath }/set_officialjudge.jsp'"></td>
	<td></td>
	<td></td>
	</tr>
</table>
  </body>
</html>
