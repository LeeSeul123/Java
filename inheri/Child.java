package com.yedam.inheri;

public class Child extends Parent{
	//0515 11번
	
	//필드
	String name;
	
	//생성자
	
	//메소드
	
	@Override
	public void getInfo() {
		super.getInfo(); //부모꺼.
		System.out.println("나의 이름은 " + name + " 입니다.");
	}
	
	@Override
	public void method1() {
		this.getInfo(); //자식꺼.
		method2();
		System.out.println("자식 객체의 method1 실행");
	}
	
	public void method3() {
		System.out.println("자식 객체의 method3 실행");
	}
	
	//실행 클래스는 Application03
}
