package homework;

import java.util.Arrays;
import java.util.Scanner;

public class 마방진 {

	public static void main(String[] args) {
//	홀수 정사각형의 크기를 입력 받은 후, 가로 세로 대각선의 합이 일정한 마방진을 출력하는 프로그램을 작성하시오.


//	마방진이란 1부터 N*N까지의 숫자를 한 번씩만 써서 정사각형에 배치하여 가로와 세로, 그리고 대각선의 합이 같도록 하는 것이다.


//	다음의 순서에 따라 각 위치에 차례대로 값을 넣는다.
//	1. 첫 번째 숫자인 1을 넣는 위치는 첫 번째 행 가운데이다.
//	2. 숫자가 N의 배수이면 바로 아래의 행으로 이동하여 다음의 수를 넣고
//	3. 그렇지 않으면 왼쪽 위로 이동하여 다음의 숫자를 넣는다. 만약 행이 첫 번째를 벗어나면 마지막 행으로 이동하고, 열이 첫 번째를 벗어나면 마지막 열로 이동한다.
		
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("정사각형의 크기를 입력해주세요(홀수)>");
		int size = Integer.parseInt(sc.nextLine());
		int[][] mabangjin = new int[size][size];
		int garo = size/2;
		int sero = 0;
		//for문을 정사각형의 넓이만큼 돌림.(이중포문 사용X) 그리고 i를 넣는 숫자로 사용함
		//열 변수, 행변수를 따로 정해줌
		for(int i=1; i<=size*size; i++) {
			if(i==1) {
				mabangjin[sero][garo] = i;
			} else if(i%size ==0) {
				if(sero+2>size) {
					sero =0;
					mabangjin[sero][garo] = i;
				}else {
					sero += 1;
					mabangjin[sero][garo] = i;
				}
			} else {
				if(sero-1<0) {
					sero = size -1;
				} else {
					sero = sero -1;
				}
				if(garo-1<0) {
					garo = size -1;
				} else {
					garo = garo - 1;
				}
				mabangjin[sero][garo] = i;
			}
		}
		for(int i=0; i< mabangjin.length; i++) {
			System.out.print(Arrays.toString(mabangjin[i]));
			System.out.println();
		}
	}
}
