package com.yedam.oop;

public class Student {
	//학생의 정보를 관리하는 프로그램
	
			//학생의 정보를 관리하는 객체
			//이름, 학년, 국어, 영어, 수학 점수 가짐
			
			//기본 생성자만 가짐
			
			//메소드
			//모든 정보를 출력 getInfo()
	
	//필드
	String name;
	int year;
	int kor;
	int eng;
	int math;
	
	//생성자
	Student(){
		
	}
	//메소드
	void getInfo() {
		System.out.println("이름 : " + name);
		System.out.println("학년 : " + year);
		System.out.println("국어 : " + kor);
		System.out.println("영어 : " + eng);
		System.out.println("수학 : " + math);
	}
}
