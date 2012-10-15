<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="text.js" charset="UTF-8"></script>
<title>综合查询</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
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
			 var sportsid = $("#sportsname").find("option:selected").val();
		});
function leader(){
	var sportsid = $("#sportsname").find("option:selected").val();
	var departname = $("#departname").find("option:selected").val();
	if(sportsid==0){
		Dialog.alert("请选择届次");
		return false;
	}
	
	
	
	 function verifyAddress(){ 
		var  email = document.getElementById("text").value;
		var pattern = /^([0-5]\d{1}):([0-5]\d{1})$/;
 　　　　　　	var patt = /^(\d{0,4})\.$/;
		flag = pattern.test(email);
	     flag2 = patt.test(email);  
 　　　　　　if(flag||flag2)   
 　　　　　　{
 		Dialog.alert("success");   
 　　　　　　　return true;
 　　　　　　}
 　　　　　　else   
　　　　　　　{   
　　　　　　　　	Dialog.alert("##:(分)##/####.(米)");  
		email = ""; 
		return false;
}   
}   
	$.ajax( {
		url : "servlet/SelectTeamleaderServlet?sportsid="+sportsid+"&departname="+departname,
		type : 'post',
		contentType : "application/json;charset=utf-8",
		dataType : 'json',
		success : function(jsonarray) {
			var inhtml = "";
			Dialog.alert("共查询出"+jsonarray.length+"条");
	inhtml += "<tr class='tableTitle'>";
    inhtml += "<td width='10%' height='20'><div align='center'><span>届次</span></div></td>";
    inhtml += "<td width='10%' height='20'><div align='center'><span>系别</span></div></td>";
    inhtml += "<td width='15%' height='20' ><div align='center'><span>邻队</span></div></td>";
    inhtml += "<td width='14%' height='20' ><div align='center'><span>教练 </span></div></td>";
    inhtml += "<td width='16%' height='20' ><div align='center'><span>队医</span></div></td>";
    inhtml +="</tr>";
    if(jsonarray.length == 0){
    	inhtml += "<tr class='tableContent'>";
        inhtml += "<td width='10%' height='20' colspan='8'><div align='center'><span><h1>没有查到数据请重新查找</h1></span></div></td>";
        inhtml +="</tr>";
    }else{
			for (i = 0; i < jsonarray.length; i++) {
				inhtml += "<tr class='tableContent'>";
		        inhtml += "<td width='15%' height='20' ><div align='center'><span>"+jsonarray[i].sportsname+"</span></div></td>";
		        inhtml += "<td width='14%' height='20' ><div align='center'><span>"+jsonarray[i].departshortname+"</span></div></td>";
	        	inhtml += "<td width='16%' height='20' ><div align='center'><span>"+jsonarray[i].teamleader+"</span></div></td>";
		        inhtml += "<td width='16%' height='20' ><div align='center'><span>"+jsonarray[i].coach+"</span></div></td>";
		        inhtml += "<td width='14%' height='20'><div align='center'><span>"+jsonarray[i].doctor+"</span></div></td>";
		        inhtml +="</tr>";
			}
    }
			$('#infocontent').html(inhtml);
		},
		error : function(xhr, status, errorThrown) {
			Dialog.alert("errorThrown=" + errorThrown);
		}
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
             <select name="sportsname" id="sportsname" onchange="show(),itemtype()">
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
           </select><span>（请先选择项目类型）</span>
           </td>
 
         
           
    </tr>
    <tr>
     <td>参赛成绩
           <input type="text" id="score1" size="12" name="score1" value=""/>至       
           <input type="text" id="score2" size="12" name="score2" value="" />
    </td>
  
       <td>
    		 破&nbsp;纪&nbsp;录<input type="checkbox" id="breakrecord" name="breakrecord"/> 
           </td>
       <td><input type="button" value="成绩查询" onclick="selectdata()"/>
           <input type="button" value="领队查询" onclick="leader()"/>
       </td>
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
</body>
</html>