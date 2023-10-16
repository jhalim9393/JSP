<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="emp.*" %>

<%
	EmpDAO dao = EmpDAO.getInstance(); // singleton 패턴 
%>


<%
//	request.setCharacterEncoding("utf-8"); 
	// 1. 이전 화면의 사용자 입력값 얻어오기
	int empno = Integer.parseInt(request.getParameter("empno"));
	String ename = request.getParameter("ename");
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	String job = request.getParameter("job");
	int sal = Integer.parseInt(request.getParameter("sal"));
	
	// 2. 얻어온 입력값들을 EmpVO의 멤버변수에 저장
	EmpVO vo = new EmpVO();
	vo.setEmpno(empno);
	vo.setEname(ename);
	vo.setDeptno(deptno);
	vo.setJob(job);
	vo.setSal(sal);
	
	// 드라이버 실행
	
	// 3. EmpDAO에 insertEmp() 함수로 EmpVO 객체를 전송
	dao.insertEmp(vo);
	

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 사원등록 </title>
</head>
<body>
	 성공적으로 입력되었는지 DB에서 확인합니다.
</body>
</html>