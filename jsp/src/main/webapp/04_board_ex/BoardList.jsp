<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="board_ex.model.*, board_ex.service.*" %>
<%@ page import="java.util.List" %>

<%  //웹브라우저가 게시글 목록을 캐싱할 경우 새로운 글이 추가되더라도 새글이 목록에 안 보일 수 있기 때문에 설정
	response.setHeader("Pragma","No-cache");		// HTTP 1.0 version
	response.setHeader("Cache-Control","no-cache");	// HTTP 1.1 version
	response.setHeader("Cache-Control","no-store"); // 일부 파이어폭스 버스 관련
	response.setDateHeader("Expires", 1L);			// 현재 시간 이전으로 만료일을 지정함으로써 응답결과가 캐쉬되지 않도록 설정
%>

<%
// 전체 메세지 레코드 검색

ListArticleService service = ListArticleService.getInstance();
int totalPageCount = service.getTotalPage();  

// 넘겨오는 page 번호 얻어오기
String pNum = request.getParameter("page");
List <BoardVO> mList =  service.getArticleList(pNum); 

 
%>

<%
// 현재 페이지 번호 가져오기
int currentPage = 1;
if (pNum != null) {
    currentPage = Integer.parseInt(pNum); // 받아오는 번호에 따라 현재 페이지 설정
}

int pageGroupSize = 5; // 한 그룹당 보여줄 페이지 지정

// 현재 페이지 그룹의 시작 페이지 계산
int startPage = ((currentPage - 1) / pageGroupSize) * pageGroupSize + 1;
// 현재 페이지 그룹의 끝 페이지 계산
int endPage = startPage + pageGroupSize - 1;

// 마지막 페이지가 전체 페이지 수를 넘기지 못하도록 설정
if (endPage > totalPageCount) { 
    endPage = totalPageCount;
}

// 이전 그룹의 시작 페이지 계산
int prevGroupStart = startPage - pageGroupSize;
// 다음 그룹의 시작 페이지 계산
int nextGroupStart = endPage + 1;
%>



<HTML>
<head><title> 게시글 목록 </title>
</head>

<BODY>

	<h3> 게시판 목록 </h3>
	
	<table border="1" bordercolor="darkblue">
	<tr>
		<td> 글번호 </td>
		<td> 제 목 </td>
		<td> 작성자 </td>
		<td> 작성일 </td>
		<td> 조회수 </td>
	</tr>
	
	<% if( mList.isEmpty() ) { %>
		<tr><td colspan="5"> 등록된 게시물이 없습니다. </td></tr>
	<% } else { %>
	<!-- *************** -->
	<!--  여기에 목록 출력하기  -->
		<% for( BoardVO vo : mList) { %>
				<tr>
				<td><%= vo.getSeq() %></td>
				<td><a href="BoardView.jsp?seq=<%= vo.getSeq()%>"><%= vo.getTitle() %></a></td>
				<td><%= vo.getWriter() %></td>
				<td><%= vo.getRegdate() %></td>
				<td><%= vo.getCnt() %></td>
				</tr>
				
		<% }  %>

	<% }  %>
		<tr>
			<td colspan="5">
				<a href="BoardInputForm.jsp">글쓰기</a>
			</td>
		</tr>
	</table>
	
	<hr/>

<%-- 	<% for(int i=1; i<=totalPageCount; i++) { %>
		<a href="BoardList.jsp?page=<%=i%>">[<%= i %>]
	<% } // end of for %> --%>
	
	<% if (prevGroupStart >= 1) { %>
		<a href="BoardList.jsp?page=<%=prevGroupStart%>">이전</a>
	<% } %>
	
	<% for (int i = startPage; i <= endPage; i++) { %>
		<a href="BoardList.jsp?page=<%=i%>"><%=i%></a>
	<% } %>
	
	<% if (nextGroupStart <= totalPageCount) { %>
		<a href="BoardList.jsp?page=<%=nextGroupStart%>">다음</a>
	<% } %>

</BODY>
</HTML>
