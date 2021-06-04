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
							<div class="userInfo">

							</div>
					</header>
					<form method="post" action="/member/myPage">
						<h3>
							<input type="submit" class="button small" value="마이페이지"></a>
						</h3>
					</form>
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
					<form method="post" action="/book/search">
						<div class="fields">
							<div class="field">
								<div style="text-align: center">
									<select name="type">
										<option value="{book.title}">제목</option>
										<input type="text" name="title" value="${book.title }"></input>
									</select>
							<c:choose >
							<c:when test="${!empty list }">
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
							</c:when>
							</c:choose>
								<a onclick="location.href='/book/search?title=${book.title }'" class="search button primary icon solid fa-search">검색</a>
								</div>
							</div>
						</div>
					</form>
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
							<input type="hidden" name="pageNum"
								value="${pageMaker.cri.pageNum}"> <input type="hidden"
								name="amount" value="${pageMaker.cri.amount}">
							<%-- <input type="hidden" name="type" value="${pageMaker.cri.type}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}"> --%>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>

		var actionForm = $("#actionForm");
		var result = '${result}';
		var memberResult = '${memberResult}';
		$(document).ready(function() {

			// 세션 정보
			// var sessionId = sessionStorage.getItem("sessionId");

			var sessionId = '<%=(String)session.getAttribute("sessionId")%>';

			var $userInfo = $('.userInfo');

			if(sessionId !== "null") {
				$userInfo.append(
						'<p>'+ sessionId +' 님환영합니다</p>' +
						'<li class="nav-item">' +
						'<a href="/member/logout" class="nav-link">로그아웃</a> ' +
						'</li>');
			}else{
				$userInfo.append(
						'<li class="nav-item">' +
						'<a href="/member/signup" class="nav-link">회원가입</a>' +
						'</li> ' +
						'<li class="nav-item">' +
						'<a href="/member/login" class="nav-link">로그인</a>' +
						'</li>');
			}

			$(".goGet").on("click", function(e) {
				e.preventDefault();
				actionForm.append("<input type='hidden' name='bookNum' value='" + $(this).attr("href") + "'>");
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

		if (result == 'success') {
			alert('대여완료!');
		} else if (result == 'fail') {
			alert('대여하지 못합니다.');
		}
		$("#searchForm a").on("click", function(e) {
			e.preventDefault();

			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드를 입력하세요");
				return false;
			}

			searchForm.find("input[name='pageNum']").val("1");
			searchForm.submit();
		});

		if (memberResult == 'success') {
			alert('로그인완료!');
		}
		if(result == 'returnSuccess'){
			alert('반납완료!!!');
		}
	</script>
</body>
</html>