<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>成绩录入</title>
    <link href="css/subcss.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">
var reg ;
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
	
	//根据组别获取相应的项目 	
	
	function gpchange(){
		var option = $("#group").find("option:selected").text();	//被选取的组别 
		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/GetConditonServlet?action=itmecondBygp",
				type : 'get',
				contentType : "application/json;charset=utf-8",

				dataType : 'json',
				data : 'option=' + option,
				success : function(json) {
					var inhtml = "<option>--请选择--</option>";
					for (i = 0; i < json.length; i++) {
						inhtml += "<option>" + json[i] + "</option>";
					}	
					$('#item').html(inhtml);
				},
				error : function(xhr, status, errorThrown) { 
					alert("errorThrown=" + errorThrown);
				}
		});
	}	
	//根据项目获取项目类型
	
	function getItemType(){
		var group = $("#group").find("option:selected").text();		//被选取的组别
		var item = $("#item").find("option:selected").text();		//被选取的项目
		if( group == "--请选择--" || item == "--请选择--"){
			Dialog.alert("项目或组别为选择，请检查！ ");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/GetMessageServlet?action=itemtype",
				type : 'get',
				contentType : "application/json;charset=utf-8",
				dataType : 'json',
				data : 'item=' + item,
				success : function(tp) {
					if( tp == "1"){
					
						tp = "<tr class='tableTitle' align='center'><td width='20%'>号码</td><td width='20%'>姓名</td><td width='20%'>道次</td><td width='20%'>成绩</td><td width='40%'>成绩格式</td></tr>";
					}
					
					if( tp == "2" ){
						tp = "<tr class='tableTitle' align='center'><td width='20%'>号码</td><td width='20%'>姓名</td><td width='20%'>出场顺序</td><td width='20%'>成绩</td><td width='40%'>成绩格式</td></tr>";
					}
					
					if( tp == "3"){
						tp = "<tr class='tableTitle' align='center'><td width='20%'>单位</td><td width='20%'>道次</td><td width='20%'>成绩</td><td width='20%'>成绩格式</td><td width='20%'></td></tr>";
					}
					 $('#content').html(tp);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		});	
	}
	//根据项目获取运动员的信息
	function getPlayerMessage(){
		var item = $("#item").find("option:selected").text();
		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/GetMessageServlet?action=playermse",
				type : 'get',
				contentType : "application/json;charset=utf-8",
				dataType : 'json',
				data : 'item=' + item,
				success : function(json) {	
					var inhtml = "";
						var itemtype = json[0];//项目类型 
						if( itemtype == "3"){
						var id = -1;
							for(i =1 ; i < json.length; i++){
								inhtml += "<tr class='tableTitle'><td colspan='4'>第"+i+"组</td></tr>";
								var json1 = json[i];
								for( j = 0 ; j < json1.length; j++ ){
										var json2 = json1[j];
										inhtml += "<tr class='tableContent'>";
										for( q = 0; q < json2.length; q++ ){
											if( q == 0 ){
												inhtml += "<td width='20%'><input name='playernum' value='"+json2[0]+"' style=' border-style:none' readonly='readonly'/></td>";	//部门 
											}
											if( q == 1 ){
												inhtml += "<td width='20%'>"+json2[1]+"</td>";	//道次
											}
											if( q == 2 ){
												id++;
												if( json2[2] == null){
													inhtml += "<td width='20%' align='left'><input id='"+id+"' name='score' value='' onblur='javascript:curInput=this;checkFormat();'/></td>";	//成绩 
												}else{
													inhtml += "<td width='20%' align='left'><input id='"+id+"' name='score' value='"+json2[2]+"' onblur='javascript:curInput=this;checkFormat();'/></td>";	//成绩
												} 
											}
										}
										inhtml += "<td width='20%'><input type='text' name='format' style=' text-align:center; border-style:none' readonly='readonly' value=''/></td></tr>";						
								}
							}
						}else{
							var id = -1;
							for(i =1 ; i < json.length; i++){
								inhtml += "<tr class='tableTitle'><td colspan='5'>第"+i+"组</td></tr>";
								var json1 = json[i];
								for( j = 0 ; j < json1.length; j++ ){
										var json2 = json1[j];
										inhtml += "<tr class='tableContent'>";
										for( q = 0; q < json2.length; q++ ){
											if( q == 0 ){
												inhtml += "<td width='20%'><input name='playernum' value='"+json2[0]+"' style=' border-style:none' readonly='readonly'/></td>";	//运动员号码 
											}
											if( q == 1 ){
												inhtml += "<td width='20%'>"+json2[1]+"</td>";	//运动员名字
											}
											if( q == 2 ){
												inhtml += "<td width='20%'>"+json2[2]+"</td>";	//道次或出场顺序 
											}
											if( q == 3){
												id++;
												if( json2[3] == null){
													inhtml += "<td width='20%' align='left'><input id='"+id+"' name='score' value='' onblur='javascript:curInput=this;checkFormat();'/></td>";	//成绩 
												}else{
													inhtml += "<td width='20%' align='left'><input id='"+id+"' name='score' value='"+json2[3]+"' onblur='javascript:curInput=this;checkFormat();'/></td>";	//成绩
												}
											}
										}
										inhtml += "<td width='20%'><input type='text' name='format' style=' text-align:center; border-style:none' readonly='readonly' value=''/></td></tr>";						
								}
							}
						}
					//}
					$('#content1').html(inhtml);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		});
	}
	 //提交成绩 
	function saveScore(){
		var item = $("#item").find("option:selected").text();
		var group = $("#group").find("option:selected").text();
		
		var playernum="";var score="";
		
		var playernumobj = document.getElementsByName("playernum");
		var scoreobj = document.getElementsByName("score");
		
		for(var i = 0; i < playernumobj.length; i++ ){
				playernum += playernumobj[i].value;
				score += scoreobj[i].value;
				if(scoreobj[i].value.length== 0){
					Dialog.alert("提示：有成绩未录入！未完成提交！  ");
					return false;
				}
				playernum += ",";
				score += ",";	
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/AddScoreServlet?action=addscore",
				type : 'get',
				data : {playernum:playernum,score:score,item:item,group:group},
				success : function(mm) {
					Dialog.alert(mm);
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		});
	}
	
	//获取成绩格式 
	function getFormat(){
	var item = $("#item").find("option:selected").text();
	$.ajax({
			url : "${pageContext.request.contextPath}/servlet/AddScoreServlet?action=getFormat",
			type : 'get',
			data : {finalitemname:item},
			success : function(mm) {
				$("input[name=format]").val(mm+"");
			},
			error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
		
		});
	
	}
	//获取成绩的正则表达式 
	function getCheckFormat(){
		var item = $("#item").find("option:selected").text();
			$.ajax({
				url : "${pageContext.request.contextPath}/servlet/AddScoreServlet?action=checkFormat",
				type : 'get',
				data : {finalitemname:item},
				success : function(mm) {
					reg = new RegExp(eval(mm));
				},
				error : function(xhr, status, errorThrown) {
						alert("errorThrown=" + errorThrown);
					}
			});
	}
	//验证 成绩格式 
	function checkFormat(){
		if( curInput!=null){
			var score = curInput.value;
			if( score != ''){
				if( !reg.test(score) ){
					Dialog.alert("格式不正确！");
					//curInput.focus();
					return false;			
							}			
						}						
					} 		
		
	}
	
	
	//清空所有成绩 
	function empty(){
		$("input[name=score]").val("");
	}
	
	function getGpIt(){
		var item = $("#item").find("option:selected").text();
		var group = $("#group").find("option:selected").text();
		
		var inhtml = "<td id='title' height='20' width='100%' align='center' colspan='5'>"+group+"----->"+item+"</td>";
		$('#title').html(inhtml);
	}
</script>
  </head>
  
  <body>
    <form action="servlet/AddScoreServlet" method="post">
    	<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
        	<tr>
            	<td bgcolor="#353c44" colspan="9"  height="19">
                    <table>
                    	<tr>
                         <td width="6%"  valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                         <td width="94%" valign="bottom"><span class="pageTitle">赛前管理-->成绩录入</span></td>
                         </tr>
                     </table>
                 </td>
            </tr>
            
            <tr class="tableTitle">
            	<td height="20" colspan="7">请选择条件</td>
            </tr>
            <tr class="tableTitle" align="center" bgcolor="#a8c7ce">
            	<td height="20" width='20%'>组别</td>
                <td height="20" width='20%'>
                	<select id="group" onChange="gpchange()">
                    	<option>--请选择--</option>
                    	<c:forEach items="${sessionScope.conditionlist}" var="gp">
                        	<option>${gp.groupname }</option>
                        </c:forEach>
                    </select>
                </td>
                <td height="20" width='20%'>项目名称</td>
                <td height="20" width='20%'>
                	<select id="item" name="finalitem">
                    	<option>--请选择--</option> 
                    </select>
                </td>
                <td width='20%' height="20"><input type="button" value="录入成绩"  onclick="getItemType(),getPlayerMessage(),getFormat(),getCheckFormat(),getCheckFormat(),getGpIt();"/></td>
            </tr>
       		<tr id="title" class='tableTitle'></tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  class="stripe_tb" id="content"> 
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  class="stripe_tb" id="content1"> 
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" > 
            <tr><td colspan="4"><input type="button" value="提交" onclick="saveScore();"/><input type="button" value="清空所有成绩" onclick="empty();"/></td></tr>
        </table>
    </form>
  </body>
</html>
