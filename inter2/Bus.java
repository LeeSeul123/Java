package com.yedam.inter2;

public class Bus implements Vehicle {

	//0516 19번
	
	@Override
	public void run() {
		System.out.println("버스가 달립니다.");
	}
	
	//397p. 인터페이스가 가진 메소드 외에 개별적으로 정의한 메소드
	public void checkFare() {
		System.out.println("승차 요금 확인");
	}

}
