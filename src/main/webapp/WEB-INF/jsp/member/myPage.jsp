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
					<header class="major">
						<h1 class="home">마이페이지</h1>
					</header>
					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>저자</th>
									<th>평점</th>
									<th>재고</th>
									<th>대여가능여부</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="book" items="${book}">
									<tr>
										<td>${book.bookNum}</td>
										<td><a class="goGet" href="${book.bookNum}">${book.title}</a></td>
										<td class="author">${book.author}</td>
										<td class="grade">${book.grade}</td>
										<td class="stock">${book.stock}</td>
										<td class="rental">${book.rental}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>