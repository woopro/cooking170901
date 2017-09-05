<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<title>회원 가입 페이지</title>
<!-- 다음 우편번호 검색 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/js/post.js"></script>
<script src="${pageContext.request.contextPath}/js/password.js"></script>
</head>
<body>
	<article class="container" style="margin-top: 5%">
		<div class="page-header">
			<h1>
				회원가입 <small>sign page</small>
			</h1>
		</div>
		<div class="col-md-6 col-md-offset-3">
			<form role="form" action="sign" method="post">
				<div class="user-input">
					<label for="InputEmail">이메일 주소</label> <input name="email"
						type="email" class="form-control" id="InputEmail" value="${email}">
				</div>
				<div class="user-input">
					<label for="InputPassword1">비밀번호</label> <input name="password"
						type="password" class="form-control" id="InputPassword1"
						placeholder="비밀번호">
				</div>
				<div class="user-input">
					<label for="InputPassword2">비밀번호 확인</label> <input name="pw2"
						type="password" class="form-control" id="InputPassword2"
						placeholder="비밀번호 확인">
					<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
				</div>
				<div class="user-input">
					<label for="username">이름</label> <input name="name" type="text"
						class="form-control" id="username" value="${name}">
				</div>
				<div class="user-input">
					<label for="username">전화번호 입력</label>
				</div>
				<div class="user-input">
					<input name="phone" type="tel" class="form-control" id="username"
						placeholder="- 없이 입력해 주세요"> <SELECT NAME=telecom SIZE=1>
						<OPTION VALUE=kt>KT</OPTION>
						<OPTION VALUE=lg>LG</OPTION>
						<OPTION VALUE=sk>SK</OPTION>
						<OPTION VALUE=알뜰폰 SELECTED>알뜰폰</OPTION>
					</SELECT>
				</div>
				<div class="user-input">
					<input type="text" id="sample6_postcode" placeholder="우편번호"
						name="post" class="form-control"> <input type="button"
						onclick="sample6_execDaumPostcode()" value="우편번호 찾기"
						class="btn btn-info"> <input type="text"
						id="sample6_address" placeholder="검색된 주소" name="addr1"
						class="form-control"> <input type="text"
						id="sample6_address2" placeholder="상세 주소" name="addr2"
						class="form-control"> <span id="guide" style="color: #999">
					</span>
				</div>
		<div class="user-input text-center">
			<button type="submit" class="btn btn-info">
				회원가입<i class="fa fa-check spaceLeft"></i>
			</button>
			<button type="button" class="btn btn-warning"
				onclick="location.href='home'">
				가입취소<i class="fa fa-times spaceLeft"></i>
			</button>
		</div>
		</form>
		<hr>
		</div>
	</article>
</body>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>