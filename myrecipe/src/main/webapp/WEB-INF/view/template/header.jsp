<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!Doctype HTML>
<html>
<head>
	<title>My Cooking</title>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/order.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/write.css">
</head>
<body>
	<div class="page">
		<header>
			<div align="center">
				<h1>My Cooking</h1>
			</div>
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="#">이용방법</a></li>
					<li><a href="mlist">메뉴</a></li>
					<li><a href="#">공지사항</a></li>
					<li id="QnA"><a href="<c:url value="blist"/>">고객센터</a> <!-- 	                    	<ul> -->
						<!-- 	                    		<li><a href="#">자주 묻는 질문</a><li> --> <%-- 	                    		<li><a href="${pageContext.request.contextPath}/board/list">질의 게시판</a></li> --%>
						<!-- 	                    	</ul> --></li>

					<li style="float: right"><a href="login">로그인</a></li>
					<li style="float: right"><a href="logout">로그아웃</a></li>
					<li style="float: right"><a href="login">회원가입</a></li>
					<li style="float: right"><a href="info">마이페이지</a></li>
					<li style="float: right"><a href="login">회원가입</a></li>
					<li style="float: right"><a href="list">회원목록</a></li>
					<li style="float: right"><a href="madd">상품등록</a></li>
				</ul>
			</nav>
		</header>
		<main>