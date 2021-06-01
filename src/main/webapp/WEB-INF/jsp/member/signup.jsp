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
						<h1 class="home">회원가입</h1>
						<form method="post" action="/member/signup">
							<div class="form-group">
								<label for="id">아이디 </label> <input type="text" name="sessionId"
									placeholder="id" value="${member.id}"><br>
							</div>
							<div class="form-group">
								<label for="name">이름 </label> <input type="text" name="name"
									placeholder="name" value="${member.name}"><br>
							</div>
							<div class="form-group">
								<label for="email">이메일 </label> <input type="text" name="email"
									placeholder="email" value="${member.email}"><br>
							</div>
							<div class="form-group">
								<label for="password">비밀번호 </label> <input type="text"
									name="password" placeholder="password"
									value="${member.password}"><br>
							</div>
							<div class="form-group">
								<label for="tel">전화번호 </label> <input type="text" name="tel"
									placeholder="tel" value="${member.tel}"><br>
							</div>

							<div class="col-12">
								<ul class="actions">
									<li><input type="submit" value="회원가입" class="primary"></li>
								</ul>
							</div>
						</form>
					</header>
				</div>
			</div>
		</div>
	</div>

</body>
</html>