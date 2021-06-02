<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/assets/css/main.css" />
</head>
<body class="is-preload ">
	<div id="main">
		<div class="wrapper">
			<div class="inner">
				<div>
					<header class="major">
						<h1 class="home">로그인</h1>

						<form method="post" action="/member/login">
							<div class="row gtr-uniform">
								<div class="col-6 col-12-xsmall">
									<input type="text" name="sessionId" placeholder="id"><br>
								</div>
								<div class="col-6 col-12-xsmall">
									<input type="text" name="password" placeholder="password">
								</div>
							</div>
							<div class="col-12">
									<ul class="actions">
										<li>
											<input type="submit" value="로그인" class="primary">
										</li>
									</ul>
								</div>
						</form>

					</header>
				</div>
			</div>
		</div>
	</div>
	<script>
		var memberResult = "${memberResult}";
		
		if(memberResult == 'error'){
			alert('로그인실패. 다시 시도하세요.');
		}
	</script>


</body>
</html>