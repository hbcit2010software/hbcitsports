<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/mark_manager.js"></script>
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/MarkShowHidden.js"></script>
<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt			
		});
		   //层的隐藏  
    $(document).ready(function(){
        $("#departDiv").hide();
        $("#itemDiv").hide();
    });
   
   
   function checkQuery(){
   	var depart = $("#department").find("option:selected").val();
   	var group = $("#group").find("option:selected").val();
   	var departtext = $("#department").find("option:selected").text();
   	var grouptext = $("#group").find("option:selected").text();
   	if( depart == '-1' ){
   		alert('请选择部门');
   		return false;
   	}
   	if( group == '-1' ){
   		alert('请选择组别');
   		return false;
   	}
   	$.ajax({
			url : "${pageContext.request.contextPath}/servlet/QueryMarkServlet?action=QueryMarkdp",
				type : 'get',
				data : {depart:depart,group:group },
				success : function(sum) {
					var inhtml = "<tr><td width=30% height='20'>"+departtext+"</td><td width=30% height='20'>"+grouptext+"</td><td width=40% height='20'>"+sum+"</td></tr>";
					$('#dgQuery').html(inhtml);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		});
   }
   
   function checkItemQuery(){
   		var item = $("#item").find("option:selected").val();
	   	var role = $("#role").find("option:selected").val();
	   	var itemtext = $("#item").find("option:selected").text();
	   	var roletext = $("#role").find("option:selected").text();
	   	//alert(item);alert(role);alert(itemtext);alert(roletext);
	   	if( item == '-1' ){
   			alert('请选择项目');  
   			return false;
	   	}
	   	if( role == '-1' ){
	   		alert('请选择组别');
	   		return false;
	   	}
	   		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/QueryMarkServlet?action=QueryMarkit",
				type : 'get',
				data : {item:item,role:role },
				success : function(sum) {
					var inhtml = "<tr><td width=30% height='20'>"+itemtext+"</td><td width=30% height='20'>"+roletext+"</td><td width=40% height='20'>"+sum+"</td></tr>";
					$('#igQuery').html(inhtml);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		});
   }
   function createMarkDoc(){
   
   		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/QueryMarkServlet?action=QueryMarkit",
				type : 'get',
				success : function(sum) {
					alert(sum);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		});
   }
   
</script>
</head>
<body onload="checkItem(),check()">
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
 <tr>    
  <td height="24" bgcolor="#353c44">  
   <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
   	 <tr bgcolor="#353c44">
   	   <td width="6%" height="19" valign="middle">
   			<div align="center">
   			<img src="images/tb.gif" width="14" height="14" />
   			</div>
   	  </td>
      <td>    
      <span class="pageTitle">赛中设置--&gt;积分管理</span>
     </td>       
    </tr>
   </table>
  </td> 
 </tr>
 <tr><td><input type="button" value="打印积分"/></td></tr>
  <tr>
    <td> 
    <!--内嵌表格begin--> 
    <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" > 
      <tr class="tableTitle"> 
        <td height="20" width="5%"><div align="center">部门</div></td> 
        <td height="20" width="5%"><div align="center">
        	<select id="department">
        	<option>--请选择--</option>
        	</select>
          </div>
        </td> 
        <td height="20" width="5%"><div align="center">组别</div></td> 
        <td height="20" width="5%"><div align="center">
        	<select id="group">
        	<option value='-1'>请选组别</option>
        	<option value='1'>学生</option>
        	<option value='0'>教工</option>
        	<option value='2'>总分</option>        	
        	</select>
          </div>
        </td> 
        <td height="20" width="10%"><div align="center">
          <input type="button" value="查询" onclick="showDepartDiv(),checkQuery()"/>
          </div>
        </td> 
        <td height="20" width="5%"><div align="center">项目</div></td>
        <td height="20" width="5%"><div align="center">
        	<select id="item"> 
        	<option>--请选择--</option>       	       	
        	</select>
        	</div>
        </td> 
        <td height="20" width="5%"><div align="center">组别</div></td>   
        <td height="20" width="5%"><div align="center">
        	<select id="role">
        	<option value='-1'>请选组别</option>
        	<option value="1">学生</option> 
        	<option value="0">教工</option>   	     	 
        	</select>
        	</div>
        </td>
        <td height="20" width="10%"><div align="center">
        <input type="button" value="查询"  onclick="showItemDiv(),checkItemQuery()"/>
        </div>
        </td>       
       </tr>
    </table> 
    <!--内嵌表格end-->  
     </td> 
     </tr>   
</table>
<div id="departDiv">
 <table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" >
 <tr class="tableContent">
 <td width=30% height="20"><div align="center">部门</div></td>
 <td width=30% height="20"><div align="center">组别</div></td>
 <td width=40% height="20"><div align="center">总积分</div></td>
 </tr>
 <tr class="tableContent" >
 <td colspan="3"> 
 <table id="dgQuery" width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb">
 	
 </table>
 </td>
 </tr>
 </table>
</div>
<div id="itemDiv">
 <table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" >
 <tr class="tableContent">
 <td width=30% height="20"><div align="center">项目</div></td>
 <td width=30% height="20"><div align="center">组别</div></td>
 <td width=40% height="20"><div align="center">总积分</div></td>
 </tr>
 <tr class="tableContent" >
 <td colspan="3"> 
 <table id="igQuery" width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" style="">
 </table>
 </td>
 </tr>
 </table>
</div>
<div align="center"><span class="pageJump">共有&nbsp;<b>243</b>&nbsp;条记录，当前第&nbsp;<b>1</b>&nbsp;页，共&nbsp;<b>10</b>&nbsp;页&nbsp;&nbsp;上一页&nbsp;&nbsp;下一页</span></div>
</body>
</html>
