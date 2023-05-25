package com.yedam.access;

public class Application01 {
	public static void main(String[] args) {
		//패키지가 다르면 같은 파일명을 쓸 수 있다는 확인용

		//292페이지
		//Access에서 만든 변수 사용
		Access ac = new Access();
		ac.free = "public";
		ac.parent = "protected";
		ac.basic = "default";
//		ac.privacy = "private";
		
		//Access에서 만든 메소드 사용
		ac.instead();
	}
	
	
}
