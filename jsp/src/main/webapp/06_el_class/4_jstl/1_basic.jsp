<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1_basic.jsp</title>
</head>
<body>

<!-- 변수 선언 (자바 코딩 안쓰는 법) -->
<c:set var="gender" value="male"/>
<c:set var="age">15</c:set>
<%--  <c:set var="age" value="25"></c:set> 위에 코딩과 같음 
   => 이 주석은 반드시 jsp 주석으로만 처리해야 함--%> 

<!-- 제어문 -->
<c:if test="${gender eq 'male' }">
	당신은 남정네입니다
</c:if></br>
<c:if test="${gender eq 'female' }">
	당신은 여인네입니다
</c:if></br>

<c:if test="${age ge 20 }">
	당신은 성인입니다
</c:if></br>

<!--  10 미만이면 어린이, 10이상 20미만이면 청소년, 그렇지않으면 성인 -->
<c:choose>
	<c:when test="${ age lt 10 }">어린이</c:when>
	<c:when test="${ age ge 10 && age lt 20 }">청소년</c:when>
	<c:otherwise>어린이</c:otherwise>
</c:choose>

<c:set var="sum" value="0"/>
<c:forEach var="i" begin="1" end="100">
	<c:set var="sum" value="${ sum + i }"/>
</c:forEach>

1~100까지의 합 : ${ sum } </br>

<%-- 1~100 까지의 홀수의 합과 짝수의 합 --%>
<c:forEach var="i" begin="1" end="100">
	<c:if test="${i % 2 == 0 }">
		<c:set var="even_sum" value="${ even_sum + i }"/>
	</c:if>
	<c:if test="${i % 2 != 0 }">
		<c:set var="odd_sum" value="${ odd_sum + i }"/>
	</c:if>
</c:forEach>

 1~100 까지의 짝수의 합 : ${ even_sum }</br>
 1~100 까지의 홀수의 합 : ${ odd_sum }</br>


</body>
</html>