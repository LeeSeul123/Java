package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	//DAO -> DB에 접근하는 객체 Data(DB) Access Object
	//JDBC를 통해서 JAVA<->DB가 연결이 된다.
	
	//java -> DB 연결할때 쓰는 객체
	protected Connection conn = null;
	
	//Query문(SQL, 질의)를 가지고 실행하는 객체
	protected PreparedStatement pstmt = null;
	
	//Query문(SQL, 질의)를 가지고 실행하는 객체
	protected Statement stmt = null;
	
	//SELECT(조회) 결과 값을 반환받는 객체
	protected ResultSet rs = null;
	
	//DB 접속 정보
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "bank";
	String pw = "1234";
	
	//DB 연결 메소드
	public void conn() { //DB 인사관리 화면까지 오는거
		try {
			//1. 드라이버 로딩(경로 실행시킴)
			Class.forName(driver);
			//2. DB연결
			conn = DriverManager.getConnection(url,id,pw);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//DB 연결 해제
	public void disconn() { //쿼리문 다 쓴 후 developer 종료. 문을 열고 온거 다 닫아야함. 연결 순서의 역순
		try { //null이 아니다 == 한번씩 사용했다 / else if문 쓰면 안됨
			if(rs != null) { //결과 받아오는거 종료. 기본값 null로 들어가있음. 1번이상 실행했으면 조회한 결과가 들어가 있음. null이면 사용을 못했다는 뜻
				rs.close();
			}
			if(stmt != null) { //쿼리문 실행시키는거
				stmt.close();
			}
			if(pstmt != null) { //쿼리문 실행시키는거 종료. 쿼리문 사용했으면 실행한 내용이 들어있을 것.
				pstmt.close();
			}
			if(conn != null) {// 연결한 결과
				conn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
