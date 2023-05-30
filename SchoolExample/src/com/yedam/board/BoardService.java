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
	

	
	public void createBoard(int part, String content) {
		List<Board> list = BoardDAO.getInstance().searchBoard(part, content);
		//list주면 게시글 출력하는 메소드 만들기
		if(part ==1) {
			getBoard(list, getLastPageTitle(content));
		} else if(part ==2) {
			getBoard(list, getLastPageTitleContent(content));
		} else if(part ==3) {
			getBoard(list, getLastPageWriterId(content));	
		}

	}
	public void selectmenu(int part, String content) {
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
	
	//제목 검색 마지막 페이지 구하기
	public int getLastPageTitle(String content) {
		int lastPage = 1;
		if(BoardDAO.getInstance().getLastPageTitle(content) % 5 ==0) {
			lastPage = BoardDAO.getInstance().getLastPageTitle(content) / 5;
		} else {
			lastPage = BoardDAO.getInstance().getLastPageTitle(content) / 5 + 1;
		}
		
		return lastPage;
	}
	
	//제목+내용 검색 마지막 페이지 구하기
	public int getLastPageTitleContent(String content) {
		int lastPage = 0;
		if(BoardDAO.getInstance().getLastPageTitleContent(content) % 5 == 0) {
			lastPage = BoardDAO.getInstance().getLastPageTitleContent(content) / 5;
		} else {
			lastPage = BoardDAO.getInstance().getLastPageTitleContent(content) / 5 + 1;
		}		
		return lastPage;
	}
	
	//작성자 검색 마지막 페이지 구하기
	public int getLastPageWriterId(String content) {
		int lastPage = 0;
		if(BoardDAO.getInstance().getLastPageWriterId(content) % 5 == 0) {
			lastPage = BoardDAO.getInstance().getLastPageWriterId(content) / 5;
		} else {
			lastPage = BoardDAO.getInstance().getLastPageWriterId(content) / 5 + 1;
		}	
		return lastPage;
	}
	
	
	//검색, 추천게시판 틀(전체 게시판 제외)
	public void getBoard(List<Board> list, int lastPage) {
		System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                         게  시  판                                                                  │");
		System.out.println("|  id    제목                        내용                                         댓글수 조회수  작성자 id      작성일자       추천수  |");
		System.out.println("=======================================================================================================================================");
		
		if(list.size() == 0) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				//no출력
				System.out.printf("| %2s  ", list.get(i).getBoardNum());
				
				
				//제목 출력
				String title = "";
				int korean = 0;
				for(int j=0; j<list.get(i).getTitle().length(); j++) {
					
					if((list.get(i).getTitle().charAt(j) >= 'a' && list.get(i).getTitle().charAt(j) <= 'z') || (list.get(i).getTitle().charAt(j) >= 'A' && list.get(i).getTitle().charAt(j) <= 'Z') || (list.get(i).getTitle().charAt(j) >= '0' && list.get(i).getTitle().charAt(j) <= '9')) {
						korean = korean + 0;
					} else {
						korean = korean + 1;
					}
				}
				
				if(korean >= 11) {
					title = list.get(i).getTitle().substring(0, 10) + "...";
					System.out.print("  " + title + " ");
				} else if(korean == 10) {
					System.out.printf("  %-10s    ",list.get(i).getTitle());
				} else if(korean == 9) {
					System.out.printf("  %-11s    ", list.get(i).getTitle());
				} else if(korean == 8) {
					System.out.printf("  %-12s    ", list.get(i).getTitle());
				} else if(korean ==7) {
					System.out.printf("  %-13s    ", list.get(i).getTitle());
				} else if(korean == 6) {
					System.out.printf("  %-14s    ", list.get(i).getTitle());
				} else if(korean == 5) {
					System.out.printf("  %-15s    ", list.get(i).getTitle());
				} else if(korean == 4) {
					System.out.printf("  %-15s    ", list.get(i).getTitle());
				} else if(korean == 3) {
					System.out.printf("  %-16s    ", list.get(i).getTitle());
				} else if(korean == 2) {
					System.out.printf("  %-18s    ", list.get(i).getTitle());
				} else if(korean == 1) {
					System.out.printf("  %-19s    ", list.get(i).getTitle());
				} else if(korean == 0) {
					if(list.get(i).getTitle().length() > 17) {
						title = list.get(i).getTitle().substring(0, 17) + "..";
						System.out.print("  " + title);
					}else {
						System.out.printf("  %-20s   ", list.get(i).getTitle());																		
					}
				}
				
				
				//내용 출력
				
				String content = "";
				int korean2 = 0;
				for(int k=0; k<list.get(i).getContent().length(); k++) {
					
					if((list.get(i).getContent().charAt(k) >= 'a' && list.get(i).getContent().charAt(k) <= 'z') || (list.get(i).getContent().charAt(k) >= 'A' && list.get(i).getContent().charAt(k) <= 'Z') || (list.get(i).getContent().charAt(k) >= '0' && list.get(i).getContent().charAt(k) <= '9')) {
						korean2 = korean2 + 0;
					} else {
						korean2 = korean2 + 1;
					}
				}
				
				
				if(korean2 >= 23) {
					content = list.get(i).getContent().substring(0, 22) + "..";
					System.out.print("  " + content + "  ");
				} else if(korean2 == 23) {
					System.out.printf("  %-21s  ",list.get(i).getContent());
				} else if(korean2 == 22) {
					System.out.printf("  %-22s   ", list.get(i).getContent());
				} else if(korean2 == 21) {
					System.out.printf("  %-23s   ", list.get(i).getContent());
				} else if(korean2 == 20) {
					System.out.printf("  %-24s   ", list.get(i).getContent());
				} else if(korean2 == 19) {
					System.out.printf("  %-25s   ", list.get(i).getContent());
				} else if(korean2 == 18) {
					System.out.printf("  %-26s   ", list.get(i).getContent());
				} else if(korean2 == 17) {
					System.out.printf("  %-27s   ", list.get(i).getContent());
				} else if(korean2 == 16) {
					System.out.printf("  %-28s   ", list.get(i).getContent());
				} else if(korean2 == 15) {
					System.out.printf("  %-29s   ", list.get(i).getContent());
				} else if(korean2 == 14) {
					System.out.printf("  %-30s   ", list.get(i).getContent());
				} else if(korean2 == 13) {
					System.out.printf("  %-31s   ", list.get(i).getContent());
				} else if(korean2 == 12) {
					System.out.printf("  %-32s   ", list.get(i).getContent());
				} else if(korean2 == 11) {
					System.out.printf("  %-33s   ", list.get(i).getContent());
				} else if(korean2 == 10) {
					System.out.printf("  %-34s   ", list.get(i).getContent());
				} else if(korean2 == 9) {
					System.out.printf("  %-35s   ", list.get(i).getContent());
				} else if(korean2 == 8) {
					System.out.printf("  %-36s   ", list.get(i).getContent());
				} else if(korean2 == 7) {
					System.out.printf("  %-37s   ", list.get(i).getContent());
				} else if(korean2 == 6) {
					System.out.printf("  %-38s   ", list.get(i).getContent());
				} else if(korean2 == 5) {
					System.out.printf("  %-38s   ", list.get(i).getContent());			
				} else if(korean2 == 4) {
					System.out.printf("  %-39s    ", list.get(i).getContent());			
				} else if(korean2 == 3) {
					System.out.printf("  %-41s   ", list.get(i).getContent());			
				} else if(korean2 == 2) {
					System.out.printf("  %-41s    ", list.get(i).getContent());			
				} else if(korean2 == 1) {
					System.out.printf("  %-43s   ", list.get(i).getContent());			
				} else if(korean2 == 0) {
					if(list.get(i).getContent().length() > 30) {
						content = list.get(i).getContent().substring(0, 30) + "..";
						System.out.print("  " + content);
					}else {
						System.out.printf("  %-44s   ", list.get(i).getContent());																		
					}
				}
				
				
				
				//댓글수 출력
				System.out.printf("   %-3s ", getCountReply(list.get(i).getBoardNum()));
				//조회수 출력
				System.out.printf("  %-3s  ", list.get(i).getViewCnt());
				//작성자 id출력
				System.out.printf("   %-8s  ", list.get(i).getWriterId());
				//작성일자 출력
				System.out.printf("   %-10s   ", list.get(i).getWrDate());
				//추천수 출력
				System.out.printf("    %3s    |", list.get(i).getRecommend());
				System.out.println();
			}
				
		}
		if(list.size()<5) {
			for(int i=list.size()+1; i<=5; i++) {
				System.out.println("| " + i + "번째 글 없음                                                                                                                        │");
				
			}
		}
		
		
		System.out.println("└─────────────────────────────────────────────────────────────"+ (currentPage+1) + " / " + lastPage +"────────────────────────────────────────────────────────────────────┘");
		
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
				System.out.println("등록한 게시글의 고유 번호 : " + list.get(i).getBoardNum() + "         등록한 게시글의 제목 : " + list.get(i).getTitle());
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
				System.out.println("등록한 댓글의 고유 번호 : " + list.get(i).getCommentNum() + "         등록한 댓글 내용 : " + list.get(i).getContent());
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
				System.out.println("등록한 대댓글의 고유 번호 : " + list.get(i).getRecommentNum() + "           등록한 대댓글 내용 : " + list.get(i).getContent());
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
		System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                         게  시  판                                                                  │");
		System.out.println("|  no    제목                        내용                                         댓글수 조회수  작성자 id      작성일자       추천수 |");
		System.out.println("=======================================================================================================================================");
	}
		
	//게시판의 내용출력
	public void getAllBoard() {
		List<Board> list = BoardDAO.getInstance().getAllBoard();
		if(list.size() == 0) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			for(int i=0; i<list.size(); i++) {
				//no출력
				System.out.printf("| %2s  ", i+1);
				
				
				//제목 출력
				String title = "";
				int korean = 0;
				for(int j=0; j<list.get(i).getTitle().length(); j++) {
					
					if((list.get(i).getTitle().charAt(j) >= 'a' && list.get(i).getTitle().charAt(j) <= 'z') || (list.get(i).getTitle().charAt(j) >= 'A' && list.get(i).getTitle().charAt(j) <= 'Z') || (list.get(i).getTitle().charAt(j) >= '0' && list.get(i).getTitle().charAt(j) <= '9')) {
						korean = korean + 0;
					} else {
						korean = korean + 1;
					}
				}
				
				if(korean >= 11) {
					title = list.get(i).getTitle().substring(0, 10) + "..";
					System.out.print("  " + title );
				} else if(korean == 10) {
					System.out.printf("  %-10s    ",list.get(i).getTitle());
				} else if(korean == 9) {
					System.out.printf("  %-11s    ", list.get(i).getTitle());
				} else if(korean == 8) {
					System.out.printf("  %-12s       ", list.get(i).getTitle());
				} else if(korean ==7) {
					System.out.printf("  %-13s    ", list.get(i).getTitle());
				} else if(korean == 6) {
					System.out.printf("  %-14s    ", list.get(i).getTitle());
				} else if(korean == 5) {
					System.out.printf("  %-15s   ", list.get(i).getTitle());
				} else if(korean == 4) {
					System.out.printf("  %-15s    ", list.get(i).getTitle());
				} else if(korean == 3) {
					System.out.printf("  %-16s    ", list.get(i).getTitle());
				} else if(korean == 2) {
					System.out.printf("  %-18s    ", list.get(i).getTitle());
				} else if(korean == 1) {
					System.out.printf("  %-19s    ", list.get(i).getTitle());
				} else if(korean == 0) {
					if(list.get(i).getTitle().length() > 17) {
						title = list.get(i).getTitle().substring(0, 17) + "..";
						System.out.print("  " + title);
					}else {
						System.out.printf("  %-20s   ", list.get(i).getTitle());																		
					}
				}
				
				
				//내용 출력
				
				String content = "";
				int korean2 = 0;
				for(int k=0; k<list.get(i).getContent().length(); k++) {
					
					if((list.get(i).getContent().charAt(k) >= 'a' && list.get(i).getContent().charAt(k) <= 'z') || (list.get(i).getContent().charAt(k) >= 'A' && list.get(i).getContent().charAt(k) <= 'Z') || (list.get(i).getContent().charAt(k) >= '0' && list.get(i).getContent().charAt(k) <= '9')) {
						korean2 = korean2 + 0;
					} else {
						korean2 = korean2 + 1;
					}
				}
				
				if(korean2 >= 23) {
					content = list.get(i).getContent().substring(0, 22) + "..";
					System.out.print("  " + content + " ");
				} else if(korean2 == 23) {
					System.out.printf("  %-21s  ",list.get(i).getContent());
				} else if(korean2 == 22) {
					System.out.printf("  %-22s   ", list.get(i).getContent());
				} else if(korean2 == 21) {
					System.out.printf("  %-23s   ", list.get(i).getContent());
				} else if(korean2 == 20) {
					System.out.printf("  %-24s   ", list.get(i).getContent());
				} else if(korean2 == 19) {
					System.out.printf("  %-25s   ", list.get(i).getContent());
				} else if(korean2 == 18) {
					System.out.printf("  %-26s   ", list.get(i).getContent());
				} else if(korean2 == 17) {
					System.out.printf("  %-27s   ", list.get(i).getContent());
				} else if(korean2 == 16) {
					System.out.printf("  %-28s   ", list.get(i).getContent());
				} else if(korean2 == 15) {
					System.out.printf("  %-29s   ", list.get(i).getContent());
				} else if(korean2 == 14) {
					System.out.printf("  %-30s   ", list.get(i).getContent());
				} else if(korean2 == 13) {
					System.out.printf("  %-31s   ", list.get(i).getContent());
				} else if(korean2 == 12) {
					System.out.printf("  %-32s   ", list.get(i).getContent());
				} else if(korean2 == 11) {
					System.out.printf("  %-33s   ", list.get(i).getContent());
				} else if(korean2 == 10) {
					System.out.printf("  %-34s   ", list.get(i).getContent());
				} else if(korean2 == 9) {
					System.out.printf("  %-35s   ", list.get(i).getContent());
				} else if(korean2 == 8) {
					System.out.printf("  %-36s   ", list.get(i).getContent());
				} else if(korean2 == 7) {
					System.out.printf("  %-37s   ", list.get(i).getContent());
				} else if(korean2 == 6) {
					System.out.printf("  %-38s   ", list.get(i).getContent());
				} else if(korean2 == 5) {
					System.out.printf("  %-38s    ", list.get(i).getContent());			
				} else if(korean2 == 4) {
					System.out.printf("  %-39s    ", list.get(i).getContent());			
				} else if(korean2 == 3) {
					System.out.printf("  %-41s   ", list.get(i).getContent());			
				} else if(korean2 == 2) {
					System.out.printf("  %-41s    ", list.get(i).getContent());			
				} else if(korean2 == 1) {
					System.out.printf("  %-43s   ", list.get(i).getContent());			
				}  else if(korean2 == 0) {
					if(list.get(i).getContent().length() > 30) {
						content = list.get(i).getContent().substring(0, 30) + "..";
						System.out.print("  " + content);
					}else {
						System.out.printf("  %-44s   ", list.get(i).getContent());																		
					}
				}
				
				
				
				//댓글수 출력
				System.out.printf("   %-3s ", getCountReply(list.get(i).getBoardNum()));
				//조회수 출력
				System.out.printf("  %-3s  ", list.get(i).getViewCnt());
				//작성자 id출력
				System.out.printf("   %-8s  ", list.get(i).getWriterId());
				//작성일자 출력
				System.out.printf("   %-10s   ", list.get(i).getWrDate());
				//추천수 출력
				System.out.printf("    %3s    |", list.get(i).getRecommend());
				System.out.println();
			}
				
		}
		if(list.size()<BoardInApplication.pageSize) {
			for(int i=list.size()+1; i<=BoardInApplication.pageSize; i++) {
				System.out.println("| " + i + "번째 글 없음                                                                                                                        │");
				
			}
		}
	}
		
	public void getEndTle() {
		int lastPage = getLastPage();
		System.out.println("└─────────────────────────────────────────────────────────────"+ (currentPage+1) + " / " + lastPage +"───────────────────────────────────────────────────────────────────┘");
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
			//System.out.println(board.getBoardNum() + " " + board.getTitle() + " " + board.getContent() + " " + board.getViewCnt() + " " + board.getWriterId() + " " + board.getWrDate() + " " +board.getRecommend());
			//게시글 번호
			
			System.out.println("게시글 id> " + board.getBoardNum() + "    ||    작성자id> " + board.getWriterId() + "    ||    조회수> " + board.getViewCnt() + "    ||    작성일자> " + board.getWrDate() + "    ||    추천수> " + board.getRecommend());
			
			System.out.println();
			
			System.out.println("글 제목 : " + board.getTitle());
			System.out.println("글 내용 : " + board.getContent());
			System.out.println();
		}
	}
	
	//상세 게시글 1-1(id로 게시글 검색)
	public void getPost2(int selectNo) {
		Board board = BoardDAO.getInstance().getPost2(selectNo);
		if(board == null) {
			System.out.println("해당 게시글이 없습니다.");
		} else {
			//System.out.println(board.getBoardNum() + " " + board.getTitle() + " " + board.getContent() + " " + board.getViewCnt() + " " + board.getWriterId() + " " + board.getWrDate() + " " +board.getRecommend());
			//게시글 번호
			
			System.out.println("게시글 id> " + board.getBoardNum() + "    ||    작성자id> " + board.getWriterId() + "    ||    조회수> " + board.getViewCnt() + "   ||    작성일자> " + board.getWrDate() + "    ||    추천수> " + board.getRecommend());
			
			System.out.println();
			
			System.out.println("글 제목 : " + board.getTitle());
			System.out.println("글 내용 : " + board.getContent());
			System.out.println();
		}
	}
	
	
	//상세 게시글2(해당 게시물의 댓글), 상세 게시글3(해당 게시물의 대댓글
	public void getReply() {
		List<Reply> list = BoardDAO.getInstance().getReply();
		if(list.size() == 0) {
			System.out.println("등록된 댓글이 없습니다  ");
		} else {
			System.out.println("==================================================================================================================");
			for(int i=0; i<list.size(); i++) {
				
				if(list.get(i).getStatus().equals("X")) {
					System.out.println("삭제된 댓글입니다.");
				} else {
					System.out.println(list.get(i).getContent() + "         ( 작성자 : " + list.get(i).getWriterId() + "   댓글작성일 : " + list.get(i).getWrDate() + "   댓글번호 : " + list.get(i).getCommentNum() + " )");
				}
				
				
				List<ReReply> list2 = BoardDAO.getInstance().getReReply(list.get(i).getCommentNum());
				if(list2.size()==0) {
					System.out.println();
				} else {
					
					for(int j=0; j<list2.size(); j++) {
						for(int k=0; k<=j; k++) {
							System.out.print("\t");
						}
						//System.out.println("작성자 id : " + list2.get(j).getWriterId() + " 작성일 : " + list2.get(j).getWrDate() + " 내용 : " + list2.get(j).getContent());
						System.out.println("|-> " + list2.get(j).getContent() +"         ( 작성자 : " + list2.get(j).getWriterId() + "   대댓글작성일 : " + list2.get(j).getWrDate() + " )");
					}
				}
			}
		}
		System.out.println("==================================================================================================================");
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
			boolean flag2 = false;
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getStatus().equals("O")) {
					flag2 = true;
				}
			}
			if(!flag2) {
				System.out.println("대댓글을 달 댓글이 없습니다.");
				return;
			}
			System.out.println("대댓글을 달 댓글의 번호 입력(숫자)>");
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
				int spot = 0;
				for(int i=0; i<list.size(); i++) {
					if(Integer.parseInt(commentId) == list.get(i).getCommentNum()) {
						flag = true;
						spot = i;
					}
				}
				if(list.get(spot).getStatus() == "X") {
					flag = false;
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
