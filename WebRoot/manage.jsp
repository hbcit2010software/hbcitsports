<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>成绩管理</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script language="javascript">
function checkSelected()
{
  var finalitem = $("#finalitem").find("option:selected").val();
  var group = $("#group").find("option:selected").val();
     if(group=="0")
	   {
         Dialog.alert('请选择组别');
		 return false;
       }
	   else
	   {
	     if(finalitem=="0"){
	     Dialog.alert('请选择项目');
		 return false;
		 }
		 else { getAthleteList(); }	   
  }
}

  function getFinalItems()
     {
           var groupid = $("#group").find("option:selected").val();
           
			$.ajax( {
				url : "${pageContext.request.contextPath}/servlet/GameManageGetItemsServlet",
				type : 'get',
				contentType : "application/json;charset=utf-8",

				dataType : 'json',
				data : 'groupid=' + groupid,
				success : function(json) {
					var inhtml = "";
					inhtml += "<option value='0'>--请选择项目--</option>";
					for (i = 0; i < json.contents.length; i++) {

						inhtml += "<option value='" +json.contents[i].itemid+ "'>"+ json.contents[i].itemname + "</option>";

					}
					$('#finalitem').html(inhtml);

				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
			});
}

  function getAthleteList()
     {
          
           var finalItemId = $("#finalitem").find("option:selected").val();
           $.ajax({
               url : "${ pageContext.request.contextPath }/servlet/GameManageGetAthleteListServlet?action=get",
               type : 'get',
               contentType : "application/json;charset=utf-8",
               dataType : 'json',
               data : 'finalItemId=' + finalItemId,
               success : function(json) {
                      var inhtml = "";
                      inhtml += "<tr class='tableTitle' style='text-align:center'>";
                      inhtml += "<td height='20'><div>号码</div></td>";
                      inhtml += "<td height='20'><div>姓名</div></td>";                 
                      inhtml += "<td height='20'><div>性别</div></td>";               
                      inhtml += "<td height='20'><div>成绩</div></td>";                  
                      inhtml += "<td height='20'><div>是否违纪 </div></td>";                 
		              inhtml += "<td height='20'><div>破纪录</div></td>";
		              inhtml += "<td height='20'><div>部门名称 </div></td>";                    
		              inhtml += "<td height='20'><div>基本操作</div></td></tr>"; 
	                 if(json.contents.length <= 0){
					      Dialog.alert("数据库记录数为"+json.contents.length+"条");
					    inhtml += "<tr><td height='40' colspan='8'><div style='color:red; text-align:center; font-style:bold' background='green'>数据库中无记录，请重新查询！！！</div></td></tr>";
					       }                                            
		             else{ 
		                  //alert("数据库记录数为"+json.contents.length+"条");
		                  for (i = 0; i < json.contents.length; i++) {
						inhtml += "<tr><td height='20'><div>"+ json.contents[i].playernum +"</div></td>";
                        inhtml += "<td height='20'><div>"+ json.contents[i].playername +"</div></td>";
                        inhtml += "<td height='20'><div>"+ json.contents[i].playersex +"</div></td>";
                        inhtml += "<td height='20'><div>"+ json.contents[i].score +"</div></td>";                                                                    
		                inhtml += "<td height='20' style='color:#ff0000; font:bold'><div>"+ json.contents[i].foul +"</div></td>";
		                inhtml += "<td height='20' style='color:#ff0000; font:bold'><div>"+ json.contents[i].recordlevel +"</div></td>";
		                inhtml += "<td height='20'><div>"+ json.contents[i].departname +"</div></td>";
                        inhtml += "<td height='20'><div><a href='javascript:void(0);' onclick='updScore("+json.contents[i].matchid+")' style='font-size:15px'>修改</a>&nbsp;&nbsp;</div></td></tr>";
					} 
                                 
               }
                        $('.playertable').html(inhtml);  
               },
               error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
           });
}

 function updScore(matchid){
 var finalItemId = $("#finalitem").find("option:selected").val();
            $.ajax({
               url : "${ pageContext.request.contextPath }/servlet/GameManageGetAthleteListServlet?action=upds&finalItemId="+finalItemId+"&matchid="+matchid,
               type : 'get',
               contentType : "application/json;charset=utf-8",
               dataType : 'json',
               data : 'matchid=' + matchid,
               success : function(json) {
             // alert(json.contents.length);
                  for (i = 0; i < json.contents.length; i++) {
                  //alert(json.contents[i].playerid);
                     var diag = new Dialog();
						diag.Top =20;
						diag.Width = 400;
						diag.Height = 200;
						diag.Title = "修改部门积分";
						diag.URL = "${ pageContext.request.contextPath }/updScore.jsp?playernum="+json.contents[i].playernum+"&playername="+json.contents[i].playername+"&playersex="+ json.contents[i].playersex+"&score="+json.contents[i].score+"&foul="+json.contents[i].foul+"&recordlevel="+json.contents[i].recordlevel+"&departname="+json.contents[i].departname+"&matchid="+json.contents[i].matchid+"&playerid="+json.contents[i].playerid;
						diag.OKEvent = function(){
							window.location.reload();
							//diag.close();
					};
						diag.ShowCloseButton=false;
						diag.MessageTitle = "修改运动会提示：";
						diag.Message = "填完各项内容后不要忘记先\"确认修改\"，然后才可关闭窗口";
						diag.show();
						diag.okButton.value="结果刷新";
						diag.cancelButton.value="关闭";
                }},
               error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
           });
           
	}
  
  function createWordOfAthleteInf()
  {
           var finalItemId = $("#finalitem").find("option:selected").val()
           if(finalItemId != '0')
           {
           $.ajax({
               url : "${ pageContext.request.contextPath }/servlet/GameManageCreateWordOfAthleteInfServle",
               type : 'get',
               data : 'finalItemId=' + finalItemId,
               contentType : "application/json;charset=utf-8",

			   dataType : 'json',
               success : function(json) {
               
				 Dialog.alert("成功生成此文档，请点击连接下载word附件");
				 var inhtml = "";
				   
				 for(i=0;i<json.contents.length;i++)	
				 {		
				   //alert(json.contents[i].fileName1);
				   inhtml = "<a href='download.jsp?file="+json.contents[i].file+"&fileName="+ encodeURIComponent(encodeURIComponent(json.contents[i].fileName1)) +"'>"+ json.contents[i].fileName1 +"</a>";
                   $("#aOfUpload").html(inhtml);
                 }  
               }, 
               error : function(xhr, status, errorThrown) {
					alert("服务器连接出错了");
				}			
               });
  }
           else {
           alert('请选择项目，如无项目，请先选择组别再选择项目名称！！！');
           }
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛中管理-->成绩管理</span></td>
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
	<div width="100%">
		<label>请选择必选内容：</label>		
		组别<select id="group" name="group" onChange="getFinalItems()">
		<option value="0">--请选择组别--</option>
		<c:forEach items="${ sessionScope.groupList }" var="group">
		<option value="${ group.groupid }">${ group.groupname }</option>
		</c:forEach>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		项目<select id="finalitem"  name="finalitem">
		<option value="0">--请选择项目--</option>		
		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
		<input type="button" value="按条件查询" onclick="checkSelected()">
		&nbsp;&nbsp;&nbsp;&nbsp;
		
		<a href="#" onclick="Dialog.alert('温情提示，请仔细查看：<br>修改运动员成绩时请输入正确成绩格式,例如：10.23')" style="font-size:12px">友情提示,请点击这里</a>
		</div>
		</td></tr>
  	<tr><td>
    <!--内嵌表格begin-->
     <table width="100%"   cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
      <tr>
      <td colspan='8'>
      <table class='playertable1' width="100%" style="text-align:center; display:none;" border="0">                     
      </table>
      </td>
      </tr>
      
	 <tr>
	 <td colspan="8">
	 <table  width="100%" style="text-align:center" class='playertable' border="0">
	 <tr class="tableTitle" style='text-align:center'>
                      <td height='20'><div>号码</div></td>
                      <td height='20'><div>姓名</div></td>                 
                      <td height='20'><div>性别</div></td>               
                      <td height='20'><div>成绩</div></td>                  
                      <td height='20'><div>是否违纪 </div></td>                 
		              <td height='20'><div>破纪录</div></td>
		              <td height='20'><div>部门名称 </div></td>                    
		              <td height='20'><div>基本操作</div></td>
	 </tr>
    </table>
     <!--内嵌表格end-->
    </td>
  </tr>
</table>
<div align="right" style="margin-right:50px; margin-top:20px">
<input type="button" value="打印成绩单" onclick="createWordOfAthleteInf()">
<span id='aOfUpload'></span>
<div>
</div>
</div>
</td>
</tr>
</table>
</body>
</html>