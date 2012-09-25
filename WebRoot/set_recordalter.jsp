<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     
    
    <title>My JSP 'set_recordalter.jsp' starting page</title>
     <link href="${pageContext.request.contextPath }/css/subcss.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.6.min.js">
</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
</script>
<script language="JavaScript">

function alterRecord() {
	var plaName = $("#plaName").val();
 
	var sportsName1 = $("#sportsName1").val();
	var recTime = $('#recTime').val().substring(0, 7);
	var recLevel = $("#recLevel").val();
	var sor = $("#sor").val();
	var depName = $("#depName").val();
 	var recordId = $("#recordId").val();
	var itemName = $("#itemName").val();
	var sx = $("#sx").val();
 
	if (itemName.length == 0) {
		alert("项目名称不能为空!");
		return false;
	}	 
	if (plaName.length == 0) {
		alert("运动员姓名不能为空!");
		return false;
	}
	if (sportsName1.length == 0) {
		alert("运动会名称不能为空!");
		return false;
	}
	if (recLevel.length == 0) {
		alert("等级不能为空!");
		return false;
	}
	if (depName.length == 0) {
		alert("部门不能为空!");
		return false;
	}
	if (sor.length == 0) {
		alert("成绩不能为空!");
		return false;
	}
	if (recTime.length == 0) {
		alert("破记录时间不能为空!");
		return false;
	}
	if (sx.length == 0) {
		alert("性别不能为空!");
		return false;
	}
	var plaSex;
			
			if(sx=="男")
			{
				plaSex = 1;
			}
			
			if(sx=="女")
			{
				plaSex = 0;
			}
 
	$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/AlterRecordServlet",
		type : 'post',
		data : {plaName:plaName,sportsName1:sportsName1,recTime:recTime,recLevel:recLevel,sor:sor,depName:depName,itemName:itemName,plaSex:plaSex,recordId:recordId},
				 
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');

			if (revalue == "success") {
				alert("修改信息成功!");
			} else
				alert("修改信息失败!");
		}
	});
}
</script>

  </head>
  
  <body>
 <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#353c44">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->学生记录基本操作-->修改记录</span></td>
              </tr>
            </table>
            <p></p>
            <p>
              <label>
               
              </label>
            </p>
            <div style="position:relative; margin:0 auto;">
			<table border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
				<c:forEach items="${adminList}" var="es">
            		 
                    
					 <tr class="tableTitle" height="45">
						<td width="127"align="center">
							记录Id
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.recordId}" name="recordId" id="recordId" disabled="disabled" size="36" maxlength="10">						</td>
					</tr>
                	<tr class="tableContent" height="40">
						<td width="127"align="center">
							项目名称
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.itemName}" name="itemName" id="itemName" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent" height="40">
						<td width="127"align="center">
							姓名
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.plaName}" name="plaName" id="plaName" size="36" maxlength="10">						</td>
					</tr>
					
					<tr class="tableContent" height="40">
						<td width="127"align="center">
							性别
						:</td>
						<c:if test="${es.plaSex == 1}"> 
					<td>
						<input type="text" value="男" name="sx" id="sx" size="36" maxlength="10">
					</td>
					</c:if>
					<c:if test="${es.plaSex == 0}"> 
					<td>
						<input type="text" value="女" name="sx" id="sx" size="36" maxlength="10">
					</td>
					</c:if>
						 
							  
					</tr>
					<tr class="tableContent" height="40">
						<td width="127"align="center">
							成绩
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.sor}" name="sor" id="sor" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent" height="40">
						<td width="127"align="center">
							系别
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.depName}" name="depName" id="depName" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent" height="40">
						<td width="127"align="center" height="40">
							运动会名称
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.sportsName1}" name="sportsName1" id="sportsName1" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent" height="40">
						<td width="127"align="center">
							破记录时间
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.recTime}" name="recTime" id="recTime" size="36" maxlength="10" onClick="WdatePicker()">						</td>
					</tr>
					<tr class="tableContent" height="40">
						<td width="127"align="center">
							记录级别
						:</td>
						<td>
							&nbsp;
							<input type="text" value="${es.recLevel}" name="recLevel" id="recLevel" size="36" maxlength="10">						</td>
					</tr>
					</c:forEach>
                    <tr class="tableContent" height="40">
						<td width="127" align="center"><label>
						  <input name="button" type="button" id="button" onClick="alterRecord()" value="保存">
						</label></td>
						<td>
						&nbsp;
						<label>
						<input type="button" name="button2" id="button2" onClick="window.location.href='../set_record.jsp'" value="返回主菜单">
						</label></td>
					</tr>
				</table>
		</div>
	</body>
</html>
