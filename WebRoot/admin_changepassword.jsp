<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>admin_changepassword.jsp</title>
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
	//
		function updatePWD(){
			var username = document.getElementById("username").value;
			var old = document.getElementById("old").value;
			var new1 = document.getElementById("new1").value;
			var new2 = document.getElementById("new2").value;

			if(old=="" || new1=="" || new2==""){
				Dialog.alert("不能有空项！");
				return false;
			}
			if(new1!=new2){
				Dialog.alert("两次输入的新密码不一致！");
				return false;
			}
			
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/UpdatePasswordServlet",
			type : 'post',
			data : 'username='+username+'&old='+old+'&new1='+new1+'&new2='+new2,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="success"){
						Dialog.alert("密码修改成功！",function(){window.location.reload();});
					}else{
						Dialog.alert("密码修改失败！",function(){window.location.reload();});
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
                <td width="94%" valign="bottom"><span class="pageTitle">系统管理-->修改密码</span></td>
              </tr>
            </table></td>
            <td>
            <!--
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF" onclick="addSports();">添加新运动会</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
            </div>
            -->
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
      <!--tr class="tableTitle">
        <td width="3%" height="20"><div align="center">名称</div></td>
        <td width="25%" height="20" ><div align="center"><span>值</span></div></td>
        </tr-->
         <input type="hidden" id="ruleid" value="${r.id}" />
       <tr class="tableContent">
        <td><div align="right" style="margin-right:10px;">您的用户名：</div></td>
        <td><div align="left" style="margin-left:5px;">${sessionScope.username}<input name="username" type="hidden" id="username" value="${sessionScope.username}"  /></div></td>
        </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">旧密码：</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="old" type="password" id="old" value=""  /></div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">新密码：</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="new1" type="password" id="new1" value=""  />
         </div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">再次输入新密码：</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="new2" type="password" id="new2" value=""  />
         </div></td>
       </tr>
    </table>
    <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />

<div align="center">
	<span class="pageJump">
		<input type="button" value="修  改"  onclick="updatePWD();"/>
	</span>
</div>
</body>
</html>
