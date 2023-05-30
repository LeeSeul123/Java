package com.yedam.exe;

import java.util.Scanner;

import com.yedam.board.BoardService;

public class SearchBoardApplication {
	Scanner sc = new Scanner(System.in);
	BoardService bs = new BoardService();
	
	int part;
	String content;
	SearchBoardApplication(int part, String content){
		this.part = part;
		this.content = content;
		start();
	}

	private void start() {

		
		
		
		boolean run = true;
		while(run) {
			//게시글 출력
			int lastPage = 0;
			if(part == 1) {
				lastPage = bs.getLastPageTitle(content);
			} else if(part == 2) {
				lastPage = bs.getLastPageTitleContent(content);
			} else if(part == 3) {
				lastPage = bs.getLastPageWriterId(content);
			}
			bs.createBoard(part, content);
			//검색 결과
			
			//선택메뉴 1.조회할 게시글넘버 입력 2.뒤로가기
			if(BoardService.currentPage ==0 && BoardService.currentPage+1 == lastPage) {
				System.out.println("1.게시글 조회 | 4.뒤로가기");
			} else if(BoardService.currentPage == 0) {
				System.out.println("1.게시글 조회 | 2.다음 페이지 | 4.뒤로가기");
			} else if(BoardService.currentPage+1 == lastPage) {
				System.out.println("1.게시글 조회 | 3.이전 페이지 | 4.뒤로가기");
			} else {
				System.out.println("1.게시글 조회 | 2.다음 페이지 | 3.이전 페이지 | 4.뒤로가기");				
			}
			String selectNo = sc.nextLine();
			switch(selectNo) {
			case "1":
				//게시글 조회
				System.out.println("조회할 게시글의 id를 입력해주세요.");
				//만약 그 번호가 아니라면 안되는 문구도 추가
				String boardNum = sc.nextLine();
				new BoardDetailApplication(boardNum);
				
				break;
			case "2":
				if(BoardService.currentPage+1 == lastPage) {
					System.out.println("마지막 페이지라서 이동할 수 없습니다.");
				} else {
					BoardService.currentPage = BoardService.currentPage+1;
				}
				break;
			case "3":
				if(BoardService.currentPage == 0) {
					System.out.println("첫 페이지라서 이동할 수 없습니다.");
				} else {
					BoardService.currentPage = BoardService.currentPage - 1;
				}
				break;
			case "4":
				System.out.println("뒤로가기 실행");
				run = false;
				BoardService.currentPage = 0;
				break;
			}
			
		}
	}

	
	
	
	
	
}
