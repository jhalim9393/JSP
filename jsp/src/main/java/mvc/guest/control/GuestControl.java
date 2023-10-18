package mvc.guest.control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.guest.command.Command;
import mvc.guest.command.CommandDelete;
import mvc.guest.command.CommandException;
import mvc.guest.command.CommandInput;
import mvc.guest.command.CommandList;
import mvc.guest.command.CommandNull;

/**
 * Servlet implementation class GuestControl
 */

@WebServlet("/GuestControl") // main.jsp 에 있는 경로와 같아야 함
public class GuestControl extends HttpServlet {
	
	private HashMap commandMap;
	private String	jspDir = "/07_mvc_class/2_mvcGuest_mysql/";
	private String  error = "error.jsp";
	

    public GuestControl() {
        super();       
		initCommand();
	}

	private void initCommand(){
		commandMap = new HashMap();

		commandMap.put("main-page",	new CommandNull("main.jsp") ); // main-page 라는 애가 들어오면 main.jsp 랑 짝을 맺어줌 <key:value>
		commandMap.put("list-page",	new CommandList("listMessage.jsp") ); // listMessage.jsp 라는 애로 포워딩
		commandMap.put("input-page",	new CommandNull("insertMessage.jsp") );
		commandMap.put("input-do", new CommandInput("saveMessage.jsp")); // 사용자에게 보여줄 다음 페이지 지정
		commandMap.put("delete-page", new CommandNull("deleteMessage.jsp"));
		commandMap.put("delete-do", new CommandDelete("deleteConfirm.jsp")); // 찐 DB 삭제
		
		// 나머지도 추가하기
		
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response); 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String nextPage = "";
		String cmdKey	= request.getParameter("cmd"); // cmd 값이 들어왔다면 그 파라미터 값을 받아옴
		if( cmdKey == null ){ // main.jsp 에서 cmd=list-page -> null 이 아님
			cmdKey = "main-page"; 
		}

		
		Command cmd = null;

		try{
			
			if( commandMap.containsKey( cmdKey ) ){ // hashmap 에 
				cmd = (Command)commandMap.get( cmdKey);
			}else{
				throw new CommandException("지정할 명령어가 존재하지 않음");
			}

			nextPage = cmd.execute( request, response  ); // 해당하는 객체가 불려짐 > 다형성에 맞게 개발한 예

		}catch( CommandException e ){
			request.setAttribute("javax.servlet.jsp.jspException", e );
			nextPage = error;
			System.out.println("오류 : " + e.getMessage() );
		}

		RequestDispatcher reqDp = getServletContext().getRequestDispatcher( jspDir + nextPage );
		reqDp.forward( request, response );
		
	}

}
