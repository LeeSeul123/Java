import java.util.Scanner;

public class WhileLoop {

	public static void main(String[] args) {
		
		//짝수를 구하는 while문.
		int i = 1;
		while(i <= 100) {
			if(i % 2 == 0) {
				System.out.println(i);
			}
			i++;
		}
		
		//계산기 프로그램
		//무한 루프활용해서 코딩 많이 함
		//반복을 계속하다가 어떠한 조건 -> 프로그램 종료 -> 두 가지
		//1) 변수 활용
		
		boolean run = true;
		
		Scanner sc = new Scanner(System.in);
		
//		while(run) {
//			System.out.println("1. 덧셈 | 2. 뺄셈 | 3. 곱셈 | 4. 종료");
//			System.out.println("입력>");
			
			//int num = Integer.parseInt(sc.nextLine());
			
			//if, switch
			//첫번째 방식) switch
//			switch(num) {
//			case 1:
//				System.out.println("더하고자 하는 두 수 입력");
//				System.out.println("1>");
//				int num1 = Integer.parseInt(sc.nextLine());
//				System.out.println("2>");
//				int num2 = Integer.parseInt(sc.nextLine());
//				
//				System.out.println(num1 + "+" + num2 + "의 결과" + (num1 + num2));
//				break;
//			case 2:
//				break;
//			case 3:
//				break;
//			case 4:
//				System.out.println("프로그램 종료");
//				run = false;
//				break;
//			}
			
			//2번째 방식) if
//			while(true) {    //변수 사용안하고 그냥 true써버림
//				System.out.println("1. 덧셈 | 2. 뺄셈 | 3. 곱셈 | 4. 종료");
//				System.out.println("입력>");
//				
//				int num = Integer.parseInt(sc.nextLine());
//				if(num ==1){
//					
//				} else if(num == 2) {
//					
//				} else if(num == 4) {
//					System.out.println("프로그램 종료");
//					break; //break 활용해서 반복문 종료
//				}
//				
//			}
		
			// 3번째 while문 컨트롤방식) 비교문의 활용
			int num = 0;
			while(num != 4) { //while(num < 4)도 가능.3이랑 4가 다릅니까? => true . 4가 아니면 반복하세요
				System.out.println("1. 덧셈 | 2. 뺄셈 | 3. 곱셈 | 4. 종료");
				System.out.println("입력>");
				
				num = Integer.parseInt(sc.nextLine());
				if(num ==1){
					
				} else if(num == 2) {
					
				} else if(num == 4) {
					System.out.println("프로그램 종료");
					break; //break 활용해서 반복문 종료
				}
			
		    }
			
			//오락실 만들기
			//오락 한 번 -> 500원
			//1500원 투입 -> 3번
			System.out.println("===insert coin===");
			int money = Integer.parseInt(sc.nextLine()); //500, 700
			while(money >= 500) {
				//몫을 활용하여 게임의 기회 => 1500/500 -> 3
				System.out.println((money/500)+"번 기회 남았습니다.");
				System.out.println("1. 가위바위보 | 2. UP & DOWN | 3.종료");
				
				//입력 받음
				System.out.println("메뉴 입력>");
				int gameNo = Integer.parseInt(sc.nextLine());
				
				//게임 진행
				switch (gameNo) {
				case 1:
					System.out.println("가위바위보 진행");
					money = money - 500;
					break;
				case 2:
					System.out.println("UP & DOWN 진행");
					money = money - 500;
					break;
				case 3:
					System.out.println("프로그램 종료");
					money = 0; //money -> 500보다 작게 변경하면 while문 종료됨
					break;
				}
				
				int month;
				
				//do-while문
				do {
					System.out.println("올바른 월을 입력하세요[1-12]");
					month = sc.nextInt();
				}while(month < 1 || month > 12); //올바른 월을 입력할 때까지 반복
				
				System.out.println("사용자가 입력한 월은 " + month);
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
		
		
		
		
		
		
	}

}