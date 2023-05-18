package homework5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringGame2 {
	private String[] strData = {"Orange", "Game", "Phone", "Smart"};
	//랜덤으로 뽑은 단어가 입력
	private String strAnswer;
	//랜덤으로 뽑은 단어 중에 입력한 문자가 존재한다면 list에 담는다.
	private List<Character> charData; //몇개의 데이터가 들어올지 모르므로 가변적으로 데이터 보관할 수 있는 List에 입력한 문자 보관
	
	public StringGame2() {
		init(); //랜덤데이터 뽑아내고 리스트를 초기화 해줌
	}
	
	
	public void setCharData() {
		showStrData();
		//list의 길이와 문자열의 길이 같다면,, 단어가 가진 문자는 다 찾았다.
		if(charData.size() == strAnswer.length()) {
			System.out.println("문자열을 구성하는 문자를 다 입력했습니다.");
		} else {
			String inputStr = inputData();
			if(strAnswer.indexOf(inputStr.charAt(0)) != -1) {//문자를 여러개썼을수도 있어서 ABCD쓰면 A만 비교함
				System.out.println("문자열을 구성하는 문자입니다.");
				charData.add(inputStr.charAt(0));
			} else {
				System.out.println("문자열을 구성하는 문자가 아닙니다.");
			}
		}
	}

	public void showStrData() { //3번은 이거 사용하면 됨
		System.out.println("현재까지 확인된 문자열의 문자들은 다음과 같습니다.");
		for(char data : charData) { //list에 담긴 데이터 꺼내옴
			System.out.println(data + " ");
		}
		//같은 방법
//		for(int i=0; i<charData.size(); i++) {
//			System.out.print(charData.get(i) + " ");
//		}
		
		System.out.println();
	}

	private String inputData() {
		System.out.println("입력 값>>");
		return new Scanner(System.in).next().toLowerCase(); //값을 입력받은 후 소문자로 변환 후 반환
	}
	
	public void setStrData() {
		showStrData();
		
		String inputStr = inputData();
		if(strAnswer.equals(inputStr)) {
			System.out.println("정답입니다.");
		} else {
			showStrData(inputStr);
		}
	}

	private void showStrData(String inputStr) {
		if(strAnswer.length() == inputStr.length()) {
			for(int i=0; i<strAnswer.length(); i++) {
				if(strAnswer.charAt(i) == inputStr.charAt(i)) {
					System.out.print("O ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		} else {
			System.out.println("입력된 문자열의 길이가 정답과 다릅니다.");
		}
	}
	
	public void init() {
		//랜덤 단어
		strAnswer = getStrAnswer();
		//입력한 내역 초기화
		charData = new ArrayList<>(); //안에있는 내용이 싹 다 초기화됨
	}

	private String getStrAnswer() {
		int idx = (int)(Math.random()*4);
		String str = strData[idx].toLowerCase();
		return str;
	}

	
	
}
