package cn.edu.hbcit.smms.services.gamemanageservices;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import cn.edu.hbcit.smms.dao.gamemanagedao.QueryMark;
import cn.edu.hbcit.smms.pojo.QueryAllMarksPlayerMsg;
import cn.edu.hbcit.smms.pojo.QueryMarkPoJo;


public class QueryMarkServices {

	 public List<QueryMarkPoJo> getDepName(){
		 return new QueryMark().getDepName();
	 }
	 
	 public List<QueryMarkPoJo> getStudentsMark(List<QueryMarkPoJo> depNameList){
		 return new QueryMark().getStudentsMark(depNameList);
	 }
	 
	 public List<QueryMarkPoJo> getTeacherMark(List<QueryMarkPoJo> depNameList){
		 return new QueryMark().getTeacherMark(depNameList);
	 }
	 
	 public List<QueryMarkPoJo> getStudentsFinalMark(List<QueryMarkPoJo> depNameList){
		 return new QueryMark().getStudentsFinalMark(depNameList);
	 }
	 
	 public List<QueryMarkPoJo> getTeacherFinalMark(List<QueryMarkPoJo> depNameList){
		 return new QueryMark().getTeacherFinalMark(depNameList);
	 }
	 
	 public void createMarksDocContext(String file,List<QueryMarkPoJo> depNameList,List<QueryMarkPoJo> studentsMarkList,List<QueryMarkPoJo> teacherMarkList,List<QueryMarkPoJo> studentsFinalMarkList,List<QueryMarkPoJo> teacherFinalMarkList)throws DocumentException, IOException{   
		  new QueryMark().createMarksDocContext(file, depNameList, studentsMarkList, teacherMarkList, studentsFinalMarkList, teacherFinalMarkList);
	 }
	 
	 public List<QueryAllMarksPlayerMsg> getStudentsMarks(String depName){
		 return new QueryMark().getStudentsMarks(depName);
	 }
	 
	 public List<QueryAllMarksPlayerMsg> getTeacherMarks(String depName){
		 return new QueryMark().getTeacherMarks(depName);
	 }
	 
	 public void printAllPlayerMarksMessage(String file,List<QueryMarkPoJo> depNameList)throws DocumentException, IOException{   
		 new QueryMark().printAllPlayerMarksMessage(file, depNameList);
	 }
}
