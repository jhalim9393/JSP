<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ page import="member.beans.*" %>  

<%

	String userId = request.getParameter("userId"); // 데이터 받아오기
	MemberDao dao = MemberDao.getInstance();
	boolean result = MemberDao.getInstance().isDuplicatedId( userId );
	
	String resultData="NO";
	if (result){		
		resultData = "YES";
	}		
	out.print(resultData);

	
%>