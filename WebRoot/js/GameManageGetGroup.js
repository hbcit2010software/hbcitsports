$(function(){
	$("#printScan").attr("disabled",true);
	$.ajax( {
		url : "servlet/GameManageCheckTableGetGroupServlet",
		type : 'get',
		contentType : "application/json;charset=utf-8",
		dataType : 'json',
		data : null,
		success : function(json) {
			var inhtml = "";
		        inhtml+="<option value=0>--请选择--</option>";
			for (i = 0; i < json.contents.length; i++) {

				inhtml += "<option value="+json.contents[i].groupid+">" + json.contents[i].groupname + "</option>";

			}
			$('#group').html(inhtml);
		}
		
	});
});