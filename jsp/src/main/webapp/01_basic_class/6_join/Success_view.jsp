<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%

	//# 1."id"로 저장된 세션값을 얻어온다.
	Object obj = session.getAttribute("id"); // 통신하는 것이 아니라 객체 저장이라 object
	
	//# 2. 값이 null이라면 LoginForm.jsp로 페이지 이동
	if(obj == null){
		response.sendRedirect("Login_view.jsp");
		return;
	}
	
	//# 3. null이 아니라면 String 형변환하여 변수에 지정
	String user = (String)obj;
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공화면</title>
</head>
<body>
<h1> 접속에 성공하셨습니다 ! </h1>
<%= user %>님께서 로그인 중입니다.
</body>
</html>