package com.yedam.inter2;

public class Driver {
	
	//0516 21번
	
	public void drive(Vehicle vehicle) {
		//vehicle에 들어온 객체가 Bus인지 Taxi인지에 따라 결과가 달라짐
		if(vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.checkFare();
		}
		vehicle.run();
	}
	
	
}
