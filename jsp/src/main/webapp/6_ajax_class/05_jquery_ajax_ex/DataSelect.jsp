<%@page contentType="text/xml; charset=utf-8" %>
<%@ page language="java" import="java.sql.*"%>

<%
String driver 	= "com.mysql.cj.jdbc.Driver";
String url		= "jdbc:mysql://localhost:3306/join";
String user		= "harim";
String pass		= "1234";

String rtn_xml="";

	Class.forName(driver);
	Connection connection=DriverManager.getConnection(url,user,pass);
	
	String sql = "select * from ajax_temp";
	
	PreparedStatement ps = connection.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();	
	
	rtn_xml += "<customer>";

	while (rs.next()){		
		rtn_xml += "<person>"; // 사람 개수만큼 생김
		rtn_xml += "<name>" + rs.getString("name") + " </name>";
		rtn_xml += "<age>" + rs.getString("age") +  "</age>";
		rtn_xml += "<tel>" + rs.getString("tel") +  "</tel>";
		rtn_xml += "<addr>" + rs.getString("addr") +  "</addr>";
		rtn_xml += "</person>";		
	}	
	rtn_xml += "</customer>";
	rs.close();
	ps.close();
	connection.close();

	

	System.out.println(rtn_xml);
	
	out.write(rtn_xml);

%>