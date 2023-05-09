package com.yedam.condition;

import java.util.Scanner;

public class SwitchCondition {
	public static void main(String[] args) {
		int number = (int)(Math.random()*6)+1;
		
		switch(number) {
		case 1 :
			System.out.println("주사위 번호 1");
			break;
		case 2 :
			System.out.println("주사위 번호 2");
			break;
		case 3 :
			System.out.println("주사위 번호 3");
			break;
		case 4 :
			System.out.println("주사위 번호 4");
			break;
		case 5 :
			System.out.println("주사위 번호 5");
			break;
		default :
			System.out.println("주사위 번호 6");
		}
		
		//char
		char grade = 'B';
		String grade2 = "b"; //string은 equals로 비교하긴 하지만 그냥 넣어도 됨
		
		switch(grade2) {
		case "A":
			System.out.println("우수 회원");
			break;
		case "B":
		case "b":     //B가 대문자, 소문자 상관없이 만족할 때. 이게 가능한 이유는 break가 없기 때문
			System.out.println("일반 회원");
			break;
		default:
			System.out.println("손님");
		}
		
		//입력한 성적을 등급으로 표현
		Scanner sc = new Scanner(System.in);
		
		System.out.println("성적입력>");
		int scores = Integer.parseInt(sc.nextLine());
		//주 : switch 부 : if
		//0~100
		//90 이상은 A -> 95 이상이면 A+ 90 이하면 A
		//80 이상은 B -> 85~90 B+, 80~84 B
		//70 이상은 C 
		//그 이하는 D
		
		switch(scores/10) {
		case 10:
		case 9:
			if(scores>=95) {
				System.out.println("획득 학점 A+");
			} else {
				System.out.println("획득 학점 A");
			}
			break;
		case 8:
			if(scores>=85) {
				System.out.println("획득 학점 B+");
			} else {
				System.out.println("획득 학점 B");
			}
			break;
		case 7:
			if(scores>=75) {
				System.out.println("획득 학점 C+");
			} else {
				System.out.println("획득 학점 C");
			}
			break;
		default :
			System.out.println("획득 학점 D");
		}
		
		//switch문은 메뉴 선택에 자주 사용됨(if문보다 편해서)
		System.out.println("1. 글 보기 2. 글 수정 3. 글 삭제 4. 뒤로 가기");
		int selectNo = Integer.parseInt(sc.nextLine());
		
		switch(selectNo) {
		case 1:
			System.out.println("1번 선택");
			System.out.println("글 보는 기능");
			break;
		case 2:
			System.out.println("2번 선택");
			System.out.println("글 수정하는 기능");
			break;
		case 3:
			System.out.println("3번 선택");
			System.out.println("글 삭제하는 기능");
			break;
		case 4:
			System.out.println("4번 선택");
			System.out.println("뒤로가는 기능");
			break;
		}
		
		//if문으로 하려면 지저분해서 안하는게 나음
		//if(selectNo ==1) else if 머시기
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
