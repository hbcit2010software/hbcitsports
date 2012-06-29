<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报名情况查询</title>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">
//隔行变色
$(document).ready(function(){
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt		
		}); 
 //查询JSON格式    
function showSelectValue(){ 
    var groupid =$('#selectGroup').find('option:selected').val();
	$.ajax( {
				url : "${pageContext.request.contextPath }/servlet/SelectPlayerByGroupNameServlet",
				type : 'get',
				contentType : "application/json;charset=utf-8",
				dataType : 'json',
				data : 'groupid=' + groupid,
					success : function(json) {
					var inhtml = "";
			    	inhtml += "<table width='100%' border='0' class='stripe_tb' align='center' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>";
					inhtml +="<tr class='tableTitle'>";
					inhtml +="<td width='8%' height='20'><div align='center'><span>运动员号码</span></div></td>";
					inhtml +="<td width='5%' height='20'><div align='center'><span>姓名</span></div></td>";
					inhtml +="<td width='4%' height='20'><div align='center'><span>性别</span></div></td>";
					inhtml +="<td width='60%' height='20'><div align='center'><span>参赛项目</span></div></td>";
					inhtml +="<td width='7%' height='20'><div align='center'><span>操作</span></div></td>";
					inhtml +="</tr>";
					for (i = 0; i < json.contents.length; i++) {
					    inhtml +="<tr class='tableContent'>";
                        inhtml +="<td width='8%' height='20'><div align='center'><span>" + json.contents[i].num + "</span></div></td>";   
                        inhtml += "<td width='5%' height='20'><div align='center'><span>" + json.contents[i].name + "</span></div></td></td>";
                        if( json.contents[i].sex == 1){
                        	inhtml += "<td width='4%' height='20'><div align='center'><span>" + "男" + "</span></div></td>";
                        }
						if( json.contents[i].sex == 0 ){
							inhtml += "<td width='4%' height='20'><div align='center'><span>" + "女" + "</span></div></td>";
						}
						inhtml += "<td width='55%' height='20'><div align='center'><span>" + json.contents[i].item + "</span></div></td>";
						inhtml +="<td width='10%' height='20'><div align='center'><span>";
						inhtml +="<a title='修改' href='${pageContext.request.contextPath }/servlet/SelectAllPlayerByNumServlet?playernum="+json.contents[i].num+"&" + "groupid="+groupid+"'>修改</a>";
						inhtml +="</span></div></td>";
						inhtml +="</tr>";
					}
					inhtml += "</table>";
					$('#div1').html(inhtml);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
			});
}
</script>
  </head>
  <body>
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td height="30">
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
     <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
          <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
           <td width="94%" valign="bottom"><span class="pageTitle">赛事报名-->报名情况查询</span></td>
        </tr>
     </table>
     </td>
       <td>
      <div align="right"><span class="pageTitle">
         <input type="checkbox" name="checkbox11" id="checkbox11" />全选      &nbsp;&nbsp;
         <img src="images/add.gif" width="10" height="10" /> 添加   &nbsp; 
         <img src="images/del.gif" width="10" height="10" /> 删除    &nbsp;&nbsp;
         <img src="images/edit.gif" width="10" height="10" /> 编辑   &nbsp;</span><span class="pageTitle"> &nbsp;</span>
      </div>
      </td>
       </tr>
     </table>
     </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td>
<!--内嵌表格 组别分类 begin-->
    <table width="40%" border="0" align="center">
    <tr>
    <td width="15%" scope="col">参赛组别</td>
    <td width="30%" scope="col">
    <div class="centent">
    <select id="selectGroup" name="selectGroup" >
           <option value="0">--请选择--</option>
           <c:forEach items="${sessionScope.grouplist}" var="grouplist">
           <option value=${grouplist.id } >${ grouplist.groupname }</option>
           </c:forEach>
   </select>
   </div>
   </td>
   <td><input type="button" name="query" id="query" value="查询" onclick="showSelectValue()"/></td>
   </tr>
  </table>
<!--内嵌表格 组别分类 end-->
 <div><font color="blue">${sessionScope.departName }</font></div>
<!-- 用来显示查询结果的层 -->
 <br/> 
  <table width="100%" border="0" class="stripe_tb" id="div1">
  </table>
<!-- 用来显示查询结果的层  end -->
</td>
</tr>
</table>
</body>
</html>
