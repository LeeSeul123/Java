package com.yedam.condition;

import java.util.Scanner;

public class IfCondition {

	public static void main(String[] args) {
		int score = 93;
		
		//if문 한개
		if(score >= 90) {
			System.out.println("점수가 90 이상이고 등급 A");
		}
		
		//if문 실행문 한줄
		if(score > 90) 
			System.out.println("점수가 90 이상이고 등급 A");
			System.out.println("스코어 90");
			
		if(score >= 90) {
			System.out.println("등급 A");
		}
		
		if(score >= 90) {
			System.out.println("등급 A");
		} else if (score >= 80) { //여기 왔다는 건 맨 처음 조건을 만족 못했다는 뜻. score<90도 넣어서 비교를 두번하면 시간이 더 걸림
			System.out.println("등급 B");
		} else if (score >= 70) { //여기 왔다는 건 1,2 조건 만족 못했다는 뜻
			System.out.println("등급 C");
		} else {
			System.out.println("등급 D");
		}
		
		if(score < 70) { //값의 범위 : 70미만
			System.out.println("등급 D");
		} else if (score < 80) { //값의 범위 : 70이상 80미만
			System.out.println("등급 C");
		} else if (score < 90) {
			System.out.println("등급 B");
		} else {
			System.out.println("등급 A");
		}
		
		//if문 활용해서 주사위값
		//random() 값 추출
		//Random() -> 헷갈림 / Math.random() -> 더 쉬움
		//Random(Seed) : 매개변수로 씨앗 받음. 1을 넣었을 떄 랜덤값과 2를 넣었을 때 랜덤값과 3을 넣었을 때 랜덤값이 다름.
		//Math.random() -> 실수
		// 0 <= Math.random() < 1 
		System.out.println(Math.random());
		// 1부터 10 사이의 데이터를 랜덤 추출
		// 0 * 10 <= Math.random() * 10 < 1 * 10 : 0 <= x < 1 
		// 0 ~ 9.xxxx  ,, double -> int 소수점 삭제
		// (int) 0 <= (int) Math.random() * 10 < (int) 10
		// (int) 0 + 1 <= (int) (Math.random() * 10) +1 < (int) 10 + 1
		// (int) 1 ~ (int) 11
		System.out.println((int)Math.random()*10 +1);
		//*10 -> 값 몇개 뽑을건지 (최대-최소+1)
		//+1 -> 초기값
		
		//주사위 번호 뽑기(1~6)
		// 0 <= x < 1
		// 0 * 6 < x * 6 < 1 * 6
		// (int) 0 <= (int) x * 6 < (int) 6
		// (int) 0 +1 <= (int) x * 6 + 1 < (int) 7
		
		int number = (int)(Math.random() * 6) + 1;
		if(number == 1) {
			System.out.println("주사위 눈 1");
		} else if (number == 2) {
			System.out.println("주사위 눈 2");
		} else if (number == 3) {
			System.out.println("주사위 눈 3");
		} else if (number == 4) {
			System.out.println("주사위 눈 4");
		} else if (number == 5) {
			System.out.println("주사위 눈 5");
		} else { 
			System.out.println("주사위 눈 6");
		}
		
		//중첩 if문
		//하나의 데이터를 여러번 조건을 확인해야할 때
//		if(조건식) {
//			실행문
//			if(조건식) {
//				실행문
//				if(조건식) {
//					실행문
//				}
//			}
//		}
		
		//가지고 있는 값이 2의 배수인지 5의 배수인지 확인
		int no = 10;
		if(no % 2 == 0) {
			if(no % 5 ==0) {
				if(no % 10 ==0) {
					System.out.println("2의 배수, 5의 배수, 10의 배수");
				}
			}
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("점수 입력>");
		
		score = Integer.parseInt(sc.nextLine()); //80도 하나의 문자열로 볼 수 있음
		
		String grade= "";
		
		if(score >= 90) {
			if(score >= 95) {
				grade = "A+";
			} else {
				grade = "A";
			}
		}
		System.out.println("획득한 학점 : " + grade);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
