<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="text.js" charset="UTF-8"></script>
<title>综合查询</title>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery_gamequery.js"></script> 
<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			 
		});
}
</script>
<style type="text/css">
.tableContent1{
color: #344b50;
font-size: 12px;
background-color:#FFFFFF;
line-height:1.5em;}
select{
	width:100px
}
</style></head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">综合查询-->查询纪录</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    
<table width="100%" border="0">
  <tr>
    <td >
    <hr/>
   
    </td>
  </tr>
  <tr>
    <td>
    <table width="100%" class="tableContent1">
      <tr>
       <td width="41%">参赛届次
             <select name="sportsname" id="sportsname" onchange="show()">
             		<option value="0">--请选择--</option>
               <c:forEach items="${sessionScope.sportsname}" var="sportsname">
               		<option value="${sportsname.id }">${ sportsname.sportsname }</option>
               </c:forEach>
               </select>
       </td>
       <td width="29%">姓&nbsp;&nbsp;&nbsp;&nbsp;名 
           <input name="playername" id="playername" type="text" size="12"/></td>
        
           
    	<td width="30%">系别/部门
         <select id="departname" name="departname" onchange="departname()">
           <option value="0">
             -请选择-
           </option>
          </select></td>
      </tr>
  <tr>
  	  <td>参赛组别
          <select id="province" name="province">
           <option value="0">不限</option>
           </select></td>
     	<td>项目类型
  		<select id="itemtype" name="itemtype" onchange="itemtype()">
  			<option value="0">不限</option>
  			<option value="1">径赛</option>
  			<option value="2">田赛</option>
  			<option value="3">接力</option>
  		</select>
  	</td>
    <td>参赛&nbsp;项目
          <select id="item" name="item">
            <option value="0">-不限-</option>
           </select>
           </td>
 
         
           
    </tr>
    <tr>
     <td>参赛成绩
           <input name="score1" type="text" class="kw" id="score1" size="12" />
           至<input name="score2" type="text" class="kw" id="score2" size="12" />
    </td>
  
       <td>
    		 破&nbsp;纪&nbsp;录<input type="checkbox" id="breakrecord" name="breakrecord"/> 
           </td>
       <td><input type="button" value="查询" onclick="selectdata()"/></td>
    </tr>
    </table>
    </td>
  </tr>
</table>
<hr/>
    <!--内嵌表格begin-->
   <table width="100%" height="100" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" id="infocontent">
    
    </table>
    <!-- 内嵌表格end--> 
    </td>
  </tr>
</table>


<div align="center"><span class="pageJump">共有&nbsp;<b>243</b>&nbsp;条记录，当前第&nbsp;<b>1</b>&nbsp;页，共&nbsp;<b>10</b>&nbsp;页&nbsp;&nbsp;上一页&nbsp;&nbsp;下一页</span></div>
</body>
</html>