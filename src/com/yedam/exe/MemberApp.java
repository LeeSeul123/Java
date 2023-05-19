package com.yedam.exe;

import java.util.Scanner;

public class MemberApp {
	//고객의 업무
	Scanner sc = new Scanner(System.in);
	public MemberApp() {
		memberRun();
	}
	
	private void memberRun() {
		//고객의 메뉴
		//-> 계좌 조회, 입출금(한번에 구현), 이체
		boolean flag = true;
		while(flag) {
			menu();
			String selectNo = sc.nextLine();
			switch(selectNo) {
			case "1":
				
				break;
			case "2":
				
				break;
			case "3":
				
				break;
			case "4":
				flag = false; //while문을 멈추면 원래 있던 생성자 자리로 돌아감(Application)
				break;
			}
		}
		
	}
	
	private void menu() {
		System.out.println("1. 계좌 조회 | 2. 입출금 | 3. 이체 | 4. 뒤로가기");
	}
}
