<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file= "/WEB-INF/view/template/header.jsp" %>

<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href="/myrecipe/css/main.css">
<script type="text/javascript" src="/myrecipe/js/ckeditor/ckeditor.js"></script>

<script>
	var cnt = 1;
	$(document).ready(function(){
		$("#add").on("click", function(){
			if(cnt==3) {
				alert("파일 첨부는 최대 3개까지 가능합니다.");				
			} else {
				cnt = cnt+1;
// 				var input = "<br><input type='file' id='file' name='file'>";
				var input = $("<input/>").attr({id:"file"+cnt, type:"file", name:"file"});
				var img = $("<img>").attr({src:"/myrecipe/image/x.jpg", 
						id:"img"+cnt, style:"vertical-align: middle;",width:"20", height:"20"});
				$(".addFile").append("<br>").append(img).append(input);
			}
		});
		$("#img2").on("click", function(){
			console.log("img2 클릭");
			$("#file2").remove();
			cnt-=1;
		});
		
		$("#img3").on("click", function() {
			console.log("img3 클릭");
			$("#file3").remove();
			cnt-=1;
		});
	});

	function input() {
	    var max = 2000;
	    var length = 0;
	    var num= 0;
	    var ta = document.querySelector("#content");
	    //console.log(ta.value)
	    length = ta.value.length;
	    
	    for(var i=0; i<length; i++) {
	        if(!(ta.value.charCodeAt(i) >='0' && ta.value.charCodeAt(i)<='126')) {
	            num+=2;
	        } else num+=1;
	    }
	    
	    if(num > max) {
	        if(event.keyCode != '8') {
	            alert("최대 "+max+"글자 만큼만 작성가능합니다.");
	            ta.value = ta.value.substring(0, length-1);
	            return;   
	        }
	    } else {
	        var text = "("+num+ " / "+ max+")";
	        var target = document.querySelector("#limit");
	        target.innerHTML = "<small>"+text+"</small>";
	    }
	    //console.log(typeof length);
	    //console.log(length);
	}
</script>

<div class="page" align="center">
	<div class="area-100 center">
		<div class="row center" style="background-color: rgb(239,239,239)">
			<h1>문의 글 작성하기</h1>
		</div>
		<form action="bwrite" method="post" name="form" enctype="multipart/form-data">
			<div class="row center">
				<table class="user-table area-70">
					<tr>
						<th>질문유형</th>
						<td>
							<select name="category" class="select_box area-30">
								<option value="">선택하세요</option>
								<option>배송지연/불만</option>
								<option>반품문의</option>
								<option>환불문의</option>
								<option>교환/취소문의</option>
								<option>상품정보문의</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>
<%-- 							<input type="email" class="user-input area-90" name="email" value="${mdto.email}" readonly> --%>
							<input type="email" class="user-input area-90" name="email" required>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
<%-- 							<input type="text" class="user-input area-90" name="name" value="${mdto.name}" readonly> --%>
							<input type="text" class="user-input area-90" name="name">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
<%-- 							<input type="email" class="user-input area-90" name="email" value="${mdto.email}" readonly> --%>
							<input type="email" class="user-input area-90" name="email">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" class="user-input area-90" name="title" placeholder="제목" required>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="10" cols="30" name="detail" id="content"
										onkeydown="input();" onkeyup="input();" class="user-area"></textarea>
										<script>
											CKEDITOR.replace('content');
										</script><br>
							<div id="limit"></div>
						</td>
					</tr>
					<tr>
						<th>파일 첨부</th>
						<td class="addFile">
							<input type="file" name="file" id="file">
							<input type="button" id="add" class="input-btn" value="추가" style="float:right;">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" class="user-input" name="pw" placeholder="비밀번호" required>
						</td>
					</tr>
				</table>
			</div>
			<div class="btn_area area-70 center">
				<span class="left">
					<input type="button" class="input-btn" value="목록" onclick="history.back();">
				</span>
				<input type="submit" class="input-btn" value="등록">
				<input type="reset" class="input-btn" value="취소">
			</div>
		</form>
	</div>
</div>


<%@ include file= "/WEB-INF/view/template/footer.jsp" %>