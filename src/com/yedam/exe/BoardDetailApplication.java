package com.yedam.exe;

import java.util.Scanner;

import com.yedam.board.BoardService;

public class BoardDetailApplication {
	int selectNo;
	Scanner sc= new Scanner(System.in);
	BoardService bs = new BoardService();
	
	BoardDetailApplication(int selectNo){
		this.selectNo = selectNo;
		start();
	}

	private void start() {
		boolean run = true;
		while(run) {
			bs.getPost(selectNo);
			bs.getReply(selectNo);
			System.out.println("1.댓글 입력 | 2.대댓글 입력 | 3.추천 누르기 | 4. 뒤로가기"); //시간 있으면 댓글 입력 및 수정
			String select = sc.nextLine();
			
			switch(select) {
			case "1":
				//댓글 입력
				bs.insertReply(selectNo);
				break;
			case "2":
				//대댓글 입력
				bs.insertReReply();
				break;
			case "3":
				//추천 누르기
				bs.setReco(selectNo);
				break;
			case "4":
				//뒤로가기
				run = false;
				System.out.println("뒤로가기 실행");
				break;
			default :
				System.out.println("잘못된 번호를 입력하셨습니다.");
				break;
			}
		}
	}
}
