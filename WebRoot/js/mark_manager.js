
/**
 * 显示部门下拉列表
 * @return
 */


function check(){		
    $.ajax( {
		url : "servlet/MarkManagerServlet",
		type : 'get',
		contentType : "application/json;charset=utf-8",
		dataType : 'json',
		data : null,
		success : function(json) {
			var inhtml = "";	
			inhtml+="<option value='-1'>--请选择--</option>";
			for (i = 0; i < json.contents.length; i++) {

				inhtml += "<option value='"+json.contents[i].Id+"'>" + json.contents[i].Department + "</option>";

			}
			$('#department').html(inhtml);
		}
		
	});
}

/**
 * 显示项目下拉列表
 * @return
 */
function checkItem(){	
    $.ajax( {
		url : "servlet/MarkItemServlet",
		type : 'get',
		contentType : "application/json;charset=utf-8",
		dataType : 'json',
		data : null,
		success : function(json) {
			var inhtml = "";	
			inhtml+="<option value='-1'>--请选择--</option>";
			for (i = 0; i < json.contents.length; i++) {

				inhtml += "<option value='"+json.contents[i].Id+"'>" + json.contents[i].ItemName + "</option>";

			}
			$('#item').html(inhtml);
		}
	});
} 
/**
 * 获取根据部门和组别查询得到的结果集
 * @return
 */
function checkQuery(){    	
    	var department=$("#department").find("option:selected").val();    	
    	var group=$("#group").find("option:selected").val();    	
    	if(department=='0'){
    		alert('请选择部门');    		
    	}
    	else 
    	{
    		if(group=='0'){
    		alert('请选择组别');
    	}else{
        $.ajax({
    		url :"servlet/MarkDepartQuerySerlvet",
    		type : 'get',
    		contentType : "application/json;charset=utf-8",
    		dataType : 'json',
    		data :'departId='+department+'&groupId='+group,
    		success : function(json){
    			var inhtml = "";    			
    			for (i = 0; i < json.contents.length; i++) {
    				inhtml += "<tr><td width='30%' height='20'><div align='center'>"+json.contents[i].DepartName+"</div></td><td width='30%' height='20'><div align='center'>"+json.contents[i].GroupName+"</div></td><td width='40%' height='20'><div align='center'>"+json.contents[i].Sum+"</div></td></tr>";
    			}
    			$('#dgQuery').html(inhtml);  
    		}
    	});  
 }
  }
    }
/**
 * 获取根据项目和组别查询得到的结果集
 * @return
 */  
 function checkItemQuery(){    	
    		    var itemId=$("#item").find("option:selected").val();    	
    		    var roleId=$("#role").find("option:selected").val();
    		    	if(itemId=='0'){
    		    		alert('请选择项目');    		
    		    	}
    		    	else 
    		    	{
    		    		if(roleId=='0'){
    		    		alert('请选择组别');
    		    	}else{
    		        $.ajax({
    		    		url :"servlet/MarkItemQueryServlet",
    		    		type : 'get',
    		    		contentType : "application/json;charset=utf-8",
    		    		dataType : 'json',
    		    		data :{itemId:itemId,roleId:roleId},
    		    		success : function(json){
    		    			var inhtml = "";    			
    		    			for (i = 0; i < json.contents.length; i++) {
    		    				inhtml += "<tr><td width='30%' height='20'><div align='center'>"+json.contents[i].ItemName+"</div></td><td width='30%' height='20'><div align='center'>"+json.contents[i].GroupName+"</div></td><td width='40%' height='20'><div align='center'>"+json.contents[i].Sum+"</div></td></tr>";
    		    			}
    		    			$('#igQuery').html(inhtml);     		    			
    		    			
    		    		}
    		       });
 }
  }
    }

    		    
    		    	
 

    	












