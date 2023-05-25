package com.yedam.board;

import java.sql.Date;

public class Reply {
//	comment_num NUMBER primary key,
//	board_num NUMBER references board(board_num),
//	writer_id VARCHAR2(10) NOT NULL,
//	wr_date DATE default sysdate,
//	content VARCHAR2(100)
	private int commentNum;
	private int boardNum;
	private String writerId;
	private Date wrDate;
	private String content;
	
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public Date getWrDate() {
		return wrDate;
	}
	public void setWrDate(Date wrDate) {
		this.wrDate = wrDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
