package com.yedam.coffee;

import java.util.List;
import java.util.Scanner;

public class CoffeeService {
	Scanner sc = new Scanner(System.in);
	//1. 메뉴 조회 | 2. 메뉴 상세 조회 | 3.메뉴 등록 | 4. 판매 | 5. 메뉴 삭제 | 6. 매출 | 7. 종료
	
	//1.메뉴 조회
	public void getCoffeeList() {
		List<Coffee> list = CoffeeDAO.getInstance().getCoffeeList();
		for(int i=0; i<list.size(); i++) {
			System.out.println("메뉴 : " + list.get(i).getCoffeeMenu() + " 가격 : " + list.get(i).getCoffeePrice());
		}
	}
	//2.메뉴 상세 조회
	public void getCoffee() {
		System.out.println("메뉴 입력>");
		String coffeeName = sc.nextLine();
		Coffee coffee = CoffeeDAO.getInstance().getCoffee(coffeeName);
		
		if(coffee == null) {
			System.out.println("메뉴가 없습니다.");
		} else {
			System.out.println("메뉴 : " + coffee.getCoffeeMenu() + " 가격 : " + coffee.getCoffeePrice() + "설명 : " + coffee.getCoffeeExplain());
		}
	}
	//3.메뉴 등록
	public void insertMenu() {
		Coffee coffee = new Coffee();
		
		System.out.println("메뉴 입력>");
		coffee.setCoffeeMenu(sc.nextLine());
		
		System.out.println("가격 입력>");
		coffee.setCoffeePrice(Integer.parseInt(sc.nextLine()));
		
		System.out.println("설명 입력>");
		coffee.setCoffeeExplain(sc.nextLine());
		
		int result = CoffeeDAO.getInstance().insertMenu(coffee);
		if(result > 0) {
			System.out.println("메뉴 등록 완료");
		} else {
			System.out.println("메뉴 등록 실패");
		}
	}
	
	//4.판매
	public void saleCoffe() {
		System.out.println("메뉴 입력>");
		String coffeeName = sc.nextLine();
		int result = CoffeeDAO.getInstance().saleCoffee(coffeeName);
		
		if(result > 0) {
			System.out.println("판매량 완료");
		} else {
			System.out.println("판매량 실패");
		}
	}
	
	//5.메뉴 삭제
	public void deleteCoffee() {
		System.out.println("메뉴 입력>");
		String coffeeName = sc.nextLine();
		int result = CoffeeDAO.getInstance().deleteCoffee(coffeeName);
		if(result > 0) {
			System.out.println("메뉴 삭제 완료");
		} else {
			System.out.println("메뉴 삭제 실패");
		}
	}
	
	//6.매출
	public void totalSale() {
		List<Coffee> list = CoffeeDAO.getInstance().getCoffeeList();
		for(int i=0; i<list.size(); i++) {
			System.out.println("메뉴 : " + list.get(i).getCoffeeMenu() + ", 판매개수 : " + list.get(i).getCoffeeSales() + "개, 판매 금액 : " + (list.get(i).getCoffeeSales()*list.get(i).getCoffeePrice()) + "원");
		}
		System.out.println("총 매출액 : " + CoffeeDAO.getInstance().sumTotal() + "원");
	}
}
