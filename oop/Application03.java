package com.yedam.oop;

public class Application03 {

	public static void main(String[] args) {
		Computer compute = new Computer();
		
		//1) 배열을 활용 (타입을 똑같이 맞춰야 한다)
		int[] values1 = {1,2,3};
		/* 1. */int result1 = compute.sum1(values1);
		System.out.println(result1);
		/* 2. */result1 = compute.sum1(new int[10]);
		
		/* 3. */result1 = compute.sum1(new int[] {1,2,3,4}); //변수에 담지않고 바로 넘겨주는 방식
		System.out.println(result1);
		
		//2) 목록을 넘겨주는 방식
		int result2 = compute.sum2(1,2,3);
		System.out.println(result2);
		int result3 = compute.sum2(1,2,3,4,5);
		System.out.println(result3);
		
		Calculator cal = new Calculator();
		cal.excute(); //장점 :  하나만 실행하면 다 실행됨
		
		//정사각형, 직사각형 오버로딩 예제
		double result4 = cal.areaRectangle(10.5);
		System.out.println(result4);
		
		double result5 = cal.areaRectangle(20.4, 10.2);
		System.out.println(result5);
	}

}
