<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/myrecipe/css/main.css">

<c:choose>
	<c:when test="${fail=='no'}">
		<script>
			window.alert("비밀번호를 입력하여 주세요");
		</script>
	</c:when>
	<c:when test="${success=='yes'}">
		<script>
			window.close();
			console.log(${next});
		</script>
	</c:when>
	<c:otherwise>
		<script>
			window.alert("비밀번호가 잘못되었습니다.");
		</script>
	</c:otherwise>	
</c:choose>

<div class="empty-row"></div>
<div>
	<div align="center">
		<form action="pw" method="post">
		<input type="hidden" name="next" value="${next}">
		<input type="hidden" name="board_no" value="${no}">
		<input type="password" name="pw" placeholder="비밀번호 입력">
		<input type="submit" value="입력">
	</form>
	</div>
</div>

