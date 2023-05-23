package com.yedam.studentSubject;

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
					sql = "SELECT s.subject_name, s.subject_starttime, s.subject_endtime FROM subject as s, student_subject as ss ON(s.subject_id = ss.subject_id) WHERE student_id = ? AND subject_week = ? AND (subject_starttime > ? OR subject_endtime < ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, MemberService.memberInfo.getMemberId());
					pstmt.setInt(2, subject.getSubjectWeek());
					pstmt.setInt(3, subject.getSubjectEndTime());
					pstmt.setInt(4, subject.getSubjectStartTime());
					rs = pstmt.executeQuery();
					Subject subject3 = null;
					if(rs.next()) {
						subject3 = new Subject();
						subject3.setSubjectName(rs.getString("s.subject_name"));
						subject3.setSubjectStartTime(rs.getInt("s.subject_starttime"));
						subject3.setSubjectEndTime(rs.getInt("s.subject_endtime"));
					}
					
					if(subject3 == null) {
						sql = "INSERT INTO student_subject VALUES(?,?,null,?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, MemberService.memberInfo.getMemberId());
						pstmt.setInt(2, subjectId);
//						pstmt.setInt(3, subject3.getSubjectEndTime()-subject3.getSubjectStartTime());
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
}
