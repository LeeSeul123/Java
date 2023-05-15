package com.yedam.oop;

public class Application01 {

	public static void main(String[] args) {
		//Car Class -> 객체로 만듦
		Car myCar = new Car(); //Car 클래스의 타입 + 변수 = 객체생성할 때 쓰는 new연산자 + 생성자
		
		//dot,,.
		//필드에 값을 넣지 않았을 때(선언만 했을 때) 나오는 결과 확인   ---> 기본값이 나오게 된다. 
		System.out.println(myCar.company); //null
		System.out.println(myCar.price);   //0
		System.out.println(myCar.name);    //null
		
		myCar.company = "현대"; //myCar.company = Car클래스의 String company. 즉 타입은 myCar.company = String이다
		myCar.price = 1234;
		myCar.name = "소나타";
		
		//1번 방식) 클래스 외부에서 객체를 생성하고 필드를 호출하여 데이터를 넣어준 후 다시 출력
		System.out.println("==객체 필드 데이터 입력==");
		System.out.println(myCar.company);
		System.out.println(myCar.price); 
		System.out.println(myCar.name);
		
		//myCar와 yourCar는 같은 Car 객체를 쓰는 듯 하지만 다른 객체를 사용하고 있다는 반증
		Car yourCar = new Car();
		System.out.println("MyCar 와 YourCar 비교");
		System.out.println(myCar.company);
		System.out.println(yourCar.company);
		
		System.out.println("==Korean Class를 활용한 객체 생성==");
		
		//Korean k1 = new Korean(); //에러.생성자 있어서 기본생성자 자동으로 안만들어줌
		
		Korean k1 = new Korean("박자바","01125-321321");
		System.out.println(k1.nation);
		System.out.println(k1.name);
		System.out.println(k1.ssn);
		
		System.out.println("==생성자 오버로딩을 활용한 객체 생성==");
		
		//매개변수가 하나인 생성자 활용
		Car oneCar = new Car("소나타");
		System.out.println("oneCar의 필드 name : " + oneCar.name);
		oneCar.run(); //반환값이 없어서 안에서 모든기능 실행
		System.out.println(oneCar.info()); //반환 = 이자리에 위치하게 됨
		
		Car threeCar = new Car("그랜저", 3000, "현대");
		System.out.println("ThreeCar의 필드 : " + threeCar.name);
		System.out.println("ThreeCar의 필드 : " + threeCar.price);
		System.out.println("ThreeCar의 필드 : " + threeCar.company);
	}

}
