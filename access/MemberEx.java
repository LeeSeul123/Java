package com.yedam.access;

public class MemberEx {

	public static void main(String[] args) {
		Member mb = new Member();
		
		
		//인스턴스 필드 -> 데이터 입력
		
		mb.setId("aaaa");
		mb.setPassword("bbbb");
		mb.setName("아무거나");
		mb.setAge(-5);
		
		//데이터 얻기 & 읽기
		
		System.out.println("아이디 : " + mb.getId());
		System.out.println("비밀번호 : " + mb.getPassword());
		System.out.println("이름 : " + mb.getName());
		System.out.println("나이 : " + mb.getAge());
	}

}
