package com.yedam.print;

import java.io.IOException;
import java.util.Scanner;

public class KeyCode {
	public static void main(String[] args) throws IOException{
		//KeyCode  ->  하나의 문자만 받아 올 때(초과시 에러는 안나지만 값이 다 안나옴)
		System.out.println("입력>");
		
		int keyCode = 0;
		
		keyCode = System.in.read();//값을 입력했을 때 생각지도 못한 이상한 값(예외처리 필요)
		System.out.println("KeyCode : " + keyCode);
		
		keyCode = System.in.read();//
		System.out.println("KeyCode : " + keyCode);
		
		keyCode = System.in.read();
		System.out.println("KeyCode : " + keyCode);
		
		//Scanner
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("입력>");
		
		//문자열 읽기
		//nextLine
		//=> enter키 이전까지 데이터를 받아 옴.(엔터키는 인식X. nextInt,float 등등 같은 경우데이터는 변수에 들어가서 사라지고 엔터키는 콘솔창에 유지)
		//=> enter키 기준으로 데이터를 읽어 옴.(위의 결과로 빈데이터 입력됨)
		String inputData = scanner.nextLine(); //scanner의 기능을 담아놔서 함수를 쓸 수 있음
		
		//정수 읽기
		int data = scanner.nextInt();
		//엔터키 소멸시키기용(마지막단계)
		scanner.nextLine(); 
		
		//11치고 엔터키치니까 공백을 넣어버린것.(마지막에서 두번째)
		//해결: nextLine만 쓰던지 nextInt같은거 써준뒤 scanner.nextLine();해서 엔터키 소멸시켜줌
		inputData = scanner.nextLine();
		
		System.out.println("Scanner 활용 => " + inputData);
		
		//데이터 비교 -> 입력한 값 == 저장된 값 비교
		
		//기본 타입(정수, 실수 비교 ==) ->자바스크립트는 모든 데이터를 ==으로 비교
		//문자열 -> equals
		if(inputData.equals("yedam")) {
			System.out.println("yedam과 일치합니다.");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
