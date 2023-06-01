<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  <h1> EL 표현식 - 사칙연산</h1>
	<!-- 엑박 상관 X 늦게 받아줘서 그럼 -->
	<h2> 5 + 2 = ${ 5 + 2 } </h2>
	<h2> 5 - 2 = ${ 5 - 2 } </h2>
	<!--  <h2> 5 * 2 = ${ 5 * 2 } </h2>
	<h2> 5 / 2 = ${ 5 / 2 } </h2>
	
	<h2> ${"관계연산자" } </h2>
	<h2> 5 > 2 = ${5 > 2 } </h2>-->
	<!--<c:if test="${5 < 3 }"> 학생입니다.</c:if>-->
	<c:if test="${name eq '홍길동' }"> 학생입니다.</c:if>
	
	<!-- name이라는 변수가 비어있으면 정답입니다. -->
	<c:if test="${empty name}"> 정답입니다.</c:if>
	
	
	<h1> JSTL 표현식 </h1>
	<c:set var="name" value="micol"></c:set> <!-- jstl 변수선언 -->
	<c:if test="${name eq 'micol' }"> <!-- eq는 ==와 같다 -->
		<h1>${name }</h1> <!-- 위의 조건이 참이면 micol출력 -->
	</c:if>
	
	
	<c:if test="${name eq null }"> 
		<h1>${name }</h1> <!-- 위의 조건이 참이면 micol출력 -->
	</c:if>
	
	<c:if test="${name ne null }"> <!-- ne는 not equal이므로 null이 아니면 micol출력 -->
		<h1>${name }</h1> <!-- 위의 조건이 참이면 micol출력 -->
	</c:if>
	
	
	<!-- empty: 배열이나 List가 비어있거나 문자열이 null또는 빈 문자열. 숫자 0은 eq(==)으로 비교해야한다. -->
	<c:if test="${empty name }"> 
		<h1>${name }</h1> <!-- 위의 조건이 참이면 micol출력 -->
	</c:if>
	
	<c:if test="${not empty name }"> 
		<h1>${name }</h1> <!-- 위의 조건이 참이면 micol출력 -->
	</c:if>
	
	
	
	<c:choose>
		<c:when test="${name eq '김철수' }"> 김철수 입니다 </c:when>
		<c:when test="${name eq '박영희' }"> 박영희 입니다 </c:when>
		<c:otherwise> ${name } </c:otherwise>
	</c:choose>
	
	
	<c:forEach var = "i" begin="0" end="10"> <!-- item이 없을 때 . step이 없으면 default로 1이 됨-->
		<h1>${i }</h1><br>
	</c:forEach>
	
	
	<c:forEach var = "i" begin="0" end="10" step="2"> <!-- item이 없을 때 . step이 없으면 default로 1이 됨-->
		<h1>${i }</h1><br>
	</c:forEach>
	
	<c:set var="names" value="안녕,하,세,요"></c:set>
	<c:forEach items = "${names }" var = "name"> <!-- item이 없을 때 . step이 없으면 default로 1이 됨-->
		<h1>${name }</h1><br>
	</c:forEach>
	

	
	
	<!-- for(String names:name ){ System.out.println(name);} 과 같음-->
</body>
</html>