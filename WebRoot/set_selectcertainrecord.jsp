<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
     
    <title>My JSP 'set_uprecord.jsp' starting page</title>
    <link href="${pageContext.request.contextPath }/css/subcss.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.6.min.js">
</script>

		 
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


function deleteRecordcertain(recordId) {
	if (confirm('警告：您确认要删除吗？')) {
		$
				.ajax( {
					url : "${pageContext.request.contextPath }/servlet/DeleteRecordServlet",
					type : 'post',
					data :  'recordId='+recordId,
					
					success : function(mm) {
						var revalue = mm.replace(/\r\n/g, '');

						if (revalue == "success") {
							alert("删除成功!", function() {
								window.location.href = window.location.href;
							});
						} else
							alert("删除失败!", function() {
								window.location.href = window.location.href;
							});
					}
				});
	}
}
 
 
</script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr bgcolor="#353c44">
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->赛会纪录</span></td>
              </tr>
            </table>
            
            <p><input type="button" name="button3" id="button3"  onClick="window.location.href='set_record.jsp'" value="返回主菜单">
             
            </p>
  <div style="position:relative; margin:0 auto; width: 1033px; height: 612px;">
    <table width="1033" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
       
<tr class="tableTitle" height="45">
<td align="center">
					记录Id				</td>
<td align="center">
					项目名称				</td>
<td align="center">
					姓名				</td>
<td align="center">
					性别				</td>
<td align="center">
					成绩				</td>
<td align="center">
					系别				</td>
<td align="center">
					运动会名称				</td>
<td align="center">
					时间				</td>
<td align="center">
					级别				</td>
<td align="center">
					操作				</td>
	  </tr>
      <c:forEach items="${admininf}" var="re">
				<tr background="" class="tableContent" height="40">
				<td>
						${re.recordId}</td>
			  	 
					<td>
						${re.itemName}</td>
			  	<td>
						${re.plaName}</td>
				  <c:if test="${re.plaSex == 1}"> 
					<td>
						男
					</td>
					</c:if>
					<c:if test="${re.plaSex == 0}"> 
					<td>
						女
					</td>
					</c:if>	
				   
                  <td> ${re.sor}	</td>
                  <td> ${re.depName}	</td>
                  <td> ${re.sportsName1}	</td>
                  <td> ${re.recTime}	</td>
                  <td> ${re.recLevel}	</td>
			  <td>

						<a title="修改"
							href="${pageContext.request.contextPath }/servlet/RecordSelectByNameTimeServlet?recordId=${re.recordId}">修改</a>
						<a title="删除" onClick="deleteRecordcertain('${re.recordId}')" href="#">删除</a>					</td>
		</tr>
			</c:forEach>
    </table>
  </div>
  </body>
  
</html>
