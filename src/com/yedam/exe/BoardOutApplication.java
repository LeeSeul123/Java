package com.yedam.exe;

import java.util.Scanner;

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
				bs.searchBoard();
				break;
			case "4":
				bs.getMyreco();
				break;
			case "5":
				System.out.println("뒤로가기 실행");
				run = false;
				break;
			}
		}
	}

	private void menu() {
		System.out.println("=================================================================");
		System.out.println("1.전체 게시글 | 2.글쓰기 | 3.게시글 검색 | 4.내가 추천한글 보기 | 5. 뒤로가기");
		System.out.println("=================================================================");
		selectNo = sc.nextLine();
	}
}
