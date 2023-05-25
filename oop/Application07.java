package com.yedam.oop;

import com.yedam.access.Access;

public class Application07 {
	//필드 선언
	static int num = 1;
	
	int num1 = 1;
	
	//메소드 선언
	static void info() {
		System.out.println("info출력");
	}
	//정적 메소드에서 사용해보기
	public static void main(String[] args) {
		int a = num + 1;
		
		info();
		
		//자기 자신을 객체로 만든 예제 -> 절대로 하지 말 것.
		Application07 app = new Application07();
		int b = app.num1 + 1;
		
		//293페이지 접근제한자 확인
		Access ac = new Access(); //다른 패키지이므로 ctrl + shift + O
		ac.free = "public";
//		ac.parent = "protected";
//		ac.basic = "default";
//		ac.privacy = "private";
	}
	
	
	
}
