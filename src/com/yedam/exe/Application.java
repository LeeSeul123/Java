package com.yedam.exe;

import java.util.Scanner;

import com.yedam.member.MemberService;

public class Application {
	Scanner sc = new Scanner(System.in);
	String selectNo = "";
	MemberService ms = new MemberService();
	
	Application(){
		start();
	}

	private void start() {
		
		while(selectNo != "3") {
			System.out.println("===============================");
			System.out.println("1. 로그인 | 2. 회원가입 | 3.종료");
			System.out.println("===============================");
			selectNo = sc.nextLine();
			switch(selectNo) {
			case "1":
				//로그인
				ms.login();
				if(MemberService.memberInfo.getMemberAuth().equals("S")) {
					//학생 메뉴
					new studentApplication();
				} else {
					//교수메뉴
					new professorApplication();
				}
				break;
			case "2":
				//회원가입
				ms.insertMember();
				break;
			case "3":
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("잘못된 번호 입력");
			}
		}
	}
	
	
}
