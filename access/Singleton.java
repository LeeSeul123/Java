package com.yedam.access;

public class Singleton {
	//279페이지
	
	//클래스 내부에서 하나의 객체를 생성
	//하나의 객체는 외부에서 접근이 불가능
	//private static Singleton singleton = new Singleton();
	private static Singleton singleton = null; //객체가 생성되지 않음
	
	//외부에서 객체를 생성하지 못하도록 막는 역할
	private Singleton() {
		
	}
	
	//클래스 내부에서 만든 단 하나의 객체를 외부에 전달하기 위한 용도
//	public static Singleton getInstance() {
//		return singleton;
//	}
	public static Singleton getInstance() { //사용할 때 얘를 동작시켜서 싱글톤 만들어서 메모리 낭비를 막음(안쓰는데 만들어봤자 아까움)
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton; //한번 생성되면 이걸로
	}
	
	//---------------싱글톤 세팅 끝--------------------
	
	//필드
	public String name;
	public int age;
			
	//메소드
	public void info() {
	System.out.println("싱클톤의 인스턴스 메소드 info 출력");
	}
}
