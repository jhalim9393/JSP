<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="customer.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인</title>

<%

	// 이전화면 폼에서 넘겨받는 값
	String id = request.getParameter("id"); // input박스 name값
	String pass = request.getParameter("pass");

	CusDAO dao = CusDAO.getInstance();
	boolean result = dao.checkLogin(id, pass); // vo에서 선언한 이름
	
	
	// user, password가 같을 때 로그인 성공, 그렇지 않으면 로그인 실패
	if( result ){
		// #2. 세션에 "id"라는 이름에 변수 user 값을 저장
		session.setAttribute("id", id);

		// #1. 로그인 성공하면 바로 MainPage.jsp를 요청
		response.sendRedirect("Success_view.jsp");
		
	} else {

		// #1. 로그인에 실패하면 바로 LoginForm.jsp을 요청
		response.sendRedirect("Login_view.jsp");
	}			
%>
</head>
<body>

</body>
</html>