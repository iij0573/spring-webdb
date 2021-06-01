<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/resources/assets/css/main.css" />
<body class="is-preload">
	<!-- Main -->
	<div id="main">
		<div class="wrapper">
			<div class="inner">

				<!-- Elements -->
				<header class="major">
					<h1><a href="/book/list">Book</a></h1>
					<p>도서 상세보기</p>
				</header>
				<!-- Table -->
				<h3>
					<a href="/book/list" class="button small">목록 보기</a>
				</h3>
				<div class="content">
					<div class="form">
					<form method="post" action="/book/borrow" id="actionForm">
						<div class="fields">
							<div class="field">
								<h4>번호</h4>
								<input name="bookNum" type="text" value="${book[0].bookNum}" readonly />
							</div>
							<div class="field">
								<h4>제목</h4>
								<input name="title" type="text" value="${book[0].title}" readonly />
							</div>
							<div class="field">
								<h4>저자</h4>
								<input name="author" type="text" value="${book[0].author}" readonly />
							</div>
							<div class="field">
								<h4>평점</h4>
								<input name="grade" type="text" value="${book[0].grade}" readonly />
							</div>
							<div class="field">
								<h4>재고</h4>
								<input name="stock" type="text" value="${book[0].stock}" readonly />
							</div>
							
							<div class="field">
								<h4>대여가능여부</h4>
								<input name="rental" type="text" value="${book[0].rental}"
									readonly />
							
							</div>
								<c:if test="${!empty member }">
							<ul class="actions special">
								<li>
									<input type="submit" class="button" value="대여" />
								</li>
							</ul>
								</c:if>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	</script>

</body>
</html>