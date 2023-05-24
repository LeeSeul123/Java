package com.yedam.board;

import java.util.List;
import java.util.Scanner;

import com.yedam.exe.BoardInApplication;

public class BoardService {
	Scanner sc = new Scanner(System.in);
	public static int currentPage = 0;
	
	//BoardOut
	
	//게시판에 글 쓰기
	public void writing() {
		Board board = new Board();
		System.out.println("글 제목을 입력해주세요 > ");
		board.setTitle(sc.nextLine());
		System.out.println("글 내용을 입력해주세요 > ");
		board.setContent(sc.nextLine());
		
		int result = BoardDAO.getInstance().writing(board);
		
		if(result > 0) {
			System.out.println("글 등록 완료");
		} else {
			System.out.println("글 등록 실패");
		}
	}
	
	//게시글 검색
	public void searchBoard() {
		System.out.println("1. 제목 | 2. 제목+내용 | 3. 작성자 id");
		System.out.println("검색할 부분을 선택해주세요 >");
		int part = Integer.parseInt(sc.nextLine());
		System.out.println("검색할 내용을 선택해주세요 >");
		String content = sc.nextLine();
		
		List<Board> list = BoardDAO.getInstance().searchBoard(part, content);
		
		if(list.size() == 0) {
			System.out.println("등록된 게시글 없음");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).getBoardNum() + " " + list.get(i).getTitle() + " " + list.get(i).getContent() + " " + list.get(i).getViewCnt() + " " + list.get(i).getWriterId() + " " + list.get(i).getWrDate() + " " +list.get(i).getRecommend());
				
			}
			System.out.println();
		}
	}
	
	//추천 게시글 보기
	public void getMyreco() {
		List<Board> list = BoardDAO.getInstance().getMyreco();
		
		if(list.size() == 0) {
			System.out.println("추천 한 게시글이 없습니다.");
		} else {
			for(int i=0; i<list.size();i++) {
				System.out.println(list.get(i).getBoardNum() + " " + list.get(i).getTitle() + " " + list.get(i).getContent() + " " + list.get(i).getViewCnt() + " " + list.get(i).getWriterId() + " " + list.get(i).getWrDate() + " " +list.get(i).getRecommend());
			}
			System.out.println();
		}
	}
	
	//BoardIn
	
	//게시판 틀
	public void getStartTle() {
		System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                   게 시 판 😀                                                            │");
		System.out.println("│  no  |        제목        |                       내용                       |댓글수|조회수|  작성자id  |     작성일자     |추천수|");
	}
		
	//게시판의 내용출력
	public void getAllBoard() {
		List<Board> list = BoardDAO.getInstance().getAllBoard();
		if(list.size() == 0) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.printf("| %3s  ", list.get(i).getBoardNum());
				System.out.printf("| %-16s", list.get(i).getTitle());
				//글자수가 44개를초과시 ...으로 표시하는 if문 작성
				System.out.printf("| %-44s", list.get(i).getContent());
				//댓글수 나중에 11을 수정 
				System.out.printf("| %3s", 11);
				System.out.printf("| %4s", list.get(i).getViewCnt());
				System.out.printf("| %-10s", list.get(i).getWriterId());
				System.out.printf("|   %-10s   ", list.get(i).getWrDate());
				System.out.printf("| %3s|", list.get(i).getRecommend());
				System.out.println();
			}
				
		}
	}
		
	public void getEndTle() {
		System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	
	
	//BoardDetail
	
	//상세 게시글1(해당 게시물의 내용)
	public void getPost(int selectNo) {
		Board board = BoardDAO.getInstance().getPost(selectNo);
		if(board == null) {
			System.out.println("해당 게시글이 없습니다.");
		} else {
			System.out.println(board.getBoardNum() + " " + board.getTitle() + " " + board.getContent() + " " + board.getViewCnt() + " " + board.getWriterId() + " " + board.getWrDate() + " " +board.getRecommend());			
		}
	}
	
	//상세 게시글2(해당 게시물의 댓글), 상세 게시글3(해당 게시물의 대댓글
	public void getReply(int selectNo) {
		List<Reply> list = BoardDAO.getInstance().getReply(selectNo);
		if(list.size() == 0) {
			System.out.println("등록된 댓글이 없습니다.");
		} else {
			System.out.println("댓글 : ");
			for(int i=0; i<list.size(); i++) {
				System.out.println("작성자 id : " + list.get(i).getWriterId() + " 작성일 : " + list.get(i).getWrDate() + "댓글 고유번호 : " + list.get(i).getCommentNum());
				System.out.println("내용 : " +list.get(i).getContent());
				List<ReReply> list2 = BoardDAO.getInstance().getReReply(list.get(i).getCommentNum());
				if(list2.size()==0) {
					System.out.println("등록된 대댓글이 없습니다.");
				} else {
					System.out.println("대댓글 : ");
					for(int j=0; j<list2.size(); j++) {
						for(int k=0; k<=j; k++) {
							System.out.print("\t");
						}
						System.out.println("작성자 id : " + list2.get(j).getWriterId() + " 작성일 : " + list2.get(j).getWrDate() + " 내용 : " + list2.get(j).getContent());
					}
				}
			}
		}
	}
	
	//추천하기
	public void setReco(int selectNo) {
		int result = BoardDAO.getInstance().setReco(selectNo);
		
		if(result > 0) {
			System.out.println("추천 완료");
		} else {
			System.out.println("추천 실패..");
		}
	}
	
	//댓글 입력
	public void insertReply(int selectNo) {
		Reply reply = new Reply();
		reply.setBoardNum(currentPage * BoardInApplication.pageSize + selectNo);
		System.out.println("댓글 내용을 입력해주세요 >");
		reply.setContent(sc.nextLine());
		
		int result = BoardDAO.getInstance().insertReply(reply);
		
		if(result > 0) {
			System.out.println("댓글 등록 성공");
		} else {
			System.out.println("댓글 등록 실패..");
		}
	}
	
	//대댓글 입력
	public void insertReReply() {
		ReReply reReply = new ReReply();
		System.out.println("대댓글을 달 댓글의 id 입력>");
		//있는 댓글인지 확인
		reReply.setCommentNum(Integer.parseInt(sc.nextLine()));
		System.out.println("대댓글 내용 입력>");
		reReply.setContent(sc.nextLine());
		
		int result = BoardDAO.getInstance().insertReReply(reReply);
		
		if(result > 0) {
			System.out.println("대댓글 등록 성공");
		} else {
			System.out.println("대댓글 등록 실패..");
		}
	}
}
