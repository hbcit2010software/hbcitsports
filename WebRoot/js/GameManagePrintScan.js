$(function (){//下载文档方法
	$("#printScan").click(function(){
		var groupname =$("#group").find("option:selected").text();
		var groupvalue =$("#group").find("option:selected").val();
		var finalitemname = $("#item").find("option:selected").text();
		var finalitemtype = $("#item").find("option:selected").val();
		if(groupvalue == "0"){
			alert("请选择组别");
			
		}else if(finalitemtype == "0"){
			alert("请选择项目");
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
					alert("已生成此链接，请点击下面的连接可以下载文档!!!!!");
					
				}
				else{
					alert("生成成功，下载请点击此链接");
			var a =	$("<a  href='downloads.jsp?nihao="+groupname+finalitemname+"'>"+groupname+finalitemname+finalitemtype+"</a><br />");
				$("#href").append(a);
				}
				}else
				{
					alert("这个文档还未生成，不能下载！！！！！！");
					
				}
				
			},
			error : function(xhr, status, errorThrown) {
				alert("errorThrown=" + errorThrown);
				
			}
		});
		}
	
	});
	
});