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
					<c:if test="${empty member }">
						<p>로그인후 이용해주세요</p>
					</c:if>
					<c:if test="${!empty member }">
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
										<td>${book[0].bookNum}</td>
										<td><a class="goGet" href="${book.bookNum}">${book[0].title}</a></td>
										<td class="author">${book[0].author}</td>
										<td class="grade">${book[0].grade}</td>
										<td class="stock">${book[0].stock}</td>
										<td class="rental">${book[0].rental}</td>
									</tr>
								</c:forEach>
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