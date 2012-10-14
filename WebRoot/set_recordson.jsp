<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
 
    
    <title>My JSP 'set_recordson.jsp' starting page</title>
     <link href="${pageContext.request.contextPath }/css/subcss.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.6.min.js">
</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
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

		function addRecord()
		{
			var plaName=$('#plaName').val();
			var sportsName=$('#sportsName').val();
			var recTime=$('#recTime').val().substring(0, 7);
			var recLevel=$('#recLevel').val();
			var sor=$('#sor').val();
			var depName=$('#depName').val();
			var itemName=$('#select').find('option:selected').val();
			 var sex=document.getElementsByName("SexRadioGroup");
			var plaSex;
			for(var i=0;i<sex.length;i++){
	           if(sex[i].checked==true){
			       if(sex[i].value=="1"){
				      plaSex = 1;
			       }else{
				      plaSex = 0;
			       }
 		       }
  		    }
			
			
			
		if (plaName.length == 0) {
		alert("姓名不能为空!");
		return false;
		}
		if (sx.length == 0) {
		alert("性别不能为空!");
		return false;
		}
		if (sor.length == 0) {
		alert("成绩不能为空!");
		return false;
		}
		if (depName.length == 0) {
		alert("系别不能为空!");
		return false;
		}
		if (sportsName.length == 0) {
		alert("运动会名称不能为空!");
		return false;
		}
		if (recTime.length == 0) {
		alert("创记录时间不能为空!");
		return false;
		}
		if (recLevel.length == 0) {
		alert("记录级别不能为空!");
		return false;
		}
		 

			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/AddRecordServlet",
					type : 'post',
				   // data : 'username='+username+'&password='+password+'&dj='+dj,
					data:{plaName:plaName,sportsName:sportsName,recTime:recTime,recLevel:recLevel,sor:sor,depName:depName,itemName:itemName,plaSex:plaSex},
					
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								alert("添加成功!",function(){window.location.reload();});
							}
							else
								alert("添加失败!");
						}
					});	
		}
</script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#353c44">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->学生记录基本操作-->添加记录</span></td>
              </tr>
            </table>
            <p></p>
            <p>
              <label>
               
              </label>
            </p>
             
<div style="position:relative; margin:0 auto;"> 
	  <table border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">
                	<tr class="tableTitle" height="45">
	  	  			<td colspan="2" align="center" height="45">添加学生记录</td>
                    </tr>
       	<tr class="tableContent">
<td width="127"align="center" height="40">
							项目名称
						:</td>
						<td width="325">
							&nbsp;
							<label>
			<select name="select" id="select">
			<option value="0">-请选择-</option>
          <c:forEach items="${iteRecord}" var="rs">
          <option value="${rs.iteId }">${rs.itemName}</option>
          </c:forEach>
          </select></label>		  			 </td>
		</tr>
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							姓名
						:</td>
						<td>
							&nbsp;
							<input type="text" value="" name="plaName" id="plaName" size="36" maxlength="10">						</td>
					</tr>
					
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							性别
						:</td>
						<td><form name="form1" method="post" action="">
						  
						    <label>
						      <input name="SexRadioGroup" type="radio" id="sx" value="1" checked="checked">
						      男						   
						    
						   
						      <input name="SexRadioGroup" type="radio" id="sx" value="0">
                               女						   
						    
					      
						  </label>
						</form>
					 </td>
					</tr>
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							成绩
						:</td>
						<td>
							&nbsp;
							<input type="text" value="" name="sor" id="sor" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							系别
						:</td>
						<td>
							&nbsp;
							<input type="text" value="" name="depName" id="depName" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							运动会名称
						:</td>
						<td>
							&nbsp;
							<input type="text" value="" name="sportsName" id="sportsName" size="36" maxlength="10">						</td>
					</tr>
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							破记录时间(年)
						:</td>
						<td>
							&nbsp;
							<input type="text" value="" name="recTime" id="recTime" size="36" maxlength="10" onClick="WdatePicker()">						</td>
					</tr>
					<tr class="tableContent">
						<td width="127"align="center" height="40">
							记录级别
						:</td>
						<td width="325">
							&nbsp;
							<label>
			<select name="recLevel" id="recLevel">
			<option value="0">-院级-</option>
			<option value="1">-省级-</option>
          </select></label>					</tr>
                    <tr class="tableContent">
						
						<td colspan="2">
						&nbsp;
						
                        <input type="button" name="button" id="button" onClick="addRecord()" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="set_record.jsp"><input type="button" name="button2" id="button2"  value="返回"></a>
						</td>
					</tr>
				</table>
  </div>
  </body>
</html>
