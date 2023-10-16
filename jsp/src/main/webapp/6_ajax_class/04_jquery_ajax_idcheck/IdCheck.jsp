<%@ page contentType="text/xml; charset=utf-8" %>
<%@ page language="java" import="java.sql.*"%>

<%
String driver 	= "com.mysql.cj.jdbc.Driver";
String url		= "jdbc:mysql://localhost:3306/basic";
String user		= "scott";
String pass		= "tiger";


	Class.forName(driver);
	Connection connection=DriverManager.getConnection(url,user,pass);
	
	String sql = "select * from emp where ename=?";
	System.out.println(sql);
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setString(1, request.getParameter("id"));
	ResultSet rs = ps.executeQuery();		

	String result="NO";
	if (rs.next()){		
		result = "YES";
	}		
	out.print(result);
%>

