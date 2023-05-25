package com.yedam.board;

import java.util.List;
import java.util.Scanner;

import com.yedam.exe.BoardInApplication;

public class BoardService {
	Scanner sc = new Scanner(System.in);
	public static int currentPage = 0;
	public static Board currentBoard = null; //현재 상세조회중인 게시글
	
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
	
	//추천누른 게시글 보기
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
	
	//게시글 삭제
	public void deleteBoard() {
		List<Board> list = BoardDAO.getInstance().getMyBoard();
		if(list.size() == 0) {
			System.out.println("등록하신 게시글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.println("등록한 게시글의 고유 번호 : " + list.get(i).getBoardNum() + "등록한 게시글의 제목 : " + list.get(i).getTitle());
			}
			System.out.println("삭제하실 게시글의 고유 번호를 입력해주세요>");
			int boardNum = Integer.parseInt(sc.nextLine());
			boolean flag = false;
			for(int i=0; i<list.size(); i++) {
				
				if(list.get(i).getBoardNum() == boardNum) {
					flag = true;
				}
			}
			if(flag) {
				int result = BoardDAO.getInstance().deleteBoard(boardNum);
				
				if(result > 0) {
					System.out.println("게시글 삭제 완료");
				} else {
					System.out.println("게시글 삭제 실패..");
				}
			} else {
				System.out.println("본인이 등록한 게시글이 아닙니다.");
			}
			
		}
	}
	
	
	//댓글 삭제
	public void deleteReply() {
		List<Reply> list = BoardDAO.getInstance().getReplyList();
		if(list.size() == 0) {
			System.out.println("등록하신 댓글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.println("등록한 댓글의 고유 번호 : " + list.get(i).getCommentNum() + "등록한 댓글 내용 : " + list.get(i).getContent());
			}
			System.out.println("삭제하실 댓글의 고유 번호를 입력해주세요>");
			int commentNum = Integer.parseInt(sc.nextLine());
			boolean flag = false;
			for(int i=0; i<list.size(); i++) {
				
				if(list.get(i).getCommentNum() == commentNum) {
					flag = true;
				}
			}
			if(flag) {
				int result = BoardDAO.getInstance().deleteReply(commentNum);
				
				if(result > 0) {
					System.out.println("댓글 삭제 완료");
				} else {
					System.out.println("댓글 삭제 실패..");
				}
			} else {
				System.out.println("본인이 등록한 댓글이 아닙니다.");
			}
			
		}
	}
	
	//대댓글 삭제
	public void deleteReReply() {
		List<ReReply> list = BoardDAO.getInstance().getReReplyList();
		if(list.size() == 0) {
			System.out.println("등록하신 대댓글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.println("등록한 대댓글의 고유 번호 : " + list.get(i).getRecommentNum() + "등록한 대댓글 내용 : " + list.get(i).getContent());
			}
			System.out.println("삭제하실 대댓글의 고유 번호를 입력해주세요>");
			int recommentNum = Integer.parseInt(sc.nextLine());
			boolean flag = false;
			for(int i=0; i<list.size(); i++) {
				
				if(list.get(i).getRecommentNum() == recommentNum) {
					flag = true;
				}
			}
			if(flag) {
				int result = BoardDAO.getInstance().deleteReReply(recommentNum);
				
				if(result > 0) {
					System.out.println("대댓글 삭제 완료");
				} else {
					System.out.println("대댓글 삭제 실패..");
				}
			} else {
				System.out.println("본인이 등록한 대댓글이 아닙니다.");
			}
			
		}
	}
	
	
	
	//BoardIn
	
	//게시판 틀
	public void getStartTle() {
		System.out.println("┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                   게 시 판 😀                                                   |         │");
		System.out.println("|  no     제목                  내용                                        댓글수 조회수  작성자 id   작성일자   추천수   |");
		System.out.println("===========================================================================================================================");
		//System.out.println("│  no  |        제목        |                       내용                       |댓글수|조회수|  작성자id  |     작성일자     |추천수|");
	}
		
	//게시판의 내용출력
	public void getAllBoard() {
		List<Board> list = BoardDAO.getInstance().getAllBoard();
		if(list.size() == 0) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				System.out.printf("| %2s  ", i+1);
				
				
				//영어만 있으면 16자
				String title = "";
				
//				if(list.get(i).getTitle().length() > 11) {
//					title = list.get(i).getTitle().substring(0, 11) + "..";
//					
//				} else {
//					title = list.get(i).getTitle();
//				}
				int korean = 0;
				for(int j=0; j<list.get(i).getTitle().length(); j++) {
					
					if((list.get(i).getTitle().charAt(j) > 'a' && list.get(i).getTitle().charAt(j) < 'z') || (list.get(i).getTitle().charAt(j) > 'A' && list.get(i).getTitle().charAt(j) < 'Z') || (list.get(i).getTitle().charAt(j) > '0' && list.get(i).getTitle().charAt(j) < '9')) {
						//한글이 아닐때
					} else {
						korean = korean + 1;
					}
				}
				if(korean >= 11) {
					title = list.get(i).getTitle().substring(0, 11) + "..";
					System.out.print("  " + title);
				} else if(korean == 10) {
					System.out.printf("  %-7s   ",list.get(i).getTitle());
				} else if(korean == 9) {
					System.out.printf("  %-8s   ", list.get(i).getTitle());
				} else if(korean == 8) {
					System.out.printf("  %-9s   ", list.get(i).getTitle());
				} else if(korean ==7) {
					System.out.printf("  %-10s   ", list.get(i).getTitle());
				} else if(korean == 6) {
					System.out.printf("  %-11s   ", list.get(i).getTitle());
				} else if(korean == 5) {
					System.out.printf("  %-12s   ", list.get(i).getTitle());
				} else if(korean == 4) {
					System.out.printf("  %-13s   ", list.get(i).getTitle());
				} else if(korean == 3) {
					System.out.printf("  %-14s   ", list.get(i).getTitle());
				} else if(korean == 2) {
					System.out.printf("  %-15s   ", list.get(i).getTitle());
				} else if(korean == 1) {
					System.out.printf("  %-16s   ", list.get(i).getTitle());
				} else if(korean == 0) {
					if(list.get(i).getTitle().length() > 16) {
						title = list.get(i).getTitle().substring(0, 16) + "..";
						System.out.printf("  " + title);
					} else {
						System.out.printf("  %-18s   ", list.get(i).getTitle());												
					}
					
				}
				
				
				
				//글자수가 44개를초과시 ...으로 표시하는 if문 작성
				System.out.printf(" %-46s", list.get(i).getContent());
				//댓글수 나중에 11을 수정 
				System.out.printf(" %-3s", getCountReply(list.get(i).getBoardNum()));
				System.out.printf(" %-3s", list.get(i).getViewCnt());
				System.out.printf("   %-8s", list.get(i).getWriterId());
				System.out.printf("   %-10s   ", list.get(i).getWrDate());
				System.out.printf(" %3s|", list.get(i).getRecommend());
				System.out.println();
			}
				
		}
		if(list.size()<BoardInApplication.pageSize) {
			for(int i=list.size()+1; i<=BoardInApplication.pageSize; i++) {
				System.out.println("| " + i + "번째 글 없음                                                                                                             │");
				
			}
		}
	}
		
	public void getEndTle() {
		int lastPage = getLastPage();
		System.out.println("└─────────────────────────────────────────────────────"+ (currentPage+1) + " / " + lastPage +"───────────────────────────────────────────────────────────────┘");
	}
	
	//댓글 수 구하기
	public int getCountReply(int boardNum) {
		int count = BoardDAO.getInstance().getCountReply(boardNum);
		
		return count;
	}
	
	//마지막 페이지 구하기
	public int getLastPage() {
		int lastPage = 0;
		int lastBoardNum = BoardDAO.getInstance().getLastBoardNum();
		if(lastBoardNum % BoardInApplication.pageSize == 0) {
			lastPage = lastBoardNum / BoardInApplication.pageSize;
		} else {
			lastPage = lastBoardNum / BoardInApplication.pageSize + 1;
		}
		
		return lastPage;
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
	public void getReply() {
		List<Reply> list = BoardDAO.getInstance().getReply();
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
	
	//추천하기 or 추천취소
	public boolean recoCheck() {
		boolean check = true;
		List<Board> list = BoardDAO.getInstance().getMyreco();
		if(list.size() == 0) {
			check = true;
		} else {
			for(int i=0; i<list.size(); i++) {
				if(currentBoard.getBoardNum() == list.get(i).getBoardNum()) {
					check = false;
				}
			}
		}
		return check;
	}
	
	
	//추천하기
	public void setReco() {
		int result = BoardDAO.getInstance().setReco();
			
		if(result > 0) {
			System.out.println("추천 완료");
		} else {
			System.out.println("추천 실패..");
		}
	}
	
	//추천 취소
	public void deleteReco() {
		int result2 = BoardDAO.getInstance().deleteReco();
		if(result2 > 0) {
			System.out.println("추천 취소 완료");
		} else {
			System.out.println("추천 취소 실패");
		}
	}	

			
	
	
	
	
	
	//댓글 입력
	public void insertReply() {
		System.out.println("댓글 내용을 입력해주세요 >");
		String content = sc.nextLine();
		
		int result = BoardDAO.getInstance().insertReply(content);
		
		if(result > 0) {
			System.out.println("댓글 등록 성공");
		} else {
			System.out.println("댓글 등록 실패..");
		}
	}
	
	//대댓글 입력
	public void insertReReply() {
		ReReply reReply = new ReReply();
		
		List<Reply> list = BoardDAO.getInstance().getReply();
		
		if(list.size()==0) {
			System.out.println("대댓글을 달 댓글이 없습니다.");
		} else {
			System.out.println("대댓글을 달 댓글의 id 입력(숫자)>");
			String commentId = sc.nextLine();
			boolean flag = true;
			
			for(int i=0; i<commentId.length(); i++) {
				if(commentId.charAt(i) > '9' || commentId.charAt(i)<'0') {
					System.out.println("숫자만 입력해주세요");
					flag = false;
				}
			}
			
			if(flag) {
				flag = false;
				for(int i=0; i<list.size(); i++) {
					if(Integer.parseInt(commentId) == list.get(i).getCommentNum()) {
						flag = true;
					}
				}
				
				if(flag) {
					reReply.setCommentNum(Integer.parseInt(commentId));
					System.out.println("대댓글 내용 입력>");
					reReply.setContent(sc.nextLine());
					
					int result = BoardDAO.getInstance().insertReReply(reReply);
					
					if(result > 0) {
						System.out.println("대댓글 등록 성공");
					} else {
						System.out.println("대댓글 등록 실패..");
					}
				} else {
					System.out.println("현재 게시글에 존재하지 않는 댓글 id입니다.");
				}
			}
		}	
	}
	
	
}
