package com.yedam.inter;

public class MyClassExample {

	public static void main(String[] args) {
		//0516 12번
		
		System.out.println("1) 필드==========");
		
		MyClass myClass = new MyClass();
		
		//RemoteControl rc를 가져오는 것과 똑같음(MyClass의 필드)
		//RemoteControl rc = new Television
		myClass.rc.turnOn();
		myClass.rc.setVolume(1);
		
		System.out.println("2) 생성자=========");
		MyClass myClass2 = new MyClass(new Audio());
		myClass2.rc.turnOn();
		myClass2.rc.turnOff();
		
		System.out.println("3) 메소드=========");
		MyClass myClass3 = new MyClass();
		myClass3.methodA(); //필드의 rc와 상관없이 새롭게 만들어짐
		
		System.out.println("4) 메소드==========");
		MyClass myClass4 = new MyClass();
		myClass4.methodB(new Television());
		myClass4.methodB(new Audio());
	}

}
