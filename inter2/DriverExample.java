package com.yedam.inter2;

public class DriverExample {

	public static void main(String[] args) {
		//0516 22번
		
		Driver driver = new Driver();
		
		driver.drive(new Bus());
		driver.drive(new Taxi());
	}

}
