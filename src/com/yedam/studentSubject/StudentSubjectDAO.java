package com.yedam.studentSubject;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.member.MemberService;
import com.yedam.subject.Subject;

public class StudentSubjectDAO extends DAO{
	private static StudentSubjectDAO ssDao = null;
	
	private StudentSubjectDAO() {
		
	}
	
	public static StudentSubjectDAO getInstance() {
		if(ssDao == null) {
			ssDao = new StudentSubjectDAO();
		}
		return ssDao;
	}
	
	
	//수강 신청
	public int insertSubject(int subjectId) {
		int result = 0;
		try {
			//이미 등록되어 있는 과목인지 확인
			conn();
			String sql = "SELECT * FROM student_subject WHERE student_id = ? AND subject_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			pstmt.setInt(2, subjectId);
			rs = pstmt.executeQuery();
			Subject subject2 = null;
			if(rs.next()) {
				subject2 = new Subject();
				
			} 
			  //등록 되어있지 않을경우, 등록할 과목의 시간 확인
			if(subject2 !=null) {	
				System.out.println("이미 등록된 과목");
			} else {
				sql = "SELECT subject_starttime, subject_endtime, subject_week FROM subject WHERE subject_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, subjectId);
				rs = pstmt.executeQuery();
				Subject subject = null;
				if(rs.next()) {
					subject = new Subject();
					subject.setSubjectStartTime(rs.getInt("subject_starttime"));
					subject.setSubjectEndTime(rs.getInt("subject_endtime"));
					subject.setSubjectWeek(rs.getInt("subject_week"));
				}
				
				if(subject == null) {
					System.out.println("없는 과목 입니다.");
				} else {
					Subject subject3 = null;
					sql = "SELECT s.subject_name, s.subject_starttime, s.subject_endtime FROM subject s JOIN student_subject ss ON(s.subject_id = ss.subject_id) WHERE student_id = ? AND subject_week = ? AND ((subject_starttime <= ? AND subject_endtime >= ?) OR(subject_starttime<= ? AND subject_endtime >= ?))";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, MemberService.memberInfo.getMemberId());
					pstmt.setInt(2, subject.getSubjectWeek());
					pstmt.setInt(3, subject.getSubjectEndTime());
					pstmt.setInt(4, subject.getSubjectEndTime());
					pstmt.setInt(5, subject.getSubjectStartTime());
					pstmt.setInt(6, subject.getSubjectStartTime());
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						subject3 = new Subject();
						subject3.setSubjectName(rs.getString("subject_name"));
						subject3.setSubjectStartTime(rs.getInt("subject_starttime"));
						subject3.setSubjectEndTime(rs.getInt("subject_endtime"));
					}
					
					if(subject3 == null) {
						sql = "SELECT sum(subject_point) FROM student_subject WHERE student_id = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, MemberService.memberInfo.getMemberId());
						rs = pstmt.executeQuery();
						
						int subjectPoint = 0;
						if(rs.next()) {
							subjectPoint = rs.getInt("sum(subject_point)");
						}
						
						if(subjectPoint + (subject.getSubjectEndTime()-subject.getSubjectStartTime()) > 10) {
							System.out.println("최대 들을 수 있는 10학점을 초과함");
						} else {
							sql = "INSERT INTO student_subject VALUES(?,?,null ,?)";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, MemberService.memberInfo.getMemberId());
							pstmt.setInt(2, subjectId);
							pstmt.setInt(3, subject.getSubjectEndTime()-subject.getSubjectStartTime());
							result = pstmt.executeUpdate();
						}
						
					} else {
						System.out.println(subject3.getSubjectName() + "과 시간이 겹쳐서 불가");
					}
					
				}
			}
					
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//현재 수강하는 과목, 총 학점
	public List<Subject> getAllSubject(){
		List<Subject> list = new ArrayList<>();
		Subject subject = null;
		
		try {
			conn();
			String sql = "SELECT subject_name, subject_id, subject_point FROM student_subject JOIN subject USING (subject_id) WHERE student_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new Subject();
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectEndTime(rs.getInt("subject_point")); //subject_point 받을 공간 빌려씀
				list.add(subject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//수강 취소
	public int deleteSubject(int subjectId) {
		int result = 0;
		try {
			conn();
			String sql = "SELECT * FROM student_subject WHERE student_id = ? AND subject_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			pstmt.setInt(2, subjectId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sql = "DELETE FROM student_subject WHERE subject_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, subjectId);
				result = pstmt.executeUpdate();
			} else {
				System.out.println("등록한 수업이 아닙니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//내 성적 조회
	public List<StudentSubject> getGradeList(){
		List<StudentSubject> list = new ArrayList<>();
		StudentSubject studentSubject = null;
		
		try {
			conn();
			String sql = "SELECT student_grade FROM student_subject WHERE student_id= ? AND student_grade is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				studentSubject = new StudentSubject();
				studentSubject.setStudentGrade(rs.getString("student_grade"));
				list.add(studentSubject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
	}
}
