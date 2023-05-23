package com.yedam.subject;

import java.util.List;
import java.util.Scanner;

public class SubjectService {
	Scanner sc = new Scanner(System.in);
	
	//교수 메뉴
	//수업 등록
	public void insertSubject() {
		int subjectId = Integer.parseInt(sc.nextLine());
		int result = SubjectDAO.getInstance().insertSubject(subjectId);
		
		if(result > 0) {
			System.out.println("수업 등록 성공");
		} else {
			System.out.println("수업 등록 실패");
		}
	}
	
	//수업 취소
	public void deleteSubject() {
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
					str1 += list.get(i).getSubjectName() + " : " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else if(list.get(i).getSubjectWeek() == 2) {
					str2 += list.get(i).getSubjectName() + " : " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else if(list.get(i).getSubjectWeek() == 3) {
					str3 += list.get(i).getSubjectName() + " : " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else if(list.get(i).getSubjectWeek() == 4) {
					str4 += list.get(i).getSubjectName() + " : " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				} else {
					str5 += list.get(i).getSubjectName() + " : " + list.get(i).getSubjectStartTime() + "시 ~ " + list.get(i).getSubjectEndTime() + "시\n";
				}
			}
		}
		
		System.out.print("월요일 : " + (str1.length() == 0 ? "등록된 수업 없음\n" : str1));
		System.out.print("화요일 : " + (str2.length() == 0 ? "등록된 수업 없음\n" : str2));
		System.out.print("수요일 : " + (str3.length() == 0 ? "등록된 수업 없음\n" : str3));
		System.out.print("목요일 : " + (str4.length() == 0 ? "등록된 수업 없음\n" : str4));
		System.out.print("금요일 : " + (str5.length() == 0 ? "등록된 수업 없음\n" : str5));
	}
	
	//성적 입력
	public void setGrade() {
		
	}
}
