<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board_mybatis.model.*,board_mybatis.service.*" %>

<%
	// 0. 넘겨받는 데이타의 한글 처리
	request.setCharacterEncoding("utf-8");
	
%>
 
 <!-- 1. 이전 화면의 입력값을 넘겨받아 BoardVO 객체의 각 멤버변수로 지정 -->
<jsp:useBean id="m" class="board_mybatis.model.BoardVO">
	<jsp:setProperty name="m" property="*"/>
</jsp:useBean>

<%
	ModifyArticleService service = ModifyArticleService.getInstance();

	int result =  0; 
	result = service.update(m);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판글수정</title>
</head>
<body>
<!-- 
    // 게시글 수정이 성공적으로 되었다면 그 해당 게시글을 보여주는 페이지로 이동하고
    // 그렇지 않다면, "암호가 잘못 입력되었습니다"를 출력
 -->
    <% if( result == 1 ) { 
            response.sendRedirect("BoardView.jsp?seq=" + m.getSeq()); %>
	<% } else { %>
		<h4>암호가 잘못 입력되었습니다!</h4>
		<a href="BoardModifyForm.jsp?seq=<%= m.getSeq() %>">다시 시도하기</a>
	<% }  %>


</body>
</html>