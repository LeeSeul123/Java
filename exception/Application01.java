package com.yedam.exception;

public class Application01 {
	public static void main(String[] args) {
		
		//0516 1번
		
//		try {
//			//예외가 발생할만한 코드
//		} catch(예외 종류(nullponit, numberformat,,,)) {
//			//예외, 문제가 발생했을 때 처리하는 코드
//		} finally {
//			//try 또는 catch문 실행하고 나서 무조건!
//			//실행하는 코드
//			//필수는 아니고 선택
//		}
		
		try {
			//분모가 0일때 발생하는 오류
			double avg = 1/0;
			System.out.println(avg);
			
			//numberformat
			String str = "자바";
			int a = Integer.parseInt(str);
			
			//클래스 찾는 것. ClassNotFound
			Class clazz = Class.forName("java.lang.String2");
		} catch(ArithmeticException e) {
			System.out.println("숫자를 0으로 나눠서 예외 발생");
			e.printStackTrace();
		} catch(NumberFormatException e) {
			System.out.println("문자열 -> 정수로 변환 실패");
			//e.printStackTrace();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("finally 항상 실행");
		}
		
		//public static void findClass() throws ClassNotFoundException
		//예외 던지는 메소드 처리할 시
		try {
			findClass();
		}catch(ClassNotFoundException e) {
			System.out.println("클래스가 존재하지 않습니다.");
			//e.printStackTrace();
		}
		//예외 던지는 메소드 처리안하고 호출만 할 시
		//findClass();
		
		
	}
	
	public static void findClass() throws ClassNotFoundException {
		Class clazz = Class.forName("java.lang.String2");
	}
}
