package com.yedam.inheri;

public class Application03 {
	public static void main(String[] args) {
		//0515 9번
		int r = 10;
		
		Computer com = new Computer();
		System.out.println("원 면적 : " + com.areaCircle(r));
		
		//Child실행
		Child child = new Child();
		
		//부모 객체
//		child.getInfo();
//		child.method1();
//		child.method2();
//		
//		//자식 객체
//		child.method3();
		
		
		//Plane실행
		System.out.println();
		SupersonicAirPlane sa = new SupersonicAirPlane();
		sa.takeOff();
		sa.fly();
		sa.flyMode = SupersonicAirPlane.SUPERSONIC;
		sa.fly();
		sa.flyMode = SupersonicAirPlane.NORMAL;
		sa.fly();
		sa.land();
		
	}
}
