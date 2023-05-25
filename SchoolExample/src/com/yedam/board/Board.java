package com.yedam.board;

import java.sql.Date;

public class Board {
//	board_num NUMBER primary key,
//	title VARCHAR2(100) NOT NULL,
//	content VARCHAR2(1000),
//	writer_id VARCHAR2(10) NOT NULL,
//	wr_date DATE default sysdate,
//	view_cnt NUMBER default 0,
//	recommend NUMBER default 0
	private int boardNum;
	private String title;
	private String content;
	private String writerId;
	private Date wrDate;
	private int viewCnt;
	private int recommend;
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
}
