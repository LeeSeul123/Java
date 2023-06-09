package com.yedam.access;

public class Student {
	//300페이지
	/*
	 * 이름, 학과, 학년, 과목별 점수
	 * 과목 -> programing, database, os
	 * 필드들은 모두 private
	 * setter 메소드를 활용해서 필드 초기화
	 * getter 메소드를 활용해서 객체의 필드 reading
	 * getInfo() 학생의 정보 출력
	 */
	
	//필드
	private String stdName;
	private String major;
	private String stdGrade;
	private int programing;
	private int database;
	private int os;
	
	//생성자(기본 생성자) -> 안만들면 알아서 만들어짐
	
	//메소드
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	
	public String getStdName() {
		return stdName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getStdGrade() {
		return stdGrade;
	}

	public void setStdGrade(String stdGrade) {
		this.stdGrade = stdGrade;
	}

	public int getPrograming() {
		return programing;
	}

	//과목의 점수 입력
	//프로그래밍 점수가 0점보다 낮은 점수(음수)가 입력될 경우에는
	//프로그래밍 점수를 0점으로 하겠다.
	public void setPrograming(int programing) {
		if(programing < 0) {
			this.programing = 0;
			return; //메소드 강제종료
		}
		this.programing = programing;
		
		// 위의 코드 줄이는 방법 : 삼항 연산자 사용
		// this.programing = programing > 0 ? programing : 0;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public int getOs() {
		return os;
	}

	public void setOs(int os) {
		this.os = os;
	}
	
	
	
	
}
