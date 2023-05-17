package com.yedam.API;

public class MathExample {

	public static void main(String[] args) {
		//0517 3번
		
		//절대값
		int v1 = Math.abs(-1);
		System.out.println(v1);
		double v2 = Math.abs(-3.1);
		System.out.println(v2);
		
		//올림값
		double v3 = Math.ceil(5.3);
		System.out.println(v3);
		double v4 = Math.ceil(-5.3);
		System.out.println(v4);
		
		//내림값
		double v5 = Math.floor(5.3);
		System.out.println(v5);
		double v6 = Math.floor(-5.3);
		System.out.println(v6);
		
		//최대값, 최소값
		int v7 = Math.max(5, 9);
		System.out.println(v7);
		
		int v8 = Math.min(5, 9);
		System.out.println(v8);
		
		//랜덤값
		double v9 = Math.random();
		System.out.println(v9);
		
		//rint
		double v10 = Math.rint(8.5); //0.5 붙을 경우 짝수 내림
		System.out.println(v10);
		
		double v11 = Math.rint(7.5); //0.5 붙을 경우 홀수 올림
		System.out.println(v11);
		
		double v12 = Math.rint(3.5);
		System.out.println(v12);
		
		//반올림
		double v13 = Math.round(5.3);
		System.out.println(v13);
	}

}
