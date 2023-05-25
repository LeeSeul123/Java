package com.yedam.API;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class StringExample {

	public static void main(String[] args) {
		
		//0517 1번
		
		//String API
		
		//byte[] -> String
		byte[] bytes = {72, 101, 108, 108, 111, 32, 74, 97, 118, 97};
		
		String str1 = new String(bytes);
		System.out.println(str1);
		
		//byte[] -> String(위치를 지정), 인덱스 6의 자리에서 4개
		String str2 = new String(bytes, 6, 4);
		System.out.println(str2);
		
		//charAt()
		//인덱스 값(문자 위치)를 입력해서 해당 위치에 있는 문자 읽기
		String ssn = "010654-157984";
		char gender = ssn.charAt(7);
		System.out.println(gender);
		switch(gender) {
		case '1':
		case '3':
			System.out.println("남자");
			break;
		case '2':
		case '4':
			System.out.println("여자");
			break;
		}
		
		//equals() 문자열 비교
		String str3 = "김또치";
		String str4 = "김또치";
		String str5 = new String("김또치");
		
		if(str3.equals(str4)) {
			System.out.println("같은 문자열");
		} else {
			System.out.println("다른 문자열");
		}
		
		if(str3.equals(str5)) {
			System.out.println("같은 문자열2");
		} else {
			System.out.println("다른 문자열2");
		}
		
		//문자열 -> 바이트 배열
		String str6 = "안녕하세요";
		byte[] byte1 = str6.getBytes();
		for(byte word : byte1) {
			System.out.println(word);
		}
		//System.out.println(Arrays.toString(byte1));
		
		//byte -> String
		String str7 = new String(byte1);
		System.out.println("byte->String : " + str7);
		
		//byte <-> String + encoding type
		
		try {//()안에 "aa"가 들어갈 수도 있으니까 예외 처리 필요
			//String -> byte(EUC-KR)
			byte[] byte2 = str6.getBytes("EUC-KR");
			//byte -> String(EUC-KR)
			String str8 = new String(byte2, "EUC-KR");
			System.out.println(str8);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		//문자열 위치 찾기
		String subject = "자바 프로그래밍";
		int idx = subject.indexOf("그래밍");
		System.out.println(idx);
		//문자열을 못 찾았을 때
		idx = subject.indexOf("java");
		System.out.println(idx);
		
		//문자열 길이
		System.out.println(subject.length());
		
		String ssn2 = "000102-3157849";
		if(ssn2.length() == 14) {
			System.out.println("주민등록 번호 자리 수 맞음");
		} else {
			System.out.println("주민등록 번호 자리 수 틀림");
		}
		
		//문자열 대치(문자열 바꾸기)
		String oldStr = "자바 프로그래밍";
		String newStr = oldStr.replace("자바", "java");
		System.out.println(newStr);
		
		//문자열 자르기 - substring
		//매개변수에 따라서 자르는 방법 다름
		//1) 매개변수가 1개일 경우 - 해당 위치로부터 뒤로 다 짤라라.
		String ssn3 = "123456-1234567";
		String firstNum = ssn.substring(7);
		System.out.println(firstNum);
		
		//2) 매개변수가 2개일 경우 - 시작 위치 ~ 끝나는 위치 앞! 까지 짤라라.
		String secondNum = ssn3.substring(0,6);
		System.out.println(secondNum);
		
		//대문자 -> 소문자
		String big = "ABCDEFG";
		System.out.println(big.toLowerCase());
		
		//소문자 -> 대문자
		String small = "abcdefg";
		System.out.println(small.toUpperCase());
		
		//양쪽 공백 제거
		String name = "     고희동     ";
		System.out.println(name.trim());
		
		//기본 타입 -> 문자열 변환
		String temp = String.valueOf(123);
		System.out.println(temp); //"123" 단어가 됨
		temp = String.valueOf(true);
		System.out.println(temp); //"true" 단어가 됨
		
		//문자열 분리하기 - split()
		//구분자를 통한 문자열 분리
		String value = "1,2,3,4,5,6,7,8,9";
		
		String[] strAry = value.split(",");
		
		for(String data : strAry) {
			System.out.println(data);
		}
		
		//isEmpty - 문자열이 비어 있는지 확인
		String empty = "";
		if(empty.isEmpty()) {
			System.out.println("비었음");
		} else {
			System.out.println("안 비었음");
		}
		
		//String의 단점을 보완해주는 API
		//StringBuilder, StringBuffer
		StringBuilder sb = new StringBuilder();
		sb.append("ye"); //자바스크립트 돔 붙일 때 append
		sb.append("ah");
		System.out.println(sb);
		sb.deleteCharAt(1);
		System.out.println(sb);
		sb.delete(0, 1); //인덱스 0부터 1개 지운다
		System.out.println(sb);
		
		//concat - 문자열 합치기(+)
		//contains - 포함하고 있는 문자열 확인(indexOf로 대체 가능)
		
		//endsWith("a") : a로 끝나는지?
		//str.endsWith("a")
		
		
		//=========================================================문제=============================================================
		//1) 문자열 뒤집기
		//String str = "abcdefg" -> "gfedcba"
		String oldStr1 = "abcdefg";
		String newStr1 = "";
		for(int i=oldStr1.length()-1; i>=0; i--) {
			newStr1 += oldStr1.charAt(i);
		}
		System.out.println(newStr1);
		
		//답안
//		String str = "abcdefg";
//		for(int i= str.length()-1; i>=0; i--) {
//			System.out.println(str.charAt(i));
//		}
		
		
		//2) 문자 개수 찾기
		String str = "1n2ASD 330naa1";
		//숫자 : 6개 / 알파벳 : 7개 / 공백(띄어쓰기) : 1
		int number = 0;
		int alpha = 0;
		int space = 0;
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c >= '0' && c <= '9') {
				number ++;
			} else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				alpha ++;
			} else if(c == ' ') {
				space ++;
			}
		}
		System.out.println("숫자 : " + number);
		System.out.println("알파벳 : " + alpha);
		System.out.println("공백 : " + space);
		
		
		//3) 주민등록번호 입력 후 나이 계산하기(원래 : 오늘-> 년도+월+일)
		//단, 올해 기준 00~23년생 -> 2000년대 /  24~99년생 -> 1900년대
		String ssn4 = "980102-1234567";
		//나이 : 26
		//1924년 이후 2000년 이전 출생 -> 98+26 = 124
		String ssn5 = "000102-1234567";
		//나이 : 24
		//2000년 이후 2023년 이전 출생 -> 00+24 = 24
		
		String newSsn4 = ssn4.substring(0,2);
		int intSsn4 = Integer.parseInt(newSsn4);
		if(intSsn4 >=0 && intSsn4 <24) { //음수가 나올 일이 없음 그래서 0보다 크다 조건이 필요 없음. 전제 자체가 0보다 크다
			System.out.println(23 - intSsn4 + 1);
		} else {
			System.out.println(123 - intSsn4 + 1);
		}
		
		//3번 답안
		Scanner sc = new Scanner(System.in);
		System.out.println("생년월일>");
		String birth = sc.nextLine();
		//년도 문자열을 자르고 정수로 변환
		int birthNo = Integer.parseInt(birth.substring(0,2));
		
		//조건 / 제어문
		if(birthNo <= 23) {
			System.out.println("나이 : " + (24-birthNo) + "살");
		} else {
			System.out.println("나이 : " + (124-birthNo) + "살");
		}
		
		
		
		
		
		
		//4)문자열 개수 압축
		String str8 = "KKHSSSSSSSSE";
		//결과 -> K2HS8E
		char ch = 0;
		String newStr8 = "";
		int count = 1;
		for(int i=0; i<str8.length(); i++) {
			if(i==0) {
				ch = str8.charAt(i);
				newStr8 += str8.charAt(i);
			} else if(ch != str8.charAt(i)) {
				ch = str8.charAt(i);
				newStr8 += (String.valueOf(count));
				newStr8 += str8.charAt(i);
				count = 1;
				if(i == str8.length() -1 ) {
					newStr8 += count;
				}
			} else if(ch == str8.charAt(i)) {
				count ++;
			}
			
			
			
		
		}
		
		System.out.println(newStr8);
		
		//4번 답안
		//String str = "KKHSSSSSSSSE";
		String result = "";
		//int count = 1;
		for(int i=0; i<str.length(); i++) {
			//문자 비교
			if(i < str.length() -1 && str.charAt(i) == str.charAt(i+1)) { //현재위치랑 그 다음 위치가 똑같으면. &&기준으로 앞뒤바꾸면 안됨. &&는 특성상 앞이 false면 뒤에꺼 체크안해버려서
				count ++;
			} else {
				result += str.charAt(i);
				if(count > 1) {
					result += String.valueOf(count); //String.valueOf 안넣어도됨
				}
				count = 1;
			}
		}
		System.out.println(result);
		
		
		
		
		
		
		
		
	}

}
