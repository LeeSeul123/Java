package com.yedam.exe;

import java.util.List;
import java.util.Scanner;

import com.yedam.board.Board;
import com.yedam.board.BoardDAO;
import com.yedam.board.BoardService;

public class BoardOutApplication {
	Scanner sc = new Scanner(System.in);
	String selectNo = "";
	
	BoardService bs = new BoardService();
	
	BoardOutApplication(){
		start();
	}

	private void start() {
		boolean run = true;
		
		
		while(run) {
			menu();
			switch(selectNo) {
			case "1":
				new BoardInApplication();
				break;
			case "2":
				bs.writing();
				break;
			case "3":
				System.out.println("1. 제목 검색 | 2. 제목+내용 검색 | 3. 작성자 id");
				
				String part1 = "";
				while(!part1.equals("1") && !part1.equals("2") && !part1.equals("3")) {
					System.out.println("검색할 부분을 선택해주세요 >");
					part1 = sc.nextLine();
				}
				
				
				int part = Integer.parseInt(part1);
				System.out.println("검색 내용을 입력해주세요 > ");
				String content = sc.nextLine();
				
				List<Board> list = BoardDAO.getInstance().searchBoard(part, content);
				//게시글이 있는지 확인
				if(list.size() == 0) {
					System.out.println("게시글이 없습니다.");
				} else {
					//게시글이 있을 경우
					new SearchBoardApplication(part, content);
				}
				break;
			case "4":
				List<Board> list2 = BoardDAO.getInstance().getMyreco();
				if(list2 == null) {
					System.out.println("추천한 게시글이 없습니다.");
				} else {
					new RecommendationApplication();
				}
				//bs.getMyreco();
				break;
			case "5":
				//내가 쓴글/댓글삭제
				System.out.println("글삭제는 1번, 댓글 삭제는 2번, 대댓글 삭제는 3번");
				String select = sc.nextLine();
				if(select.equals("1")) {
					bs.deleteBoard();
				} else if(select.equals("2")) {
					bs.deleteReply();
				} else if(select.equals("3")){
					bs.deleteReReply();
				} else {
					System.out.println("잘못 된 값 입력");
				}
				break;
			case "6":
				System.out.println("뒤로가기 실행");
				run = false;
				break;
			default :
				System.out.println("잘못된 값 입력");
			}
		}
	}

	private void menu() {
		System.out.println("===========================================================================================================");
		System.out.println("1.전체 게시글 | 2.글쓰기 | 3.게시글 검색 | 4.내가 추천한글 보기 | 5.내가 쓴 글/댓글 삭제 | 6. 뒤로가기  ");
		System.out.println("===========================================================================================================");
		System.out.println("번호 입력>");
		selectNo = sc.nextLine();
	}
}
