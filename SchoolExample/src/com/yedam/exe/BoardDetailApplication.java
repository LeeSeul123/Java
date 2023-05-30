package com.yedam.exe;

import java.util.Scanner;

import com.yedam.board.Board;
import com.yedam.board.BoardDAO;
import com.yedam.board.BoardService;

public class BoardDetailApplication {
	int selectNo = 0;
	Scanner sc= new Scanner(System.in);
	BoardService bs = new BoardService();
	int boardNum = 0;
	
	BoardDetailApplication(String boardNum){
		this.boardNum = Integer.parseInt(boardNum);
		start();
	}
	
	BoardDetailApplication(int selectNo){
		this.selectNo = selectNo;
		start();
	}

	private void start() {
		boolean run = true;
		while(run) {
			if(selectNo == 0) {
				
				bs.getPost2(boardNum);
			} else {
				Board board = BoardDAO.getInstance().checkPost(selectNo);
				if(board == null) {
					System.out.println("해당 글 없음");
					this.selectNo = 0;
					this.boardNum = 0;
					BoardService.currentBoard = null;
					run = false;
					return;
				} else {
					
					bs.getPost(selectNo);
				}
			}
			
			bs.getReply();
			if(bs.recoCheck()) {
				System.out.println("1.댓글 입력 | 2.대댓글 입력 | 3.추천 누르기 | 4. 뒤로가기");
			} else {
				System.out.println("1.댓글 입력 | 2.대댓글 입력 | 3.추천 취소 | 4. 뒤로가기");
			}
			
			String select = sc.nextLine();
			
			switch(select) {
			case "1":
				//댓글 입력
				bs.insertReply();
				break;
			case "2":
				//대댓글 입력
				bs.insertReReply();
				break;
			case "3":
				//추천 누르기
				
				if(bs.recoCheck()) {
					bs.setReco();
				} else {
					bs.deleteReco();
				}
				break;
			case "4":
				//뒤로가기
				run = false;
				this.selectNo = 0;
				this.boardNum = 0;
				BoardService.currentBoard = null;
				System.out.println("뒤로가기 실행");
				break;
			default :
				System.out.println("잘못된 번호를 입력하셨습니다.");
				break;
			}
		}
	}
}
