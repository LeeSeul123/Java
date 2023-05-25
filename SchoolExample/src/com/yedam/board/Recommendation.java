package com.yedam.board;

public class Recommendation {
//	member_id VARCHAR2(10) references member(member_id),
//	board_num NUMBER references board(board_num)
	
	private String memberId;
	private int boardNum;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	
	
}
