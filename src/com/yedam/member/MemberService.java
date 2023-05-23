package com.yedam.member;

import java.util.Scanner;

public class MemberService {
	public static Member memberInfo = null; 
	Scanner sc = new Scanner(System.in);
	
	//로그인
	public void login() {
		System.out.println("아이디를 입력해주세요>");
		String memberId = sc.nextLine();
		Member member = MemberDAO.getInstance().login(memberId);
		if(member == null) {
			System.out.println("아이디가 틀렸습니다.");
		} else {
			System.out.println("비밀번호를 입력해주세요>");
			String memberPw = sc.nextLine();
			if(memberPw.equals(member.getMemberPw())) {
				System.out.println("로그인 성공");
				memberInfo = member;
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		}
	}
	
	//회원가입
	public void insertMember() {
		Member member = new Member();
		String who = "";
		while(!(who.equals("1") || who.equals("2"))) {
			System.out.println("교수님이면 1번, 학생이라면 2번을 눌러주세요");
			who = sc.nextLine();
		}
		
		System.out.println("아이디 입력>");
		member.setMemberId(sc.nextLine());
		
		Member member2 =  MemberDAO.getInstance().login(member.getMemberId());
		if(member2 != null) {
			System.out.println("이미 생성된 아이디 입니다.");
		} else {
			System.out.println("비밀번호 입력>");
			member.setMemberPw(sc.nextLine());
			
			int result = MemberDAO.getInstance().insertMember(member, who);
			
			if(result > 0) {
				System.out.println("회원 가입 성공 😀😀");
			} else {
				System.out.println("회원 가입 실패");
			}
		}
		
		
	}
	
	
}
