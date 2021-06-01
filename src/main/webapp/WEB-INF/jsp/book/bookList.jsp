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
						<c:if test="${empty member }">
						<li class="nav-item"><a href="/member/login"
								class="nav-link">로그인</a></li>
							<li class="nav-item"><a href="/member/signup"
								class="nav-link">회원가입</a></li>
						</c:if>
						<c:if test="${!empty member }">
						<p>${member[0].name} 님 환영합니다</p>
						<li class="nav-item"><a href="/member/logout"
								class="nav-link">로그아웃</a></li>
						</c:if>
					</header>
					<h3>
					<a href="/member/myPage" class="button small">마이페이지</a>
				</h3>
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
					<form method="get" action="/book/list" id="searchForm">
					<div class="fields">
						<div class="field">
							<div style="text-align:center">
								<select name="type">
									<option value="T" ${pageMaker.cri.type == 'T' ? 'selected' : ''}>제목</option>
								</select>
								<input id="keyword" name="keyword" type="text" value="${pageMaker.cri.keyword}"/>
								<input id="pageNum" type="hidden" value="${pageMaker.cri.pageNum}"/>
								<input id="amount" type="hidden" value="${pageMaker.cri.amount}"/>
								<a href="#" class="search button primary icon solid fa-search">검색</a>
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
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<%-- <input type="hidden" name="type" value="${pageMaker.cri.type}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}"> --%>
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
	var result = '${result}';
	var memberResult = '${memberResult}';
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
	
	if(result == 'success'){
		alert('대여완료!');
	}else if(result == 'fail'){
		alert('대여하지 못합니다.')
	}
	$("#searchForm a").on("click", function(e){
		e.preventDefault();
		
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");
			return false;
		}
		
		searchForm.find("input[name='pageNum']").val("1");
		searchForm.submit();
	});
	
	if(memberResult == 'success'){
		alert('로그인완료!');
	}
	
	</script>
</body>
</html>