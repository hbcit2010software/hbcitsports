<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>赛前设置</title>
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
		//添加部门
	function addDepart(){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 200;
			diag.Title = "添加新单位";
			diag.URL = "${pageContext.request.contextPath }/set_departmentcreate.jsp";
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "添加新单位提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认添加\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
	}
	//修改部门信息
	function updateDepart(dpid){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 200;
			diag.Title = "修改部门信息";
			diag.URL = "${pageContext.request.contextPath }/servlet/GetDepartInfoByIdServlet?dpid="+dpid;
			diag.OKEvent = function(){
				window.location.reload();
				//diag.close();
			};
			diag.ShowCloseButton=false;
			diag.MessageTitle = "修改部门信息提示：";
			diag.Message = "填完各项内容后不要忘记先\"确认修改\"，然后才可关闭窗口";
			diag.show();
			diag.okButton.value="结果刷新";
			diag.cancelButton.value="关闭";
	}
	//删除部门信息
		function delDepart(dpid){
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/RemoveDepartmentServlet",
			type : 'post',
			data : 'dpid='+dpid,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("删除单位信息失败！",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("删除单位信息成功！",function(){window.location.reload();});
					}
				}
			});
	}
	//设置部门与运动会关系
		function setDp2Sp(obj,dpid){
		var param;
		if(obj.checked == true){
			param = "add";
		}else if(obj.checked == false){
			param = "del";
		}
		//alert(param);
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/SetDepartToSportsServlet",
			type : 'post',
			data : 'dpid='+dpid+'&param='+param,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="error"){
						Dialog.alert("设置失败！",function(){window.location.reload();});
					}
					if(revalue=="success"){
						Dialog.alert("设置成功！",function(){window.location.reload();});
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->参赛单位设置</span></td>
              </tr>
            </table></td>
            <td>
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF" onclick="addDepart();">添加新单位</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
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
        <td height="20" colspan="6"><div align="center" style="color:#FF0000; font-weight:bold">请为 ${sessionScope.currSportsName } 选择参赛单位</div></td>
        </tr>
      <tr class="tableTitle">
        <td width="5%" height="20"><div align="center">序号</div></td>
        <td width="10%" height="20" ><div align="center"><span>是否参赛</span></div></td>
        <td width="30%" height="20" ><div align="center"><span>单位全称</span></div></td>
        <td width="30%" height="20" ><div align="center"><span>单位简称</span></div></td>
        <td width="10%" height="20" ><div align="center"><span>单位类型</span></div></td>
        <td width="15%" height="20"><div align="center"><span>基本操作</span></div></td>
      </tr>
      <c:forEach var="dinfo" items="${departinfo}" varStatus="countItem">
	       <tr class="tableContent">
	        <td><div align="center">${countItem.count}</div></td>
	        <td>
	        		<div><input 
	        		<c:forEach var="checkit" items="${departinfobysportsid}" varStatus="myCountItem">
	        		<c:if test="${dinfo.id eq checkit.departid}">
	        		checked="checked" 
	        		</c:if>
	        		</c:forEach>
	        		type="checkbox" name="depart" value="${dinfo.id }" onclick="setDp2Sp(this,${dinfo.id});" /></div>
	        		
	        </td>
	        <td><div>${dinfo.departmentName }</div></td>
	        <td><div>${dinfo.departmentShortName }</div></td>
	        <td>
	        	<c:if test="${dinfo.departmentType eq 1}">
	        	<div style="color:#0000FF">有学生</div>
	        	</c:if>
	        	<c:if test="${dinfo.departmentType eq 0}">
	        	<div style="color:#00FFFF">无学生</div>
	        	</c:if>
	        </td>
	        <td><div><a href="#" onclick="Dialog.confirm('提示：您确认要修改“${dinfo.departmentShortName }”的信息吗？',function(){updateDepart(${dinfo.id});});">修改</a> | 
             <a href="#" onclick="Dialog.confirm('提示：您确认要删除“${dinfo.departmentShortName }”吗？',function(){delDepart(${dinfo.id});});">删除</a></div></td>
	      </tr>
      </c:forEach>
    </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />
<!--
<div align="center"><span class="pageJump">共有&nbsp;<b>243</b>&nbsp;条记录，当前第&nbsp;<b>1</b>&nbsp;页，共&nbsp;<b>10</b>&nbsp;页&nbsp;&nbsp;上一页&nbsp;&nbsp;下一页</span></div>
-->
</body>
</html>
