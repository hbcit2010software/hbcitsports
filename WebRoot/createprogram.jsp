<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>赛事预览页面</title>
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
		<script>
	function trackShow() {
		document.getElementById("track").style.display = "";
		document.getElementById("field").style.display = "none";
	}
	function fieldShow() {
		document.getElementById("track").style.display = "none";
		document.getElementById("field").style.display = "";
	}
</script>
		<script>
	function trackShow1() {
		document.getElementById("track1").style.display = "";
		document.getElementById("field1").style.display = "none";
	}
	function fieldShow1() {
		document.getElementById("track1").style.display = "none";
		document.getElementById("field1").style.display = "";
	}
	function fieldShow2() {
		document.getElementById("track1").style.display = "none";
		document.getElementById("field1").style.display = "";
		document.getElementById("track2").style.display = "none";
	}
		function fieldShow3() {
		document.getElementById("track1").style.display = "";
		document.getElementById("field1").style.display = "none";
		document.getElementById("track2").style.display = "none";
	}
		function fieldShow4() {
		document.getElementById("track1").style.display = "none";
		document.getElementById("field1").style.display = "none";
		document.getElementById("track2").style.display = "";
	}
</script>

	</head>

	<body>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="24" bgcolor="#353c44">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="6%" height="19" valign="bottom">
														<div align="center">
															<img src="images/tb.gif" width="14" height="14" />
														</div>
													</td>
													<td width="94%" valign="bottom">
														<span class="pageTitle">秩序册管理-->赛事预览</span>
													</td>
												</tr>
											</table>
										</td>
										<td>
											
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
					<!--内嵌表格begin-->
					<b>赛事预览(查看前请先进行赛事分组)<br/>
						
<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#a8c7ce" class="stripe_tb">
							<tr class="tableTitle">
								<td align="center">
									<a href="#" onclick="fieldShow2()";
>径赛</a>
								</td>
								<td align="center">
									<a href="#" onclick="fieldShow3()";
>田赛</a>
								</td>
								<td align="center">
									<a href="#" onclick="fieldShow4()";
>接力</a>
								</td>
							</tr>
						</table>
						 </b>
					<!--内嵌表格end-->
					<!--内嵌表格begin-->
					<!--径赛Div begin-->
					<div id="field1" style="";">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#a8c7ce" class="stripe_tb">
							<tr class="tableTitle">
								<td align="center">
									组别级项目名称
								</td>
								<td align="center">
									预览
								</td>
							</tr>
							<c:forEach items="${ItemsList}" var="user">
								<tr class="tableContent">
									<td>
										${user.finalitemname}
									</td>

									<td>
										<a title="预览"
											href="${pageContext.request.contextPath }/servlet/TrackGameLook?id=${user.fmid}">预览</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<!--径赛Div end-->
					<!--田赛Div begin-->
					<div id="track1" style="display: none";">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#a8c7ce" class="stripe_tb">
							<tr class="tableTitle">
								<td align="center">
									组别级项目名称
								</td>
								<td align="center">
									预览
								</td>
							</tr>
							<c:forEach items="${ItemsList1}" var="user">
								<tr class="tableContent">
									<td>
										${user.finalitemname}
									</td>

									<td>
										<a title="预览"
											href="${pageContext.request.contextPath }/servlet/FileLook?id=${user.fmid}">预览</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<!--田赛Div end-->
					<!--接力Div begin-->
					<div id="track2" style="display: none";">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#a8c7ce" class="stripe_tb">
							<tr class="tableTitle">
								<td align="center">
									组别级项目名称
								</td>
								<td align="center">
									预览
								</td>
							</tr>
							<c:forEach items="${ItemsList2}" var="user">
								<tr class="tableContent">
									<td>
										${user.finalitemname}
									</td>

									<td>
										<a title="预览"
											href="${pageContext.request.contextPath }/servlet/ReailyLook?id=${user.fmid}">预览</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<!--接力Div end-->

					<!--内嵌表格end-->
				</td>
			</tr>
		</table>
		<br />

	</body>
</html>
