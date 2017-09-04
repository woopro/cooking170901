<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/recipe/css/main.css">


<html>
<head>
<title>디자인</title>
<style>
div {
	border: 1px;
	border-color: darkgreen;
	border-style: dotted;
}

.block {
	margin: 20px;
	display: block;
}

.in-flex {
	display: inline-flex;
}

.form {
	width: 400px;
}
</style>

</head>
<body>
	<div align="center">
		<h1>상품 등록 화면</h1>
	</div>
	<div class="item_body">
		<div align="center">
			<form id="madd" action="madd" method="POST">
				<div class="in-flex">
					메뉴 이미지 <br>
					<div class="block">
						<p>메인 이미지</p>
						<img src="http://via.placeholder.com/150x150" height="150"
							width="150">
						<p>
							<input type="button" value="등록">
						</p>
					</div>
					<div class="block">
						<p>재료 이미지</p>
						<img src="http://via.placeholder.com/150x150" height="150"
							width="150">
						<p>
							<input type="button" value="등록">
						</p>
					</div>
					<div class="block">
						<p>레시피 이미지</p>
						<img src="http://via.placeholder.com/150x150" height="150"
							width="150">
						<p>
							<input type="file" name="recipe" required>
						</p>
					</div>
				</div>
				<div class="block form" align="center">
					메뉴 상세 정보<br>
					<table>
						<tbody>
							<tr>
								<th>메뉴 명 :</th>
								<th><input type="text" name="name" value="${mdto.name}"></th>
							</tr>
							<tr>
								<th>메뉴 종류 :</th>
								<th>
								<input type="radio" name="type" value="밥">밥
								<input type="radio" name="type" value="찌개">찌개
								<input type="radio" name="type" value="국">국
								</th>
							</tr>
							<tr>
								<th>가격 :</th>
								<th><input type="number" name="price"></th>
							</tr>
							<tr>
								<th>옵션1 :</th>
								<th><input type="text" name="op1" placeholder="고기 추가"></th>
							</tr>
							<tr>
								<th>옵션1 가격 :</th>
								<th><input type="number" name="op1_price"></th>
							</tr>
							<tr>
								<th>옵션2 :</th>
								<th><input type="text" name="op2" placeholder="야채 추가"></th>
							</tr>
							<tr>
								<th>옵션2 가격 :</th>
								<th><input type="number" name="op2_price"></th>
							</tr>
							<tr>
								<th>옵션3 :</th>
								<th><input type="text" name="op3" placeholder="양념 추가"></th>
							</tr>
							<tr>
								<th>옵션3 가격 :</th>
								<th><input type="number" name="op3_price"></th>
							</tr>
							<tr>
								<th>상태 :</th>
								<th>
								<input type="radio" name="stat" value="sell" checked="checked">판매중 
								<input type="radio" name="stat" value="soldout">판매중지
								</th>
							</tr>
						</tbody>
					</table>
					<br>
					<br> <input type="hidden" name="menu_no value=${mdto.menu_no}">
					<input type="hidden" name="stat_grade" value="일반">
					<div align="center">
						<input type="submit" value="등록"> 
						<input type="button" value="취소" onclick="location.href='mlist'">
					</div>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>



<%@ include file="/WEB-INF/view/template/footer.jsp"%>