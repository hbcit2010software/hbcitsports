<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
	 <link href="css/subcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
		<script language="JavaScript">

function alter() {
	        var  fieldjudge=$('#fieldjudge').val();
			var  fieldjudge_1=$('#fieldjudge_1').val();
			var  fieldjudge_2=$('#fieldjudge_2').val();
			var  fieldjudge_3=$('#fieldjudge_3').val();
			var  fieldjudge_4=$('#fieldjudge_4').val();
			var  fieldjudge_5=$('#fieldjudge_5').val();
			var  fieldjudge_6=$('#fieldjudge_6').val();
			
			var tempStr = /^([\u4e00-\u9fa5]{1,},){0,}([\u4e00-\u9fa5]{1,})$/;
	        if(!tempStr.test(fieldjudge)){
		      Dialog.alert("田赛裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(fieldjudge_1)){
	          Dialog.alert("总记录裁判长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(fieldjudge_2)){
	          Dialog.alert("记录员包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(fieldjudge_3)){
	          Dialog.alert("检查长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(fieldjudge_4)){
	          Dialog.alert("检查员包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(fieldjudge_5)){
	          Dialog.alert("场地器材组长包含特殊字符，请以英文逗号进行分隔");
	        }else if(!tempStr.test(fieldjudge_6)){
	          Dialog.alert("器材员包含特殊字符，请以英文逗号进行分隔");
	        }else{
						
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/UpdateFiledJudgeServlet",
					type : 'post',
				   	data:{fieldjudge:fieldjudge,fieldjudge_1:fieldjudge_1,fieldjudge_2:fieldjudge_2,
				   	fieldjudge_3:fieldjudge_3,fieldjudge_4:fieldjudge_4,fieldjudge_5:fieldjudge_5,
				   	fieldjudge_6:fieldjudge_6},
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置--修改田赛裁判员</span></td>
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
					<tr  class="tableContent" >
						<td width="347">
							田赛裁判长：
							</td>
							<td>
						 <input type="text" name="fieldjudge" id="fieldjudge" value="${officialsetlist1.fieldjudge}" />
					    </td>
                    </tr>
					<tr  class="tableContent" >
						<td>
							总记录裁判长：
							</td>
							<td>
							<input type="text" name="fieldjudge_1" id="fieldjudge_1" value="${officialsetlist1.fieldjudge_1}" />
						</td>
					</tr>
					<tr  class="tableContent" >
						<td>
							记录员：
							</td>
							<td>
							<textarea name="fieldjudge_2" id="fieldjudge_2" cols="45" rows="5" >${officialsetlist1.fieldjudge_2}</textarea>
						</td>
					</tr>
                    <tr  class="tableContent" >
						<td>
							检查长：
							</td>
							<td>
							<input type="text" name="fieldjudge_3" id="fieldjudge_3" value="${officialsetlist1.fieldjudge_3}" />
						</td>
					</tr>
                  	<tr  class="tableContent" >
						<td>
							检查员：
							</td>
							<td>
							<textarea name="fieldjudge_4" id="fieldjudge_4" cols="45" rows="5" >${officialsetlist1.fieldjudge_4}</textarea>
						</td>
					</tr>
                    <tr  class="tableContent" >
						<td>
							场地器材组长：
							</td>
							<td>
							<input type="text" name="fieldjudge_5" id="fieldjudge_5" value="${officialsetlist1.fieldjudge_5}" />
						</td>
					</tr>
                    <tr  class="tableContent" >
						<td>
							器材员：
							</td>
							<td>
							<textarea name="fieldjudge_6" id="fieldjudge_6" cols="45" rows="5" >${officialsetlist1.fieldjudge_6}</textarea>
						</td>
					</tr>
					<tr  class="tableContent" >
					<td colspan="3">
				<input type="button" name="button" id="button" value="保存修改" onClick="alter()">
				<input type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath }/servlet/SelectAllItemServlet'">
					</td>
					</tr>
                </c:forEach>
				</table> 
				</td>
				</tr>
				</table>		
</body>
</html>

