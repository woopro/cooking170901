<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/__jquery.tablesorter/jquery.tablesorter.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/__jquery.tablesorter/themes/blue/style.css" type="text/css">
<script>
   $(document).ready(function() {
   $("#myTable").tablesorter( {
		sortList: [[0,1]],
		headers:{
		1:{sorter:"Integer"},
		debug:true
	   }}
); })
</script>
<table id="myTable" class="tablesorter">
   <thead>
      <tr>
         <th id="no">회원 번호</th>
         <th id="email">회원 이메일</th>
         <th id="name">회원 이름</th>
         <th id="phone">회원 전화번호</th>
         <th id="post">회원 우편번호</th>
         <th id="addr1">회원 주소</th>
         <th id="addr2">회원 상세</th>
         <th id="reg">가입일</th>
      </tr>
      </thead>
      <tbody>
       <c:forEach var="list" items="${memberlist}">
         <tr>
            <td>${list.no}</td>
            <td>${list.email}</td>
            <td>${list.name}</td>
            <td>${list.telecom}${list.phone}</td>
            <td>${list.post}</td>
            <td>${list.addr1}</td>
            <td>${list.addr2}</td>
            <td>${list.reg}</td>
         </tr>
            </c:forEach>
      </tbody>
</table>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>