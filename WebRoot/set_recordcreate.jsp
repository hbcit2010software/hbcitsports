<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>set_recordcreate.jsp</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
<style>
			body { background: #ffffff; color: #444;font-size:12px; }
			a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
			body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
			img, fieldset { border: none 0; }
			body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
			body, input, textarea, select, button { margin: 0; font-size: 12px; font-family: Tahoma, SimSun, sans-serif; }
			div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
			h5 { font-size:12px; }
			ol li,ul li{ margin-bottom:0.5em;}
			pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
			pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
			#content { padding-left:50px; padding-right:50px; }
			#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
			#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
			#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
			#content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
			hr { clear:both; margin:7px 0; +margin: 0;
			border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
			.infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
			.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
			.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
			.input_on{padding:2px 8px 0pt 3px;height:18px;border:1px solid #999;background-color:#eeeeee;}
			
		</style>
        <script language="JavaScript">
        function addScore()
		{
			var reg=/["']/;
			if($('#score').val() == "")
			{
				Dialog.alert("请填写比赛成绩!");
				return false;
			}
			//
			if($('#playername').val() == "")
			{
				Dialog.alert("请填写选手姓名!");
				return false;
			}
			//
			if(reg.test($('#playername').val()))
			{
				Dialog.alert("选手姓名中请不要包含半角单引号或双引号!");
				return false;
			}
			//
			if($('#depart').val() == "")
			{
				Dialog.alert("请填写系部姓名!");
				return false;
			}
			//
			if(reg.test($('#depart').val()))
			{
				Dialog.alert("系部名称中请不要包含半角单引号或双引号!");
				return false;
			}
			//
			if($('#sportsname').val() == "")
			{
				Dialog.alert("请填写运动会名称!");
				return false;
			}
			//
			if(reg.test($('#sportsname').val()))
			{
				Dialog.alert("运动会名称中请不要包含半角单引号或双引号!");
				return false;
			}
			//
			if($('#scoretime').val() == "")
			{
				Dialog.alert("请填写运动会举办时间!");
				return false;
			}
			//
			if($('#level').val() == "none")
			{
				Dialog.alert("请填写记录的级别!");
				return false;
			}
			//
			if($('#item').val() == "none")
			{
				Dialog.alert("请选择比赛项目!");
				return false;
			}
			//ajax提交
			$.ajax({
			url :"${pageContext.request.contextPath }/servlet/AddRecordServlet",
			type : 'post',
			data : 'item='+$('#item').val()+'&sex='+$('#sex').val()+'&score='+$('#score').val()+'&playername='+$('#playername').val()+'&depart='+$('#depart').val()+'&sportsname='+$('#sportsname').val()+'&scoretime='+$('#scoretime').val()+'&level='+$('#level').val(),
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("添加新记录失败!",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("添加新记录成功!",function(){window.location.reload();});
					}
				}
			});
		}
		//
		function checkFormat(obj){
			var regValue = document.getElementById("item").value;
			var reg;
			if(regValue != "none"){
				reg = eval(document.getElementById("hidd_"+regValue).value);
			}else{
				Dialog.alert("请选择比赛项目!");
				obj.value = "";
				return false;
			}
			//alert(reg);
			if(obj.value != ""){
				if(!reg.test(obj.value)){
					Dialog.alert("您输入的成绩格式错误!");
					obj.value = "";
					return false;
				}
			}
			
		}
        </script>
</head>

<body style="margin-top:30px">
<%--生成项目对应的格式--%>
<c:forEach var="myitem" items="${itemlist}">
      	<input type="hidden" id="hidd_${myitem.itemid}" value="${myitem.format}"/>
</c:forEach>
<table width="100%" border="0" align="center" cellspacing="0" cellpadding="2" style="border-collapse: collapse" bordercolor="#C0C0C0">
  <tr>
    <td align="right" width="150">比赛项目：</td>
    <td>
    <select name="item" id="item">
      <option value="none">-请选择项目-</option>
      <c:forEach var="myitem" items="${itemlist}">
      	<option value="${myitem.itemid }">${myitem.itemname}</option>
      </c:forEach>
    </select>
    </td>
  </tr>
  <tr>
    <td align="right">选手性别：</td>
    <td>
    <select name="sex" id="sex">
      <option value="1">男</option>
      <option value="0">女</option>
    </select>
    </td>
  </tr>
  <tr>
    <td align="right">比赛成绩：</td>
    <td><input name="score" type="text" id="score" onblur="checkFormat(this);"></td>
  </tr>
  <tr>
    <td align="right">选手姓名：</td>
    <td><input name="playername" type="text" id="playername" ></td>
  </tr>
  <tr>
    <td align="right">系别：</td>
    <td><input name="depart" type="text" id="depart" ></td>
  </tr>
  <tr>
    <td align="right">运动会名称：</td>
    <td><input name="sportsname" type="text" id="sportsname"></td>
  </tr>
  <tr>
    <td align="right">破记录时间：</td>
    <td><input readonly="readonly" name="scoretime" class="Wdate" type="text" id="scoretime" onClick="WdatePicker({dateFmt:'yyyy-MM'})"></td>
  </tr>
    <tr>
    <td align="right" width="150">记录级别：</td>
    <td>
    <select name="level" id="level">
      <option value="none">-请选择级别-</option>
      <option value="0">院级</option>
      <option value="1">省级</option>
    </select>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input name="" type="button" value="确认添加" onclick="addScore();"/></td>
  </tr>
</table>
</body>
</html>