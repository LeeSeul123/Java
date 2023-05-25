package com.yedam.board;

import java.sql.Date;

public class ReReply {
//	recomment_num NUMBER primary key,
//	comment_num NUMBER references reply(comment_num),
//	writer_id VARCHAR2(10) NOT NULL,
//	wr_date DATE default sysdate,
//	content VARCHAR2(100)
	private int recommentNum;
	private int commentNum;
	private String writerId;
	private Date wrDate;
	private String content;
	
	
	public int getRecommentNum() {
		return recommentNum;
	}
	public void setRecommentNum(int recommentNum) {
		this.recommentNum = recommentNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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
