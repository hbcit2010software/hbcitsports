<%@ page language="java" import="java.util.*,cn.edu.hbcit.smms.pojo.PlayerNum,cn.edu.hbcit.smms.pojo.Item" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript"><!--
//隔行变色
$(document).ready(function(){
	
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		});
<%
	int num = Integer.parseInt(session.getAttribute("num").toString());
%>		
<%
	ArrayList list = (ArrayList)session.getAttribute("playernum");
   	PlayerNum p = null;
   	int begin = 0;
   	int end = 0;	
   	if(!list.isEmpty()){
   		p = (PlayerNum)list.get(0);
   		begin = Integer.parseInt(p.getBeginnum());
   		end = Integer.parseInt(p.getEndnum());	
   	}
%>
<%
	ArrayList itemlist =(ArrayList)session.getAttribute("mylist");
	
	int[] itemid=new int[itemlist.size()];
	for(int j = 0;j<itemlist.size();j++ ){
		Item item = (Item)itemlist.get(j);
		itemid[j]=item.getItemid();
		System.out.println(itemid[j]);
	}
%>

var sample = "sample";
var sum = 0;
var cover = "cover";
var add = 0;
var number = 0;
number = <%=begin%>;
var itemids = new Array(<%for(int i=0;i<itemid.length;i++){System.out.println(itemid[i]); out.print(itemid[i]);if(i!=itemid.length-1)out.print(","); }   %>);
function addRow(obj)
        {
        if(number><%=end%>){
        alert("您的号码布有限，请重新分配！ ");
        return false;
        }
        
       var stuentApply = document.getElementById("stuentApply");
        //添加一行 
        sample = sample + sum;
        cover = cover + add;      
        var newTr = stuentApply.insertRow(3+add);
        //添加两列
        var newTd0 = newTr.insertCell(0);
        var newTd1 = newTr.insertCell(1);
		var newTd2 = newTr.insertCell(2);
        //设置列内容和属性
        newTd0.innerHTML = '<td><lable><div align="center"><input type="hidden" name="hide" id="'+cover+'"><input type=text size="4" readonly="true" id="num_'+sample+'" name="num_'+sample+'" value='+number+'></div></lable></td>'; 
		newTd1.innerHTML = '<td><div align="center"><input type=text size="6" id="name_'+sample+'" name="name_'+sample+'"></div></td>'; 
        newTd2.innerHTML = '<td align="center" valign="middle"><div align="center"><label><select name="select_'+sample+'" id="select_'+sample+'">'+
        '<option>——请选择——</option>'+
        <c:forEach items="${sessionScope.grouplist}" var="groupname">
        '<option value="'+${groupname.id }+'">'+'${ groupname.groupname }'+'</option>'+
        </c:forEach>
        '</select></label></div></td>';
         var i = 3;
         for(i = 3;i <= <%=num+2%>;i++){
        	newTr.insertCell(i).innerHTML= '<td><div align="center"><input type="checkbox" name="'+sample+'" value="'+itemids[i-3]+'"></div></td>';
			//alert('<td><div align="center"><input type="checkbox" name="'+sample+'" value="'+itemids[i-3]+'"><input type="hidden" name="hide" id='+cover+'></div></td>');
         } 
         sum=sum+1;
         add=add+1;
         number = number+1;
      if(number<=<%=end%>){
         	return number;
         }else{
         	
         	alert("您的号码布有限，请重新分配！ ");
         }   
        }


function test(){
	
	var myobj=document.getElementsByName("hide");
	var sample = "sample";
	var sum = 0;
	var cover = "cover";
	var add = 0;
	for(var i = 0;i < myobj.length; i++){
		cover = cover + add;
		sample = sample + sum;
		var num = document.getElementById("num_"+sample).value;
		var name = document.getElementById("name_"+sample).value;
		var group=document.getElementById("select_"+sample).value;
		var itemid = document.getElementsByName(sample);
		var allitem = "";
		var checkednum = 0;
		for(var j=0;j < itemid.length; j++){
			if(itemid[j].checked){
				checkednum++;
			}
		}
		for(var j = 0,jj=0;j < itemid.length; j++){
			if(itemid[j].checked){
				allitem += itemid[j].value;
				if(jj != checkednum - 1){
					allitem += ";";
				}
				jj++;
			}
		}
		var str = num+","+name+","+group+","+allitem;
		document.getElementById(cover).value=str;
		str = "";
		add+=1;
		sum+=1;
		}
}
</SCRIPT>



<style type="text/css">
<!--
.STYLE1 {font-size: 36px}
-->
</style>
</head>
  
  <body>
 
   <form  action="servlet/UpdatePlayerTeacherServlet" method="post">
  
    <table id="stuentApply" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
      <tr class="tableTitle">
        <td height="20" colspan="17" ><div align="center">
          <h1>教工组报名页面  </h1>
        </div></td>
      </tr>
      
      <tr><td valign="middle" height="20" colspan="17"><div align="center"><input type="button" name="button" id="button"  style="width:80px;height:30px;" value="添   加" onclick="addRow();"></div></td></tr>
    <tr id="tabletitle">
      <td width="4%"><p align="center">号码<br></p> </td>
      <td width="4%"><p align="center">姓名</p>      </td>
      <td width="5%"><p align="center">组别</p></td>
      <c:forEach items="${mylist}" var="user">
	   	<td width="4%" class="a"><p align="center">${user.itemname}</p></td>
      </c:forEach>
    </tr>
    </table>
    
  <center><input type="submit" name="button" id="button"  style="width:80px;height:30px;" value="提   交" onclick="test()"></center>
</form>
</body>
</html>
