package com.yedam.inheri;

public class Application02 {

	public static void main(String[] args) {
		//0515 6번
		
		Student std = new Student("또치", "111-111", 1);
	
		//부모 클래스(People에게 상속 받은 필드)
		System.out.println("name : " + std.name);
		System.out.println("ssn : " + std.ssn);
		
		//자식 객체의 필드
		System.out.println("stdNo : " + std.stdNo);
	}

}
