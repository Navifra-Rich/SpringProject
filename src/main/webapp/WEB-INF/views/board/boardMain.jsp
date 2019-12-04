<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<style>
.head {
	margin: 0;
	width: 100%;
	height: 20%;
	background-color: black;
}

.middle {
	height: 300px;
	position: relative;
}

.content {
	display: inline-block;
	position: absolute;
	left: 10%;
	height: 100%;
	overflow: hidden;
	background-color: green;
	width: 100%;
}

.left {
	display: inline-block;
	margin: 0;
	width: 10%;
	height: 100%;
	background-color: red;
}

.right {
	display: inline-block;
	margin: 0;
	width: 10%;
	height: 100%;
	width: 10%;
}
</style>

<body>
	<div class="head"></div>
	<div class="middle">
		<div class="left"></div>
		<div class="content">
			<form action="/ex/Board/selectPost" method="GET" id="selectPost">
			</form>
			<table class="table table-striped table-hover">

				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>댓글 수</th>
						<th>시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="0" end="4" var="post" items="${viewAll}"
						varStatus="status">
						<tr>
							<td>${post.num}</td>
							<td class="title" id="${post.num}">${post.title}</td>
							<td>${post.content}</td>
							<td>${post.time}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="write">
		<form action="/ex/Board/writeForm" method="POST">
			<input type="submit" value="글쓰기">
		</form>
	</div>
	<div class="page">
		<p>현재 페이지${page.curPage}</p>
		<p>시작 페이지${page.startPage}</p>
		<p>끝 페이지${page.endPage}</p>
		<p>삐로롱</p>
		<c:forEach varStatus="idx" var="page" begin="${page.startPage}"
			end="${page.endPage}">
			<p>${idx.current }</p>
		</c:forEach>
		<p>뾰로롱</p>
	</div>
</body>
<script>
	$('.title').on('click', function() {
		var frm = $('#selectPost');
		frm.attr('method', 'GET');
		frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
		frm.submit();
	});
</script>
</html>
