<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*,cn.edu.hbcit.smms.services.systemmanageservices.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//根据登录时的userrights，判断5种权限
	AccountService as = new AccountService();
	int userrights = ((Integer)session.getAttribute("userrights")).intValue();
	pageContext.setAttribute("r0",Boolean.valueOf(as.checkPower(userrights, 0)));
	pageContext.setAttribute("r1",Boolean.valueOf(as.checkPower(userrights, 1)));
	pageContext.setAttribute("r2",Boolean.valueOf(as.checkPower(userrights, 2)));
	pageContext.setAttribute("r3",Boolean.valueOf(as.checkPower(userrights, 3)));
	pageContext.setAttribute("r4",Boolean.valueOf(as.checkPower(userrights, 4)));
	/**
 		* 控制系统权限
 		* 0-系统设置
 		* 1-赛前设置
 		* 2-秩序册生成
 		* 3-赛中管理
 		* 4-赛事报名
	*/
 %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.accordion.js"></script>
<script type="text/javascript">

		function checkgroup(){
		alert('检查是否执行过赛事分组');
		$.ajax({
					url :"${pageContext.request.contextPath }/servlet/CheckGroupServlet",
					type : 'get',
				    // data : 'username='+username+'&password='+password+'&dj='+dj,
					//data:{username:username},
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success"){
								var flag = window.confirm("已经分过，是否重新分组？？？");
								if (flag == true){
									$.ajax({
									url :"${pageContext.request.contextPath }/servlet/GameGroupingServlet",
									type : 'get',
								   // data : 'username='+username+'&password='+password+'&dj='+dj,
									//data:{username:username},
									
									success :function(mm){
											var revalue=mm.replace(/\r\n/g,'');
											if(revalue=="success")
											{
												alert("分组完成！");
											}
											else{
												alert("分组失败！");
											}
										}
									});	
								}else{
									alert('分组取消');
								}
							}else{
								alert("按回车键  或者点击确定开始分组！");
								$.ajax({
									url :"${pageContext.request.contextPath }/servlet/GameGroupingServlet",
									type : 'get',
								   // data : 'username='+username+'&password='+password+'&dj='+dj,
									//data:{username:username},
									
									success :function(mm){
											var revalue=mm.replace(/\r\n/g,'');
											if(revalue=="success")
											{
												alert("分组完成！");
											}
											else{
												alert("分组失败！");
											}
										}
								});	
							}
					}
		});
	}
</script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	/*width:147px;*/
	width:100%;
	height:100%;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
	color:#344b50;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
	border:solid 1px #adb9c2;
	color:#344b50;
}
#navigation li li a:visited {
	color:#344b50;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
-->
</style>
</head>
<body>
<div  style="height:100%; width:100%;">
  <ul id="navigation">
  <c:if test="${r1 eq true}">
	<!--赛前设置begin-->
    <li> <a class="head">赛前设置</a>
      <ul>
        <li><a href="servlet/GetSportsInfoServlet" target="rightFrame">运动会管理</a></li>
        <li><a href="servlet/GetDepartInfoServlet" target="rightFrame">参赛单位设置</a></li>
        <li><a href="servlet/GetGroupInfoServlet" target="rightFrame">组别设置</a></li>
        <li><a href="servlet/GetItemInfoServlet" target="rightFrame">项目设置</a></li>
        <li><a href="servlet/AddFinalItemServlet" target="rightFrame">日程安排</a></li>
        <li><a href="servlet/AddPlayerNumServlet" target="rightFrame">号段分配</a></li>
        <li><a href="#" target="rightFrame">赛事规程</a></li>
        <li><a href="#" target="rightFrame">会程设置</a></li>
        <li><a href="#" target="rightFrame">工作人员设置</a></li>
        <li><a href="#" target="rightFrame">赛事纪律设置</a></li>
        <li><a href="#" target="rightFrame">赛会纪录</a></li>
      </ul>
    </li>
	<!--赛前设置end-->
  </c:if>
  <c:if test="${r4 eq true}">
    <!--赛事报名begin-->
	<li> <a class="head">赛事报名</a>
      <ul>
        <li><a href="apply_teamleader.jsp" target="rightFrame">领队报名</a></li>
        <li><a href="servlet/GetItemNameServlet" target="rightFrame">学生组报名</a></li>
        <li><a href="servlet/GetPlayerServlet" target="rightFrame">教工组报名</a></li>
        <li><a href="servlet/SelectGroupNameBySportsIdServlet" target="rightFrame">报名情况查询</a></li>
        <li><a href="#" target="rightFrame">报名审核</a></li>
      </ul>
    </li>
     <!--赛事报名end-->
     </c:if>
      <c:if test="${r2 eq true}">
	<!--秩序册管理begin-->
    <li> <a class="head">秩序册管理</a>
      <ul>
       <li><a href="#" onclick="checkgroup()">赛事分组</a></li>
        <li><a href="servlet/SelectItemsServlet" target="rightFrame">赛事浏览</a></li>
        <li><a href="creatword.jsp" target="rightFrame">秩序册生成</a></li>
      </ul>
    </li>
    <!--秩序册管理end-->
     </c:if>
    <c:if test="${r3 eq true}">
    <!--赛中管理begin-->
    <li> <a class="head">赛中管理</a>
      <ul>
        <li><a href="#" target="rightFrame">生成检录表</a></li>
        <li><a href="servlet/GetConditonServlet?action=allcond" target="rightFrame">成绩录入</a></li>
        <li><a href="servlet/GameManageGetGroupServlet" target="rightFrame">违纪管理</a></li>
        <li><a href="#" target="rightFrame">生成决赛名单</a></li>
        <li><a href="mark.jsp" target="rightFrame">积分管理</a></li>
        <li><a href="#" target="rightFrame">成绩单管理</a></li>
      </ul>
    </li>
    <!--赛中管理end-->
    </c:if>
    <!--综合查询begin-->
    <li> <a class="head">综合查询</a>
      <ul>
        <li><a href="servlet/PageDataServlet" target="rightFrame">综合查询</a></li>
      </ul>
    </li>
    <!--综合查询end-->
   <c:if test="${r0 eq true}">
    <!--系统管理begin-->
    <li> <a class="head">系统管理</a>
      <ul>
        <li><a href="servlet/GetAccountInfoServlet" target="rightFrame">帐号及权限管理</a></li>
        <li><a href="admin_db.jsp" target="rightFrame">数据库备份与恢复</a></li>
        <li><a href="#" target="rightFrame">操作日志</a></li>
      </ul>
    </li>
    <!--系统管理end-->
   </c:if>
    <li> <a class="head">版本信息</a>
      <ul>
        <li><a href="#" target="rightFrame">关于本系统</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>
