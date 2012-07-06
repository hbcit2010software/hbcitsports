<%@ page language="java" import="java.util.*,cn.edu.hbcit.smms.pojo.GameApplyPJ" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head> 
 <title>修改运动员信息</title>
 <link href="css/subcss.css" rel="stylesheet" type="text/css" />
 <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
  <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">
//隔行变色
$(document).ready(function(){	
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		});
//修改
function updatePlayer(){
	var playernum = $("#playernum").val();
	var sp2dpid = $("#sp2dpid").val();
	var groupid =$('#selectGroup').find('option:selected').val();
	var playername = $("#playername").val();
	var playersex = $("input[name='playersex']:checked").val();	
	var registitem = document.getElementsByName('checkbox');
	if(registitem.length!=0){
	     var item = "";
	     var checkednum = 0;
	     for(var i=0 ;i<registitem.length ; i++){
	          if(registitem[i].checked){
	               checkednum++;
	           }
	       }
	     for(var i=0,ii=0;i<registitem.length ; i++){  
	           if (registitem[i].checked){
	                item +=registitem[i].value;
	                if(ii!=checkednum -1){
	                      item +=";";
	                } 
	                  ii++;
	           }         
	     }
	}
	if (playername.length == 0) {
		alert("运动员姓名不能为空!");
		return false;
	}
	$.ajax( {
		url : "${pageContext.request.contextPath }/servlet/UpdatePlayerByNumServlet",
		type : 'post',
		data : 'playernum=' + playernum +'&playername=' + playername + '&playersex='
				+ playersex+' &groupid=' + groupid +'&item='+item,
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');
			if (revalue == "success"){
				alert("修改信息成功!");
			} else
				alert("修改信息失败!");
		}
	});
}

</script>
  </head> 
  <body style="margin-top: 30px"> 
 <font color="red">注：每人限报两项，接力除外</font>
  <br>
  <br>
  <form id="form1" name="form1" method="post" action="servlet/UpdatePlayerByNumServlet">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
      <tr class="tableTitle">
       <td width="4%" height="20"><div align="center"><span>运动员号</span></div></td>
       <td width="3%" height="20"><div align="center"><span>姓名</span></div></td>
       <td width="8%" height="20"><div align="center"><span>性别</span></div></td>
       <td width="2%" height="20"><div align="center"><span>组别</span></div></td>
      <c:forEach items="${sessionScope.itemList}" var="item">
	   	<td width="6%"  height="20"><div align="center"><span>${item.itemname }</span></div></td>
      </c:forEach>
      </tr>  
      <c:forEach items="${sessionScope.playerList}" var="player">
      <tr class="tableContent">
         <td width="5%" height="20"><input id="playernum" disabled="disabled" type="text" value="${player.playernum}" size="7"/></td>
         <td width="5%" height="20"><input id="playername" type="text" value="${player.playername}" size="7" /></td>
         <td width="8%" height="20">
         <c:if test="${player.playersex ==1}">
         <label><input type="radio" name="playersex" value="1" id="manRadioGroup" checked>男</label>
         <label><input type="radio" name="playersex" value="0" id="womanRadioGroup">女</label>
         </c:if>
         <c:if test="${player.playersex ==0}">
         <label><input type="radio" name="playersex" value="1" id="manRadioGroup" >男</label>
         <label><input type="radio" name="playersex" value="0" id="womanRadioGroup" checked>女</label></c:if>        
         </td>
         <td width="2%" height="20">
         <div align="center">
         <span>
         <select id="selectGroup" name="selectGroup" >
           <option value="0">--重新选择组别--</option>
           <c:forEach items="${sessionScope.groupList}" var="grouplist">
           <option value="${grouplist.id}">${ grouplist.groupname }</option>
           </c:forEach>
         </select>
         </span>
         </div>
         </td>
        <c:forEach items="${sessionScope.itemList}" var="item">
         <td width="6%" height="20"><div align="center"><span><input type="checkbox" name="checkbox" value="${item.itemid}"/></span></div></td>
        </c:forEach> 
      </tr>
     </c:forEach> 
  </table> 
  </form>
  <br/>
 <div align="left"><a href="#" onClick="updatePlayer()">保存修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/servlet/SelectGroupNameBySportsIdServlet">返回</a></div>
   <br>
  <br>
</body>
</html>
