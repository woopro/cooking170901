<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file= "/WEB-INF/view/template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

<div class="page">
	<div class="row center line-red">
		<table border="1" class="user-table area-50">
			<tr>
				<th width="50%">아이디</th>
				<td>${bdto.email}</td>			
			</tr>
			<tr>
				<th>이름</th>
				<td>${bdto.name}</td>			
			</tr>
			<tr>
				<th>질문유형/제목</th>
				<td>[${bdto.category}] ${bdto.title}</td>			
			</tr>
			<tr>
				<th>이메일</th>
				<td>${bdto.email}</td>			
			</tr>
			<tr>
				<th>내용</th>
				<td>${bdto.detail2}</td>			
			</tr>
		</table>
		<div class="row center">
			<input type="button" class="input-btn" value="수정">
			<input type="button" class="input-btn" value="삭제">
			<input type="button" class="input-btn" value="목록">
		</div>
	</div>
</div>


<%@ include file= "/WEB-INF/view/template/footer.jsp" %>