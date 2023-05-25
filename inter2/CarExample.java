package com.yedam.inter2;

public class CarExample {

	public static void main(String[] args) {
		//0516 17번
		
		Car myCar = new Car();
		myCar.run();
		
		myCar.frt = new HankookTire();
		System.out.println("타이어 교체 후===");
		myCar.run();
	}

}
