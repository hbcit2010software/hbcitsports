<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script language="javascript">
function backup()
{
	$.ajax({
			url :"${pageContext.request.contextPath }/servlet/BackupRecoveryDatabaseServlet",
			type : 'get',
			data : 'type='+0,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("数据库备份失败！",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("数据库备份成功！",function(){window.location.reload();});
					}
				}
			});
}
//
function recovery()
{
	$.ajax({
			url :"${pageContext.request.contextPath }/servlet/BackupRecoveryDatabaseServlet",
			type : 'get',
			data : 'type='+1,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("数据库恢复失败！",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("数据库恢复成功！",function(){window.location.reload();});
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
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">系统管理-->数据库备份与恢复</span></td>
              </tr>
            </table></td>
            <td>&nbsp;
            
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <!--内嵌表格begin-->
    <table width="80%" border="0" align="center" cellpadding="0" cellspacing="1">
      <tr>
        <td width="4%" height="20">
        <div align="center">
          <img src="pic/backup.png" width="128" height="128" /></div>
        </td>
        <td width="10%" height="20" >
        <div align="center">
          <img src="pic/recovery.png" width="128" height="128" /></div>
        </td>
        </tr>
       <tr>
         <td><div align="center"><input type="button" name="backup" id="backup" value="备份数据库" onclick="backup();" /></div></td>
         <td><div align="center"><input type="button" name="recovery" id="recovery" value="恢复数据库" onclick="recovery();" /></div></td>
        </tr>
      </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>

</body>
</html>
