<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="is-preload">
	<!-- Main -->
	<div id="main">
		<div class="wrapper">
			<div class="inner">

				<!-- Elements -->
				<header class="major">
					<h1>book</h1>
					<p>게시글 상세보기</p>
				</header>
				<!-- Table -->
				<h3>
					<a href="/book/list" class="button small">목록 보기</a>
				</h3>
				<div class="content">
					<div class="form">
						<div class="fields">
							<div class="field">
								<h4>번호</h4>
								<input name="bookNum" type="text" value="${book.bookNum}" readonly />
							</div>
							<div class="field">
								<h4>제목</h4>
								<input name="title" type="text" value="${book.title}" readonly />
							</div>
							<div class="field">
								<h4>저자</h4>
								<textarea name="author" rows="6" style="resize: none" readonly>${book}</textarea>
							</div>
							<div class="field">
								<h4>작성자</h4>
								<input name="writer" type="text" value="${book.author}"
									readonly />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>