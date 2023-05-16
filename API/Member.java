package com.yedam.API;

public class Member {
	
	//0516 27번
	
	//필드
	public String id;
	
	//생성자
	Member(String id){
		this.id = id;
	}

	//메소드
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member member = (Member)obj;
			if(id.equals(member.id)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode(); //hashCode를 이용해 int로 바뀜
		//문자열의 해시코드. 같은 문자열이면 같은 해시코드가 나온다
	}
	
	
}
