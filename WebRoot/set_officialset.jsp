<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script  type="text/javascript" src="js/jquery-1.6.min.js"></script>
<title>大会主席团及工作人员</title>
<script type="text/javascript">
function alterpws()
		{
		    
			var presidium=$('#presidium').val();
			var org_committee_1=$('#org_committee_1').val();
			var org_committee_2=$('#org_committee_2').val();
			var org_committee_3=$('#org_committee_3').val();
			var  secretariat_1=$('#secretariat_1').val();
			var  secretariat_2=$('#secretariat_2').val();
			var  secretariat_3=$('#secretariat_3').val();
			var  secretariat_4=$('#secretariat_4').val();
			var  secretariat_5=$('#secretariat_5').val();
			var  secretariat_6=$('#secretariat_6').val();
			var  secretariat_7=$('#secretariat_7').val();
			var  arbitration=$('#arbitration').val();
			
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/AddOfficialSetServlet",
					type : 'post',
				   	data:{presidium:presidium,org_committee_1:org_committee_1,org_committee_2:org_committee_2,
					org_committee_3:org_committee_3,secretariat_1:secretariat_1,secretariat_2:secretariat_2,
					secretariat_3:secretariat_3,secretariat_4:secretariat_4,secretariat_5:secretariat_5,
					secretariat_6:secretariat_6,secretariat_7:secretariat_7,arbitration:arbitration},
					
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								alert("添加成功!",function(){window.location.reload();});
							}
							else
								alert("添加失败!");
						}
					});	
		}
</script>
<style type="text/css">
<!--
.STYLE3 {
	font-size: 12px
}
-->
</style>
<style>
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.pageTitle {
	color: #e1e2e3;
	font-size: 12px;
}
-->
#table1{
margin-left:10%;margin-top:30px}
#table2{
margin-right:10%;margin-top:30px}
#table3{
margin-left:10%; margin-top:80px}
#table4{
margin-right:10%; margin-top:80px}
#table5{
margin-left:10%;margin-top:0px}
#submit{
margin-top:640px}
body{
background-color: #FFFFFF}
.STYLE2 {
	color: #339900;
	font-weight: bold;
}
.STYLE4 {
	color: #339900;
	font-weight: bold;
	font-size: 12px;
}
</style>
</head>
<body>
<table width="100%" height="31" bgcolor="#353c44" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
         <table width="100%" height="19"  border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="6%" height="19" valign="bottom"><div align="center">/<img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->大会主席团及工作人员</span></td>
			</tr>
		</table> 
		</td>
	</tr>
</table>            
<p></p>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="240">
        <table width="500" " cellpadding="0" cellspacing="0" align="center"  id="table1"> 
	<tr> 
	<td ><FIELDSET style="BORDER-LEFT-COLOR: #FFCC33; BORDER-BOTTOM-COLOR:#FFCC33; BORDER-TOP-COLOR: #FFCC33; HEIGHT:160px; BORDER-RIGHT-COLOR:#FFCC33" align=center><LEGEND align=center class="STYLE2 STYLE3"><FONT style="FONT-FAMILY: 宋体">大会主席团</FONT></LEGEND>
  <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
 		 <tr >
    		<td width="200" height="160px"  ><div align="center" class="STYLE3">成员：</div></td>
      		<td width="300"> <textarea name="presidium" id="presidium" cols="45" rows="5"></textarea></td>
 	 </tr>
	</table></FIELDSET> 
	</td>
   </tr>
 </table>
		</td>
		<td height="240">
         <table width="500" cellpadding="0" cellspacing="0" align="center" id="table2"  >
	<tr> 
	<td><FIELDSET style="BORDER-LEFT-COLOR: #FFCC33; BORDER-BOTTOM-COLOR:#FFCC33; BORDER-TOP-COLOR: #FFCC33; HEIGHT: 160px; BORDER-RIGHT-COLOR:#FFCC33" align=center><LEGEND align=center class="STYLE4"><FONT style="FONT-FAMILY: 宋体">大会仲裁委员会</FONT></LEGEND>
    <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
 		 <tr >
    		<td width="200" height="160px"  ><div align="center" class="STYLE3">成员：</div></td>
      		<td width="300"> <textarea name="arbitration" id="arbitration" cols="45" rows="5"></textarea></td>
    	 </tr>
	</table></FIELDSET> 
	</td>
   </tr>
 </table> 
		</td>
	</tr>
    	<tr>
		<td height="280">
        
    <table width="500" cellpadding="0" cellspacing="0" align="center" id="table3"  >
	<tr> 
	<td><FIELDSET style="BORDER-LEFT-COLOR: #FFCC33; BORDER-BOTTOM-COLOR:#FFCC33; BORDER-TOP-COLOR: #FFCC33; HEIGHT: 240px; BORDER-RIGHT-COLOR:#FFCC33" align=center><LEGEND align=center class="STYLE4"><FONT style="FONT-FAMILY: 宋体">大会组委会</FONT></LEGEND>
    <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
 		 <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">主 任：</div></td>
      		<td width="300"> <input type="text" name="org_committee_1" id="org_committee_1"/></td>
    	 </tr>
         <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">副主任：</div></td>
      		<td width="300"><input type="text" name="org_committee_2" id="org_committee_2"/></td>
    	 </tr>
          <tr >
    		<td width="200" height="140px"  ><div align="center" class="STYLE3">委 员:</div></td>
      		<td width="300"> <textarea name="org_committee_3" id="org_committee_3" cols="45" rows="5"></textarea></td>
    	 </tr>
	</table></FIELDSET> 
	</td>
   </tr>
 </table> 
		</td>
		<td height="280">
        <table width="500" cellpadding="0" cellspacing="0" align="center" id="table4"  >
	<tr> 
	<td><FIELDSET style="BORDER-LEFT-COLOR: #FFCC33; BORDER-BOTTOM-COLOR:#FFCC33; BORDER-TOP-COLOR: #FFCC33; HEIGHT: 290px; BORDER-RIGHT-COLOR:#FFCC33" align=center><LEGEND align=center class="STYLE4"><FONT style="FONT-FAMILY: 宋体">大会秘书处</FONT></LEGEND>
   <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
 		 <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">秘 书 长：</div></td>
      		<td width="300"> <input type="text" name="secretariat_1" id="secretariat_1"/></td>
    	 </tr>
         <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">副秘书长：</div></td>
      		<td width="300"><input type="text" name="secretariat_2" id="secretariat_2"/></td>
    	 </tr>
           <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">会务组负责人：</div></td>
      		<td width="300"><input type="text" name="secretariat_3" id="secretariat_3"/></td>
    	 </tr>
           <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">宣传组负责人：</div></td>
      		<td width="300"><input type="text" name="secretariat_4" id="secretariat_4"/></td>
    	 </tr>
           <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">奖品组负责人：</div></td>
      		<td width="300"><input type="text" name="secretariat_5" id="secretariat_5"/></td>
    	 </tr>
           <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">保卫组负责人：</div></td>
      		<td width="300"><input type="text" name="secretariat_6" id="secretariat_6"/></td>
    	 </tr>
           <tr >
    		<td width="200" height="40px"  ><div align="center" class="STYLE3">后勤保障组负责人：</div></td>
      		<td width="300"><input type="text" name="secretariat_7" id="secretariat_7"/></td>
    	 </tr>
	</table></FIELDSET> 
	</td>
   </tr>
 </table>
		</td>
	</tr>
</table>
<p>&nbsp;</p>

    <p align="center">

    <input type="button" name="button" id="button" value="保存" onclick="alterpws()">
  <input type="button" value="修改" onclick="window.location.href='${pageContext.request.contextPath }/servlet/SelectAllOfficialBySportsidServlet?sportsid==sportsid}'">
  </p>
</body>
</html>
