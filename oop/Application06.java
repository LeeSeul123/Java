package com.yedam.oop;

import java.util.Scanner;

public class Application06 {
	public static void main(String[] args) {
		
		//학생의 정보를 관리하는 프로그램
	
		//학생의 정보를 관리하는 객체
		//이름, 학년, 국어, 영어, 수학 점수 가짐
		
		//기본 생성자만 가짐
		
		//메소드
		//모든 정보를 출력 getInfo()
	
		//1. 학생수
		//2. 정보 입력
		//3. 정보 확인
		//4. 분석 - 전체 학생의 점수를 총합(국+영+수)
		//       - 총합의 평균
		//       - 개인별 가장 점수가 높은 과목 / 낮은 과목
		//5. 종료
		
		//학생의 정보를 보관하는 배열
		Student[] studentArray = null;
		
		//학생의 정보를 몇명 보관하는 변수
		int studentNumber = 0;
		
		//데이터 입력을 받을 수 있는 스캐너
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("===============================================");
			System.out.println("1.학생수 | 2.정보 입력 | 3.정보 확인 | 4.분석 | 5.종료");
			System.out.println("===============================================");
			
			String push = sc.nextLine();
			
			if(push.equals("1")) {
				System.out.println("학생 수 입력>");
				studentNumber = Integer.parseInt(sc.nextLine());
			} else if(push.equals("2")) {
				studentArray = new Student[studentNumber];
				
				for(int i=0; i<studentArray.length; i++) { //studentNumber도 가능
					
					//배열에 바로 객체를 만듬.
					studentArray[i] = new Student();
					System.out.println("이름>");
					studentArray[i].name = sc.nextLine();
					System.out.println("학년>");
					studentArray[i].year = Integer.parseInt(sc.nextLine());
					System.out.println("국어");
					studentArray[i].kor = Integer.parseInt(sc.nextLine());
					System.out.println("영어"); 
					studentArray[i].eng = Integer.parseInt(sc.nextLine());
					System.out.println("수학");
					studentArray[i].math = Integer.parseInt(sc.nextLine());
					
					
//					Student student = new Student();
//					System.out.println("이름>");
//					student.name = sc.nextLine();
//					System.out.println("학년>");
//					student.year = Integer.parseInt(sc.nextLine());
//					System.out.println("국어");
//					student.kor = Integer.parseInt(sc.nextLine());
//					System.out.println("영어"); 
//					student.eng = Integer.parseInt(sc.nextLine());
//					System.out.println("수학");
//					student.math = Integer.parseInt(sc.nextLine());
//					studentArray[i] = student;
					
					
				}
			} else if(push.equals("3")) {
				for(int i=0; i<studentArray.length; i++) {
					System.out.print((i+1)+ "번째 학생 정보>");
					studentArray[i].getInfo();
					System.out.println();
				}
					
			} else if(push.equals("4")) {
//				int total = 0;
//				double avg = 0;
//				int max = 0;
//				int min = 0;
//				for(int i=0; i<studentArray.length; i++) {
//					//반복문 돌때마다 누적 합계
//					total = studentArray[i].kor + studentArray[i].eng + studentArray[i].math;
//					
//					max = studentArray[i].kor;
//					min = studentArray[i].kor;
//					
//					if(studentArray[i].eng < studentArray[i].math) {
//						if(max < studentArray[i].math) {
//							max = studentArray[i].math;
//						}	
//					} else {
//						if(max < studentArray[i].eng) {
//							max = studentArray[i].eng;
//						}
//							
//					}	
//				}
				int sumsum = 0;
				int sum = 0;
				int max = 0;
				int min = 0;
				for(int i=0; i<studentArray.length; i++) {
					sum = studentArray[i].kor + studentArray[i].eng + studentArray[i].math;
					min = studentArray[i].kor < (studentArray[i].eng < studentArray[i].math ? studentArray[i].eng : studentArray[i].math) ? studentArray[i].kor : (studentArray[i].eng < studentArray[i].math ? studentArray[i].eng : studentArray[i].math);
					max = studentArray[i].kor > (studentArray[i].eng > studentArray[i].math ? studentArray[i].eng : studentArray[i].math) ? studentArray[i].kor : (studentArray[i].eng > studentArray[i].math ? studentArray[i].eng : studentArray[i].math);
					sumsum +=sum;
					System.out.println(studentArray[i].name + "의 점수 총합>" +  sum);
					System.out.println(studentArray[i].name + "의 평균>" + (double)sum/3);
					System.out.println(studentArray[i].name + "의 최고점수>" + max + " / 최저점수>" + min);
					
				}
				System.out.println("전체 학생의 총점>" + sumsum);
				System.out.println("전체 학생의 평균>" + (double)sumsum/(studentArray.length*3));
			} else if(push.equals("5")) {
				System.out.println("프로그램 종료");
				break;
			} else {
				System.out.println("없는 번호 입력!!");
			}
		}
	}
}
