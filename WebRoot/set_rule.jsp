<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>set_rule.jsp</title>
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
		function updateRule(){
			var id = document.getElementById("ruleid").value;
			var mark = document.getElementById("mark").value;
			var position = document.getElementById("position").value;
			var perman = document.getElementById("perman").value;
			var perdepartment = document.getElementById("perdepartment").value;
			var recordmark_low = document.getElementById("recordmark_low").value;
			var recordmark_high = document.getElementById("recordmark_high").value;
			var reg = /[0-9]/;
			var regMark;
			var tempStr;

			if(position=="" || mark=="" || perman=="" || perdepartment=="" || recordmark_low=="" || recordmark_high==""){
				Dialog.alert("不能有空项！");
				return false;
			}
			if( !(reg.test(position) && reg.test(perman) && reg.test(perdepartment) && reg.test(recordmark_low) && reg.test(recordmark_high)) ){
				Dialog.alert("请填写数字！");
				return false;
			}
			var tempInt = parseInt(position)-1;
			tempStr = "/^(\\d,){" + tempInt + "}(\\d)$/";
			//alert(tempStr);
			regMark = eval(tempStr);
			if(!regMark.test(mark)){
				Dialog.alert("\"各名次获得的积分\"格式错误！");
				return false;
			}
			
		$.ajax({
			url :"${pageContext.request.contextPath }/servlet/UpdateRule",
			type : 'post',
			data : 'id='+id+'&mark='+mark+'&position='+position+'&perman='+perman+'&perdepartment='+perdepartment+'&recordmark_low='+recordmark_low+'&recordmark_high='+recordmark_high,
			success :function(mm){
					var revalue=mm.replace(/\r\n/g,'');
					if(revalue=="success"){
						Dialog.alert("修改规则成功！",function(){window.location.reload();});
					}else{
						Dialog.alert("修改规则失败！",function(){window.location.reload();});
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->规则管理</span></td>
              </tr>
            </table></td>
            <td>
            <!--
            <div align="right"><span class="pageTitle">
              <img src="${pageContext.request.contextPath }/images/add.gif" width="10" height="10" /> <a href="#" style="color:#FFF" onclick="addSports();">添加新运动会</a> &nbsp;</span><span class="pageTitle"> &nbsp;</span>
            </div>
            -->
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
      <!--tr class="tableTitle">
        <td width="3%" height="20"><div align="center">名称</div></td>
        <td width="25%" height="20" ><div align="center"><span>值</span></div></td>
        </tr-->
         <c:forEach items="${requestScope.rule}" var="r" varStatus="countItem">
         <input type="hidden" id="ruleid" value="${r.id}" />
       <tr class="tableContent">
        <td><div align="right" style="margin-right:10px;">当前运动会名称</div></td>
        <td><div align="left" style="margin-left:5px;">${sessionScope.currSportsName}</div></td>
        </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">可获得积分的名次数量</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="position" type="text" id="position" value="${r.position}"  style="text-align:center"/></div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">各名次获得的积分</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="mark" type="text" id="mark" value="${r.mark}"  style="text-align:center"/> 
           (根据名次由高到低，中间用,号隔开)</div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">每单位每个运动员限报数量</div></td>
         <td><div align="left" style="margin-left:5px;">
         <c:if test="${not isAlreadyRegist}">
         <input name="perman" type="text" id="perman" value="${r.perman}"  style="text-align:center"/>(接力除外)
         </c:if>
         <c:if test="${isAlreadyRegist}">
         <input name="perman" type="text" id="perman" value="${r.perman}"  style="text-align:center" disabled="disabled"/><span style="color:#F00; margin-left:5px;">本届运动会已经有人报名，因此本选项已禁用</span>
         </c:if>
           </div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">每个项目每个单位限报数量</div></td>
         <td><div align="left" style="margin-left:5px;">
         <c:if test="${not isAlreadyRegist}">
         <input name="perdepartment" type="text" id="perdepartment" value="${r.perdepartment}"  style="text-align:center"/>
         </c:if>
         <c:if test="${isAlreadyRegist}">
         <input name="perdepartment" type="text" id="perdepartment" value="${r.perdepartment}"  style="text-align:center" disabled="disabled"/><span style="color:#F00; margin-left:5px;">本届运动会已经有人报名，因此本选项已禁用</span>
         </c:if>
         </div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">破院级记录的加分</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="recordmark_low" type="text" id="recordmark_low" value="${r.recordmark_low}"  style="text-align:center"/></div></td>
       </tr>
       <tr class="tableContent">
         <td><div align="right" style="margin-right:10px;">破省级记录的加分</div></td>
         <td><div align="left" style="margin-left:5px;"><input name="recordmark_high" type="text" id="recordmark_high" value="${r.recordmark_high}"  style="text-align:center"/></div></td>
       </tr>
       </c:forEach>
    </table>
    <!--内嵌表格end-->
    </td>
  </tr>
</table>
<br />

<div align="center">
	<span class="pageJump">
		<input type="button" value="修  改"  onclick="updateRule();"/>
	</span>
</div>
</body>
</html>
