<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
		
		
function check(){
	var teamleader=$("#teamleader").val();
	var coach=$("#coach").val();
	var doctor=$("#doctor").val();
	if(teamleader == "" || teamleader == null || coach == "" || coach == null || doctor == null || doctor ==""){
		alert("你的领队、教练、队医都不能为空才能提交！");
		return false;
	}
}
		
</script>
</head>

<body>
<form  action="servlet/AddTeamleaderServlet" method="post" onsubmit="return check();"> 
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛事报名-->领队报名</span></td>
              </tr>
            </table></td>
            
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
        <td width="3%" height="20"><div align="center" >领队</div></td>
        <td width="3%" height="20"><div align="center" >教练</div></td>
        <td width="3%" height="20"><div align="center" >队医</div></td>
      </tr>
       <tr class="tableContent">
        <td><div align="center"><input type="text" name="teamleader" id="teamleader"/></div></td>
        <td><div align="center"><input type="text" name="coach" id="coach"/></div></td>
        <td><div align="center"><input type="text" name="doctor" id="doctor"/></div></td>
      </tr>  
    </table>
    	<center><input type="submit" name="button" id="button"  style="width:80px;height:30px;" value="提   交" ></center>
    </form>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>


</body>
</html>
