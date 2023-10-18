package mvc.simple;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/SimpleControl")
public class SimpleControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String jspDir = "/07_mvc_class/1_mvcSimple/";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [1] request(요청객체)로 부터 사용자 요청을 받음
		String type = request.getParameter("type");
		
		// [2] 사용자의 요청에 따른 기능 수행
		String result = "없음";
		if(type == null) {
			result = "안녕하세요"; }
		else if(type.equals("first")){
			result = "반갑습니다"; }
		
		// [3] 처리 결과를 request(session) 저장
		request.setAttribute("param", result); // request에 저장
		
		// [4] 포워딩 (서버 단에서 사용자 모르게 페이지 변경)
		//<jsp:forward page=""/> -> java 파일에서는 jsp 태그 못씀(변환해줘야 함)
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspDir + "simpleView.jsp"); // 위치 값을 
		dispatcher.forward(request, response);
	}

}
