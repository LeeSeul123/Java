package com.yedam.member;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class MemberDAO extends DAO{
	private static MemberDAO memberDao = null;
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if(memberDao == null) {
			memberDao = new MemberDAO();
		}
		return memberDao;
	}
	
	//아이디를 활용해서 정보 조회 -> java에서 비교
	//1.로그인 기능
	public Member login(String id) {
		Member member = null;
		try {
			conn();
			String sql = "SELECT * FROM member WHERE member_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //조회된 정보가 있으면 member객체에 데이터 존재
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberAddr(rs.getString("member_addr"));
				member.setMemberGrade(rs.getString("member_grade")); //character가 없어서 string 사용
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return member;
	}
	
	//회원가입 기능
	public int insertMember(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO member values(?,?,?,?,'N')"; //원하는 데이터가 있을때는 바로 대입해줘도 됨
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberAddr());
//			pstmt.setString(5, member.getMemberGrade());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//전체 조회
	public List<Member> getMemberList(){
		List<Member> list = new ArrayList<>();
		Member member = null;
		try {
			conn();
			String sql = "SELECT * FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//한개의 데이터가 아니라서 while문
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberAddr(rs.getString("member_addr"));
				member.setMemberGrade(rs.getString("member_grade"));
				list.add(member);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
	}
	//회원 수정 - 연락처 수정
	public int updatePhone(Member member) { //id, 연락처 둘 다 필요
		int result = 0;
		try {
			conn();
			String sql = "UPDATE member SET member_phone = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
			//update와 delete는 여러개가 될 수 있어서. 0보다 크다로 해야함(1과 같다 안됨)
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	//회원 삭제
	public int deleteMember(String id) {
		int result = 0;
		
		try {
			conn();
			String sql = "delete FROM member WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
}
