package com.yedam.oop;

public class Bycle {
	//필드
	int gas;
	//생성자
	
	//메소드
	void setGas(int gas) {
		this.gas = gas;
	}
	
	boolean isLeftGas() { //매개변수는 없지만 리턴값 존재.true 나 false만 가능
		if(gas==0) {
			System.out.println("gas가 없습니다.");
			return false;
		}
		System.out.println("gas가 있습니다.");
		return true;
	}
	
	void run() {
		while(true) {
			if(gas>0) {
				System.out.println("달립니다.(gas잔량 :" + gas + " )");
				gas -= 1;
			} else {
				System.out.println("멈춥니다.(gas잔량 :" + gas + " ");
				return; //return뒤에 값이 없으면 메소드 강제종료. 연료가 0이 되면 while문 강제종료
			}
		}
	}
}
