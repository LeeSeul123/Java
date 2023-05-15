package com.yedam.poly;

public class DriverExample {

	public static void main(String[] args) {
		//0515 27번
		Driver driver = new Driver();
		
		//1번방식. 객체를 만든 후 넣어줌
		Taxi taxi = new Taxi();
		driver.drive(taxi);
		
		//2번방식. 1번방식을 더 짧게함
		driver.drive(new Bus());
	}

}
