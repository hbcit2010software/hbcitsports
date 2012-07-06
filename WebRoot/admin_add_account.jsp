<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
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
        function adduser()
		{
			var MyList = document.getElementsByName("rights");
			var rightVal = 0;
			for(var i=0; i<MyList.length; i++){
				if((MyList[i].checked == true) && (!isNaN(MyList[i].value)) ){
					rightVal += Math.pow(2,parseInt(MyList[i].value));
				}
			}
			//ajax提交
			$.ajax({
			url :"${pageContext.request.contextPath }/servlet/AddAccountServlet",
			type : 'post',
			data : 'uname='+$('#uname').val()+'&rightsVal='+rightVal+'&realname='+$('#realname').val()+'&departid='+$('#depart').val(),
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("添加新帐号失败!",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("添加新帐号成功!初始密码为111111",function(){window.location.reload();});
					}
				}
			});
		}
        </script>
</head>

<body style="margin-top:30px">

<table width="100%" border="0" align="center" cellspacing="0" cellpadding="2" style="border-collapse: collapse" bordercolor="#C0C0C0">
  <tr>
    <td align="right" width="150">用户名：</td>
    <td><label>
      <input type="text" name="uname" id="uname" />
    </label></td>
  </tr>
  <tr>
    <td align="right">所属部门：</td>
    <td>
      <select name="depart" id="depart">
       <c:forEach var="mydepart" items="${depart}" varStatus="countItem"> 
        <option value="${mydepart.id }">${mydepart.departmentShortName }</option>
      </c:forEach>
      </select>
    </td>
  </tr>
  <tr>
    <td align="right">真实姓名：</td>
    <td><input type="text" name="realname" id="realname" /></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><b>请选择该用户的权限</b></td>
  </tr>
  <tr>
    <td align="right">系统设置权限：</td>
    <td><input type="checkbox" name="rights" id="rights_0" value="0" /></td>
  </tr>
  <tr>
    <td align="right">赛前设置权限：</td>
    <td><input type="checkbox" name="rights" id="rights_1"  value="1" /></td>
  </tr>
  <tr>
    <td align="right">秩序册生成权限：</td>
    <td><input type="checkbox" name="rights" id="rights_2"  value="2" /></td>
  </tr>
  <tr>
    <td align="right">赛中管理权限：</td>
    <td><input type="checkbox" name="rights" id="rights_3"  value="3" /></td>
  </tr>
  <tr>
    <td align="right">赛事报名权限：</td>
    <td><input type="checkbox" name="rights" id="rights_4"  value="4" /></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input name="" type="button" value="确认添加" onclick="adduser();"/></td>
  </tr>
</table>
</body>
</html>