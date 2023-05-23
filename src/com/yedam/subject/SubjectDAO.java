package com.yedam.subject;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.member.MemberService;
import com.yedam.studentSubject.StudentService;
import com.yedam.studentSubject.StudentSubject;

public class SubjectDAO extends DAO{
	private static SubjectDAO sDao = null;
	
	private SubjectDAO() {
		
	}
	
	public static SubjectDAO getInstance() {
		if(sDao == null) {
			sDao = new SubjectDAO();
		}
		
		return sDao;
	}
	
	//교수메뉴
	//수업등록
	public int insertSubject(int subjectId) {
		int result = 0;
		try {
			//이미 등록되어 있는 과목인지 확인
			conn();
			String sql = "SELECT professor_id FROM subject WHERE professor_id IS NOT NULL AND subject_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subjectId);
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
					sql = "SELECT subject_name FROM subject WHERE professor_id = ? AND subject_week = ? AND ((subject_starttime <= ? AND subject_endtime >= ?) OR(subject_starttime<= ? AND subject_endtime >= ?))";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, MemberService.memberInfo.getMemberId());
					pstmt.setInt(2, subject.getSubjectWeek());
					pstmt.setInt(3, subject.getSubjectEndTime());
					pstmt.setInt(4, subject.getSubjectEndTime());
					pstmt.setInt(5, subject.getSubjectStartTime());
					pstmt.setInt(6, subject.getSubjectStartTime());
					rs = pstmt.executeQuery();
					Subject subject3 = null;
					if(rs.next()) {
						subject3 = new Subject();
						subject3.setSubjectName(rs.getString("subject_name"));
					}
					
					if(subject3 == null) {
						sql = "UPDATE subject SET professor_id = ? WHERE subject_id = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, MemberService.memberInfo.getMemberId());
						pstmt.setInt(2, subjectId);
						result = pstmt.executeUpdate();
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
	
	//현재 맡은 수업 확인
	public List<Subject> getAllSubject(){
		List<Subject> list = new ArrayList<>();
		Subject subject = null;
		
		try {
			conn();
			String sql = "SELECT * FROM subject WHERE professor_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectName(rs.getString("subject_name"));
				list.add(subject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
		
	}
	
	//수업취소
	public int deleteSubject(int subjectId) {
		int result = 0;
		try {
			conn();
			String sql = "SELECT subject_id FROM subject WHERE professor_id = ? AND subject_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			pstmt.setInt(2, subjectId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sql = "update subject SET professor_id = null WHERE subject_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, subjectId);
				result = pstmt.executeUpdate();
			} else {
				System.out.println("교수님께서 등록한 수업이 아닙니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//시간표 확인
	public List<Subject> getSchedule() {
		List<Subject> list = new ArrayList<>();
		Subject subject = null;
		
		try {
			conn();
			String sql = "SELECT * FROM subject WHERE professor_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new Subject();
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectWeek(rs.getInt("subject_week"));
				subject.setSubjectStartTime(rs.getInt("subject_starttime"));
				subject.setSubjectEndTime(rs.getInt("subject_endtime"));
				list.add(subject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
	}
	
	//본인이 맡은 과목이 맞는지 확인
	public Subject checkSubject(int subjectId) {
		Subject subject = null;
		
		try {
			conn();
			String sql = "SELECT * FROM subject WHERE subject_id = ? AND professor_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subjectId);
			pstmt.setString(2, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				subject = new Subject();
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return subject;
	}
	
	//학생 목록 얻기
	public List<StudentSubject> getStudentList(int subjectId) {
		List<StudentSubject> list = new ArrayList<>();
		StudentSubject studentSubject = null;
		
		try {
			conn();
			String sql = "SELECT student_id FROM student_subject WHERE subject_id = ? AND student_id IS NOT NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subjectId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				studentSubject = new StudentSubject();
				studentSubject.setStudentId(rs.getString("student_id"));
				list.add(studentSubject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
	}
	//학생 성적 입력
	public int updateGrade(String studentId, String studentGrade, int subjectId) {
		int result = 0;
		
		try {
			conn();
			String sql = "UPDATE student_subject SET student_grade = ? WHERE student_id = ? AND subject_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentGrade);
			pstmt.setString(2, studentId);
			pstmt.setInt(3, subjectId);
			result = pstmt.executeUpdate();
	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	
}
