function show() {
	var sportsid = $("#sportsname").find("option:selected").val();
	$.ajax( {
				url : "servlet/SelectDepartNameInSportsServlet?action=departname",
				type : 'get',
				contentType : "application/json;charset=utf-8",
				dataType : 'json',
				data :'sportsid=' + sportsid,
				success : function(json) {
					var inhtml = "";
					inhtml += "<option value='0'>--不限--</option>";
					for (i = 0; i < json.contents.length; i++) {
						inhtml += "<option value="+ json.contents[i].getDepartid +">" + json.contents[i].getDepartname + "</option>";
					}
					$('#departname').html(inhtml);
				},
				error : function(xhr, status, errorThrown) {
					Dialog.alert("errorThrown=" + errorThrown);
				}
			});
	$.ajax( {
				url : "servlet/SelectDepartNameInSportsServlet?action=province",
				type : 'get',
				contentType : "application/json;charset=utf-8",
				dataType : 'json',
				data :'sportsid=' + sportsid,
				success : function(json) {
					var inhtml = "";
					inhtml += "<option value='0'>--不限--</option>";
					for (i = 0; i < json.contents.length; i++) {
						inhtml += "<option value="+ json.contents[i].groupid +">" + json.contents[i].groupname + "</option>";
					}
					$('#province').html(inhtml);
				},
				error : function(xhr, status, errorThrown) {
					Dialog.alert("errorThrown=" + errorThrown);
				}
			});
			}

function itemtype(){
	var sportsid = $("#sportsname").find("option:selected").val();
	var itemtype = $("#itemtype").find("option:selected").val();
	var params="sportsid="+sportsid+"&itemtype="+itemtype;
	
	if(sportsid==0){
		Dialog.alert("请选择届次");
		return false;
	}else{
		$.ajax( {
			url : "servlet/SelectItemByTypeServlet",
			type : 'get',
			contentType : "application/json;charset=utf-8",
			dataType : 'json',
			data :params,
			success : function(json) {
				var inhtml = "";
				inhtml += "<option value='0'>--不限--</option>";
				for (i = 0; i < json.contents.length; i++) {
					inhtml += "<option value="+ json.contents[i].itemid +">" + json.contents[i].itemname + "</option>";
				}
				$('#item').html(inhtml);
			},
			error : function(xhr, status, errorThrown) {
				Dialog.alert("errorThrown=" + errorThrown);
			}
		});
	}
	
}

//点击查询后的数值传递
function selectdata()
{
	var sportsid = $("#sportsname").find("option:selected").val();//届次
	var playername = $('#playername').val();//运动员姓名
	var departname = $("#departname").find("option:selected").val();//参赛系别/部门
	var province = $("#province").find("option:selected").val();//参赛组别
	var itemtype = $("#itemtype").find("option:selected").val();//项目类型
	var item = $("#item").find("option:selected").val();//参赛项目
	var score1 = $('#score1').val();//成绩1
	var score2 = $('#score2').val();//成绩2
	var breakrecord="0";
	if($("#breakrecord").attr("checked")) //判断是否已经打勾 
	{
		breakrecord="1";
	}
	if(score1.length==0&&score2.length!=0){
		Dialog.alert("参赛成绩两者都写，或者两者都不写");
		return false;
		}
	if(score1.length!=0&&score2.length==0){
		Dialog.alert("参赛成绩两者都写，或者两者都不写");
		return false;
		}
	//if(score1.length!=0&&score2.length!=0){
		//if(itemtype==0){
			//alert("比赛类型不能为空");
			//if(item==0){
				//alert("比赛项目不能为空");
			//}
		//}
	//}
	if(sportsid == 0)
	{
		Dialog.alert("请选择届次");
		return false;
	}else{
		$.ajax( {
			url : "servlet/SelectByQuestionServlet?sportsid="+sportsid+"&playername="+playername+"&departname="+departname+"&province="+province+"&itemtype="+itemtype+"&item="+item+"&score1="+score1+"&score2="+score2+"&breakrecord="+breakrecord,
			type : 'post',
			contentType : "application/json;charset=utf-8",
			dataType : 'json',
			success : function(jsonarray) {
				var inhtml = "";
				Dialog.alert("共查询出"+jsonarray.length+"条");
			inhtml += "<tr class='tableTitle'>";
		if(jsonarray.length == 0){
			inhtml += "<td width='10%' height='20' colspan='8'><div align='center'><span><h1>没有查到数据请重新查找</h1></span></div></td>";
		    inhtml +="</tr>";
	        }else{
		if(jsonarray[0].size == 5){
	        inhtml += "<td width='14%' height='20' ><div align='center'><span>系别 </span></div></td>";
	        inhtml += "<td width='16%' height='20' ><div align='center'><span>比赛项目</span></div></td>";
	        inhtml += "<td width='16%' height='20' ><div align='center'><span>成绩</span></div></td>";
	        inhtml += "<td width='14%' height='20'><div align='center'><span>名次</span></div></td>";
	        inhtml += "<td width='14%' height='20'><div align='center'><span>破纪录</span></div></td>";
	        inhtml +="</tr>";
		}else{
	        inhtml += "<td width='10%' height='20'><div align='center'><span>姓名</span></div></td>";
	        inhtml += "<td width='10%' height='20'><div align='center'><span>号码</span></div></td>";
	        inhtml += "<td width='15%' height='20' ><div align='center'><span>组别</span></div></td>";
	        inhtml += "<td width='14%' height='20' ><div align='center'><span>系别 </span></div></td>";
	        inhtml += "<td width='16%' height='20' ><div align='center'><span>比赛项目</span></div></td>";
	        inhtml += "<td width='16%' height='20' ><div align='center'><span>成绩</span></div></td>";
	        inhtml += "<td width='14%' height='20'><div align='center'><span>名次</span></div></td>";
	        inhtml += "<td width='14%' height='20'><div align='center'><span>破纪录</span></div></td>";
	        inhtml +="</tr>";
		}
				for (i = 0; i < jsonarray.length; i++) {
					inhtml += "<tr class='tableContent'>";
					if(jsonarray[0].size == 5){
						inhtml += "<td width='10%' height='20'><div align='center'><span>"+jsonarray[i].departshortname+"</span></div></td>";
				        inhtml += "<td width='10%' height='20'><div align='center'><span>"+jsonarray[i].finalitemname+"</span></div></td>";
				        inhtml += "<td width='15%' height='20' ><div align='center'><span>"+jsonarray[i].score+"</span></div></td>";
				        inhtml += "<td width='14%' height='20' ><div align='center'><span>"+jsonarray[i].position+"</span></div></td>";
				        inhtml += "<td width='14%' height='20'><div align='center'><span>"+jsonarray[i].recordlevel+"</span></div></td>";
					}else{
						inhtml += "<td width='10%' height='20'><div align='center'><span>"+jsonarray[i].playername+"</span></div></td>";
				        inhtml += "<td width='10%' height='20'><div align='center'><span>"+jsonarray[i].playernum+"</span></div></td>";
				        inhtml += "<td width='15%' height='20' ><div align='center'><span>"+jsonarray[i].groupname+"</span></div></td>";
				        inhtml += "<td width='14%' height='20' ><div align='center'><span>"+jsonarray[i].departshortname+"</span></div></td>";
				        var reg = /[,]/g;
			        	var str = jsonarray[i].itemname+"";
			        	var str1= str.replace(reg,"<br>");
				        inhtml += "<td width='16%' height='20' ><div align='center'><span>"+str1+"</span></div></td>";
			        	var score = jsonarray[i].score+"";
			        	var score2 = score.replace(reg,"<br>");
				        inhtml += "<td width='16%' height='20' ><div align='center'><span>"+score2+"</span></div></td>";
				        var position = jsonarray[i].position+"";
				        var position2 = position.replace(reg,"<br>");
				        inhtml += "<td width='14%' height='20'><div align='center'><span>"+position2+"</span></div></td>";
				        var recordlevel = jsonarray[i].recordlevel+"";
				        var recordlevel2 = recordlevel.replace(reg,"<br>");
				        inhtml += "<td width='14%' height='20'><div align='center'><span>"+recordlevel2+"</span></div></td>";
					}
			        inhtml +="</tr>";
					
				}
        }
				$('#infocontent').html(inhtml);
			},
			error : function(xhr, status, errorThrown) {
				Dialog.alert("errorThrown=" + errorThrown);
			}
		});
	}
}






