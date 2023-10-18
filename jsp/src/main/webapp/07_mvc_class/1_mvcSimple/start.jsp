<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	// 프로젝트명을 미리 변수로 지정하고 시작
	String projectName = "/jsp";

%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>start.jsp</title>
</head>
<body>

<a href="simpleView.jsp">기존 방식(상대경로)</a></br>
<a href="<%= projectName %>/07_mvc_class/1_mvcSimple/simpleView.jsp">기존 방식(절대경로)</a></br>
<hr/>

<a href="<%= projectName %>/SimpleControl">MVC</a></br>
<a href="<%= projectName %>/ict.kim">MVC2</a></br>
<a href="<%= projectName %>/ict.kim?type=first">MVC3</a></br>


</body>
</html>