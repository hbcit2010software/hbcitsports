<%@page language="java" import="java.net.URLEncoder,java.io.*,java.net.URLDecoder" contentType="application/x-msdownload" pageEncoding="utf-8"%>   
<!-- 此页面用于下载时实现，使用文件流方式下载资源        -->
<!-- 修改bug  刘然       2012/09/09       -->
<%    
		  //关于文件下载时采用文件流输出的方式处理：    
		  //加上response.reset()，并且所有的％>后面不要换行，包括最后一个；    
		   
		  response.reset();//可以加也可以不加    
		  response.setContentType("application/x-download");    
		  String file = (String)request.getParameter("file");
		  String fileName = URLDecoder.decode((String)request.getParameter("fileName"),"UTF-8");

          if(file !=null && fileName !=null){
		  String filedownload = file + fileName;    
		  String filedisplay =  new String(fileName.getBytes("gb2312"), "iso8859-1");;        
		  response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);    
		   
		  java.io.OutputStream outp = null;    
		  java.io.FileInputStream in = null;    
		  try    
		  {    
		  outp = response.getOutputStream();    
		  in = new FileInputStream(filedownload);    
		   
		  byte[] b = new byte[1024];    
		  int i = 0;    
		   
		  while((i = in.read(b)) > 0)    
		  {    
		  outp.write(b, 0, i);    
		  }    
		//      
		outp.flush();    
		//要加以下两句话，否则会报错    
		//java.lang.IllegalStateException: getOutputStream() has already been called for //this response      
		out.clear();    
		out = pageContext.pushBody();    
		}    
		  catch(Exception e)    
		  {    
		  //System.out.println("Error!");    
		  e.printStackTrace();    
		  }    
		  finally    
		  {    
		  if(in != null)    
		  {    
		  in.close();    
		  in = null;    
		  }    
		//这里不能关闭      
		//if(outp != null)    
		  //{    
		  //outp.close();    
		  //outp = null;    
		  //}    
		  }  }
		%>  
