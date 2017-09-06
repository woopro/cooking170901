<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
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
<link rel="stylesheet" type="text/css" href="/recipe/css/main.css">
<html>
<head>
<title>Menu List</title>
<style>
div {
   align-content: center;
   text-align: center;
   border: 1px;
   border-color: darkgreen;
   border-style: solid;
}

.menu_grid {
   width: 70%;
   height: auto;
   border: 1px;
   border-color: red;
   border-style: dotted;
}

.menu_item {
   width: 32%;
   height: 200px;
   background-color: darkgray;
   display: inline-block;
}

</style>
</head>
<body>
   <div>
      <h1>Menu List !</h1>
      <div class="table_center">
         <table border="1"  id="myTable" class="tablesorter">
            <thead>
               <tr>
                  <th>no</th>
                  <th>메뉴명</th>
                  <th>가격</th>
                  <th>종류</th>
                  <th>옵션1 내용</th>
                  <th>옵션1 가격</th>
                  <th>옵션2 내용</th>
                  <th>옵션2 가격</th>
                  <th>옵션3 내용</th>
                  <th>옵션3 가격</th>
                  <th>판매 상태</th>
                  <th>판매 등급</th>
                  <th>등록 일시</th>
                  <th colspan="2"><input type="button" value="신규 등록" onclick="location.href='madd?no='"></th>
               </tr>
            </thead>
            <tbody>
               <c:forEach var="mdto" items="${mdto}">
                  <tr>
                     <td>${mdto.menu_no}</td>
                     <td>${mdto.name}</td>
                     <td>${mdto.price}</td>
                     <td>${mdto.type}</td>
                     <td>${mdto.op1_name}</td>
                     <td>${mdto.op1_price}</td>
                     <td>${mdto.op2_name}</td>
                     <td>${mdto.op2_price}</td>
                     <td>${mdto.op3_name}</td>
                     <td>${mdto.op3_price}</td>
                     <td>${mdto.stat}</td>
                     <td>${mdto.stat_grade}</td>
                     <td>${mdto.reg}</td>
                     <td><input type="button" value="수정" onclick="location.href='mdetail?no=${mdto.menu_no}'"></td>
                     <td><input type="button" value="삭제" onclick="location.href='mdelete?no=${mdto.menu_no}'"></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </div>
   </div>

</body>
</html>

<%@ include file="/WEB-INF/view/template/footer.jsp"%>