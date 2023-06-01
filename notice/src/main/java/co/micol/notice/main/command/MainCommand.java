package co.micol.notice.main.command;
//main과 관련된 command들을 다 여기에 작성하겠다
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.notice.common.Command;

public class MainCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// main.jsp를 돌려주는 곳(main.do가 들어왔으니 main.jsp를 돌려줌)
		return "main/main"; //main밑에 jsp(jsp안붙임) 돌려줄 view reserve를 만듦. main 밑에 main
	}

}
