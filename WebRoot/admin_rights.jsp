<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*,cn.edu.hbcit.smms.dao.systemmanagedao.RightsDAO" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理模块</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<!--link href="${pageContext.request.contextPath }/js/jquery.alerts.css" rel="stylesheet" type="text/css" /-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<!--script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.alerts.js"></script-->
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
function setrights(obj){
	var userid = obj.name;
	var MyList = document.getElementsByName(userid);
	var rightVal = 0;
	for(var i=0; i<MyList.length; i++){
		if((MyList[i].checked == true) && (!isNaN(MyList[i].value)) ){
			rightVal += Math.pow(2,parseInt(MyList[i].value));
		}
	}
	//alert(rightVal);
	$.ajax({
			url :"${pageContext.request.contextPath }/servlet/UpdateAccountRightsServlet",
			type : 'post',
			data : 'uid='+userid+'&rightsVal='+rightVal,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("修改权限失败!",function(){window.location.reload();});
					}
				}
			});
}
//
function initpwd(uid){
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/InitUserPasswordServlet",
			type : 'get',
			data : 'uid='+uid,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("密码初始化失败！可能是该帐号的密码已为111111。",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("密码初始化成功！",function(){window.location.reload();});
					}
				}
			});
	}
	//
	function deluser(uid){
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/RemoveAccountServlet",
			type : 'get',
			data : 'uid='+uid,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("帐号删除失败！",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("帐号删除成功！",function(){window.location.reload();});
					}
				}
			});
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
                <td width="94%" valign="bottom"><span class="pageTitle">系统管理-->帐号及权限管理</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> 添加帐号   &nbsp; </span>
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
        <td width="8%" height="20" ><div align="center"><span>用户名</span></div></td>
        <td width="15%" height="20" ><div align="center"><span>用户单位</span></div></td>
        <td width="7%" height="20" ><div align="center"><span>真实姓名</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>系统设置权限</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>赛前设置权限</span></div></td>
        <td width="10%" height="20"><div align="center"><span>秩序册生成权限</span></div></td>
        <td width="10%" height="20"><div align="center"><span>赛中管理权限</span></div></td>
        <td width="10%" height="20"><div align="center"><span>赛事报名权限</span></div></td>
        <td width="15%" height="20"><div align="center"><span>管理操作</span></div></td>
      </tr>
      <c:forEach var="myaccount" items="${account}" varStatus="countItem"> 
       <tr class="tableContent">
        <td><div>${myaccount.username }</div></td>
        <td><div>${myaccount.departShortName }</div></td>
        <td><div>${myaccount.realname }</div></td>
        <c:forTokens var="rights" items="${myaccount.everyRight }" delims="|" varStatus="rightsItem">
        <td><div><input onclick="setrights(this);" type="checkbox" name="${myaccount.id}" value="${rightsItem.count - 1}" id="right_${myaccount.id}_${rightsItem.count - 1}" ${rights}/></div></td>
        </c:forTokens>
       <!-- <td><div><input type="checkbox" name="${myaccount.id}" value="1" id="right_${myaccount.id}_1"/></div></td>
        <td><div><input type="checkbox" name="${myaccount.id}" value="2" id="right_${myaccount.id}_2"/></div></td>
        <td><div><input type="checkbox" name="${myaccount.id}" value="3" id="right_${myaccount.id}_3"/></div></td>
        <td><div><input type="checkbox" name="${myaccount.id}" value="4" id="right_${myaccount.id}_4"/></div></td>
         --> 
        <td><div><a href="#" onclick="Dialog.confirm('提示：您确认要将${myaccount.username }的密码初始化为111111吗？',function(){initpwd(${myaccount.id});});">密码初始化</a> | <a href="#" onclick="Dialog.confirm('提示：您确认要将帐号${myaccount.username }删除吗？',function(){deluser(${myaccount.id});});">删除</a>| <a href="#">修改</a></div></td>
      </tr>
      </c:forEach>
      
    </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />
<div align="center"><span class="pageJump">共有&nbsp;<b>243</b>&nbsp;条记录，当前第&nbsp;<b>1</b>&nbsp;页，共&nbsp;<b>10</b>&nbsp;页&nbsp;&nbsp;上一页&nbsp;&nbsp;下一页</span></div>
</body>
</html>