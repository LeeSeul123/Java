package com.yedam.abs;

public class AnimalExample {

	public static void main(String[] args) {
		//0515 33번
		Dog dog = new Dog();
		Cat cat = new Cat();
		
		dog.sound();
		cat.sound();
		System.out.println("-------------------------------------");
		
		//추상클래스를 다형성으로 표현
		//추상클래스는 스스로를 객체로 만들지 못하기 때문.(재정의 되지 않은 게 있음) 자동타입변환을 활용해서 만들 수 있음
		//변수의 다형성 / 필드의 다형성
		Animal animal = dog;
		animal.sound(); //반드시 재정의하라고했던 메소드가 무조건 재정의 해놨음. 무조건 dog의 sound가 실행이 됨. 상속은 재정의가 되있을지 모르지만 추상클래스는 무조건 재정의 되어있음
		
		animal = cat;
		animal.sound();
		
		animalSound(new Dog());
		animalSound(new Cat());
	}
	
	//매개변수의 다형성을 활용 -> 추상 클래스
	public static void animalSound(Animal animal) {
		animal.sound();
	}

}
