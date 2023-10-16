<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! String name; %>

<% name = "홍길동"; %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>00_basic.jsp</title>
</head>
<body>

안녕하세요 <%= name %> 님

<!-- HTML 주석 -->
<% // 자바 주석 %>
<%-- JSP 주석 --%>

</body>
</html>