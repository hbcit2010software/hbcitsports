<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 
<html>
  <head>
    
    <title>My JSP 'bumenshezhi.jsp' starting page</title>
    
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
 
 </script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#353c44">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->学生记录基本操作</span></td>
              </tr>
  </table>
  <%-- <div style="position:relative; margin:0 auto;">--%>
  <form id="form1" name="form1" method="post" action="servlet/SelectCertainRecordServlet">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">
      <tr class="tableTitle">
        <td height="45" colspan="2" align="center">学生记录基本操作</td>
      </tr>
      <tr class="tableContent">
        <td width="81" height="40" colspan="2"><label>
        <a title="添加新记录"
							href="${pageContext.request.contextPath }/servlet/SeleteItemRecordSonServlet"><font>添加新记录</font></a>
          
           
        </label></td>

      </tr>
       
      <tr class="tableContent">
        <td rowspan="2">条件查询：</td>
        <td height="36">项目名称：
          <label>
          <select name="itemname" id="itemname">
          <c:forEach items="${itemRecord}" var="re">
          <option value="${re.iteId }">${re.itemName}</option>
          </c:forEach>
          </select>
        </label></td>
      </tr>
      <tr class="tableContent">
        <td height="40">创记录时间：
          <label>
          <input type="text" name="recTime" id="recTime" value="" onClick="WdatePicker()">
        </label></td>
      </tr>
      <tr class="tableContent">
      <td></td>
       <td height="45"><input type="submit" name="button3" id="button3" value="查询">
       </td>
      </tr>
    </table>
    </form>
  <br>
  </body>
</html>
