<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
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
function checkItem(){

	var strGI=new Array();
	<c:forEach var="gi" items="${group2item}" varStatus="countItem">
		strGI[${countItem.count - 1}]="${gi.gp2spidItemidMatchtype}";
	</c:forEach>
	var mylist = document.getElementsByName("iteminfo");
	//alert(mylist[0].options[1].value);
	for(var i=0; i<mylist.length; i++){
		for(var j=0; j<strGI.length; j++){
			for(var k=0; k<3; k++){
				if(mylist[i].options[k].value==strGI[j]){
					//alert(mylist[i].options[k].value);
					mylist[i].options[k].selected=true;
					mylist[i].style.borderColor="#F00";
				}
			}
		}
	}
}
function changeBorder(obj){
//alert(obj.options[1].selected);
	if(obj.options[1].selected == true || obj.options[2].selected == true){
		obj.style.borderColor="#F00";
	}else{
		obj.style.borderColor="#CCC";
	}
}
</script>
</head>

<body onLoad="checkItem();">
<form method="post" action="${pageContext.request.contextPath }/servlet/AddGroupToItemServlet" name="form01">
<%
//如果有后台消息传来，则在前台页面弹出提示窗口
if(request.getAttribute("msg") != null){
	out.print("<script type='text/javascript'>Dialog.alert('"+(String)request.getAttribute("msg")+"');</script>");
}
%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->项目设置</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF">添加新运动会</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
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
        <td width="5%" height="20"><div align="center">序号</div></td>
        <td width="13%" height="20" ><div align="center"><span>项目名称</span></div></td>
        <td width="12%" height="20" ><div align="center"><span>项目类型</span></div></td>
       <c:forEach var="ginfo1" items="${groupinfo}">
        <td height="20"><div align="center"><span>${ginfo1.groupname }</span></div></td>
       </c:forEach>
        </tr>
        <!-- 行 -->
       <c:forEach var="iinfo" items="${iteminfo}" varStatus="countItem">
	       <tr class="tableContent">
	        <td><div align="center">${countItem.count}</div></td>
	        <td><div>${iinfo.itemname }</div></td>
	        <td>
		        <c:if test="${iinfo.itemtype eq 1}"><div>径赛</div></c:if>
		        <c:if test="${iinfo.itemtype eq 2}"><div>田赛</div></c:if>
		        <c:if test="${iinfo.itemtype eq 3}"><div>接力</div></c:if>
	        </td>
	        <c:forEach var="ginfo2" items="${groupinfo}">
		        <td><div>
		        
			          <select name="iteminfo" id="${ginfo2.id}${iinfo.id}" style="border-style:solid; border-color:#CCC" onchange="changeBorder(this);">
			            <option value="0">未选择</option>
			            <option value="${ginfo2.id},${iinfo.id},1">预决赛</option>
			            <option value="${ginfo2.id},${iinfo.id},2">预+决</option>
			          </select>

		        </div></td>
	        </c:forEach>
	        </tr>
        </c:forEach>
    </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />

<div align="center">
	<span class="pageJump">
		<input type="submit" value="确  认"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重  置"/>
	</span>
</div>
</form>
</body>
</html>
