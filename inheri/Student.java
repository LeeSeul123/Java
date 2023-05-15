package com.yedam.inheri;

public class Student extends People{
	//20230515 5번
	
	int stdNo;
	
	public Student(String name, String ssn, int stdNo) {
		super(name,ssn);
		this.stdNo = stdNo;
	}
}
