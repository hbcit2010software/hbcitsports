<%@ page language="java" import="java.util.*,cn.edu.hbcit.smms.services.systemmanageservices.AccountService" pageEncoding="utf-8"%>
<%
/**
 * 控制系统权限
 * 0-系统设置
 * 1-赛前设置
 * 2-秩序册生成
 * 3-赛中管理
 * 4-赛事报名
*/
int urights = 0;  //用户权限
boolean rightsFlag = false;  
if(session != null && session.getAttribute("userrights")!=null){
	urights = ((Integer)session.getAttribute("userrights")).intValue();
}
AccountService as = new AccountService();
rightsFlag = as.checkPower(urights, 0);
pageContext.setAttribute("rightsCheck",Boolean.valueOf(rightsFlag));
System.out.println(rightsFlag);
 %>
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
function changeSports()
{
	alert("跳！");
	//location.replace("${pageContext.request.contextPath}/servlet/AdminNewsListServlet?cpage=1");
}
</script>
</head>
<c:if test="${rightsCheck eq true}">
<body onload="Dialog.confirm('提示：当前运动会是${sessionScope.currSportsName}，您需要改变或新建一届运动会吗？',function(){changeSports();});">
</c:if> 
<c:if test="${rightsCheck eq false}">
<body>
</c:if> 
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">欢迎使用SMMS运动会综合管理系统</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right"><span class="pageTitle"> &nbsp;</span>
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
    <div align="center"><img src="${pageContext.request.contextPath }/pic/welcome.jpg" width="640" height="400" /></div>
    <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />
<div align="center"><span class="pageJump">欢迎使用SMMS运动会综合管理系统</span></div>
</body>
</html>
