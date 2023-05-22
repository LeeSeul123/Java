package com.yedam.account;

import java.util.Scanner;

import com.yedam.common.DAO;
import com.yedam.member.Member;

public class AccountDAO extends DAO{
	
	Scanner sc = new Scanner(System.in);
	
	
	private static AccountDAO aDao = null;
	
	private AccountDAO() {
		
	}
	
	public static AccountDAO getInstance() {
		if(aDao == null) {
			aDao = new AccountDAO();
		}
		
		return aDao;
	}
	
	//1. 고객 등록
	public int insertMember(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO member VALUES(?,?,?,'N')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//2. 계좌 개설
	public int insertAccount(Account account) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO account VALUES(?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account.getAccountId());
			pstmt.setInt(2, account.getAccountBalance());
			pstmt.setString(3, account.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//3. 입출금 (패턴이 동일해서 가능. 쿼리문 안의 부호만 다름)
	public int inoutMoney(Account account, int cmd) { //입금이면 cmd = 1, 출금이면 cmd = 2
		int result = 0;
		try {
			conn();
			//cmd에 따른 입금, 출금 구분
			//통장 잔고를 확인하는 sql(출금했을 때 -가 되면 안되므로)
			String sql2 = "SELECT account_balance FROM account WHERE account_id =?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, account.getAccountId());
			rs = pstmt.executeQuery();
			
			//잔고를 담는 변수
			int balance = 0;
			if(rs.next()) {
				balance = rs.getInt("account_balance"); //출금할때 balance변수 사용
			}
			
			
			
			String sql = "";
			if(cmd == 1) {//입금
				sql = "UPDATE account SET account_balance = account_balance + ? where account_id = ?";
			} else if(cmd == 2) {//출금
				if(balance < account.getAccountBalance()) {//잔고와 출금예정액 비교
					
					sql = null; 
				} else {
					sql = "UPDATE account SET account_balance = account_balance - ? where account_id = ?";
					
				}
			}
			
			if(sql == null) {
				//예금액 < 출금액 예외 처리
				System.out.println("예금액보다 출금액이 많음. 출금 실패");
			} else {//쿼리문이 들어가 있다.
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, account.getAccountBalance());
				pstmt.setString(2, account.getAccountId());
				result = pstmt.executeUpdate();
			}
			
		} catch(Exception e) {
			
		} finally {
			disconn();
		}
		return result;
	}
	
	
	//계좌 이체 - A통장에서 돈이 빠져나간 걸 확인 한 다음 B통장에 돈 +
	public int transferMoney(String toAccount, String fromAccount, int balance) {
		//toAccount => 받는 사람의 계좌
		//fromAccount => 보내는 사람의 계좌
		//balance => 송금하는 금액
		int result = 0;
		try {
			conn();
			//1. 보내는 계좌에서 금액 차감
			String sql = "UPDATE account SET account_balance = account_balance - ? WHERE account_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, fromAccount);
			result = pstmt.executeUpdate();
			//2. 받는 계좌에서 금액 증액
			if(result == 1) {
				System.out.println("정상 출금");
				//받는 사람의 계좌에 돈 입금
				sql = "UPDATE account SET account_balance = account_balance + ? WHERE account_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, balance);
				pstmt.setString(2, toAccount);
				result = pstmt.executeUpdate();
				
			} else {
				System.out.println("출금 실패");
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
		
	}
	
	//계좌 해지
	public int deleteAccount(String accountId) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM account WHERE account_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountId);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
}
