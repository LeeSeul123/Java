package com.yedam.employees;

import java.sql.Date;

public class Employees {
	//DB에서 가져온 데이터 보관하는 곳 -> 가져온 내용 활용
	
	//DTO : DATA Transfer Object(데이터 전송할 때 쓰는 Object)
	//VO : DTO + 기능
	//BEANS : DTO == VO == BEANS(Web에서 종종쓰임)
	
	//EMPLOYEE_ID -> DB에서의 언더바는 자바에서 안씀. 자바형태의 변수로 만들어줌. 자바에서의 컬럼명과 DB에서의 컬럼명을 매칭시켜줌
	private int employeeId;  //NOT NULL NUMBER(6);
	private String lastName; //Last_NAME NOT NULL VARCHAR2(25);
	private String email;
	private Date hireDate;
	private String jobId;
	//한명씩만 가져올 수 있음
	
	
	//필드 private이므로 getter, setter필요
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", lastName=" + lastName + ", email=" + email + ", jobId="
				+ jobId + "]";
	}
}
