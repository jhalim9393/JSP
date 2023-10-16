<%@ page contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>

<%@ page import="info.beans.*" %>

<%-- 한글 처리 --%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="bean" class="info.beans.InfoBean">
	<jsp:setProperty name="bean" property="*"/> 
	<!-- 이름만 다 맞춰주면 *만 써도 다 받아올 수 있음 -->

</jsp:useBean> <!-- InfoBean bean = new InfoBean(); 과 같음 -->


<!DOCTYPE html>
<html>
<body>
	<h2>  당신의 신상명세서 확인 </h2>
	이   름  : <%= bean.getName() %><br/>
	주민번호 : <jsp:getProperty property="id" name="bean"/><br/>
	성  별   :<jsp:getProperty property="gender" name="bean"/><br/>  
	맞습니까???? <!-- bean.getGender() -->
</body>
</html>