<%@ page language="java" import="java.util.*,cn.edu.hbcit.smms.pojo.PlayerNum,cn.edu.hbcit.smms.pojo.Item" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<%
	int num = ((Integer)session.getAttribute("num")).intValue();
	int perMan = Integer.parseInt(session.getAttribute("perMan").toString());
	int perDepartment = Integer.parseInt(session.getAttribute("perDepartment").toString());	
%>		
<%
	ArrayList list = (ArrayList)session.getAttribute("playernum");
   	PlayerNum p = null;
   	int begin = 0;
   	int end = 0;
   	int namesum = ((Integer)session.getAttribute("namesum")).intValue();
   		if(!list.isEmpty()){
   		p = (PlayerNum)list.get(0);
   		begin = Integer.parseInt(p.getBeginnum())+namesum;
   		end = Integer.parseInt(p.getEndnum());	
   	}
   	
%>


<script type="text/javascript">
//隔行变色
$(document).ready(function(){
	
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		});
//判断每项男女限报6人用的全局变量
<%
	ArrayList itemlist =(ArrayList)session.getAttribute("mylist");
	ArrayList playerNumList =(ArrayList)request.getAttribute("playerNumList");
	int[] itemid=new int[itemlist.size()];
	String[] itemtype = new String[itemlist.size()];
	out.print("var itemSix = new Array();");
	out.print("var playerNum = new Array();");
	for(int j = 0;j<itemlist.size();j++ ){
		Item item = (Item)itemlist.get(j);
		itemid[j]=item.getItemid();
		itemtype[j]= item.getItemtype();
		out.print("itemSix["+j+"] = "+ item.getItemid()+";");
		
	}
	for(int j = 0;j<playerNumList.size();j++ ){
		PlayerNum pn = (PlayerNum)playerNumList.get(j);
		out.print("playerNum["+j+"] = "+ pn.getPlayerNum()+";");
	}
	//System.out.println(itemlist);
%> 

var sample = "sample";
var sum = 0;
var cover = "cover";
var add = 0;
var number = 0;
//number = <%=begin%>;
var itemids = new Array(<%for(int i=0;i < itemid.length;i++){out.print(itemid[i]);if(i!=itemid.length-1)out.print(","); }   %>);
var itemtypes = new Array(<%for(int i=0;i<itemtype.length;i++){out.print(itemtype[i]);if(i!=itemtype.length-1)out.print(","); }   %>);
function checkItem(obj){
	var thisname = obj.name;
	//alert(thisname);
	var arr = document.getElementsByName(thisname);
	var count = 0;
	for(var i=0; i<arr.length; i++){
		var temp;
		temp = arr[i].value.split("#");
		if( arr[i].checked == true && temp[1] != "3"){
			count++;
		}
	}
	if(count > <%=perMan%>){
		Dialog.alert("除接力比赛外，每人限报2项！");
		obj.checked = false;
	}
}
function addRow(obj)
        {
        if(number > playerNum.length){
        Dialog.alert("您的号码簿有限，请重新分配！ ");
        return false;
        }
       var stuentApply = document.getElementById("stuentApply");
        //添加一行 
        sample = sample + sum;
        cover = cover + add;     
        var newTr = stuentApply.insertRow(3+add);
        //newTr.style.height=30;
        //alert(newTr.style.height);
        //添加两列
        var newTd0 = newTr.insertCell(0);
        var newTd1 = newTr.insertCell(1);
		var newTd2 = newTr.insertCell(2);
		//newTd0.style.height=30;
        //设置列内容和属性
        newTd0.innerHTML = '<td><div align="center"><lable><input type="hidden" name="hide" id="'+cover+'"><input type=text size="4" readonly="true" id="num_'+sample+'" name="num_'+sample+'"  value='+playerNum[number]+'></lable></div></td>'; 
		newTd1.innerHTML = '<td><div align="center"><input type="text" size="6" name="name_'+sample+'" id="name_'+sample+'"></div></td>'; 
		newTd2.innerHTML = '<td align="center" valign="middle"><div align="center">'+
		'<input type="radio" name="sex_'+sample+'" value="true" checked="checked">男&nbsp;'+
		'<input type="radio" name="sex_'+sample+'" value="false">女</div></td>';
         var i = 3;
         for(i = 3;i <= <%=num+2%>; i++){
        	newTr.insertCell(i).innerHTML= '<td><div align="center"><input onchange="checkItem(this);" type="checkbox" name="'+sample+'" value="'+itemids[i-3]+'#'+itemtypes[i-3]+'"></div></td>';
         }
         sum=sum + 1;
         add=add+1;
         number = number+1;
         if(number <= playerNum.length){
         	return number;
         }else{  	
         	Dialog.alert("您的号码簿有限，请重新分配！ ");
         }
    }

function test(){
	var myobj=document.getElementsByName("hide");
	var cover = "cover";
	var add = 0;
	var sample = "sample";
	var sum = 0;
	//if(myobj.length==0){ Dialog.alert("您未添加运动员所报项目"); return false;}
	for(var i = 0;i < myobj.length; i++){
		cover = cover + add;
		sample = sample + sum;
		var num = document.getElementById("num_"+sample).value;
		var name = document.getElementById("name_"+sample).value;
		var sex=document.getElementsByName("sex_"+sample);
		var sex_str = true;
		for(var k=0;k<sex.length;k++){
			if(sex[k].checked){
				sex_str = sex[k].value;
			}
		}
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
		var str;
		if(name != "" && allitem == ""){
			//Dialog.alert(name+"的项目未选，请选择后再提交！ ");
			str = num+","+""+","+sex_str+","+allitem;
		}else{
			str = num+","+name+","+sex_str+","+allitem;
		}
		//var str = num+","+name+","+sex_str+","+allitem;
		document.getElementById(cover).value=str;
		//alert(document.getElementById(cover).value);
		str = "";
		add+=1;
		sum+=1;
	}
}
function submitCheck(){
//判断每项限报6人

	var countMan = 0;
	var countWoman = 0;
	var myobj=document.getElementsByName("hide");
	if(myobj.length==0){ Dialog.alert("您未点击添加！"); return false;}
	var itemNoPlus = new Array();
	var itemCountMan = new Array();
	var itemCountWoman = new Array();
	for(var i=0;i<itemSix.length;i++){
		itemCountMan[i]=0;
		itemCountWoman[i]=0;
	}
	for(var i = 0; i<myobj.length; i++){
		var arrSex = myobj[i].value.split(",");	//{"20031","张三","true","2+3;4+5"}
		var itemId = arrSex[3].split(";"); //{"2+3","4+5"}
		for(var p=0; p<itemId.length; p++){
			var tempStr = itemId[p].split("#"); //{"2","3"}
			itemNoPlus[p] = tempStr[0]; //{"2","4"}
		}
		
		for(var j=0 ;j<itemSix.length; j++){
			for(var k=0; k<itemNoPlus.length; k++){
				//alert(k +"==arrSex[2]:"+arrSex[2]+"--itemNoPlus[k]: "+itemNoPlus[k] + "--itemSix[j]:"+itemSix[j]);
				if(arrSex[2] == "true" && itemNoPlus[k]==itemSix[j]){
					itemCountMan[j]++;
				}else if(arrSex[2] == "false" && itemNoPlus[k]==itemSix[j]){
					itemCountWoman[j]++;
				}
			}
		}
	}
	for(var i = 0;i<itemCountWoman.length;i++){
		//alert("第"+i+1+"项=itemCountMan[i]:"+itemCountMan[i]+"--itemCountWoman[i]:"+itemCountWoman[i]);
		if(itemCountMan[i] > <%=perDepartment%>){
			Dialog.alert("男子组中有一些项目报名人数超过6人，请检查！");
			return false;
		}
		if(itemCountWoman[i] > <%=perDepartment%>){
			Dialog.alert("女子组中有一些项目报名人数超过6人，请检查！");
			return false;
		}
	}
	
}
//报名注意事项
function selGroup(){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 400;
			diag.Height = 200;
			diag.Title = "报名注意事项";
			diag.URL = "${pageContext.request.contextPath }/servlet/GetListServlet";
			//diag.OKEvent = function(){
				//window.location.reload();
				//diag.close();
			//};
			diag.ShowCloseButton=true;
			//diag.MessageTitle = "添加运动会提示：";
			//diag.Message = "填完各项内容后不要忘记先\"确认添加\"，然后才可关闭窗口";
			diag.show();
			//diag.okButton.value="确定";
			diag.cancelButton.value="关闭";
	}
</SCRIPT>


<style type="text/css">
<!--
.STYLE1 {font-size: 36px}
-->
</style>
</head>
  <form action="${pageContext.request.contextPath }/servlet/UpdatePlayerServlet" method="post" onsubmit="return submitCheck();">
    <table id="stuentApply" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
      <tr class="tableTitle">
        <td height="20" colspan="17" ><div align="center">
          <h1>学生组报名页面  </h1>
        </div>
        <div align="right">
        <a href="#" style="color:#ff0000" onclick="selGroup();">报名注意事项</a>
        </div>
        </td>
      </tr>  
      <tr><td valign="middle" height="20" colspan="17"><div align="center"><input type="button" name="button" id="button"  style="width:80px;height:30px;" value="添   加" onclick="addRow();"></div></td></tr>
    <tr id="tabletitle">
      <td width="4%"><p align="center">号码<br></p> </td>
      <td width="4%"><p align="center">姓名</p>      </td>
      <td width="5%"><p align="center">性&nbsp;&nbsp;&nbsp;&nbsp;别</p></td>
      <c:forEach items="${mylist}" var="user">
	   	<td width="4%" class="a"><p align="center">${user.itemname}</p></td>
      </c:forEach>
    </tr>
    </table>  
  <center><input type="submit" name="button" id="button"  style="width:80px;height:30px;" value="提   交" onclick="test()"></center>
</form>
</body>                                                                                                                                                                                          
</html>
