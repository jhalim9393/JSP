<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board_mybatis.service.*, board_mybatis.model.*" %>
<%
   // 1. 수정할 레코드의 게시글번호를 넘겨받기
   String seq = request.getParameter("seq");

   // 2. Service에 getArticleById()함수를 호출하여 그 게시글번호의 레코드를 검색
   BoardVO vo = ViewArticleService.getInstance().getArticleById(seq);
    
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정하기</title>
</head>
 <body>
   <h4> 게시판 글 수정하기 </h4>
   <table>
   		<form action="BoardModify.jsp" name='frm' method='post'>
   		   <tr>
   			   <td>제  목 </td>
   			   <td> <input name="title" type='text' value='<%= vo.getTitle()%>'></td>
   		   </tr>
		   <tr>
			   <td>패스워드</td>
			   <td><input name="pass" type='password'>(수정/삭제시 필요)</td>
		   </tr>
		   <tr>
			   <td>내  용 </td>
			   <td> <textarea name='content' rows='10' cols='40'><%= vo.getContent()%></textarea></td>
		   </tr>
		   <tr>
			   <td></td>
			   <td><input type='submit' value='수정하기'>
		   	   <input type='button' value='목록보기' onclick="window.location='BoardList.jsp'"></td>
		   </tr>
		   <!-- 2. 게시글번호를 다음 페이지로 넘기기 위해 hidden 태그로 지정 -->
		   <input type="hidden" name="seq" value="<%= seq %>">

   </form>
</table>
</body>
</html>