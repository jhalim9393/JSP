<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%

	// *********************************************
	// 이전 화면에서 넘어오는 값을 받을 때는
	// 		request.getParameter("파라메터명");

	// 서버단에서 request 객체 저장한 값을 받을 때는
	Object obj = request.getAttribute("param");
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 메인이라네 </title>
</head>
<body>
	
		<%= obj %> <br/>
		복잡하다고 지나친 좌절과 놀람은 금물입니다. <br/><br/>
		<img src="07_mvc_class/1_mvcSimple/imgs/sponge_1.JPG"/><br/><br/>

</body>
</html>