<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
function updates(depName){
            var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 200;
			diag.Title = "修改部门积分";
			diag.URL = "${pageContext.request.contextPath }/updateMarks.jsp?depName="+depName;
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "修改运动会提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认修改\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="确定";
			diag.cancelButton.value="关闭";
	}
	
	function printMarks(){
	
	   $.ajax({ 
               url : "servlet/PrintMarkSerlvet",
               type : 'post',
               contentType : "application/json;charset=utf-8",
			   dataType : 'json',
               success : function(json) {
               
				 Dialog.alert("成功生成此文档，请点击连接下载word附件");
				 var inhtml = "";
				   
				 for(i=0;i<json.contents.length;i++)	
				 {		
				   Dialog.alert(json.contents[i].fileName1);
				   inhtml = "<a href='download.jsp?file="+json.contents[i].file+"&fileName="+ encodeURIComponent(encodeURIComponent(json.contents[i].fileName1)) +"'>"+ json.contents[i].fileName1 +"</a>";
                   $("#aOfUpload").html(inhtml);
                 }  
               }, 
               error : function(xhr, status, errorThrown) {
					Dialog.alert("服务器连接出错了");
				}			
               });
  }
  
   function printAllMarks(){
	
	   $.ajax({ 
               url : "servlet/PrintAllMarkSerlvet",
               type : 'post',
               contentType : "application/json;charset=utf-8",
			   dataType : 'json',
               success : function(json) {
               
				 Dialog.alert("成功生成此文档，请点击连接下载word附件");
				 var inhtml = "";
				   
				 for(i=0;i<json.contents.length;i++)	
				 {		
				   Dialog.alert(json.contents[i].fileName1);
				   inhtml = "<a href='download.jsp?file="+json.contents[i].file+"&fileName="+ encodeURIComponent(encodeURIComponent(json.contents[i].fileName1)) +"'>"+ json.contents[i].fileName1 +"</a>";
                   $("#aOfUpload").html(inhtml);
                 }  
               }, 
               error : function(xhr, status, errorThrown) {
					Dialog.alert("服务器连接出错了");
				}			
               });
  }
</script>
</head>
<body>
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
  <tr>
    <td> 
    <!--内嵌表格begin--> 

 <table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" >
 <tr class="tableContent">
 <td width=18% height="30"><div align="center">部门</div></td>
 <td width=18% height="30"><div align="center">学生实得积分</div></td>
 <td width=18% height="30"><div align="center">教工实得积分</div></td>
 <td width=18% height="30"><div align="center">学生最终积分</div></td>
 <td width=18% height="30"><div align="center">教工最终积分</div></td>
 <td width=10% height="30"><div align="center">更改</div></td>
 </tr>
 <c:forEach items="${ sessionScope.list1 }" var="li1">
 <c:forEach items="${ sessionScope.list2 }" var="li2">
 <c:forEach items="${ sessionScope.list3 }" var="li3">
 <c:forEach items="${ sessionScope.list4 }" var="li4">
 <c:forEach items="${ sessionScope.list5 }" var="li5">
 <tr>
    
    <td><table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" ><c:forEach items="${ li1 }" var="l1"><tr class="tableContent"><td>${ l1.depName }</td></tr></c:forEach></table></td>
    <td><table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" ><c:forEach items="${ li2 }" var="l2"><tr class="tableContent"><td>${ l2.studetsMarks }</td></tr></c:forEach></table></td>
    <td><table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" ><c:forEach items="${ li3 }" var="l3"><tr class="tableContent"><td>${ l3.teacherMarks }</td></tr></c:forEach></table></td>
    <td><table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" ><c:forEach items="${ li4 }" var="l4"><tr class="tableContent"><td>${ l4.finalStudentsSum }</td></tr></c:forEach></table></td>
    <td><table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" ><c:forEach items="${ li5 }" var="l5"><tr class="tableContent"><td>${ l5.finalTeacherSum }</td></tr></c:forEach></table></td>
    <td><table  width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#a8c7ce" class="stripe_tb" ><c:forEach items="${ li1 }" var="l1"><tr class="tableContent"><td><a href="javascript:void(0);" onclick="updates('${ l1.depName }');">更改</a></td></tr></c:forEach></table></td>
 </tr>
 </c:forEach></c:forEach></c:forEach></c:forEach></c:forEach>
 </table>

 <input value="打印积分" type="button" onclick="printMarks()"/>
 <input value="打印总积分" type="button" onclick="printAllMarks()"/>
 <div id="aOfUpload"></div>
</body>
</html>
