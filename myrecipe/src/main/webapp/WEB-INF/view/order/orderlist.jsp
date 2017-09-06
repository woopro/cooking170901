<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/view/template/header.jsp" %>
    <div align="center">
	<h1>"${memberInfo.name }"님의 주문 내역</h1>
	
	
	<table border="1" width="600">
		<tbody>
			<tr>
				<th>주문자 이름</th>
				<td>${memberInfo.name }</td>
			</tr>
			<tr>
				<th>메뉴 이름</th>
				<td>${menuInfo.name }</td>
			</tr>
			<tr>
				<th>수량</th>
				<td>${orderInfo.menu_cnt }</td>
			</tr>
			<tr>
				<th>옵션1수량</th>
				<td>${orderInfo.op1_cnt }</td>
			</tr>
			<tr>
				<th>옵션2수량</th>
				<td>${orderInfo.op2_cnt }</td>
			</tr>
			<tr>
				<th>옵션3수량</th>
				<td>${orderInfo.op3_cnt }</td>
			</tr>
			<tr>
				<th>총 주문 금액</th>
				<td>${orderInfo.totalprice }</td>
			</tr>
			<tr>
				<th>배송지 정보</th>
				<td>(${orderInfo.post }) ${orderInfo.addr1 } ${orderInfo.addr2 }</td>
			</tr>
			<tr>
				<th>주문일자</th>
				<td>${orderInfo.order_date }</td>
			</tr>
			<tr>
				<th>배송예정일</th>
				<td>${orderInfo.want_date }</td>
			</tr>
			<tr>
				<th>주문 상태</th>
				<td>${orderInfo.stat}</td>
			</tr>
		</tbody>
	</table>
</div>
    
    
<%@ include file="/WEB-INF/view/template/footer.jsp" %>