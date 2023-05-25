package com.yedam.map;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class HashTableExample {

	public static void main(String[] args) {
		//로그인
		//1) 아이디 확인 -> 데이터가 존재 -> 비밀번호 확인 -> 맞음 / 틀릴 수도 있음(비밀번호 불일치 안내)
		//						존재X(ID 불일치 안내)
		
		//id : 중복되면 안됨
		Map<String, String> map = new Hashtable<>();
		
		//임의의 ID/PW입력
		map.put("sptring", "12");
		map.put("summer", "123");
		map.put("fall", "1234");
		map.put("winter", "12345");
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("ID> ");
			String id= sc.nextLine();
			System.out.println("PW> ");
			String pw= sc.nextLine();
		
			//map이 가지고 있는 키(아이디) 확인
			if(map.containsKey(id)) {
				if(map.get(id).equals(pw)) {
					System.out.println("로그인 성공");
					break;
				} else {
					System.out.println("비밀번호 불일치");
				}
			} else {
				System.out.println("아이디 불일치");
			}
		
		}
	}

}
