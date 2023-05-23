package com.yedam.studentSubject;

import java.util.List;
import java.util.Scanner;

import com.yedam.subject.Subject;


public class StudentService {
	Scanner sc = new Scanner(System.in);
	
	//수강신청
	public void insertSubject() {
		//현재 수강한 과목
		System.out.print("현재 수강신청한 과목 > ");
		int sum = 0;
		List<Subject> list = StudentSubjectDAO.getInstance().getAllSubject();
		if(list.size()==0) {
			System.out.println("없음 😅😅");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.print(list.get(i).getSubjectName() + "     ");
				sum += list.get(i).getSubjectEndTime();
			}
			System.out.println();
		}
		
		System.out.println("현재 신청 학점 : " + sum);
		
		System.out.println("번호를 입력해주세요(겹치는 시간 불가능, 10학점 이상 불가능)>");
		int subjectId = Integer.parseInt(sc.nextLine());
		int result = StudentSubjectDAO.getInstance().insertSubject(subjectId);
		
		if(result > 0) {
			System.out.println("수업 등록 성공");
		} else {
			System.out.println("수업 등록 실패");
		}
		
	}
	
	//수강 취소
	public void deleteSubject() {
		//현재 수강한 과목
		System.out.print("현재 수강신청한 과목 > ");
		List<Subject> list = StudentSubjectDAO.getInstance().getAllSubject();
		if(list.size()==0) {
			System.out.println("없음 😅😅");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.print("과목 코드 : " + list.get(i).getSubjectId() + " 과목 명 : " + list.get(i).getSubjectName());
			}
			System.out.println();
		}
		
		int subjectId = Integer.parseInt(sc.nextLine());
		int result = StudentSubjectDAO.getInstance().deleteSubject(subjectId);
		
		if(result > 0) {
			System.out.println("수업 삭제 성공");
		} else {
			System.out.println("수업 삭제 실패");
		}
	}
	
	//내 성적 조회
	public void getGradeList() {
		
		//현재 수강한 과목
		System.out.print("현재 수강신청한 과목 > ");
		List<Subject> list = StudentSubjectDAO.getInstance().getAllSubject();
		if(list.size()==0) {
			System.out.println("없음 😅😅");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.print(list.get(i).getSubjectName() + "     ");
			}
			System.out.println();
		}
		
		
		List<StudentSubject> list2 = StudentSubjectDAO.getInstance().getGradeList();
		
		if(list.size() == 0) {
			System.out.println("성적이 아직 나오지 않았습니다.");
		} else {
			double sum = 0;
			for(int i=0; i<list.size(); i++) {
				if(list2.get(i).getStudentGrade().equals("A")) {
					sum += 4;
				} else if(list2.get(i).getStudentGrade().equals("B")) {
					sum += 3;
				} else {
					sum += 2;
				}
			}
			System.out.println("성적 결과가 나온 과목의 수는 " + list2.size() + "개고 지금까지의 평균은 " + sum/list2.size() + "점 입니다.");
		}
	}
	
}
