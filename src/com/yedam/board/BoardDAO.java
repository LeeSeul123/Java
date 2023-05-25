package com.yedam.board;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.exe.BoardInApplication;
import com.yedam.member.MemberService;

public class BoardDAO extends DAO{
	private static BoardDAO bDao = null;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(bDao == null) {
			bDao = new BoardDAO();
		}
		
		return bDao;
	}
	
	
	
	
	//BoardOut
	
	//게시글 작성
	public int writing(Board board) {
		int result = 0;
		
		try {
			conn();
			String sql = "INSERT INTO board VALUES((SELECT count(*)+1 FROM board),?,?,?,sysdate,0,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, MemberService.memberInfo.getMemberId());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//게시글 검색
	public List<Board> searchBoard(int part, String content){
		List<Board> list = new ArrayList<>();
		Board board = null;
		try {
			conn();
			String sql = "";
			content = "%" + content + "%";
			if(part == 1) {
				sql = "SELECT * FROM board WHERE title LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
			} else if(part == 2) {
				sql = "SELECT * FROM board WHERE title Like ? OR content LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setString(2, content);
			} else {
				sql = "SELECT * FROM board WHERE writer_id LIKE ?";
				pstmt.setString(1, content);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new Board();
				board.setBoardNum(rs.getInt("board_num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriterId(rs.getString("writer_id"));
				board.setWrDate(rs.getDate("wr_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setRecommend(rs.getInt("recommend"));
				list.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//내가 추천한 글 보기
	public List<Board> getMyreco(){
		List<Board> list = new ArrayList<>();
		Board board = null;
		
		try {
			conn();
			String sql = "SELECT * FROM recommendation JOIN board USING (board_num) WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new Board();
				board.setBoardNum(rs.getInt("board_num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriterId(rs.getString("writer_id"));
				board.setWrDate(rs.getDate("wr_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setRecommend(rs.getInt("recommend"));
				list.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	
	//BoardIn
	
	//전체 게시글 보기
	public List<Board> getAllBoard(){
		List<Board> list = new ArrayList<>();
		Board board = null;
			
		try {
			conn();
			
			String sql = "SELECT  * FROM (SELECT ROWNUM num, b.* FROM (SELECT * FROM board ORDER BY wr_date desc)b) WHERE num BETWEEN ? AND ?";
			//String sql = "SELECT ROWNUM, b.* FROM board b WHERE board_num BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardService.currentPage * BoardInApplication.pageSize +1);
			pstmt.setInt(2, (BoardService.currentPage+1) * BoardInApplication.pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board = new Board();
				board.setBoardNum(rs.getInt("board_num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriterId(rs.getString("writer_id"));
				board.setWrDate(rs.getDate("wr_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setRecommend(rs.getInt("recommend"));
				list.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//댓글 수 구하기
	public int getCountReply(int boardNum) {
		int count = 0;
		
		try {
			conn();
			String sql = "SELECT count(comment_num)+count(recomment_num) FROM reply FULL OUTER JOIN rereply USING (comment_num) WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(comment_num)+count(recomment_num)");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return count;
	}
	
	//마지막 페이지 구하기에 사용할 메소드
	public int getLastBoardNum() {
		int lastBoardNum = 0;
		
		try {
			conn();
			String sql = "SELECT max(board_num) FROM board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lastBoardNum = rs.getInt("max(board_num)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return lastBoardNum;
	}
	
	
	//BoardDetail
	
	
	//상세 글조회1(글의 내용. 댓글 등 제외)
	public Board getPost(int selectNo) {
		Board board = null;
		int result = 0;
			
		try {
			conn();
			String sql = "UPDATE board SET view_cnt = view_cnt + 1 WHERE board_num = (SELECT board_num FROM (SELECT ROWNUM num, b.* FROM (SELECT * FROM board ORDER BY wr_date desc)b) WHERE num = ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardService.currentPage * BoardInApplication.pageSize + selectNo);
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("조회수 수정 실패..");
			} else {
				sql = "SELECT * FROM board WHERE board_num = (SELECT board_num FROM (SELECT ROWNUM num, b.* FROM (SELECT * FROM board ORDER BY wr_date desc)b) WHERE num = ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, BoardService.currentPage * BoardInApplication.pageSize + selectNo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board = new Board();
					board.setBoardNum(rs.getInt("board_num"));
					board.setTitle(rs.getString("title"));
					board.setContent(rs.getString("content"));
					board.setWriterId(rs.getString("writer_id"));
					board.setWrDate(rs.getDate("wr_date"));
					board.setViewCnt(rs.getInt("view_cnt"));
					board.setRecommend(rs.getInt("recommend"));
					BoardService.currentBoard = board;
				}
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
			
		return board;
	}
	
	//상세글 조회2(댓글 불러오기)
	public List<Reply> getReply() {
		List<Reply> list = new ArrayList<>();
		Reply reply = null;
		
		try {
			conn();
			String sql = "SELECT * FROM reply WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardService.currentBoard.getBoardNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reply = new Reply();
				reply.setBoardNum(rs.getInt("board_num"));
				reply.setCommentNum(rs.getInt("comment_num"));
				reply.setWriterId(rs.getString("writer_id"));
				reply.setWrDate(rs.getDate("wr_date"));
				reply.setContent(rs.getString("content"));
				list.add(reply);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
		
	}
	
	//상세글 조회3(대댓글 조회)
	public List<ReReply> getReReply(int commentNum){
		List<ReReply> list = new ArrayList<>();
		ReReply reReply = null;
		
		try {
			conn();
			String sql = "SELECT * FROM rereply WHERE comment_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reReply = new ReReply();
				reReply.setCommentNum(rs.getInt("comment_num"));
				reReply.setRecommentNum(rs.getInt("recomment_num"));
				reReply.setWriterId(rs.getString("writer_id"));
				reReply.setWrDate(rs.getDate("wr_date"));
				reReply.setContent(rs.getString("content"));
				list.add(reReply);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return list;
	}
	
	
	
	
	//추천하기
	public int setReco() {
		int result = 0;
		
		try {
			conn();
			String sql = "INSERT INTO recommendation VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			pstmt.setInt(2, BoardService.currentBoard.getBoardNum());
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("추천 누르기 실패");
			} else {
				sql = "UPDATE board SET recommend = recommend + 1 WHERE board_num= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, BoardService.currentBoard.getBoardNum());
				result = pstmt.executeUpdate();
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//추천 취소
	public int deleteReco() {
		int result = 0;
		
		try {
			conn();
			String sql = "DELETE FROM recommendation WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardService.currentBoard.getBoardNum());
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("추천 취소 실패");
			} else {
				sql = "UPDATE board SET recommend = recommend-1 WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, BoardService.currentBoard.getBoardNum());
				result = pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result ;
	}
	
	//댓글 입력
	public int insertReply(String content) {
		int result = 0;
		
		try {
			conn();
			String sql = "INSERT INTO reply VALUES((SELECT count(*)+1 FROM reply), ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardService.currentBoard.getBoardNum());
			pstmt.setString(2, MemberService.memberInfo.getMemberId());
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//대댓글 입력
	public int insertReReply(ReReply reReply) {
		int result = 0;
		
		try {
			conn();
			String sql = "INSERT INTO rereply VALUES((SELECT count(*)+1 FROM rereply), ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reReply.getCommentNum());
			pstmt.setString(2, MemberService.memberInfo.getMemberId());
			pstmt.setString(3, reReply.getContent());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
}
