<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'longtracllook.jsp' starting page</title>
		<link href="${pageContext.request.contextPath }/css/subcss.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/zDrag.js"></script>
		<script type="text/javascript">
	//隔行变色
	  $(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
	   function	updateGroup(){
	     var gnum = $("#num").val();
	     var fnid = $("#hidden").val();

		if (gnum.length == 0) {
		   alert("分组数目不能为空");
			return false;
		}

		$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/Update1500GroupServlet",
		type : 'post',
		data : 'num=' + gnum +"&fid=" + fnid,
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');

			if (revalue == "success") {
				alert("重新分组成功!");
			} else
				alert("重新分组失败!");
		}
	   });
    }
    
    function clear(){
        $("#num").val()=null;
    }
  </script>
	</head>

	<body>
		<table class="stripe_tb">
			<c:forEach items="${ longTrack }" var="temp">
				<tr>
					<c:forEach items="${ temp }" var="mp">
						<td>
							${ mp }
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<input type="text" id="num" value="请输入想要重新分组的数目" onfocus="clear()" />
		<input type="button" onclick="updateGroup()" value="重新分组">
		<input type="hidden" value="${ sessionScope.fid } }" />
		<a href="servlet/TrackGameLook?fid=${ sessionScope.fid }">信息刷新</a>
		<a href="servlet/SelectItemsServlet">返回</a>
	</body>
</html>
