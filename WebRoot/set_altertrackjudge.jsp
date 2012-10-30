<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
		<link href="${pageContext.request.contextPath }/css/subcss.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/jjs/jquery-1.6.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
		<script type="text/javascript">

$(document).ready(function() {

	$(".stripe_tb tr").mouseover(function() { //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
				$(this).addClass("over");
			}).mouseout(function() { //给这行添加class值为over，并且当鼠标一出该行时执行函数
				$(this).removeClass("over");
			}) //移除该行的class
		$(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt

	});
function alter() {
	        var chiefjudge_1=$('#chiefjudge_1').val();
			var chiefjudge_2=$('#chiefjudge_2').val();
			var trackjudge=$('#trackjudge').val();
			var trackjudge_rollcall_1=$('#trackjudge_rollcall_1').val();
			var  trackjudge_rollcall_2=$('#trackjudge_rollcall_2').val();
			var  trackjudge_rollcall_3=$('#trackjudge_rollcall_3').val();
			var  startingpoint_1=$('#startingpoint_1').val();
			var  startingpoint_2=$('#startingpoint_2').val();
			var  startingpoint_3=$('#startingpoint_3').val();
			var  timejudge_1=$('#timejudge_1').val();
			var  timejudge_2=$('#timejudge_2').val();
			var  timejudge_3=$('#timejudge_3').val();
			var  endpoint_1=$('#endpoint_1').val();
			var  endpoint_2=$('#endpoint_2').val();
			var  endpoint_3=$('#endpoint_3').val();
			var  endpoint_4=$('#endpoint_4').val();
			var  endpoint_5=$('#endpoint_5').val();
			
			var tempStr = /^([\u4e00-\u9fa5]{1,},){0,}([\u4e00-\u9fa5]{1,})$|^\s*$/;
	        if(!tempStr.test(chiefjudge_1)){
		      Dialog.alert("总裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(chiefjudge_2)){
	          Dialog.alert("副总裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(trackjudge)){
	          Dialog.alert("径赛裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(trackjudge_rollcall_1)){
	          Dialog.alert("检录裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(trackjudge_rollcall_2)){
	          Dialog.alert("检录裁判长助理包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(trackjudge_rollcall_3)){
	          Dialog.alert("检录员包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(startingpoint_1)){
	          Dialog.alert("起点裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(startingpoint_2)){
	          Dialog.alert("起点裁判长助理包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(startingpoint_3)){
	          Dialog.alert("发令员包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(timejudge_1)){
	          Dialog.alert("记时长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(timejudge_2)){
	          Dialog.alert("记时员包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(timejudge_3)){
	          Dialog.alert("司线员会包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(endpoint_1)){
	          Dialog.alert("终点裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(endpoint_2)){
	          Dialog.alert("终点裁判长助理包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(endpoint_3)){
	          Dialog.alert("终点裁判员包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(endpoint_4)){
	          Dialog.alert("终点记录长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(endpoint_5)){
	          Dialog.alert("终点记录员包含特殊字符，请以英文逗号进行分隔");
	        }else{
									
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/UpdateTrackJudgeServlet",
					type : 'post',
				   	data:{chiefjudge_1:chiefjudge_1,chiefjudge_2:chiefjudge_2,trackjudge:trackjudge,
				   	trackjudge_rollcall_1:trackjudge_rollcall_1,trackjudge_rollcall_2:trackjudge_rollcall_2,
				   	trackjudge_rollcall_3:trackjudge_rollcall_3,startingpoint_1:startingpoint_1,
				   	startingpoint_2:startingpoint_2,startingpoint_3:startingpoint_3,timejudge_1:timejudge_1,
				   	timejudge_2:timejudge_2,timejudge_3:timejudge_3,endpoint_1:endpoint_1,endpoint_2:endpoint_2,
				   	endpoint_3:endpoint_3,endpoint_4:endpoint_4,endpoint_5:endpoint_5},
		success : function(mm) {
			var revalue = mm.replace(/\r\n/g, '');

			if (revalue == "success") {
				Dialog.alert("修改成功!");
			} else
				Dialog.alert("修改失败!");
		}
	});
	
	}
}

//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
</script>
<style>
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.pageTitle {
	color: #e1e2e3;
	font-size: 12px;
}
</style>

	</head>
  
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->修改径赛裁判员</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
 <table width="55%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">
<tr class="tableContent" >
                   <td  colspan="3" align="right" style="color:red;">
                   人名之间请以英文逗号隔开
                   </td>
                   </tr>
<c:forEach items="${officialsetlist1}" var="officialsetlist1">
					<tr class="tableContent" >
						<td width="347">
							总裁判长：
							</td>
							<td>
						 <input type="text" name="chiefjudge_1" id="chiefjudge_1" value="${officialsetlist1.chiefjudge_1}" />
					  </td>
    </tr>
					<tr class="tableContent" >
						<td>
							副总裁判长：
							</td>
							<td>
							<input type="text" name="chiefjudge_2" id="chiefjudge_2" value="${officialsetlist1.chiefjudge_2}" />
						</td>
					</tr>
					<tr class="tableContent" >
						<td>
							径赛裁判长: 
							</td>
							<td>
							<input type="text" name="trackjudge" id="trackjudge" value="${officialsetlist1.trackjudge}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							检录裁判长:
							</td>
							<td>
							<input type="text" name="trackjudge_rollcall_1" id="trackjudge_rollcall_1" value="${officialsetlist1.trackjudge_rollcall_1}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							检录裁判长助理：
							</td>
							<td>
							<input type="text" name="trackjudge_rollcall_2" id="trackjudge_rollcall_2" value="${officialsetlist1.trackjudge_rollcall_2}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							检录员:
							</td>
							<td>
							<textarea name="trackjudge_rollcall_3" id="trackjudge_rollcall_3" cols="45" rows="5">${officialsetlist1.trackjudge_rollcall_3}</textarea>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							起点裁判长：
							</td>
							<td>
							<input type="text" name="startingpoint_1" id="startingpoint_1" value="${officialsetlist1.startingpoint_1}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							起点裁判长助理：
							</td>
							<td>
							<input type="text" name="startingpoint_2" id="startingpoint_2" value="${officialsetlist1.startingpoint_2}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							发令员：
							</td>
							<td>
							<textarea name="startingpoint_3" id="startingpoint_3" cols="45" rows="5">${officialsetlist1.startingpoint_3}</textarea>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							计时长：
							</td>
							<td>
							<input type="text" name="timejudge_1" id="timejudge_1" value="${officialsetlist1.timejudge_1}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							计时员：
							</td>
							<td>
							<textarea name="timejudge_2" id="timejudge_2" cols="45" rows="5">${officialsetlist1.timejudge_2}</textarea>
						</td>
					</tr>
                    <tr class="tableContent" >
						<td width="347">
							司线员：
							</td>
							<td>
						 <input type="text" name="timejudge_3" id="timejudge_3" value="${officialsetlist1.timejudge_3}" />
					  </td>
    </tr>
					<tr class="tableContent" >
						<td>
							终点裁判长：
							</td>
							<td>
							<input type="text" name="endpoint_1" id="endpoint_1" value="${officialsetlist1.endpoint_1}" />
						</td>
					</tr>
					<tr class="tableContent" >
						<td>
							终点裁判长助理： 
							</td>
							<td>
							<input type="text" name="endpoint_2" id="endpoint_2" value="${officialsetlist1.endpoint_2}" />
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							终点裁判员：
							</td>
							<td>
							<textarea name="endpoint_3" id="endpoint_3" cols="45" rows="5">${officialsetlist1.endpoint_3}</textarea>
						</td>
					</tr>
                    <tr class="tableContent" >
						<td width="347">
							终点记录长：
							</td>
							<td>
						 <input type="text" name="endpoint_4" id="endpoint_4" value="${officialsetlist1.endpoint_4}" />
					  </td>
    </tr>
					<tr class="tableContent" >
						<td>
							终点记录员：
							</td>
							<td>
							<input type="text" name="endpoint_5" id="endpoint_5" value="${officialsetlist1.endpoint_5}" />
						</td>
					</tr>
                  </c:forEach>
				<tr class="tableContent" >
				<td colspan="3">
				<input type="button" name="button" id="button" value="保存修改" onClick="alter()">
				<input type="button" value="返回" onClick="window.location.href='${pageContext.request.contextPath }/servlet/SelectAllItemServlet'">
			  </tr>
			</table>
			</td>
			</tr>
			</table>
</body>
</html>
