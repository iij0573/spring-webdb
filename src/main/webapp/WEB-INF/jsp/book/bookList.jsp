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
						<h1 class="home">Book</h1>
						<p>도서 목록</p>
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
									<c:forEach var="book" items="${list}">
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
					<div class="big-width" style="text-align: center">
					<c:if test="${pageMaker.prev}">
						<a class="changePage" href="${pageMaker.startPage - 1}"><code>&lt;</code></a>
					</c:if>
					<c:forEach var="num" begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}">
						<c:choose>
							<c:when test="${pageMaker.cri.pageNum eq num}">
								<code>${num}</code>
							</c:when>
							<c:otherwise>
								<a class="changePage" href="${num}"><code>${num}</code></a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageMaker.next}">
						<a class="changePage" href="${pageMaker.endPage + 1}"><code>&gt;</code></a>
					</c:if>
					
					<form id="actionForm" action="/book/list" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="type" value="${pageMaker.cri.type}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
					</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
	var actionForm = $("#actionForm");
	
	$(document).ready(function(){
		$(".goGet")
				.on("click", function(e) {
							e.preventDefault();
							actionForm.append("<input type='hidden' name='bookNum' value='"
											+ $(this).attr("href") + "'>");
							actionForm.attr("action", "/book/get");
							actionForm.submit();
						});
	});
	
	$(".changePage").on("click", function(e) {
		//a 태그의 동작을 막고 원하는 url에 페이지 번호를 넘겨주어야 한다.
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	})
	
	</script>
</body>
</html>