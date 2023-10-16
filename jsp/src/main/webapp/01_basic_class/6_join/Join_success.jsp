<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="customer.*" %>
    
    
<%

	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String phone_num = request.getParameter("phone_num");
	String addr = request.getParameter("addr");
	
	CusVO vo = new CusVO();
	
	vo.setId(id);
	vo.setPass(pass);
	vo.setName(name);
	vo.setPhone_num(phone_num);
	vo.setAddr(addr);

	CusDAO dao = CusDAO.getInstance();
	
	dao.insertCus(vo);
	

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>

<h1>회원가입 성공하셨습니다! </br> 반갑습니다 :) </h1>
<a href="Login_view.jsp"><input type="reset" value='로그인'></a>
</body>
</html>