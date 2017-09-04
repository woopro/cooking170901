<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/recipe/css/main.css">
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>

<html>
<head>
<title>Menu List</title>
<style>
	div {
		align-content: center;
		text-align: center;
		border: 1px;
		border-color: darkgreen;
		border-style: solid;
	}
	.menu_grid {
		width: 70%;
		height: auto;
		border: 1px;
		border-color: red;
		border-style: dotted;
	}
	.menu_item {
		width: 32%;
		height: 200px;
		background-color: darkgray;
		display: inline-block;
	}
	.admin_option {
		width: 200px;
		height: 50px;
		background-color: aqua;
	}
	function .menu_item().{
		.menu_item().cl
	};
	
</style>
</head>
<body>
	<div>
		<h1>Menu List !</h1>
		<div>
			<div class="admin_option" align="right">
				관리자 옵션 <a href="madd"><input type="button" value="메뉴 추가"></a>
			</div>
			<div class="menu_grid center">
				<c:forEach var="mdto" items="${mdto}">
					<a href="mdetail?no=${mdto.menu_no}">
						<div class="menu_item">
							<img src="http://via.placeholder.com/200x110">
							<h4>메뉴 : <label>${mdto.name}</label></h4>
							<h4>가격 : <label>${mdto.price}</label></h4>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>

<%@ include file="/WEB-INF/view/template/footer.jsp"%>
