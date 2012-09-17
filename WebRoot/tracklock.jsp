<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <title>My JSP 'tracklock.jsp' starting page</title>
     <link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script language="javascript">
	//隔行变色
$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
</script>
 <script language="javascript">
				function adduser(obj) {
				   //var tt = window.event.srcElement.id;
				   //alert(obj.id);
					var diag = new Dialog();
					diag.Top="50%";
					diag.Left="50%";
				
					diag.Modal = false;
					
					diag.Width = 450;
					diag.Height = 150;
					diag.Title = "修改跑道";
					diag.URL = "${pageContext.request.contextPath }/servlet/AdjustByHandServlet?str="+obj.id;
					diag.OKEvent = function() {
						window.location.reload();
				
					};
				
					diag.ShowCloseButton = false;
					diag.MessageTitle = "提示：";
					diag.Message = "修改时请注意，同一组中不可以出现相同的两个跑道";
					diag.show();
					diag.okButton.value = "结果刷新";
					diag.cancelButton.value = "关闭";
				}
	
       </script>
<style>
			body { background: #ffffff; color: #444;font-size:12px; }
			a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
			body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
			img, fieldset { border: none 0; }
			body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
			body, input, textarea, select, button { margin: 0; font-size: 12px; font-family: Tahoma, SimSun, sans-serif; }
			div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
			h5 { font-size:12px; }
			ol li,ul li{ margin-bottom:0.5em;}
			pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
			pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
			#content { padding-left:50px; padding-right:50px; }
			#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
			#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
			#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
			#content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
			hr { clear:both; margin:7px 0; +margin: 0;
			border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
			.infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
			.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
			.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
			.input_on{padding:2px 8px 0pt 3px;height:18px;border:1px solid #999;background-color:#eeeeee;}
			
		</style>
  </head> 
  <body>
  
 		<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#a8c7ce" class="stripe_tb">
             <c:forEach items="${ trackPlayers }" var="temp">
		 		<tr>
		     	   <td>${ temp.groupNum }</td><td>${ temp.players }</td><td><a onclick="adduser(this)" id="${ temp.nextFlag }" href="#">修改</a></td>
		     	</tr>
	          </c:forEach>
 		</table> 
 		<a href="servlet/SelectItemsServlet">返回</a>
 		
  </body>
</html>
