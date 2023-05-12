package com.yedam.array;

import java.util.Arrays;

public class Array03 {

	public static void main(String[] args) {
		//2차원 배열
		//계단식 배열
		int[][] intAry = { {1,2}, {1,2,3} };
		
		// intAry[0] -> 1, 2
		// intAry[1] -> 1, 2, 3
		
		int[][] mathScore = new int[2][3]; //큰배열 안에 배열이 2개 있음
		
		for(int i = 0; i<mathScore.length; i++) { //mathScore의 길이는 2를 가리킴
			System.out.println("mathScore length: " + mathScore.length);
			for(int j=0; j<mathScore[i].length; j++) {
				System.out.println("mathScore[" + i +"][" + j + "] = " + mathScore[i][j]+ " ");
			}
			System.out.println();
		}
		
		//이차원 배열 -> 구구단 저장
		//2단, 3단, 4단....9단 => 2,3,4,5,6,7,8,9 데이터 8개
		//1,2,3,..........9 => 9개
		//{ {2,4,6,...,16,18},{3단},{4단}...{8단},{9단} }
		
		int[][] gugu = new int[8][9];
		for(int i=2; i<10; i++) {
			//곱셈하는 구간
			for(int j=1; j<10; j++) {
				//gugu[0][0], i = 2 j = 1 (노가다로 규칙찾기)
				gugu[i-2][j-1] = i * j;
			}
		}
		
		for(int i=0; i<gugu.length; i++) {
			System.out.print(i+2 + "단 : " );
			System.out.println(Arrays.toString(gugu[i]));
		}
		
		
		
		
		
		
		//배열에 저장된 내용 출력(구구단)
		for(int i=0; i<gugu.length; i++) {
			System.out.print((i+2) + "단 : ");
			//Arrays.toString() : 배열의 정보를 출력하는 메소드
			System.out.println(Arrays.toString(gugu[i]));
			System.out.println();
		}
		
		//배열을 다룰 때 많이 나는 오류 : java.lang.ArrayIndexOutOfBoundsException
		//배열의 범위를 벗어나는 인덱스 사용 
		int[] intAry2 = {1,2,3,4};
		//System.out.println(intAry2[4]); index의 4는 벗어나는 범위다. 없는 인덱스에서 데이터를 가져오려고한다.
		
		//참조 타입 배열
		String[] strAry = new String[3];
		//String 변수명 = strAry[0] = "yedam";
		strAry[0] = "yedam";
		strAry[1] = "yedam";
		strAry[2] = new String("yedam");
		
		//String 배열간의 주소값 비교
		System.out.println(strAry[0] == strAry[1]);
		System.out.println(strAry[0] == strAry[2]);
		//String 배열간 데이터 비교
		System.out.println(strAry[0].equals(strAry[2]));
		
		//배열 복사
		int[] oldAry = {1,2,3};
		int[] newAry = new int[5];
		System.out.println("===배열 복사===");
		for(int i=0; i<oldAry.length; i++) {
			newAry[i] = oldAry[i];
		}
		
		for(int i =0; i<newAry.length; i++) {
			System.out.println(newAry[i]);
		}
		
		//arrayCopy
		int[] oldAry2 = {1,2,3,4,5,6,7};
		int[] newAry2 = new int[10];
		
		System.arraycopy(oldAry2, 1, newAry2, 1, oldAry2.length-1);
		System.out.println("배열복사");
		for(int i=0;i<newAry2.length; i++) {
			System.out.println(newAry2[i]);
		}
		
		//향상된 for문
		for(int temp : newAry2) {
			System.out.print(temp + "\t");
		}
		
		
		
		
		
		
		
		
		
		
	}

}
