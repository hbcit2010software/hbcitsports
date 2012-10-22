<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>生成检录表</title>
    <link href="css/subcss.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="js/zDialog_inner.js"></script>
    <script type="text/javascript" src="js/zDrag.js"></script>
     <script type="text/javascript" src="js/GameManageGetGroup.js"></script>
     <script type="text/javascript" src="js/GameManageGetItem.js"></script>
      <!--<script type="text/javascript" src="js/GameManagePrintScan.js" CharSet="utf-8"></script>-->
     <script type="text/javascript" language="javascript"> 
	$(function(){//页面加载后执行
	   
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
    });
		    $("#printScan").attr("disabled",true);
		    
    function checkDate(){//判断预赛束缚有成绩
			    var finalitemname = $("#item").find("option:selected").text();
				var finalitemtype = $("#item").find("option:selected").val();
				var groupvalue =$("#group").find("option:selected").val();
			    var groupname =$("#group").find("option:selected").text();
	if(groupvalue=="0"){
				 Dialog.alert("请选择组别");
				  }else if(finalitemtype=="0"){
				  Dialog.alert("请选择项目");
				  }else{
		if(finalitemtype==1 || finalitemtype==3){
				scan1();
				}
				else{
			      $.ajax({
						url: "servlet/GameManageCheckTableGetItemServlet?action=checkDate",
						type: "get",
						data: {itemname:finalitemname,itemtype:finalitemtype},
						success : function(a) {
							if( a == 0 ){
								//$("#scan2").attr("disabled",true);
								Dialog.alert("预赛还未完成!");return false;
							}else{
							    scan1();
							}
						}
		});	
		}
			  }}
			  
		function scan1(){//预览
            var groupvalue =$("#group").find("option:selected").val();
			var groupname =$("#group").find("option:selected").text();
			var finalitemname = $("#item").find("option:selected").text();
			var finalitemtype = $("#item").find("option:selected").val();
			 if(groupvalue=="0"){
				 Dialog.alert("请选择组别");
				  }else if(finalitemtype=="0"){
				  Dialog.alert("请选择项目");
				  }else{
				    $("#printScan").attr("disabled",false);
			$.ajax( {
				url : "${pageContext.request.contextPath}/servlet/GameManageCheckTableGetScanServlet",
				type : 'get',
				contentType : "application/json;charset=utf-8",
				dataType : 'json',
				data : {itemname:finalitemname,itemtype:finalitemtype},
				success : function(json) {
				  
					$("#gpit").text(groupname+"-->"+finalitemname);
					var inhtml = "";
					//if(finalitemtype == '2'||finalitemtype == '1'){}
					for(i = 0; i < json.length; i++){
					
							var json1 = json[i];
							inhtml += "<tr class='tableTitle'><td colspan='5' width='10%' height='20' >第"+(i+1)+"小组</td></tr>";
							for( j = 0 ; j < json1.length ; j++ ){
								var json2 = json1[j];
								inhtml += "<tr class='tableTitle'><td  width='10%' height='20' align='center'>"+json1[j][0]+"</td><td  width='15%' height='20' align='center'>"+json1[j][1]+"</td><td  width='14%' height='20' align='center'>"+json1[j][2]+"</td><td  width='16%' height='20' align='center'></td><td  width='27%' height='20' align='center'></td></tr>";
							}
					}
					/*
					else{
					for(i = 0; i < json.length; i++){
					
							var json1 = json[i];
							for( j = 0 ; j < json1.length ; j++ ){
								var json2 = json1[j];
								inhtml += "<tr class='tableTitle'><td  width='10%' height='20' align='center'>"+json1[j][0]+"</td><td  width='15%' height='20' align='center'>"+json1[j][1]+"</td><td  width='14%' height='20' align='center'>"+json1[j][2]+"</td><td  width='16%' height='20' align='center'></td><td  width='27%' height='20' align='center'></td></tr>";
							}
					}
					}*/
					$('#content').html(inhtml);					
				},
				error : function(xhr, status, errorThrown) {
					alert("errorThrown=" + errorThrown);
				}
			});
			}
		}
		
		function getItem(){//得到项目名称
			var groupid= $("#group").find("option:selected").val();
			$.ajax({
			url:"servlet/GameManageCheckTableGetItemServlet",
			type:"get",
			data:"groupid="+groupid,
			contentType:"application/json;charset=utf-8",
			dataType:"json",
			success : function(json) {
				var inhtml = "<option value=0>--请选择项目--</option>";
				for (i = 0; i < json.contents.length; i++) {
	
					inhtml += "<option value='"+json.contents[i].itemtype+"'>" + json.contents[i].itemname + "</option>";
				}
				$('#item').html(inhtml);
	
			}
		});	
		}
			
$(function (){//下载文档方法
	$("#printScan").click(function(){
		var groupname =$("#group").find("option:selected").text();
		var groupvalue =$("#group").find("option:selected").val();
		var finalitemname = $("#item").find("option:selected").text();
		var finalitemtype = $("#item").find("option:selected").val();
		if(groupvalue == "0"){
			Dialog.alert("请选择组别");
			
		}else if(finalitemtype == "0"){
			Dialog.alert("请选择项目");
		}else{
		$.ajax( {
			url :"servlet/GameManageCheckTablePrintScanServlet",
			type : 'get',
			contentType : "application/json;charset=utf-8",
			dataType : 'json',
			data : {finalitemname:finalitemname,finalitemtype:finalitemtype,groupname:groupname},
			success : function(json) {
				if(json==1){
				if(groupname+finalitemname+finalitemtype==$("#href a").text()){
					Dialog.alert("已生成此链接，请点击下面的连接可以下载文档!!!!!");
					
				}
				else{
					Dialog.alert("生成成功，下载请点击此链接");
			var a =	$("<a  href='downloads.jsp?nihao="+groupname+finalitemname+"'>"+groupname+finalitemname+finalitemtype+"</a><br />");
				$("#href").append(a);
				}
				}else
				{
					Dialog.alert("这个文档还未生成，不能下载！！！！！！");
					
				}
				
			},
			error : function(xhr, status, errorThrown) {
				alert("errorThrown=" + errorThrown);
				
			}
		});
		}
	
	});
	
});
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
                <td width="94%" valign="bottom"><span class="pageTitle">赛中管理-->生成检录表</span></td>
              </tr>
            </table>
             </td>
            <td>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr><td height="5px"></td></tr>
  <tr><td>
  <div>&nbsp;&nbsp;&nbsp;&nbsp;请选择条件&nbsp;&nbsp;&nbsp;&nbsp;组别
  <select id='group' onchange="getItem();">
  <option value='0'> --请选择--</option>
  </select>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  项目<select id='item' style="width:180px" >
  <option value='0'> --请选择项目--</option>
  </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="预览 " id="scan2" onclick="checkDate()" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="打印预览"  id="printScan" />
  </div>
  </td></tr>
 <tr><td height="10px"></td></tr>
  <tr>
    <td>
    <!--内嵌表格begin-->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb"  id="table">
	    <tr class="tableTitle" >       
	       <td width="100%" height="20" colspan='5' ><div align="center" id="gpit"></div></td> 
	      </tr>
	      <tr class="tableTitle">       
	        <td width="10%" height="20" ><div align="center"><span>号码</span></div></td>
	        <td width="15%" height="20" ><div align="center"><span>姓名</span></div></td>
	        <td width="14%" height="20" ><div align="center"><span>道次/出场顺序</span></div></td>
	        <td width="16%" height="20" ><div align="center"><span>成绩</span></div></td>
	        <td width="27%" height="20" ><div align="center"><span>备注</span></div></td>
	      </tr> 
	       <tr class="tableTitle">       
	         <table width="100%" border="0" cellpadding="0" cellspacing="1"  class="stripe_tb"  id="content" > 
     
             </table>
	      </tr> 
      </table>
   
     <!--内嵌表格end-->
     <p>
     <%
      String a=(String)session.getAttribute("path"); 
      %>
     <div id="href"></div>
    </td>
  </tr>
</table>
<br />
</body>
</html>