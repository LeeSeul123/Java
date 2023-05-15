package com.yedam.inheri;

public class CellPhone {
	//20230515 1번
	//필드
	String model;
	String color;
	
	//생성자
	public CellPhone() {
		System.out.println("부모 객체 생성");
	}
	//생성자 오버로딩
	CellPhone(String model){
		this.model = model;
	}
	
	//메소드
	void powerOn() {
		System.out.println("전원을 켭니다.");
	}
	
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
	
	void bell() {
		System.out.println("전화가 옵니다.");
	}
	
	void hangUp() {
		System.out.println("전화를 끊습니다.");
	}
}
