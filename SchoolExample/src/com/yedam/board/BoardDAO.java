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
//	public List<Board> searchBoard(int part, String content){
//		List<Board> list = new ArrayList<>();
//		Board board = null;
//		try {
//			conn();
//			String sql = "";
//			content = "%" + content + "%";
//			if(part == 1) {
//				sql = "SELECT * FROM board WHERE title LIKE ?";
//				//sql = "SELECT * FROM(SELECT ROWNUM num, b.* FROM(SELECT * FROM board WHERE title LIKE ? ORDER BY board_num desc) b) WHERE NUM BETWEEN
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, content);
//			} else if(part == 2) {
//				sql = "SELECT * FROM board WHERE title Like ? OR content LIKE ?";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, content);
//				pstmt.setString(2, content);
//			} else {
//				sql = "SELECT * FROM board WHERE writer_id LIKE ?";
//				pstmt.setString(1, content);
//			}
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				board = new Board();
//				board.setBoardNum(rs.getInt("board_num"));
//				board.setTitle(rs.getString("title"));
//				board.setContent(rs.getString("content"));
//				board.setWriterId(rs.getString("writer_id"));
//				board.setWrDate(rs.getDate("wr_date"));
//				board.setViewCnt(rs.getInt("view_cnt"));
//				board.setRecommend(rs.getInt("recommend"));
//				list.add(board);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			disconn();
//		}
//		return list;
//	}
	
	//게시글 검색
	public List<Board> searchBoard(int part, String content) {
		List<Board> list = new ArrayList<>();
		Board board = null;
		
		try {
			conn();
			String sql = "";
			content = "%" + content + "%";
			if(part == 1) {
				 sql = "SELECT * FROM(SELECT ROWNUM num, b.* FROM(SELECT * FROM board WHERE title LIKE ? ORDER BY board_num DESC)b) WHERE num BETWEEN ? AND ?";
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, content);
				 pstmt.setInt(2, BoardService.currentPage*5 + 1);
				 pstmt.setInt(3, BoardService.currentPage*5 + 5);
			} else if(part == 2) {
				sql = "SELECT * FROM(SELECT ROWNUM num, b.* FROM(SELECT * FROM board WHERE title LIKE ? OR content LIKE ? ORDER BY board_num DESC)b) WHERE num BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setString(2, content);
				pstmt.setInt(3, BoardService.currentPage*5 + 1);
				pstmt.setInt(4, BoardService.currentPage*5 + 5);
			} else if(part == 3) {
				sql = "SELECT * FROM(SELECT ROWNUM num, b.* FROM(SELECT * FROM board WHERE writer_id LIKE ? ORDER BY board_num DESC)b) WHERE num BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setInt(2, BoardService.currentPage*5 + 1);
				pstmt.setInt(3, BoardService.currentPage*5 + 5);
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
	
	//내가 쓴 글
	public List<Board> getMyBoard(){
		List<Board> list = new ArrayList<>();
		Board board = null;
		
		try {
			conn();
			String sql = "SELECT * FROM board WHERE writer_id = ?";
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
	
	//게시글 삭제
	public int deleteBoard(int boardNum) {
		int result = 0;
		
		
		try {
			//대댓글 삭제 -> 댓글 삭제 -> 게시글 삭제
			conn();
			String sql = "SELECT recomment_num FROM reply left JOIN rereply USING(comment_num) WHERE board_num = ? AND recomment_num IS NOT NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sql = "DELETE FROM rereply WHERE recomment_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("recomment_num"));
				result = pstmt.executeUpdate();
			}
			
			sql = "DELETE FROM reply WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
			
			sql = "DELETE FROM board WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
			
			
			sql = "SELECT max(board_num) FROM board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int max = 0;
			if(rs.next()) {
				max = rs.getInt("max(board_num)");
			}
			
			int result2 = 0;
			for(int i = boardNum +1; i <= max; i++) {
				sql = "UPDATE board SET board_num = board_num -1 WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				result2 = pstmt.executeUpdate();
				
				sql = "UPDATE reply SET board_num = board_num -1 WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				result2 = pstmt.executeUpdate();
				
				sql = "UPDATE recommendation SET board_num = board_num -1 WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				result2 = pstmt.executeUpdate();
				
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//내가 쓴 댓글, 대댓글
	public List<Reply> getReplyList(){
		List<Reply> list = new ArrayList<>();
		Reply reply = null;
		
		try {
			conn();
			String sql = "SELECT comment_num FROM reply WHERE writer_id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reply = new Reply();
				reply.setCommentNum(rs.getInt("comment_num"));
				reply.setBoardNum(rs.getInt("board_num"));
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
	//댓글 삭제
	public int deleteReply(int commentNum) {
		int result = 0;
		
		try {
			//대댓글 삭제 -> 댓글 삭제
			conn();
			String sql = "DELETE FROM rereply WHERE comment_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNum);
			result = pstmt.executeUpdate();
			
			
			
			sql = "DELETE FROM reply WHERE comment_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNum);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//대댓글 목록
	public List<ReReply> getReReplyList(){
		List<ReReply> list = new ArrayList<>();
		ReReply reReply = null;
		
		try {
			conn();
			String sql = "SELECT recomment_num FROM rereply WHERE writer_id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
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
	
	//대댓글 삭제
	public int deleteReReply(int recommentNum) {
		int result = 0;
			
		try {
			//대댓글 삭제
			conn();
			String sql = "DELETE FROM rereply WHERE recomment_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recommentNum);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
			
		return result;
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
	
	//제목 검색 마지막페이지
	public int getLastPageTitle(String content) {
		int countNum = 0;
		content = "%" + content + "%";
		try {
			conn();
			String sql = "SELECT count(board_num) FROM board WHERE title LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				countNum = rs.getInt("count(board_num)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return countNum;
	}
	
	//제목+내용 검색 마지막 페이지
	public int getLastPageTitleContent(String content) {
		int countNum = 0;
		content = "%" + content + "%";
		try {
			conn();
			String sql = "SELECT count(board_num) FROM board WHERE title LIKE ? OR content LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				countNum = rs.getInt("count(board_num)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return countNum;
	}
	
	//작성자 검색
	public int getLastPageWriterId(String content) {
		int countNum = 0;
		content = "%" + content + "%";
		try {
			conn();
			String sql = "SELECT count(board_num) FROM board WHERE writer_id LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				countNum = rs.getInt("count(board_num)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return countNum;
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
	
	//상세글 조회1-1 게시글 id로 검색
	public Board getPost2(int selectNo) {
		Board board = null;
		int result = 0;
			
		try {
			conn();
			String sql = "UPDATE board SET view_cnt = view_cnt + 1 WHERE board_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectNo);
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("조회수 수정 실패..");
			} else {
				sql = "SELECT * FROM board WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, selectNo);
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
