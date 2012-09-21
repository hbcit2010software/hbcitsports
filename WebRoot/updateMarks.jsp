<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'updateMarks.jsp' starting page</title>
     
     <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
<style>
			body { background: #ffffff; color: #444;font-size:12px; }
			a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
			body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
			img, fieldset { border: none 0; }
			body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
			body, input, textarea, select, button { margin: 0; font-size: 12px; font-family: Tahoma, SimSun, sans-serif; }
			div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
			h5 { font-size:12px; }
			ol li,ul li{ margin-bottom:0.5em;}
			pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
			pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
			#content { padding-left:50px; padding-right:50px; }
			#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
			#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
			#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
			#content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
			hr { clear:both; margin:7px 0; +margin: 0;
			border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
			.infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
			.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
			.buttonStyle:hover{background:url(${pageContext.request.contextPath }/admin/js/images/buticon.gif) no-repeat left -23px;}
			.input_on{padding:2px 8px 0pt 3px;height:18px;border:1px solid #999;background-color:#eeeeee;}
			</style>  
<script type="text/javascript">
    function upd(){
        
        var depName = $('#depName').val();
        var finalusm = $('#finalusm').val();
        var finalutm = $('#finalutm').val();
       // Dialog.alert("您将修改的部门为"+depName+"<br/>学生积分："+finalusm+"<br/>教师积分"+finalutm);
        var reg = /^[0-9]*$/;
        if((reg.test(finalusm)==true) && (reg.test(finalutm)==true)){
             $.ajax({ 
               url : "servlet/UpdateMarksServlet",
               type : 'post',
               data : {depName:depName,finalsm:finalusm,finaltm:finalutm},
               
               success : function(mm) {
               var revalue = mm.replace(/\r\n/g, '');
               
               if (revalue =="success") {
							alert('更新成功');
								}
			   else{ 
			   alert("更新失败!您已经更新过了", function() {
							    window.location.href = window.location.href;
							}); }
         }
     });  }else{alert('请输入准确数字');return false;}
        }
       
    </script>
    
  </head>

  <body>
    <%
       String depName = "";
       depName = new String(request.getParameter("depName").getBytes("ISO-8859-1"),"UTF-8");
       System.out.println(depName);
      %>
                         当前更改部门：<input id="depName" type="text" style="border:0" readonly value="<%=depName%>"><br/>
                        学生最终成绩：<input id="finalusm" type="text"/><br/>
                        教工最终成绩：<input id="finalutm" type="text"/><br/>
       <input type="button" value="修改" onclick="upd()" />
    
  </body>
</html>
