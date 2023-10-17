<%@ page language="java" contentType="text/html; charset=utf-8" %>
 <%@ page import="java.util.*" %>
 <%@ page import="mybatis.guest.model.Comment" %>    
 <%@ page import="mybatis.guest.service.CommentService" %>  
  
  <!-- 서비스의 메소드 호출  -->
  <%
  	String searchKey = request.getParameter("searchKey");
  	String searchWord = request.getParameter("searchWord");
  	System.out.println(searchKey + "/" + searchWord); // 내가 입력한 값 잘 받아오는지 확인
  
  
    //HashMap condition = new HashMap();
    List<Comment> mList = CommentService.getInstance().selectComment(searchKey, searchWord);
 %>  
  
<!DOCTYPE HTML>
<html>  
<head>
	<meta charset="UTF-8">
<title>목록보기</title>
</head>
<body>

<hr/>
	<form action="listComment.jsp"> <!-- method 지정 안하면 기본값 get , action 지정 안하면 자기 자신 파일로 -->
		<select name="searchKey">
				<option value="user_id">작성자</option>
				<option value="comment_content">글내용</option>
		</select>
		<input type="text" name="searchWord">
		<input type="submit" value="검색">
	</form>
<hr/>

<a href="insertCommentForm.jsp">방명록 남기기 </a><br/><br/>


<table border="1">
	<tr><td>글번호</td><td>작성자</td><td>등록일</td></tr>
	<% for( Comment comment : mList ) { %>
		<tr>
			<td><%= comment.getCommentNo() %></td>
			<td><a href="viewComment.jsp?cId=<%=comment.getCommentNo()%>"><%= comment.getUserId()%> 님이 쓴 글</a></td>
			<td><%= comment.getRegDate()%></td>
		</tr>
	<% } %>
</table>
</body>
</html>