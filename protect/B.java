package com.yedam.protect;

public class B extends A{
	//0515 17번
	
	@Override
	public void method() {
		A a = new A();
		a.field = "value";
		a.method();
	}
}
