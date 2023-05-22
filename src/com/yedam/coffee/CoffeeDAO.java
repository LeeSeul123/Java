package com.yedam.coffee;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class CoffeeDAO extends DAO {
	private static CoffeeDAO cDAO = null;
	
	private CoffeeDAO() {
		
	}
	
	public static CoffeeDAO getInstance() {
		if(cDAO == null) {
			cDAO = new CoffeeDAO();
		}
		return cDAO;
	}
	
	//1.메뉴 조회
	public List<Coffee> getCoffeeList(){
		List<Coffee> list = new ArrayList<>();
		Coffee coffee = null;
		
		try {
			conn();
			String sql = "SELECT * FROM coffee";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				coffee = new Coffee();
				coffee.setCoffeeMenu(rs.getString("coffee_menu"));
				coffee.setCoffeePrice(rs.getInt("coffee_price"));
				coffee.setCoffeeExplain(rs.getString("coffee_explain"));
				coffee.setCoffeeSales(rs.getInt("coffee_sales"));
				list.add(coffee);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//2.메뉴 상세 조회
	public Coffee getCoffee(String coffeeMenu) {
		Coffee coffee = null;
		try {
			conn();
			String sql = "SELECT * FROM coffee WHERE coffee_menu = ?";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, coffeeMenu);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				coffee = new Coffee();
				coffee.setCoffeeMenu(rs.getString("coffee_menu"));
				coffee.setCoffeePrice(rs.getInt("coffee_price"));
				coffee.setCoffeeExplain(rs.getString("coffee_explain"));
				coffee.setCoffeeSales(rs.getInt("coffee_sales"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return coffee;
	}
	
	//3.메뉴 등록
	public int insertMenu(Coffee coffee) {
		int result = 0;
		
		try {
			conn();
			String sql = "INSERT INTO coffee VALUES(?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coffee.getCoffeeMenu());
			pstmt.setInt(2, coffee.getCoffeePrice());
			pstmt.setString(3, coffee.getCoffeeExplain());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//판매
	public int saleCoffee(String coffeeName) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE coffee SET coffee_sales = coffee_sales + 1 WHERE coffee_menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coffeeName);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//메뉴 삭제
	public int deleteCoffee(String coffeeName) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM coffee WHERE coffee_menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coffeeName);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return result;
	}
	
	//총 매출액
	public int sumTotal() {
		int sumTotal = 0;
		try {
			conn();
			String sql = "SELECT SUM(coffee_sales * coffee_price) as sum FROM coffee";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sumTotal = rs.getInt("sum");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		return sumTotal;
	}
}
