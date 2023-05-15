package com.yedam.oop;

public class Car {

	
//필드 : 객체를 만들었을 때 정보를 담아주는 역할
		//필드 선언(후 사용은 실행클래스(Application01에서)
		String name; //자동차 이름 필드
		int price; //가격
		//String company; //브랜드, 제조사
		
		//데이터 넣는 방법2번)클래스 내부에서 필드에 정보를 입력
		String company = "벤틀리"; //브랜드, 제조사
//생성자 : 객체로 만들때 내가 하고 싶은 행위를 정의
//      : 클래스 이름과 동일해야함
//      : 다양한 방법으로 객체를 생성하기 위해서 생성자를 오버로딩
		
		//생성자의 오버로딩(서로다른 생성자 만들기)
		//기본 생성자
		Car(){
			
		}
		
		Car(String name, int price){
			
		}
//		Car(String company, int price){
//			위에 생성자때문에 오류남
//		}
		//매개변수의 개수가 같으면 순서를 바꿔주면 서로 다른 생성자로 인식한다.
		Car(int price, String company){
			
		}
	
		Car(String name){
			this(name, 10000, "현대"); //name을 받고 씌워서 해당하는 생성자 호출
			//this.name = name;
			System.out.println("자동차 이름만 입력"); //객체생성할때 출력문, 반복문 넣고싶은거 아무거나 가능
		}
		
		Car(int price){
			
		}
		
		Car(String name, int price, String company){
			this.name = name;
			this.price = price;
			this.company = company;
			System.out.println("모든 필드 초기화");
		}
		
//메소드 : 객체가 사용할 수 있는 기능
		//반환값(리턴값)이 없는 메소드 : void
		void run() {
			System.out.println("자동차가 달립니다.");
		}
		
		//문자열을 반환(리턴)하는 메소드
		String info() {
			String data = company + " : " + name + " : " + price;
			return data;
		}
		
	

}
