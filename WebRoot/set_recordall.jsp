<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>历届记录</title>
	<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath }/css/themes/base/jquery.ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ui.tabs.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath }/css/demos.css" rel="stylesheet" />
	<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
	</script>
		<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
		
		//
	function updateRecord(id){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 250;
			diag.Title = "修改运动会记录信息";
			diag.URL = "${pageContext.request.contextPath }/servlet/ViewUpdateRecordServlet?id="+id;
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "修改运动会记录提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认修改\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
	}
	//
	function delRecord(id){
	$.ajax({
		url :"${pageContext.request.contextPath }/servlet/RemoveRecordServlet",
		type : 'get',
		data : 'id='+id,
		success :function(mm){
				var revalue=mm.replace(/\r\n/g,'');
				if(revalue=="error"){
					Dialog.alert("删除记录失败！",function(){window.location.reload();});
				}
				if(revalue=="success"){
					Dialog.alert("删除记录成功！",function(){window.location.reload();});
				}
			}
		});
	}
</script>
<style>

#table
{
font-size:16px;}
.STYLE5 {font-size: 12px}
</style> 
		<script>
	function trackShow() {
		document.getElementById("track").style.display = "";
		document.getElementById("field").style.display = "none";
	}
	function fieldShow() {
		document.getElementById("track").style.display = "none";
		document.getElementById("field").style.display = "";
	}
</script>
		<script>
	function trackShow1() {
		document.getElementById("track1").style.display = "";
		document.getElementById("field1").style.display = "none";
	}
	function fieldShow1() {
		document.getElementById("track1").style.display = "none";
		document.getElementById("field1").style.display = "";
	}
	function fieldShow2() {
		document.getElementById("track1").style.display = "none";
		document.getElementById("field1").style.display = "";
		document.getElementById("track2").style.display = "none";
	}
		function fieldShow3() {
		document.getElementById("track1").style.display = "";
		document.getElementById("field1").style.display = "none";
		document.getElementById("track2").style.display = "none";
	}
		function fieldShow4() {
		document.getElementById("track1").style.display = "none";
		document.getElementById("field1").style.display = "none";
		document.getElementById("track2").style.display = "";
	}
</script>

	</head>

	<body>
		<table width="100%" height="31" bgcolor="#353c44" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
         <table width="100%" height="19"  border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->历届记录</span></td>
			</tr>
		</table> 
		</td>
	</tr>
</table>           

<div class="demo">

<div id="tabs">
	<ul class="STYLE5">
		<li><strong><a href="#tabs-1">男子组</a></strong></li>
		<li><strong><a href="#tabs-2">女子组</a></strong></li>
	</ul>
  <div id="tabs-1" >
<!--<p class="STYLE5">赛事预览(查看前请先进行赛事分组)</p>-->
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
    <!--男子组begin-->
    <tr class="tableTitle">
        <td height="20" colspan="8"><div align="center" style="color:#FF0000; font-weight:bold">田径运动会记录（男子组）</div></td>
        </tr>
      <tr class="tableTitle">
        <td ><div align="center"><span>项目</span></div></td>
        <td ><div align="center"><span>成绩</span></div></td>
        <td ><div align="center"><span>运动员</span></div></td>
        <td ><div align="center"><span>系别</span></div></td>
        <td ><div align="center"><span>运动会</span></div></td>
        <td ><div align="center"><span>级别</span></div></td>
        <td ><div align="center"><span>时间</span></div></td>
        <td ><div align="center"><span>操作</span></div></td>
      </tr>
      <c:forEach items="${requestScope.man}" var="temp" varStatus="countItem">
      <tr class="tableContent">
        <td>${temp.itemname}</td>
        <td>${temp.score}</td>
        <td>${temp.playername}</td>
        <td>${temp.departname}</td>
        <td>${temp.sportsname}</td>
        <td>
        <c:if test="${temp.recordlevel eq 0}">
        院级
        </c:if>
        <c:if test="${temp.recordlevel eq 1}">
        省级
        </c:if>
        </td>
        <td>${temp.recordtime}</td>
        <td>
        <a href="javascript:void(0);" onclick="Dialog.confirm('提示：您确认要修改“${temp.playername}”的记录吗？',function(){updateRecord(${temp.id});});">修改</a>
         | 
         <a href="javascript:void(0);" onclick="Dialog.confirm('提示：您确认要删除“${temp.playername}”的记录吗？',function(){delRecord(${temp.id});});">删除</a>
         </td>
      </tr>
     </c:forEach>
    </table>
  </div>
  
  <div id="tabs-2" >
<!--<p class="STYLE5">赛事预览(查看前请先进行赛事分组)</p>-->
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
    <!--男子组begin-->
    <tr class="tableTitle">
        <td height="20" colspan="8"><div align="center" style="color:#FF0000; font-weight:bold">田径运动会记录（女子组）</div></td>
        </tr>
      <tr class="tableTitle">
        <td ><div align="center"><span>项目</span></div></td>
        <td ><div align="center"><span>成绩</span></div></td>
        <td ><div align="center"><span>运动员</span></div></td>
        <td ><div align="center"><span>系别</span></div></td>
        <td ><div align="center"><span>运动会</span></div></td>
        <td ><div align="center"><span>级别</span></div></td>
        <td ><div align="center"><span>时间</span></div></td>
        <td ><div align="center"><span>操作</span></div></td>
      </tr>
      <c:forEach items="${requestScope.woman}" var="temp" varStatus="countItem">
      <tr class="tableContent">
        <td>${temp.itemname}</td>
        <td>${temp.score}</td>
        <td>${temp.playername}</td>
        <td>${temp.departname}</td>
        <td>${temp.sportsname}</td>
        <td>
        <c:if test="${temp.recordlevel eq 0}">
        院级
        </c:if>
        <c:if test="${temp.recordlevel eq 1}">
        省级
        </c:if>
        </td>
        <td>${temp.recordtime}</td>
        <td>
        <a href="javascript:void(0);" onclick="Dialog.confirm('提示：您确认要修改“${temp.playername}”的记录吗？',function(){updateRecord(${temp.id});});">修改</a>
         | 
         <a href="javascript:void(0);" onclick="Dialog.confirm('提示：您确认要删除“${temp.playername}”的记录吗？',function(){delRecord(${temp.id});});">删除</a>
         </td>
      </tr>
     </c:forEach>
    </table>
  </div>


</div>
</div>	
	</body>
</html>
