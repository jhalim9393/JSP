<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="customer.*" %>  

<%

	String userId = request.getParameter("userId"); // 데이터 받아오기
	boolean result = CusDAO.getInstance().isDuplicatedId(userId);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 아이디 중복 확인 </title>
</head>
<body>
<%
	if( result ) {
%>
		사용중인 아이디가 있습니다. 다시 입력하여 주십시오.
<%  } else { %>
		사용할 수 있는 아이디입니다. 
<%  } %>
</body>
</html>