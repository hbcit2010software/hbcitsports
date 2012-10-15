<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<html>
  <head>
    
    
    <title>修改大会纪律</title>
    <link href="${pageContext.request.contextPath }/css/subcss.css"
			type="text/css" rel="stylesheet" />
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
</script>

<script type="text/javascript">
    function alterjl() {
    	
    	var rule = $("#rule").val();
    	var open = $("#open").val();
    	var close = $("#close").val();
 		
 		if (rule.length == 0) {
		Dialog.alert("大会纪律内容不能为空!");
		return false;
		}
	
		if (open.length == 0) {
		Dialog.alert("开幕式内容不能为空!");
		return false;
		}
	
		if (close.length == 0) {
		Dialog.alert("闭幕式内容不能为空!");
		return false;
		}
		
		 
 
    	//var sportsname = $("select").find("option:selected").text();

    	
		$.ajax( {
					url : "${pageContext.request.contextPath }/servlet/DaHuiJiLvServlet",
					type : 'post',
					data : { rule:rule,open:open,close:close},
				
					success : function(mm) {
						var revalue = mm.replace(/\r\n/g, '');
						 
						if (revalue == "success") {
							Dialog.alert("提交 成功!", function() {
								window.location.href = window.location.href;
							});
						} else
							Dialog.alert("提交 失败!", function() {
								window.location.href = window.location.href;
							});
					}
					 
				});
	
}


    </script>
	 
  </head>
  
  <body>
    <form action="" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->赛事纪律设置</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <!--内嵌表格begin-->
<c:forEach items="${rule}" var="es">
   <table width="55%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">
  <tr class="tableTitle">
    <td width="598" align="center"><div class="jilv"><span style="font-size:18px;">大会纪律</span></div>
      </td>
    
  </tr>
  
  <tr class="tableContent">
    <td colspan="2" align="center"><span style="font-size:16px;">大会纪律具体内容：</span><br />
      <textarea name="rule" id="rule" cols="70" rows="25">${es.meetingJl}</textarea></td>
  </tr>
  <tr class="tableContent">
    <td colspan="2" align="center"><div class="jilv"><span style="font-size:18px;">大会程序</span></div>
      </td>
  </tr>
  <tr class="tableContent">
    <td colspan="2" align="center"><span style="font-size:16px;">开幕式内容:</span><br />
      <textarea name="open" id="open" cols="70" rows="25">${es.openDh}</textarea></td>
  </tr>
  <tr class="tableContent">
    <td colspan="2" align="center"><span style="font-size:16px;">闭幕式内容：</span><br />
      <textarea name="close" id="close" cols="70" rows="25">${es.closeDh}</textarea></td>
  </tr>
 
  <tr class="tableContent">
    <td colspan="2" align="center"><input name="button" type="button" id="button" onClick="alterjl();"  value="保存"/>
    &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="button2" id="button2" onClick="window.location.href='set_dahuijilv.jsp'" value="返回"></td>
  </tr>
</table>
</c:forEach>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>
</form>
<br />

  </body>
</html>
