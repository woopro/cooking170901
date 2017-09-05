<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/info.css">
<body>
		<table class="type09">
			<thead>
				<tr>
					<th scope="cols">My Page</th>
					<th scope="cols">회원 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">이메일</th>
					<td>${dto.email}</td>
				</tr>
				<tr>
					<th scope="row">이름</th>
					<td>${dto.name}</td>
				</tr>
				<tr>
					<th scope="row">통신사</th>
					<td>${dto.telecom}</td>
				</tr>
				<tr>
					<th scope="row">전화번호</th>
					<td>${dto.phone}</td>
				</tr>
				<tr>
					<th scope="row">우편번호</th>
					<td>${dto.post}</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td>${dto.addr1}<br>
					<hr>${dto.addr2}</td>
				</tr>
			</tbody>
		</table>
		<div>
		<button onclick="location.href='edit'" type="button">정보
					수정</button>
		</div>
</body>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>