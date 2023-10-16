<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="guest.service.WriteMessageService"%>    

<!--  0. 넘겨받는 데이타의 한글처리 -->
<% request.setCharacterEncoding("utf-8"); %>
	
<!--1. 화면의 입력값을 Message 클래스로 전달 -->
<jsp:useBean id="m" class="guest.model.Message">
	<jsp:setProperty name="m" property="*"/>
</jsp:useBean>
	
<!-- 2. Service 클래스의 함수 호출  -->
<%
	WriteMessageService service = WriteMessageService.getInstance();
	service.write(m); 

%>

<!-- 다른 화면으로 변경 -->
<% response.sendRedirect("listMessage.jsp"); %>


