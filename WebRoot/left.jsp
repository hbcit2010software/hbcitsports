<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.accordion.js"></script>
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
	width:147px;
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
<div  style="height:100%;">
  <ul id="navigation">
	<!--赛前设置begin-->
    <li> <a class="head">赛前设置</a>
      <ul>
        <li><a href="#" target="rightFrame">大会设置</a></li>
        <li><a href="#" target="rightFrame">项目设置</a></li>
        <li><a href="#" target="rightFrame">组别设置</a></li>
        <li><a href="#" target="rightFrame">日程安排</a></li>
        <li><a href="#" target="rightFrame">参赛单位设置</a></li>
        <li><a href="#" target="rightFrame">号段分配</a></li>
        <li><a href="#" target="rightFrame">赛事规程</a></li>
        <li><a href="#" target="rightFrame">会程设置</a></li>
        <li><a href="#" target="rightFrame">工作人员设置</a></li>
        <li><a href="#" target="rightFrame">赛事纪律设置</a></li>
        <li><a href="#" target="rightFrame">赛会纪录</a></li>
      </ul>
    </li>
	<!--赛前设置end-->
    <!--赛事报名begin-->
	<li> <a class="head">赛事报名</a>
      <ul>
        <li><a href="#" target="rightFrame">学生组报名</a></li>
        <li><a href="#" target="rightFrame">教工组报名</a></li>
        <li><a href="#" target="rightFrame">报名情况查询</a></li>
        <li><a href="#" target="rightFrame">报名审核</a></li>
      </ul>
    </li>
     <!--赛事报名end-->
	<!--秩序册管理begin-->
    <li> <a class="head">秩序册管理</a>
      <ul>
        <li><a href="#" target="rightFrame">赛事编排</a></li>
        <li><a href="#" target="rightFrame">秩序册预览</a></li>
        <li><a href="#" target="rightFrame">秩序册生成</a></li>
        <li><a href="#" target="rightFrame">赛事手工调整</a></li>
      </ul>
    </li>
    <!--秩序册管理end-->
    <!--赛中管理begin-->
    <li> <a class="head">赛中管理</a>
      <ul>
        <li><a href="#" target="rightFrame">生成检录表</a></li>
        <li><a href="#" target="rightFrame">成绩录入</a></li>
        <li><a href="#" target="rightFrame">违纪管理</a></li>
        <li><a href="#" target="rightFrame">生成决赛名单</a></li>
        <li><a href="#" target="rightFrame">积分管理</a></li>
        <li><a href="#" target="rightFrame">成绩单管理</a></li>
      </ul>
    </li>
    <!--赛中管理end-->
    <!--综合查询begin-->
    <li> <a class="head">综合查询</a>
      <ul>
        <li><a href="#" target="rightFrame">综合查询</a></li>
      </ul>
    </li>
    <!--综合查询end-->
    <!--系统管理begin-->
    <li> <a class="head">系统管理</a>
      <ul>
        <li><a href="admin_right.jsp" target="rightFrame">帐号及权限管理</a></li>
        <li><a href="#" target="rightFrame">帐号管理</a></li>
        <li><a href="#" target="rightFrame">数据库备份</a></li>
        <li><a href="#" target="rightFrame">数据库恢复</a></li>
        <li><a href="#" target="rightFrame">操作日志</a></li>
      </ul>
    </li>
    <!--系统管理end-->
    <li> <a class="head">版本信息</a>
      <ul>
        <li><a href="#" target="rightFrame">关于本系统</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>
