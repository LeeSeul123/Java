package com.yedam.inter2;

public class Application {
	//0516 26번
	public static void main(String[] args) {
		//A(인터페이스) <- B <- C
		A a = new B();
		a.info();
		
		a = new C();
		a.info();
	}

}
