package com.yedam.API;

import java.util.HashMap;

public class ObjectExample {
	
	//0516 28번

	public static void main(String[] args) {
		Member member = new Member("123");
		Member member2 = new Member("123");
		Member member3 = new Member("321");
		
		if(member.equals(member2)) {
			System.out.println("동등한 객체");
		} else {
			System.out.println("다른 객체");
		}
		
		if(member.equals(member3)) {
			System.out.println("동등한 객체");
		} else {
			System.out.println("다른 객체");
		}
		
		HashMap<Key, String> hashMap = new HashMap<>();
		
		hashMap.put(new Key(1), "홍길동");
		
		String value = hashMap.get(new Key(1)); //150번지 열쇠에는 아무것도 없으므로 null
		
		System.out.println(value);
		
		//Member에 String hashcode
		HashMap<Member, String> hashMap01 = new HashMap<>();
		
		hashMap01.put(new Member("123"),"길동");
		
		hashMap01.get(new Member("123"));
		
		System.out.println(hashMap01.get(new Member("123")));
		
		Object obj = new Object();
		Object obj2 = new Object();
		
		System.out.println(obj); //toString이 뒤에 생략되어 있다.
		System.out.println(obj.toString());
		
		SmartPhone sp = new SmartPhone("apple", 100, "iphone");
				
		System.out.println(sp.toString());
	}

}
