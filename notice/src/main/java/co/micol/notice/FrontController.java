package co.micol.notice;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.notice.common.Command;
import co.micol.notice.main.command.MainCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<String, Command>();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//처음 시작될 때 동작하는 메소드(한번만 수행. 그다음부터는 service()만 수행), 요청한 것을 담아두는 곳
		map.put("/main.do", new MainCommand()); //처음 들어오는 페이지를 돌려준다. 구현체로 실어놓음
		//여기에 추가하면 기능이 추가됨. 인터페이스도 만들어줘야함
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서비스 하는 부분 (서버가 만들어주니까 request, response 자동으로 들어감)
		//요청을 분석하고, 수행할 Command를 찾아서 수행하고, 결과를 돌려준다.
		
		//한글깨짐방지
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI(); //요청한거의 uri를 찾아야함. 도메인을 뺀 나머지 전부. uri는 리퀘스트 객체가 가지고 있음. 호출한 uri구하는 거
		String contextPath = request.getContextPath(); //contextPath구하기 (실제로는 root를 구하는 것). uri의 제일 첫부분. uri-contextPath = 실제 요청한 거
		//uri = /notice/main.do
		//contextPath = /notice
		String page = uri.substring(contextPath.length());//요청한 페이지 구하기 = /main.do
		
		Command command = map.get(page);//수행할 command를 가져온다. 그래서 인터페이스를 씀. 동일한 이름으로 원하는 구현체 가져와서 수행가능. 인터페이스는 자기 자신을 인스턴스 생성못하고 구현체를 통해 초기화 함. new MainCommand()가 들어오게 됨.
		String viewPage = command.exec(request, response);//보여줄 page. 이제 사람들한테 보여줘야하므로 view reserve로 html.
		//viewPage에 main/main이 들어옴
		if(!viewPage.endsWith(".do")) { //돌아왔는데 결과가 .do가 아니면
			viewPage = "WEB-INF/views/" + viewPage + ".jsp";
		} else {
			response.sendRedirect(viewPage); //돌아온 결과가 *.do이면 위임해버린다. forward = sendRedirect 값을 안 싣고 다른 데로 넘겨버린다
		}//결과 response, 실행 request 결과를 넘겨버림
		
		//.do가 아니면 여기까지 오게됨
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //RequestDispatcher는 인터페이스라서 자식을 이용. 이 정보를 가지고 넘김
		dispatcher.forward(request, response); //디스패쳐를 이용해서 forward하면 request객체, response객체 가져가면 바로 들어갈 수 있음.(그냥 sendRed하면은 작성해서 들어가게 함)
		
	}

}
