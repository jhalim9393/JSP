<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%	

	String userId = "";
	// 1. 클라이언트로부터 Cookie를 얻어옴 
	Cookie[] ck = request.getCookies();
	
	// 2. 쿠키 이름 중에 "yourid"가 있다면 그 쿠키의 값을 출력
	for(int i=0; ck!=null && i<ck.length; i++){ // 쿠키가 있을 때 그 길이만큼
		if(ck[i].getName().equals("yourid")){ // 문자열이므로 equals
			userId = ck[i].getValue(); // 쿠키값을 getValue()로 받아옴
		}
	}
	
	
%>

<html>
<head><title>쿠키</title></head>
<body>	

<h1>Cookie 정보 알아내기</h1>

<h4>다음은 클라이언트 브라우저의 쿠키에서 얻어온 값 : <%= userId %></h4><br><br>


<br><a href="01_ChangeCookie.jsp"> 쿠키값 변경 </a><br/>
<br><a href="01_RemoveCookie.jsp"> 쿠키제거 </a>

</body></html>
