package com.yedam.employees;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeesService {// 데이터 입력, 출력 담당
	//실행 <-> 데이터를 가공 <-> DB(DAO)
	//실행은 실행만, DB는 조회하는 기능만
	Scanner sc = new Scanner(System.in);
	
	//전체조회
	public void getEmployeesList() {
		List<Employees> list = EmployeesDAO.getInstance().getEmployeesList(); //싱글톤이 가지는 객체 가져옴(empDAO). empDAO가 가지는 메소드는 list를 반환함
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public void getEmployee() {
		System.out.println("사번 입력>");
		int empId = Integer.parseInt(sc.nextLine());
		Employees emp = EmployeesDAO.getInstance().getEmployee(empId);
		
		if(emp != null) { //데이터가 조회가 되었을 경우
			System.out.println("employeeID : " + emp.getEmployeeId());
			System.out.println("lastName : " + emp.getLastName());
			System.out.println("email : " + emp.getEmail());
			System.out.println("hireDate : " + emp.getHireDate());
			System.out.println("jobId : " + emp.getJobId());
		} else { //데이터가 조회 안 된 경우
			System.out.println("존재하지 않는 사번입니다.");
		}
	}
	
	public void insertEmp() {
		Employees emp = new Employees();
		
		System.out.println("====사 원 등 록====");
		
		System.out.println("사번>");
		emp.setEmployeeId(Integer.parseInt(sc.nextLine())); //emp객체에 데이터 넣어줌
		
		System.out.println("이름>");
		emp.setLastName(sc.nextLine());
		
		System.out.println("이메일>");
		emp.setEmail(sc.nextLine());
		
		System.out.println("입사일>");
		emp.setHireDate(Date.valueOf(sc.nextLine())); //문자열을 날짜로 바꿔서 넣어줌
		
		System.out.println("직책>");
		emp.setJobId(sc.nextLine());
		
		int result = EmployeesDAO.getInstance().insertEmp(emp); //결과 값이 1이면 제대로 된 거
		
		if(result > 0) {
			System.out.println("사원 입력 완료");
		} else {
			System.out.println("사원 입력 실패");
		}
	}
}
