<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>


<script type="text/javascript">
	$(function() {
		var type = 
		//첫번째 텝을 제외하고 나머지 텝들은 숨김
		$(".tab_content").hide();
		$(".tab_content:first").show();
		
		//텝 클릭 시 메뉴 리스트 변경
		$("ul.tabs li").click(function() {
			$("ul.tabs li").removeClass("active").css("color", "#333");
			//$(this).addClass("active").css({"color": "darkred","font-weight": "bolder"});
			$(this).addClass("active").css("color", "blue");
			$(".tab_content").hide()
			var activeTab = $(this).attr("rel");
			$("#" + activeTab).fadeIn()
		});
		
		//마우스 리스너(텝에 마우스온 : 빨강 / 마우스리브 : 검정)
		$("ul.tabs li").mouseenter(function() {
			$(this).addClass("textcolor").css("color", "red");
			$(this).mouseleave(function() {
				$("ul.tabs li").removeClass("textcolor").css("color", "black");
				$(".active").css("color", "blue");
			});
		});
		
	});
</script>

<div>
	<h1>Menu List !<span></span></h1>
	<div id="container">
		<ul class="tabs">
			<li class="active" rel="tab1" value="">전체 메뉴</li>
			<li rel="tab2" value="밥">밥</li>
			<li rel="tab3" value="국">국</li>
			<li rel="tab3" value="찌개">찌개</li>
		</ul>
		<div class="tab_container">
			<div id="tab1" class="tab_content">
				<div class="tab_category">
					<c:forEach var="mdto" items="${mdto}">
						<div class="line-blue center width-30 dis_in_block">
							<div class="line-red center margin-10 width-200p height-250p">
								<a href="order?no=${mdto.menu_no}"> 
								<img src="http://via.placeholder.com/200x150">
									<h4><label>${mdto.name}</label></h4>
									<h4><label>${mdto.price} 원</label></h4>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="tab2" class="tab_content">
				<div class="tab_category">
					<c:forEach var="mdto" items="${mdto}">
						<!-- 현재 type -->
						<c:set var="type" value="${mdto.type}"></c:set>
						<!-- 기준 type -->
<%-- 						<c:set var="settype" value="${stype}"></c:set> --%>
						<c:if test="${type=='밥'}">
							<div class="line-blue center width-30 dis_in_block">
								<div class="line-red center margin-10 width-200p height-250p">
									<a href="order?no=${mdto.menu_no}"> 
									<img src="http://via.placeholder.com/200x150">
										<h4><label>${mdto.name}</label></h4>
										<h4><label>${mdto.price} 원</label></h4>
									</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div id="tab3" class="tab_content">
				<div class="tab_category">
					<c:forEach var="mdto" items="${mdto}">
						<c:set var="type">${mdto.type}</c:set>
						<c:if test="${type=='국'}">
							<div class="line-blue center width-30 dis_in_block">
								<div class="line-red center margin-10 width-200p height-250p">
									<a href="order?no=${mdto.menu_no}"> 
									<img src="http://via.placeholder.com/200x150">
										<h4><label>${mdto.name}</label></h4>
										<h4><label>${mdto.price} 원</label></h4>
									</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div id="tab4" class="tab_content">
				<div class="tab_category">
					<c:forEach var="mdto" items="${mdto}">
						<c:set var="type">${mdto.type}</c:set>
						<c:if test="${type=='찌개'}">
							<div class="line-blue center width-30 dis_in_block">
								<div class="line-red center margin-10 width-200p height-250p">
									<a href="order?no=${mdto.menu_no}"> 
									<img src="http://via.placeholder.com/200x150">
										<h4><label>${mdto.name}</label></h4>
										<h4><label>${mdto.price} 원</label></h4>
									</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
		</div>
	</div>

	<div></div>
</div>


<%@ include file="/WEB-INF/view/template/footer.jsp"%>
