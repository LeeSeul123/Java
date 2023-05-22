package com.yedam.exe;

import java.util.Scanner;

import com.yedam.account.AccountService;
import com.yedam.member.MemberService;

public class MemberApp {
	//고객의 업무
	Scanner sc = new Scanner(System.in);
	MemberService ms = new MemberService();
	AccountService as = new AccountService();
	
	public MemberApp() {
		memberRun();
	}
	
	private void memberRun() {
		//고객의 메뉴
		//-> 계좌 조회, 입출금(한번에 구현), 이체
		boolean flag = true;
		while(flag) {
			menu();
			String selectNo = sc.nextLine();
			switch(selectNo) {
			case "1":
				ms.getAccountInfo();
				break;
			case "2":
				as.inoutMoney();
				break;
			case "3":
				as.transferMoney();
				break;
			case "4":
				flag = false; 
				MemberService.memberInfo = null;
				System.out.println("개인 업무 종료");
				break;
			}
		}
		
	}
	
	private void menu() {
		System.out.println("1. 계좌 조회 | 2. 입출금 | 3. 이체 | 4. 뒤로가기");
	}
}
