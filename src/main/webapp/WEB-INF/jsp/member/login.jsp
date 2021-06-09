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
									<li><input type="submit" value="로그인" class="primary">
									</li>
									<li><input onclick="location.href='/member/signup'"
										type="button" value="회원가입" class="primary"></li>
								</ul>
							</div>
							<div>
								<ul class="actions">
									<li><input onclick="location.href='/member/findId'" type="button" value="아이디 찾기" class="primary">
									</li>
									<!-- Get방식 -->
									<li><input onclick="location.href='/member/findPw'" 
										type="button" value="비밀번호 찾기" class="primary"></li>
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
		var result = "${result}";
		var id = "${id}";
		var newPw = "${newPw}";
		if(memberResult == 'error'){
			alert('로그인실패. 다시 시도하세요.');
		}
		if(result == 'signup'){
			alert('회원가입 성공! 로그인하세요');
		}
		
		if(result == 'IdNull'){
			alert("로그인먼저 하세요.");
		}
		
		if(result == 'idSuccess'){
			alert("당신의 아이디는"+ id + "입니다.");
		}
		
		if(result == 'updatePw'){
			alert(newPw + "새로운 비밀번호입니다.")
		}
	</script>


</body>
</html>