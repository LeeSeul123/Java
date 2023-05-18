package com.yedam.employees;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class EmployeesDAO extends DAO{
	//싱글톤 -> DB에 접속
	private static EmployeesDAO empDAO = null;
	
	private EmployeesDAO() {
		
	}
	
	public static EmployeesDAO getInstance() {
		if(empDAO == null) {
			empDAO = new EmployeesDAO();
		}
		return empDAO; //객체
	}
	//empDAO로 DB연결
	
	//전체 조회 기능
	//empDAO가 사용할 수 있는 메소드
	public List<Employees> getEmployeesList(){
		//한명에 대한 정보만 가능해서 List에 다 담아줌
		List<Employees> list = new ArrayList<>();
		Employees emp = null;
		try {//DB다룰 때 어떤 일이 발생할지 몰라서
			conn();
			String sql ="SELECT * FROM employees"; //내가 하고자 하는 쿼리문
			stmt = conn.createStatement(); //연결하고나서 DB랑 연결시킬 수 있는 메소드. 쿼리문 실행준비.
			rs = stmt.executeQuery(sql); //쿼리문 실행 조회된 결과 값을 rs에 담아줌(107행 모두 다)
			
			while(rs.next()) { //rs.next -> 한칸 밑으로 내려가서 데이터가 있습니까? -> true,false 반환
				emp = new Employees();
				emp.setEmployeeId(rs.getInt("employee_id")); //set은 data넣을 때 씀. 조회한 결과중 employee_id컬럼의 결과를 넣어줌
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getDate("hire_date")); //매개변수는 db 관련. (" ")-> table의 컬럼명이 아니라 조회된 결과에 따른 컬럼명(as쓰면 별칭 써야함)!!!!
				list.add(emp); //배열같은 형태가 됨
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//단건 조회(한건 조회)
	public Employees getEmployee(int empid) {//매개변수로 조회하고자하는 id
		Employees emp = null; //나눠서 쓰는 이유 :
		try {
			conn(); //db연결
			String sql = "SELECT * FROM employees WHERE employee_id =?"; //위에는 statement. 여기는 preparestatement연결 후 하고자하는 쿼리
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empid); //setInt = 정수값을 넣어준다. 몇번째 물음표에 어떤 데이터를 넣겠냐
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //1건이라서 while문 쓸 필요 X. 데이터 존재하면 담아주고 존재하지 않으면 담아주지 않음
				emp = new Employees(); //조회한 결과 데이터가 없으면  --> null객체(조회한 결과가 없음)거나 데이터가 있는객체(조회한 결과가 있음)
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getDate("hire_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return emp;
	}
	
	//등록(insert)
	public int insertEmp(Employees emp) {
		//객체에 데이터 담아오고 그걸 활용
		int result = 0;
		try {
			conn();
			String sql = "insert into employees(employee_id, last_name, email, hire_date, job_id) values(?,?,?,?,?)"; //? = 데이터가 들어갈 공간
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmployeeId()); //첫번째 물음표에 emp.getEmployeeId()넣어줌
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3,  emp.getEmail());
			pstmt.setDate(4, emp.getHireDate());
			pstmt.setString(5,  emp.getJobId());
			
			//select와 다름
			result = pstmt.executeUpdate(); //excuteUpdate = > 1건이 삭제됨 처럼 숫자가 반환됨. 조회가 안되면 위에서 초기화한 0이 나옴. 내가 수행한 DML의 개수
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	
	
	
}
