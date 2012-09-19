<%@ page language="java" import="java.util.*,cn.edu.hbcit.smms.pojo.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>update_runway.jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDialog_inner.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/zDrag.js"></script>
   <script type="text/javascript">
   function update(){
  
      var runway = document.getElementsByName("runway");
      for( var i = 0; i < runway.length; i++){
          for(var j = i + 1; j < runway.length; j++){
              if(runway[i].value == runway[j].value){
                Dialog.alert("不能出现重复值!");
                 return false;
              }
           }
           
      }

      var playernumid="";
      var runway="";
      var playernumids = document.getElementsByName("playerId");
	  var runways = document.getElementsByName("runway");
	  for(var i = 0; i < runways.length; i++ ){
		        if (i > 0){
		              runway = runway + ";";
		              playernumid = playernumid + ";";
		        
		        }
				runway = runway + runways[i].value;
				playernumid = playernumid + playernumids[i].value;
	   }
	  
	  $.ajax({
			url : "${pageContext.request.contextPath}/servlet/UpdatePlayerNumServlet",
				type : 'post',
				data : {playerID:playernumid,runway:runway},
				success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success"){
								Dialog.alert("修改成功!");
							}
							else{
								Dialog.alert("修改失败!");
								}
						}
		});
   }
   </script>

  </head>
  
  <body>
  
  <form method="post" >
  <label>第${ sessionScope.teamNum}组</label>
  <table width="70%" border="1">
  <tr><td>运动员号码</td><td>跑道</td></tr>
  <c:forEach items="${sessionScope.info}" var="info">
  <tr>
  <td>
   <input type="text" value="${info.playNum }" style=" width:80px; height:20px" name="playnum" />
  <input type="hidden" name="playerId" value="${info.playerId }" />
  </td>
 
   
    <td>
    <input type="text" value="${info.runway }" style=" width:80px; height:20px" name="runway" />
   
    </td>
  </tr>
    </c:forEach>
</table>
 <input type="button" value="保存" onclick="update()">
  </form>
    
  </body>
</html>
