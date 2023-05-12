package com.yedam.reference;

public class Reference {

	public static void main(String[] args) {
		//참조 : 배열
		//자바에서 배열을 선언하는 방식
		int[] array = {1,2,3,4,5,6};
		int[] array2 = {1,2,3,4,5,6};
		int[] array3 = null;
		
		System.out.println(array); //array변수 안에 저장된 주소. 자바스크립트에서는 값이 나옴
		//데이터가 똑같아도 살고 있는 곳이 다름
		System.out.println(array2);
		System.out.println(array == array2);
		
		System.out.println(array[0]);
//		System.out.println(array3[0]);//참조할 수 있는 게 없으면 데이터를 가져올 수 없음 = NullPointerException.
		
		//객체 null 체크 -> nullpointerException예방
		System.out.println(array3 == null);
		if(array3 == null) {
			//array3이 null 경우
		} else {
			//array3에 객체가 있는 경우
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
