<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		function gpchange(){
			var option = $("#group").find("option:selected").text()
			$.ajax({
			
				url:"${pageContext.request.contextPath}/servlet/GetConditonServlet?action=itmecondBygp",
				type : "get",
				contentType : "application/json;charset=utf-8",
				dataType : "json",
				data:{option:option},
				success :function(json){
				
				//var inhtml = "<option>";
				for(int i = 0;i<json.contents.length;i++){
				
					inhtml += "<option>"+json.contents[i].option+"</option>";
					
				}
				
				$("#group").html(inhtml);
				
			},
				error : function(xhr, status, errorThrown) {
						alert("errorThrown=" + errorThrown);
					}
					
			});
		}
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
                         <td width="94%" valign="bottom"><span class="pageTitle">赛前管理-->成绩录入</span></td>
                         </tr>
                     </table>
                 </td>
            </tr>
            <tr>
            	<td colspan="7" align="center"><b>成绩录入</b></td>
            </tr>
            <tr class="tableTitle">
            	<td height="20" colspan="7">请择条件</td>
            </tr>
            <tr class="tableTitle" align="center">
            	<td height="20">组别</td>
                <td height="20">
                	<select onChange="gpchange()">
                    	<option>--请选择--</option>
                    	<c:forEach items="${sessionScope.conditionlist}" var="gp">
                        	<option>${gp.groupname }</option>
                        </c:forEach>
                    </select>
                </td>
                <td height="20">项目名称</td>
                <td height="20">
                	<select id="group">
                    	<option>--请选择--</option>
                    	<c:forEach items="${sessionScope.conditionlist1}" var="item">
                        	<option>${item.itemname}</option>
                        </c:forEach>
                    </select>
                </td>
                <td height="20">赛次</td>
                <td height="20"> 
                	<select>
                    	<option>--请选择--</option>
                        <option>预赛</option>
                        <option>决赛</option>
                        <option>决/预赛</option>
                    </select>
                </td>
                <td><input type="button" value="录入成绩"/></td>
            </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  class="stripe_tb">
            <tr class="tableTitle" align="center" >
            	<td width="20%">编码</td>
                <td width="20%">姓名</td>
                <td width="20%">道次/出场顺序</td>
                <td width="40%">成绩</td>
            </tr>
            <tr><td colspan="4"><input type="button" value="提交"/><input type="button" value="重置"/></td></tr>
        </table>
    </div>
  </body>
</html>
