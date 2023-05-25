package com.yedam.oop;

public class Korean {
	//필드
	String nation = "대한민국";
	String name;
	String ssn;
	//생성자
	//1.이해 쉬운버전
//	Korean(String n, String s){
//		name = n;
//		ssn = s;
//	}
	//2.관례적인 버전
	Korean(String name, String ssn){
		this.name = name;
		this.ssn = ssn;
	}
	//메소드
}
