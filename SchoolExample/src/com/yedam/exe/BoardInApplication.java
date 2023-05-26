package com.yedam.exe;

import java.util.Scanner;

import com.yedam.board.BoardService;

public class BoardInApplication {
	Scanner sc = new Scanner(System.in);
	BoardService bs = new BoardService();
	int selectNo = 0;
	public static int pageSize = 0;
	
	
	BoardInApplication(){
		start();
	}

	private void start() {
		boolean run = true;
		System.out.println("한 페이지 당 보일 게시글 수를 설정해주세요 > ");
		pageSize = Integer.parseInt(sc.nextLine());
		while(run) {
			bs.getStartTle();
			bs.getAllBoard();
			
			bs.getEndTle();
			int lastPage = bs.getLastPage();
			if(BoardService.currentPage == 0 && BoardService.currentPage+1 == lastPage) {
				System.out.println("게시글 순서번호를 누르면 이동합니다. " + (pageSize+3) + " = 뒤로가기");
			} else if(BoardService.currentPage == 0) {
				System.out.println("게시글 순서번호를 누르면 이동합니다. " + (pageSize+2)  + "= 다음페이지 / " + (pageSize+3) + " = 뒤로가기");
			} else if(BoardService.currentPage+1 == lastPage) {
				System.out.println("게시글 순서번호를 누르면 이동합니다. " + (pageSize+1) + " = 전 페이지 / "  + (pageSize+3) + " = 뒤로가기");
			} else {
				System.out.println("게시글 순서번호를 누르면 이동합니다. " + (pageSize+1) + " = 전 페이지 / " + (pageSize+2)  + "= 다음페이지 / " + (pageSize+3) + " = 뒤로가기");
			}
			selectNo = Integer.parseInt(sc.nextLine());
			if(selectNo <= BoardInApplication.pageSize && selectNo >=1) {
				for(int i=0; i<=BoardInApplication.pageSize; i++) {
					if(i == selectNo) {
						//해당 게시글로 이동하는 메소드
						
						new BoardDetailApplication(selectNo);
						
					}
				}
			} else if(selectNo == BoardInApplication.pageSize+1) {
				//전 페이지로
				if(BoardService.currentPage == 0) {
					System.out.println("첫 페이지라서 이동이 불가능합니다.");
				} else {
					BoardService.currentPage = BoardService.currentPage-1;
				}
			} else if(selectNo == BoardInApplication.pageSize+2) {
				//다음 페이지로
				int lastPage1 = bs.getLastPage();
				if(BoardService.currentPage + 1 == lastPage1) {
					System.out.println("마지막 페이지라서 이동이 불가능합니다.");
				} else {
					BoardService.currentPage = BoardService.currentPage+1;
				}
				
			} else if(selectNo == BoardInApplication.pageSize+3) {
				//뒤로가기
				pageSize = 0;
				BoardService.currentPage = 0;
				System.out.println("뒤로가기 실행");
				run = false;
			} else {
				System.out.println("잘못된 숫자 입력");
			}
			
			
			
			
			//rownum +1 -> 전 페이지로 rownum +2 -> 다음페이지로 rownum +3 -> 종료
		}
	}
	
	
	
	
}
