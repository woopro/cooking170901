<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/info.css">
<title>정보 수정 페이지</title>
</head>
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
				<td><input name="email" type="email" class="form-control"
					id="InputEmail" value="${dto.email}" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="row">패스워드</th>
				<td><input name="password" type="password" class="form-control"
					id="InputPassword1" placeholder="변경할 패스워드를 입력하세요"></td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td><input name="name" type="text" class="form-control"
					id="username" value="${dto.name}" required></td>
			</tr>
			<tr>
				<th scope="row">전화번호</th>
				<td><input name="phone" type="tel" class="form-control"
					id="username" value="${dto.phone}"><SELECT NAME=telecom
					SIZE=1>
						<OPTION VALUE=kt>KT</OPTION>
						<OPTION VALUE=lg>LG</OPTION>
						<OPTION VALUE=sk>SK</OPTION>
						<OPTION VALUE=알뜰폰 SELECTED>알뜰폰</OPTION>
				</SELECT></td>
			</tr>
			<tr>
				<th scope="row">우편번호</th>
				<td><input type="text" id="sample6_postcode"
					value="${dto.post}" name="post" class="form-control"> <input
					type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"
					class="btn btn-info"></td>
			</tr>
			<tr>
				<th scope="row">주소</th>
				<td><input type="text" id="sample6_address"
					value="${dto.addr1}" name="addr1" class="form-control"> <br>
					<hr> <input type="text" id="sample6_address2"
					value="${dto.addr2}" name="addr2" class="form-control"></td>
			</tr>
		</tbody>
	</table>
	<div>
		<button onclick="location.href='edit'" type="button">정보 수정</button>
	</div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
    }
</script>
</body>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>
