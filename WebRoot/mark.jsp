<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>赛前设置</title>
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
		
	//打印成绩单
	function printMarks(){
		
		     $.ajax ({
		     
		       url : "${ pageContext.request.contextPath }/servlet/PrintAllMarkSerlvet",
               type : 'post',
               //data : 'finalItemId=' + finalItemId,
               contentType : "application/json;charset=utf-8",

			   dataType : 'json',
               success : function(json) {
               
				 Dialog.alert("成功生成此文档，请点击连接下载excel附件");
				 var inhtml = "";
				   
				 for(i=0;i<json.contents.length;i++)	
				 {		
				   //alert(json.contents[i].fileName1);
				   inhtml = "<a href='download.jsp?file="+json.contents[i].file+"&fileName="+ encodeURIComponent(encodeURIComponent(json.contents[i].fileName)) +"'>"+ json.contents[i].fileName +"</a>";
                   $("#aOfUpload").html(inhtml);
                 }  
               }, 
               error : function(xhr, status, errorThrown) {
					alert("服务器连接出错了");
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛中管理-->积分管理</span></td>
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
    <!--学生实得积分begin-->
    <tr class="tableTitle">
        <td height="20" colspan="8"><div align="center" style="color:#FF0000; font-weight:bold">学生实得积分</div></td>
        </tr>
      <tr class="tableTitle">
        <td ><div align="center">部门</div></td>
        <td ><div align="center"><span>学生男子组实得积分</span></div></td>
        <td ><div align="center"><span>名次</span></div></td>
        <td ><div align="center"><span>学生女子组实得积分</span></div></td>
        <td ><div align="center"><span>名次</span></div></td>
        <td ><div align="center"><span>总积分</span></div></td>
        <td ><div align="center"><span>名次</span></div></td>
      </tr>
      <c:forEach items="${sessionScope.allMarkInfo}" var="temp">
      <tr class="tableContent">
      <c:forEach items="${ temp }" var="markInfo">
        <td>${ markInfo }</td>
        
        </c:forEach>
      </tr>
     </c:forEach>
     <tr><a href="#" onclick='printMarks()'>打印学生积分明细</a></tr>
  
     <!--教工实得积分begin-->
     <tr class="tableTitle">
        <td height="20" colspan="8"><div align="center" style="color:#FF0000; font-weight:bold">教工实得积分</div></td>
        </tr>
      <tr class="tableTitle">
        <td ><div align="center">部门</div></td>
        <td ><div align="center"><span>教工男子组实得积分</span></div></td>
        <td ><div align="center"><span>名次</span></div></td>
        <td ><div align="center"><span>教工女子组实得积分</span></div></td>
        <td ><div align="center"><span>名次</span></div></td>
        <td ><div align="center"><span>总积分</span></div></td>
        <td ><div align="center"><span>名次</span></div></td>
        </tr>
       <c:forEach items="${sessionScope.allTeachInfo}" var="temp">
      <tr class="tableContent">
      <c:forEach items="${ temp }" var="markInfo1">
        <td>${ markInfo1 }</td>
        
        </c:forEach>
      </tr>
     </c:forEach>
    </table>
    <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br/>
<div id='aOfUpload'></div>
</body>
</html>
