<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
        <link href="css/subcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>


<script language="javascript">

function alterUser(obj) {
	var id=obj.id;
	//alert(id);
	var contact = $("#contact").val();
	var tel = $("#tel").val();
	var member = $('#member').val();
	
	var tempStr = /^([\u4e00-\u9fa5]{1,},){0,}([\u4e00-\u9fa5]{1,})$/;
	    	if(!tempStr.test(contact)){
		      Dialog.alert("系部联系人包含特殊字符，请以英文逗号进行分隔");
	        }
	         if(!tempStr.test(member)){
	          Dialog.alert("学生裁判包含特殊字符，请以英文逗号进行分隔");
	        }else{
	
    //alert("judge_1");
	$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/UpdateStuJudgeServlet",
		type : 'post',
		data : 'id=' + id +'&contact=' + contact + '&tel=' + tel + '&member='+ member,
		dataType:"html",
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');

			if (revalue == "success") {
				Dialog.alert("修改信息成功!");
			} else
				Dialog.alert("修改信息失败!");
		}
	});
}
}

//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
</script>

<style>
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.pageTitle {
	color: #e1e2e3;
	font-size: 12px;
}
</style>

	</head>
	<body >
	

		<div align="center">

			<table  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">

				<c:forEach items="${stuJudge}" var="stu">
					<tr class="tableContent" >
						<td>
							联系人姓名：
							</td>
							<td>
							<input id="contact" "  type="text" value="${stu[1]}" />
						</td>
					</tr>
					<tr class="tableContent">
						<td>
							联系电话：
							</td>
							<td>
							<input id="tel" type="text" value="${stu[2]}" />
						</td>
					</tr>
					<tr class="tableContent">
						<td>
							裁判成员：
							</td>
							<td>
							<input id="member" type="text" value="${stu[3]}" />
						</td>
					</tr>
					<tr class="tableContent">
					<td>
						
					</td>
					<td >
						<a href="#" id="${stu[0]}" onclick="alterUser(this)">保存</a>
					</td>
				</tr>
				</c:forEach>
				
			</table>
		</div>
	</body>
</html>
