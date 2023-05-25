package com.yedam.abs;

public class Cat extends Animal {
	//0515 32번
	
	public Cat() {
		this.kind = "포유류";
	}
	
	@Override
	public void sound() {
		System.out.println("야-옹");
	}

}
