<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>set_schedule.html</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath }/css/jquery-ui.css"/>
<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath }/css/jquery-ui-timepicker-addon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-timepicker-zh-CN.js"></script>
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

</script>
<script type="text/javascript">
function checkSubmit(){
	var strFi=new Array();
	<c:forEach var="fi" items="${requestScope.finalItemList}" varStatus="countItem">
		strFi[${countItem.index}]="${fi.id}";
	</c:forEach>
	for(var i=0; i<strFi.length; i++){
		var days = document.getElementById("day"+strFi[i]).value;
		var times = document.getElementById("example"+strFi[i]).value;
		var nums = document.getElementById("num"+strFi[i]).value;
		var finalitemtype = document.getElementById("finalitemtype"+strFi[i]).value;  //2012-10-15添加 by liwei
		var gp2itid = document.getElementById("gp2itid"+strFi[i]).value;  //2012-10-15添加 by liwei
		document.getElementById("hidden"+strFi[i]).value = strFi[i] + "," + days + "," + times + "," + nums + "," +finalitemtype+ "," +gp2itid;
		//alert(document.getElementById("hidden"+strFi[i]).value);
	}
	document.forms[0].submit();
}
</script>
<style type="text/css"> 
			#ui-datepicker-div, .ui-datepicker{ font-size: 80%; }
</style>
</head>

<body>
<%
//如果有后台消息传来，则在前台页面弹出提示窗口
if(request.getAttribute("msg") != null){
	out.print("<script type='text/javascript'>Dialog.alert('"+(String)request.getAttribute("msg")+"');</script>");
}
%>
<form action="${pageContext.request.contextPath }/servlet/UpdateFinalItemServlet" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->日程管理</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF">添加新运动会</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
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
      <tr class="tableTitle">
        <td width="5%" height="20"><div align="center">序号</div></td>
        <td width="20%" height="20" ><div align="center"><span>项目名称</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>比赛阶段</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>项目类型</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>运动员类型</span></div></td>
        <td width="15%" height="20" ><div align="center"><span>比赛日期</span></div></td>
        <td width="15%" height="20" ><div align="center"><span>比赛时间</span></div></td>
        <td width="15%" height="20"><div align="center"><span>晋级数量</span></div></td>
      </tr>
      <c:forEach items="${requestScope.finalItemList}" var="finalItem" varStatus="countItem">
       <tr class="tableContent">
        <td><div align="center">${countItem.count }</div></td>
        <td><div align="left" style="margin-left:10px;">${finalItem.finalitemname }</div></td>
        <td>
        	<c:if test="${finalItem.finalitemtype eq 1 }">
        		<div>预赛</div>
        	</c:if>
        	<c:if test="${finalItem.finalitemtype eq 2 }">
        		<div>决赛</div>
        	</c:if>
        	<c:if test="${finalItem.finalitemtype eq 3 }">
        		<div>预决赛</div>
        	</c:if>
        	<input name="finalitemtype" type="hidden" id="finalitemtype${finalItem.id}" value="${finalItem.finalitemtype}" />
        	<input name="gp2itid" type="hidden" id="gp2itid${finalItem.id}" value="${finalItem.gp2itid}" />
        </td>
        <td>
        	<c:if test="${finalItem.itemtype eq 1 }">
        		<div>径赛</div>
        	</c:if>
        	<c:if test="${finalItem.itemtype eq 2 }">
        		<div>田赛</div>
        	</c:if>
        	<c:if test="${finalItem.itemtype eq 3 }">
        		<div>接力</div>
        	</c:if>
        </td>
         <td>
        	<c:if test="${finalItem.grouptype eq 0 }">
        		<div>教工</div>
        	</c:if>
        	<c:if test="${finalItem.grouptype eq 1 }">
        		<div>学生</div>
        	</c:if>
        </td>
        <td><div>
          <select name="select" id="day${finalItem.id}">
          	<c:forEach items="${requestScope.daysList}" var="days">
          		<c:if test="${finalItem.date eq days}">
            		<option selected="selected" value="${days}">${days}</option>
            	</c:if>
            	<c:if test="${finalItem.date ne days}">
            		<option value="${days}">${days}</option>
            	</c:if>
            </c:forEach>
          </select>
        </div></td>
        <!-- 时间选择控件 -->
        <script type="text/javascript">$(function(){$('#example${finalItem.id}').timepicker({});});</script>
        <td><div><input readonly="readonly" name="example${countItem.count }" type="text" id="example${finalItem.id}" value="${finalItem.time}" size="10"  style="text-align:center"/></div></td>
        <td><div>
        <c:if test="${finalItem.promotionnum eq 0}">
        	<input name="promotionnum" type="text" id="num${finalItem.id}" value="8" size="10" style="text-align:center"/>
        </c:if>
        <c:if test="${finalItem.promotionnum ne 0}">
        	<input name="promotionnum" type="text" id="num${finalItem.id}" value="${finalItem.promotionnum}" size="10" style="text-align:center"/>
        </c:if>
        </div></td>
        <input type="hidden" name="finalitemhidden" id="hidden${finalItem.id}" value="" />
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
<div align="center"><span class="pageJump"><input type="button" value="提  交" onclick="checkSubmit()" /></span></div>
</form>
</body>
</html>
