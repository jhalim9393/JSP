<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	// 한글 인코딩
	request.setCharacterEncoding("utf-8"); 
	// 이전 화면에 사용자 입력값을 얻어오기
	String name = request.getParameter("name"); // 대소문자 가림 
	String gender = request.getParameter("gender");
	String occupation = request.getParameter("occupation");
	String [] hobby = request.getParameterValues("hobby");
	String hobbyTxt = "";
	
	if(hobby != null){
		for(int i=0; i<hobby.length; i++) {
			hobbyTxt += hobby[i];
			if (i < hobby.length - 1) {
	            hobbyTxt += ", ";
	        }
		}
	}
	else{
		hobbyTxt += "선택된 취미가 없습니다!";
	}
	
	
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 입력값 출력</title>
</head>
<body>
	이름 : <%= name %> <br/>
	성별 : <%= gender %> <br/>
	직업 : <%= occupation %> <br/>
	취미 : <%= hobbyTxt %> <br/> 
	

</body>
</html>