package com.yedam.exe;

import java.util.Scanner;

import com.yedam.member.MemberService;
import com.yedam.studentSubject.StudentService;

public class studentApplication {
	Scanner sc = new Scanner(System.in);
	StudentService studentService = new StudentService();
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
				studentService.getGradeList();
			} else if(selectNo.equals("2")) {
				classMenu();
				studentService.insertSubject();
			} else if(selectNo.equals("3")) {
				System.out.println("수강 취소할 과목번호 입력");
				classMenu();
				studentService.deleteSubject();
			} else if(selectNo.equals("4")) {
				
			} else if(selectNo.equals("5")) {
				MemberService.memberInfo = null;
				run = false;
			} else {
				System.out.println("번호 잘못 입력");
			}
		}
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
