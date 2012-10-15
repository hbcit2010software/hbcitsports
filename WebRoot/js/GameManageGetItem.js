$(function(){
	$("#item").change(function(){
		$("#printScan").attr("disabled",true);
	});
	$("#group").change(function(){
		$("#printScan").attr("disabled",true);
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
});
	

	
});