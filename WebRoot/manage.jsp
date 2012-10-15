<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>成绩管理</title>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/zDialog_inner.js"></script>
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script language="javascript">
function checkSelected()
{
  var finalitem = $("#finalitem").find("option:selected").val();
  var group = $("#group").find("option:selected").val();
     if(group=="0")
	   {
         alert('请选择组别');
		 return false;
       }
	   else
	   {
	     if(finalitem=="0"){
	     alert('请选择项目');
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
               url : "${ pageContext.request.contextPath }/servlet/GameManageGetAthleteListServlet",
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
					      alert("数据库记录数为"+json.contents.length+"条");
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
                        inhtml += "<td height='20'><div><a href='#' onclick='deletePlayer("+ json.contents[i].playernum +")'>删除</a>&nbsp;&nbsp;</div></td></tr>";
					} 
                                 
               }
                        $('.playertable').html(inhtml);  
               },
               error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
           });
}

function getPlayerInf()
{
  var playerNum = $("#playerNum").val();
  var finalItemId = $("#finalitem").find("option:selected").val();
  var a = /\d{2,5}/;
  if(!(a.test(playerNum)))
     {
       alert("如果要查询学生的成绩请输入四位的数字，查询教工请输入三位的数字");
     }
     else{
     if(confirm('您将查找号码为'+playerNum+'的运动员'))
      {  
       $.ajax( {
				url : "${pageContext.request.contextPath}/servlet/GameManageGetPlayerInfByPlayerNum",
				type : 'get',
				contentType : "application/json;charset=utf-8",

				dataType : 'json',
				data : 'playerNum=' + playerNum + '&finalItemId=' + finalItemId,
				success : function(json) {
					var inhtml = "";
					if(json.contents.length > 0)
                     {                 
		              for (i = 0; i < json.contents.length; i++) {
                                             
						inhtml += "<tr class='tableTitle' bgcolor='#006600'><td height='30' colspan='2' align='left'>运动员编号</td>";
                        inhtml += "<td height='30' colspan='4' align='left'><div><input type='text' id='playernum' readonly style='background-color:#CCCCCC' value='"+ json.contents[i].playernum +"'></input></div></td>";
                        inhtml += "<td height='30' colspan='2' align='left'>不可修改</td></tr>";
                        inhtml += "<tr class='tableTitle'><td height='30' colspan='2' align='left'>运动员姓名</td>";
                        inhtml += "<td height='30' colspan='4' align='left'><div><input type='text' id='playername' readonly style='background-color:#CCCCCC' value='"+ json.contents[i].playername +"'></input></div></td>";
                        inhtml += "<td height='30' colspan='2' align='left'>不可修改</td></tr>";
                        inhtml += "<tr class='tableTitle'><td height='30' colspan='2' align='left'>运动员性别</td>";
                        inhtml += "<td height='30' colspan='4' align='left'><div><input type='text' id='playersex' readonly style='background-color:#CCCCCC' value='"+ json.contents[i].playersex +"'></input></div></td>";
                        inhtml += "<td height='30' colspan='2' align='left'>不可修改</td></tr>";
                        inhtml += "<tr class='tableTitle'><td height='30' colspan='2' align='left'><span style='border:none;color:red;font:bold'>"+ json.contents[i].finalitemname +"分数</span></td>";
                        inhtml += "<td height='30' colspan='4'  align='left'><div><input type='text' id='score' value='"+ json.contents[i].score +"'></input></div></td>";   
                        inhtml += "<td height='30' colspan='2' align='left'>请输入正确格式</td></tr>";                                                                 
		                inhtml += "<tr class='tableTitle'><td height='30' colspan='2' align='left'>运动员是否违纪</td>";
		                inhtml += "<td height='30' colspan='4' align='left'><div><input type='text' id='foul' value='"+ json.contents[i].foul +"'></input></div></td>";
		                inhtml += "<td height='30' colspan='2' align='left'>0：没有违纪 1：已经违纪</td></tr>";
		                inhtml += "<tr class='tableTitle'><td height='30' colspan='2' align='left'>运动员是否破纪录</td>";
		                inhtml += "<td height='30' colspan='4' align='left'><div><input type='text' id='recordlevel' value='"+ json.contents[i].recordlevel +"'></input></div></td>";
		                inhtml += "<td height='30' colspan='2' align='left'>0：未破纪录 1：破学校纪录 2：破省记录</td></tr>";
		                inhtml += "<tr class='tableTitle'><td height='30' colspan='2' align='left'>所属部门</td>";
		                inhtml += "<td height='30' colspan='4' align='left'><div><input type='text' id='departname' readonly style='background-color:#CCCCCC' value='"+ json.contents[i].departname +"'></input></div></td>";
		                inhtml += "<td height='30' colspan='2' align='left'>不可修改</td></tr>";
                        inhtml += "<hr height='10px'/>";
                        inhtml +="<tr class='tableTitle'><td height='30' colspan='8' align='center'>";
					    inhtml +="<input type='button' value='更改记录' onclick='updateMatch("+json.contents[i].playernum+","+json.contents[i].matchid+")'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='reset' value='重置文本' onclick='resetText()'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='收起文本' onclick='hide()'>";
                        inhtml +="</td></tr>";                       
                        
					}
					           
                               $('.playertable1').html(inhtml);
                               $('.playertable1').toggle("slow");
                   }
                         else{ alert("此运动员可能没在此项目中，请核实运动员的组别及选择的项目并选择相应的项目再输入此运动员编号进行查询"); }      
               },
				error : function(xhr, status, errorThrown) {
					alert("此运动员可能没在此项目中，请核实运动员的组别及选择的项目并选择相应的项目再输入此运动员编号进行查询");
				}
			});
    }
     
     }
  }
  function deletePlayer(playerNum)
  {      
     if (confirm('警告：请确认次学生已经违纪，如果违纪，此操作该运动员将被除名，请谨慎操作！！！')) {
     if (confirm('警告：您将删除编号为' + playerNum +'的运动员')) {
     
     $.ajax({ 
               url : "${ pageContext.request.contextPath }/servlet/GameManageDelPlayerByPlayerNum",
               type : 'get',
               data : 'playerNum=' + playerNum,              
               success : function(mm) {
               var revalue = mm.replace(/\r\n/g, '');
               if (revalue == "success") {
							alert("删除成功!");
							getAthleteList();	
					}			
			   else{ 
			   alert("删除失败!数据库中已经无此运动员记录", function() {
							    window.location.href = window.location.href;
							}); }
         }
     });
  }}
  }
  
  function resetText()
  {
    $("#score").val("");
    $("#foul").val("");
    $("#recordlevel").val(""); 
  }
  function hide()
  {
    $('.playertable1').toggle("slow");    
  }
  function updateMatch(playernum,matchid)
  {
     if (confirm('警告：是否确定更新编号为'+playernum+'的运动员'))
     {          
        var score = $("#score").val();
        var foul = $("#foul").val();
        var recordlevel = $("#recordlevel").val();
        var b = /\d{1}/;
        var reg1 = /^[1-5]?\d\.\d\d$/;
        var reg2 = /^[1-5]?\d\.[1-5]\d\.\d\d$/;
        var reg3 = /^\d\d\.\d\d$/;
        var reg4 = /^[1-2]?\d\.[1-5]\d\.[1-5]\d\$/;
        if(((!reg1.test(score)) && (!reg2.test(score)) && (!reg3.test(score)) && (!reg4.test(score))) || (score=="") || (foul=="") || (recordlevel=="") || (!b.test(foul)) || (!b.test(recordlevel)) || parseInt(foul)>1 || parseInt(recordlevel)>2 )
               { alert("填写数据不正确，请重新填写后提交");return false; }
        else 
        {
              $.ajax({ 
               url : "${ pageContext.request.contextPath }/servlet/GameManageUpdateMatchByPlayerNum",
               type : 'get',
               data : 'playerNum='+ playernum +'&score='+ score +'&foul='+ foul +'&recordlevel='+ recordlevel +'&matchid='+ matchid,
               
               success : function(mm) {
               var revalue = mm.replace(/\r\n/g, '');
               if (revalue == "success") {
							alert('更新成功');
							$('.playertable1').toggle("slow");
							getAthleteList();
								}
			   else{ 
			   alert("更新失败!您已经更新过了", function() {
							    window.location.href = window.location.href;
							}); }
         }
     });
     }}
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
               
				 alert("成功生成此文档，请点击连接下载word附件");
				 var inhtml = "";
				   
				 for(i=0;i<json.contents.length;i++)	
				 {		
				   alert(json.contents[i].fileName1);
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
		<input type="text" align="right" id="playerNum" style=" color:#CCCCCC" value="请输入您要查找的号码" onfocus="if (value =='请输入您要查找的号码'){value =''};this.style.color='black'" onblur="if (value ==''){value='请输入您要查找的号码'};this.style.color='gray';"/>     
		<input type="button" value="按号码查询" onclick="getPlayerInf()">
		<a href="#" onclick="Dialog.alert('温情提示，请仔细查看：<br>输入运动员编号前，请先确认此运动员所属的组别及项目,选定组别及项目菜单后才能实现查询！！！请认真读完提示信息再操作，否则您的操作可能不起作用');" style="font-size:12px">友情提示,请点击这里</a>
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
