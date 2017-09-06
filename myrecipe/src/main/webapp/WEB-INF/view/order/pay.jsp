<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/template/header.jsp"%>
<h1>결제창</h1>
<form action="pay" method="post">
	<input id="paybtn" name="pay" type="submit" value="결제확인">
	<input id="canclebtn" name="cancle" type="submit" value="결제취소">
</form>


<style>
#paybtn {
	padding: 7px 30px 7px 30px;
	font-size: 15px;
	font-weight: bold;
}

#canclebtn {
	padding: 7px 30px 7px 30px;
	font-size: 15px;
	font-weight: bold;
}
</style>


<%@ include file="/WEB-INF/view/template/footer.jsp"%>