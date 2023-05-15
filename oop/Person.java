package com.yedam.oop;

public class Person {
	//final 실습(280쪽)

	//필드
	final String nation = "대한민국"; //final 필드 초기화 방식 1번
	final String ssn;
	String name;
	//생성자
	Person(String ssn, String name){
		this.ssn = ssn;
		this.name = name;
	}
	//메소드
	
	
	//실행
	public static void main(String[] args) {
		Person p1 = new Person("111111-1111111", "김또치"); //final 필드 초기화 방식 2번
		
		System.out.println(p1.nation);
		System.out.println(p1.ssn);
		System.out.println(p1.name);
		
	  //p1.ssn = "222222-2222222";  final이라서 변경 시 에러
	//  p1.nation = "미국";	
	}
}
