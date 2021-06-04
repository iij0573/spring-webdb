<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/assets/css/main.css" />
<body class="is-preload ">
	<div id="main">
		<div class="wrapper">
			<div class="inner">
				<div>
					<c:if test="${empty id}">
						alert("로그인 후 이용해주세요.");
						<a location.href="/member/login"></a>
					</c:if>
					<c:if test="${!empty id }">
						<header class="major">
							<h1 class="home">마이페이지</h1>
						</header>
						<h3>
							<a href="/book/list" class="button small">목록 보기</a>
						</h3>
						<div class="table-wrapper">
							<table>
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>저자</th>
										<th>평점</th>
									</tr>
								</thead>
								<tbody>
									<form mehtod="post" action="/member/return">
										<c:forEach var="book" items="${book}">
											<tr>
												<td>${book.bookNum}</td>
												<td><a class="goGet" href="${book.bookNum}">${book.title}</a></td>
												<td class="author">${book.author}</td>
												<td class="grade">${book.grade}</td>
												<input type="submit" class="button" value="반납"></input>
												</tr>
										</c:forEach>
									</form>
								</tbody>
							</table>
						</div>

					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>