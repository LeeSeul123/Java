package com.yedam.abs;

public abstract class Animal {
	//0515 30번
	//추상 클래스
	
	//필드
	public String kind;
	
	//생성자 -> 기본 생성자
	
	//메소드
	public void breath() {
		System.out.println("숨을 쉰다.");
	}
	
	//추상 메소드
	public abstract void sound();
}
