<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
        <link href="css/subcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript">
</script>
<script language="javascript">

function alterUser(obj) {
	var id=obj.id;
	alert(id);
	var judge_1 = $("#judge_1").val();
	var judge_2 = $("#judge_2").val();
	var judge_3 = $('#judge_3').val();
    //alert("judge_1");
	$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/UpdateFieldItemJudgeServlet",
		type : 'get',
		data : 'id=' + id +'&judge_1=' + judge_1 + '&judge_2=' + judge_2 + '&judge_3='+ judge_3,
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');

			if (revalue == "success") {
				alert("修改信息成功!");
			} else
				alert("修改信息失败!");
		}
	});
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
							<input id="judge_1" "  type="text" value="${field[0]}" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
					<tr class="tableContent">
						<td>
							裁判长助理：
							</td>
							<td>
							<input id="judge_2" type="text" value="${field[1]}" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
					<tr class="tableContent">
						<td>
							裁判员：
							</td>
							<td>
							<input id="judge_3" type="text" value="${field[2]}" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
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
