<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>set_playernum.jsp</title>
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
</script>
<script type="text/javascript">
var strFi=new Array();
var fiStu=new Array();
var fiTea=new Array();
	<c:set var="stu" value="0" scope="page" />
	<c:set var="teacher" value="0" scope="page" />
	<c:forEach var="pnl" items="${playerNumList}" varStatus="countItem">
		strFi[${countItem.index}]="${pnl.id}";
		<c:if test="${pnl.numtype eq 1}">
			fiStu[${stu}] = ${pnl.id};
			<c:set var="stu" value="${stu + 1}" scope="page" />
		</c:if>
		<c:if test="${pnl.numtype eq 0}">
			fiTea[${teacher}] = ${pnl.id};
			<c:set var="teacher" value="${teacher + 1}" scope="page" />
		</c:if>
	</c:forEach>

//表单提交检测
function checkSubmit(){
	for(var i=0; i<strFi.length; i++){
		var beginNum = document.getElementById("begin"+strFi[i]).value;
		var endNum = document.getElementById("end"+strFi[i]).value;
		document.getElementById("hidden"+strFi[i]).value = strFi[i] + "," + beginNum + "," + endNum;
		if(beginNum == "" || endNum == ""){
			Dialog.alert("请先自动分配号码！");
			return false;
		}
	}
	//document.forms[0].submit();
}

//分配号码
function addNum(){
	var initStu = 1001;
	var initTeacher = 101;
	var reg = /[0-9]/;
	var step_stu,step_teacher;
	if(reg.test(document.getElementById("step_stu").value) && reg.test(document.getElementById("step_teacher").value)){
		step_stu = parseInt(document.getElementById("step_stu").value);
		step_teacher = parseInt(document.getElementById("step_teacher").value);
		if(step_stu >= 2 && step_teacher >= 2){
			for(var i=0; i<fiStu.length; i++){
				document.getElementById("begin"+fiStu[i]).value = initStu + step_stu*i;
				document.getElementById("end" + fiStu[i]).value = initStu + step_stu*i + step_stu - 1;
			}
			for(var i=0; i<fiTea.length; i++){
				document.getElementById("begin"+fiTea[i]).value = initTeacher + step_teacher*i;
				document.getElementById("end" + fiTea[i]).value = initTeacher + step_teacher*i + step_teacher - 1;
			}
		}else{
			Dialog.alert("输入的数字必须大于等于2！");
		}
		
	
	}else{
		Dialog.alert("请输入数字！");
	}
	
	
	
}
</script>
</head>

<body>
<form method="post" action="${pageContext.request.contextPath }/servlet/UpdatePlayernumServlet" name="form01" onsubmit="return checkSubmit();">
<%
//如果有后台消息传来，则在前台页面弹出提示窗口
if(request.getAttribute("msg") != null){
	out.print("<script type='text/javascript'>Dialog.alert('"+(String)request.getAttribute("msg")+"');</script>");
}
%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->运动员编号设置</span></td>
              </tr>
            </table></td>
            <td>
             <!--  
            <div align="right"><span class="pageTitle">
            红色方框代表当前运动会已选择的项目
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF">添加新运动会</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
            </div> -->  
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
    <!--学生组-->
    	<tr class="tableTitle">
        <td height="20" colspan="3"><div align="center" style="font-weight:bold;">学生组&nbsp;&nbsp;&nbsp;&nbsp;每单位分配<input id="step_stu" type="text" size="5" value="250" />个号码</div></td>
        </tr>
      <tr class="tableTitle">
        <td width="40%" height="20"><div align="center">单位名称</div></td>
        <td width="30%" height="20" ><div align="center">起始号码</div></td>
        <td width="30%" height="20" ><div align="center">结束号码</div></td>
        </tr>
        <!-- 行 -->
       <c:forEach var="pnl" items="${playerNumList}" varStatus="countItem">
       		<c:if test="${pnl.numtype eq 1}">
	       <tr class="tableContent">
	        <td><div align="center">${pnl.departshortname}</div></td>
	        <td><div align="center"><input type="text" id="begin${pnl.id}" value="${pnl.beginnum}" readonly="readonly"/></div></td>
	        <td><div align="center"><input type="text" id="end${pnl.id}" value="${pnl.endnum}" readonly="readonly"/></div></td>
            <input type="hidden" name="hid" id="hidden${pnl.id}" value="" />
	        </tr>
            </c:if>
        </c:forEach>
        <!--教工组-->
    	<tr class="tableTitle">
        <td height="20" colspan="3"><div align="center" style="font-weight:bold;">教工组&nbsp;&nbsp;&nbsp;&nbsp;每单位分配<input id="step_teacher" type="text" size="5" value="50" />个号码</div></td>
        </tr>
      <tr class="tableTitle">
        <td width="40%" height="20"><div align="center">单位名称</div></td>
        <td width="30%" height="20" ><div align="center">起始号码</div></td>
        <td width="30%" height="20" ><div align="center">结束号码</div></td>
        </tr>
        <!-- 行 -->
       <c:forEach var="pnl" items="${playerNumList}" varStatus="countItem">
       		<c:if test="${pnl.numtype eq 0}">
	       <tr class="tableContent">
	        <td><div align="center">${pnl.departshortname}</div></td>
	        <td><div align="center"><input type="text" id="begin${pnl.id}" value="${pnl.beginnum}" readonly="readonly"/></div></td>
	        <td><div align="center"><input type="text" id="end${pnl.id}" value="${pnl.endnum}" readonly="readonly"/></div></td>
            <input type="hidden" name="hid" id="hidden${pnl.id}" value="" />
	        </tr>
        	</c:if>
        </c:forEach>
    </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />

<div align="center">
	<span class="pageJump">
    	<input type="button" value="自动分配号码" onclick="addNum();" />&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="提  交" />
		<!-- 
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重  置"/>
		 -->
	</span>
</div>
</form>
</body>
</html>
