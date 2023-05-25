package com.yedam.oop;

public class StaticCal {
	//정적 필드
	static double pi = 3.14;
	
	//282p 공유상수
	
	static final int EARTH_RADIUS = 6400;
	static final double PI = 3.14;
	//정적 메소드
	static int plus ( int x, int y) {
		return x + y;
	}
	
	//정적 메소드
	static int minus (int x, int y) {
		return x - y;
	}
	
	public static void main(String[] args) {
		//클래스에 정의된 정적 멤버 사용
		//클래스명.필드명 | 클래스명.메소드명
		//정적 필드
		System.out.println(StaticCal.pi);
		
		//정적 메소드
		System.out.println(StaticCal.minus(10,5));
		System.out.println(StaticCal.minus(10,5));

		
		//공유상수 사용
		System.out.println("지구의 반지름 : " + StaticCal.EARTH_RADIUS );
		System.out.println("지구의 표면적 : " + StaticCal.pi * StaticCal.EARTH_RADIUS * StaticCal.EARTH_RADIUS);
	}
}
