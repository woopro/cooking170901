<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file= "/WEB-INF/view/template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>

<script>
	$(document).ready(function(){
		var xOffset = 10;
		var yOffset = 30;
		var toggle = false;
		
		$(document).on("click", ".pic", function(e){
			if(!toggle) {
				$("#prev").append("<p id='preview'><img src='"+ $(this).attr("src") + "' width='400px'/></p>");
				$("#preview")
					.css("top",(e.pageY - xOffset) + "px")
					.css("left", (e.pageX - yOffset) + "px")
					.fadeIn("fast");
				toggle = true;
			} else {
				$("#preview").remove();
				toggle = false;
			}
			
		});
	});	
</script>

<div class="page">
	<div class="empty-row"></div>
	<div class="area-100 center">
		<div class="row center" style="background-color: rgb(239,239,239)">
			<h1>내 문의 글</h1>
		</div>
		<div class="row center">
			<table class="info-table area-70" border="1">
				<tr>
					<th class="title">아이디</th>
					<td>${bdto.email}</td>			
				</tr>
				<tr>
					<th class="title">이름</th>
					<td>${bdto.name}</td>			
				</tr>
				<tr>
					<th class="title">질문유형/제목</th>
					<td>
						<c:choose>
							<c:when test="${empty bdto.category}">
								${bdto.title}
							</c:when>
							<c:otherwise>
								[${bdto.category}] ${bdto.title}
							</c:otherwise>
						</c:choose>
					</td>			
				</tr>
				<tr>
					<th class="title">이메일</th>
					<td>${bdto.email}</td>			
				</tr>
				<tr>
					<th class="title">문의내용</th>
					<td class="detail">${bdto.detail2}</td>			
				</tr>
				<tr>
					<th clss="title">업로드 파일</th>
					<td id="prev">
						<c:choose>
							<c:when test="${empty bdto.filename}">
								업로드된 파일 없음
							</c:when>
							<c:otherwise>
								<img src = "http://placehold.it/50X50" 
										class="pic" height="50" style="vertical-align: middle;">
								<a href="download/${bdto.filename}?name=${bdto.name}">${bdto.filename}</a> (${bdto.filesize} byte)
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th class="title">작성일</th>
					<td>${bdto.auto}</td>			
				</tr>
			</table>
		</div>
		<div class="btn_area area-70 center">
			<span class="left">
				<input type="button" class="input-btn" value="목록" onclick="location.href='blist#q2';">
				<input type="button" class="input-btn" value="답글">
			</span>
			<input type="button" class="input-btn" value="수정" onclick="location.href='bedit?no=${bdto.no}';">
			<input type="button" class="input-btn" value="삭제" onclick="location.href='bdelete?no=${bdto.no}';">
		</div>
	</div>
</div>


<%@ include file= "/WEB-INF/view/template/footer.jsp" %>