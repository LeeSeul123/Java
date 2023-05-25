package com.yedam.access;

public class Member {
	
	//어떠한 사이트에 회원 정보를 저장
	//아이디, 비밀번호, 이름, 나이
	//생성자 기본생성자
	//getter, setter 활용해서 정보를 읽고 저장
	//1) 나이를 입력 받았을 때 0보다 작은 숫자가 들어오면 0으로 바꿔준다.
	//2) 나이 정보를 얻어 갈 때에는 +1해서 얻어간다.
	
	private String id;
	private String password;
	private String name;
	private int age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return ++age;
	}
	public void setAge(int age) {
		this.age = age > 0 ? age : 0;
	}
	
	public void instance() {
		Singleton single = Singleton.getInstance();
		System.out.println("Member 객체의 instance 메소드");
		System.out.println(single.age);
		System.out.println(single.name);
	}
}
