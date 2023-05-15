package com.yedam.inheri;

public class SupersonicAirPlane extends AirPlane {
	//0515 13번
	
	//일반
	public static final int NORMAL = 1;
	//초음속
	public static final int SUPERSONIC = 2;
	
	//기본 비행 -> 일반 비행(default)
	public int flyMode = NORMAL;

	@Override
	public void fly() {
		if(flyMode == SUPERSONIC) {
			System.out.println("초음속 비행합니다.");
		} else {
			super.fly(); //일반비행
		}
	}
	//실행은 Application03
}
