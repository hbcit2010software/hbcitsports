<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/subcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script language="javascript">

function alterUser(obj) {
	var id=obj.id;
	//alert(id);
	var judge_1 = $("#judge_1").val();
	var judge_2 = $("#judge_2").val();
	var judge_3 = $('#judge_3').val();
	
	var tempStr = /^([\u4e00-\u9fa5]{1,},){0,}([\u4e00-\u9fa5]{1,})$/;
	    	if(!tempStr.test(judge_1)){
		      Dialog.alert("项目裁判长包含特殊字符，请以英文逗号进行分隔");
	        }
	         if(!tempStr.test(judge_2)){
	          Dialog.alert("裁判长助理包含特殊字符，请以英文逗号进行分隔");
	        }
	        if(!tempStr.test(judge_3)){
	          Dialog.alert("裁判员包含特殊字符，请以英文逗号进行分隔");
	        }else{
	
    //alert("judge_1");
	$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/UpdateFieldItemJudgeServlet",
		type : 'post',
		data : 'id=' + id +'&judge_1=' + judge_1 + '&judge_2=' + judge_2 + '&judge_3='+ judge_3,
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

				<c:forEach items="${fieldjudge}" var="field">
					<tr class="tableContent" >
						<td>
							项目裁判长：
							</td>
							<td>
							<input id="judge_1" "  type="text" value="${field[0]}" />
						</td>
					</tr>
					<tr class="tableContent">
						<td>
							裁判长助理：
							</td>
							<td>
							<input id="judge_2" type="text" value="${field[1]}" />
						</td>
					</tr>
					<tr class="tableContent">
						<td>
							裁判员：
							</td>
							<td>
							<input id="judge_3" type="text" value="${field[2]}" />
						</td>
					</tr>
					<tr class="tableContent">
					<td >
						
					</td>
					<td >
						<a href="#" id="${field[3]}" onclick="alterUser(this)">保存</a>
					</td>
				</tr>
				</c:forEach>
				
			</table>
		</div>
	</body>
</html>
