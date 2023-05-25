package com.yedam.access;

public class Application03 {

	public static void main(String[] args) {
		//singleton 279페이지
		
		//하나의 객체를 외부로 전달
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		//진짜 같은지 확인
		System.out.println(s1 == s2);
		
		s1.age = 100;
		s1.name = "고길동";
		
		System.out.println(s2.age);
		System.out.println(s2.name);
		
		Member member = new Member();
		
		member.instance();
	}

}
