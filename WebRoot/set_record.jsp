<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>赛前设置</title>
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
	function addRecord(){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 280;
			diag.Title = "添加新记录";
			diag.URL = "${pageContext.request.contextPath }/servlet/ViewCreateRecordServlet";
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "添加新记录提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认添加\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
	}
		
</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->赛会纪录</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right">
             <span class="pageTitle"> <img src="${pageContext.request.contextPath }/images/edit.gif" width="10" height="10" /> <a href="${pageContext.request.contextPath }/servlet/ViewAllRecordServlet" style="color:#FFF">查看历届记录</a> &nbsp;</span>
            <span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="javascript:void(0);" style="color:#FFF" onclick="addRecord();">添加新记录</a> &nbsp;</span>
            </div>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <!--内嵌表格begin-->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
    <!--男子组begin-->
    <tr class="tableTitle">
        <td height="20" colspan="8"><div align="center" style="color:#FF0000; font-weight:bold">田径运动会记录（男子组）</div></td>
        </tr>
      <tr class="tableTitle">
        <td width="5%"><div align="center">序号</div></td>
        <td ><div align="center"><span>项目</span></div></td>
        <td ><div align="center"><span>成绩</span></div></td>
        <td ><div align="center"><span>运动员</span></div></td>
        <td ><div align="center"><span>系别</span></div></td>
        <td ><div align="center"><span>运动会</span></div></td>
        <td ><div align="center"><span>级别</span></div></td>
        <td ><div align="center"><span>时间</span></div></td>
      </tr>
      <c:forEach items="${requestScope.lastRecord_man}" var="temp" varStatus="countItem">
      <tr class="tableContent">
        <td>${countItem.count}</td>
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
      </tr>
     </c:forEach>
     <!--女子组begin-->
     <tr class="tableTitle">
        <td height="20" colspan="8"><div align="center" style="color:#FF0000; font-weight:bold">田径运动会记录（女子组）</div></td>
        </tr>
      <tr class="tableTitle">
        <td ><div align="center">序号</div></td>
        <td ><div align="center"><span>项目</span></div></td>
        <td ><div align="center"><span>成绩</span></div></td>
        <td ><div align="center"><span>运动员</span></div></td>
        <td ><div align="center"><span>系别</span></div></td>
        <td ><div align="center"><span>运动会</span></div></td>
        <td ><div align="center"><span>级别</span></div></td>
        <td ><div align="center"><span>时间</span></div></td>
      </tr>
      <c:forEach items="${requestScope.lastRecord_woman}" var="temp" varStatus="countItem">
      <tr class="tableContent">
        <td>${countItem.count}</td>
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
      </tr>
     </c:forEach>
    </table>
    <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />
<!--
<div align="center"><span class="pageJump">共有&nbsp;<b>243</b>&nbsp;条记录，当前第&nbsp;<b>1</b>&nbsp;页，共&nbsp;<b>10</b>&nbsp;页&nbsp;&nbsp;上一页&nbsp;&nbsp;下一页</span></div>
-->
</body>
</html>
