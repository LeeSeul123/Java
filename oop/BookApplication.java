package com.yedam.oop;

public class BookApplication {

	public static void main(String[] args) {
		Book book1 = new Book("혼자 공부하는 자바", "학습서", "24000원" , "0001", "한빛 미디어");
		book1.getInfo();
		
		Book book2 = new Book("자바스크립트", "학습서", "15000원", "0002", "어포스트");
		book2.getInfo();
	}

}
