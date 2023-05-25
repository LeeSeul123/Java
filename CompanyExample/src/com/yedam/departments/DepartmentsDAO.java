package com.yedam.departments;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class DepartmentsDAO extends DAO {
	//싱 글 톤
	private static DepartmentsDAO deptDao = null;
	
	private DepartmentsDAO() {
		
	}
	
	public static DepartmentsDAO getInstance() {
		if(deptDao == null) {
			deptDao = new DepartmentsDAO();
		}
		return deptDao;
	}
	//--------------싱글톤-----------------
	
	//1. 모든 부서 조회
	//2. 부서 조회
	//3. 등록
	
	
	//1. 전체 조회 기능
	public List<Departments> getDeptList(){
		List<Departments> list = new ArrayList<>();
		Departments dept = null;
		
		//데이터를 조회할 수 있는 구문. DB를 쓸 땐 무조건 try, catch, finally를 써야한다.
		try {
			conn();
			String sql = "SELECT * FROM departments";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //조회한 모든 결과가 rs에 들어옴(데이터가 여러개)
			
			while(rs.next()) { //데이터가 조회된 개수만큼 반복
				dept = new Departments();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				list.add(dept);
			}			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//2.단건 조회
	public Departments getDepartment(int deptId) { //프라이머리 키가 deptId라서
		Departments dept = null;
		try {
			conn();
			String sql = "SELECT * FROM departments WHERE department_id = ?"; //입력한 부서번호를 ?에 넣을거임
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptId); //내가 받아온 부서번호를 첫번째 물음표에 넣어주겠다
			rs = pstmt.executeQuery(); //DB를 실행시킨다.
			
			if(rs.next()) {
				dept = new Departments();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return dept;
	}
	
	//3.등록
	public int insertDept(Departments dept) { //매개변수로 데이터를 전달
		int result = 0; //데이터가 잘 들어갔는지 확인하기 위해서.
		try {
			conn();
			String sql = "INSERT INTO departments(department_id, department_name) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql); //pstmt가 동작
			pstmt.setInt(1, dept.getDepartmentId()); //입력받아온 id를 입력
			pstmt.setString(2, dept.getDepartmentName());
			
			result = pstmt.executeUpdate();//쿼리문 실행
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	
	
	
	
	
	
}
