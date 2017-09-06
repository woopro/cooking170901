<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>My Cooking</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/order.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/write.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="${pageContext.request.contextPath}/js/logincheck.js"></script>
</head>
<body>
	<div class="page">
		<header>
			<div align="center">
				<h1>My Cooking</h1>
				<c:if test="${cookie.memberEmail.value eq 'admin@myrecipe.com'}">
				<a href="admin">관리자페이지</a>
				</c:if>
			</div>
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="#">이용방법</a></li>
					<li><a href="mlist">메뉴</a></li>
					<li><a href="#">공지사항</a></li>
					<li id="QnA"><a href="<c:url value="blist"/>">고객센터</a></li>
					
					<!-- 로그인 전 -->
					<c:choose>
					<c:when test="${empty cookie.memberEmail.value}">
					<li style="float: right"><a href="login">로그인</a></li>
					<li style="float: right"><a href="login">회원가입</a></li>
					</c:when>
					<c:otherwise>
					<!-- 로그인 후 -->
					<li style="float: right"><a href="logout">로그아웃</a></li>
					<li style="float: right"><a href="info">마이페이지</a></li>
					</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</header>
		<main>