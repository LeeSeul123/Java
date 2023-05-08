package com.yedam.variable;

public class Variable01 {
	
	public static void main(String[] args) {
		//변수 선언
		int value = 0;
		
		int result = value + 10;  //??? + 10. 커서올리면 오류가 나옴. 콘솔창에서 맨끝에거 눌러보고 해결못하겠으면 value 커서올렸을 때 나오는 링크누르기
		
		System.out.println(result);
		
		//변수 사용
		int hour = 3;
		int minute = 5;
		
		System.out.println(hour + "시간" + minute + "분");
		
		//시간 -> 분으로 변환
		int totalMinute = (hour*60) + minute;
		System.out.println("총 " + totalMinute + "분");
		
		//변수 복사
		int x = 3;
		int y;
		
		System.out.println("바꾸기 전" + x);
		
		//y = 3;
		y = x;
		System.out.println("바꾼 후 : " + x + y);
		
		//swap 기법(변수의 값을 교환하는 방식)
		//x와 y의 값을 바꾼다.
		//x = y -> x의 값과 y의 값이 같다.
		//대신 담아줄 수 있는 변수를 생성 => z
		x = 3;
		y = 5;
		int z = 0;
		z = x;//1) x -> z ( z = 3 )
		x = y;//2) y -> x ( x = 3->5 )
		y = z;//3) y -> z ( y = 5->3 )
		
		//변수의 사용 범위
		int v1 = 15; // v1-> main문 안에서 만들어짐
		if(v1 > 10) {
			int v2;
			v2 = v1 - 10;
		}
//		int v3 = v1+v2+5;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
