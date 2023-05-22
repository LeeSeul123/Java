package com.yedam.exe;

import java.util.Scanner;

import com.yedam.coffee.CoffeeService;

public class Application {
	Scanner sc = new Scanner(System.in);
	String selectNo = "";
	CoffeeService cs = new CoffeeService();
	
	Application(){
		boolean run = true;
		while(run) {
			menu();
			
			switch(selectNo) {
			case "1":
				cs.getCoffeeList();
				break;
			case "2":
				cs.getCoffee();
				break;
			case "3":
				cs.insertMenu();
				break;
			case "4":
				cs.saleCoffe();
				break;
			case "5":
				cs.deleteCoffee();
				break;
			case "6":
				cs.totalSale();
				break;
			case "7":
				System.out.println("end of prog");
				run = false;
				break;
			default :
				System.out.println("번호를 잘못 입력하였습니다. 1~7번 사이로 입력해주세요");
			}
		}
		
	}

	private void menu() {
		System.out.println("1. 메뉴 조회 | 2. 메뉴 상세 조회 | 3.메뉴 등록 | 4. 판매 | 5. 메뉴 삭제 | 6. 매출 | 7. 종료");
		System.out.println("메뉴 입력>");
		selectNo = sc.nextLine();
	}
}
