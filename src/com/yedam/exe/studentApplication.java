package com.yedam.exe;

import java.util.Scanner;

import com.yedam.member.MemberService;

public class studentApplication {
	Scanner sc = new Scanner(System.in);
	
	studentApplication(){
		start();
	}

	private void start() {
		boolean run = true;
		while(run) {
			System.out.println("=========================학생메뉴============================");
			System.out.println("1.내 성적 조회 | 2.수강신청 | 3.수강취소 | 4.학교 게시판 | 5.로그아웃");
			System.out.println("===========================================================");
			String selectNo = sc.nextLine();
			
			if(selectNo.equals("1")) {
				
			} else if(selectNo.equals("2")) {
				
			} else if(selectNo.equals("3")) {
				
			} else if(selectNo.equals("4")) {
				
			} else if(selectNo.equals("5")) {
				MemberService.memberInfo = null;
				run = false;
			} else {
				System.out.println("번호 잘못 입력");
			}
		}
	}
	
	
}
