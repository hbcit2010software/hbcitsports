<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>inputscore.jsp</title>
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
</script>
  </head>
  
  <body>
    <div>
    	<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr>
            	<td bgcolor="#353c44" colspan="9"  height="19">
                    <table>
                    	<tr>
                         <td width="6%"  valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                         <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->赛会纪录</span></td>
                         </tr>
                     </table>
                 </td>
            </tr>
            <tr>
            	<td colspan="9" align="center"><b>决赛成绩录入</b></td>
            </tr>
        </table>
        
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
        	<tr><td colspan="5">100米决赛成绩单</td></tr>
            <tr class="tableTitle" align="center" >
            	<td>编码</td>
                <td>姓名</td>
                <td>道次/出场顺序</td>
                <td>系别</td>
                <td>成绩</td>
            </tr>
            <tr class="tableContent">
            	<td>001</td>
                <td>无国法</td>
                <td>1</td>
                <td>计算机系</td>
                <td>100</td>
            </tr>
            <tr class="tableContent">
            	<td>001</td>
                <td>无国法</td>
                <td>1</td>
                <td>计算机系</td>
                <td>100</td>
            </tr>
            <tr class="tableContent">
            	<td>001</td>
                <td>无国法</td>
                <td>1</td>
                <td>计算机系</td>
                <td>100</td>
            </tr>
            <tr><td colspan="5"><input type="button" value="提交"/><input type="button" value="打印"/></td></tr>
        </table>
    </div>
  </body>
</html>
