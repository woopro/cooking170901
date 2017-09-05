<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/template/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<title>로그인 페이지 입니다</title>
</head>
<body>
	<section class="container">
		<article class="half">
			<h1>My Cooking</h1>
			<div class="tabs">
				<span class="tab signin active"><a href="#Lognin">Logn in</a></span>
				<span class="tab signup"><a href="#signup">Sign</a></span>
			</div>
			<div class="content">
				<div class="signin-cont cont">
					<form action="login" method="post">
						<input type="email" name="email" id="email" class="inpt"
							required="required" placeholder="Your email"> <label
							for="email">Your email</label> <input type="password"
							name="password" id="password" class="inpt" required="required"
							placeholder="Your password"> <label for="password">Your
							password</label> <input type="checkbox" id="remember" class="checkbox"
							checked> <label for="remember">Remember me</label>
						<div class="submit-wrap">
							<input type="submit" value="Logn in" class="submit"> <a
								href="#" class="more">Forgot your password?</a>
						</div>
					</form>
				</div>
				<div class="signup-cont cont">
					<form action="signcheck" method="post">
						<input type="email" name="email" id="name" class="inpt"
							required="required" placeholder="Your email?">
							<input type="text" name="name" id="name" class="inpt"
							required="required" placeholder="Your name?">
						<div class="submit-wrap">
							<input type="submit" value="Sign up" class="submit"> 
							<a href="tac" class="more">Terms and conditions</a>
						</div>
					</form>
				</div>
			</div>
		</article>
		<div class="half bg"></div>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">
		$('.tabs .tab').click(function() {
			if ($(this).hasClass('signin')) {
				$('.tabs .tab').removeClass('active');
				$(this).addClass('active');
				$('.cont').hide();
				$('.signin-cont').show();
			}
			if ($(this).hasClass('signup')) {
				$('.tabs .tab').removeClass('active');
				$(this).addClass('active');
				$('.cont').hide();
				$('.signup-cont').show();
			}
		});
		$('.container .bg').mousemove(
				function(e) {
					var amountMovedX = (e.pageX * -1 / 30);
					var amountMovedY = (e.pageY * -1 / 9);
					$(this).css('background-position',
							amountMovedX + 'px ' + amountMovedY + 'px');
				});
	</script>
</body>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>