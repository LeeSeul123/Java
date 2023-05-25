package com.yedam.inheri;

import com.yedam.protect.A;

public class C extends A {
	//0515 18번
	
	public C() { //상속을 받아서 활용
		super();
		this.field = "value";
		this.method();
	}
	
	
//	public void method2() {
//		A a = new A(); //다른 패키지라서 import 필요
//		a.field = "value";
//		a.method();
//	}  -> 상속이 아니라 객체를 만들어서 쓰는거라서 안됨
}
