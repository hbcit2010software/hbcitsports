<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'updateMarks.jsp' starting page</title>
     
     <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">

    function resetText()
   {
    $("#score").val("");
   }
	  function updateMatch(playernum,matchid)
	  {
	     if (confirm('警告：是否确定更新编号为'+playernum+'的运动员'))
	     {          
	        var score = $("#score").val();
	        var foul = $("#foul").find("option:selected").val();
	        var recordlevel = $("#record").find("option:selected").val();
	        var b = /\d{1}/;
	        var reg1 = /^[1-5]?\d\.\d\d$/;
	        var reg2 = /^[1-5]?\d\.[1-5]\d\.\d\d$/;
	        var reg3 = /^\d\d\.\d\d$/;
	        var reg4 = /^[1-2]?\d\.[1-5]\d\.[1-5]\d\$/;
	        if(((!reg1.test(score)) && (!reg2.test(score)) && (!reg3.test(score)) && (!reg4.test(score))) || (score==""))
	               { Dialog.alert("填写数据不正确，请重新填写后提交");return false; }
	        else 
	        {
	              $.ajax({ 
	               url : "${ pageContext.request.contextPath }/servlet/GameManageUpdateMatchByPlayerNum",
	               type : 'get',
	               data : 'playerNum='+ playernum +'&score='+ score +'&foul='+ foul +'&recordlevel='+ recordlevel +'&matchid='+ matchid,
	               
	               success : function(mm) {
	               var revalue = mm.replace(/\r\n/g, '');
	               if (revalue == "success") {
								Dialog.alert('更新成功');
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
  
    </script>
    
  </head>

  <body>
    <%
       String playernum = "";
       String playername = "";
       String playersex = "";
       String score = "";
       String foul = "";
       String recordlevel = "";
       String departname = "";
       String matchid = "";
       playernum = new String(request.getParameter("playernum").getBytes("ISO-8859-1"),"UTF-8");
       playername = new String(request.getParameter("playername").getBytes("ISO-8859-1"),"UTF-8");
       playersex = new String(request.getParameter("playersex").getBytes("ISO-8859-1"),"UTF-8");
       score = new String(request.getParameter("score").getBytes("ISO-8859-1"),"UTF-8");
       foul = new String(request.getParameter("foul").getBytes("ISO-8859-1"),"UTF-8");
       recordlevel = new String(request.getParameter("recordlevel").getBytes("ISO-8859-1"),"UTF-8");
       departname = new String(request.getParameter("departname").getBytes("ISO-8859-1"),"UTF-8");
       matchid = new String(request.getParameter("matchid").getBytes("ISO-8859-1"),"UTF-8");
       //System.out.println(playernum+playername+playersex+score+foul+recordlevel+departname);
       System.out.println(recordlevel);
      %>
       <table align="center">
       
       <tr><td><label>运动员编号</label></td><td><input type='text' id='playernum' readonly style='background-color:#CCCCCC' value="<%=playernum %>"></input></td></tr>
       <tr><td><label>运动员姓名</label></td><td><input type='text' id='playername' readonly style='background-color:#CCCCCC' value="<%=playername %>"></input></td></tr>
       <tr><td><label>运动员性别</label></td><td><input type='text' id='playersex' readonly style='background-color:#CCCCCC' value="<%=playersex %>"></input></td></tr>
       <tr><td><label>分数</label></td><td><input type='text' id='score' value="<%=score %>"></input></td></tr>
       <tr><td><label>运动员是否违纪</label></td><td><select id="foul"><option value="<%=foul %>">
       <% if(foul.equals("0")){ %><%="没有违纪"%><% }else{%> <%="违纪"%><%}%> </option>
       <% if(foul.equals("0")){ %><option value="1"><%="违纪"%><% }else{%> 
       <option value="0"><%="没有违纪"%><%}%>
       </option></select></td></tr>
       <tr><td><label>运动员是否破纪录</label></td><td><select id="record">
       <option value="<%=recordlevel %>"><% if(recordlevel.equals("0")){ %><%="破院级记录" %></option><option value="1"><%="破省级记录"%></option><option value="2"><%="未破记录" %></option><% }else if(recordlevel.equals("1")){ %><%="破省级记录"%></option><option value="0"><%="破院级记录"%></option><option value="2"><%="未破记录" %></option><% }else{ %><%="未破记录" %></option><option value="0"><%="破院级记录"%></option><option value="1"><%="破省记录" %></option><% } %>
       </select></td></tr>
       <tr><td><label>所属部门</label></td><td><input type='text' id='departname' style='background-color:#CCCCCC' value="<%=departname %>"></input></td></tr>
       <tr><td cellspan="2"><input type='button' value='更改记录' onclick='updateMatch("<%=playernum %>","<%=matchid%>")'/><input type='reset' value='重置文本' onclick='resetText()'></td></tr>
       </table>
  </body>
</html>
