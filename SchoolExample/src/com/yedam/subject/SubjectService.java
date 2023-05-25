package com.yedam.subject;

import java.util.List;
import java.util.Scanner;

import com.yedam.studentSubject.StudentSubject;

public class SubjectService {
	Scanner sc = new Scanner(System.in);
	
	//교수 메뉴
	//수업 등록
	public void insertSubject() {
		System.out.print("현재 맡은 수업 > ");
		
		List<Subject> list = SubjectDAO.getInstance().getAllSubject();
		if(list.size() ==0) {
			System.out.print("없음");
			System.out.println();
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.println("과목 코드 : " + list.get(i).getSubjectId() + " 과목 명 : " +list.get(i).getSubjectName());
			}
		}
		System.out.println("수업을 선택해주세요(다른 교수와 중복 불가, 겹치는 시간 불가능)>");
		int subjectId = Integer.parseInt(sc.nextLine());
		int result = SubjectDAO.getInstance().insertSubject(subjectId);
		
		if(result > 0) {
			System.out.println("수업 등록 성공");
		} else {
			System.out.println("수업 등록 실패 😰😰");
		}
	}
	
	//수업 취소
	public void deleteSubject() {
		
		System.out.print("현재 맡은 수업 > ");
		List<Subject> list = SubjectDAO.getInstance().getAllSubject();
		if(list.size() ==0) {
			System.out.print("없음");
			System.out.println();
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.println("과목 코드 : " + list.get(i).getSubjectId() + " 과목 명 : " +list.get(i).getSubjectName());
			}
		}
		System.out.println("삭제할 수업의 번호를 입력해주세요>");
		int subjectId = Integer.parseInt(sc.nextLine());
		int result = SubjectDAO.getInstance().deleteSubject(subjectId);
		
		if(result > 0) {
			System.out.println("수업 삭제 성공");
		} else {
			System.out.println("수업 삭제 실패");
		}
	}
	
	
	
	
	
	
	//시간표 확인
	public void getSchedule() {
		List<Subject> list = SubjectDAO.getInstance().getSchedule();
		
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		String str5 = "";
		
		if(list.size() == 0) {
			System.out.println("등록된 수업 없음");
		} else {
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getSubjectWeek() == 1) {
					str1 += list.get(i).getSubjectName() + " - " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else if(list.get(i).getSubjectWeek() == 2) {
					str2 += list.get(i).getSubjectName() + " - " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else if(list.get(i).getSubjectWeek() == 3) {
					str3 += list.get(i).getSubjectName() + " - " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else if(list.get(i).getSubjectWeek() == 4) {
					str4 += list.get(i).getSubjectName() + " - " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else {
					str5 += list.get(i).getSubjectName() + " - " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				}
			}
		}
		
		System.out.print("월요일 : \n" + (str1.length() == 0 ? "등록된 수업 없음\n" : str1));
		System.out.print("화요일 : \n" + (str2.length() == 0 ? "등록된 수업 없음\n" : str2));
		System.out.print("수요일 : \n" + (str3.length() == 0 ? "등록된 수업 없음\n" : str3));
		System.out.print("목요일 : \n" + (str4.length() == 0 ? "등록된 수업 없음\n" : str4));
		System.out.print("금요일 : \n" + (str5.length() == 0 ? "등록된 수업 없음\n" : str5));
	}
	
	//성적 입력
	public void setGrade() {
		System.out.println("성적 입력할 과목의 아이디를 입력해주세요 > ");
		//본인이 담당한 과목이 맞는지 확인
		int subjectId = Integer.parseInt(sc.nextLine());
		Subject subject = SubjectDAO.getInstance().checkSubject(subjectId);
		if(subject == null) {
			System.out.println("교수님께서 맡으신 과목이 아닙니다.");
		} else {
			List<StudentSubject> list = SubjectDAO.getInstance().getStudentList(subjectId);
			
			if(list.size()==0) {
				System.out.println("이 수업을 등록한 학생이 없습니다.");
			} else {
				System.out.print("이 수업을 등록한 학생의 목록 : ");
				for(int i=0; i<list.size(); i++) {
					System.out.print(list.get(i).getStudentId() + ",    ");
				}
				System.out.println();
				System.out.println("성적 입력할 학생의 id를 입력해주세요>");
				String studentId = sc.nextLine();
				String studentGrade = "";
				while(!(studentGrade.equals("A") || studentGrade.equals("B") || studentGrade.equals("C"))) {
					System.out.println("성적을 입력해주세요(A, B, C중에 선택)");
					studentGrade = sc.nextLine();
				}
				
				int result = SubjectDAO.getInstance().updateGrade(studentId, studentGrade, subjectId);
				if(result > 0) {
					System.out.println("성적 입력 완료");
				} else {
					System.out.println("성적 입력 실패");
				}
			}
		}
	}
}
