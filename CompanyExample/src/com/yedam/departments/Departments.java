package com.yedam.departments;

public class Departments {
//	DEPARTMENT_ID   NOT NULL NUMBER(4)    
//	DEPARTMENT_NAME NOT NULL VARCHAR2(30)
	
	private int departmentId;
	private String departmentName;
	//이 2개의 데이터를 입력하거나 받을 수 있다. 밑의 getter와 setter로
	
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
	
}
