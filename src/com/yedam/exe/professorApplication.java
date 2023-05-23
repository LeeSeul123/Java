package com.yedam.exe;

import java.util.Scanner;

import com.yedam.member.MemberService;
import com.yedam.subject.SubjectService;

public class professorApplication {
	Scanner sc = new Scanner(System.in);
	String selectNo = "";
	SubjectService ss = new SubjectService();
	
	professorApplication(){
		start();
	}

	private void start() {
		boolean run = true;
		while(run) {
			menu();
			switch(selectNo) {
			case "1":
				//수업선택
				classMenu();
				
				ss.insertSubject();
				break;
			case "2":
				//수업취소
				ss.deleteSubject();
				break;
			case "3":
				//시간표 확인
				ss.getSchedule();
				break;
			case "4":
				//성적 입력
				ss.setGrade();
				break;
			case "5":
				//로그아웃
				MemberService.memberInfo = null;
				run = false;
				break;
			}
		}
	}

	private void menu() {
		System.out.println("===================================교수 메뉴====================================");
		System.out.println("   1.수업선택   |   2.수업취소   |   3.시간표 확인   |   4.성적입력   |   5.로그아웃   ");
		System.out.println("==============================================================================");
		selectNo = sc.nextLine();
	}
	
	private void classMenu() {
		System.out.println("===========================과목 종류=============================");
		System.out.println("1. korean : 월요일 11시 ~ 13시 | 2. math     : 월요일 12시 ~ 14시 ");
		System.out.println("3. english: 월요일 15시 ~ 17시 | 4. history  : 화요일 12시 ~ 14시 ");
		System.out.println("5. art    : 수요일 13시 ~ 17시 | 6. music    : 수요일 15시 ~ 17시 ");
		System.out.println("7. ethics : 목요일 11시 ~ 15시 | 8. biology  : 목요일 12시 ~ 13시 ");
		System.out.println("9. physics: 금요일 10시 ~ 12시 | 10.chemistry: 금요일 13시 ~ 17시 ");
		System.out.println("===============================================================");
	}
	
}
