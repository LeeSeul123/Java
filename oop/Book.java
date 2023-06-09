package com.yedam.oop;

public class Book {
	//Book이라는 클래스는 아래의 정보를 가진다.
	//책이름, 책종류, 가격, 도서번호, 출판사
	String bookName;
	String kind;
	String price;
	String isbn;
	String company;
	
	//생성자는 2개
	//기본 생성자 
	Book(){
		
	}
	//모든 데이터를 받아오는 생성자
	Book (String bookName, String kind, String price, String isbn, String company){
		this.bookName = bookName;
		this.kind = kind;
		this.price = price;
		this.isbn = isbn;
		this.company = company;
	}
	
	
	//메소드
	//모든 정보를 출력할 수 있는 getInfo()
	void getInfo() {
		System.out.println("책 이름 : " + bookName);
		System.out.println("#내용");
		System.out.println("1) 종류 : " + kind);
		System.out.println("2) 가격 : " + price);
		System.out.println("3) 출판사 : " + company);
		System.out.println("4) 도서번호 : " + isbn);
	}
	
	//객체 생성할 때, 생성자를 통한 필드 초기화
	//다음과 같은 출력물 나오도록 구현
	//객체.getInfo()하면 다음과 같은 내용이 나오도록한다.
	//책 이름 : 혼자 공부하는 자바
	//#내용
	//1) 종류 : 학습서
	//2) 가격 : 24000원
	//3) 출판사 : 한빛 미디어
	//4) 도서번호 : 0001
	
}
