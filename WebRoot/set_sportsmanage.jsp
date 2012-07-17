<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
		//
		function setCurrSports(uid){
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/SetCurrSportsServlet",
			type : 'get',
			data : 'uid='+uid,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("设置当前运动会失败！请检查网络设置。",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("设置当前运动会成功！请重新登录系统，以使您的设置生效。",function(){top.location.replace("${pageContext.request.contextPath }/servlet/LogoutServlet");});
					}
				}
			});
	}
	//
	function addSports(){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 200;
			diag.Title = "添加新运动会";
			diag.URL = "${pageContext.request.contextPath }/set_sportscreate.jsp";
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "添加运动会提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认添加\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
	}
	//
	function updateSports(spid){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 200;
			diag.Title = "修改运动会信息";
			diag.URL = "${pageContext.request.contextPath }/servlet/GetSportsInfoByIdServlet?spid="+spid;
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "修改运动会提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认修改\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
	}
	//
		function delSports(spid){
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/RemoveSportsServlet",
			type : 'get',
			data : 'spid='+spid,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("删除运动会失败！",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("删除运动会成功！",function(){window.location.reload();});
					}
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->运动会管理</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF" onclick="addSports();">添加新运动会</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
            </div>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <!--内嵌表格begin-->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
      <tr class="tableTitle">
        <td width="3%" height="20"><div align="center">序号</div></td>
        <td width="25%" height="20" ><div align="center"><span>运动会名称</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>起始日期</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>结束日期</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>报名截止日期</span></div></td>
        <td width="20%" height="20" ><div align="center"><span>赛会地点</span></div></td>
        <td width="7%" height="20"><div align="center"><span>是否当前</span></div></td>
        <td width="15%" height="20"><div align="center"><span>基本操作</span></div></td>
      </tr>
      <c:forEach var="sinfo" items="${sportsinfo}" varStatus="countItem"> 
       <tr class="tableContent">
        <td><div align="center">${countItem.count}</div></td>
        <td><div>${sinfo.sportsname}</div></td>
        <td><div>${sinfo.sportsbegin}</div></td>
        <td><div>${sinfo.sportsend}</div></td>
        <td><div>${sinfo.registend}</div></td>
        <td><div>${sinfo.address}</div></td>
        <c:if test="${sinfo.current eq 1}">
        <td><div style="color:#F00; font-weight:bold">是</div></td>
        </c:if>
        <c:if test="${sinfo.current ne 1}">
        <td><div>否</div></td>
        </c:if>
        <td><div>
        <c:if test="${sinfo.current eq 1}">已为当前 | </c:if>
        <c:if test="${sinfo.current ne 1}">
<a href="#" onclick="Dialog.confirm('提示：您确认要将“${sinfo.sportsname }”设为当前运动会吗？<br>如若更改，需重新登录系统。',function(){setCurrSports(${sinfo.id});});">设为当前</a> | 
        </c:if>
<a href="#" onclick="Dialog.confirm('提示：您确认要修改“${sinfo.sportsname }”的信息吗？',function(){updateSports(${sinfo.id});});">修改</a> | 
<c:if test="${sinfo.current eq 1}">删除</c:if>
<c:if test="${sinfo.current ne 1}">
<a href="#" onclick="Dialog.confirm('提示：您确认要删除“${sinfo.sportsname }”吗？<br>删除运动会将影响后续功能，请务必谨慎操作！',function(){delSports(${sinfo.id});});">删除</a>
</c:if>
            </div></td>
      </tr>
     </c:forEach>
    </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>

</body>
</html>
