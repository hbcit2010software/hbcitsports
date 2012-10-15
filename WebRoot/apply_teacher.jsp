<%@ page language="java" import="java.util.*,cn.edu.hbcit.smms.pojo.PlayerNum,cn.edu.hbcit.smms.pojo.Item,cn.edu.hbcit.smms.pojo.Player" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/subcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<script type="text/javascript">
//隔行变色
$(document).ready(function(){
	
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		});
<%
	int num = Integer.parseInt(session.getAttribute("num").toString());
	int perMan = Integer.parseInt(session.getAttribute("perMan").toString());
	int perDepartment = Integer.parseInt(session.getAttribute("perDepartment").toString());
	//System.out.println("perMan:"+perMan+"perDepartment:"+perDepartment);
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
//判断每项男女限报6人用的全局变量
<%
	ArrayList itemlist =(ArrayList)session.getAttribute("mylist");
	ArrayList grouplist =(ArrayList)session.getAttribute("grouplist");
	ArrayList playerNumList =(ArrayList)request.getAttribute("playerNumList");
	int[] itemid=new int[itemlist.size()];
	String[] itemtype = new String[itemlist.size()];
	out.println("var itemSix = new Array();");
	out.print("var playerNum = new Array();");
	for(int j = 0;j<itemlist.size();j++ ){
		Item item = (Item)itemlist.get(j);
		itemid[j]=item.getItemid();
		itemtype[j]= item.getItemtype();
		out.println("itemSix["+j+"] = "+ item.getItemid()+";");
		
	}
	for(int j = 0;j<playerNumList.size();j++ ){
		PlayerNum pn = (PlayerNum)playerNumList.get(j);
		out.print("playerNum["+j+"] = "+ pn.getPlayerNum()+";");
	}
	out.println("var groupId = new Array();");
	for(int j = 0;j<grouplist.size();j++ ){
		Player group = (Player)grouplist.get(j);
		out.println("groupId["+j+"] = "+ group.getId()+";");
		
	}
	out.println("var groupName = new Array();");
	for(int j = 0;j<grouplist.size();j++ ){
		Player group = (Player)grouplist.get(j);
		out.println("groupName["+j+"] = \""+ group.getGroupname()+"\";");
		
	}
	
%>


var sample = "sample";
var sum = 0;
var cover = "cover";
var add = 0;
var number = 0;
//number = <%=begin%>;
var itemids = new Array(<%for(int i=0;i<itemid.length;i++){System.out.println(itemid[i]); out.print(itemid[i]);if(i!=itemid.length-1)out.print(","); }   %>);
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
	if(count ><%=perMan%>){
		Dialog.alert("除接力比赛外，每人限报<%=perMan%>项！");
		obj.checked = false;
	}
	//alert(count);
}
//

//
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
        //添加两列
        var newTd0 = newTr.insertCell(0);
        var newTd1 = newTr.insertCell(1);
		var newTd2 = newTr.insertCell(2);
        //设置列内容和属性
        newTd0.innerHTML = '<td><lable><div align="center"><input type="hidden" name="hide" id="'+cover+'"><input type=text size="4" readonly="true" id="num_'+sample+'" name="num_'+sample+'" value='+playerNum[number]+'></div></lable></td>'; 
		newTd1.innerHTML = '<td><div align="center"><input type=text size="6" id="name_'+sample+'" name="name_'+sample+'"></div></td>'; 
        newTd2.innerHTML = '<td align="center" valign="middle"><div align="center"><label><select name="select_'+sample+'" id="select_'+sample+'">'+
        //'<option>——请选择——</option>'+
        <c:forEach items="${sessionScope.grouplist}" var="groupname">
        '<option value="'+${groupname.id }+'">'+'${ groupname.groupname }'+'</option>'+
        </c:forEach>
        '</select></label></div></td>';
         var i = 3;
         for(i = 3;i <= <%=num+2%>;i++){
        	newTr.insertCell(i).innerHTML= '<td><div align="center"><input onchange="checkItem(this);" type="checkbox" name="'+sample+'" value="'+itemids[i-3]+'#'+itemtypes[i-3]+'"></div></td>';
			//alert('<td><div align="center"><input type="checkbox" name="'+sample+'" value="'+itemids[i-3]+'"><input type="hidden" name="hide" id='+cover+'></div></td>');
         } 
         sum=sum+1;
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
		var str;
		if(name != "" && allitem == ""){
			//Dialog.alert(name+"的项目未选，请选择后再提交！ ");
			str = num+","+""+","+group+","+allitem;
		}else{
			str = num+","+name+","+group+","+allitem;
		}
		//var str = num+","+name+","+group+","+allitem;
		
		document.getElementById(cover).value=str;
		str = "";
		add+=1;
		sum+=1;
		}
		
}
function submitCheck(){
//判断每项限报6人
	var countOldMan = 0;
	var countOldWoman = 0;
	var countYoungMan = 0;
	var countYoungWoman = 0;
	var myobj=document.getElementsByName("hide");
	if(myobj.length==0){ Dialog.alert("您未点击添加！"); return false;}
	var itemNoPlus = new Array();
	var itemCountOldMan = new Array();
	var itemCountOldWoman = new Array();
	var itemCountYoungMan = new Array();
	var itemCountYoungWoman = new Array();
	for(var i=0;i<itemSix.length;i++){
		itemCountOldMan[i]=0;
		itemCountOldWoman[i]=0;
		itemCountYoungMan[i]=0;
		itemCountYoungWoman[i]=0;
	}
	for(var i = 0; i<myobj.length; i++){
		var arrGroup = myobj[i].value.split(",");	//{"20031","张三","教工青年男子组","2+3;4+5"}
		var itemId = arrGroup[3].split(";"); //{"2+3","4+5"}
		for(var p=0; p<itemId.length; p++){
			var tempStr = itemId[p].split("#"); //{"2","3"}
			itemNoPlus[p] = tempStr[0]; //{"2","4"}
		}
		
			for(var j=0 ;j<itemSix.length; j++){
				for(var k=0; k<itemNoPlus.length; k++){
					//alert(k +"==arrSex[2]:"+arrSex[2]+"--itemNoPlus[k]: "+itemNoPlus[k] + "--itemSix[j]:"+itemSix[j]);
					if(arrGroup[2] == groupId[0] && itemNoPlus[k]==itemSix[j] && (arrGroup[1]!="" && arrGroup[3]!="")){
						itemCountOldMan[j]++;
					}else if(arrGroup[2] == groupId[1] && itemNoPlus[k]==itemSix[j] && (arrGroup[1]!="" && arrGroup[3]!="")){
						itemCountOldWoman[j]++;
					}else if(arrGroup[2] == groupId[2] && itemNoPlus[k]==itemSix[j] && (arrGroup[1]!="" && arrGroup[3]!="")){
						itemCountYoungMan[j]++;
					}else if(arrGroup[2] == groupId[3] && itemNoPlus[k]==itemSix[j] && (arrGroup[1]!="" && arrGroup[3]!="")){
						itemCountYoungWoman[j]++;
					}
				}
			}
	}
	for(var i = 0;i<itemCountOldWoman.length;i++){
		//alert("第"+i+1+"项=itemCountMan[i]:"+itemCountMan[i]+"--itemCountWoman[i]:"+itemCountWoman[i]);
		if(itemCountOldMan[i] > <%=perDepartment%>){
			Dialog.alert(groupName[0]+"中有一些项目报名人数超过<%=perDepartment%>人，请检查！");
			return false;
		}
		if(itemCountOldWoman[i] > <%=perDepartment %>){
			Dialog.alert(groupName[1]+"中有一些项目报名人数超过<%=perDepartment%>人，请检查！");
			return false;
		}
		if(itemCountYoungMan[i] > <%=perDepartment%>){
			Dialog.alert(groupName[2]+"中有一些项目报名人数超过<%=perDepartment%>人，请检查！");
			return false;
		}
		if(itemCountYoungWoman[i] > <%=perDepartment%>){
			Dialog.alert(groupName[3]+"中有一些项目报名人数超过<%=perDepartment%>人，请检查！");
			return false;
		}
	}
	
}
//报名注意事项
function selGroup(){
		var diag = new Dialog();
			diag.Top =20;
			diag.Width = 600;
			diag.Height = 400;
			diag.Title = "报名注意事项";
			diag.URL = "${pageContext.request.contextPath }/servlet/GetListServlet";
			//diag.OKEvent = function(){
			//	window.location.reload();
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
  
  <body>
 
   <form  action="${pageContext.request.contextPath }/servlet/UpdatePlayerTeacherServlet" method="post" onSubmit="return submitCheck();">
  
    <table id="stuentApply" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb">
      <tr class="tableTitle">
        <td height="20" colspan="17" ><div align="center">
          <h1 style="margin-bottom:0px;">教工组报名页面  </h1>
        </div>
        <div align="right">
        <a href="#" style="color:#ff0000" onClick="selGroup();">报名注意事项</a>
        </div>
        </td>
      </tr>
      
      <tr><td valign="middle" height="20" colspan="17"><div align="center"><input type="button" name="button" id="button"  style="width:80px;height:30px;" value="添   加" onClick="addRow()"></div></td></tr>
    <tr id="tabletitle" style="font-size:12px; font-weight:bold;">
      <td width="4%"><p align="center">号码<br></p> </td>
      <td width="4%"><p align="center">姓名</p>      </td>
      <td width="5%"><p align="center">组别</p></td>
      <c:forEach items="${mylist}" var="user">
	   	<td width="4%" class="a"><p align="center">${user.itemname}</p></td>
      </c:forEach>
    </tr>
    </table>
    
  <center><input type="submit" name="button" id="button"  style="width:80px;height:30px;" value="提   交" onClick="test()"></center>
</form>
</body>
</html>
