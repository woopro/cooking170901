<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/view/template/header.jsp" %>
<div>
	<h1>관리자 메뉴입니다!!!</h1>
</div>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head> 
	<body>
		<h3><a href="memberlist">회원 목록</a></h3>
		<h3><a href="mnqlist">문의 목록</a></h3>
		<h3><a href="mmodi">메뉴 목록</a></h3>
		<h3><a href="olist">주문 현황</a></h3>
		<h3><a href="msales">월별 매출액</a></h3>
	</body>
</html>
<%@ include file="/WEB-INF/view/template/footer.jsp" %>
