$(function (){//下载文档方法
	$("#printScan").click(function(){
		var groupname =$("#group").find("option:selected").text();
		var finalitemname = $("#item").find("option:selected").text();
		var finalitemtype = $("#item").find("option:selected").val();
		if(groupname == "0"){
			alert("请选择组别");
			
		}else if(finalitemname == "0"){
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
				alert("生成成功，下载请点击此链接");
				
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